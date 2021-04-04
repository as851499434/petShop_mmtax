package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author lf
 */
public class AgreeAndRefuseCheckDTO {
    @ApiModelProperty(value = "发票记录表id")
    private Integer id;
    /**
     * 开票状态
     */
    @ApiModelProperty
            (value = "1-申请开票 2-已完成 3-已驳回（申请开票驳回）4-退票申请中 5-退票已完成 6-退票已驳回 7-等待开票 8-已作废")
    private String invoiceStatus;
    /**
     * 申请请求
     */
    @ApiModelProperty(value = "0-驳回 1-通过")
    private String invoiceId;
    /**
     * 发票申请编号
     */
    @ApiModelProperty(value = "发票申请编号")
    private String invoiceSerialNum;
    /**
     * 发票驳回理由
     */
    @ApiModelProperty(value = "发票驳回理由")
    private String rejectedReason;
    /**
     * 发票号
     */
    @ApiModelProperty(value = "发票号")
    private String invoiceNo;
    /**
     * 快递公司名字
     */
    @ApiModelProperty(value = "快递公司名字")
    private String expressCompanyName;
    /**
     * 快递单号
     */
    @ApiModelProperty(value = "快递单号")
    private String expressNo;

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceSerialNum() {
        return invoiceSerialNum;
    }

    public void setInvoiceSerialNum(String invoiceSerialNum) {
        this.invoiceSerialNum = invoiceSerialNum;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    @Override
    public String toString() {
        return "AgreeAndRefuseCheckDTO{" +
                "id='" + id + '\'' +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", invoiceSerialNum='" + invoiceSerialNum + '\'' +
                ", rejectedReason='" + rejectedReason + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", expressCompanyName='" + expressCompanyName + '\'' +
                ", expressNo='" + expressNo + '\'' +
                '}';
    }
}
