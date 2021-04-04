package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 后台资金流水
 * @author yuanligang
 * @date 2019/7/24
 */
public class ManagerCapitalFlowDTO {
    @ApiModelProperty("表记录序列号")
    private Integer id;
    /**
     * 商户名称
     */
    @ApiModelProperty("商户名称")
    private String merchantName;

    /**
     *商户编码
     */
    @ApiModelProperty("商户编码")
    private String merchantCode;

    /**
     * 入账时间
     */
    @ApiModelProperty("入账时间")
    private String accountingTime;
    /**
     * 审核时间
     */
    @ApiModelProperty("审核时间")
    private String auditTime;
    /**
     * 代征主体
     * todo 一期安薪宝
     */
    @ApiModelProperty("代征主体")
    private String subject;
    /**
     * 审核人
     * todo 暂定system
     */
    @ApiModelProperty("审核人")
    private String auditor;
    /**
     * 流水号
     */
    @ApiModelProperty("流水号")
    private String orderSerialNum;

    /**
     * 交易类型
     */
    @ApiModelProperty("交易类型")
    private Integer type;
    /**
     * 金额
     */
    @ApiModelProperty("金额")
    private BigDecimal amount;

    /**
     * 订单状态
     */
    @ApiModelProperty("订单状态")
    private Integer status;

    @ApiModelProperty(value = "销售名字")
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

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getAccountingTime() {
        return accountingTime;
    }

    public void setAccountingTime(String accountingTime) {
        this.accountingTime = accountingTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ManagerCapitalFlowDTO{" +
                "id=" + id +
                ", merchantName='" + merchantName + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", accountingTime='" + accountingTime + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", subject='" + subject + '\'' +
                ", auditor='" + auditor + '\'' +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
