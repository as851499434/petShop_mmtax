package com.mmtax.common.enums;

public enum LinkRecordTypeEnum {
    //
    TRANFER_ORDER(1,"tbl_transfer_order表");

    private Integer type;
    private String decription;

    LinkRecordTypeEnum(Integer type, String decription) {
        this.type = type;
        this.decription = decription;
    }

    public static LinkRecordTypeEnum getEnumByType(Integer type){
        for(LinkRecordTypeEnum one:LinkRecordTypeEnum.values()){
            if(one.getType().equals(type)){
                return one;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public String getDecription() {
        return decription;
    }
}
