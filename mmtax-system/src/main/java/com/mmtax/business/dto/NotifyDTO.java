package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/4/7 1:42 下午
 */
public class NotifyDTO {

    @ApiModelProperty("通知类型")
    private String notifyType;
    @ApiModelProperty("业务码code")
    private String code;
    @ApiModelProperty("业务描述")
    private String message;
    @ApiModelProperty("订单号")
    private String tradeNo;
    @ApiModelProperty("商户号")
    private String merchantNo;
    @ApiModelProperty("订单金额")
    private String amount;
    @ApiModelProperty("结算时间")
    private String settleTime;

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(String settleTime) {
        this.settleTime = settleTime;
    }

    @Override
    public String toString() {
        return "NotifyDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", amount='" + amount + '\'' +
                ", settleTime='" + settleTime + '\'' +
                '}';
    }
}
