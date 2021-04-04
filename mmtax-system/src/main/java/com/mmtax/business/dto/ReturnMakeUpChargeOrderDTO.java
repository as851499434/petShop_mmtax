package com.mmtax.business.dto;


import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class ReturnMakeUpChargeOrderDTO {

    @Excel(name = "收款人姓名")
    @ApiModelProperty("收款人姓名")
    private String payeeName;

    @Excel(name = "收款账号")
    @ApiModelProperty("收款账号")
    private String payeeBankCard;

    @Excel(name = "商户打款请求(元)")
    @ApiModelProperty("商户打款请求(元)")
    private BigDecimal amount;

    @Excel(name = "订单流水号")
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;

    @Excel(name = "退补服务费(元)")
    @ApiModelProperty(value = "退补服务费(元)")
    private BigDecimal returnMakeupCharge;

    @Excel(name = "退补流水号")
    @ApiModelProperty(value = "退补流水号")
    private String returnMakeUpSerialNum;

    @Excel(name = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private String createTime;


    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeBankCard() {
        return payeeBankCard;
    }

    public void setPayeeBankCard(String payeeBankCard) {
        this.payeeBankCard = payeeBankCard;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getReturnMakeUpChargeepCharge() {
        return returnMakeupCharge;
    }

    public void setReturnMakeUpChargeepCharge(BigDecimal returnMakeUpChargeepCharge) {
        this.returnMakeupCharge = returnMakeUpChargeepCharge;
    }

    public String getReturnMakeUpSerialNum() {
        return returnMakeUpSerialNum;
    }

    public void setReturnMakeUpSerialNum(String returnMakeUpSerialNum) {
        this.returnMakeUpSerialNum = returnMakeUpSerialNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ReturnMakeUpChargeOrderDTO{" +
                "payeeName='" + payeeName + '\'' +
                ", payeeBankCard='" + payeeBankCard + '\'' +
                ", amount=" + amount +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", returnMakeUpChargeepCharge=" + returnMakeupCharge +
                ", returnMakeUpSerialNum='" + returnMakeUpSerialNum + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
