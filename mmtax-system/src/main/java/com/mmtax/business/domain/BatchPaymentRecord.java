package com.mmtax.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 批量打款记录表 tbl_batch_payment_record
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Table(name = "tbl_batch_payment_record")
public class BatchPaymentRecord {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 平台订单号
     */
    @ApiModelProperty(value = "平台订单号")
    private String trxExternalNo;
    /**
     * 批次号
     */
    @ApiModelProperty(value = "批次号")
    private String batchNo;
    /**
     * 打款渠道BANK-银行ALIPAY-支付宝WECHAT-微信
     */
    @ApiModelProperty(value = "打款渠道BANK-银行ALIPAY-支付宝WECHAT-微信")
    private String paymentChannel;
    /**
     * 打款记录
     */
    @ApiModelProperty(value = "打款记录数")
    private Integer paymentCount;
    /**
     * 已完成的打款条数
     */
    @ApiModelProperty(value = "已完成的打款条数")
    private Integer paymentCompleteCount;
    /**
     * 商户打款请求总额
     */
    @ApiModelProperty(value = "商户打款请求总额")
    private BigDecimal paymentAmount;
    /**
     * 实际打款总额
     */
    @JsonIgnore
    private BigDecimal paymentActualAmount;
    /**
     * 商户打款请求总额
     */
    @ApiModelProperty(value = "此次批量打款我方补偿的服务费")
    private BigDecimal overCharge;
    /**
     * 0-待打款 3-已取消 2-打款中 1-已完成
     */
    @JsonIgnore
    private Integer status;
    /**
     * 是否已发送校验 0-否 1-是
     */
    private Integer checkStatus;
    /**
     * 确认执行打款 0-未确认 1-已确认
     */
    private Integer confirmStatus;
    /**
     * 是否已打款 0-否 1-是
     */
    private Integer payStatus;
    /**
     * 商户id
     */
    @JsonIgnore
    private Integer merchantId;
    /**
     * 创建者
     */
    @JsonIgnore
    private String creater;
    /**
     * 操作人
     */
    @JsonIgnore
    private String operator;
    /**
     *
     */
    @JsonIgnore
    @Transient
    private Integer providerId;
    /**
     *
     */
    @JsonIgnore
    private Date createTime;
    /**
     *
     */
    @JsonIgnore
    private Date updateTime;

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String taskName;

    public Integer getPaymentCompleteCount() {
        return paymentCompleteCount;
    }

    public void setPaymentCompleteCount(Integer paymentCompleteCount) {
        this.paymentCompleteCount = paymentCompleteCount;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public BigDecimal getOverCharge() {
        return overCharge;
    }

    public void setOverCharge(BigDecimal overCharge) {
        this.overCharge = overCharge;
    }
    
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrxExternalNo() {
        return trxExternalNo;
    }

    public void setTrxExternalNo(String trxExternalNo) {
        this.trxExternalNo = trxExternalNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public Integer getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(Integer paymentCount) {
        this.paymentCount = paymentCount;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getPaymentActualAmount() {
        return paymentActualAmount;
    }

    public void setPaymentActualAmount(BigDecimal paymentActualAmount) {
        this.paymentActualAmount = paymentActualAmount;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("trxExternalNo" , getTrxExternalNo())
                .append("batchNo" , getBatchNo())
                .append("paymentChannel" , getPaymentChannel())
                .append("paymentCount" , getPaymentCount())
                .append("paymentAmount" , getPaymentAmount())
                .append("paymentActualAmount" , getPaymentActualAmount())
                .append("status", getStatus())
                .append("creater", getCreater())
                .append("operator", getOperator())
                .append("merchantId" , getMerchantId())
                .append("providerId" , getProviderId())
                .append("createTime" , getCreateTime())
                .append("updateTime" , getUpdateTime())
                .append("taskName" , getTaskName())
                .toString();
    }
}
