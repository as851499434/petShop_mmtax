package com.mmtax.common.enums;

public enum NoticeTypeEnum {
    /**
     * 已处理
     */
    SMS(1,"短信"),
    /**
     * 未处理
     */
    NOTNEED(0,"不需要"),
    /**
     * 已处理
     */
    EMAIL(2,"邮件");

    private Integer status;
    private String description;

    NoticeTypeEnum(Integer status, String description) {
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
