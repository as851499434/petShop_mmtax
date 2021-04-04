package com.mmtax.common.enums;

public enum UseBigRateEnum {
    /**
     * 大额费率
     */
    BIGRATE(1,"大额费率"),
    /**
     * 普通费率
     */
    NORMALRATE(0,"普通费率");

    private Integer status;
    private String description;

    UseBigRateEnum(Integer status, String description) {
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
