package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/22 14:54
 */
@ApiModel(description = "校验密码")
public class CheckPasswordDTO {
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "商户id")
    private Integer merchantId;
    @ApiModelProperty(value = "选择代付通道,BANK-银行ALIPAY-支付宝WECHAT-微信")
    private String paymentChannel;
    private String BatchNo;
    @ApiModelProperty(hidden = true)
    private String serverId;
    @ApiModelProperty(value = "打款账户id")
    private String accountId;

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }
}
