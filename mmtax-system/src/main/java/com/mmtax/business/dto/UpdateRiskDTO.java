package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 风险配置DTO
 * @author yuanligang
 * @date 2019/7/16
 */
public class UpdateRiskDTO {

    /**
     * 表序列号
     */
    @ApiModelProperty(value = "id", required = true)
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
     * 商户id
     */
    @ApiModelProperty(hidden = true)
    private Integer merchantId;
    /**
     * 单商户日限额ON开OFF关
     */
    @ApiModelProperty(value = "单商户日限额ON开OFF关", required = true)
    private String singleDayQuota;

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

    @Override
    public String toString() {
        return "UpdateRiskDTO{" +
                "id=" + id +
                ", quotaConfig='" + quotaConfig + '\'' +
                ", singleQuotaConfig=" + singleQuotaConfig +
                ", temporaryQuota=" + temporaryQuota +
                ", temporaryQuotaBegin='" + temporaryQuotaBegin + '\'' +
                ", temporaryQuotaEnd='" + temporaryQuotaEnd + '\'' +
                ", merchantId=" + merchantId +
                ", singleDayQuota='" + singleDayQuota + '\'' +
                '}';
    }
}
