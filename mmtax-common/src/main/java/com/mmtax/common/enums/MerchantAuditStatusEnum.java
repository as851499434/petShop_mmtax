package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/14 16:05
 */
public enum MerchantAuditStatusEnum {
    /**
     * 提交资料
     */
    SUBMISSION_INFORMATION(0),
    /**
     * 平台审核
     */
    PLATFORM_AUDIT(1),
    /**
     * 工商审核
     */
    BUSINESS_REGIST(2),
    /**
     * 审核完成
     */
    SUCCESS_REGIST(3);

    public int code;

    MerchantAuditStatusEnum(int code) {
        this.code = code;
    }

}
