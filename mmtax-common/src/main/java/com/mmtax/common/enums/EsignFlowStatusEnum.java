package com.mmtax.common.enums;

public enum EsignFlowStatusEnum {
    //
    BEGIN(1,"开启中"),
    //
    INIT(0,"初始化"),
    //
    SUCCESS(2,"已完成"),
    //
    LOGOUT(3,"已撤销"),
    //
    EXPIRED(5,"已过期"),
    //
    REFUSE(7,"已拒签"),
    //
    FAIL(8,"创建失败"),
    //
    CREATESUCCESS(9,"创建成功");

    private Integer status;
    private String description;

    EsignFlowStatusEnum(Integer status, String description) {
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
