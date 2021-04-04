package com.mmtax.business.dto;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/16 19:59
 */
public class PaymentResultDTO {

    /**
     * 返回描述
     */
    private String description;
    /**
     * 签名
     */
    private String sign;

    /**
     * 响应码
     */
    private String code;
    /**
     * 请求时间
     */
    private String transTime;

    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 备注
     */
    private String comment;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "description='" + description + '\'' +
                ", sign='" + sign + '\'' +
                ", code='" + code + '\'' +
                ", transTime='" + transTime + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
