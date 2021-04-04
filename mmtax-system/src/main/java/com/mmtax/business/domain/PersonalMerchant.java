package com.mmtax.business.domain;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 个体工商户表 tbl_personal_merchant
 *
 * @author meimiao
 * @date 2020-11-26
 */
@Table(name = "tbl_personal_merchant")
public class PersonalMerchant {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 个人微信关联id
     */
    private Integer wechatInfoId;
    /**
     * 税务类型关联id
     */
    private Integer taxTypeId;
    /**
     * 申请编号
     */
    private String applyNumber;
    /**
     * 申请人姓名
     */
    private String applyName;
    /**
     * 身份证号
     */
    private String idCardNumber;
    /**
     * 银行卡号
     */
    private String bankNo;
    /**
     * 手机号
     */
    private String mobileNumber;
    /**
     * 税源地Id
     */
    private Integer taxSounrceCompanyId;
    /**
     * 税源地公司名称
     */
    private String taxSounrceCompanyName;
    /**
     * 身份证人像面存储地址
     */
    private String idCardPictureFront;
    /**
     * 身份证国徽面存储地址
     */
    private String idCardPictureBehind;
    /**
     * 手持身份证存储地址
     */
    private String idCardPictureFrontWithPeople;
    /**
     * 营业执照存储地址
     */
    private String businessLicense;
    /**
     * 营业执照名称
     */
    private String businessLicenseName;
    /**
     * 营业执照注册时间
     */
    private String businessLicenseRegisterTime;
    /**
     * 营业执照类型
     */
    private String businessLicenseType;
    /**
     * 组织形式
     */
    private String organizationType;
    /**
     * 经营场所
     */
    private String premises;
    /**
     * 0-未删除 1-已删除
     */
    private Integer delStatus;
    /**
     *
     */
    private Integer providerId;
    /** 预留字段一 */
    private String reservedFieldOne;
    /** 预留字段二 */
    private String reservedFieldTwo;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setWechatInfoId(Integer wechatInfoId) {
        this.wechatInfoId = wechatInfoId;
    }

    public Integer getWechatInfoId() {
        return wechatInfoId;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public void setTaxTypeId(Integer taxTypeId) {
        this.taxTypeId = taxTypeId;
    }

    public Integer getTaxTypeId() {
        return taxTypeId;
    }

    public void setApplyNumber(String applyNumber) {
        this.applyNumber = applyNumber;
    }

    public String getApplyNumber() {
        return applyNumber;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getBusinessLicenseRegisterTime() {
        return businessLicenseRegisterTime;
    }

    public void setBusinessLicenseRegisterTime(String businessLicenseRegisterTime) {
        this.businessLicenseRegisterTime = businessLicenseRegisterTime;
    }

    public void setTaxSounrceCompanyId(Integer taxSounrceCompanyId) {
        this.taxSounrceCompanyId = taxSounrceCompanyId;
    }

    public Integer getTaxSounrceCompanyId() {
        return taxSounrceCompanyId;
    }

    public void setTaxSounrceCompanyName(String taxSounrceCompanyName) {
        this.taxSounrceCompanyName = taxSounrceCompanyName;
    }

    public String getTaxSounrceCompanyName() {
        return taxSounrceCompanyName;
    }

    public void setIdCardPictureFront(String idCardPictureFront) {
        this.idCardPictureFront = idCardPictureFront;
    }

    public String getIdCardPictureFront() {
        return idCardPictureFront;
    }

    public void setIdCardPictureBehind(String idCardPictureBehind) {
        this.idCardPictureBehind = idCardPictureBehind;
    }

    public String getIdCardPictureBehind() {
        return idCardPictureBehind;
    }

    public void setIdCardPictureFrontWithPeople(String idCardPictureFrontWithPeople) {
        this.idCardPictureFrontWithPeople = idCardPictureFrontWithPeople;
    }

    public String getIdCardPictureFrontWithPeople() {
        return idCardPictureFrontWithPeople;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicenseName(String businessLicenseName) {
        this.businessLicenseName = businessLicenseName;
    }

    public String getBusinessLicenseName() {
        return businessLicenseName;
    }

    public void setPremises(String premises) {
        this.premises = premises;
    }

    public String getPremises() {
        return premises;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public String getBusinessLicenseType() {
        return businessLicenseType;
    }

    public void setBusinessLicenseType(String businessLicenseType) {
        this.businessLicenseType = businessLicenseType;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getReservedFieldOne() {
        return reservedFieldOne;
    }

    public void setReservedFieldOne(String reservedFieldOne) {
        this.reservedFieldOne = reservedFieldOne;
    }

    public String getReservedFieldTwo() {
        return reservedFieldTwo;
    }

    public void setReservedFieldTwo(String reservedFieldTwo) {
        this.reservedFieldTwo = reservedFieldTwo;
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
                .append("id", getId())
                .append("bankNo", getBankNo())
                .append("wechatInfoId", getWechatInfoId())
                .append("taxTypeId", getTaxTypeId())
                .append("applyNumber", getApplyNumber())
                .append("applyName", getApplyName())
                .append("idCardNumber", getIdCardNumber())
                .append("mobileNumber", getMobileNumber())
                .append("taxSounrceCompanyId", getTaxSounrceCompanyId())
                .append("taxSounrceCompanyName", getTaxSounrceCompanyName())
                .append("idCardPictureFront", getIdCardPictureFront())
                .append("idCardPictureBehind", getIdCardPictureBehind())
                .append("idCardPictureFrontWithPeople", getIdCardPictureFrontWithPeople())
                .append("businessLicense", getBusinessLicense())
                .append("businessLicenseName", getBusinessLicenseName())
                .append("businessLicenseRegisterTime",getBusinessLicenseRegisterTime())
                .append("premises", getPremises())
                .append("delStatus", getDelStatus())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
