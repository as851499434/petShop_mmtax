package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 点击审核按钮中的信息
 */
public class InvoiceCheckDTO {
    /**
     * 发票id
     */
    @ApiModelProperty(value = "发票id")
    private String  id;
    /**
     * 发票申请编号
     */
    @ApiModelProperty(value = "发票申请编号")
    private String  invoiceSerialNum;
    /**
     * 发票申请编号
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 代征主体
     */
    @ApiModelProperty(value = "代征主体")
    private String behalfMainBody;
    /**
     * 发票金额
     */
    @ApiModelProperty(value = "发票金额")
    private String invoiceAmount;
    /**
     * 发票抬头
     */
    @ApiModelProperty(value = "发票抬头")
    private String invoiceTitle;
    /**
     * 发票类型
     */
    @ApiModelProperty(value = "发票类型")
    private String invoiceType;
    /**
     * 纳税人识别号
     */
    @ApiModelProperty(value = "纳税人识别号")
    private String taxpayerIdentificationNumber;

    /**
     * 开户银行名称
     */
    @ApiModelProperty(value = "开户银行名称")
    private String bankName;
    /**
     * 开户账号
     */
    @ApiModelProperty(value = "开户账号")
    private String bankAccountNo;
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
     * 发票内容
     */
    @ApiModelProperty(value = "科目内容")
    private String invoiceContent;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 备注
     */
    @ApiModelProperty(value = "退票备注")
    private String returnRemark;
    /**
     * 收件人姓名
     */
    @ApiModelProperty("收件人姓名")
    private String addresseeName;
    /**
     * 收件人电话
     */
    @ApiModelProperty("收件人电话")
    private String addressMobile;
    /**
     * 收件人地址
     */
    @ApiModelProperty("收件人地址")
    private String address;
    /**
     * 退票原因
     */
    @ApiModelProperty("退票原因")
    private String returnReason;
    /**
     * 快递公司名称（税源地寄出）
     */
    @ApiModelProperty("快递公司名称（税源地寄出）")
    private String expressCompanyName;
    /**
     * 快递单号（税源地寄出）
     */
    @ApiModelProperty("快递单号（税源地寄出）")
    private String expressNo;
    /**
     * 快递公司名称（商户退票寄出）
     */
    @ApiModelProperty("快递公司名称（商户退票寄出）")
    private String expressCompanyNameReturn;
    /**
     * 快递单号（商户退票寄出）
     */
    @ApiModelProperty("快递单号（商户退票寄出）")
    private String expressNoReturn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReturnRemark() {
        return returnRemark;
    }

    public void setReturnRemark(String returnRemark) {
        this.returnRemark = returnRemark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExpressCompanyNameReturn() {
        return expressCompanyNameReturn;
    }

    public void setExpressCompanyNameReturn(String expressCompanyNameReturn) {
        this.expressCompanyNameReturn = expressCompanyNameReturn;
    }

    public String getExpressNoReturn() {
        return expressNoReturn;
    }

    public void setExpressNoReturn(String expressNoReturn) {
        this.expressNoReturn = expressNoReturn;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getBehalfMainBody() {
        return behalfMainBody;
    }

    public void setBehalfMainBody(String behalfMainBody) {
        this.behalfMainBody = behalfMainBody;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTaxpayerIdentificationNumber() {
        return taxpayerIdentificationNumber;
    }

    public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) {
        this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
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

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public String getAddressMobile() {
        return addressMobile;
    }

    public void setAddressMobile(String addressMobile) {
        this.addressMobile = addressMobile;
    }

    public String getInvoiceSerialNum() {
        return invoiceSerialNum;
    }

    public void setInvoiceSerialNum(String invoiceSerialNum) {
        this.invoiceSerialNum = invoiceSerialNum;
    }

    @Override
    public String toString() {
        return "InvoiceCheckDTO{" +
                "id='" + id + '\'' +
                ", invoiceSerialNum='" + invoiceSerialNum + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", behalfMainBody='" + behalfMainBody + '\'' +
                ", invoiceAmount='" + invoiceAmount + '\'' +
                ", invoiceTitle='" + invoiceTitle + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", taxpayerIdentificationNumber='" + taxpayerIdentificationNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNo='" + bankAccountNo + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", invoiceMobile='" + invoiceMobile + '\'' +
                ", invoiceContent='" + invoiceContent + '\'' +
                ", remark='" + remark + '\'' +
                ", returnRemark='" + returnRemark + '\'' +
                ", addresseeName='" + addresseeName + '\'' +
                ", addressMobile='" + addressMobile + '\'' +
                ", address='" + address + '\'' +
                ", returnReason='" + returnReason + '\'' +
                ", expressCompanyName='" + expressCompanyName + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", expressCompanyNameReturn='" + expressCompanyNameReturn + '\'' +
                ", expressNoReturn='" + expressNoReturn + '\'' +
                '}';
    }
}
