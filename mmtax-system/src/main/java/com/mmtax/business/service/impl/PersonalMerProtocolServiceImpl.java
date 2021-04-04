package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.EsignFlow;
import com.mmtax.business.domain.OrderStatusInfo;
import com.mmtax.business.domain.PersonalMerProtocol;
import com.mmtax.business.domain.PersonalMerchant;
import com.mmtax.business.mapper.EsignFlowMapper;
import com.mmtax.business.mapper.OrderStatusInfoMapper;
import com.mmtax.business.mapper.PersonalMerProtocolMapper;
import com.mmtax.business.mapper.PersonalMerchantMapper;
import com.mmtax.common.enums.ExpireStatusEnum;
import com.mmtax.common.enums.PerMerStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPersonalMerProtocolService;

/**
 * 个体工商户协议 服务层实现
 * 
 * @author meimiao
 * @date 2020-11-27
 */
@Service
@Slf4j
public class PersonalMerProtocolServiceImpl implements IPersonalMerProtocolService
{
    @Autowired
    PersonalMerProtocolMapper personalMerProtocolMapper;
    @Autowired
    EsignFlowMapper esignFlowMapper;
    @Autowired
    PersonalMerchantMapper personalMerchantMapper;
    @Autowired
    OrderStatusInfoMapper orderStatusInfoMapper;

    @Override
    public void handNotify(String flowId,String accountId,Integer signResult,String signTime,String resultDescription) {
        EsignFlow esignFlow = esignFlowMapper.seletByFlowId(flowId);
        PersonalMerProtocol personalMerProtocol = new PersonalMerProtocol();
        personalMerProtocol.setEsignFlowId(esignFlow.getId());
        personalMerProtocol = personalMerProtocolMapper.selectOne(personalMerProtocol);
        if(null == personalMerProtocol){
            log.info("不属于个体工商户签约，返回");
            return;
        }
        personalMerProtocol.setSignStatus(signResult);
        personalMerProtocol.setSignTime(signTime);
        personalMerProtocol.setComment(resultDescription);
        personalMerProtocol.setUpdateTime(DateUtil.date());
        personalMerProtocolMapper.updateByPrimaryKeySelective(personalMerProtocol);
        if(2 == signResult){
            PersonalMerchant personalMerchant = new PersonalMerchant();
            personalMerchant.setApplyNumber(personalMerProtocol.getApplyNumber());
            personalMerchant = personalMerchantMapper.selectOne(personalMerchant);
            OrderStatusInfo orderStatusInfo = new OrderStatusInfo();
            orderStatusInfo.setApplyId(personalMerchant.getId());
            orderStatusInfo = orderStatusInfoMapper.selectOne(orderStatusInfo);
            orderStatusInfo.setOrderStatus(PerMerStatusEnum.PROCESS.getCode());
            orderStatusInfo.setUpdateTime(DateUtil.date());
            orderStatusInfoMapper.updateByPrimaryKeySelective(orderStatusInfo);
        }
    }

    @Override
    public void dealDocExpire(String flowId) {
        EsignFlow esignFlow = esignFlowMapper.seletByFlowId(flowId);
        PersonalMerProtocol personalMerProtocol = new PersonalMerProtocol();
        personalMerProtocol.setEsignFlowId(esignFlow.getId());
        personalMerProtocol = personalMerProtocolMapper.selectOne(personalMerProtocol);
        if(null == personalMerProtocol){
            log.info("不属于个体工商户签约，返回");
            return;
        }
        personalMerProtocol.setExpireStatus(ExpireStatusEnum.EXPIRE.getCode());
        personalMerProtocol.setUpdateTime(DateUtil.date());
        personalMerProtocolMapper.updateByPrimaryKeySelective(personalMerProtocol);
    }
}
