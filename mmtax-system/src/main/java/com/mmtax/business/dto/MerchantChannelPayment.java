package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 不同平台月代付金额
 *
 * @author yuanligang
 * @date 2019/7/31
 */
public class MerchantChannelPayment {

    @ApiModelProperty("BANK-银行ALIPAY-支付宝WECHAT-微信打款渠道")
    private String paymentChannel;
    @ApiModelProperty("月累计金额")
    private BigDecimal amount;

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MercahntChannelPaymenet{" +
                "paymentChannel='" + paymentChannel + '\'' +
                ", amount=" + amount +
                '}';
    }
}
