package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/30 11:32
 */
public enum BatchPaymentRecordStatusEnum {
    /**
     * 待打款
     */
    TODO(0),
    /**
     * 已取消
     */
    CANCEL(3),
    /**
     * 打款中
     */
    GOING(2),
    /**
     * 已完成
     */
    COMPLETE(1),
    /**
     * 已处理
     */
    SUCCESS(1),
    /**
     * 未处理
     */
    Fail(0);

    public Integer code;

    BatchPaymentRecordStatusEnum(Integer code) {
        this.code = code;

    }

}
