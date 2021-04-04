package com.mmtax.business.dto;


import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/31 4:56 下午
 */
public class ManagerRechargeDTO  {
    @ApiModelProperty(value = "商户id")
    private Integer id;

    @ApiModelProperty(value = "商户名字")
    private String merchantName;

    @ApiModelProperty(value = "商户号")
    private String merchantCode;

    @ApiModelProperty(value = "商户手机号")
    private String contactsMobile;

    @ApiModelProperty(value = "所属销售")
    private String userName;

    @ApiModelProperty(value = "流水号")
    private String orderSerialNum;

    @ApiModelProperty(value = "充值金额")
    private String amount;

    @ApiModelProperty(value = "充值类型")
    private String rechargeType;

    @ApiModelProperty(value = "状态")
    private String status;


    @ApiModelProperty(value = "入账时间")
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
