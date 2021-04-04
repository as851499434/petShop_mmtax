package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.MerchantAccount;
import com.mmtax.business.domain.MerchantAccountDetail;
import com.mmtax.business.mapper.MerchantAccountDetailMapper;
import com.mmtax.business.service.IMerchantAccountDetailService;
import com.mmtax.common.enums.PaymentEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class MerchantAccountDetailServiceImpl implements IMerchantAccountDetailService {

    @Autowired
    MerchantAccountDetailMapper merchantAccountDetailMapper;

    /**
     * 加入账户变动记录
     * @param paymentAmount 变动的金额
     * @param accountBefore
     * @param accountAfter
     * @param status 变动是否成功
     * @param serialNum 关联的流水号
     * @param type 1充值或0代付
     */
    @Override
    public void insertMerchantAccountDetail(BigDecimal paymentAmount, MerchantAccount accountBefore,
                                            MerchantAccount accountAfter, int status,
                                            String serialNum, int type,int paymentType) {
        MerchantAccountDetail merchantAccountDetail = new MerchantAccountDetail();
        merchantAccountDetail.setType(type);
        merchantAccountDetail.setPaymentType(paymentType);
        merchantAccountDetail.setPaymentAmountBefore(accountBefore.getAmount());
        merchantAccountDetail.setPaymentFrozenAmountBefore(accountBefore.getFrozenAmount());
        merchantAccountDetail.setPaymentUsableAmountBefore(accountBefore.getUsableAmount());
        merchantAccountDetail.setPaymentChannel(PaymentEnum.BANK.name());
        merchantAccountDetail.setPaymentAmount(paymentAmount);
        merchantAccountDetail.setPaymentAmountAfter(accountAfter.getAmount());
        merchantAccountDetail.setPaymentUsableAmountAfter(accountAfter.getUsableAmount());
        merchantAccountDetail.setPaymentFrozenAmountAfter(accountAfter.getFrozenAmount());
        merchantAccountDetail.setOrderSerialNum(serialNum);
        merchantAccountDetail.setMerchantId(accountBefore.getMerchantId());
        merchantAccountDetail.setStatus(status);
        merchantAccountDetail.setCreateTime(DateUtil.date());
        merchantAccountDetail.setUpdateTime(DateUtil.date());
        merchantAccountDetailMapper.insertSelective(merchantAccountDetail);
    }

}
