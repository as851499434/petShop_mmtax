package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.*;
import com.mmtax.business.mapper.MakeUpChargeDetailMapper;
import com.mmtax.business.mapper.MerchantAccountMapper;
import com.mmtax.business.mapper.ReturnChargeOrderMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.IMerchantAccountDetailService;
import com.mmtax.business.service.IMerchantAccountService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.enums.*;
import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IReturnChargeOrderService;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 退还补交服务费记录 服务层实现
 * 
 * @author meimiao
 * @date 2020-08-19
 */
@Service
@Slf4j
public class ReturnChargeOrderServiceImpl implements IReturnChargeOrderService
{
    @Autowired
    private MakeUpChargeDetailMapper makeUpChargeDetailMapper;
    @Autowired
    private TrxOrderMapper trxOrderMapper;
    @Autowired
    private ReturnChargeOrderMapper returnChargeOrderMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private IMerchantAccountService merchantAccountService;
    @Autowired
    private MerchantAccountMapper merchantAccountMapper;
    @Autowired
    IMerchantAccountDetailService merchantAccountDetailService;

    @Override
    public void returnHistoryFail(Integer makeUpChargeId) {
        List<MakeUpChargeDetail> makeUpChargeDetails =
                makeUpChargeDetailMapper.selectByMakeUpIdAndOrderStatusNotSuccess(makeUpChargeId);
        makeUpChargeDetails.forEach(one->{
            TrxOrder trxOrder = trxOrderMapper.selectByOrderSerialNum(one.getOrderSerialNum());
            if(!TrxOrderStatusEnum.ORDER_FAIL.code.equals(trxOrder.getOrderStatus())){
                return;
            }
            ReturnChargeOrder returnChargeOrder = returnChargeOrderMapper.selectByOrderSerialNumOrReturnSerialNum(
                    trxOrder.getOrderSerialNum(),null);
            if(null != returnChargeOrder){
                log.info("该订单{}已回退过服务费",trxOrder.getOrderSerialNum());
                return;
            }
            returnChargeOrder = new ReturnChargeOrder();
            returnChargeOrder.setReturnSerialNum(ChanPayUtil.generateOutTradeNo());
            returnChargeOrder.setOrderSerialNum(one.getOrderSerialNum());
            returnChargeOrder.setStatus(ReturnChargeStatusEnum.INIT.getCode());
            returnChargeOrder.setReturnCharge(one.getChargeMakeUp());
            returnChargeOrder.setMerchantId(trxOrder.getMerchantId());
            returnChargeOrder.setCustomerId(trxOrder.getCustomerId());
            returnChargeOrder.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
            returnChargeOrder.setCreateTime(DateUtil.date());
            returnChargeOrder.setUpdateTime(DateUtil.date());
            returnChargeOrderMapper.insertSelective(returnChargeOrder);
            trxOrder.setUseBigRate(UseBigRateEnum.NORMALRATE.getStatus());
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            returnCharge(returnChargeOrder);
        });
    }

    @Override
    public void returnBigRateSuccess(Integer customerId,Integer merchantId) {
        List<TrxOrder> trxOrders = trxOrderMapper.selectByCustomerIdAndStatusAndUseBigRate(customerId,merchantId
                ,TrxOrderStatusEnum.PAID.code,UseBigRateEnum.BIGRATE.getStatus());
        if(CollectionUtils.isEmpty(trxOrders)){
            log.info("没有大额费率成功的订单需要退回");
            return;
        }
        trxOrders.forEach(one->{
            ReturnChargeOrder returnChargeOrder = returnChargeOrderMapper.selectByOrderSerialNumOrReturnSerialNum(
                    one.getOrderSerialNum(),null);
            if(null != returnChargeOrder){
                log.info("该订单{}已回退过服务费",one.getOrderSerialNum());
                return;
            }
            returnChargeOrder = new ReturnChargeOrder();
            returnChargeOrder.setReturnSerialNum(ChanPayUtil.generateOutTradeNo());
            returnChargeOrder.setOrderSerialNum(one.getOrderSerialNum());
            returnChargeOrder.setStatus(ReturnChargeStatusEnum.INIT.getCode());
            BigDecimal returnCharge = one.getChargeBig().subtract(one.getCharge());
            returnChargeOrder.setReturnCharge(returnCharge);
            returnChargeOrder.setMerchantId(one.getMerchantId());
            returnChargeOrder.setCustomerId(one.getCustomerId());
            returnChargeOrder.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
            returnChargeOrder.setCreateTime(DateUtil.date());
            returnChargeOrder.setUpdateTime(DateUtil.date());
            returnChargeOrderMapper.insertSelective(returnChargeOrder);
            TrxOrder trxOrder = new TrxOrder();
            trxOrder.setId(one.getId());
            trxOrder.setUseBigRate(UseBigRateEnum.NORMALRATE.getStatus());
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            returnCharge(returnChargeOrder);
        });
    }

    public void returnCharge(ReturnChargeOrder returnChargeOrder){
        Integer merchantId = returnChargeOrder.getMerchantId();
        BigDecimal amount = returnChargeOrder.getReturnCharge();
        String returnSerialNum = returnChargeOrder.getReturnSerialNum();
        //商户入账冻结
        MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                merchantId, BigDecimal.ZERO.subtract(amount), UpdateAccountTypeOfMethod.PAYSUCCESS.getType());
        MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        merchantAccountDetailService.insertMerchantAccountDetail(amount,beforeAccount,afterAccount
                , AccountDetailStatusEnum.SUCCESS.code,returnSerialNum, MerchantAccountDetailTypeEnum.PAID.code
                ,DetailTypeEnum.RETUANCHARGE.getCode());

        returnChargeOrder.setStatus(ReturnChargeStatusEnum.PROCESS.getCode());
        OnlineCommonResultDTO resultDTO = onlineBankService.transferAmountFromPincomeToMer(merchantId,amount
                ,returnSerialNum);
        if(!"T".equals(resultDTO.getIs_success())){
            returnChargeOrder.setStatus(ReturnChargeStatusEnum.FAIL.getCode());
            returnChargeOrder.setComment(resultDTO.getError_message());
        }
        returnChargeOrder.setUpdateTime(DateUtil.date());
        returnChargeOrderMapper.updateByPrimaryKeySelective(returnChargeOrder);
    }
}
