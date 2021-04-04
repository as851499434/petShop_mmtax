package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/22 16:18
 */
public enum DelStatusEnum {

    NORMAL(0),
    DELETE(1);

    public Integer code;

    DelStatusEnum(int code) {
        this.code = code;
    }


}
