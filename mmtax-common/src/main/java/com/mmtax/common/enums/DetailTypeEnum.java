package com.mmtax.common.enums;

public enum DetailTypeEnum {
    // 变动类型
    //代付
    PAYMENT(0),
    //充值
    RECHARGE(1),
    //大额补交服务费
    MAKEUPCHARGE(2),
    //大额退还服务费
    RETUANCHARGE(3),
    //退回
    BACK(4),
    //取款
    GET(5);
    private Integer code;

    DetailTypeEnum(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
