package com.mmtax.common.enums;

public enum InvoiceNatureEnum {



    /**
     * 纸质发票
     */
    PAPER_INVOICE(1),

    /**
     * 电子发票
     */
    E_INVOICE(2);

    public Integer code;

    InvoiceNatureEnum(int code) {
        this.code = code;
    }
}
