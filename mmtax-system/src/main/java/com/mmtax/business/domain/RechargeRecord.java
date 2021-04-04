package com.mmtax.business.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值记录表 tbl_recharge_record
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Table(name = "tbl_recharge_record")
public class RechargeRecord {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     *
     */
    private Integer merchantId;
    /**
     * 充值金额
     */
    @ApiModelProperty("充值金额== 申请开票金额")
    private BigDecimal amount;
    /**
     * 充值前余额
     */
    private BigDecimal rechargeAmountBefore;
    /**
     * 充值后余额
     */
    private BigDecimal rechargeAmountAfter;
    /**
     * 充值前可用余额
     */
    private BigDecimal rechargeUsableAmountBefore;
    /**
     * 充值后可用余额
     */
    private BigDecimal rechargeUsableAmountAfter;
    /**
     * 充值渠道BANK-银行ALIPAY-支付宝WECHAT-微信
     */
    @ApiModelProperty("充值渠道BANK-银行ALIPAY-支付宝WECHAT-微信")
    private String rechargeChannel;
    /**
     * ONLINE-线上UNDERLINE-线下
     */
    @ApiModelProperty("充值类型---ONLINE-线上UNDERLINE-线下")
    private String rechargeType;

    @ApiModelProperty("0-未开发票1-已开发票")
    private Integer invoiceStatus;

    @ApiModelProperty("充值状态SUCCESS-成功FAIL-失败ALLPYING-申请中")
    private String status;

    @ApiModelProperty("充值流水号")
    private String orderSerialNum;

    /**
     *
     */
    @Transient
    private Integer providerId;
    /**
     *
     */
    @ApiModelProperty("充值时间")
    private Date createTime;
    /**
     *
     */
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRechargeAmountBefore() {
        return rechargeAmountBefore;
    }

    public void setRechargeAmountBefore(BigDecimal rechargeAmountBefore) {
        this.rechargeAmountBefore = rechargeAmountBefore;
    }

    public BigDecimal getRechargeAmountAfter() {
        return rechargeAmountAfter;
    }

    public void setRechargeAmountAfter(BigDecimal rechargeAmountAfter) {
        this.rechargeAmountAfter = rechargeAmountAfter;
    }

    public BigDecimal getRechargeUsableAmountBefore() {
        return rechargeUsableAmountBefore;
    }

    public void setRechargeUsableAmountBefore(BigDecimal rechargeUsableAmountBefore) {
        this.rechargeUsableAmountBefore = rechargeUsableAmountBefore;
    }

    public BigDecimal getRechargeUsableAmountAfter() {
        return rechargeUsableAmountAfter;
    }

    public void setRechargeUsableAmountAfter(BigDecimal rechargeUsableAmountAfter) {
        this.rechargeUsableAmountAfter = rechargeUsableAmountAfter;
    }

    public String getRechargeChannel() {
        return rechargeChannel;
    }

    public void setRechargeChannel(String rechargeChannel) {
        this.rechargeChannel = rechargeChannel;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    @Override
    public String toString() {
        return "RechargeRecord{" +
                "id=" + id +
                ", merchantId=" + merchantId +
                ", amount=" + amount +
                ", rechargeAmountBefore=" + rechargeAmountBefore +
                ", rechargeAmountAfter=" + rechargeAmountAfter +
                ", rechargeUsableAmountBefore=" + rechargeUsableAmountBefore +
                ", rechargeUsableAmountAfter=" + rechargeUsableAmountAfter +
                ", rechargeChannel='" + rechargeChannel + '\'' +
                ", rechargeType='" + rechargeType + '\'' +
                ", invoiceStatus=" + invoiceStatus +
                ", status='" + status + '\'' +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", providerId=" + providerId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
