package com.mmtax.common.enums;

public enum ReturnChargeStatusEnum {
    /**
     * 初始化
     */
    INIT(-1,"初始化"),
    /**
     * 进行中
     */
    PROCESS(0,"进行中"),
    /**
     * 已支付
     */
    SUCESS(1,"成功"),
    /**
     * 支付挂起
     */
    FAIL(2,"失败");

    private Integer code;
    private String description;

    ReturnChargeStatusEnum(Integer code, String description) {
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
