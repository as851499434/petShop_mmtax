package com.mmtax.common.enums;

/**
 * 发票有效状态枚举
 *
 * @author yuanligang
 * @date 2019/8/6
 */
public enum InvoiceEffectiveEnum {
    /**
     * 未作废
     */
    EFFECTIVE(0),

    /**
     * 已作废
     */
    INVALID(1),

    /**
     * 作废中
     */
    PROCESSING(2),

    /**
     * 重开中
     */
    RESTARTING(3),

    /**
     * 已经重开
     */
    RESTARTED(4);

    private Integer code;

    InvoiceEffectiveEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
