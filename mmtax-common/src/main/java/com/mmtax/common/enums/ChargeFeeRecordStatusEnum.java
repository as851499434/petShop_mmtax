package com.mmtax.common.enums;

public enum ChargeFeeRecordStatusEnum {
    /**
     * 初始化
     */
    INIT(-1,"初始化"),
    /**
     * 进行中
     */
    PROCESS(0,"进行中"),
    /**
     * 成功
     */
    SUCCESS(1,"成功"),
    /**
     * 手动处理
     */
    MANUAL(2,"手动处理"),
    /**
     * 失败
     */
    FAIL(3,"失败"),
    /**
     * 手动处理完成
     */
    MANUALSUCESS(4,"手动处理完成"),
    /**
     * 已再次发起
     */
    AGAINPAY(5,"已再次发起");

    private Integer status;
    private String explain;

    ChargeFeeRecordStatusEnum(Integer status, String explain) {
        this.status = status;
        this.explain = explain;
    }

    public Integer getStatus() {
        return status;
    }

    public String getExplain() {
        return explain;
    }
}
