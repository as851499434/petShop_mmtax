package com.mmtax.common.enums;

/**
 * 支付方式枚举
 * @author yuanligang
 * @date 2019/7/10
 */
public enum  PaymentEnum {
    /**
     * 银行支付
     */
    BANK("银行"),

    /**
     * 支付宝
     */
    ALIPAY("支付宝"),

    /**
     * 微信
     */
    WECHAT("微信");

    PaymentEnum(String des) {
        this.des = des;
    }
    /**
     * 描述信息
     */
    private String des;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
