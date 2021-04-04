package com.mmtax.common.enums;

/**
 * 发票渠道枚举
 */
public enum InvoiceChannelEnum {
    /**
     * 云资金渠道
     */
    YUNZIJIN("云资金"),
    /**
     * 天津渠道
     */
    TIANJIN("天津");

    private String explain;

    InvoiceChannelEnum(String explain) {
        this.explain = explain;
    }

    public String getExplain() {
        return explain;
    }
}
