package com.mmtax.common.enums;

/**
 * @author: liangfan
 * @Date: 2021/4/6 16:18
 */


public enum DelStatusEnum {
    /**
     * 未删除
     */
    NORMAL(0),
    /**
     * 已删除
     */
    DELETE(1);

    private final Integer code;

    DelStatusEnum(int code) {
        this.code = code;
    }

    DelStatusEnum(Integer code)
    {
        this.code = code;
    }

    public Integer getCode()
    {
        return code;
    }


}
