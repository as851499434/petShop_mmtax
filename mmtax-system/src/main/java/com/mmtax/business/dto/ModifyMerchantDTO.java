package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商户基本信息修改DTO
 *
 * @author yuanligang
 * @date 2019/7/12
 */
public class ModifyMerchantDTO {

    /**
     * 主键
     */
    @ApiModelProperty(value = "商户表记录ID", required = true)
    private Integer id;
    /**
     * 主键
     */
    @ApiModelProperty(value = "商户名称", required = true)
    private String merchantName;
    /**
     * 营业执照编码
     */
    @ApiModelProperty(value = "营业执照编码", required = true)
    private String businessLicenseCode;
    /**
     * 营业执照图片
     */
    @ApiModelProperty(value = "营业执照图片", required = true)
    private String businessLicenseImg;
    /**
     * 0-一般纳税人1-小规模纳税人
     */
    @ApiModelProperty(value = "0-一般纳税人1-小规模纳税人", required = true)
    private Integer taxpayerType;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称", required = true)
    private String companyName;


    @ApiModelProperty(value = "联系人", required = true)
    private String contacts;

    @ApiModelProperty(value = "联系人邮件", required = true)
    private String email;

    @ApiModelProperty(value = "报税邮箱", hidden = true)
    private String invoiceEmail;

    @ApiModelProperty("联系地址")
    private String contactsAddress;
    @ApiModelProperty(value = "商户经营地址", required = true)
    private String merchantAddress;
    /**
     * 法人身份证正面
     */
    @ApiModelProperty(value = "法人身份证正面")
    private String contactsIdCardFront;
    /**
     * 法人身份证背面
     */
    @ApiModelProperty(value = "法人身份证背面")
    private String contactsIdCardBack;
    /**
     * 法人手机号
     */
    @ApiModelProperty(value = "法人手机号")
    private String legalPersonMobile;
    /**
     * 法人身份证号
     */
    @ApiModelProperty(value = "法人身份证号")
    private String legalPersonIdCardNo;
    /**
     * 法人身份证号有效期
     */
    @ApiModelProperty(value = "法人身份证号有效期")
    private String legalPersonIdCardExp;
    /**
     * 所属行业
     */
    @ApiModelProperty(value = "所属行业")
    private String industry;
    /**
     * 营业执照有效期
     */
    @ApiModelProperty(value = "营业执照有效期, 格式YYYYMMDD 长期：99991231")
    private String businessLicenseExp;
    /**
     * 合同图片链接
     */
    @ApiModelProperty("合同图片链接")
    private String contractImgUrl;
    @ApiModelProperty("代征主体")
    private String subject;
    @ApiModelProperty(value = "所属销售")
    private Integer belongSaler;

    @ApiModelProperty(value = "登录账号")
    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getBusinessLicenseCode() {
        return businessLicenseCode;
    }

    public void setBusinessLicenseCode(String businessLicenseCode) {
        this.businessLicenseCode = businessLicenseCode;
    }

    public String getBusinessLicenseImg() {
        return businessLicenseImg;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        this.businessLicenseImg = businessLicenseImg;
    }

    public Integer getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerType(Integer taxpayerType) {
        this.taxpayerType = taxpayerType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInvoiceEmail() {
        return invoiceEmail;
    }

    public void setInvoiceEmail(String invoiceEmail) {
        this.invoiceEmail = invoiceEmail;
    }

    public String getContactsAddress() {
        return contactsAddress;
    }

    public void setContactsAddress(String contactsAddress) {
        this.contactsAddress = contactsAddress;
    }

    public String getContractImgUrl() {
        return contractImgUrl;
    }

    public void setContractImgUrl(String contractImgUrl) {
        this.contractImgUrl = contractImgUrl;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getContactsIdCardFront() {
        return contactsIdCardFront;
    }

    public void setContactsIdCardFront(String contactsIdCardFront) {
        this.contactsIdCardFront = contactsIdCardFront;
    }

    public String getContactsIdCardBack() {
        return contactsIdCardBack;
    }

    public void setContactsIdCardBack(String contactsIdCardBack) {
        this.contactsIdCardBack = contactsIdCardBack;
    }

    public String getLegalPersonMobile() {
        return legalPersonMobile;
    }

    public void setLegalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile;
    }

    public String getLegalPersonIdCardNo() {
        return legalPersonIdCardNo;
    }

    public void setLegalPersonIdCardNo(String legalPersonIdCardNo) {
        this.legalPersonIdCardNo = legalPersonIdCardNo;
    }

    public String getLegalPersonIdCardExp() {
        return legalPersonIdCardExp;
    }

    public void setLegalPersonIdCardExp(String legalPersonIdCardExp) {
        this.legalPersonIdCardExp = legalPersonIdCardExp;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusinessLicenseExp() {
        return businessLicenseExp;
    }

    public void setBusinessLicenseExp(String businessLicenseExp) {
        this.businessLicenseExp = businessLicenseExp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getBelongSaler() {
        return belongSaler;
    }

    public void setBelongSaler(Integer belongSaler) {
        this.belongSaler = belongSaler;
    }

    @Override
    public String toString() {
        return "ModifyMerchantDTO{" +
                "id=" + id +
                ", businessLicenseCode='" + businessLicenseCode + '\'' +
                ", businessLicenseImg='" + businessLicenseImg + '\'' +
                ", taxpayerType=" + taxpayerType +
                ", companyName='" + companyName + '\'' +
                ", contacts='" + contacts + '\'' +
                ", email='" + email + '\'' +
                ", invoiceEmail='" + invoiceEmail + '\'' +
                ", contactsAddress='" + contactsAddress + '\'' +
                ", merchantAddress='" + merchantAddress + '\'' +
                ", contactsIdCardFront='" + contactsIdCardFront + '\'' +
                ", contactsIdCardBack='" + contactsIdCardBack + '\'' +
                ", legalPersonMobile='" + legalPersonMobile + '\'' +
                ", legalPersonIdCardNo='" + legalPersonIdCardNo + '\'' +
                ", legalPersonIdCardExp='" + legalPersonIdCardExp + '\'' +
                ", industry='" + industry + '\'' +
                ", businessLicenseExp='" + businessLicenseExp + '\'' +
                ", contractImgUrl='" + contractImgUrl + '\'' +
                ", subject='" + subject + '\'' +
                ", loginName='" + loginName + '\'' +
                '}';
    }
}
