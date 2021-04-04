package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值记录详情
 */
public class RechargeDetialDTO {

    @ApiModelProperty("充值流水号")
    private String orderSerialNum;

    /**
     * 充值金额
     */
    @ApiModelProperty("充值金额== 申请开票金额")
    private BigDecimal amount;

    @ApiModelProperty("充值时间")
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RechargeDetialDTO{" +
                "orderSerialNum='" + orderSerialNum + '\'' +
                ", amount=" + amount +
                ", createTime=" + createTime +
                '}';
    }
}
