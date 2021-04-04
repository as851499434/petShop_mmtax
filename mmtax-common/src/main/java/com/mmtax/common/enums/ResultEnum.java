package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/16 20:03
 */
public enum ResultEnum {
    /**
     * 请求成功
     */
    SUCCESS("请求成功"),
    /**
     * 请求失败
     */
    FAIL("请求失败"),

    /**
     * 查询订单记录不存在
     */
    ORDER_NOT_EXIST("您查询的订单不存在"),
    /**
     * 签名类型不正确
     */
    ILLEGAL_SIGN_TYPE("签名类型不正确"),
    /**
     * 验签未通过
     */
    ILLEGAL_SIGN("验签未通过"),
    /**
     * 重复的请求号
     */
    DUPLICATE_REQUEST_NO("重复的请求号"),
    /**
     * 交易总额小于等于0
     */
    TOTAL_FEE_LESSEQUAL_ZERO("交易总额小于等于0"),
    /**
     * 未知商户
     */
    UNKOWN_MERCHANT("未知商户"),
    /**
     * 版本号错误
     */
    VERSION_ERROR("版本号错误"),

    /**
     * 您的钱包余额不足
     */
    INSUFFICIENT_WALLET_BALANCE("您的钱包余额不足"),
    /**
     * 秘钥错误
     */
    ERROR_APP_KEY("秘钥错误");


    public String description;

    ResultEnum(String description) {
        this.description = description;

    }
}
