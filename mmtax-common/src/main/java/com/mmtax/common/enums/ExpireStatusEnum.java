package com.mmtax.common.enums;

public enum ExpireStatusEnum {
    //
    NOEXPIRE(0,"未过期"),
    //
    EXPIRE(1,"已过期");

    private Integer code;
    private String description;

    ExpireStatusEnum(Integer code, String description) {
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
