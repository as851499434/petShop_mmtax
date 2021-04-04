package com.mmtax.common.enums;

public enum SendMessageLimitNumberEnum {
    //一天限制发送短信数
    SENDNUMBER(5);

    private Integer code;

    SendMessageLimitNumberEnum(Integer code) {
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
}
