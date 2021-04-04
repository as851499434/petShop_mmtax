package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/8 14:07
 */
@ApiModel(description = "商户端要素认证列表")
public class MerchantFactorCertificationDTO {

    /**
     * 平台订单号
     */
    @Excel(name = "平台订单号")
    @ApiModelProperty(value = "平台订单号")
    private String orderSerialNum;
    /**
     * 商户订单号
     */
    @Excel(name = "商户订单号")
    @ApiModelProperty(value = "商户订单号")
    private String merchantSerialNum;
    /**
     * 0-三要素认证
     */
    @Excel(name = "认证类型", readConverterExp = "0=三要素认证")
    @ApiModelProperty(value = "认证类型，0-三要素认证")
    private Integer type;
    /**
     * 姓名
     */
    @Excel(name = "姓名")
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    @ApiModelProperty(value = "身份证号")
    private String idCardNo;
    /**
     * 银行卡号
     */
    @Excel(name = "银行卡号")
    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;
    /**
     * 0-同意1-拒绝
     */
    @Excel(name = "状态", readConverterExp = "0=同意,1=拒绝")
    @ApiModelProperty(value = "0-同意1-拒绝")
    private Integer status;
    /**
     * 商户平台
     */
    @Excel(name = "商户平台")
    @ApiModelProperty(value = "商户平台")
    private String merchantName;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @Excel(name = "修改时间")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getMerchantSerialNum() {
        return merchantSerialNum;
    }

    public void setMerchantSerialNum(String merchantSerialNum) {
        this.merchantSerialNum = merchantSerialNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MerchantFactorCertificationDTO{" +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", merchantSerialNum='" + merchantSerialNum + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", status=" + status +
                ", merchantName='" + merchantName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
