package com.mmtax.business.dto;

/**
 * 后台获取商户开票信息
 * @author yuanligang
 * @date 2019/7/18
 */
public class SysInvoiceInfoDTO {
    /**
     * 发票基本信息
     */
    private InvoiceInfoDTO invoiceInfo;


    public InvoiceInfoDTO getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfoDTO invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    @Override
    public String toString() {
        return "SysInvoiceInfoDTO{" +
                "invoiceInfo=" + invoiceInfo +
                '}';
    }
}
