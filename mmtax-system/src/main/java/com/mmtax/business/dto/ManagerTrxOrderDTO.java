package com.mmtax.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/12 16:17
 */
@ApiModel(value = "web端代付列表")
public class ManagerTrxOrderDTO  extends BaseEntity {
    @ApiModelProperty(value = "记录id")
    private Integer id;

    @ApiModelProperty(value = "收款户名")
    private String payeeName;
    @ApiModelProperty(value = "商户打款请求金额")
    private String amount;
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;
    @ApiModelProperty(value = "商户订单流水号")
    private String merchantSerialNum;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "商户id")
    private String merchantId;
    @ApiModelProperty(value = "打款渠道")
    private String paymentChannel;
    @ApiModelProperty(value = "批次号")
    private String batchNo;
    @ApiModelProperty(value = "收款人银行卡号")
    private String payeeBankNo;
    @ApiModelProperty(value = "收款人银行卡所属银行名称")
    private String bankName;
    @ApiModelProperty(value = "收款人身份证号")
    private String payeeIdCardNo;
    @ApiModelProperty(value = "收款人手机号")
    private String payeeMobile;
    @ApiModelProperty(value = "手续费")
    private String charge;
    @ApiModelProperty(value = "实发金额")
    private String actualAmount;
    @ApiModelProperty(value = "0-未打款1-已打款2-打款挂起")
    private String orderStatus;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "备注")
    private String comment;
    @JsonIgnore
    private String startDate;
    @JsonIgnore
    private String endDate;
    @ApiModelProperty(value = "所属销售", required = true)
    private String saleName;
    @ApiModelProperty(hidden = true)
    private String saleId;
    private String taxSourceName;
    private String accountName;
    private String subject;
    private List<Integer> chargeMerIds;

    public List<Integer> getChargeMerIds() {
        return chargeMerIds;
    }

    public void setChargeMerIds(List<Integer> chargeMerIds) {
        this.chargeMerIds = chargeMerIds;
    }

    public String getTaxSourceName() {
        return taxSourceName;
    }

    public void setTaxSourceName(String taxSourceName) {
        this.taxSourceName = taxSourceName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getStartDate() {
        return startDate;
    }

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

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "ManagetTrxOrderDTO{" +
                "id=" + id +
                ", payeeName='" + payeeName + '\'' +
                ", amount='" + amount + '\'' +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", merchantSerialNum='" + merchantSerialNum + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", paymentChannel='" + paymentChannel + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", payeeBankNo='" + payeeBankNo + '\'' +
                ", bankName='" + bankName + '\'' +
                ", payeeIdCardNo='" + payeeIdCardNo + '\'' +
                ", payeeMobile='" + payeeMobile + '\'' +
                ", charge='" + charge + '\'' +
                ", actualAmount='" + actualAmount + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", comment='" + comment + '\'' +
                ", subject='" + subject + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
