package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/26 17:33
 */
public class DayPayTrendDTO {
    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("代付金额")
    private BigDecimal amount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
