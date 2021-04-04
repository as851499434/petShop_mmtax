package com.mmtax.business.service;

import com.mmtax.business.domain.MerchantAccount;

import java.math.BigDecimal;

public interface IMerchantAccountDetailService {

    /**
     * 插入账户变动记录
     * @param paymentAmount
     * @param accountBefore
     * @param accountAfter
     * @param status
     * @param serialNum
     * @param type
     */
    void insertMerchantAccountDetail(BigDecimal paymentAmount, MerchantAccount accountBefore,
                                     MerchantAccount accountAfter, int status,
                                     String serialNum, int type,int paymentType);

}
