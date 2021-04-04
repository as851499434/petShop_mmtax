package com.mmtax.business.yunzbdto;

import com.mmtax.common.utils.yunzbutil.BaseRequest;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/8 15:17
 */
public class YunZBNewMerchantInfoDTO extends BaseRequest {
    /**
     * 商户注册地址
     */
    private String address;

    /**
     * 基本户银行账户
     */
    private String basicBankCardNo;

    /**
     * 基本户银行代码
     */
    private String basicBankCode;

    /**
     * 基本户银行行号
     */
    private String basicBankNo;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

    /**
     * 联系人电话
     */
    private String contactMobile;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 法人身份证有效期
     */
    private String corpIdCardExp;

    /**
     * 法人身份证号
     */
    private String corpIdCardNo;

    /**
     * 法人手机号
     */
    private String corpMobile;

    /**
     * 法人姓名
     */
    private String corpName;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 营业执照号有效期
     */
    private String licenceExp;

    /**
     * 营业执照号
     */
    private String licenceNumber;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户简称
     */
    private String merchantShortName;

    /**
     * 结算银行代码
     */
    private String settBankCode;

    /**
     * 结算银行名称
     */
    private String settBankName;

    /**
     * 结算银行卡号
     */
    private String settBankcardAccount;

    /**
     * 结算银行卡账户名称
     */
    private String settBankcardName;

    /**
     * 结算银行卡开户行行号
     */
    private String settBankcardNo;

    /**
     * 二级子商户号
     */
    private String subMchId;

    /**
     * 纳税人类型
     */
    private String taxpayerType;

    /**
     * 单位注册地址及电话（开票信息）
     */
    private String addressAndTel;

    /**
     * 开户行名称及账户（开票信息）
     */
    private String settBankNameAndAccount;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBasicBankCardNo() {
        return basicBankCardNo;
    }

    public void setBasicBankCardNo(String basicBankCardNo) {
        this.basicBankCardNo = basicBankCardNo;
    }

    public String getBasicBankCode() {
        return basicBankCode;
    }

    public void setBasicBankCode(String basicBankCode) {
        this.basicBankCode = basicBankCode;
    }

    public String getBasicBankNo() {
        return basicBankNo;
    }

    public void setBasicBankNo(String basicBankNo) {
        this.basicBankNo = basicBankNo;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCorpIdCardExp() {
        return corpIdCardExp;
    }

    public void setCorpIdCardExp(String corpIdCardExp) {
        this.corpIdCardExp = corpIdCardExp;
    }

    public String getCorpIdCardNo() {
        return corpIdCardNo;
    }

    public void setCorpIdCardNo(String corpIdCardNo) {
        this.corpIdCardNo = corpIdCardNo;
    }

    public String getCorpMobile() {
        return corpMobile;
    }

    public void setCorpMobile(String corpMobile) {
        this.corpMobile = corpMobile;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLicenceExp() {
        return licenceExp;
    }

    public void setLicenceExp(String licenceExp) {
        this.licenceExp = licenceExp;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantShortName() {
        return merchantShortName;
    }

    public void setMerchantShortName(String merchantShortName) {
        this.merchantShortName = merchantShortName;
    }

    public String getSettBankCode() {
        return settBankCode;
    }

    public void setSettBankCode(String settBankCode) {
        this.settBankCode = settBankCode;
    }

    public String getSettBankName() {
        return settBankName;
    }

    public void setSettBankName(String settBankName) {
        this.settBankName = settBankName;
    }

    public String getSettBankcardAccount() {
        return settBankcardAccount;
    }

    public void setSettBankcardAccount(String settBankcardAccount) {
        this.settBankcardAccount = settBankcardAccount;
    }

    public String getSettBankcardName() {
        return settBankcardName;
    }

    public void setSettBankcardName(String settBankcardName) {
        this.settBankcardName = settBankcardName;
    }

    public String getSettBankcardNo() {
        return settBankcardNo;
    }

    public void setSettBankcardNo(String settBankcardNo) {
        this.settBankcardNo = settBankcardNo;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerType(String taxpayerType) {
        this.taxpayerType = taxpayerType;
    }

    public String getAddressAndTel() {
        return addressAndTel;
    }

    public void setAddressAndTel(String addressAndTel) {
        this.addressAndTel = addressAndTel;
    }

    public String getSettBankNameAndAccount() {
        return settBankNameAndAccount;
    }

    public void setSettBankNameAndAccount(String settBankNameAndAccount) {
        this.settBankNameAndAccount = settBankNameAndAccount;
    }
}
