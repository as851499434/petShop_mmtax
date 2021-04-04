package com.mmtax.common.enums;

public enum CheckStatusEnum {
    YES(1),
    NO(0);

    private Integer code;

    CheckStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
