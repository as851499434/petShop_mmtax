package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 要素认证表 tbl_factor_certification
 *
 * @author meimiao
 * @date 2019-08-08
 */
@Table(name = "tbl_factor_certification")
public class FactorCertification {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 平台订单号
     */
    private String orderSerialNum;
    /**
     * 商户订单号
     */
    private String merchantSerialNum;
    /**
     * 0-三要素认证
     */
    private Integer type;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String idCardNo;
    /**
     * 银行卡号
     */
    private String bankCardNo;
    /**
     * 0-同意1-拒绝
     */
    private Integer status;
    /**
     * 要素认证订单id
     */
    private Integer factorOrderId;
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

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setMerchantSerialNum(String merchantSerialNum) {
        this.merchantSerialNum = merchantSerialNum;
    }

    public String getMerchantSerialNum() {
        return merchantSerialNum;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
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

    public Integer getFactorOrderId() {
        return factorOrderId;
    }

    public void setFactorOrderId(Integer factorOrderId) {
        this.factorOrderId = factorOrderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderSerialNum", getOrderSerialNum())
                .append("merchantSerialNum", getMerchantSerialNum())
                .append("type", getType())
                .append("name", getName())
                .append("idCardNo", getIdCardNo())
                .append("bankCardNo", getBankCardNo())
                .append("status", getStatus())
                .append("factorOrderId", getFactorOrderId())
                .append("merchantId", getMerchantId())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
