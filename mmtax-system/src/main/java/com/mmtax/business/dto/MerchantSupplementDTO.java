package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 个体用户注册 商户信息补全DTO
 *
 * @author yuanligang
 * @date 2019/7/30
 */
public class MerchantSupplementDTO {

    /**
     * 商户账户ID
     */
    @ApiModelProperty("商户账户ID")
    private Integer merchantId;

    /**
     * 商户名称
     */
    @ApiModelProperty("商户名称")
    private String merchantName;

    /**
     * 法人
     */
    @ApiModelProperty("法人姓名")
    private String contacts;

    /**
     * 法人手机号
     */
    @ApiModelProperty("法人手机号")
    private String contactsMobile;
    /**
     * 商户地址
     */
    @ApiModelProperty("商户地址")
    private String merchantAddress;

    /**
     * 开户银行名称
     */
    @ApiModelProperty("开户行")
    private String bankName;

    /**
     * 开户行账户
     */
    @ApiModelProperty("开户行账户")
    private String accountNo;

    /**
     * 身份证正面
     */
    @ApiModelProperty("身份证正面")
    private String contactsIdCardFront;

    /**
     * 身份证背面
     */
    @ApiModelProperty("身份证背面")
    private String contactsIdCardBack;


    @ApiModelProperty("经营场所产权证明")
    private String businessPlacesProof;

    @ApiModelProperty("个体工商户设立登记申请书")
    private String applyRegistBusiness;

    @ApiModelProperty("经营者签署委托代理书")
    private String managerOperatorLetter;

    @ApiModelProperty("个体工商户名称预先核准通知书")
    private String individualBusinessNotice;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
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

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    public String getBusinessPlacesProof() {
        return businessPlacesProof;
    }

    public void setBusinessPlacesProof(String businessPlacesProof) {
        this.businessPlacesProof = businessPlacesProof;
    }

    public String getApplyRegistBusiness() {
        return applyRegistBusiness;
    }

    public void setApplyRegistBusiness(String applyRegistBusiness) {
        this.applyRegistBusiness = applyRegistBusiness;
    }

    public String getManagerOperatorLetter() {
        return managerOperatorLetter;
    }

    public void setManagerOperatorLetter(String managerOperatorLetter) {
        this.managerOperatorLetter = managerOperatorLetter;
    }

    public String getIndividualBusinessNotice() {
        return individualBusinessNotice;
    }

    public void setIndividualBusinessNotice(String individualBusinessNotice) {
        this.individualBusinessNotice = individualBusinessNotice;
    }

    @Override
    public String
    toString() {
        return "MerchantSupplementDTO{" +
                "merchantId='" + merchantId + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", contacts='" + contacts + '\'' +
                ", contactsMobile='" + contactsMobile + '\'' +
                ", merchantAddress='" + merchantAddress + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", contactsIdCardFront='" + contactsIdCardFront + '\'' +
                ", contactsIdCardBack='" + contactsIdCardBack + '\'' +
                ", businessPlacesProof='" + businessPlacesProof + '\'' +
                ", applyRegistBusiness='" + applyRegistBusiness + '\'' +
                ", managerOperatorLetter='" + managerOperatorLetter + '\'' +
                ", individualBusinessNotice='" + individualBusinessNotice + '\'' +
                '}';
    }
}
