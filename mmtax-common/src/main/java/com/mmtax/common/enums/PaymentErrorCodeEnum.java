package com.mmtax.common.enums;

public enum PaymentErrorCodeEnum {
    //未定义异常
    UNSPECIFIED("500","网络异常，稍后再试"),
    NO_SERVICE("404","网络异常，服务器出错"),
    //支付相关
    TWO_ELEMENTS_ERROR("40001","姓名或身份证验证失败"),
    THREE_ELEMENTS_ERROR("40002","银行卡信息错误"),
    ALIPAY_INFO_ERROR("40003","支付宝信息错误")
    ;
    private final String code;
    private final String description;


    PaymentErrorCodeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
