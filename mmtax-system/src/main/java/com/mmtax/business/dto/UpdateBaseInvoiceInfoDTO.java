package com.mmtax.business.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

public class UpdateBaseInvoiceInfoDTO {
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    /**
     * 0-一般纳税人1小规模纳税人
     */
    @ApiModelProperty(value = "0-一般纳税人1小规模纳税人")
    private Integer taxpayerType;
    /**
     * 纳税人识别号
     */
    @ApiModelProperty(value = "纳税人识别号")
    private String taxpayerIdentificationNumber;
    /**
     * 单位注册地址
     */
    @ApiModelProperty(value = "单位注册地址")
    private String companyAddress;
    /**
     * 单位手机号
     */
    @ApiModelProperty(value = "单位手机号")
    private String invoiceMobile;
    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行")
    private String bankName;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String bankNumber;
    /**
     * 发票内容
     */
    @ApiModelProperty(value = "商户id")
    private String merchantId;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerType(Integer taxpayerType) {
        this.taxpayerType = taxpayerType;
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

    public String getInvoiceMobile() {
        return invoiceMobile;
    }

    public void setInvoiceMobile(String invoiceMobile) {
        this.invoiceMobile = invoiceMobile;
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

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "UpdateBaseInvoiceInfoDTO{" +
                "companyName='" + companyName + '\'' +
                ", taxpayerType=" + taxpayerType +
                ", taxpayerIdentificationNumber='" + taxpayerIdentificationNumber + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", invoiceMobile='" + invoiceMobile + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                ", merchantId='" + merchantId + '\'' +
                '}';
    }
}
