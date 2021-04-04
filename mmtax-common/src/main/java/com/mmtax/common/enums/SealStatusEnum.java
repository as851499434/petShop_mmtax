package com.mmtax.common.enums;

public enum SealStatusEnum {
    //
    NOCREATE(0,"未创建"),
    //
    CREATE(1,"已创建"),
    //
    DELETE(2,"已删除");

    private Integer code;
    private String description;

    SealStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
