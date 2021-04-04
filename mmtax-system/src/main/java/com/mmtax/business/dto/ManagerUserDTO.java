package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/7 14:00
 */
public class ManagerUserDTO extends BaseEntity {
    @ApiModelProperty(value = "名单id")
    private Integer id;

    /**
     * 名字
     */
    @ApiModelProperty(value = "名字")
    private String name;
    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "身份证号码")
    private String idCardNo;
    /**
     * 银行卡号
     */
    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobileNo;
    /**
     * 国家
     */
    @ApiModelProperty(value = "国家")
    private String country;
    /**
     * 免验状态0-未加入1-已加入2-已拒绝
     */
    @ApiModelProperty(value = "免验状态0-未加入1-已加入2-已拒绝")
    private Integer status;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 证件号类型
     */
    @ApiModelProperty(value = "证件号类型")
    private String certificateTypeId;
    @ApiModelProperty(value = "证件正面照片")
    private String idCardFront;
    @ApiModelProperty(value = "证件反面照片")
    private String idCardBack;
    @ApiModelProperty(value = "备注")
    private String comment;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "审核时间")
    private String updateTime;
    @ApiModelProperty(value = "所属销售" , required = true)
    private String saleName;
    @ApiModelProperty(hidden = true)
    private String saleId;

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getCertificateTypeId() {
        return certificateTypeId;
    }

    public void setCertificateTypeId(String certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
