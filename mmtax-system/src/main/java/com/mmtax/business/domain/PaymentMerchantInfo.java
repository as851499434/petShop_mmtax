package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 代付平台商户表 tbl_payment_merchant_info
 *
 * @author meimiao
 * @date 2019-07-10
 */
@Table(name = "tbl_payment_merchant_info")
public class PaymentMerchantInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 系统商户id
     */
    private Integer merchantId;
    /**
     * 私钥
     */
    private String secretKey;
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 代付通道的商户号
     */
    private String merchantNo;
    /**
     * BANK银行
     */
    private String type;
    /**
     * 通道CHANPAY-畅捷YUNZB-云众包
     */
    private String channel;
    /**
     * 所属税源地公司id
     */
    private Integer taxSourceId;
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

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTaxSourceId(Integer taxSourceId) {
        this.taxSourceId = taxSourceId;
    }

    public Integer getTaxSourceId() {
        return taxSourceId;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("merchantId" , getMerchantId())
                .append("secretKey" , getSecretKey())
                .append("publicKey" , getPublicKey())
                .append("merchantNo" , getMerchantNo())
                .append("type" , getType())
                .append("channel", getChannel())
                .append("taxSourceId" , getTaxSourceId())
                .append("providerId" , getProviderId())
                .append("createTime" , getCreateTime())
                .append("updateTime" , getUpdateTime())
                .toString();
    }
}
