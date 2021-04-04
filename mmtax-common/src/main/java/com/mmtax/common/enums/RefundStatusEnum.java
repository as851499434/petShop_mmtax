package com.mmtax.common.enums;

public enum RefundStatusEnum {
    /**
     * 初始化
     */
    INIT(-1,"初始化"),
    /**
     * 进行中
     */
    PROCESS(0,"进行中"),
    /**
     * 已支付
     */
    SUCESS(1,"成功"),
    /**
     * 支付挂起
     */
    FAIL(2,"失败"),
    //重新发起
    AGAIN(3,"重新发起");

    private Integer code;
    private String description;

    RefundStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static RefundStatusEnum getByCode(Integer code){
        for (RefundStatusEnum one:RefundStatusEnum.values()) {
            if(one.getCode().equals(code)){
                return one;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
