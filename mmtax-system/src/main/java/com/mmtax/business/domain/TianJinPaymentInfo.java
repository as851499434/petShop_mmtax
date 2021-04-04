package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 天津渠道商户表 tbl_tian_jin_payment_info
 *
 * @author meimiao
 * @date 2020-03-20
 */
@Table(name = "tbl_tian_jin_payment_info")
public class TianJinPaymentInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 商户id
     */
    private Integer merchantId;
    /**
     * token
     */
    @ApiModelProperty("token秘钥")
    private String token;
    /**
     * 企业用户id
     */
    @ApiModelProperty("企业用户id")
    private String xinbadaUserUuid;
    /**
     * 供应商 UUID
     */
    @ApiModelProperty(hidden = true)
    private String serverUserUuid;
    /**
     * 账户 UUID
     */
    @ApiModelProperty(hidden = true)
    private String customerAccountUuid;
    /**
     * 税源地id
     */
    @ApiModelProperty("税源地id")
    private Integer taxSourceCompanyId;
    /**
     *
     */
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

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setXinbadaUserUuid(String xinbadaUserUuid) {
        this.xinbadaUserUuid = xinbadaUserUuid;
    }

    public String getXinbadaUserUuid() {
        return xinbadaUserUuid;
    }

    public void setServerUserUuid(String serverUserUuid) {
        this.serverUserUuid = serverUserUuid;
    }

    public String getServerUserUuid() {
        return serverUserUuid;
    }

    public void setCustomerAccountUuid(String customerAccountUuid) {
        this.customerAccountUuid = customerAccountUuid;
    }

    public String getCustomerAccountUuid() {
        return customerAccountUuid;
    }

    public void setTaxSourceCompanyId(Integer taxSourceCompanyId) {
        this.taxSourceCompanyId = taxSourceCompanyId;
    }

    public Integer getTaxSourceCompanyId() {
        return taxSourceCompanyId;
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
                .append("merchantId", getMerchantId())
                .append("token", getToken())
                .append("xinbadaUserUuid", getXinbadaUserUuid())
                .append("serverUserUuid", getServerUserUuid())
                .append("customerAccountUuid", getCustomerAccountUuid())
                .append("taxSourceCompanyId", getTaxSourceCompanyId())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
