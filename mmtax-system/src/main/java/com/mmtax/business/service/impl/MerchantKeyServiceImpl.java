package com.mmtax.business.service.impl;

import com.mmtax.business.domain.MerchantKey;
import com.mmtax.business.domain.TianJinPaymentInfo;
import com.mmtax.business.dto.ManagerSeretKeyDTO;
import com.mmtax.business.mapper.MerchantKeyMapper;
import com.mmtax.business.mapper.TianJinPaymentInfoMapper;
import com.mmtax.business.service.IMerchantKeyService;
import com.mmtax.business.tianjindto.TianJinSecretDTO;
import com.mmtax.common.annotation.DataScope;
import com.mmtax.system.domain.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/16 19:38
 */
@Service
public class MerchantKeyServiceImpl implements IMerchantKeyService {


    @Resource
    MerchantKeyMapper merchantKeyMapper;
    @Resource
    TianJinPaymentInfoMapper tianJinPaymentInfoMapper;

    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerSeretKeyDTO> getListManagerSeretKey(ManagerSeretKeyDTO managerSeretKeyDTO) {
        return merchantKeyMapper.getListManagerSeretKey(managerSeretKeyDTO);
    }

    @Override
    public MerchantKey getMerchantKey(Integer id) {
        return merchantKeyMapper.selectByPrimaryKey(id);
    }

    @Override
    public MerchantKey getMerchantKeyByMerchantId(Integer merchantId) {
        MerchantKey merchantKey = new MerchantKey();
        merchantKey.setMerchantId(merchantId);
        MerchantKey rMerchantKey = merchantKeyMapper.selectOne(merchantKey);
        return rMerchantKey;
    }

    @Override
    public void updateMerchantKey(MerchantKey merchantKey) {
        merchantKeyMapper.updateByPrimaryKeySelective(merchantKey);
    }

    @Override
    public TianJinSecretDTO getTianJinSecret(Integer merchantId) {
        TianJinSecretDTO tianJinSecretDTO = new TianJinSecretDTO();
        TianJinPaymentInfo tianJinPaymentInfo = tianJinPaymentInfoMapper.getTianJinPaymentInfoByMerchantId(merchantId);
        tianJinSecretDTO.setServerUserUuid(tianJinPaymentInfo.getServerUserUuid());
        tianJinSecretDTO.setCustomerAccountUuid(tianJinPaymentInfo.getCustomerAccountUuid());
        MerchantKey merchantKey = getMerchantKeyByMerchantId(merchantId);
        tianJinSecretDTO.setAppKey(merchantKey.getAppKey());
        tianJinSecretDTO.setDesKey(merchantKey.getDesKey());
        tianJinSecretDTO.setCallBackAddress(merchantKey.getCallBackAddress());
        tianJinSecretDTO.setWhiteUrl(merchantKey.getWhiteUrl());
        return tianJinSecretDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTianJinSecret(TianJinSecretDTO tianJinSecretDTO) {
        TianJinPaymentInfo tianJinPaymentInfo = tianJinPaymentInfoMapper.getTianJinPaymentInfoByMerchantId(tianJinSecretDTO.getMerchantId());
        tianJinPaymentInfo.setCustomerAccountUuid(tianJinSecretDTO.getCustomerAccountUuid());
        tianJinPaymentInfo.setServerUserUuid(tianJinSecretDTO.getServerUserUuid());
        tianJinPaymentInfoMapper.updateByPrimaryKeySelective(tianJinPaymentInfo);
        MerchantKey merchantKey = getMerchantKeyByMerchantId(tianJinSecretDTO.getMerchantId());
        merchantKey.setCallBackAddress(tianJinSecretDTO.getCallBackAddress());
        merchantKey.setWhiteUrl(tianJinSecretDTO.getWhiteUrl());
        merchantKeyMapper.updateByPrimaryKeySelective(merchantKey);


    }
}
