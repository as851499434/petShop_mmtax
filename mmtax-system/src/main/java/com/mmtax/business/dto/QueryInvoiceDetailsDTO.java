package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

public class QueryInvoiceDetailsDTO {

    @Excel(name = "发票申请编号")
    @ApiModelProperty("发票申请编号")
    private String invoiceSerialNum;

    @Excel(name = "发票申请编号")
    @ApiModelProperty("开票编号")
    private String invoiceNo;

    @Excel(name = "开票类型")
    @ApiModelProperty("开票类型")
    private String invoiceType;

    @Excel(name = "开票金额")
    @ApiModelProperty("开票金额")
    private String invoiceAmount;

    @Excel(name = "商户名称")
    @ApiModelProperty("商户名称")
        private String merchantName;

    @Excel(name = "科目内容")
    @ApiModelProperty("科目内容")
    private String invoiceContent;

    @Excel(name = "创建时间")
    @ApiModelProperty("创建时间")
    private String createTime;

    @Excel(name = "发票状态")
    @ApiModelProperty("发票状态")
    private String invoiceStatus;

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceSerialNum() {
        return invoiceSerialNum;
    }

    public void setInvoiceSerialNum(String invoiceSerialNum) {
        this.invoiceSerialNum = invoiceSerialNum;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "QueryInvoiceDetailsDTO{" +
                "invoiceSerialNum='" + invoiceSerialNum + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", invoiceAmount='" + invoiceAmount + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", invoiceContent='" + invoiceContent + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
