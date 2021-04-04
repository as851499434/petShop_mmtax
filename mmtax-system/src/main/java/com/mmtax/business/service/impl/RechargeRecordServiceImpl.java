package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.MerchantAccount;
import com.mmtax.business.domain.RechargeRecord;
import com.mmtax.business.mapper.MerchantAccountMapper;
import com.mmtax.business.mapper.RechargeRecordMapper;
import com.mmtax.business.service.IMerchantAccountDetailService;
import com.mmtax.business.service.IRechargeRecordService;
import com.mmtax.common.enums.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@Slf4j
public class RechargeRecordServiceImpl implements IRechargeRecordService {
    @Autowired
    MerchantAccountMapper merchantAccountMapper;
    @Autowired
    IMerchantAccountDetailService merchantAccountDetailService;
    @Autowired
    RechargeRecordMapper rechargeRecordMapper;

    /**
     * @param status 1成功 0失败
     */
    @Override
    public void addAccountDetailAndRechargeRecord(MerchantAccount accountBefore, BigDecimal addAmount,
                                                  String orderSerialNum, int status){
        //查询变动后的账户信息
        MerchantAccount accountAfter = merchantAccountMapper.getMerchantAccountByMerchantId(
                accountBefore.getMerchantId());
        merchantAccountDetailService.insertMerchantAccountDetail(addAmount, accountBefore, accountAfter
                , AccountDetailStatusEnum.SUCCESS.code, orderSerialNum, MerchantAccountDetailTypeEnum.RECHARGE.code
                ,DetailTypeEnum.RECHARGE.getCode());
        //加上充值记录
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setMerchantId(accountBefore.getMerchantId());
        rechargeRecord.setAmount(addAmount);
        rechargeRecord.setRechargeAmountBefore(accountBefore.getAmount());
        rechargeRecord.setRechargeAmountAfter(accountAfter.getAmount());
        rechargeRecord.setRechargeUsableAmountBefore(accountBefore.getUsableAmount());
        rechargeRecord.setRechargeUsableAmountAfter(accountAfter.getUsableAmount());
        rechargeRecord.setRechargeChannel(PaymentChannelEnum.ONLINE.name());
        rechargeRecord.setRechargeType(RechargeTypeEnum.UNDERLINE.name());
        rechargeRecord.setInvoiceStatus(0);
        rechargeRecord.setOrderSerialNum(orderSerialNum);
        rechargeRecord.setStatus(RechargeStatusEnum.SUCCESS.name());
        if(0 == status) {
            rechargeRecord.setStatus(RechargeStatusEnum.FAIL.name());
        }
        rechargeRecord.setCreateTime(DateUtil.date());
        rechargeRecordMapper.insertSelective(rechargeRecord);
    }
}
