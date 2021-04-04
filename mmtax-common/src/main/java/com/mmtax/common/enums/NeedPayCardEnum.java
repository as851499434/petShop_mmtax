package com.mmtax.common.enums;

public enum NeedPayCardEnum {
    //需要打到卡
    NEED(1,"需要打到卡"),
    //不需要打到卡
    NO_NEED(0,"不需要打到卡");

    private Integer status;
    private String decription;

    NeedPayCardEnum(Integer status, String decription) {
        this.status = status;
        this.decription = decription;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDecription() {
        return decription;
    }
}
