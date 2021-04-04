package com.mmtax.common.enums;

/**
 * 派单模式枚举类
 * @author Ljd
 * @date 2020/10/16
 */
public enum SendOrderModeEnum {

    /**
     * 手动
     */
    MANUAL(0),
    /**
     * 自动
     */
    AUTOMATION(1);

    private Integer code;

    SendOrderModeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
