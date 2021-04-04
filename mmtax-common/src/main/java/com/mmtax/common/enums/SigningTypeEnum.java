package com.mmtax.common.enums;

/**
 * 签约类型枚举类
 * @author yuanligang
 * @date 2019/7/10
 */
public enum  SigningTypeEnum {

    /**
     * 微信签约
     */
    WECHAT("微信小程序签约", 0),

    /**
     * 线上签约
     */
    ONLINE("线上签约", 1),

    /**
     *纸质签约
     */
    PAPER("纸质签约", 2),

    /**
     * 线上签约(可打款)
     */
    ONLINEPAY("线上签约(可打款)", 3);

    SigningTypeEnum(String des, Integer value) {
        this.des = des;
        this.value =value;
    }
    /**
     * 描述信息
     */
    private String des;

    /**
     * 字段值
     */
    private Integer value;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
