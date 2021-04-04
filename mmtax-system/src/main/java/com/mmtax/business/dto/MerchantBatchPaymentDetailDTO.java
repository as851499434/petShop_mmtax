package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/15 10:44
 */
public class MerchantBatchPaymentDetailDTO {

    @ApiModelProperty(value = "记录id")
    private Integer id;
    @Excel(name = "收款户名")
    @ApiModelProperty(value = "收款户名")
    private String payeeName;
    @Excel(name = "商户打款请求金额")
    @ApiModelProperty(value = "商户打款请求金额")
    private String amount;
    @Excel(name = "订单流水号")
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;
    @Excel(name = "商户订单流水号")
    @ApiModelProperty(value = "商户订单流水号")
    private String merchantSerialNum;
    @Excel(name = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @Excel(name = "更新时间")
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "商户id")
    private String merchantId;
    @Excel(name = "打款渠道", readConverterExp = "BANK=银行,ALIPAY=支付宝,WECHAT=微信")
    @ApiModelProperty(value = "打款渠道")
    private String paymentChannel;
    @Excel(name = "批次号")
    @ApiModelProperty(value = "批次号")
    private String batchNo;
    @Excel(name = "收款人银行卡号")
    @ApiModelProperty(value = "收款人银行卡号")
    private String payeeBankNo;
    @Excel(name = "收款人身份证号")
    @ApiModelProperty(value = "收款人身份证号")
    private String payeeIdCardNo;
    @ApiModelProperty(value = "收款人手机号")
    private String payeeMobile;
    @Excel(name = "手续费")
    @ApiModelProperty(value = "手续费")
    private String charge;
    @ApiModelProperty(value = "大额手续费")
    private String chargeBig;
    @ApiModelProperty(value = "实发金额")
    private String actualAmount;
    @Excel(name = "打款状态", readConverterExp = "0=未打款,1=已打款,2=打款挂起,3=调单,9=失败")
    @ApiModelProperty(value = "0-未打款1-已打款2-打款挂起")
    private String orderStatus;
    @ApiModelProperty(value = "0-普通1-大额")
    private Integer useBigRate;
    @ApiModelProperty(value = "失败原因 ")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getUseBigRate() {
        return useBigRate;
    }

    public void setUseBigRate(Integer useBigRate) {
        this.useBigRate = useBigRate;
    }

    public String getChargeBig() {
        return chargeBig;
    }

    public void setChargeBig(String chargeBig) {
        this.chargeBig = chargeBig;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
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

    public String getPayeeBankNo() {
        return payeeBankNo;
    }

    public void setPayeeBankNo(String payeeBankNo) {
        this.payeeBankNo = payeeBankNo;
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

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
