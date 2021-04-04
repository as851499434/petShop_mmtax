package com.mmtax.common.enums;

public enum CofirmStatusEnum {
    /**
     * 未确认
     */
    NO(0),
    /**
     * 已确认
     */
    YES(1),
    //取消
    CANCEL(2);

    private int code;

    CofirmStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
