package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yuanligang
 * @date 2019/8/13
 */
public class MerchantInvoiceRefuseDTO {
    /**
     * 发票ID
     */
    private Integer id;
    /**
     * 开票类型
     */
    @ApiModelProperty(value = "0-增值税专用发票1-普通发票", required = true)
    private Integer invoiceType;
    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行", required = true)
    private String bankName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remarks;
    /**
     * 开票说明
     */
    @ApiModelProperty(value = "开票说明")
    private String instruction;

    /**
     * 服务费金额集合
     */
    @ApiModelProperty(value = "服务费金额集合", required = true)
    private List<InvoiceAmountDTO> serviceAmountList;

    @ApiModelProperty(value = "单张发票合计金额")
    private BigDecimal singleInvoiceAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<InvoiceAmountDTO> getServiceAmountList() {
        return serviceAmountList;
    }

    public void setServiceAmountList(List<InvoiceAmountDTO> serviceAmountList) {
        this.serviceAmountList = serviceAmountList;
    }

    public BigDecimal getSingleInvoiceAmount() {
        return singleInvoiceAmount;
    }

    public void setSingleInvoiceAmount(BigDecimal singleInvoiceAmount) {
        this.singleInvoiceAmount = singleInvoiceAmount;
    }

    @Override
    public String toString() {
        return "MerchantInvoiceRefuseDTO{" +
                "id=" + id +
                ", invoiceType=" + invoiceType +
                ", bankName='" + bankName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", instruction='" + instruction + '\'' +
                ", serviceAmountList=" + serviceAmountList +
                ", singleInvoiceAmount=" + singleInvoiceAmount +
                '}';
    }
}
