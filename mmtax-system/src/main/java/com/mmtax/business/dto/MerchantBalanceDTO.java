package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 余额日账单DTO
 * @author yuanligang
 * @date 2019/7/23
 */
public class MerchantBalanceDTO {
    /**
     * 批量打款记录ID
     */
    @ApiModelProperty(value = "批量打款记录ID")
    private Integer id;
    /**
     * 对账编号
     */
    @ApiModelProperty(value = "对账编号")
    private String  trxExternalNo;
    /**
     * 账单日期
     */
    @ApiModelProperty(value = "账单日期")
    private String  createTime;
    /**
     * 代征主体 一期为常量 安薪宝
     */
    @ApiModelProperty(value = "代征主体")
    private String subject;
    /**
     * 请求打款金额
     * 不含杂费
     */
    @ApiModelProperty(value = "请求打款金额" )
    private BigDecimal paymentAmount;
    /**
     * 服务费实收金额
     * 该笔批量打款下的所有批量打款相加
     */
    @ApiModelProperty(value = "服务费实收金额")
    private BigDecimal serviceFee;
    /**
     * 冲补金额
     * 一期为0
     */
    @ApiModelProperty(value = "冲补金额")
    private BigDecimal complementAmount;
    /**
     * 账单总金额
     * 包含所有杂费
     */
    @ApiModelProperty(value = "账单总金额")
    private BigDecimal paymentActualAmount;
    /**
     *打款订单状态
     * 0-未处理1-已处理
     */
    @ApiModelProperty(value = "打款订单状态")
    private Integer status;
    /**
     * 服务费抵扣金额
     * todo 一期暂不处理
     */
    @ApiModelProperty(value = "服务费抵扣金额")
    private BigDecimal serviceFeeDeduction;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getComplementAmount() {
        return complementAmount;
    }

    public void setComplementAmount(BigDecimal complementAmount) {
        this.complementAmount = complementAmount;
    }

    public BigDecimal getPaymentActualAmount() {
        return paymentActualAmount;
    }

    public void setPaymentActualAmount(BigDecimal paymentActualAmount) {
        this.paymentActualAmount = paymentActualAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getServiceFeeDeduction() {
        return serviceFeeDeduction;
    }

    public void setServiceFeeDeduction(BigDecimal serviceFeeDeduction) {
        this.serviceFeeDeduction = serviceFeeDeduction;
    }

    @Override
    public String toString() {
        return "MerchantBalanceDTO{" +
                "id=" + id +
                ", trxExternalNo='" + trxExternalNo + '\'' +
                ", createTime='" + createTime + '\'' +
                ", subject='" + subject + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", serviceFee=" + serviceFee +
                ", complementAmount=" + complementAmount +
                ", paymentActualAmount=" + paymentActualAmount +
                ", status=" + status +
                ", serviceFeeDeduction=" + serviceFeeDeduction +
                '}';
    }
}
