package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class CustomerPromotionDTO {
    @Excel(name = "批次号")
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @Excel(name = "员工号")
    @ApiModelProperty(value = "员工号")
    private String customerNo;

    @Excel(name = "姓名")
    @ApiModelProperty(value = "姓名")
    private String payeeName;

    @Excel(name = "所属商户")
    @ApiModelProperty(value = "所属商户")
    private String merchantName;

    @Excel(name = "身份证号码")
    @ApiModelProperty(value = "身份证号码")
    private String payeeIdCardNo;

    @Excel(name = "银行卡号")
    @ApiModelProperty(value = "银行卡号")
    private String payeeBankCard;

    @Excel(name = "手机号码")
    @ApiModelProperty(value = "手机号码")
    private String payeeMobile;

    @Excel(name = "手机号码")
    @ApiModelProperty(value = "手机号码")
    private BigDecimal charge;

    @Excel(name = "金额")
    @ApiModelProperty(value = "金额")
    private String amount;

    @Excel(name = "数量")
    @ApiModelProperty(value = "数量")
    private String quantity;

    @Excel(name = "单价")
    @ApiModelProperty(value = "单价")
    private String unitPrice;

    @Excel(name = "省")
    @ApiModelProperty(value = "省")
    private String province;

    @Excel(name = "市")
    @ApiModelProperty(value = "市")
    private String city;

    @Excel(name = "区")
    @ApiModelProperty(value = "区")
    private String area;

    @Excel(name = "街道")
    @ApiModelProperty(value = "街道")
    private String street;

    @Excel(name = "社区")
    @ApiModelProperty(value = "社区")
    private String community;

    @Excel(name = "推广开始时间")
    @ApiModelProperty(value = "推广开始时间")
    private String promotionStartTime;

    @Excel(name = "推广结束时间")
    @ApiModelProperty(value = "推广结束时间")
    private String promotionEndTime;

    @Excel(name = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getPayeeIdCardNo() {
        return payeeIdCardNo;
    }

    public void setPayeeIdCardNo(String payeeIdCardNo) {
        this.payeeIdCardNo = payeeIdCardNo;
    }

    public String getPayeeBankCard() {
        return payeeBankCard;
    }

    public void setPayeeBankCard(String payeeBankCard) {
        this.payeeBankCard = payeeBankCard;
    }

    public String getPayeeMobile() {
        return payeeMobile;
    }

    public void setPayeeMobile(String payeeMobile) {
        this.payeeMobile = payeeMobile;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(String promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public String getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(String promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "CustomerPromotionDTO{" +
                "batchNo='" + batchNo + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", payeeName='" + payeeName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", payeeIdCardNo='" + payeeIdCardNo + '\'' +
                ", payeeBankCard='" + payeeBankCard + '\'' +
                ", payeeMobile='" + payeeMobile + '\'' +
                ", charge=" + charge +
                ", amount='" + amount + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", street='" + street + '\'' +
                ", community='" + community + '\'' +
                ", promotionStartTime='" + promotionStartTime + '\'' +
                ", promotionEndTime='" + promotionEndTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
