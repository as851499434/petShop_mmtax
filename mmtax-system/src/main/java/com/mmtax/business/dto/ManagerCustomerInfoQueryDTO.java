package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 后台员工信息查询
 * @author Ljd
 * @date 2020/7/10
 */
public class ManagerCustomerInfoQueryDTO {

    @ApiModelProperty(value = "员工姓名")
    private String realName;

    @ApiModelProperty(value = "所属商户名称")
    private String merchantName;

    @ApiModelProperty(value = "预警字段")
    private Integer customerWarn;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getCustomerWarn() {
        return customerWarn;
    }

    public void setCustomerWarn(Integer customerWarn) {
        this.customerWarn = customerWarn;
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
