package com.mmtax.common.enums;

public enum BankcardOrAlipayBindEnum {
    //绑定
    BIND(1,"绑定"),
    //未绑定
    UNBIND(0,"未绑定");

    private Integer status;
    private String description;

    BankcardOrAlipayBindEnum(Integer status, String description) {
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
