package com.mmtax.common.enums;

/**
 * 绑定状态
 * @author Ljd
 * @date 2020/7/12
 */
public enum BindStatusEnum {

    BIND("Y"),
    NON_BIND("N");

    public String code;

    BindStatusEnum(String code) {
        this.code = code;
    }
}
