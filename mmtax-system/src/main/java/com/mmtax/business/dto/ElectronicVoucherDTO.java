package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 单笔电子凭证DTO
 * @author yuanligang
 * @date 2019/7/18
 */
public class ElectronicVoucherDTO {
    /**
     * 电子回单号码
     */
    @ApiModelProperty(value = "电子回单号码")
    private String electronicCode;
    /**
     * 订单生成时间
     */
    @ApiModelProperty(value = "订单生成时间")
    private String createTime;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    /**
     * 商户编码(付款人账号)
     */
    @ApiModelProperty(value = "商户编码(付款人账号)")
    private String merchantCode;
    /**
     *收款人账号
     */
    @ApiModelProperty(value = "收款人账号")
    private String payeeBankCard;
    /**
     *收款人开户银行
     */
    @ApiModelProperty(value = "收款人开户银行")
    private String bankName;

    /**
     * 付款单号 交易流水号
     */
    @ApiModelProperty(value = "=交易流水号=交易文件号")
    private String orderSerialNum;
    /**
     * 交易金额
     */
    @ApiModelProperty(value = "交易金额")
    private String amount;
    /**
     * 金额大写
     */
    @ApiModelProperty(value = "金额大写")
    private String upperAmount;
    /**
     * 交易流水
     */
    @ApiModelProperty(value = "付款单号=收款单号")
    private String merchantSerialNum;

    /**
     * 收款人姓名
     */
    @ApiModelProperty("收款人账户名称")
    private String payeeName;

    /**
     * 交易状态
     */
    @ApiModelProperty("交易状态0-未打款1-已打款2-打款挂起3-调单状态")
    private String orderStatus;

    /**
     * 时间戳
     */
    @ApiModelProperty("时间戳")
    private String  timestamp;

    public String getElectronicCode() {
        return electronicCode;
    }

    public void setElectronicCode(String electronicCode) {
        this.electronicCode = electronicCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getPayeeBankCard() {
        return payeeBankCard;
    }

    public void setPayeeBankCard(String payeeBankCard) {
        this.payeeBankCard = payeeBankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUpperAmount() {
        return upperAmount;
    }

    public void setUpperAmount(String upperAmount) {
        this.upperAmount = upperAmount;
    }

    public String getMerchantSerialNum() {
        return merchantSerialNum;
    }

    public void setMerchantSerialNum(String merchantSerialNum) {
        this.merchantSerialNum = merchantSerialNum;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    @Override
    public String toString() {
        return "ElectronicVoucherDTO{" +
                "electronicCode='" + electronicCode + '\'' +
                ", createTime='" + createTime + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", payeeBankCard='" + payeeBankCard + '\'' +
                ", bankName='" + bankName + '\'' +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", amount='" + amount + '\'' +
                ", upperAmount='" + upperAmount + '\'' +
                ", merchantSerialNum='" + merchantSerialNum + '\'' +
                ", payeeName='" + payeeName + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
