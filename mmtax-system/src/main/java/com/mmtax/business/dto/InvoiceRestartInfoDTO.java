package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 发票重开申请DTO
 *
 * @author yuanligang
 * @date 2019/8/5
 */
public class InvoiceRestartInfoDTO {

    @ApiModelProperty(value = "重开(作废)发票ID", required = true)
    private Integer invoiceId;
    @ApiModelProperty("红冲发票信息表")
    private String redInvoice;
    @ApiModelProperty(value = "盖章版退票说明", required = true)
    private String sealExplain;
    @ApiModelProperty(value = "发票认证状态", required = true)
    private Integer status;
    @ApiModelProperty(value = "发票实体集", required = true)
    private List<InvoiceApplyDTO> invoices;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getRedInvoice() {
        return redInvoice;
    }

    public void setRedInvoice(String redInvoice) {
        this.redInvoice = redInvoice;
    }

    public String getSealExplain() {
        return sealExplain;
    }

    public void setSealExplain(String sealExplain) {
        this.sealExplain = sealExplain;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<InvoiceApplyDTO> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceApplyDTO> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "InvoiceRestartInfoDTO{" +
                "invoiceId=" + invoiceId +
                ", redInvoice='" + redInvoice + '\'' +
                ", sealExplain='" + sealExplain + '\'' +
                ", status=" + status +
                ", invoices=" + invoices +
                '}';
    }
}
