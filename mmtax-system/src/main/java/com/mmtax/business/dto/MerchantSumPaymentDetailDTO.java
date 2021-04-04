package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/15 16:12
 */
@ApiModel(value = "当月累计打款统计")
public class MerchantSumPaymentDetailDTO {

    @ApiModelProperty(value = "本月累计打款金额")
    private String amount;
    @ApiModelProperty(value = "本月累计打款笔数")
    private Integer num;
    @ApiModelProperty("银行卡打金额")
    private BigDecimal bankAmount;
    @ApiModelProperty("支付宝打款金额")
    private BigDecimal alipayAmount;
    @ApiModelProperty("微信打款金额")
    private BigDecimal wechatAmount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(BigDecimal bankAmount) {
        this.bankAmount = bankAmount;
    }

    public BigDecimal getAlipayAmount() {
        return alipayAmount;
    }

    public void setAlipayAmount(BigDecimal alipayAmount) {
        this.alipayAmount = alipayAmount;
    }

    public BigDecimal getWechatAmount() {
        return wechatAmount;
    }

    public void setWechatAmount(BigDecimal wechatAmount) {
        this.wechatAmount = wechatAmount;
    }

    @Override
    public String toString() {
        return "MerchantSumPaymentDetailDTO{" +
                "amount='" + amount + '\'' +
                ", num=" + num +
                ", bankAmount=" + bankAmount +
                ", alipayAmount=" + alipayAmount +
                ", wechatAmount=" + wechatAmount +
                '}';
    }
}
