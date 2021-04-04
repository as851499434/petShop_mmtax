package com.mmtax.common.enums;

public enum PerMerApplySchEnum {
    //
    SUBMIT(1,"提交申请"),
    TURNDOWN(1,"驳回申请"),
    SUBMIT_AGAIN(1,"提交申请资料"),
    WORDER_ORDER(0,"生成待处理工单"),
    FEEDBACK_WORDER_ORDER(0,"反馈工单"),
    HANDLE_BUSINESS_LICENSE(2,"审核通过，开始办理"),
    BUSINESS_LICENSE_SUCCESS(2,"营业执照办理完成"),
    HANDLE_SUCCESS(3,"办理完成");

    private String action;
    private Integer type;

    PerMerApplySchEnum(Integer type, String action) {
        this.type = type;
        this.action = action;
    }

    public Integer getType() {
        return type;
    }

    public String getAction() {
        return action;
    }
}
