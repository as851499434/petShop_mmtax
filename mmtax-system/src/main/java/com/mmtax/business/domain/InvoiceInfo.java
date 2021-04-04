package com.mmtax.business.domain;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 发票表 tbl_invoice_info
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Table(name = "tbl_invoice_info")
public class InvoiceInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
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
    private String invoiceContent;
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
    @Transient
    @ApiModelProperty(hidden = true)
    private Integer providerId;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setTaxpayerType(Integer taxpayerType) {
        this.taxpayerType = taxpayerType;
    }

    public Integer getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) {
        this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
    }

    public String getTaxpayerIdentificationNumber() {
        return taxpayerIdentificationNumber;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setInvoiceMobile(String invoiceMobile) {
        this.invoiceMobile = invoiceMobile;
    }

    public String getInvoiceMobile() {
        return invoiceMobile;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceDefaultContent(String invoiceDefaultContent) {
        this.invoiceDefaultContent = invoiceDefaultContent;
    }

    public String getInvoiceDefaultContent() {
        return invoiceDefaultContent;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("companyName" , getCompanyName())
                .append("taxpayerType" , getTaxpayerType())
                .append("taxpayerIdentificationNumber" , getTaxpayerIdentificationNumber())
                .append("companyAddress" , getCompanyAddress())
                .append("invoiceMobile" , getInvoiceMobile())
                .append("bankName",getBankName())
                .append("bankNumber" , getBankNumber())
                .append("invoiceContent" , getInvoiceContent())
                .append("invoiceDefaultContent" , getInvoiceDefaultContent())
                .append("merchantId" , getMerchantId())
                .append("providerId" , getProviderId())
                .append("createTime" , getCreateTime())
                .append("updateTime" , getUpdateTime())
                .toString();
    }
}
