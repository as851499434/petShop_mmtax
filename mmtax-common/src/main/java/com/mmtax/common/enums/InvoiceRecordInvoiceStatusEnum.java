package com.mmtax.common.enums;

public enum InvoiceRecordInvoiceStatusEnum {

    /**
     * 申请开票
     */
    APPLY(1),

    /**
     * 开票完成
     */
    APPLY_COMPLETE(2),

    /**
     * 申请开票驳回
     */
    APPLY_REJECTED(3),

    /**
     * 退票申请中
     */
    REFUND(4),

    /**
     * 退票已完成
     */
    REFUND_COMPLETE(5),

    /**
     * 退票拒绝
     */
    REFUND_REJETED(6),

    /**
     * 等待开票
     */
    WAITING_MAKE_OUT_INVOICE(7),

    /**
     * 发票作废
     */
    INVALID(8);

    public Integer code;

    InvoiceRecordInvoiceStatusEnum(Integer code) {
        this.code = code;
    }
}
