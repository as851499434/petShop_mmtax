package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 发票详情
 * @author lf
 */
public class InvoiceAmountDetailsDTO {
    @ApiModelProperty(value = "发票金额")
    private BigDecimal invoiceAmount;
    @ApiModelProperty(value = "充值流水号")
    private String orderSerialNum;
    @ApiModelProperty(value = "充值金额")
    private BigDecimal amount;
    @ApiModelProperty(value = "充值时间")
    private String createTime;

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "InvoiceAmountDetailsDTO{" +
                "invoiceAmount=" + invoiceAmount +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", amount=" + amount +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
