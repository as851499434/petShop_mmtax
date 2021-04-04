package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/22 10:40
 */
@ApiModel(description = "首页打款数据")
public class ManagerIndexPaymentDTO {

    @ApiModelProperty(value = "打款笔数")
    private String orderNum;
    @ApiModelProperty(value = "累计打款金额")
    private String amount;
    @ApiModelProperty(value = "银行卡打款金额")
    private String bankAmount;
    @ApiModelProperty(value = "支付宝打款金额")
    private String aLiPayAmount;
    @ApiModelProperty(value = "微信打款金额")
    private String weChatAmount;
    @ApiModelProperty(value = "累计扣税金额")
    private String taxFeeValue;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(String bankAmount) {
        this.bankAmount = bankAmount;
    }

    public String getaLiPayAmount() {
        return aLiPayAmount;
    }

    public void setaLiPayAmount(String aLiPayAmount) {
        this.aLiPayAmount = aLiPayAmount;
    }

    public String getWeChatAmount() {
        return weChatAmount;
    }

    public void setWeChatAmount(String weChatAmount) {
        this.weChatAmount = weChatAmount;
    }

    public String getTaxFeeValue() {
        return taxFeeValue;
    }

    public void setTaxFeeValue(String taxFeeValue) {
        this.taxFeeValue = taxFeeValue;
    }

    @Override
    public String toString() {
        return "ManagerIndexPaymentDTO{" +
                "orderNum='" + orderNum + '\'' +
                ", amount='" + amount + '\'' +
                ", bankAmount='" + bankAmount + '\'' +
                ", aLiPayAmount='" + aLiPayAmount + '\'' +
                ", weChatAmount='" + weChatAmount + '\'' +
                ", taxFeeValue='" + taxFeeValue + '\'' +
                '}';
    }
}
