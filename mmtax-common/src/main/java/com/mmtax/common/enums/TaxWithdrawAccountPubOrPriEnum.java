package com.mmtax.common.enums;

public enum TaxWithdrawAccountPubOrPriEnum {
    //对公
    PUBLIC(0,"对公"),
    //对私
    PRIVATE(1,"对私");

    private Integer value;
    private String description;

    TaxWithdrawAccountPubOrPriEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
