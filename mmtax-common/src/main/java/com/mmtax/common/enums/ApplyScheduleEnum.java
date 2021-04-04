package com.mmtax.common.enums;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/30 17:40
 */
public enum ApplyScheduleEnum {
    /**
     * 0-未知
     */
    UNKNOWN(0),
    /**
     * 1-提交资料审核
     */
    CKECK(1),
    /**
     * 2-办理营业执照
     */
    LICENSE(2);
    public int code;

    ApplyScheduleEnum(int code) {
        this.code = code;
    }
}
