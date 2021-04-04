package com.mmtax.common.enums;

public enum RechargeInvoiceStatusEnum {
    /**
     * 未开发票
     */
    UNOPENED(0),
    /**
     * 已开发票
     */
    OPENED(1)
    ;
    public Integer code;

    RechargeInvoiceStatusEnum(Integer code) {
        this.code = code;
    }

}
