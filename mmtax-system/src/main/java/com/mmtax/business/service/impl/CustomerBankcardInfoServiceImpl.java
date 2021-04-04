package com.mmtax.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.mmtax.business.domain.CustomerBankcardInfo;
import com.mmtax.business.domain.CustomerInfo;
import com.mmtax.business.dto.BankcardAlipayInfoDTO;
import com.mmtax.business.dto.DateQueryDTO;
import com.mmtax.business.dto.ManagerCustomerBankCardDTO;
import com.mmtax.business.dto.ManagerCustomerBankCardQueryDTO;
import com.mmtax.business.mapper.CustomerBankcardInfoMapper;
import com.mmtax.business.mapper.CustomerInfoMapper;
import com.mmtax.business.service.ICustomerBankcardInfoService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.common.constant.Constants;
import com.mmtax.common.constant.onlinebank.OnlineConstants;
import com.mmtax.common.enums.BankCodeNameEnum;
import com.mmtax.common.enums.BankcardOrAlipayBindEnum;
import com.mmtax.common.enums.VerifyStatusEnum;
import com.mmtax.common.enums.VerifyTypeEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.EnumUtil;
import com.mmtax.common.utils.onlinebank.CardbinQueryResultDTO;
import com.mmtax.common.utils.redis.RedisLockConstans;
import com.mmtax.common.utils.redis.RedisLockUtil;
import com.mmtax.common.utils.redis.RedisTimeConstans;
import com.mmtax.common.utils.redis.RedisUtil;
import com.mmtax.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 员工绑定银行卡 服务层实现
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Service
public class CustomerBankcardInfoServiceImpl extends BaseServiceImpl implements ICustomerBankcardInfoService {
    @Autowired
    private CustomerBankcardInfoMapper customerBankcardInfoMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private ISysConfigService sysConfigService;

    @Override
    public CustomerBankcardInfo queryBankcardInfo(String name,String mobile,String bankCard,Integer customerId) {
        //同一员工同一银行卡加锁
        String value = UUID.randomUUID().toString();
        String key = RedisLockConstans.CUSTOMER_INFO_BANK_LOCK + customerId + "_" + bankCard;
        logger.info("customer_info_bank_lock key{}, start:{}", key,
                DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_MS_FORMAT));
        RedisLockUtil.getRedisLock(key, value, RedisTimeConstans.ONLINE_LOCK_TIME);
        logger.info("customer_info_bank_lock key{}, end:{}", key,
                DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_MS_FORMAT));
        CustomerBankcardInfo customerBankcardInfo = customerBankcardInfoMapper.selectByCustomerIdAndBankAccountNo(
                customerId, bankCard);
        //存在改卡记录且与网商已绑定
        if (null != customerBankcardInfo) {
            if (BankcardOrAlipayBindEnum.BIND.getStatus().equals(customerBankcardInfo.getBindStatus())) {
                return customerBankcardInfo;
            }
        }
        //不存在卡信息
        if (null == customerBankcardInfo) {
            CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
            customerBankcardInfo = new CustomerBankcardInfo();
            customerBankcardInfo.setCustomerId(customerId);
            customerBankcardInfo.setCustomerNo(customerInfo.getCustomerNo());
            customerBankcardInfo.setBankName(null);
            customerBankcardInfo.setCardType(OnlineConstants.CARD_TYPE);
            customerBankcardInfo.setBankAccountNo(bankCard);
            customerBankcardInfo.setAccountName(name);
            customerBankcardInfo.setCertificateType(customerInfo.getCertificateType());
            customerBankcardInfo.setCertificateNo(customerInfo.getCertificateNo());
            customerBankcardInfo.setReservedMobile(mobile);
            //-1表示个体工商户上传，做四要素验证
            Integer verifyType = -1 != customerInfo.getMerchantId()
                    ?Integer.valueOf(sysConfigService.selectConfigByKey(Constants.VERIFY_TYPE))
                    :VerifyTypeEnum.FOURELEMENTS.getType();
            customerBankcardInfo.setVerifyType(verifyType);
            customerBankcardInfo.setVerifyStatus(VerifyStatusEnum.UNVERIFY.getStatus());
            customerBankcardInfo.setBindStatus(BankcardOrAlipayBindEnum.UNBIND.getStatus());
            customerBankcardInfo.setCreateTime(DateUtil.date());
        }
        String bankId = onlineBankService.bankcardBind(customerBankcardInfo);
        customerBankcardInfo.setBankId(bankId);
        customerBankcardInfo.setVerifyStatus(VerifyStatusEnum.VERIFY.getStatus());
        customerBankcardInfo.setBindStatus(BankcardOrAlipayBindEnum.BIND.getStatus());
        customerBankcardInfo.setUpdateTime(DateUtil.date());
        if (null == customerBankcardInfo.getId()) {
            customerBankcardInfoMapper.insertSelective(customerBankcardInfo);
        } else {
            customerBankcardInfoMapper.updateByPrimaryKeySelective(customerBankcardInfo);
        }
        return customerBankcardInfo;

    }

    @Override
    public List<ManagerCustomerBankCardDTO> listManagerCustomerBankCardDTO(ManagerCustomerBankCardQueryDTO queryDTO) {
        DateQueryDTO dateQueryDTO = getDateQueryDTOMatchNorm(queryDTO.getStartDate(), queryDTO.getEndDate());
        queryDTO.setStartDate(dateQueryDTO.getStartDate());
        queryDTO.setEndDate(dateQueryDTO.getEndDate());
        List<ManagerCustomerBankCardDTO> dtoList = customerBankcardInfoMapper.listManagerCustomerBankCardDTO(queryDTO);
        return dtoList;
    }

    @Override
    public List<BankcardAlipayInfoDTO> listBankcard(Integer customerId) {
        List<BankcardAlipayInfoDTO> bankcards = customerBankcardInfoMapper.listBankcard(customerId);
        bankcards.forEach(one->{
            one.setBankName(getBnakName(one.getAccountNo(),customerId));
            BankCodeNameEnum bankCodeNameEnum = EnumUtil.getEnumByAttributes(
                    BankCodeNameEnum.class,String.class,one.getBankName());
            one.setBankCode(null == bankCodeNameEnum?"UNKNOW":bankCodeNameEnum.name());
            one.setBankImage(null == bankCodeNameEnum?
                    "http://meimsh.oss-cn-hangzhou.aliyuncs.com/oauth/16075044536221607504453622635.png"
                    : bankCodeNameEnum.getBankImage());
        });
        return bankcards;
    }

    @Override
    public String getBnakName(String bankCardNo,Integer customerId){
        if(!RedisUtil.exists(bankCardNo)) {
            //调用第三方查询银行名称
            CardbinQueryResultDTO.CardBinInfo cardBinInfo = onlineBankService.cardbinQuery(bankCardNo, customerId);
            Integer subscript = cardBinInfo.getBank_name().indexOf("银行");
            String bankName = cardBinInfo.getBank_name().substring(0,subscript+2);
            //加上随机时间防止缓存雪崩-todo
            RedisUtil.put(bankCardNo, bankName);
            return bankName;
        }
        return RedisUtil.get(bankCardNo);
    }
}
