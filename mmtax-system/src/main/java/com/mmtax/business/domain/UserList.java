package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表 tbl_user_list
 *
 * @author meimiao
 * @date 2019-08-08
 */
@Table(name = "tbl_user_list")
public class UserList {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 身份证号码
     */
    private String idCardNo;
    /**
     * 银行卡号
     */
    private String bankCardNo;
    /**
     * 手机号码
     */
    private String mobileNo;
    /**
     * 国家
     */
    private String country;
    /**
     * 免验状态0-未加入1-已加入2-已拒绝
     */
    private Integer status;
    /**
     * 名单类型WHITE-白名单BLACK-黑名单
     */
    private String type;
    /**
     * 证件类型
     */
    private Integer certificateTypeId;
    /**
     * 证件正面
     */
    private String idCardFront;
    /**
     * 证件背面
     */
    private String idCardBack;
    /**
     * 备注
     */
    private String comment;
    /**
     * 商户id
     */
    private Integer merchantId;
    /**
     *
     */
    @Transient
    private Integer providerId;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setCertificateTypeId(Integer certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }

    public Integer getCertificateTypeId() {
        return certificateTypeId;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
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
                .append("id", getId())
                .append("name", getName())
                .append("idCardNo", getIdCardNo())
                .append("bankCardNo", getBankCardNo())
                .append("mobileNo", getMobileNo())
                .append("country", getCountry())
                .append("status", getStatus())
                .append("type", getType())
                .append("certificateTypeId", getCertificateTypeId())
                .append("idCardFront", getIdCardFront())
                .append("idCardBack", getIdCardBack())
                .append("comment", getComment())
                .append("merchantId", getMerchantId())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
