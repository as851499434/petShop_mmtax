package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/24 9:22
 */
public class PaymentBalanceDTO extends PaymentResultDTO {

    @ApiModelProperty(value = "余额")
    private String balance;


    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
