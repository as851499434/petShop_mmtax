package com.mmtax.common.enums;

public enum AsyncStatusEnum {
    /**
     * 已处理
     */
    ALREADYHANDLE(1,"已处理"),
    /**
     * 未处理
     */
    NOHANDLE(0,"未处理");

    private Integer status;
    private String description;

    AsyncStatusEnum(Integer status, String description) {
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
