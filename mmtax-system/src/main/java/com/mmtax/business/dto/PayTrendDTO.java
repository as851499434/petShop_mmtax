package com.mmtax.business.dto;


import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 年度消费走势图DTO
 * @author yuanligang
 * @date 2019/7/16
 */
public class PayTrendDTO {

    /**
     * 账单月份
     */
    @ApiModelProperty(value = "账单月份")
    private String monthNo;

    /**
     *当月代付金额累计
     */
    @ApiModelProperty(value = "当月代付金额累计")
    private BigDecimal paymentAmount;

    public String getMonthNo() {
        return monthNo;
    }

    public void setMonthNo(String monthNo) {
        this.monthNo = monthNo;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "PayTrendDTO{" +
                "monthNo=" + monthNo +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
