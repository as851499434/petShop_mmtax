package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/29 15:57
 */
public enum AccountDetailStatusEnum {
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 失败
     */
    FAIL(0);

    public Integer code;

    AccountDetailStatusEnum(Integer code) {
        this.code = code;
    }

}
