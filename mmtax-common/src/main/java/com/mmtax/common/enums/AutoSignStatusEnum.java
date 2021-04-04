package com.mmtax.common.enums;

public enum AutoSignStatusEnum {
    //
    NOAUTOSIGN(0,"未设置静默签署"),
    //
    AUTOSIGNSUCCESS(1,"已设置静默签署");

    private Integer code;
    private String description;

    AutoSignStatusEnum(Integer code, String description) {
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
