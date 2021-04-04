package com.mmtax.common.enums;

public enum  InOutDeductionEnum {
    //
    OUTDEDUCTION(1,"外扣"),
    //
    INDEDUCTION(0,"内扣");

    private Integer status;
    private String decription;

    InOutDeductionEnum(Integer status, String decription) {
        this.status = status;
        this.decription = decription;
    }

    public static InOutDeductionEnum getByStatus(Integer status){
        for(InOutDeductionEnum one:InOutDeductionEnum.values()){
            if(one.getStatus().equals(status)){
                return one;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDecription() {
        return decription;
    }
}
