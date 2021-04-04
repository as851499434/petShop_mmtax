package com.mmtax.common.enums;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/19 14:03
 */
public enum CheckAndChangeRateEnum {
    //0-未变动
    NOCHANGE(0);
    private Integer state;

    public Integer getState() {
        return state;
    }

    CheckAndChangeRateEnum(Integer state) {
        this.state = state;
    }
}
