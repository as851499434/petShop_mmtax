package com.mmtax.business.service;

import com.mmtax.business.domain.BatchPaymentDetail;
import com.mmtax.business.domain.TrxOrder;

import java.math.BigDecimal;
import java.util.List;

/**
 * 批量打款详细订单记录 服务层
 * 
 * @author meimiao
 * @date 2020-09-08
 */
public interface IBatchPaymentDetailService
{
    void addRecord(TrxOrder trxorder, BigDecimal taxRate, BigDecimal charge);
}
