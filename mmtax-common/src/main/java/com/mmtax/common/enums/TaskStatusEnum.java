package com.mmtax.common.enums;

public enum TaskStatusEnum {
    /**
     * 未作废
     */
    UNFINISH(0),

    /**
     * 已作废
     */
    FINISH(1),

    /**
     * 未完成
     */
    UNCOMPLETE(2);

    private Integer code;

    TaskStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
