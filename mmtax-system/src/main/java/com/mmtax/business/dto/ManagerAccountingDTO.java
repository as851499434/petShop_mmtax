package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 账户列表-->详情
 *
 * @author yuanligang
 * @date 2019/7/31
 */
public class ManagerAccountingDTO {

    @ApiModelProperty("每页大小")
    private Integer pageSize;

    @ApiModelProperty("当前页码")
    private Integer pageNum;

    @ApiModelProperty(value = "商户ID", required = true)
    private Integer merchantId;

    @ApiModelProperty(value = "创建开始时间", required = false)
    private String startDate;

    @ApiModelProperty(value = "创建结束时间", required = false)
    private String endDate;

    @ApiModelProperty(value = "流水号", required = false)
    private String orderSerialNum;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
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

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
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
}
