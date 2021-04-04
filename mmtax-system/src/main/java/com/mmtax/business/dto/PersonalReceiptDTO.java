package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 个人收款信息DTO
 * @author zouyuanpeng
 * @date 2020/9/24 11:13
 */
@ApiModel("个人收款DTO")
public class PersonalReceiptDTO {
    /**
     * 员工id
     */
    @ApiModelProperty("员工id")
    private Integer id;
    /**
     * 商户id
     */
    @ApiModelProperty("商户id")
    private Integer merchantId;
    /**
     * 批量打款批次号
     */
    @ApiModelProperty("批量打款批次号")
    private String batchNo;
    /**
     * 交易流水号
     */
    @ApiModelProperty("流水号")
    @Excel(name = "交易流水号")
    private String orderSerialNum;
    /**
     * 收款人姓名
     */
    @ApiModelProperty("姓名")
    @Excel(name = "姓名")
    private String payeeName;
    /**
     * 任务名称
     */
    @ApiModelProperty("任务名称")
    @Excel(name = "任务名称")
    private String taskName;
    /**
     * 收款人手机号
     */
    @ApiModelProperty("手机号")
    @Excel(name = "手机号")
    private String payeeMobile;
    /**
     * 收款人身份证号
     */
    @ApiModelProperty("身份证号")
    @Excel(name = "身份证号")
    private String payIdCardNo;
    /**
     * 商户名称
     */
    @ApiModelProperty("商户名称")
    @Excel(name = "商户名称")
    private String merchantName;
    /**
     * 收款账号类型
     */
    @ApiModelProperty("收款账号类型")
    @Excel(name = "收款账号类型",readConverterExp = "BANK=银行卡,ALIPAY=支付宝,WECHAT=微信,CHANPAY=畅捷支付")
    private String paymentChannel;
    /**
     * 收款账号
     */
    @ApiModelProperty("收款账号")
    @Excel(name = "收款账号")
    private String payeeBankcard;
    /**
     * 实际发放金额
     */
    @ApiModelProperty("发放金额")
    @Excel(name = "发放金额")
    private BigDecimal actualAmount;
    /**
     * 到账时间
     */
    @ApiModelProperty("到账时间")
    @Excel(name = "到账时间",dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPayeeMobile() {
        return payeeMobile;
    }

    public void setPayeeMobile(String payeeMobile) {
        this.payeeMobile = payeeMobile;
    }

    public String getPayIdCardNo() {
        return payIdCardNo;
    }

    public void setPayIdCardNo(String payIdCardNo) {
        this.payIdCardNo = payIdCardNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public String getPayeeBankcard() {
        return payeeBankcard;
    }

    public void setPayeeBankcard(String payeeBankcard) {
        this.payeeBankcard = payeeBankcard;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

}
