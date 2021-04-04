package com.mmtax.common.enums;

public enum BigRateSwitchEnum {
    /**
     * 大额费率打开
     */
    BIDRATEOPEN(1,"大额费率打开"),
    /**
     * 大额费率关闭
     */
    BIDRATECLOSE(0,"大额费率关闭");

    private Integer status;
    private String description;

    BigRateSwitchEnum(Integer status, String description) {
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
