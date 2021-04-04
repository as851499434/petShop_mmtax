package com.mmtax.common.enums;

public enum AccountDetailTypeEnum {
    //0-出账冻结(冻结增加，可用减少)
    DEBIT_FREEZE(0,"出账冻结"),
    //1-入账冻结(冻结增加，总额增加)
    CREDIT_FREEZE(1,"入账冻结"),
    //2出账解冻(冻结减少，总额减少)
    DEBIT_UNFREEZE(2,"出账解冻"),
    //3入账解冻(冻结减少，可用增加)
    CREDIT_UNFREEZE(3,"入账解冻");

    private Integer type;
    private String description;

    AccountDetailTypeEnum(Integer type, String description) {
        this.type = type;
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
