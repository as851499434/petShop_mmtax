package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/7 10:04
 */
@ApiModel(description = "商户端添加免验名单")
public class MerchantAddUserDTO {

    /**
     * 名字
     */
    @ApiModelProperty(value = "名字")
    private String name;
    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "证件号码")
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
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Integer merchantId;

    @ApiModelProperty(value = "证件类型")
    private Integer certificateTypeId;
    @ApiModelProperty(value = "证件正面照片")
    private String idCardFront;
    @ApiModelProperty(value = "证件反面照片")
    private String idCardBack;
    @ApiModelProperty(value = "备注")
    private String comment;

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

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getCertificateTypeId() {
        return certificateTypeId;
    }

    public void setCertificateTypeId(Integer certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }

    @Override
    public String toString() {
        return "MerchantAddUserDTO{" +
                "name='" + name + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", country='" + country + '\'' +
                ", merchantId=" + merchantId +
                ", certificateTypeId=" + certificateTypeId +
                ", idCardFront='" + idCardFront + '\'' +
                ", idCardBack='" + idCardBack + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
