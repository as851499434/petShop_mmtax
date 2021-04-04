package com.mmtax.common.enums;

public enum MoneyModelEnum {
    /**
     * 0-普通
     */
    NAMOL(0,"普通模式"),
    /**
     * 1-自动
     */
    AUTO(1,"自动模式");

    private Integer status;
    private String description;

    MoneyModelEnum(Integer status, String description) {
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
