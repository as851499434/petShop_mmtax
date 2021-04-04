package com.mmtax.quartz.task;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.TransferOrder;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.mapper.TransferOrderMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.*;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.constant.onlinebank.OnlineConstants;
import com.mmtax.common.enums.AccountDetailTypeEnum;
import com.mmtax.common.enums.AsyncStatusEnum;
import com.mmtax.common.enums.TransferStatusEnum;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import com.mmtax.common.utils.onlinebank.InfoQueryResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Component("TrxOrderPaidPingHandleJob")
@Slf4j
public class TrxOrderPaidPingHandleJob {
    @Autowired
    private TransferOrderMapper transferOrderMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private TrxOrderMapper trxOrderMapper;
    @Autowired
    ITransferOrderService transferOrderService;
    @Autowired
    private ICustomerAccountService customerAccountService;
    @Autowired
    ITradeRefundOrderService tradeRefundOrderService;

    public void handleFail(){
        log.info("处理转账成功但订单失败的记录开始-------");
            List<TrxOrder> trxOrders = trxOrderMapper.selectByOrderStatusAsyncStatus(TrxOrderStatusEnum.PAID_PENDING.code
                    , AsyncStatusEnum.ALREADYHANDLE.getStatus());
            if(CollectionUtils.isEmpty(trxOrders)){
                return;
            }
            trxOrders.forEach(one->{
                try{
                    TransferOrder transferOrder = transferOrderMapper.selectByOrderSerialNumAndStatus(
                            one.getOrderSerialNum(),null);
                    if(null == transferOrder){
                        log.info("旧订单，不予处理，返回");
                        return;
                    }
                    if(!TransferStatusEnum.PAID.getCode().equals(transferOrder.getStatus())){
                        log.info("转账记录不成功，不予处理，返回");
                        return;
                    }
                    InfoQueryResultDTO quaryDTO = onlineBankService.infoQuery(one.getMerchantId(), one.getOrderSerialNum());
                    if(null == quaryDTO){
                        log.info("未确认打款订单在网商的状态，暂不处理，返回");
                        return;
                    }
                    if ("T".equals(quaryDTO.getIs_success())
                            && OnlineConstants.TRADE_FINISHED.equals(quaryDTO.getTrade_status())) {
                        log.info("查询出打款订单在网商为成功，更新打款订单状态,变动账户添加冻结记录，返回");
                        BigDecimal transferAmount = transferOrder.getAmount();
                        //员工出账冻结并添加变动记录
                        customerAccountService.updateAccountAndInsertDetail(one.getCustomerId(), transferAmount
                                , AccountDetailTypeEnum.DEBIT_FREEZE, one.getOrderSerialNum());
                        one.setOrderStatus(TrxOrderStatusEnum.PAID.code);
                        one.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
                        one.setUpdateTime(DateUtil.date());
                        trxOrderMapper.updateByPrimaryKeySelective(one);
                        return;
                    }
                    if("T".equals(quaryDTO.getIs_success())
                            && OnlineConstants.TRADE_FAILED.equals(quaryDTO.getTrade_status())){
                        //失败退款商户
                        tradeRefundOrderService.refundTranferOrder(one.getOrderSerialNum());
//                        transferOrderService.returnTranferAmount(one.getOrderSerialNum());
                    }
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            });
        log.info("处理转账成功但订单失败的记录结束-------");
    }
}
