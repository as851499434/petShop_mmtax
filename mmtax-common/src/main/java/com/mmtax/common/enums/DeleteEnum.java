package com.mmtax.common.enums;


/**
 * @Author：YH
 * @Date：2020/10/23 13:56
 */
public enum DeleteEnum {

    //未删除
    UN_DELETE(0),
    //已删除
    DELETE(1);


    private final Integer code;


    DeleteEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
