package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 税源地公司表 tbl_tax_sounrce_company
 *
 * @author meimiao
 * @date 2019-07-10
 */
@Table(name = "tbl_tax_sounrce_company")
public class TaxSounrceCompany {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 税源地公司商户号
     */
    private String merchantNo;
    /**
     * 私钥
     */
    private String secretKey;
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 税源地公司名称
     */
    private String taxSounrceCompanyName;
    /**
     * 渠道CHANPAY-畅捷
     */
    private String channel;
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

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantNo() {
        return merchantNo;
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

    public void setTaxSounrceCompanyName(String taxSounrceCompanyName) {
        this.taxSounrceCompanyName = taxSounrceCompanyName;
    }

    public String getTaxSounrceCompanyName() {
        return taxSounrceCompanyName;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
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
                .append("id" , getId())
                .append("merchantNo" , getMerchantNo())
                .append("secretKey" , getSecretKey())
                .append("publicKey" , getPublicKey())
                .append("taxSounrceCompanyName" , getTaxSounrceCompanyName())
                .append("channel" , getChannel())
                .append("providerId" , getProviderId())
                .append("createTime" , getCreateTime())
                .append("updateTime" , getUpdateTime())
                .toString();
    }
}
