package com.mmtax.common.enums;

public enum  NameLengthEnum {
    //名字长度为2
    NAME_LENGTH(2);

    private Integer length;

    NameLengthEnum(Integer length){
        this.length = length;
    }

    public Integer getLength() {
        return length;
    }
}
