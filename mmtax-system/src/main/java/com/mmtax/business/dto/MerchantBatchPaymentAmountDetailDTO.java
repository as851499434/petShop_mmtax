package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/15 13:40
 */
@ApiModel(value = "批量代付金额")
public class MerchantBatchPaymentAmountDetailDTO {

    @ApiModelProperty(value = "成功订单数量")
    private Integer successfulOrdersNum;
    @ApiModelProperty(value = "成功订单总金额")
    private String successfulAmount;
    @ApiModelProperty(value = "失败订单数量")
    private Integer failOrdersNum;
    @ApiModelProperty(value = "失败订单总额")
    private String failAmount;
    @ApiModelProperty(value = "风险订单总数")
    private Integer riskOrdersNum;
    @ApiModelProperty(value = "风险订单总额")
    private String riskOrderAmount;
    @ApiModelProperty(value = "服务总费用")
    private String serviceAmount;
    @ApiModelProperty(value = "进行中订单总额")
    private String processAndInitAmount;
    @ApiModelProperty(value = "进行中订单数量")
    private Integer processAndInitNum;

    public String getProcessAndInitAmount() {
        return processAndInitAmount;
    }

    public void setProcessAndInitAmount(String processAndInitAmount) {
        this.processAndInitAmount = processAndInitAmount;
    }

    public Integer getProcessAndInitNum() {
        return processAndInitNum;
    }

    public void setProcessAndInitNum(Integer processAndInitNum) {
        this.processAndInitNum = processAndInitNum;
    }

    public Integer getSuccessfulOrdersNum() {
        return successfulOrdersNum;
    }

    public void setSuccessfulOrdersNum(Integer successfulOrdersNum) {
        this.successfulOrdersNum = successfulOrdersNum;
    }

    public String getSuccessfulAmount() {
        return successfulAmount;
    }

    public void setSuccessfulAmount(String successfulAmount) {
        this.successfulAmount = successfulAmount;
    }

    public Integer getFailOrdersNum() {
        return failOrdersNum;
    }

    public void setFailOrdersNum(Integer failOrdersNum) {
        this.failOrdersNum = failOrdersNum;
    }

    public String getFailAmount() {
        return failAmount;
    }

    public void setFailAmount(String failAmount) {
        this.failAmount = failAmount;
    }

    public Integer getRiskOrdersNum() {
        return riskOrdersNum;
    }

    public void setRiskOrdersNum(Integer riskOrdersNum) {
        this.riskOrdersNum = riskOrdersNum;
    }

    public String getRiskOrderAmount() {
        return riskOrderAmount;
    }

    public void setRiskOrderAmount(String riskOrderAmount) {
        this.riskOrderAmount = riskOrderAmount;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    @Override
    public String toString() {
        return "MerchantBatchPaymentAmountDetailDTO{" +
                "successfulOrdersNum=" + successfulOrdersNum +
                ", successfulAmount='" + successfulAmount + '\'' +
                ", failOrdersNum=" + failOrdersNum +
                ", failAmount='" + failAmount + '\'' +
                ", riskOrdersNum=" + riskOrdersNum +
                ", riskOrderAmount='" + riskOrderAmount + '\'' +
                ", serviceAmount='" + serviceAmount + '\'' +
                '}';
    }
}
