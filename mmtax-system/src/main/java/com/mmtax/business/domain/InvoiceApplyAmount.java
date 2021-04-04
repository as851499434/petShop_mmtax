package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 发票申请金额表 tbl_invoice_apply_amount
 *
 * @author meimiao
 * @date 2019-08-09
 */
@Table(name = "tbl_invoice_apply_amount")
public class InvoiceApplyAmount {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 对应发票ID
     */
    private Integer invoiceId;
    /**
     * 应税劳务服务名
     */
    private String invoiceSerialName;
    /**
     *
     */
    private BigDecimal invoiceAmount;
    /**
     * 税率
     */
    private BigDecimal taxRate;
    /**
     * 发票税额
     */
    private BigDecimal taxAmount;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 0-未删除1-已删除
     */
    private Integer status;
    /**
     * 发票详情id
     */
    private Integer invoiceDetailId;
    /**
     *
     */
    @Transient
    private Integer providerId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceSerialName(String invoiceSerialName) {
        this.invoiceSerialName = invoiceSerialName;
    }

    public String getInvoiceSerialName() {
        return invoiceSerialName;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(Integer invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("invoiceId", getInvoiceId())
                .append("invoiceSerialName", getInvoiceSerialName())
                .append("invoiceAmount", getInvoiceAmount())
                .append("taxRate", getTaxRate())
                .append("taxAmount", getTaxAmount())
                .append("unitPrice", getUnitPrice())
                .append("status", getStatus())
                .append("invoiceDetailId", getInvoiceDetailId())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
