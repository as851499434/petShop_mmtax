package com.mmtax.common.enums;

public enum VerifyTypeEnum {
    //三要素
    THREEELEMENTS(3,"三要素"),
    //四要素
    FOURELEMENTS(4,"四要素"),
    //不验证
    NOVERIFY(0,"不验证");

    private Integer type;
    private String description;

    VerifyTypeEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
