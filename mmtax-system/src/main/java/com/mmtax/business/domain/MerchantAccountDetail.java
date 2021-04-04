package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户账户余额变动明细表 tbl_merchant_account_detail
 *
 * @author meimiao
 * @date 2019-07-29
 */
@Table(name = "tbl_merchant_account_detail")
public class MerchantAccountDetail {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 交易之前总余额
     */
    private BigDecimal paymentAmountBefore;
    /**
     * 交易之后总余额
     */
    private BigDecimal paymentAmountAfter;
    /**
     * 交易之前冻结金额
     */
    private BigDecimal paymentFrozenAmountBefore;
    /**
     * 交易之后冻结金额
     */
    private BigDecimal paymentFrozenAmountAfter;
    /**
     * 交易之前可用余额
     */
    private BigDecimal paymentUsableAmountBefore;
    /**
     * 代付之后可用余额
     */
    private BigDecimal paymentUsableAmountAfter;
    /**
     * 订单状态 0-失败 1-成功
     */
    private Integer status;
    /**
     * 交易类型 0-代付 1-充值
     */
    private Integer type;
    /**
     * 资金明细类型（0-发放佣金 1-付款）
     */
    private Integer paymentType;
    /**
     * 交易金额
     */
    private BigDecimal paymentAmount;
    /**
     * 订单表流水号
     */
    private String orderSerialNum;
    /**
     * 商户id
     */
    private Integer merchantId;
    /**
     * 代付通道
     */
    private String paymentChannel;
    /**
     *
     */
    @Transient
    private Integer providerId;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setPaymentAmountBefore(BigDecimal paymentAmountBefore) {
        this.paymentAmountBefore = paymentAmountBefore;
    }

    public BigDecimal getPaymentAmountBefore() {
        return paymentAmountBefore;
    }

    public void setPaymentAmountAfter(BigDecimal paymentAmountAfter) {
        this.paymentAmountAfter = paymentAmountAfter;
    }

    public BigDecimal getPaymentAmountAfter() {
        return paymentAmountAfter;
    }

    public void setPaymentFrozenAmountBefore(BigDecimal paymentFrozenAmountBefore) {
        this.paymentFrozenAmountBefore = paymentFrozenAmountBefore;
    }

    public BigDecimal getPaymentFrozenAmountBefore() {
        return paymentFrozenAmountBefore;
    }

    public void setPaymentFrozenAmountAfter(BigDecimal paymentFrozenAmountAfter) {
        this.paymentFrozenAmountAfter = paymentFrozenAmountAfter;
    }

    public BigDecimal getPaymentFrozenAmountAfter() {
        return paymentFrozenAmountAfter;
    }

    public void setPaymentUsableAmountBefore(BigDecimal paymentUsableAmountBefore) {
        this.paymentUsableAmountBefore = paymentUsableAmountBefore;
    }

    public BigDecimal getPaymentUsableAmountBefore() {
        return paymentUsableAmountBefore;
    }

    public void setPaymentUsableAmountAfter(BigDecimal paymentUsableAmountAfter) {
        this.paymentUsableAmountAfter = paymentUsableAmountAfter;
    }

    public BigDecimal getPaymentUsableAmountAfter() {
        return paymentUsableAmountAfter;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("paymentAmountBefore", getPaymentAmountBefore())
                .append("paymentAmountAfter", getPaymentAmountAfter())
                .append("paymentFrozenAmountBefore", getPaymentFrozenAmountBefore())
                .append("paymentFrozenAmountAfter", getPaymentFrozenAmountAfter())
                .append("paymentUsableAmountBefore", getPaymentUsableAmountBefore())
                .append("paymentUsableAmountAfter", getPaymentUsableAmountAfter())
                .append("status", getStatus())
                .append("type", getType())
                .append("updateTime", getPaymentType())
                .append("paymentAmount", getPaymentAmount())
                .append("paymentChannel", getPaymentChannel())
                .append("orderSerialNum", getOrderSerialNum())
                .append("merchantId", getMerchantId())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
