package com.mmtax.common.enums;

public enum VerifyStatusEnum {
    //已认证
    VERIFY(1,"已认证"),
    //未认证
    UNVERIFY(0,"未认证");

    private Integer status;
    private String description;

    VerifyStatusEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
