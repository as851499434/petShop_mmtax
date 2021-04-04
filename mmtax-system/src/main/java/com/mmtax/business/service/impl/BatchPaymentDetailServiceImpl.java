package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.BatchPaymentDetail;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.mapper.BatchPaymentDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IBatchPaymentDetailService;

import java.math.BigDecimal;

/**
 * 批量打款详细订单记录 服务层实现
 * 
 * @author meimiao
 * @date 2020-09-08
 */
@Service
public class BatchPaymentDetailServiceImpl implements IBatchPaymentDetailService
{
    @Autowired
    private BatchPaymentDetailMapper batchPaymentDetailMapper;


    @Override
    public void addRecord(TrxOrder trxorder, BigDecimal taxRate,BigDecimal charge) {
        BatchPaymentDetail batchPaymentDetail = new BatchPaymentDetail();
        batchPaymentDetail.setBatchPaymentRecordId(trxorder.getBatchPaymentRecordId());
        batchPaymentDetail.setBatchNo(trxorder.getBatchNo());
        batchPaymentDetail.setOrderSerialNum(trxorder.getOrderSerialNum());
        batchPaymentDetail.setAmount(trxorder.getAmount());
        batchPaymentDetail.setTaxRate(taxRate);
        batchPaymentDetail.setCharge(charge);
        batchPaymentDetail.setActualAmount(trxorder.getActualAmount());
        batchPaymentDetail.setCreateTime(DateUtil.date());
        batchPaymentDetail.setUpdateTime(DateUtil.date());
        batchPaymentDetailMapper.insertSelective(batchPaymentDetail);
    }
}
