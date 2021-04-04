package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 后台系统交易订单查询
 * @author yuanligang
 * @date 2019/7/19
 */
public class SysTrxRecordDTO {

    /**
     * 起始日期
     */
    @ApiModelProperty("起始时间")
    private  String startDate;
    /**
     * 结束日期
     */
    @ApiModelProperty("结束日期")
    private  String endDate;
    /**
     * 商户名称
     */
    @ApiModelProperty("商户名称")
    private  String merchantName;
    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private  String orderSerialNum;

    /**
     * 交易备注
     */
    @ApiModelProperty("交易备注")
    private  String comment;

    /**
     * 订单状态
     */
    @ApiModelProperty("订单状态")
    private  Integer orderStatus;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "SysTrxRecordDTO{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", comment='" + comment + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
