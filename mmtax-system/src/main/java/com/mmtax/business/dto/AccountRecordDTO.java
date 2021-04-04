package com.mmtax.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/18 18:31
 */
@ApiModel(description = "商户账户流水")
public class AccountRecordDTO {

    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "申请时间")
    private String createTime;
    @ApiModelProperty(value = "审核时间")
    private String updateTime;
    @ApiModelProperty(value = "流水号")
    private String orderSerialNum;
    @ApiModelProperty(value = "金额")
    private String amount;

    @JsonIgnore
    private String startDate;
    @JsonIgnore
    private String endDate;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
