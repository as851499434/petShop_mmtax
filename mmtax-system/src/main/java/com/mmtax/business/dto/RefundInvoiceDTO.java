package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.util.Date;

public class RefundInvoiceDTO {

    @ApiModelProperty(value = "发票申请编号", required = true)
    private String invoiceSerialum;
    @ApiModelProperty(value = "退票原因", required = true)
    private String returnReason;
    @ApiModelProperty(value = "退票快递公司名称", required = true)
    private String expressCompanyNameReturn;
    @ApiModelProperty(value = "退票快递单号", required = true)
    private String expressNoReturn;
    @ApiModelProperty(value = "退票备注", required = true)
    private String returnRemark;

    private  Date updateTime;

    public String getInvoiceSerialum() {
        return invoiceSerialum;
    }

    public void setInvoiceSerialum(String invoiceSerialum) {
        this.invoiceSerialum = invoiceSerialum;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getExpressCompanyNameReturn() {
        return expressCompanyNameReturn;
    }

    public void setExpressCompanyNameReturn(String expressCompanyNameReturn) {
        this.expressCompanyNameReturn = expressCompanyNameReturn;
    }

    public String getExpressNoReturn() {
        return expressNoReturn;
    }

    public void setExpressNoReturn(String expressNoReturn) {
        this.expressNoReturn = expressNoReturn;
    }

    public String getReturnRemark() {
        return returnRemark;
    }

    public void setReturnRemark(String returnRemark) {
        this.returnRemark = returnRemark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "RefundInvoiceDTO{" +
                "invoiceSerialum='" + invoiceSerialum + '\'' +
                ", returnReason='" + returnReason + '\'' +
                ", expressCompanyNameReturn='" + expressCompanyNameReturn + '\'' +
                ", expressNoReturn='" + expressNoReturn + '\'' +
                ", returnRemark='" + returnRemark + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
