package com.mmtax.common.enums;

public enum SwitchEnum {

    /**
     * 开
     */
    ON(1),
    /**
     * 关
     */
    OFF(0);

    private Integer code;

    SwitchEnum(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
