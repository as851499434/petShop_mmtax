package com.mmtax.common.enums;

public enum PerMerStatusEnum {
    //进行中
    INIT(-2),
    PROCESS(0),
    SUCCESS(1),
    OVERRULE(2),
    FAIL(3),
    COMPLETE(4),
    //工单
    WORKORDER(5);


    private Integer code;

    PerMerStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
