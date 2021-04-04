package com.mmtax.common.enums;

/**
 * @author yuanligang
 * @date 2019/7/31
 */
public enum AccountTypeEnum {
    /**
     * 代付
     */

    REPLACE_PAY(0),

    /**
     * 充值
     */
    RECHARGE(1),

    /**
     * 返点
     */
    RETURN_POINT(2),

    /**
     * 转入
     */
    CHANGE_IN(3),
    /**
     * 转出
     */
    CHANGE_OUT(4);

    private Integer code;

    AccountTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
