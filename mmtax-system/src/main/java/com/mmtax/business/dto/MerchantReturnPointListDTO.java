package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 抵扣账户返点
 *
 * @author yuanligang
 * @date 2019/7/31
 */
public class MerchantReturnPointListDTO {

    @Excel(name = "商户名称")
    @ApiModelProperty("商户名称")
    private String merchantName;

    @Excel(name = "入账时间")
    @ApiModelProperty("入账时间")
    private String inAccountTime;

    @Excel(name = "订单流水号")
    @ApiModelProperty("订单流水号")
    private String orderSerialNum;

    @Excel(name = "入账金额")
    @ApiModelProperty("入账金额")
    private BigDecimal paymentAmount;

    public String getInAccountTime() {
        return inAccountTime;
    }

    public void setInAccountTime(String inAccountTime) {
        this.inAccountTime = inAccountTime;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Override
    public String toString() {
        return "MerchantReturnPointListDTO{" +
                "merchantName='" + merchantName + '\'' +
                ", inAccountTime='" + inAccountTime + '\'' +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
