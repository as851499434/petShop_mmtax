package com.mmtax.quartz.task;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.Cooperation;
import com.mmtax.business.domain.OnlinePaymentInfo;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.mapper.CooperationMapper;
import com.mmtax.business.mapper.OnlinePaymentInfoMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.IMakeUpChargeService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.common.enums.AsyncStatusEnum;
import com.mmtax.common.enums.NeedPayCardEnum;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("HandTrxOrderSuccessOrFailJob")
@Slf4j
public class HandTrxOrderSuccessOrFailJob {
    @Autowired
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    private TrxOrderMapper trxOrderMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private IMakeUpChargeService makeUpChargeService;
    @Autowired
    private CooperationMapper cooperationMapper;

    public void handleSuccessOrFail(){
        log.info("开始处理订单状态为成功或失败的后续处理------");
            List<TrxOrder> trxOrders = trxOrderMapper.selectStatusSuccessOrFail();
            trxOrders.forEach(one -> {
                try {
                    OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(one.getMerchantId());
                    if (null == onlinePaymentInfo) {
                        log.info("不是网商的订单，返回");
                        return;
                    }
                    if (null == one.getCustomerId()) {
                        log.info("旧系统订单，不予处理，返回");
                        return;
                    }
                    if (TrxOrderStatusEnum.PAID.code.equals(one.getOrderStatus())) {
                        one.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                        one.setUpdateTime(DateUtil.date());
                        trxOrderMapper.updateByPrimaryKeySelective(one);
                        if(NeedPayCardEnum.NEED.getStatus().equals(one.getNeedPayCard())) {
                            onlineBankService.orderSuccessDeal(one);
                        }
                        //补交大额服务费
                        Cooperation cooperation = new Cooperation();
                        cooperation.setMerchantId(one.getMerchantId());
                        cooperation = cooperationMapper.selectOne(cooperation);
                        makeUpChargeService.payMakeUpCharge(one.getOrderSerialNum(),cooperation.getSingleServiceBigRate());
                    }
                    if (TrxOrderStatusEnum.PAID_PENDING.code.equals(one.getOrderStatus())) {
                        one.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                        one.setUpdateTime(DateUtil.date());
                        trxOrderMapper.updateByPrimaryKeySelective(one);
                        if(NeedPayCardEnum.NEED.getStatus().equals(one.getNeedPayCard())) {
                            onlineBankService.orderFailDeal(one);
                        }
                        //订单挂起，标记补交服务费记录为失效
                        makeUpChargeService.invalidMakeUpCharge(one.getOrderSerialNum());
                    }
                    if(TrxOrderStatusEnum.ORDER_FAIL.code.equals(one.getOrderStatus())){
                        //订单失败，标记补交服务费记录为失效
                        makeUpChargeService.invalidMakeUpCharge(one.getOrderSerialNum());
                    }
                }catch(Exception e){
                    log.error("HandTrxOrderSuccessOrFailJob定时任务出错："+e.getMessage());
                }
            });
        log.info("处理订单状态为成功或失败的后续处理结束----------");
    }
}
