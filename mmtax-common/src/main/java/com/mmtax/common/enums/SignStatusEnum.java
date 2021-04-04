package com.mmtax.common.enums;

import cn.hutool.crypto.asymmetric.Sign;

public enum SignStatusEnum {
    //
    INIT(0,"待签"),
    //
    NOSIGN(1,"未签"),
    //
    SUCCESS(2,"签署完成"),
    //
    FAIL(3,"失败"),
    //
    REFUSE(4,"拒签");

    private Integer code;
    private String description;

    SignStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static SignStatusEnum getByCode(Integer code){
        for (SignStatusEnum e: SignStatusEnum.values()) {
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
