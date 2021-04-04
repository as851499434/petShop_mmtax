package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 批量打款记录
 *
 * @author yuanligang
 * @date 2019/7/31
 */
public class RecordNumberDTO {


    @ApiModelProperty("商户id")
    private Integer merchantId;
    @ApiModelProperty("批次号")
    private String batchNo;
    @ApiModelProperty("打款状态 0-未处理1-已处理")
    private Integer status;
    @ApiModelProperty("商户申请记录数")
    private Integer merchantNum;
    @ApiModelProperty("商户总金额")
    private Integer systemNum;
    @ApiModelProperty("系统申请记录数")
    private BigDecimal merchantAmount;
    @ApiModelProperty("系统打款总金额")
    private BigDecimal systemAmount;
    @ApiModelProperty(value = "处理成功数量")
    private Integer successCount;

    public Integer getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(Integer merchantNum) {
        this.merchantNum = merchantNum;
    }

    public Integer getSystemNum() {
        return systemNum;
    }

    public void setSystemNum(Integer systemNum) {
        this.systemNum = systemNum;
    }

    public BigDecimal getMerchantAmount() {
        return merchantAmount;
    }

    public void setMerchantAmount(BigDecimal merchantAmount) {
        this.merchantAmount = merchantAmount;
    }

    public BigDecimal getSystemAmount() {
        return systemAmount;
    }

    public void setSystemAmount(BigDecimal systemAmount) {
        this.systemAmount = systemAmount;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "RecordNumberDTO{" +
                "batchNo='" + batchNo + '\'' +
                ", status=" + status +
                ", merchantNum=" + merchantNum +
                ", systemNum=" + systemNum +
                ", merchantAmount=" + merchantAmount +
                ", systemAmount=" + systemAmount +
                ", successCount=" + successCount +
                '}';
    }
}
