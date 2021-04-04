package com.mmtax.common.enums;

public enum TaxWithdrawAccountAllowEnum {
    //允许
    ALLOW(1,"可提现"),
    UNALLOW(0,"不可提现");

    private Integer status;
    private String description;

    TaxWithdrawAccountAllowEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public static TaxWithdrawAccountAllowEnum getBystatus(Integer status){
        for (TaxWithdrawAccountAllowEnum e:TaxWithdrawAccountAllowEnum.values()){
            if(e.getStatus().equals(status)){
                return e;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
