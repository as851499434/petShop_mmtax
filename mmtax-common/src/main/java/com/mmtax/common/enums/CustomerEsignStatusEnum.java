package com.mmtax.common.enums;

public enum CustomerEsignStatusEnum {
    //
    NOOPEN(0,"未开户"),
    //
    OPEN(1,"已开户"),
    //
    LOGOUT(2,"已注销");

    private Integer code;
    private String description;

    CustomerEsignStatusEnum(Integer code, String description) {
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
