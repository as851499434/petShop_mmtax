package com.mmtax.common.enums;

public enum InvoiceTypeEnum {

    ORDINARY_INVOICE ("普通发票"),
    VAT_INVOICE("增值税专用发票"),
    ORDINARY_INVOICE_NUMBER("1"),
    VAT_INVOICE_NUMBER("0");
    public String code;
    InvoiceTypeEnum(String code) {
        this.code = code;
    }
}
