package com.mmtax.common.utils.onlinebank;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayInstantDTO extends OnlineBankCommonDTO {
    //买家(付款方)在业务平台的ID（UID）
    private String buyerId;
    //交易号
    private String outerTradeNo;
    // pay_method格式为json：pay_method(支付方式BALANCE POS QPAY ONLINE_BANK)，amount(金额)，memo(备注)
//    private String payMethod;
    //商品的标题/交易标题/订单标题/订单关键字等
    private String subject;
    //商品单价。单位为：RMB  小数点后两位
    private BigDecimal price;
    //服务费信息 {"sellerFee":"0.5","buyerFee":"0.3"}
    private String feeInfo;
    //商品的数量
    private BigDecimal quantity;
    //交易金额=（商品单价×商品数量）
//    private String totalAmount;
    //卖家(收款方)在业务平台的用户ID（UID）
    private String sellerId;
    //卖家账户类型
    private String accountType;
}
