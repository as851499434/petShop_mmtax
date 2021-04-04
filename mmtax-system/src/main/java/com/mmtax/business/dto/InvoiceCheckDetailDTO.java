package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 发票审核信息查看DTO
 *
 * @author yuanligang
 * @date 2019/7/18
 */
public class InvoiceCheckDetailDTO {

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

    @ApiModelProperty("单张发票开票总额")
    private BigDecimal invoiceAmount;

    @ApiModelProperty
    private List<InvoiceAmountServiceDetailDTO> serviceAmounts;

    @ApiModelProperty("发票申请编号")
    private String invoiceSerialNum;
    @ApiModelProperty("发票详情id")
    private Integer invoiceDetailId;


    public Integer getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(Integer invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

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

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public List<InvoiceAmountServiceDetailDTO> getServiceAmounts() {
        return serviceAmounts;
    }

    public void setServiceAmounts(List<InvoiceAmountServiceDetailDTO> serviceAmounts) {
        this.serviceAmounts = serviceAmounts;
    }

    public String getInvoiceSerialNum() {
        return invoiceSerialNum;
    }

    public void setInvoiceSerialNum(String invoiceSerialNum) {
        this.invoiceSerialNum = invoiceSerialNum;
    }

    @Override
    public String toString() {
        return "InvoiceCheckDetailDTO{" +
                "id=" + id +
                ", invoiceType=" + invoiceType +
                ", bankName='" + bankName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", instruction='" + instruction + '\'' +
                ", invoiceAmount=" + invoiceAmount +
                ", serviceAmounts=" + serviceAmounts +
                ", invoiceSerialNum='" + invoiceSerialNum + '\'' +
                ", invoiceDetailId=" + invoiceDetailId +
                '}';
    }
}
