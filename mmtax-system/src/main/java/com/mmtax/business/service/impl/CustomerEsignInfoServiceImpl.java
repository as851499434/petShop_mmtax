package com.mmtax.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.ManagerSignCustomerInfoAgreementDTO;
import com.mmtax.business.dto.SignInfoDTO;
import com.mmtax.business.mapper.CustomerEsignInfoMapper;
import com.mmtax.business.mapper.CustomerInfoMapper;
import com.mmtax.business.mapper.CustomerProtocolMapper;
import com.mmtax.business.mapper.OnlinePaymentInfoMapper;
import com.mmtax.business.service.IEsignFlowService;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.MerchantIdUtil;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.esign.comm.CacheKeyConstant;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.helper.AccountHelper;
import com.mmtax.common.utils.esign.helper.TokenHelper;
import com.mmtax.common.utils.redis.RedisLockConstans;
import com.mmtax.common.utils.redis.RedisLockUtil;
import com.mmtax.common.utils.redis.RedisTimeConstans;
import com.mmtax.common.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.ICustomerEsignInfoService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 员工签约 服务层实现
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Service
@Slf4j
public class CustomerEsignInfoServiceImpl implements ICustomerEsignInfoService
{
    @Autowired
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    private CustomerEsignInfoMapper customerEsignInfoMapper;
    @Autowired
    private CustomerProtocolMapper customerProtocolMapper;
    @Autowired
    private IEsignFlowService esignFlowService;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Override
    public CustomerEsignInfo createPerson(String idcardNo,String name,String mobile, Integer merchantId
            ,Integer taxSourceId) {
        //-1表示个体工商户上传
        if(null == taxSourceId && -1 != merchantId) {
            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
            taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
        }
        //同一身份证同一税源地加锁,限制多个线程操作同一个身份证相关数据操作
        String value = UUID.randomUUID().toString();
        String key = RedisLockConstans.ESIGN_CUSTOMER_LOCK + idcardNo + "_" + taxSourceId;
        log.info("esign_customer_lock key{}, start:{}", key, DateUtil.formatDateTime(DateUtil.date()));
        RedisLockUtil.getRedisLock(key, value, RedisTimeConstans.ESIGN_CUSTOMER_LOCK_TIME);
        log.info("customer_info_lock key{}, end:{}", key, DateUtil.formatDateTime(DateUtil.date()));
        CustomerEsignInfo customerEsignInfo = customerEsignInfoMapper.selectByIdCardAndTaxSourceId(idcardNo,taxSourceId);
        if(null != customerEsignInfo){
            CustomerProtocol customerProtocol = customerProtocolMapper.selectByAccountId(
                    customerEsignInfo.getAccountId(),merchantId);
            if(null != customerProtocol && -1 != merchantId){
                if(SignStatusEnum.SUCCESS.getCode().equals(customerProtocol.getSignStatus())) {
                    throw new BusinessException(name+"已签约");
                }
                Integer countSignSms = customerProtocolMapper.countSignSms(customerEsignInfo.getId(),merchantId
                        , SendSignUrlStatusEnum.SEND.getCode());
                if (countSignSms < SendMessageLimitNumberEnum.SENDNUMBER.getCode()) {
                    if(SignStatusEnum.INIT.getCode().equals(customerProtocol.getSignStatus())
                            || SignStatusEnum.NOSIGN.getCode().equals(customerProtocol.getSignStatus())){
                        customerProtocol.setSignStatus(SignStatusEnum.FAIL.getCode());
                        customerProtocol.setComment("已重新接收签约短信，本条失效");
                        customerProtocol.setUpdateTime(DateUtil.date());
                        customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);
                    }
                }
            }
            //只存了信息还没开户成功
            if(!CustomerEsignStatusEnum.OPEN.getCode().equals(customerEsignInfo.getStatus())){
                customerEsignInfo.setName(name);
                customerEsignInfo.setMobile(mobile);
                customerEsignInfo.setUpdateTime(DateUtil.date());
                customerEsignInfoMapper.updateByPrimaryKeySelective(customerEsignInfo);
            }
            return customerEsignInfo;
        }
        customerEsignInfo = new CustomerEsignInfo();
        customerEsignInfo.setThirdPartyUserId(MerchantIdUtil.getMerchantId());
        customerEsignInfo.setName(name);
        customerEsignInfo.setIdType(SignPersonIdTypeEnum.CHIDCARD.getCode());
        customerEsignInfo.setIdNumber(idcardNo);
        customerEsignInfo.setMobile(mobile);
        customerEsignInfo.setTaxSourceId(taxSourceId);
        customerEsignInfo.setStatus(CustomerEsignStatusEnum.NOOPEN.getCode());
        customerEsignInfo.setSealStatus(SealStatusEnum.NOCREATE.getCode());
        customerEsignInfo.setMerchantId(merchantId);
        customerEsignInfo.setAutoSignStatus(AutoSignStatusEnum.NOAUTOSIGN.getCode());
        customerEsignInfo.setCreateTime(DateUtil.date());
        customerEsignInfo.setUpdateTime(DateUtil.date());
        customerEsignInfoMapper.insertSelective(customerEsignInfo);
        return customerEsignInfo;
    }

    @Override
    public CustomerEsignInfo createEsignAccount(CustomerEsignInfo info,String mobile){
        boolean mobileChange = false;
        if(StringUtils.isNotEmpty(mobile) && !info.getMobile().equals(mobile)){
            log.info("员工{}手机号{}与数据库所存{}不符，更新",info.getName(),mobile,info.getMobile());
            info.setMobile(mobile);
            mobileChange = true;
        }
        CommonRequest req = new CommonRequest();
        if(CustomerEsignStatusEnum.OPEN.getCode().equals(info.getStatus())){
            log.info("员工{}已在易签宝开户",info.getName());
            if(!mobileChange){
                return info;
            }
            esignFlowService.setTokenToRedis();
            try {
                req = AccountHelper.updatePersonAcctByThirdId(info.getThirdPartyUserId(), info.getEmail()
                        , info.getMobile(), info.getName());
            }catch (Exception e){
                req.setCode(999);
                req.setMessage(e.getMessage());
            }
            if(0 != req.getCode()) {
                info.setComment("易签宝修改手机号失败：" + req.getMessage());
            }else{
                info.setMobile(mobile);
                info.setUpdateTime(DateUtil.date());
                customerEsignInfoMapper.updateByPrimaryKeySelective(info);
            }
            return info;
        }
        esignFlowService.setTokenToRedis();
        try {
            req = AccountHelper.createPersonAcct(info.getThirdPartyUserId(), info.getName(), info.getIdType()
                    , info.getIdNumber(), info.getMobile(), null);
        }catch (Exception e){
            req.setCode(999);
            req.setMessage(e.getMessage());
        }
        if(0 != req.getCode()){
            info.setComment(req.getMessage());
        }else {
            JSONObject data = req.getData();
            info.setAccountId(data.getString("accountId"));
            info.setStatus(CustomerEsignStatusEnum.OPEN.getCode());
            info.setComment("开户成功");
        }
        info.setUpdateTime(DateUtil.date());
        customerEsignInfoMapper.updateByPrimaryKeySelective(info);
        return info;
    }

    @Override
    public boolean deleteEsignAccount(CustomerEsignInfo info){
        esignFlowService.setTokenToRedis();
        CommonRequest req = new CommonRequest();
        try {
            req = AccountHelper.delPersonAcctByAcctId(info.getAccountId());
        }catch (Exception e){
            req.setCode(999);
            req.setMessage(e.getMessage());
        }
        if(0 != req.getCode()){
            throw new BusinessException("易签宝注销账户失败："+req.getMessage());
        }else {
            info.setTaxSourceId(1);
            info.setStatus(CustomerEsignStatusEnum.LOGOUT.getCode());
        }
        info.setUpdateTime(DateUtil.date());
        customerEsignInfoMapper.updateByPrimaryKeySelective(info);
        return true;
    }

    @Override
    public CustomerEsignInfo setAutoSign(Integer esignCusId,boolean openOrClose) {
        CustomerEsignInfo customerEsignInfo = customerEsignInfoMapper.selectByPrimaryKey(esignCusId);
        if(null == customerEsignInfo || !CustomerEsignStatusEnum.OPEN.getCode().equals(customerEsignInfo.getStatus())){
            throw new BusinessException("无法更改静默签署配置");
        }
        esignFlowService.setTokenToRedis();
        //更改静默签署配置
        CommonRequest req = new CommonRequest();
        try {
            if(openOrClose) {
                req = AccountHelper.setAutoSign(customerEsignInfo.getAccountId(), null);
                customerEsignInfo.setAutoSignStatus(AutoSignStatusEnum.AUTOSIGNSUCCESS.getCode());
            }else {
                req = AccountHelper.revokeAutoSign(customerEsignInfo.getAccountId());
                customerEsignInfo.setAutoSignStatus(AutoSignStatusEnum.NOAUTOSIGN.getCode());
            }
        }catch (Exception e){
            customerEsignInfo.setComment(req.getMessage());
        }
        customerEsignInfo.setUpdateTime(DateUtil.date());
        customerEsignInfoMapper.updateByPrimaryKeySelective(customerEsignInfo);
        return customerEsignInfo;
    }
}
