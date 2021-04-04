package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 商户发票详情页 合计
 *
 * @author yuanligang
 * @date 2019/8/10
 */
public class MerchantInvoiceTotalAmountDTO {

    @ApiModelProperty("总开票记录数")
    private Integer totalCount;

    @ApiModelProperty("总金额")
    private BigDecimal totalUnitprice;

    @ApiModelProperty("总税额")
    private BigDecimal totalTaxAmount;

    @ApiModelProperty("总价税合计")
    private BigDecimal totalAmount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalUnitprice() {
        return totalUnitprice;
    }

    public void setTotalUnitprice(BigDecimal totalUnitprice) {
        this.totalUnitprice = totalUnitprice;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "MerchantInvoiceTotalAmountDTO{" +
                "totalCount=" + totalCount +
                ", totalUnitprice=" + totalUnitprice +
                ", totalTaxAmount=" + totalTaxAmount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
