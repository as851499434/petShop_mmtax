package com.mmtax.business.dto;

import com.mmtax.business.yunzbdto.YunZBInvoiceContentResultDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 发票申请金额相关
 *
 * @author yuanligang
 * @date 2019/8/9
 */
public class InvoiceAmountDTO {
    /**
     * 应税劳务服务名称
     */
    @ApiModelProperty(value = "应税劳务服务名称")
    private YunZBInvoiceContentResultDTO invoiceServiceName;

    /**
     * 开票申请金额
     */
    @ApiModelProperty(value = "开票申请金额", required = true)
    private BigDecimal amount;

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

    public YunZBInvoiceContentResultDTO getInvoiceServiceName() {
        return invoiceServiceName;
    }

    public void setInvoiceServiceName(YunZBInvoiceContentResultDTO invoiceServiceName) {
        this.invoiceServiceName = invoiceServiceName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        return "InvoiceAmountDTO{" +
                "invoiceServiceName=" + invoiceServiceName +
                ", amount=" + amount +
                ", taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
