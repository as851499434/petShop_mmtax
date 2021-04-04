package com.mmtax.common.enums;

public enum AutoArchiveEnum {
    /**
     * 自动归档
     */
    TRUE(1,"自动归档"),
    /**
     * 不自动归档
     */
    FALSE(0,"不自动归档");

    private Integer status;
    private String description;

    AutoArchiveEnum(Integer status, String description) {
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
