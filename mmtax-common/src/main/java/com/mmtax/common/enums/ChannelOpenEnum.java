package com.mmtax.common.enums;

public enum ChannelOpenEnum {
    //打开
    OPEN(1,"打开"),
    //关闭
    CLOSE(0,"关闭");

    private Integer status;
    private String description;

    ChannelOpenEnum(Integer status, String description) {
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
