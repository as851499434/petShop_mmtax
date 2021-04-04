package com.mmtax.common.enums;

/**
 * 发票状态枚举
 *
 * @author yuanligang
 * @date 2019/7/10
 */
public enum InvoiceStatusEnum {
    /**
     * 申请中
     */
    APPLY("1"),
    /**
     * 已同意
     */
    AGREE("2"),
    /**
     * 已寄出
     */
    POSTED("4"),
    /**
     * 已拒绝
     */
    REFUSE("3"),
    /**
     * 无效
     */
    INVALID("5");

    public String code;

    InvoiceStatusEnum(String code) {
        this.code = code;
    }

}
