package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 开票校验DTO
 * @author yuanligang
 * @date 2019/7/17
 */
public class InvoiceApplyCheckDTO {


    /**
     *流水集
     */
    @ApiModelProperty(value = "流水集")
    private List<Integer> records;

    /**
     * 开票金额
     */
    @ApiModelProperty(value = "开票金额", required = true)
    private BigDecimal invoiceAmount;
    /**
     * 税率
     */
    @ApiModelProperty(value = "税率", required = true)
    private BigDecimal taxRate;
    /**
     * 税额
     */
    @ApiModelProperty(value = "税额", required = true)
    private BigDecimal taxAmount;
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价", required = true)
    private BigDecimal unitPrice;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID", required = true)
    private  Integer merchantId;

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

    public List<Integer> getRecords() {
        return records;
    }

    public void setRecords(List<Integer> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "InvoiceApplyCheckDTO{" +
                "records=" + records +
                ", invoiceAmount=" + invoiceAmount +
                ", taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                ", unitPrice=" + unitPrice +
                ", merchantId=" + merchantId +
                '}';
    }
}
