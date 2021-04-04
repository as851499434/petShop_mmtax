package com.mmtax.business.domain;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 风险配置表 tbl_risk_management_config
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Table(name = "tbl_risk_management_config")
public class RiskManagementConfig {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;
    /**
     * 全网限额ON-开OFF-关
     */
    @ApiModelProperty(value = "全网限额ON-开OFF-关", required = true)
    private String quotaConfig;
    /**
     * 全网单人月累计金额限制
     */
    @ApiModelProperty(value = "全网单人月累计金额限制", required = true)
    private BigDecimal singleQuotaConfig;
    /**
     * 临时额度
     */
    @ApiModelProperty(value = "临时额度", required = true)
    private BigDecimal temporaryQuota;
    /**
     * 临时额度开始时间
     */
    @ApiModelProperty(value = "临时额度开始时间", required = true)
    private String temporaryQuotaBegin;
    /**
     * 临时额度结束时间
     */
    @ApiModelProperty(value = "临时额度结束时间", required = true)
    private String temporaryQuotaEnd;

    /**
     * 单商户累计限额
     */
    @ApiModelProperty(value = "单商户累计限额")
    private BigDecimal cumulativeQuota;
    /**
     * 商户id
     */
    @ApiModelProperty(hidden = true)
    private Integer merchantId;
    /**
     * 单商户日限额ON开OFF关
     */
    @ApiModelProperty(value = "单商户日限额ON开OFF关", required = true)
    private String singleDayQuota;
    /**
     *
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private Integer providerId;
    /**
     *
     */
    @ApiModelProperty(hidden = true)
    private Date createTime;
    /**
     *
     */
    @ApiModelProperty(hidden = true)
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuotaConfig() {
        return quotaConfig;
    }

    public void setQuotaConfig(String quotaConfig) {
        this.quotaConfig = quotaConfig;
    }

    public BigDecimal getSingleQuotaConfig() {
        return singleQuotaConfig;
    }

    public void setSingleQuotaConfig(BigDecimal singleQuotaConfig) {
        this.singleQuotaConfig = singleQuotaConfig;
    }

    public BigDecimal getTemporaryQuota() {
        return temporaryQuota;
    }

    public void setTemporaryQuota(BigDecimal temporaryQuota) {
        this.temporaryQuota = temporaryQuota;
    }

    public String getTemporaryQuotaBegin() {
        return temporaryQuotaBegin;
    }

    public void setTemporaryQuotaBegin(String temporaryQuotaBegin) {
        this.temporaryQuotaBegin = temporaryQuotaBegin;
    }

    public String getTemporaryQuotaEnd() {
        return temporaryQuotaEnd;
    }

    public void setTemporaryQuotaEnd(String temporaryQuotaEnd) {
        this.temporaryQuotaEnd = temporaryQuotaEnd;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getSingleDayQuota() {
        return singleDayQuota;
    }

    public void setSingleDayQuota(String singleDayQuota) {
        this.singleDayQuota = singleDayQuota;
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

    public BigDecimal getCumulativeQuota() {
        return cumulativeQuota;
    }

    public void setCumulativeQuota(BigDecimal cumulativeQuota) {
        this.cumulativeQuota = cumulativeQuota;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("quotaConfig" , getQuotaConfig())
                .append("singleQuotaConfig" , getSingleQuotaConfig())
                .append("temporaryQuota" , getTemporaryQuota())
                .append("temporaryQuotaBegin" , getTemporaryQuotaBegin())
                .append("temporaryQuotaEnd" , getTemporaryQuotaEnd())
                .append("merchantId" , getMerchantId())
                .append("singleDayQuota" , getSingleDayQuota())
                .append("cumulativeQuota", getCumulativeQuota())
                .append("providerId" , getProviderId())
                .append("createTime" , getCreateTime())
                .append("updateTime" , getUpdateTime())
                .toString();
    }
}
