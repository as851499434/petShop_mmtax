package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.security.PrivateKey;

public class CheckingInvoiceDTO {

    /**
     * 发票抬头
     */
    @ApiModelProperty(value = "发票抬头", required = true)
    private String companyName;

    /**
     * 发票类型
     */
    @ApiModelProperty(value = "发票类型",required = true)
    private String invoiceType;

    /**
     * 开户银行名称
     */
    @ApiModelProperty(value = "开户银行名称",required = true)
    private String bankName;

    /**
     * 开户账号
     */
    @ApiModelProperty(value = "开户账号",required = true)
    private String bankNumber;

    /**
     * 纳税人识别号
     */
    @ApiModelProperty(value = "纳税人识别号", required = true)
    private String taxpayerIdentificationNumber;
    /**
     * 单位注册地址
     */
    @ApiModelProperty(value = "单位注册地址", required = true)
    private String companyAddress;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", required = true)
    private String mobile;

    /**
     *货物或应税劳务、服务名称
     */
    @ApiModelProperty(value = "货物或应税劳务、服务名称", required = true)
    private String serviceName;

    /**
     *发票备注
     */
    @ApiModelProperty(value = "发票备注", required = true)
    private String invoiceNote;

    /**
     *收件人
     */
    @ApiModelProperty(value = "收件人", required = true)
    private String addresseeName;


    /**
     * 单位手机号
     */
    @ApiModelProperty(value = "单位手机号")
    private String invoiceMobile;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "收件地址")
    private String address;

    /**
     * 联系人电话
     */
    @ApiModelProperty(value = "联系人电话")
    private String addressMobile;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getTaxpayerIdentificationNumber() {
        return taxpayerIdentificationNumber;
    }

    public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) {
        this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getInvoiceNote() {
        return invoiceNote;
    }

    public void setInvoiceNote(String invoiceNote) {
        this.invoiceNote = invoiceNote;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public String getInvoiceMobile() {
        return invoiceMobile;
    }

    public void setInvoiceMobile(String invoiceMobile) {
        this.invoiceMobile = invoiceMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressMobile() {
        return addressMobile;
    }

    public void setAddressMobile(String addressMobile) {
        this.addressMobile = addressMobile;
    }

    @Override
    public String toString() {
        return "CheckingInvoiceDTO{" +
                "companyName='" + companyName + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                ", taxpayerIdentificationNumber='" + taxpayerIdentificationNumber + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", mobile='" + mobile + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", invoiceNote='" + invoiceNote + '\'' +
                ", addresseeName='" + addresseeName + '\'' +
                ", invoiceMobile='" + invoiceMobile + '\'' +
                ", address='" + address + '\'' +
                ", addressMobile='" + addressMobile + '\'' +
                '}';
    }
}
