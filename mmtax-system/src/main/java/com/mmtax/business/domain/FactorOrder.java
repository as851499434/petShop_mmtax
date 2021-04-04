package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 要素认证订单表 tbl_factor_order
 *
 * @author meimiao
 * @date 2019-08-12
 */
@Table(name = "tbl_factor_order")
public class FactorOrder {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 账单编号
     */
    private String orderNo;
    /**
     * 订单笔数
     */
    private Integer orderNum;
    /**
     * 服务费实收金额
     */
    private BigDecimal serviceAmount;
    /**
     * 冲补金额
     */
    private BigDecimal offsetAmount;
    /**
     * 订单总额
     */
    private BigDecimal totalAmount;
    /**
     *
     */
    private Integer merchantId;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setServiceAmount(BigDecimal serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public BigDecimal getServiceAmount() {
        return serviceAmount;
    }

    public void setOffsetAmount(BigDecimal offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    public BigDecimal getOffsetAmount() {
        return offsetAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
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


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderNo", getOrderNo())
                .append("orderNum", getOrderNum())
                .append("serviceAmount", getServiceAmount())
                .append("offsetAmount", getOffsetAmount())
                .append("totalAmount", getTotalAmount())
                .append("merchantId", getMerchantId())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
