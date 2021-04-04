package com.mmtax.common.enums;

public enum DefaultStatusEnum {

    /**
     * 非默认
     */
    UN_DEFAULT(0),

    /**
     * 默认
     */
    DEFAULT(1);

    public Integer code;

    DefaultStatusEnum(int code) {
        this.code = code;
    }
}
