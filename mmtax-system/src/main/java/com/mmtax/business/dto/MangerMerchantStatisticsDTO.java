package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/28 14:57
 */
public class MangerMerchantStatisticsDTO {
    @ApiModelProperty("交易金额")
    private String amount;
    @ApiModelProperty("商户名称")
    private String merchantName;
    @ApiModelProperty("时间")
    private String date;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
