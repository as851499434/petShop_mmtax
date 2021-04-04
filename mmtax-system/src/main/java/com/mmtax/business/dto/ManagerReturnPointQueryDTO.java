package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 返点
 *
 * @author yuanligang
 * @date 2019/7/31
 */
public class ManagerReturnPointQueryDTO {
    @ApiModelProperty(value = "商户ID")
    private Integer merchantId;
    @ApiModelProperty("起始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;
    @ApiModelProperty("审核状态")
    private Integer status;
    @ApiModelProperty(value = "交易状态", hidden = true)
    private Integer type;
    @ApiModelProperty("每页大小")
    private Integer pageSize;

    @ApiModelProperty("当前页码")
    private Integer pageNum;
    @ApiModelProperty("商户名称")
    private String merchantName;


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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Override
    public String toString() {
        return "ManagerReturnPointQueryDTO{" +
                "merchantId=" + merchantId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", merchantName='" + merchantName + '\'' +
                '}';
    }
}
