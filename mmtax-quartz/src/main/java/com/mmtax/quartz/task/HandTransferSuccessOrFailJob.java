package com.mmtax.quartz.task;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.MerchantAccount;
import com.mmtax.business.domain.OrderReimburse;
import com.mmtax.business.domain.TransferOrder;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.mapper.MerchantAccountMapper;
import com.mmtax.business.mapper.OrderReimburseMapper;
import com.mmtax.business.mapper.TransferOrderMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.*;
import com.mmtax.common.enums.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component("HandTransferSuccessOrFailJob")
@Slf4j
public class HandTransferSuccessOrFailJob {
    @Autowired
    private TransferOrderMapper transferOrderMapper;
    @Autowired
    private IMerchantAccountService merchantAccountService;
    @Autowired
    private MerchantAccountMapper merchantAccountMapper;
    @Autowired
    IMerchantAccountDetailService merchantAccountDetailService;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private ICustomerAccountService customerAccountService;
    @Autowired
    private TrxOrderMapper trxOrderMapper;
    @Autowired
    private ITrxOrderService trxOrderService;
    @Autowired
    private IMakeUpChargeService makeUpChargeService;
    @Autowired
    private OrderReimburseMapper orderReimburseMapper;

    public void handSuccessOrFailTransfer(){
        log.info("处理异步通知成功或失败的转账记录后续处理开始---------");
            List<TransferOrder> transferOrders = transferOrderMapper.selectByAsyncStatus(
                    AsyncStatusEnum.NOHANDLE.getStatus());
        transferOrders.forEach(one -> {
            try {
                Integer status = one.getStatus();
                BigDecimal transferAmount = one.getActualAmount();
                OrderReimburse orderReimburse = orderReimburseMapper.selectByOrderSerilNum(one.getOrderSerialNum());
                if(null != orderReimburse){
                    transferAmount = transferAmount.subtract(orderReimburse.getAmount());
                }
                //处理成功的
                if (TransferStatusEnum.PAID.getCode().equals(status)) {
                    one.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                    one.setUpdateTime(DateUtil.date());
                    transferOrderMapper.updateByPrimaryKeySelective(one);
                    //商户出账解冻并添加变动记录
                    MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                            one.getMerchantId(), transferAmount, UpdateAccountTypeOfMethod.PAYSUCCESS.getType());
                    MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(one.getMerchantId());
                    merchantAccountDetailService.insertMerchantAccountDetail(transferAmount, beforeAccount, afterAccount
                            , AccountDetailStatusEnum.SUCCESS.code, one.getOrderSerialNum()
                            , MerchantAccountDetailTypeEnum.PAID.code, DetailTypeEnum.PAYMENT.getCode());
                    //员工入账解冻并添加变动记录
                    TrxOrder trxOrder = trxOrderMapper.selectByOrderSerialNum(one.getOrderSerialNum());
                    BigDecimal customerAmount = one.getAmount();
                    customerAccountService.updateAccountAndInsertDetail(one.getCustomerId(), customerAmount
                            , AccountDetailTypeEnum.CREDIT_UNFREEZE, one.getOrderSerialNum());

                    //需要打到卡的订单进行打款
                    if(NeedPayCardEnum.NEED.getStatus().equals(trxOrder.getNeedPayCard())){
                        trxOrderService.composeTrxOrderPay(trxOrder,customerAmount);
                    }else {
                        trxOrderService.changeTrxOrderSuccess(trxOrder);
                    }
                }
                //处理失败的
                if (TransferStatusEnum.PAID_PENDING.getCode().equals(status)) {
                    one.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                    one.setUpdateTime(DateUtil.date());
                    transferOrderMapper.updateByPrimaryKeySelective(one);
                    //商户入账解冻并添加记录
                    MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                            one.getMerchantId(), transferAmount, UpdateAccountTypeOfMethod.PAYORMAKEUP.getType());
                    MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(one.getMerchantId());
                    merchantAccountDetailService.insertMerchantAccountDetail(transferAmount, beforeAccount, afterAccount
                            , AccountDetailStatusEnum.SUCCESS.code, one.getOrderSerialNum()
                            , MerchantAccountDetailTypeEnum.PAID.code, DetailTypeEnum.PAYMENT.getCode());
                    //员工出账解冻并添加记录
                    BigDecimal customerAmount = one.getAmount();
                    customerAccountService.updateAccountAndInsertDetail(one.getCustomerId(), customerAmount
                            , AccountDetailTypeEnum.DEBIT_UNFREEZE, one.getOrderSerialNum());
                    //标记订单为失败
                    TrxOrder trxOrder = trxOrderMapper.selectByOrderSerialNum(one.getOrderSerialNum());
                    trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
                    log.info("订单{}转账失败：{}",trxOrder.getOrderSerialNum(),one.getComment());
                    trxOrder.setComment("系统错误");
                    trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                    trxOrder.setUpdateTime(DateUtil.date());
                    trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
                    //标记补交服务费记录为失效
                    makeUpChargeService.invalidMakeUpCharge(trxOrder.getOrderSerialNum());
                    // 转账失败，发送异步通知给下游
                    onlineBankService.callBackNotifyOfOnline(trxOrder);
                }
            }catch (Exception e){
                log.error("HandTransferSuccessOrFailJob定时任务出错：",e);
            } });
        log.info("处理异步通知成功或失败的转账记录后续处理结束---------");
    }
}
