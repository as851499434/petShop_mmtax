package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Arrays;

public class InvoiceRecordDTO {
    @ApiModelProperty("商户id")
    private Integer merchantId;
    @ApiModelProperty("发票金额")
    private BigDecimal invoiceAmount;
    @ApiModelProperty("发票税率")
    private BigDecimal taxRate;
    @ApiModelProperty("发票税额")
    private BigDecimal taxAmount;
    @ApiModelProperty("发票单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("发票类型")
    private Integer invocieType;
    @ApiModelProperty("发票备注")
    private String invoiceNote;
    @ApiModelProperty("货物或应税劳务、服务名称")
    private Integer invoiceContent;
    @ApiModelProperty("充值记录流水号")
    private String[] orderSerialNums;


    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getInvocieType() {
        return invocieType;
    }

    public void setInvocieType(Integer invocieType) {
        this.invocieType = invocieType;
    }

    public String getInvoiceNote() {
        return invoiceNote;
    }

    public void setInvoiceNote(String invoiceNote) {
        this.invoiceNote = invoiceNote;
    }

    public Integer getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(Integer invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String[] getOrderSerialNums() {
        return orderSerialNums;
    }

    public void setOrderSerialNums(String[] orderSerialNums) {
        this.orderSerialNums = orderSerialNums;
    }

    @Override
    public String toString() {
        return "InvoiceRecordDTO{" +
                "merchantId=" + merchantId +
                ", invoiceAmount=" + invoiceAmount +
                ", taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                ", unitPrice=" + unitPrice +
                ", invocieType=" + invocieType +
                ", invoiceNote='" + invoiceNote + '\'' +
                ", invoiceContent=" + invoiceContent +
                ", orderSerialNums=" + Arrays.toString(orderSerialNums) +
                '}';
    }
}
