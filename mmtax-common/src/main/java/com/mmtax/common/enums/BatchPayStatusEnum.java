package com.mmtax.common.enums;

public enum BatchPayStatusEnum {
    /**
     * 未打款
     */
    NO(0),
    /**
     * 已打款
     */
    YES(1),
    //取消
    CANCEL(2);

    private int code;

    BatchPayStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
