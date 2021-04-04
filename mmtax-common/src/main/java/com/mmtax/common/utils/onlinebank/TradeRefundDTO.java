package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class TradeRefundDTO extends OnlineBankCommonDTO{
    private String outerTradeNo;
    private String origOuterTradeNo;
    private String refundAmount;
    private String refundFeeInfo;

}
