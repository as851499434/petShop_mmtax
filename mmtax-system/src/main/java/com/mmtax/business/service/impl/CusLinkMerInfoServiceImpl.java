package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.*;
import com.mmtax.business.mapper.*;
import com.mmtax.common.enums.UseBigRateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.ICusLinkMerInfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工关联商户 服务层实现
 *
 * @author meimiao
 * @date 2020-10-10
 */
@Service
public class CusLinkMerInfoServiceImpl implements ICusLinkMerInfoService {

    @Autowired
    private CusLinkMerInfoMapper cusLinkMerInfoMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private MerchantInfoMapper merchantInfoMapper;
    @Autowired
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    private CustomerEsignInfoMapper customerEsignInfoMapper;

    @Override
    public CusLinkMerInfo coverCusLinkMerInfo(Integer cusId, Integer merId,Integer cusEsignId) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(cusId);
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merId);
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merId);
        CusLinkMerInfo cusLinkMerInfo = new CusLinkMerInfo();
        cusLinkMerInfo.setCustomerId(cusId);
        cusLinkMerInfo.setRealName(customerInfo.getRealName());
        cusLinkMerInfo.setCertificateNo(customerInfo.getCertificateNo());
        cusLinkMerInfo.setMerchantId(merId);
        cusLinkMerInfo.setMerchantName(merchantInfo.getMerchantName());
        if(null != cusEsignId) {
            cusLinkMerInfo.setCusEsignId(cusEsignId);
        }
        cusLinkMerInfo.setTaxSourceId(onlinePaymentInfo.getTaxSourceCompanyId());
        cusLinkMerInfo.setMonthUseRate(UseBigRateEnum.NORMALRATE.getStatus());
        cusLinkMerInfo.setCreateTime(DateUtil.date());
        cusLinkMerInfo.setUpdateTime(DateUtil.date());
        cusLinkMerInfoMapper.insertSelective(cusLinkMerInfo);
        return cusLinkMerInfo;
    }

    @Override
    public void initTblCusLinkMerInfo() {
        List<CustomerInfo> customerInfos = customerInfoMapper.selectAll();
        for (CustomerInfo customerInfo : customerInfos) {
            CusLinkMerInfo cusLinkMerInfo = new CusLinkMerInfo();
            cusLinkMerInfo.setCustomerId(customerInfo.getId());
            cusLinkMerInfo.setRealName(customerInfo.getRealName());
            cusLinkMerInfo.setMerchantId(customerInfo.getMerchantId());
            MerchantInfo merchantInfo = new MerchantInfo();
            merchantInfo.setId(customerInfo.getMerchantId());
            merchantInfo = merchantInfoMapper.selectOne(merchantInfo);
            cusLinkMerInfo.setMerchantName(merchantInfo.getMerchantName());

            OnlinePaymentInfo onlinePaymentInfo = new OnlinePaymentInfo();
            onlinePaymentInfo.setMerchantId(customerInfo.getMerchantId());
            onlinePaymentInfo = onlinePaymentInfoMapper.selectOne(onlinePaymentInfo);
            cusLinkMerInfo.setTaxSourceId(onlinePaymentInfo.getTaxSourceCompanyId());

            CustomerEsignInfo customerEsignInfo = new CustomerEsignInfo();
            customerEsignInfo.setStatus(1);
            customerEsignInfo.setIdNumber(customerInfo.getCertificateNo());
            customerEsignInfo.setTaxSourceId(cusLinkMerInfo.getTaxSourceId());
            List<CustomerEsignInfo> customerEsignInfos = customerEsignInfoMapper.select(customerEsignInfo);
            if(customerEsignInfos.size() != 0){
                cusLinkMerInfo.setCusEsignId(customerEsignInfos.get(0).getId());
            }
            cusLinkMerInfo.setCertificateNo(customerInfo.getCertificateNo());


            cusLinkMerInfo.setMonthUseRate(customerInfo.getMonthUseRate());
            cusLinkMerInfo.setCreateTime(DateUtil.date());
            cusLinkMerInfo.setUpdateTime(DateUtil.date());

            cusLinkMerInfoMapper.insertSelective(cusLinkMerInfo);
        }
    }
}
