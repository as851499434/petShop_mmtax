package com.mmtax.common.enums;

public enum WithdrawStatusEnum {
    /**
     * 初始化
     */
    INIT(-1,"初始化"),
    /**
     * 进行中
     */
    UNPAID(0,"进行中"),
    /**
     * 提现成功
     */
    PAID(1,"提现成功"),

    //提现失败
    ORDER_FAIL(2,"提现失败");

    private Integer code;
    private String description;

    WithdrawStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
