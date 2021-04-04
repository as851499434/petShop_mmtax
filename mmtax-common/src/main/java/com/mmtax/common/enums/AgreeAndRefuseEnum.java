package com.mmtax.common.enums;

/**
 * 同意和拒绝枚举
 */
public enum AgreeAndRefuseEnum {
    /**
     * 拒绝
     */
    REFUSE("0"),
    /**
     * 同意
     */
    AGREE("1")
    ;
    public String code;

    AgreeAndRefuseEnum(String code) {
        this.code = code;
    }
}
