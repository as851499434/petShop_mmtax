package com.mmtax.common.enums;

public enum TransferStatusEnum {
    /**
     * 初始化
     */
    INIT(-1,"初始化"),
    /**
     * 进行中
     */
    UNPAID(0,"进行中"),
    /**
     * 已支付
     */
    PAID(1,"成功"),
    /**
     * 支付挂起
     */
    PAID_PENDING(2,"失败"),
    //回退的转账
    RETURN_PAIDING(3,"回退中"),
    //回退完成
    RETURN_PAID(4,"回退完成"),
    //回退完成
    RETURN_FAIL(5,"回退失败");

    private Integer code;
    private String description;

    TransferStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
