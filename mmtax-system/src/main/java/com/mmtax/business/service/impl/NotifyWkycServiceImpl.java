package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.NotifyWkycDataDTO;
import com.mmtax.business.mapper.*;
import com.mmtax.common.enums.LinkRecordTypeEnum;
import com.mmtax.common.enums.NotifyStatusEnum;
import com.mmtax.common.enums.OrderTypeEnum;
import com.mmtax.common.enums.SwitchEnum;
import com.mmtax.common.utils.DateUtils;
import com.mmtax.common.utils.MmtaxSign;
import com.mmtax.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.INotifyWkycService;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 悟空云创需求代付发送 服务层实现
 * 
 * @author meimiao
 * @date 2020-11-17
 */
@Service
@Slf4j
public class NotifyWkycServiceImpl implements INotifyWkycService
{
    @Value("${wkyc.dev.key}")
    private String devKey;
    @Value("${wkyc.payment.send.url}")
    private String wkycPaymentSendUrl;
    @Autowired
    TrxOrderMapper trxOrderMapper;
    @Autowired
    TradeRefundOrderMapper tradeRefundOrderMapper;
    @Autowired
    NotifyWkycMapper notifyWkycMapper;
    @Autowired
    MerchantInfoMapper merchantInfoMapper;
    @Autowired
    TransferOrderMapper transferOrderMapper;

    @Override
    public void insertNotifyWkyc(String orderSerilNum, OrderTypeEnum orderType,Integer merchantId) {
        NotifyWkyc notifyWkyc = new NotifyWkyc();
        notifyWkyc.setSerialNum(orderSerilNum);
        notifyWkyc = notifyWkycMapper.selectOne(notifyWkyc);
        if(null != notifyWkyc){
            log.info("该笔记录已存入过wkyc通知表，return");
            return;
        }else {
            notifyWkyc = new NotifyWkyc();
        }
        notifyWkyc.setMerchantId(merchantId);
        notifyWkyc.setSerialNum(orderSerilNum);
        notifyWkyc.setNotifyStatus(0);
        notifyWkyc.setSendMethod(1);
        notifyWkyc.setOrderType(orderType.getStatus());
        notifyWkyc.setFailNum(0);
        notifyWkyc.setCreateTime(DateUtil.date());
        notifyWkyc.setUpdateTime(DateUtil.date());
        notifyWkycMapper.insertSelective(notifyWkyc);
    }

    @Override
    public void sendNotifyWkyc(NotifyWkyc notifyWkyc, Integer minutes) {
        if(2 == notifyWkyc.getNotifyStatus()) {
            Date sendTime = DateUtils.addMinutes(notifyWkyc.getLastSendTime(), minutes);
            if (DateUtils.getNowDate().compareTo(sendTime) < 0) {
                log.info("id={}的notifyWkyc记录未到下次发送的时间点之后，return", notifyWkyc.getId());
                return;
            }
        }
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(notifyWkyc.getMerchantId());
        NotifyWkycDataDTO notifyWkycData;
        if(OrderTypeEnum.NOMAL.getStatus().equals(notifyWkyc.getOrderType())){
            TrxOrder trxOrder = trxOrderMapper.selectByOrderSerialNum(notifyWkyc.getSerialNum());
            notifyWkycData = new NotifyWkycDataDTO.Builder(trxOrder,merchantInfo).build();
        }else{
            TradeRefundOrder refundOrder = tradeRefundOrderMapper.selectByRefundSerialNum(notifyWkyc.getSerialNum());
            if(LinkRecordTypeEnum.TRANFER_ORDER.getType().equals(refundOrder.getLinkRecordType())) {
                TransferOrder transferOrder = transferOrderMapper.selectByTransferSerialNum(
                        refundOrder.getLinkSerialNum());
                notifyWkycData = new NotifyWkycDataDTO.Builder(refundOrder, transferOrder.getOrderSerialNum()).build();
            }else {
                //暂时没有别的退款链接订单类型，有则记录错误日志
                log.error("退款订单{}记录的链接订单类型有问题",notifyWkyc.getSerialNum());
                return;
            }
        }
        String content = JSON.toJSONString(notifyWkycData);
        // 通知数据添加签名
        content = MmtaxSign.addSignByJsonStr(content,devKey);
        log.info("sendNotifyWkyc content:{}", content);
        String callBackResult = HttpUtils.sendPost(wkycPaymentSendUrl,content);
        log.info("返回的结果：{}",callBackResult);
        notifyWkyc.setNotifyStatus(1);
        if(StringUtils.isEmpty(callBackResult) || !NotifyStatusEnum.SUCCESS.name().equals(callBackResult)){
            notifyWkyc.setNotifyStatus(2);
            notifyWkyc.setFailNum(notifyWkyc.getFailNum()+1);
        }
        notifyWkyc.setLastSendTime(DateUtil.date());
        notifyWkyc.setUpdateTime(DateUtil.date());
        notifyWkycMapper.updateByPrimaryKeySelective(notifyWkyc);
    }
}
