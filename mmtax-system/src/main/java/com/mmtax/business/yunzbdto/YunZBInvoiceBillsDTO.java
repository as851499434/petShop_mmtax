/**
 * Copyright 2019 bejson.com
 */
package com.mmtax.business.yunzbdto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Auto-generated: 2019-11-13 9:29:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class YunZBInvoiceBillsDTO {
    @ApiModelProperty("账单本次申请开票金额")
    private String apply_amt;
    @ApiModelProperty("剩余可开票金额")
    private String available_amt;
    @ApiModelProperty("账单金额")
    private String bill_amt;
    @ApiModelProperty("账单流水号")
    private String bill_id;
    @ApiModelProperty("账单完成时间")
    private Date bill_time;
    @ApiModelProperty("渠道")
    private String channel;

    public void setApply_amt(String apply_amt) {
        this.apply_amt = apply_amt;
    }

    public String getApply_amt() {
        return apply_amt;
    }

    public void setAvailable_amt(String available_amt) {
        this.available_amt = available_amt;
    }

    public String getAvailable_amt() {
        return available_amt;
    }

    public void setBill_amt(String bill_amt) {
        this.bill_amt = bill_amt;
    }

    public String getBill_amt() {
        return bill_amt;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_time(Date bill_time) {
        this.bill_time = bill_time;
    }

    public Date getBill_time() {
        return bill_time;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

}