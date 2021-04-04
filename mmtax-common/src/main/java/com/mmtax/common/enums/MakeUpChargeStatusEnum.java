package com.mmtax.common.enums;

public enum MakeUpChargeStatusEnum {
    //
    INIT(-1,"初始化"),
    //
    PROCESS(0,"进行中"),
    //
    SUCESS(1,"已补交"),
    //
    FAIL(2,"补交失败"),
    //
    RETURN(3,"已退回"),
    //
    INVALID(9,"记录失效");

    private Integer status;
    private String description;

    MakeUpChargeStatusEnum(Integer status, String description) {
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
