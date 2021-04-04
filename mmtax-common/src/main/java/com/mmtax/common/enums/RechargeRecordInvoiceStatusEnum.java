package com.mmtax.common.enums;

public enum RechargeRecordInvoiceStatusEnum {


    /**
     * 未开发票
     */
    UN_MAKE_OUT_INVOICE(0),

    /**
     * 已开发票
     */
    MAKE_OUT_INVOICE(1);

    public Integer code;

    RechargeRecordInvoiceStatusEnum(int code) {
        this.code = code;
    }
}
