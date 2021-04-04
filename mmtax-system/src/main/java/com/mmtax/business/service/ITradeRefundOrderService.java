package com.mmtax.business.service;

import com.mmtax.business.domain.TradeRefundOrder;
import com.mmtax.common.enums.RefundStatusEnum;

import java.util.List;

/**
 * 退款记录 服务层
 * 
 * @author meimiao
 * @date 2020-09-18
 */
public interface ITradeRefundOrderService
{
    /**
     * 回退订单对应的转账记录
     * @param orderSerialNum
     * @return
     */
    String refundTranferOrder(String orderSerialNum);

    /**
     * 退款失败的再次发起退款
     * @param refundSerialNum
     * @return
     */
    String refundFail(String refundSerialNum);

    /**
     * 异步通知后续处理
     * @param tradeRefundOrder
     * @param refundStatusEnum
     */
    void handleAsync(TradeRefundOrder tradeRefundOrder, RefundStatusEnum refundStatusEnum);
}
