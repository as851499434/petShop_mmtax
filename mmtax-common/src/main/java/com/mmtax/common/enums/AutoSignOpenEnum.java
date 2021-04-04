package com.mmtax.common.enums;

public enum AutoSignOpenEnum {
    //
    OPEN(1,"开启静默签署"),
    //
    CLOSE(0,"关闭静默签署");

    private Integer code;
    private String description;

    AutoSignOpenEnum(Integer code, String description) {
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
