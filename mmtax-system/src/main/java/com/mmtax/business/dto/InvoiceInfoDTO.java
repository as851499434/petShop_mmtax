package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 用户基本开票信息展示
 * @author yuanligang
 * @date 2019/7/17
 */
public class InvoiceInfoDTO {
    /**
     * 主键
     */
    @ApiModelProperty("发票信息ID")
    private Integer id;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称", required = true)
    private String companyName;
    /**
     * 0-一般纳税人1小规模纳税人
     */
    @ApiModelProperty(value = "0-一般纳税人1小规模纳税人", required = true)
    private Integer taxpayerType;
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
     * 单位手机号
     */
    @ApiModelProperty(value = "单位手机号", required = true)
    private String invoiceMobile;
    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行",required = true)
    private String bankName;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", required = true)
    private String bankNumber;
    /**
     * 发票内容
     */
    @ApiModelProperty(value = "发票内容", required = true)
    private List<String> invoiceContentList;
    /**
     * 默认发票内容
     */
    @ApiModelProperty(value = "默认发票内容", required = true)
    private String invoiceDefaultContent;
    /**
     * 商户id
     */
    @ApiModelProperty(hidden = true)
    private Integer merchantId;


    /**
     *
     */
    @ApiModelProperty(hidden = true)
    private Date createTime;
    /**
     *
     */
    @ApiModelProperty(hidden = true)
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<String> getInvoiceContentList() {
        return invoiceContentList;
    }

    public void setInvoiceContentList(List<String> invoiceContentList) {
        this.invoiceContentList = invoiceContentList;
    }

    public String getInvoiceDefaultContent() {
        return invoiceDefaultContent;
    }

    public void setInvoiceDefaultContent(String invoiceDefaultContent) {
        this.invoiceDefaultContent = invoiceDefaultContent;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "InvoiceInfoDTO{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", taxpayerType=" + taxpayerType +
                ", taxpayerIdentificationNumber='" + taxpayerIdentificationNumber + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", invoiceMobile='" + invoiceMobile + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                ", invoiceContentList=" + invoiceContentList +
                ", invoiceDefaultContent='" + invoiceDefaultContent + '\'' +
                ", merchantId=" + merchantId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
