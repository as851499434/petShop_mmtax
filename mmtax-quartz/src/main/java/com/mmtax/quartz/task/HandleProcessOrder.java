package com.mmtax.quartz.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.MakeUpCharge;
import com.mmtax.business.domain.TransferOrder;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.mapper.MakeUpChargeMapper;
import com.mmtax.business.mapper.TransferOrderMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.common.constant.onlinebank.OnlineConstants;
import com.mmtax.common.enums.InternetBusinessFailReasonEnum;
import com.mmtax.common.enums.MakeUpChargeStatusEnum;
import com.mmtax.common.enums.TransferStatusEnum;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import com.mmtax.common.utils.onlinebank.InfoQueryResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component("HandleProcessOrder")
@Slf4j
public class HandleProcessOrder {
    @Autowired
    private TransferOrderMapper transferOrderMapper;
    @Autowired
    private TrxOrderMapper trxOrderMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private MakeUpChargeMapper makeUpChargeMapper;

    public void handleProcess(){
        log.info("处理还在进行中的代付订单和转账订单和补交订单----------");
        DateTime twoDay = DateUtil.offsetDay(DateUtil.date(), -2);
        log.info("2天前的时间：{}", twoDay);
        List<TrxOrder> trxOrders = trxOrderMapper.selectByStatusList(DateUtil.formatDateTime(twoDay));
        List<TransferOrder> transferOrders = transferOrderMapper.selectInitOrProcess(DateUtil.formatDateTime(twoDay));
        List<MakeUpCharge> makeUpCharges = makeUpChargeMapper.selectProcess(DateUtil.formatDateTime(twoDay));
        if (!CollectionUtils.isEmpty(trxOrders)) {
            trxOrders.forEach(one -> {
                try {
                    TransferOrder transferOrder = transferOrderMapper.selectByOrderSerialNumAndStatus(
                            one.getOrderSerialNum(),null);
                    if(null == transferOrder){
                        log.info("旧订单，不予处理");
                        return;
                    }
                    if(!TransferStatusEnum.PAID.getCode().equals(transferOrder.getStatus())){
                        log.info("转账记录为进行中或失败，不处理打款订单，返回");
                        return;
                    }
                    InfoQueryResultDTO resultDTO = onlineBankService.infoQuery(one.getMerchantId(), one.getOrderSerialNum());
                    if (null == resultDTO) {
                        return;
                    }
                    log.info("one:{}", JSON.toJSONString(one));
                    String errorMessage = resultDTO.getError_message();
                    if (OnlineConstants.TRADE_FAILED.equals(resultDTO.getTrade_status()) ||
                            !"T".equals(resultDTO.getIs_success())) {
                        //更新订单状态为失败
                        one.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
                        one.setComment(InternetBusinessFailReasonEnum.getByEnumName(resultDTO.getError_message())
                                .getModifyDescription());
                        trxOrderMapper.updateByPrimaryKeySelective(one);
                    }
                    if ("T".equals(resultDTO.getIs_success())
                            && OnlineConstants.TRADE_FINISHED.equals(resultDTO.getTrade_status())) {
                        //更新订单状态为成功
                        one.setOrderStatus(TrxOrderStatusEnum.PAID.code);
                        trxOrderMapper.updateByPrimaryKeySelective(one);
                    }
                    if (OnlineConstants.TRADE_FAILED.equals(resultDTO.getTrade_status())) {
                        //更新订单状态为失败
                        one.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
                        one.setComment(errorMessage);
                        trxOrderMapper.updateByPrimaryKeySelective(one);
                    }
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            });
        } else {
            log.info("没有处理的代付订单");
        }
        if (!CollectionUtils.isEmpty(transferOrders)) {
            transferOrders.forEach(one -> {
                try {
                    InfoQueryResultDTO resultDTO = onlineBankService.infoQuery(one.getMerchantId(), one.getTransferSerialNum());
                    if (null == resultDTO) {
                        return;
                    }
                    log.info("one:{}", JSON.toJSONString(one));
                    if (OnlineConstants.TRADE_FINISHED.equals(resultDTO.getTrade_status())) {
                        one.setStatus(TransferStatusEnum.PAID.getCode());
                        one.setUpdateTime(DateUtil.date());
                        transferOrderMapper.updateByPrimaryKeySelective(one);
                    }
                    if (OnlineConstants.TRADE_FAILED.equals(resultDTO.getTrade_status()) ||
                            !"T".equals(resultDTO.getIs_success())) {
                        one.setStatus(TransferStatusEnum.PAID_PENDING.getCode());
                        one.setUpdateTime(DateUtil.date());
                        transferOrderMapper.updateByPrimaryKeySelective(one);
                    }
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            });
        } else {
            log.info("没有需要处理的转账订单");
        }
        if (!CollectionUtils.isEmpty(makeUpCharges)) {
            makeUpCharges.forEach(one -> {
                try {
                    InfoQueryResultDTO resultDTO = onlineBankService.infoQuery(one.getMerchantId(), one.getMakeUpSerialNum());
                    if (null == resultDTO) {
                        return;
                    }
                    log.info("one:{}", JSON.toJSONString(one));
                    if (OnlineConstants.TRADE_FINISHED.equals(resultDTO.getTrade_status())) {
                        one.setStatus(MakeUpChargeStatusEnum.SUCESS.getStatus());
                        one.setUpdateTime(DateUtil.date());
                        makeUpChargeMapper.updateByPrimaryKeySelective(one);
                    }
                    if (OnlineConstants.TRADE_FAILED.equals(resultDTO.getTrade_status()) ||
                            !"T".equals(resultDTO.getIs_success())) {
                        one.setStatus(MakeUpChargeStatusEnum.FAIL.getStatus());
                        one.setUpdateTime(DateUtil.date());
                        makeUpChargeMapper.updateByPrimaryKeySelective(one);
                    }
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            });
        } else {
            log.info("没有需要处理的补交订单");
        }
        log.info("处理还在进行中的代付订单和转账订单和补交订单处理结束----------");
    }
}
