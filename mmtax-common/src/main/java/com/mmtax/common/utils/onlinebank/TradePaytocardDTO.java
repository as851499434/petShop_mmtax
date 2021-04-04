package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class TradePaytocardDTO extends OnlineBankCommonDTO {
    private String outerTradeNo;
    private String uid;
    private String outerInstOrderNo;
    private String whiteChannelCode;
    private String accountType;
    private String bankId;
    private String amount;
    private String feeInfo;
    private String memo;
}
