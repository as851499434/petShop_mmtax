package com.mmtax.common.enums;

public enum OrderTypeEnum {
    /**
     * 打款
     */
    NOMAL(1,"打款"),
    /**
     * 退款
     */
    REFUND(2,"退款");

    private Integer status;
    private String description;

    OrderTypeEnum(Integer status, String description) {
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
