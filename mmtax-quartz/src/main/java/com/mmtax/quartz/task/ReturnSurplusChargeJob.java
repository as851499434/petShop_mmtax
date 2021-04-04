package com.mmtax.quartz.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.MakeUpCharge;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.mapper.MakeUpChargeMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.IMakeUpChargeService;
import com.mmtax.business.service.IReturnChargeOrderService;
import com.mmtax.common.constant.PaymentConstants;
import com.mmtax.common.enums.AsyncStatusEnum;
import com.mmtax.common.enums.MakeUpChargeStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component("ReturnSurplusChargeJob")
@Slf4j
public class ReturnSurplusChargeJob {

    @Autowired
    private TrxOrderMapper trxOrderMapper;
    @Autowired
    private IReturnChargeOrderService returnChargeOrderService;
    @Autowired
    private IMakeUpChargeService makeUpChargeService;
    @Autowired
    private MakeUpChargeMapper makeUpChargeMapper;


    public void surplusCharge(){
        log.info("回退多补交的服务费开始-------");
        DateTime yesterday = DateUtil.offsetDay(DateUtil.date(),-1);
//        DateTime yesterday = DateUtil.date();
        String beginOfMonth = DateUtil.formatDateTime(DateUtil.beginOfMonth(yesterday));
        String endOfMonth = DateUtil.formatDateTime(DateUtil.endOfMonth(yesterday));
        log.info("处理的时间段(月份):{}-{}",beginOfMonth,endOfMonth);
        List<TrxOrder> trxOrders = trxOrderMapper.selectByMonthAndBigRate(beginOfMonth,endOfMonth);
        if(CollectionUtils.isEmpty(trxOrders)){
            log.info("本月无打款订单，返回");
            return;
        }
        Map<Integer,Set<Integer>> cusLinkMeres = new HashMap<>(1024);
        Set<Integer> customerIds = trxOrders.stream().map(TrxOrder::getCustomerId).collect(Collectors.toSet());
        customerIds.forEach(one->{
            Set<Integer> merchantIds = trxOrders.stream().filter(v->one.equals(v.getCustomerId()))
                    .map(TrxOrder::getMerchantId).collect(Collectors.toSet());
            cusLinkMeres.put(one,merchantIds);
        });
        cusLinkMeres.forEach((one,values)->{
            for (Integer merchantId:values) {
                try{
                    boolean hasExceed = makeUpChargeService.calculateAllPaymentInTime(one,merchantId,beginOfMonth
                            ,endOfMonth, PaymentConstants.BIG_RATE_LIMIT);
                    List<MakeUpCharge> makeUpCharges = makeUpChargeMapper.selectInMonthLatest(one,merchantId
                            ,beginOfMonth,endOfMonth);
                    if(CollectionUtils.isEmpty(makeUpCharges)){
                        log.info("员工{}在商户{}下无补交记录，返回",one,merchantId);
                        return;
                    }
                    MakeUpCharge makeUpCharge = makeUpCharges.get(0);
                    Integer makeUpChargeStatus = makeUpCharge.getStatus();
                    Integer asyncStatus = makeUpCharge.getAsyncStatus();
                    if(AsyncStatusEnum.NOHANDLE.getStatus().equals(asyncStatus)){
                        log.info("该笔补交记录{}异步处理还未完成，返回",makeUpCharge.getMakeUpSerialNum());
                        return;
                    }
                    if(hasExceed){
                        if(MakeUpChargeStatusEnum.SUCESS.getStatus().equals(makeUpChargeStatus)){
                            //开始退历史失败的
                            returnChargeOrderService.returnHistoryFail(makeUpCharge.getId());
                        }
                    }else{
                        if(MakeUpChargeStatusEnum.SUCESS.getStatus().equals(makeUpChargeStatus)){
                            returnChargeOrderService.returnHistoryFail(makeUpCharge.getId());
                            //退所有大额成功的
                            returnChargeOrderService.returnBigRateSuccess(one,merchantId);
                            //状态更新为已回退
                            makeUpCharge.setStatus(MakeUpChargeStatusEnum.RETURN.getStatus());
                            makeUpCharge.setUpdateTime(DateUtil.date());
                            makeUpChargeMapper.updateByPrimaryKeySelective(makeUpCharge);
                        }
                        if(MakeUpChargeStatusEnum.RETURN.getStatus().equals(makeUpChargeStatus)){
                            returnChargeOrderService.returnBigRateSuccess(one,merchantId);
                        }
                    }
                }catch(Exception e){
                    log.error("ReturnSurplusChargeJob："+e.getMessage());
                }
            }
        });
        log.info("回退多补交的服务费结束-------");
    }
}
