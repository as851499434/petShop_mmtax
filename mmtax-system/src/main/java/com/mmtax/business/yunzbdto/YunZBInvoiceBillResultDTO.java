package com.mmtax.business.yunzbdto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/12 9:38
 */
public class YunZBInvoiceBillResultDTO {
    /**
     * 可开票金额
     */
    @ApiModelProperty("可开票金额")
    private BigDecimal available_amt;
    /**
     * 账单金额
     */
    @ApiModelProperty("充值金额")
    private BigDecimal bill_amt;
    /**
     * 账单流水号
     */
    @ApiModelProperty("流水号")
    private String bill_id;
    /**
     * 账单完成时间
     */
    @ApiModelProperty("充值时间")
    private String bill_time;
    /**
     * 渠道
     */
    @ApiModelProperty("开票渠道")
    private String channel;

    public BigDecimal getAvailable_amt() {
        return available_amt;
    }

    public void setAvailable_amt(BigDecimal available_amt) {
        this.available_amt = available_amt;
    }

    public BigDecimal getBill_amt() {
        return bill_amt;
    }

    public void setBill_amt(BigDecimal bill_amt) {
        this.bill_amt = bill_amt;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_time() {
        return bill_time;
    }

    public void setBill_time(String bill_time) {
        this.bill_time = bill_time;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
