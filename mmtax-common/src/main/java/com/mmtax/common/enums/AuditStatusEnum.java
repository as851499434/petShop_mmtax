package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/7 13:42
 */
public enum AuditStatusEnum {
    /**
     * 待审核
     */
    WAITING_AUDIT(0),

    /**
     * 同意
     */
    AGREE(1),

    /**
     * 拒绝
     */
    REFUSE(2);

    public int code;

    AuditStatusEnum(int code) {
        this.code = code;
    }


}
