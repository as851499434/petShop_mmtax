package com.mmtax.common.enums;

public enum TakeTaskStatusEnum {

    /**
     * 已接单
     */
    TAKE(1),
    /**
     * 未结单
     */
    NOTAKE(0);

    private Integer code;

    TakeTaskStatusEnum(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
