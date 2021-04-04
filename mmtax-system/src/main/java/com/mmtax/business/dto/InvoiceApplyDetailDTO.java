package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 开票申请申请DTO
 *
 * @author yuanligang
 * @date 2019/7/24
 */
public class InvoiceApplyDetailDTO {
    /**
     * 充值记录
     */
    @ApiModelProperty(value = "充值记录")
    private List<Integer> records;
    /**
     * 发票实体集
     */
    @ApiModelProperty(value = "发票实体集")
    private List<InvoiceApplyDTO> invoices;
    /**
     * 发票金额版面 1: 千元版 2：万元版 3: 十万元版 4：百万元版
     */
    @ApiModelProperty("发票金额版面 1: 千元版 2：万元版 3: 十万元版 4：百万元版")
    private String invoiceAmtType;

    public List<Integer> getRecords() {
        return records;
    }

    public void setRecords(List<Integer> records) {
        this.records = records;
    }

    public List<InvoiceApplyDTO> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceApplyDTO> invoices) {
        this.invoices = invoices;
    }

    public String getInvoiceAmtType() {
        return invoiceAmtType;
    }

    public void setInvoiceAmtType(String invoiceAmtType) {
        this.invoiceAmtType = invoiceAmtType;
    }

    @Override
    public String toString() {
        return "InvoiceApplyDetailDTO{" +
                "records=" + records +
                ", invoices=" + invoices +
                ", invoiceAmtType='" + invoiceAmtType + '\'' +
                '}';
    }
}
