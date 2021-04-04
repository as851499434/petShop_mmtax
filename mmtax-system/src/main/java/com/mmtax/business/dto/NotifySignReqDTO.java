package com.mmtax.business.dto;

import lombok.Data;

@Data
public class NotifySignReqDTO {
    /**
     * 付款渠道(查询接口不需要)
     */
    private String paymentChannel;
    /**
     * 银行卡号(查询接口不需要)
     */
    private String bankCardNo;
    /**
     * 支付宝账号(查询接口不需要)
     */
    private String accountNo;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 姓名(查询接口不需要)
     */
    private String name;
    /**
     * 身份证(查询接口不需要)
     */
    private String idCardNo;
    /**
     * (查询接口不需要)
     */
    private String mobileNo;
    /**
     * 请求时间，精确到秒
     */
    private String requireTime;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名方式
     */
    private String signType;
    /**
     * 私钥
     */
    private String desKey;
    /**
     * 版本号
     */
    private String version;
    /**
     * 签约流水号(唯一)
     */
    private String orderId;
    /**
     * 查询用签约流水号(唯一)
     */
    private String queryOrderId;
    /**
     * 签约岗位
     */
    private String post;
}
