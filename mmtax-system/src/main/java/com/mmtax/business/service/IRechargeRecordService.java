package com.mmtax.business.service;

import com.mmtax.business.domain.MerchantAccount;

import java.math.BigDecimal;

public interface IRechargeRecordService {

    void addAccountDetailAndRechargeRecord(MerchantAccount accountBefore, BigDecimal addAmount,
                                           String orderSerialNum, int status);

}
