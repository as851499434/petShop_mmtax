package com.mmtax.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mmtax.business.domain.CustomerSupport;
import com.mmtax.business.domain.SettlementInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 商户端商户信息
 * @author yuanligang
 * @date 2019/7/29
 */
public class MerchantInfoDTO {
    /**
     * 商户编码
     */
    @ApiModelProperty(hidden = true)
    private String merchantCode;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称", required = true)
    private String merchantName;

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
     * 联系人
     */
    @ApiModelProperty(value = "联系人姓名", required = true)
    private String contacts;
    /**
     * 联系人手机号
     */
    @ApiModelProperty(value = "联系人手机号", required = true)
    private String contactsMobile;

    @ApiModelProperty("联系地址")
    private String contactsAddress;
    /**
     * 邮件
     */
    @ApiModelProperty(value = "邮件", required = true)
    private String email;
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
    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号", required = true)
    private String contractNo;
    /**
     * 合同图片链接
     */
    @JsonIgnore
    private String contractImgUrl;
    /**
     * 合同图片链接
     */
    @ApiModelProperty(value = "合同图片链接", required = true)
    private List<String> contractImgUrlList;

    /**
     * 合同名称
     */
    @ApiModelProperty(value = "合同名称", required = true)
    private String contractName;

    @ApiModelProperty(value = "报税邮箱", hidden = true)
    private String invoiceEmail;

    @ApiModelProperty("报税邮箱集")
    private List<String> emails;

    @ApiModelProperty("结算信息")
    private SettlementInfo settlementInfo;

    @ApiModelProperty("客户支持")
    private CustomerSupport customerSupport;

    @ApiModelProperty(value = "所属销售")
    private Integer belongSaler;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public String getContactsAddress() {
        return contactsAddress;
    }

    public void setContactsAddress(String contactsAddress) {
        this.contactsAddress = contactsAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getInvoiceEmail() {
        return invoiceEmail;
    }

    public void setInvoiceEmail(String invoiceEmail) {
        this.invoiceEmail = invoiceEmail;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public SettlementInfo getSettlementInfo() {
        return settlementInfo;
    }

    public void setSettlementInfo(SettlementInfo settlementInfo) {
        this.settlementInfo = settlementInfo;
    }

    public CustomerSupport getCustomerSupport() {
        return customerSupport;
    }

    public void setCustomerSupport(CustomerSupport customerSupport) {
        this.customerSupport = customerSupport;
    }

    public String getContractImgUrl() {
        return contractImgUrl;
    }

    public void setContractImgUrl(String contractImgUrl) {
        this.contractImgUrl = contractImgUrl;
    }

    public List<String> getContractImgUrlList() {
        return contractImgUrlList;
    }

    public void setContractImgUrlList(List<String> contractImgUrlList) {
        this.contractImgUrlList = contractImgUrlList;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
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

    public Integer getBelongSaler() {
        return belongSaler;
    }

    public void setBelongSaler(Integer belongSaler) {
        this.belongSaler = belongSaler;
    }

    @Override
    public String toString() {
        return "MerchantInfoDTO{" +
                "merchantCode='" + merchantCode + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", merchantAddress='" + merchantAddress + '\'' +
                ", contactsIdCardFront='" + contactsIdCardFront + '\'' +
                ", contactsIdCardBack='" + contactsIdCardBack + '\'' +
                ", legalPersonMobile='" + legalPersonMobile + '\'' +
                ", legalPersonIdCardNo='" + legalPersonIdCardNo + '\'' +
                ", legalPersonIdCardExp='" + legalPersonIdCardExp + '\'' +
                ", industry='" + industry + '\'' +
                ", businessLicenseExp='" + businessLicenseExp + '\'' +
                ", contacts='" + contacts + '\'' +
                ", contactsMobile='" + contactsMobile + '\'' +
                ", contactsAddress='" + contactsAddress + '\'' +
                ", email='" + email + '\'' +
                ", businessLicenseCode='" + businessLicenseCode + '\'' +
                ", businessLicenseImg='" + businessLicenseImg + '\'' +
                ", taxpayerType=" + taxpayerType +
                ", companyName='" + companyName + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", contractImgUrl='" + contractImgUrl + '\'' +
                ", contractImgUrlList=" + contractImgUrlList +
                ", contractName='" + contractName + '\'' +
                ", invoiceEmail='" + invoiceEmail + '\'' +
                ", emails=" + emails +
                ", settlementInfo=" + settlementInfo +
                ", customerSupport=" + customerSupport +
                '}';
    }
}
