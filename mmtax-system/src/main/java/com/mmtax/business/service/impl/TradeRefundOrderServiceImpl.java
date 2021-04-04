package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.*;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 退款记录 服务层实现
 * 
 * @author meimiao
 * @date 2020-09-18
 */
@Service
@Slf4j
public class TradeRefundOrderServiceImpl implements ITradeRefundOrderService
{
    @Autowired
    IOnlineBankService onlineBankService;
    @Autowired
    TrxOrderMapper trxOrderMapper;
    @Autowired
    TransferOrderMapper transferOrderMapper;
    @Autowired
    TradeRefundOrderMapper tradeRefundOrderMapper;
    @Autowired
    ICustomerAccountService customerAccountService;
    @Autowired
    IMerchantAccountService merchantAccountService;
    @Autowired
    MerchantAccountMapper merchantAccountMapper;
    @Autowired
    IMerchantAccountDetailService merchantAccountDetailService;
    @Autowired
    OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    OrderReimburseMapper orderReimburseMapper;

    @Override
    public String refundTranferOrder(String orderSerialNum) {
        TrxOrder trxOrder = trxOrderMapper.selectByOrderSerialNum(orderSerialNum);
        if(null == trxOrder){
            throw new BusinessException("未找到该订单");
        }
        if(NeedPayCardEnum.NEED.getStatus().equals(trxOrder.getNeedPayCard())
                && !TrxOrderStatusEnum.PAID_PENDING.getCode().equals(trxOrder.getOrderStatus())){
            throw new BusinessException("该订单并未挂起，不允许退款");
        }
        if(!AsyncStatusEnum.ALREADYHANDLE.getStatus().equals(trxOrder.getAsyncStatus())){
            throw new BusinessException("该订单正在解冻金额，请5分钟后再试");
        }
        TransferOrder transferOrder = transferOrderMapper.selectByOrderSerialNumAndStatus(orderSerialNum,null);
        if(null == transferOrder){
            throw new BusinessException("未找到该订单对应的转账订单");
        }
        if(!TransferStatusEnum.PAID.getCode().equals(transferOrder.getStatus())){
            throw new BusinessException("该订单对应的转账订单并未成功，无法退款");
        }
        if(!AsyncStatusEnum.ALREADYHANDLE.getStatus().equals(transferOrder.getAsyncStatus())){
            throw new BusinessException("该订单对应的转账订单正在解冻金额，请5分钟后再试");
        }
        OrderReimburse orderReimburse = orderReimburseMapper.selectByOrderSerilNum(orderSerialNum);
        BigDecimal overCharge = BigDecimal.ZERO;
        if(null != orderReimburse){
            overCharge = orderReimburse.getAmount();
        }
        TradeRefundOrder tradeRefundOrder = new TradeRefundOrder();
        tradeRefundOrder.setLinkRecordType(LinkRecordTypeEnum.TRANFER_ORDER.getType());
        tradeRefundOrder.setLinkSerialNum(transferOrder.getTransferSerialNum());
        tradeRefundOrder.setAmount(transferOrder.getAmount());
        tradeRefundOrder.setCharge(transferOrder.getCharge().subtract(overCharge));
        tradeRefundOrder.setActualAmount(transferOrder.getActualAmount().subtract(overCharge));
        tradeRefundOrder.setRefundSerialNum(ChanPayUtil.generateOutTradeNo());
        tradeRefundOrder.setStatus(RefundStatusEnum.INIT.getCode());
        tradeRefundOrder.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(transferOrder.getMerchantId());
        tradeRefundOrder.setTaxSourceId(onlinePaymentInfo.getTaxSourceCompanyId());
        tradeRefundOrder.setCreateTime(DateUtil.date());
        tradeRefundOrder.setUpdateTime(DateUtil.date());
        tradeRefundOrderMapper.insertSelective(tradeRefundOrder);
        //对应订单标为失败
        trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.getCode());
        trxOrder.setUpdateTime(DateUtil.date());
        trxOrderMapper.updateByPrimaryKeySelective(trxOrder);

        //灵工出账冻结
        Integer customerId = transferOrder.getCustomerId();
        BigDecimal customerAmount = tradeRefundOrder.getAmount();
        customerAccountService.updateAccountAndInsertDetail(customerId, customerAmount
                , AccountDetailTypeEnum.DEBIT_FREEZE, tradeRefundOrder.getRefundSerialNum());
        //商户入账冻结
        Integer merchantId = transferOrder.getMerchantId();
        BigDecimal merchanAmount = tradeRefundOrder.getActualAmount();
        MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                merchantId, BigDecimal.ZERO.subtract(merchanAmount)
                , UpdateAccountTypeOfMethod.PAYSUCCESS.getType());
        MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        merchantAccountDetailService.insertMerchantAccountDetail(merchanAmount,beforeAccount,afterAccount
                , AccountDetailStatusEnum.SUCCESS.code,tradeRefundOrder.getRefundSerialNum()
                , MerchantAccountDetailTypeEnum.REFUND.code,DetailTypeEnum.BACK.getCode());

        //网商退款
        OnlineCommonResultDTO resultDTO = onlineBankService.refundTransferOrder(tradeRefundOrder);
        tradeRefundOrder.setStatus(RefundStatusEnum.SUCESS.getCode());
        if(!"T".equals(resultDTO.getIs_success())){
            log.info("{}退款失败：{}",tradeRefundOrder.getRefundSerialNum(),resultDTO.getError_code());
            tradeRefundOrder.setStatus(RefundStatusEnum.FAIL.getCode());
            tradeRefundOrder.setComment(resultDTO.getError_message());
        }
        tradeRefundOrder.setUpdateTime(DateUtil.date());
        tradeRefundOrderMapper.updateByPrimaryKeySelective(tradeRefundOrder);
        return tradeRefundOrder.getRefundSerialNum();
    }

    @Override
    public String refundFail(String refundSerialNum) {
        TradeRefundOrder needRefundOrder = tradeRefundOrderMapper.selectByRefundSerialNum(refundSerialNum);
        if(null == needRefundOrder){
            throw new BusinessException("未找到退款订单");
        }
        if(!RefundStatusEnum.FAIL.getCode().equals(needRefundOrder.getStatus())){
            throw new BusinessException("退款订单未失败，不予处理");
        }
        if(!AsyncStatusEnum.ALREADYHANDLE.getStatus().equals(needRefundOrder.getAsyncStatus())){
            throw new BusinessException("退款订单未解冻，请解冻后再试");
        }
        TradeRefundOrder tradeRefundOrder = new TradeRefundOrder();
        BeanUtils.copyProperties(needRefundOrder,tradeRefundOrder);
        tradeRefundOrder.setId(null);
        tradeRefundOrder.setRefundSerialNum(ChanPayUtil.generateOutTradeNo());
        tradeRefundOrder.setStatus(RefundStatusEnum.INIT.getCode());
        tradeRefundOrder.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
        tradeRefundOrder.setProviderId(null);
        tradeRefundOrder.setCreateTime(DateUtil.date());
        tradeRefundOrder.setUpdateTime(DateUtil.date());
        tradeRefundOrderMapper.insertSelective(tradeRefundOrder);
        //原退款订单标为已重新发起
        needRefundOrder.setStatus(RefundStatusEnum.AGAIN.getCode());
        needRefundOrder.setUpdateTime(DateUtil.date());
        tradeRefundOrderMapper.updateByPrimaryKeySelective(needRefundOrder);

        TransferOrder transferOrder = transferOrderMapper.selectByTransferSerialNum(needRefundOrder.getLinkSerialNum());
        //灵工出账冻结
        Integer customerId = transferOrder.getCustomerId();
        BigDecimal customerAmount = tradeRefundOrder.getAmount();
        customerAccountService.updateAccountAndInsertDetail(customerId, customerAmount
                , AccountDetailTypeEnum.DEBIT_FREEZE, tradeRefundOrder.getRefundSerialNum());
        //商户入账冻结
        Integer merchantId = transferOrder.getMerchantId();
        BigDecimal merchanAmount = tradeRefundOrder.getActualAmount();
        MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                merchantId, BigDecimal.ZERO.subtract(merchanAmount)
                , UpdateAccountTypeOfMethod.PAYSUCCESS.getType());
        MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        merchantAccountDetailService.insertMerchantAccountDetail(merchanAmount,beforeAccount,afterAccount
                , AccountDetailStatusEnum.SUCCESS.code,tradeRefundOrder.getRefundSerialNum()
                , MerchantAccountDetailTypeEnum.REFUND.code,DetailTypeEnum.BACK.getCode());

        //网商退款
        OnlineCommonResultDTO resultDTO = onlineBankService.refundTransferOrder(tradeRefundOrder);
        tradeRefundOrder.setStatus(RefundStatusEnum.SUCESS.getCode());
        if(!"T".equals(resultDTO.getIs_success())){
            log.info("{}退款失败：{}",tradeRefundOrder.getRefundSerialNum(),resultDTO.getError_code());
            tradeRefundOrder.setStatus(RefundStatusEnum.FAIL.getCode());
            tradeRefundOrder.setComment(resultDTO.getError_message());
        }
        tradeRefundOrder.setUpdateTime(DateUtil.date());
        tradeRefundOrderMapper.updateByPrimaryKeySelective(tradeRefundOrder);
        return tradeRefundOrder.getRefundSerialNum();
    }

    @Override
    public void handleAsync(TradeRefundOrder tradeRefundOrder, RefundStatusEnum refundStatusEnum) {
        tradeRefundOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
        tradeRefundOrder.setUpdateTime(DateUtil.date());
        tradeRefundOrderMapper.updateByPrimaryKeySelective(tradeRefundOrder);
        String refundSerialNum = tradeRefundOrder.getRefundSerialNum();
        if(LinkRecordTypeEnum.TRANFER_ORDER.getType().equals(tradeRefundOrder.getLinkRecordType())) {
            TransferOrder transferOrder = transferOrderMapper.selectByTransferSerialNum(tradeRefundOrder.getLinkSerialNum());
            Integer customerId = transferOrder.getCustomerId();
            Integer merchantId = transferOrder.getMerchantId();
            BigDecimal customerAmount = tradeRefundOrder.getAmount();
            BigDecimal merchantAmount = tradeRefundOrder.getActualAmount();
            MerchantAccount beforeAccount,afterAccount;
            switch (refundStatusEnum) {
                case SUCESS:
                    //员工出账解冻
                    customerAccountService.updateAccountAndInsertDetail(customerId, customerAmount
                            , AccountDetailTypeEnum.DEBIT_UNFREEZE, refundSerialNum);
                    //商户入账解冻并添加记录
                    beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                            merchantId, merchantAmount, UpdateAccountTypeOfMethod.PAYORMAKEUP.getType());
                    afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
                    merchantAccountDetailService.insertMerchantAccountDetail(merchantAmount, beforeAccount, afterAccount
                            , AccountDetailStatusEnum.SUCCESS.code, refundSerialNum
                            , MerchantAccountDetailTypeEnum.PAID.code,DetailTypeEnum.BACK.getCode());
                    break;
                case FAIL:
                    //员工入账解冻
                    customerAccountService.updateAccountAndInsertDetail(customerId,customerAmount
                            , AccountDetailTypeEnum.CREDIT_UNFREEZE,refundSerialNum);
                    //商户出账解冻并添加变动记录
                    beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                            merchantId, merchantAmount, UpdateAccountTypeOfMethod.PAYSUCCESS.getType());
                    afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
                    merchantAccountDetailService.insertMerchantAccountDetail(merchantAmount, beforeAccount, afterAccount
                            , AccountDetailStatusEnum.SUCCESS.code, refundSerialNum
                            , MerchantAccountDetailTypeEnum.PAID.code,DetailTypeEnum.BACK.getCode());
                    break;
                default:
            }
        }
    }
}
