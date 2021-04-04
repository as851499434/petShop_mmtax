package com.mmtax.quartz.task;

import com.mmtax.business.domain.TradeRefundOrder;
import com.mmtax.business.mapper.TradeRefundOrderMapper;
import com.mmtax.business.service.ITradeRefundOrderService;
import com.mmtax.common.enums.AsyncStatusEnum;
import com.mmtax.common.enums.RefundStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component("HandleTradeRefundOrderJob")
@Slf4j
public class HandleTradeRefundOrderJob {
    @Autowired
    TradeRefundOrderMapper tradeRefundOrderMapper;
    @Autowired
    ITradeRefundOrderService tradeRefundOrderService;

    public void handSuccessOrFail(){
        log.info("异步通知成功或失败的TradeRefundOrder记录后续处理开始---------");
        List<TradeRefundOrder> tradeRefundOrders = tradeRefundOrderMapper.selectByAysncStatus(
                AsyncStatusEnum.NOHANDLE.getStatus());
        if(CollectionUtils.isEmpty(tradeRefundOrders)){
            log.info("没有需要处理的TradeRefundOrder记录");
            return;
        }
        tradeRefundOrders.forEach(one->{
            try{
                tradeRefundOrderService.handleAsync(one,RefundStatusEnum.getByCode(one.getStatus()));
            }catch (Exception e){
                log.error("HandleTradeRefundOrderJob.handSuccessOrFail定时任务出错:",e);
            }
        });
        log.info("异步通知成功或失败的TradeRefundOrder记录后续处理结束---------");
    }
}
