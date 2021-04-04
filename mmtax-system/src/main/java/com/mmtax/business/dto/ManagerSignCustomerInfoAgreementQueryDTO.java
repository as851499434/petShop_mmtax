package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 后台员工签约协议查询
 * @author Ljd
 * @date 2020/7/10
 */
public class ManagerSignCustomerInfoAgreementQueryDTO {

    @ApiModelProperty(value = "签约甲方")
    private String taxSourceName;

    @ApiModelProperty(value = "签约员工")
    private String name;

    @ApiModelProperty(value = "所属商户名称")
    private String merchantName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "签约发起开始时间")
    private String startDate;

    @ApiModelProperty(value = "签约发起结束时间")
    private String endDate;

    @ApiModelProperty(value = "协议状态")
    private String expireStatus;

    public String getTaxSourceName() {
        return taxSourceName;
    }

    public void setTaxSourceName(String taxSourceName) {
        this.taxSourceName = taxSourceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getExpireStatus() {
        return expireStatus;
    }

    public void setExpireStatus(String expireStatus) {
        this.expireStatus = expireStatus;
    }
}
