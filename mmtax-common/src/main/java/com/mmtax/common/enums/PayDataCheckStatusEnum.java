package com.mmtax.common.enums;

public enum PayDataCheckStatusEnum {
    //校验中
    NEEDCHECK(-1,"待校验"),
    PROCESS(0,"校验中"),
    SUCESS(1,"成功"),
    FAIL(2,"失败");

    private Integer status;
    private String description;

    PayDataCheckStatusEnum(Integer status, String description) {
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
