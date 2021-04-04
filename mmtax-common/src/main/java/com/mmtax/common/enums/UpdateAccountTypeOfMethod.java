package com.mmtax.common.enums;

public enum UpdateAccountTypeOfMethod {
    /**
     * 代付时冻结金额或代付失败补回金额
     */
    PAYORMAKEUP(1,"冻结或解冻到可用金额"),
    /**
     * 代付成功解冻金额
     */
    PAYSUCCESS(2,"2代付成功总余额扣除冻结金额");

    private Integer type;
    private String explain;

    UpdateAccountTypeOfMethod(Integer type, String explain) {
        this.type = type;
        this.explain = explain;
    }

    public Integer getType() {
        return type;
    }

    public String getExplain() {
        return explain;
    }
}
