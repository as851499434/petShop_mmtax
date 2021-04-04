package com.mmtax.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.mmtax.business.domain.CustomerAlipayInfo;
import com.mmtax.business.domain.CustomerInfo;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.BankcardAlipayInfoDTO;
import com.mmtax.business.dto.DateQueryDTO;
import com.mmtax.business.dto.ManagerCustomerAlipayDTO;
import com.mmtax.business.dto.ManagerCustomerAlipayQueryDTO;
import com.mmtax.business.mapper.CustomerAlipayInfoMapper;
import com.mmtax.business.mapper.CustomerInfoMapper;
import com.mmtax.business.service.ICustomerAlipayInfoService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.common.enums.BankcardOrAlipayBindEnum;
import com.mmtax.common.enums.VerifyStatusEnum;
import com.mmtax.common.utils.redis.RedisLockConstans;
import com.mmtax.common.utils.redis.RedisLockUtil;
import com.mmtax.common.utils.redis.RedisTimeConstans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工绑定支付宝 服务层实现
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Service
public class CustomerAlipayInfoServiceImpl extends BaseServiceImpl implements ICustomerAlipayInfoService
{
    @Autowired
    private CustomerAlipayInfoMapper customerAlipayInfoMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private IOnlineBankService onlineBankService;

    @Override
    public CustomerAlipayInfo queryAlipayInfo(String name,String mobile,String bankCard,Integer customerId) {
        //同一员工同一支付宝加锁
        String value = UUID.randomUUID().toString();
        String key = RedisLockConstans.CUSTOMER_INFO_ALIPAY_LOCK + customerId + "_" + bankCard;
        logger.info("customer_info_alipay_lock key{}, start:{}", key,
                DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_MS_FORMAT));
        RedisLockUtil.getRedisLock(key, value, RedisTimeConstans.ONLINE_LOCK_TIME);
        logger.info("customer_info_alipay_lock key{}, end:{}", key,
                DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_MS_FORMAT));
        CustomerAlipayInfo customerAlipayInfo = customerAlipayInfoMapper.selectByCustomerIdAndAccountNo(customerId
                , bankCard);
        //存在记录且已绑定网商
        if (null != customerAlipayInfo) {
            if (BankcardOrAlipayBindEnum.BIND.getStatus().equals(customerAlipayInfo.getBindStatus())) {
                return customerAlipayInfo;
            }
        }
        if (null == customerAlipayInfo) {
            customerAlipayInfo = new CustomerAlipayInfo();
            CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
            customerAlipayInfo.setCustomerId(customerId);
            customerAlipayInfo.setCustomerNo(customerInfo.getCustomerNo());
            customerAlipayInfo.setAccountNo(bankCard);
            customerAlipayInfo.setAccountName(name);
            customerAlipayInfo.setCertificateNo(customerInfo.getCertificateNo());
            customerAlipayInfo.setMobileNo(mobile);
            customerAlipayInfo.setVerifyStatus(VerifyStatusEnum.UNVERIFY.getStatus());
            customerAlipayInfo.setBindStatus(BankcardOrAlipayBindEnum.UNBIND.getStatus());
            customerAlipayInfo.setCreateTime(DateUtil.date());
        }
        String bankId = onlineBankService.alipayBind(customerAlipayInfo);
        customerAlipayInfo.setBankId(bankId);
        customerAlipayInfo.setVerifyStatus(VerifyStatusEnum.VERIFY.getStatus());
        customerAlipayInfo.setBindStatus(BankcardOrAlipayBindEnum.BIND.getStatus());
        customerAlipayInfo.setUpdateTime(DateUtil.date());
        if (null == customerAlipayInfo.getId()) {
            customerAlipayInfoMapper.insertSelective(customerAlipayInfo);
        } else {
            customerAlipayInfoMapper.updateByPrimaryKeySelective(customerAlipayInfo);
        }
        return customerAlipayInfo;
    }

    @Override
    public List<ManagerCustomerAlipayDTO> listManagerCustomerAlipayDTO(ManagerCustomerAlipayQueryDTO queryDTO) {
        DateQueryDTO dateQueryDTO = getDateQueryDTOMatchNorm(queryDTO.getStartDate(), queryDTO.getEndDate());
        queryDTO.setStartDate(dateQueryDTO.getStartDate());
        queryDTO.setEndDate(dateQueryDTO.getEndDate());
        List<ManagerCustomerAlipayDTO> dtoList = customerAlipayInfoMapper.listManagerCustomerAlipayDTO(queryDTO);
        return dtoList;
    }

    @Override
    public List<BankcardAlipayInfoDTO> listAlipay(Integer customerId) {
        return customerAlipayInfoMapper.listAlipay(customerId);
    }
}
