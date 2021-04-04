package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商户充值账户信息
 * @author Ljd
 * @date 2020/8/11
 */
@ApiModel(description = "商户充值账户信息")
public class RechargeAccountInfoDTO {

    @ApiModelProperty("收款户名")
    private String accountName;
    @ApiModelProperty("收款账号")
    private String accountNo;
    @ApiModelProperty("收款银行")
    private String bankName;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
