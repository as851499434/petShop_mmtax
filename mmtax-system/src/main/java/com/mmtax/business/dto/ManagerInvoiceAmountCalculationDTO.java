package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author yuanligang
 * @date 2019/8/12
 */
public class ManagerInvoiceAmountCalculationDTO {
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

    @ApiModelProperty(value = "开票金额", hidden = true)
    private BigDecimal amount;


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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MerchantInvoiceAmountCalculationDTO{" +
                "taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                '}';
    }
}
