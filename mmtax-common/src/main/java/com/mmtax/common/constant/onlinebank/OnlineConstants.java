package com.mmtax.common.constant.onlinebank;

/**
 * 云资金第三方的常量参数
 */
public class OnlineConstants {

    /**
     * 网商渠道子账号充值收款银行名称
     */
    public static final String RECEIVING_BANK_NAME = "浙江网商银行股份有限公司";

    public static final String ACCOUNT_TYPE = "BASIC";

    /**
     * 基本户类型
     */
    public static final String PBSIC_ACCOUNT = "PBASIC";
    /**
     * 收入户类型
     */
    public static final String PINCOME_ACCOUNT = "PINCOME";

    public static final String CARD_TYPE = "DC";

    /**
     * 卡属性： C    对私
     */
    public static final String CARD_ATTRIBUTE = "C";

    /**
     * 卡属性： B    对公
     */
    public static final String CARD_ATTRIBUTE_PUBLIC = "B";

    //证件类型身份证
    public static final String ID_CARD = "ID_CARD";

    /**
     * 代付异步通知
     */
    public static final String WITHDRAWAL_STATUS_SYNC = "withdrawal_status_sync";

    /**
     * 交易通知类型
     */
    public static final String TRADE_STATUS_SYNC = "trade_status_sync";

    /**
     * 支付通知类型
     */
    public static final String PAY_STATUS_SYNC = "pay_status_sync";

    /**
     * 来账异步通知
     */
    public static final String REMIT_SYNC = "remit_sync";

    /**
     * 即时入账异步通知
     */
    public static final String TRANSFER_STATUS_SYNC = "transfer_status_sync";

    /**
     * 即时入账,代付成功
     */
    public static final String TRADE_FINISHED = "TRADE_FINISHED";

    /**
     * 即时入账,代付失败
     */
    public static final String TRADE_FAILED = "TRADE_FAILED";
    /** 退款异步通知类型 */
    public static final String REFUND_STATUS_SYNC = "refund_status_sync";
    /**
     * 退款成功
     */
    public static final String REFUND_FINISH = "REFUND_FINISH";
    /**
     * 退款失败
     */
    public static final String REFUND_FAILED = "REFUND_FAIL";

    /**
     * 来账成功
     */
    public static final String SUCCESS = "SUCCESS";

}
