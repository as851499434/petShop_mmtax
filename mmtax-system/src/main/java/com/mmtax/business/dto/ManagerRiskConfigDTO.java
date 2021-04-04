package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 商户端风控信息
 *
 * @author yuanligang
 * @date 2019/8/2
 */
public class ManagerRiskConfigDTO {
    /**
     * risk表序列号
     */
    @ApiModelProperty(value = "risk表序列号")
    private Integer riskId;
    /**
     * 全网限额ON-开OFF-关
     */
    @ApiModelProperty(value = "全网限额ON-开OFF-关")
    private String quotaConfig;
    /**
     * 全网单人月累计金额限制
     */
    @ApiModelProperty(value = "全网单人月累计金额限制")
    private BigDecimal singleQuotaConfig;
    /**
     * 临时额度
     */
    @ApiModelProperty(value = "临时额度")
    private BigDecimal temporaryQuota;
    /**
     * 临时额度开始时间
     */
    @ApiModelProperty(value = "临时额度开始时间")
    private String temporaryQuotaBegin;
    /**
     * 临时额度结束时间
     */
    @ApiModelProperty(value = "临时额度结束时间")
    private String temporaryQuotaEnd;
    /**
     * 单商户日限额ON开OFF关
     */
    @ApiModelProperty(value = "单商户日限额ON开OFF关")
    private String singleDayQuota;
    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String contractNo;

    /**
     * 单商户累计限额
     */
    @ApiModelProperty(value = "单商户累计限额")
    private BigDecimal cumulativeQuota;


    public Integer getRiskId() {
        return riskId;
    }

    public void setRiskId(Integer riskId) {
        this.riskId = riskId;
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

    public String getSingleDayQuota() {
        return singleDayQuota;
    }

    public void setSingleDayQuota(String singleDayQuota) {
        this.singleDayQuota = singleDayQuota;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public BigDecimal getCumulativeQuota() {
        return cumulativeQuota;
    }

    public void setCumulativeQuota(BigDecimal cumulativeQuota) {
        this.cumulativeQuota = cumulativeQuota;
    }

    @Override
    public String toString() {
        return "ManagerRiskConfigDTO{" +
                "riskId=" + riskId +
                ", quotaConfig='" + quotaConfig + '\'' +
                ", singleQuotaConfig=" + singleQuotaConfig +
                ", temporaryQuota=" + temporaryQuota +
                ", temporaryQuotaBegin='" + temporaryQuotaBegin + '\'' +
                ", temporaryQuotaEnd='" + temporaryQuotaEnd + '\'' +
                ", singleDayQuota='" + singleDayQuota + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", cumulativeQuota=" + cumulativeQuota +
                '}';
    }
}
