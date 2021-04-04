package com.mmtax.common.enums;

public enum EffectiveEnum {
    //有效
    EFFECTIVE(1,"有效"),
    //无效
    UNEFFECTIVE(0,"无效");

    private Integer status;
    private String decription;

    EffectiveEnum(Integer status, String decription) {
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
