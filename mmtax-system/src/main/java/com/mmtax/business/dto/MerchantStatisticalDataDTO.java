package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/27 9:20
 */
public class MerchantStatisticalDataDTO {
    @ApiModelProperty("打款总笔数")
    private Integer sumOrderCount;
    @ApiModelProperty("打款成功笔数")
    private Integer successOrderCount;
    @ApiModelProperty("打款失败笔数")
    private Integer failOrderCount;
    @ApiModelProperty("未完成订单笔数")
    private Integer unFinishOrderCount;
    @ApiModelProperty("交易总金额")
    private String sumOrderAmount;
    @ApiModelProperty("打款成功金额")
    private String successOrderAmount;
    @ApiModelProperty("打款失败金额")
    private String failOrderAmount;
    @ApiModelProperty("打款未完成金额")
    private String unFinishOrderAmount;
    @ApiModelProperty("商户服务费")
    private String merchantServiceAmount;

    public Integer getSumOrderCount() {
        return sumOrderCount == null ? 0 : sumOrderCount;
    }

    public void setSumOrderCount(Integer sumOrderCount) {
        this.sumOrderCount = sumOrderCount == null ? 0 : sumOrderCount;
    }

    public Integer getSuccessOrderCount() {
        return successOrderCount == null ? 0 : successOrderCount;
    }

    public void setSuccessOrderCount(Integer successOrderCount) {
        this.successOrderCount = successOrderCount == null ? 0 : successOrderCount;
    }

    public Integer getFailOrderCount() {
        return failOrderCount == null ? 0 : failOrderCount;
    }

    public void setFailOrderCount(Integer failOrderCount) {
        this.failOrderCount = failOrderCount == null ? 0 : failOrderCount;
    }

    public Integer getUnFinishOrderCount() {
        return unFinishOrderCount == null ? 0 : unFinishOrderCount;
    }

    public void setUnFinishOrderCount(Integer unFinishOrderCount) {
        this.unFinishOrderCount = unFinishOrderCount == null ? 0 : unFinishOrderCount;
    }

    public String getSumOrderAmount() {
        return sumOrderAmount == null ? BigDecimal.ZERO.toString() : sumOrderAmount;
    }

    public void setSumOrderAmount(String sumOrderAmount) {
        this.sumOrderAmount = sumOrderAmount == null ? BigDecimal.ZERO.toString() : sumOrderAmount;
    }

    public String getSuccessOrderAmount() {
        return successOrderAmount == null ? BigDecimal.ZERO.toString() : successOrderAmount;
    }

    public void setSuccessOrderAmount(String successOrderAmount) {
        this.successOrderAmount = successOrderAmount == null ? BigDecimal.ZERO.toString() : successOrderAmount;
    }

    public String getFailOrderAmount() {
        return failOrderAmount == null ? BigDecimal.ZERO.toString() : failOrderAmount;
    }

    public void setFailOrderAmount(String failOrderAmount) {
        this.failOrderAmount = failOrderAmount == null ? BigDecimal.ZERO.toString() : failOrderAmount;
    }

    public String getUnFinishOrderAmount() {
        return unFinishOrderAmount == null ? BigDecimal.ZERO.toString() : unFinishOrderAmount;
    }

    public void setUnFinishOrderAmount(String unFinishOrderAmount) {
        this.unFinishOrderAmount = unFinishOrderAmount == null ? BigDecimal.ZERO.toString() : unFinishOrderAmount;
    }

    public String getMerchantServiceAmount() {
        return merchantServiceAmount == null ? BigDecimal.ZERO.toString() : merchantServiceAmount;
    }

    public void setMerchantServiceAmount(String merchantServiceAmount) {
        this.merchantServiceAmount = merchantServiceAmount == null ? BigDecimal.ZERO.toString() : merchantServiceAmount;
    }

    @Override
    public String toString() {
        return "MerchantStatisticalDataDTO{" +
                "sumOrderCount=" + sumOrderCount +
                ", successOrderCount=" + successOrderCount +
                ", failOrderCount=" + failOrderCount +
                ", unFinishOrderCount=" + unFinishOrderCount +
                ", sumOrderAmount='" + sumOrderAmount + '\'' +
                ", successOrderAmount='" + successOrderAmount + '\'' +
                ", failOrderAmount='" + failOrderAmount + '\'' +
                ", unFinishOrderAmount='" + unFinishOrderAmount + '\'' +
                ", merchantServiceAmount='" + merchantServiceAmount + '\'' +
                '}';
    }
}
