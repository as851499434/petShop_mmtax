package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 后台交易展示DTO
 * @author yuanligang
 * @date 2019/7/18
 */
public class SysTrxOrderDTO {

    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String  merchantName;

    /**
     * 商户打款请求
     */
    @ApiModelProperty(value = "商户打款请求")
    private BigDecimal amount;

    /**
     * 订单流水号
     */
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;

    /**
     * 商户订单流水号
     */
    @ApiModelProperty(value = "商户订单流水号")
    private String merchantSerialNum;

    /**
     * 创建订单时间
     */
    @ApiModelProperty(value = "创建订单时间")
    private String createTime;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    /**
     * 记录ID
     */
    private Integer id;

    /**
     * 打款渠道
     */
    private String paymentChannel;

    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 银行卡号
     */
    private String payeeBankCard;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 证件号
     */
    private String payeeIdCardNo;
    /**
     * 手机号
     */
    private String payeeMobile;
    /**
     * 手续费
     */
    private BigDecimal charge;
    /**
     * 实发金额
     */
    private BigDecimal actualAmount;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
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

    public String getPayeeIdCardNo() {
        return payeeIdCardNo;
    }

    public void setPayeeIdCardNo(String payeeIdCardNo) {
        this.payeeIdCardNo = payeeIdCardNo;
    }

    public String getPayeeMobile() {
        return payeeMobile;
    }

    public void setPayeeMobile(String payeeMobile) {
        this.payeeMobile = payeeMobile;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    @Override
    public String toString() {
        return "SysTrxOrderDTO{" +
                "merchantName='" + merchantName + '\'' +
                ", amount=" + amount +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", merchantSerialNum='" + merchantSerialNum + '\'' +
                ", createTime='" + createTime + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", id=" + id +
                ", paymentChannel='" + paymentChannel + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", payeeBankCard='" + payeeBankCard + '\'' +
                ", bankName='" + bankName + '\'' +
                ", payeeIdCardNo='" + payeeIdCardNo + '\'' +
                ", payeeMobile='" + payeeMobile + '\'' +
                ", charge=" + charge +
                ", actualAmount=" + actualAmount +
                '}';
    }
}
