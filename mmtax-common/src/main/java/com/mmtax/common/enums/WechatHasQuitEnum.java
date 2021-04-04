package com.mmtax.common.enums;

public enum WechatHasQuitEnum {
    /**
     * 未退出
     */

    NO_QUIT(0),

    /**
     * 已退出
     */
    QUIT(1);

    private Integer code;

    WechatHasQuitEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
