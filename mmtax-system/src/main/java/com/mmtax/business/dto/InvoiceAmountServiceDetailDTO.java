package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author yuanligang
 * @date 2019/8/9
 */
public class InvoiceAmountServiceDetailDTO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 对应发票ID
     */
    @ApiModelProperty("发票ID")
    private Integer invoiceId;
    /**
     * 应税劳务服务名
     */
    @ApiModelProperty("应税劳务服务名")
    private String invoiceSerialName;
    /**  */
    @ApiModelProperty("开票金额")
    private BigDecimal invoiceAmount;
    /**
     * 税率
     */
    @ApiModelProperty("税率")
    private BigDecimal taxRate;
    /**
     * 发票税额
     */
    @ApiModelProperty("发票税额")
    private BigDecimal taxAmount;
    /**
     * 单价
     */
    @ApiModelProperty("单价")
    private BigDecimal unitPrice;
    @ApiModelProperty("发票详情id")
    private Integer invoiceDetailId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceSerialName() {
        return invoiceSerialName;
    }

    public void setInvoiceSerialName(String invoiceSerialName) {
        this.invoiceSerialName = invoiceSerialName;
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

    public Integer getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(Integer invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    @Override
    public String toString() {
        return "InvoiceAmountServiceDetailDTO{" +
                "id=" + id +
                ", invoiceId=" + invoiceId +
                ", invoiceSerialName='" + invoiceSerialName + '\'' +
                ", invoiceAmount=" + invoiceAmount +
                ", taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                ", unitPrice=" + unitPrice +
                ", invoiceDetailId=" + invoiceDetailId +
                '}';
    }
}
