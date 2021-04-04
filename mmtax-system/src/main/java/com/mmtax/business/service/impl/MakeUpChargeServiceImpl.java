package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.PaymentInfoDTO;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IMerchantAccountDetailService;
import com.mmtax.business.service.IMerchantAccountService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.constant.PaymentConstants;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IMakeUpChargeService;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 补交服务费总记录 服务层实现
 * 
 * @author meimiao
 * @date 2020-08-19
 */
@Service
@Slf4j
public class MakeUpChargeServiceImpl implements IMakeUpChargeService
{
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    private TrxOrderMapper trxOrderMapper;
    @Autowired
    private MakeUpChargeMapper makeUpChargeMapper;
    @Autowired
    private MerchantInfoMapper merchantInfoMapper;
    @Autowired
    private MakeUpChargeDetailMapper makeUpChargeDetailMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private IMerchantAccountService merchantAccountService;
    @Autowired
    private MerchantAccountMapper merchantAccountMapper;
    @Autowired
    IMerchantAccountDetailService merchantAccountDetailService;
    @Autowired
    CusLinkMerInfoMapper cusLinkMerInfoMapper;

    @Override
    public MakeUpCharge addRecords(MakeUpCharge makeUpCharge,List<MakeUpChargeDetail> makeUpChargeDetails
            , TrxOrder trxOrder) {
        makeUpCharge.setBatchNo(trxOrder.getBatchNo());
        makeUpCharge.setBatchRecordId(trxOrder.getBatchPaymentRecordId());
        makeUpCharge.setOrderSerialNum(trxOrder.getOrderSerialNum());
        makeUpCharge.setCreateTime(DateUtil.date());
        makeUpCharge.setUpdateTime(DateUtil.date());
        makeUpChargeMapper.insertSelective(makeUpCharge);
        makeUpChargeDetails.forEach(one->{
            one.setMakeUpChargeId(makeUpCharge.getId());
            one.setCreateTime(DateUtil.date());
            one.setUpdateTime(DateUtil.date());
            makeUpChargeDetailMapper.insertSelective(one);
        });
        return makeUpCharge;
    }

    @Override
    public void payMakeUpCharge(String orderSerialSum,BigDecimal bigRate) {
        MakeUpCharge makeUpCharge = makeUpChargeMapper.selectByOrderSerialSumOrMakeUpSerialNum(
                orderSerialSum,null);
        if(null == makeUpCharge){
            log.info("无补交记录，返回");
            return;
        }
        if(!MakeUpChargeStatusEnum.INIT.getStatus().equals(makeUpCharge.getStatus())){
            log.info("补交记录{}状态不为初始化，不执行补交，返回",makeUpCharge.getMakeUpSerialNum());
            return;
        }
        //出账冻结商户的账户并加变动记录
        BigDecimal amount = makeUpCharge.getAmount();
        Integer merchantId = makeUpCharge.getMerchantId();
        MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                merchantId, BigDecimal.ZERO.subtract(amount), UpdateAccountTypeOfMethod.PAYORMAKEUP.getType());
        MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        merchantAccountDetailService.insertMerchantAccountDetail(amount,beforeAccount,afterAccount
                , AccountDetailStatusEnum.SUCCESS.code,makeUpCharge.getMakeUpSerialNum()
                ,MerchantAccountDetailTypeEnum.PAID.code, DetailTypeEnum.MAKEUPCHARGE.getCode());
        OnlineCommonResultDTO resultDTO = onlineBankService.transferAmountFromMerToPincome(
                makeUpCharge.getMerchantId(),makeUpCharge.getAmount(),makeUpCharge.getMakeUpSerialNum());
        makeUpCharge.setStatus(MakeUpChargeStatusEnum.PROCESS.getStatus());
        if(!"T".equals(resultDTO.getIs_success())){
            makeUpCharge.setStatus(MakeUpChargeStatusEnum.FAIL.getStatus());
            makeUpCharge.setComment("补交失败："+resultDTO.getError_message());
        }else{
            List<MakeUpChargeDetail> makeUpChargeDetails =
                    makeUpChargeDetailMapper.selectByMakeUpId(makeUpCharge.getId());
            makeUpChargeDetails.forEach(one->{
                TrxOrder trxOrder = trxOrderMapper.selectByOrderSerialNum(one.getOrderSerialNum());
                trxOrder.setTrxRateBig(bigRate);
                BigDecimal bigCharge = one.getChargeNow();
                trxOrder.setChargeBig(bigCharge);
                trxOrder.setBigInOutDeduction(trxOrder.getInOutDeduction());
                trxOrder.setUseBigRate(UseBigRateEnum.BIGRATE.getStatus());
                trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            });
        }
        makeUpCharge.setUpdateTime(DateUtil.date());
        makeUpChargeMapper.updateByPrimaryKeySelective(makeUpCharge);
    }

    @Override
    public void invalidMakeUpCharge(String orderSerialSum) {
        MakeUpCharge makeUpCharge = makeUpChargeMapper.selectByOrderSerialSumOrMakeUpSerialNum(
                orderSerialSum,null);
        if(null == makeUpCharge){
            log.info("无补交记录，返回");
            return;
        }
        if(!MakeUpChargeStatusEnum.INVALID.getStatus().equals(makeUpCharge.getStatus())){
            makeUpCharge.setStatus(MakeUpChargeStatusEnum.INVALID.getStatus());
            makeUpCharge.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            makeUpCharge.setUpdateTime(DateUtil.date());
            makeUpChargeMapper.updateByPrimaryKeySelective(makeUpCharge);
        }
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(makeUpCharge.getCustomerId());
        CusLinkMerInfo cusLinkMerInfo = cusLinkMerInfoMapper.selectByCusIdMerId(customerInfo.getId()
                ,makeUpCharge.getMerchantId());
        if(null != cusLinkMerInfo && UseBigRateEnum.BIGRATE.getStatus().equals(cusLinkMerInfo.getMonthUseRate())){
            cusLinkMerInfo.setMonthUseRate(UseBigRateEnum.NORMALRATE.getStatus());
            cusLinkMerInfo.setUpdateTime(DateUtil.date());
            cusLinkMerInfoMapper.updateByPrimaryKeySelective(cusLinkMerInfo);
        }
    }

    @Override
    public PaymentInfoDTO calculateMakeUpCharge(PaymentInfoDTO dto, Integer merchantId, Cooperation cooperation
            , Map<String, BigDecimal> idCardNoAmount, Set<String> hasMakeUpRecord) {
        BigDecimal judgeAmount = PaymentConstants.BIG_RATE_LIMIT;
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
        CustomerInfo customerInfo = customerInfoMapper.selectByIdCardNoAndTaxSourceId(dto.getIdCardNo()
                ,onlinePaymentInfo.getTaxSourceCompanyId());
        if(null != customerInfo) {
            CusLinkMerInfo cusLinkMerInfo = cusLinkMerInfoMapper.selectByCusIdMerId(customerInfo.getId(), merchantId);
            if (null != cusLinkMerInfo && UseBigRateEnum.BIGRATE.getStatus().equals(cusLinkMerInfo.getMonthUseRate())) {
                dto.setUseBigRate(true);
                return dto;
            }
        }
        //计算当月历史打款额start
        String startTime = DateUtil.formatDateTime(DateUtil.beginOfMonth(DateUtil.date()));
        String endTime = DateUtil.formatDateTime(DateUtil.endOfMonth(DateUtil.date()));
        Integer[] statusList = {-1,0,1};
        List<TrxOrder> historyTrxOrders = trxOrderMapper.selectByStatusInOneMonth(merchantId,null
                ,dto.getIdCardNo(),startTime,endTime,statusList,null);
        BigDecimal allPayAmount = BigDecimal.ZERO;
        if(!CollectionUtils.isEmpty(historyTrxOrders)){
            allPayAmount = historyTrxOrders.stream().reduce(BigDecimal.ZERO
                    , (a,b)->a.add(calTrxOrderMouldAmount(b)),(a, b)->BigDecimal.ZERO);
        }else{
            historyTrxOrders = new ArrayList<>();
        }
        log.info("灵工{}在商户{}当月历史订单总打款额{}",dto.getIdCardNo(),merchantId,allPayAmount);
        if(allPayAmount.add(idCardNoAmount.get(dto.getIdCardNo())).compareTo(judgeAmount) < 0){
            log.info("灵工{}在商户{}当月历史订单总打款额未达到10万，返回",dto.getIdCardNo(),merchantId);
            return dto;
        }
        //计算当月历史打款额end

        if(null != hasMakeUpRecord && hasMakeUpRecord.contains(dto.getIdCardNo())){
            log.info("批量中相同的员工打款记录已补交服务费");
            dto.setUseBigRate(true);
            return dto;
        }
        MakeUpCharge makeUpCharge = new MakeUpCharge();
        makeUpCharge.setMerchantName(merchantInfo.getMerchantName());
        makeUpCharge.setCustomerName(dto.getName());
        //计算补交金额start
        BigDecimal makeUpAmount = BigDecimal.ZERO;
        BigDecimal rateBig = cooperation.getSingleServiceBigRate();
        for (TrxOrder one:historyTrxOrders) {
            if(UseBigRateEnum.BIGRATE.getStatus().equals(one.getUseBigRate())){
                continue;
            }
            BigDecimal bigCharge;
            if(InOutDeductionEnum.INDEDUCTION.getStatus().equals(one.getInOutDeduction())) {
                bigCharge = one.getActualAmount().multiply(rateBig)
                        .divide(BigDecimal.ONE.subtract(one.getTaxRate()), 2, BigDecimal.ROUND_UP);
            }else{
                bigCharge = one.getActualAmount().multiply(rateBig).setScale(2,BigDecimal.ROUND_UP);
            }
            makeUpAmount = makeUpAmount.add(bigCharge.subtract(one.getCharge()));
        }
        //计算补交金额end
        makeUpCharge.setAmount(makeUpAmount);
        makeUpCharge.setInOutDeduction(2);
        makeUpCharge.setMonthPayAmount(allPayAmount);
        makeUpCharge.setMakeUpSerialNum(ChanPayUtil.generateOutTradeNo());
        makeUpCharge.setStatus(MakeUpChargeStatusEnum.INIT.getStatus());
        makeUpCharge.setMerchantId(merchantId);
        if(null != customerInfo) {
            makeUpCharge.setCustomerId(customerInfo.getId());
        }
        makeUpCharge.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
        dto.setMakeUpCharge(makeUpCharge);

        List<MakeUpChargeDetail> makeUpChargeDetails = new ArrayList<>();
        historyTrxOrders.forEach(one->{
            //排除大额的历史订单
            if(UseBigRateEnum.BIGRATE.getStatus().equals(one.getUseBigRate())){
                return;
            }
            BigDecimal thisOrderRate = one.getTaxRate();
            MakeUpChargeDetail value = new MakeUpChargeDetail();
            value.setOrderSerialNum(one.getOrderSerialNum());
            value.setOrderStatus(one.getOrderStatus());
            value.setTaxRate(thisOrderRate);
            value.setCharge(one.getCharge());
            value.setTaxRateNow(rateBig);
            BigDecimal bigCharge;
            if(InOutDeductionEnum.INDEDUCTION.getStatus().equals(one.getInOutDeduction())) {
                bigCharge = one.getActualAmount().multiply(rateBig)
                        .divide(BigDecimal.ONE.subtract(one.getTaxRate()), 2, BigDecimal.ROUND_UP);
            }else{
                bigCharge = one.getActualAmount().multiply(rateBig).setScale(2,BigDecimal.ROUND_UP);
            }
            value.setChargeNow(bigCharge);
            value.setChargeMakeUp(bigCharge.subtract(one.getCharge()));
            makeUpChargeDetails.add(value);
        });
        dto.setMakeUpChargeDetails(JSON.toJSONString(makeUpChargeDetails));
        dto.setUseBigRate(true);
        return dto;
    }

    private BigDecimal calTrxOrderMouldAmount(TrxOrder trxOrder){
        BigDecimal rate = trxOrder.getTaxRate();
        InOutDeductionEnum inOutDeduction = InOutDeductionEnum.getByStatus(trxOrder.getInOutDeduction());
        if(UseBigRateEnum.BIGRATE.getStatus().equals(trxOrder.getUseBigRate())){
            rate = trxOrder.getTrxRateBig();
            inOutDeduction = InOutDeductionEnum.getByStatus(trxOrder.getBigInOutDeduction());
        }
//        return calculateMouldAmount(trxOrder.getActualAmount(),rate,inOutDeduction);
        return trxOrder.getAmount();
    }

    @Override
    public BigDecimal calculateTax(BigDecimal mouldAmount, BigDecimal rate){
        BigDecimal tax;
        tax = mouldAmount.multiply(rate).setScale(2,BigDecimal.ROUND_UP);
        return tax;
    }

    @Override
    public BigDecimal calculateMouldAmount(BigDecimal settleAmount, BigDecimal rate,InOutDeductionEnum type){
        if(null == type){
            throw new BusinessException("判断服务费计算方式出错");
        }
        BigDecimal mouldAmount;
        if(InOutDeductionEnum.OUTDEDUCTION.equals(type)){
            mouldAmount = settleAmount;
        }else{
            mouldAmount = settleAmount.divide(BigDecimal.ONE.subtract(rate),2,BigDecimal.ROUND_UP);
        }
        return mouldAmount;
    }

    @Override
    public BigDecimal calculateSettleAmount(BigDecimal mouldAmount, BigDecimal rate, InOutDeductionEnum type){
        if(null == type){
            throw new BusinessException("判断服务费计算方式出错");
        }
        BigDecimal settleAmount;
        if(InOutDeductionEnum.OUTDEDUCTION.equals(type)){
            settleAmount = mouldAmount;
        }else{
            settleAmount = mouldAmount.subtract(mouldAmount.multiply(rate).setScale(2,BigDecimal.ROUND_UP));
        }
        return settleAmount;
    }

    @Override
    public boolean calculateAllPaymentInTime(Integer customerId,Integer merchantId, String startTime
            , String endTime,BigDecimal judgeAmount) {
        Integer[] statusList = {1};
        List<TrxOrder> historyTrxOrders = trxOrderMapper.selectByStatusInOneMonth(merchantId,customerId,null
                ,startTime,endTime,statusList,null);
        BigDecimal allPayAmount = BigDecimal.ZERO;
        if(!CollectionUtils.isEmpty(historyTrxOrders)){
            allPayAmount = historyTrxOrders.stream()
                    .reduce(BigDecimal.ZERO,(a,b)->a.add(b.getAmount()),(a,b)->BigDecimal.ZERO);
        }
        if(allPayAmount.compareTo(judgeAmount) > 0){
            return true;
        }
        return false;
    }
}
