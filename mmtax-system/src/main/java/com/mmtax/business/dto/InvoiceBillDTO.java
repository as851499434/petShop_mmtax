package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

public class InvoiceBillDTO {


    @ApiModelProperty(value = "商户ID", required = true)
    private Integer merchantId;
    @ApiModelProperty(value = "每页大小", required = true)
    private Integer pageSize;
    @ApiModelProperty(value = "页码", required = true)
    private Integer currentPage;
    @ApiModelProperty(value = "充值起始时间", required = false)
    private String startDate;
    @ApiModelProperty(value = "充值结束时间", required = false)
    private String endDate;
    @ApiModelProperty(value = "充值金额始", required = false)
    private String startMoney;
    @ApiModelProperty(value = "充值金额终", required = false)
    private String endMoney;
    @ApiModelProperty(hidden = true)
    private Integer startIndex;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
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

    public String getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(String startMoney) {
        this.startMoney = startMoney;
    }

    public String getEndMoney() {
        return endMoney;
    }

    public void setEndMoney(String endMoney) {
        this.endMoney = endMoney;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    @Override
    public String toString() {
        return "InvoiceBillDTO{" +
                "merchantId=" + merchantId +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startMoney='" + startMoney + '\'' +
                ", endMoney='" + endMoney + '\'' +
                ", startIndex=" + startIndex +
                '}';
    }
}
