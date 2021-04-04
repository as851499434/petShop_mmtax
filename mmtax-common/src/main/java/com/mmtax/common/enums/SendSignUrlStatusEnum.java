package com.mmtax.common.enums;

public enum SendSignUrlStatusEnum {
    //
    NOSEND(0,"未发送"),
    //
    SEND(1,"已发送");

    private Integer code;
    private String description;

    SendSignUrlStatusEnum(Integer code, String description) {
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
