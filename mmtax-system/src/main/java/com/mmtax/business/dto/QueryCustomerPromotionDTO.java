package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.security.PrivateKey;

public class QueryCustomerPromotionDTO {

    @ApiModelProperty(value = "商户ID")
    private String merchantId;

    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @ApiModelProperty(value = "姓名")
    private String payeeName;

    @ApiModelProperty(value = "身份证号码")
    private String payeeIdCardNo;

    @ApiModelProperty(value = "手机号码")
    private String payeeMobile;

    @ApiModelProperty(value = "所属商户")
    private String merchantName;

    @ApiModelProperty(value = "推广开始时间")
    private String promotionStartTime;

    @ApiModelProperty(value = "推广结束时间")
    private String promotionEndTime;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "页码")
    private Integer currentPage;

    private Integer startIndex;


    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeIdCardNo() {
        return payeeIdCardNo;
    }

    public void setPayeeIdCardNo(String payeeIdCardNo) {
        this.payeeIdCardNo = payeeIdCardNo;
    }

    public String getPayeeMobile() {
        return payeeMobile;
    }

    public void setPayeeMobile(String payeeMobile) {
        this.payeeMobile = payeeMobile;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(String promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public String getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(String promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "QueryCustomerPromotionDTO{" +
                "merchantId='" + merchantId + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", payeeName='" + payeeName + '\'' +
                ", payeeIdCardNo='" + payeeIdCardNo + '\'' +
                ", payeeMobile='" + payeeMobile + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", promotionStartTime='" + promotionStartTime + '\'' +
                ", promotionEndTime='" + promotionEndTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", startIndex=" + startIndex +
                '}';
    }
}
