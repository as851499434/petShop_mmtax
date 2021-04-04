package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 发票上传
 *
 * @author yuanligang
 * @date 2019/8/2
 */
public class InvoiceUploadDTO {
    @ApiModelProperty("发票详情ID")
    private Integer invoiceDetailId;
    @ApiModelProperty("发票影印件地址")
    private String invoiceImg;
    @ApiModelProperty("发票编号")
    private String invoiceNum;
    @ApiModelProperty("发票代码")
    private String invoiceCode;
    @ApiModelProperty("开票时间")
    private String invoiceTime;

    public Integer getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(Integer invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    public String getInvoiceImg() {
        return invoiceImg;
    }

    public void setInvoiceImg(String invoiceImg) {
        this.invoiceImg = invoiceImg;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(String invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    @Override
    public String toString() {
        return "InvoiceUploadDTO{" +
                "invoiceDetailId=" + invoiceDetailId +
                ", invoiceImg='" + invoiceImg + '\'' +
                ", invoiceNum='" + invoiceNum + '\'' +
                ", invoiceCode='" + invoiceCode + '\'' +
                ", invoiceTime='" + invoiceTime + '\'' +
                '}';
    }
}
