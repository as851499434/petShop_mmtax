package com.mmtax.business.tianjindto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/26 11:23 上午
 */
public class TianJinAccountDTO {
    /**
     * 账户Id
     */
    @ApiModelProperty("账户Id")
    private String account_uuid;
    /**
     *账户编号
     */
    @ApiModelProperty("账户编号")
    private String account_no;
    /**
     *账户名称
     */
    @ApiModelProperty("账户名称")
    private String account_name;
    /**
     *银行编码
     */
    @ApiModelProperty("银行编码")
    private String bank_code;
    /**
     *账户状态
     */
    @ApiModelProperty("账户状态1 启用 2 禁用")
    private String account_status;
    /**
     *
     */
    @ApiModelProperty("总额")
    private String balance_total;
    /**
     *
     */
    @ApiModelProperty("可用余额")
    private String balance_available;
    /**
     *
     */
    @ApiModelProperty("待结算金额")
    private String balance_tobe_settled;

    public String getAccount_uuid() {
        return account_uuid;
    }

    public void setAccount_uuid(String account_uuid) {
        this.account_uuid = account_uuid;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getAccount_status() {
        return account_status;
    }

    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    public String getBalance_total() {
        return balance_total;
    }

    public void setBalance_total(String balance_total) {
        this.balance_total = balance_total;
    }

    public String getBalance_available() {
        return balance_available;
    }

    public void setBalance_available(String balance_available) {
        this.balance_available = balance_available;
    }

    public String getBalance_tobe_settled() {
        return balance_tobe_settled;
    }

    public void setBalance_tobe_settled(String balance_tobe_settled) {
        this.balance_tobe_settled = balance_tobe_settled;
    }

    @Override
    public String toString() {
        return "TianJinAccountDTO{" +
                "account_uuid='" + account_uuid + '\'' +
                ", account_no='" + account_no + '\'' +
                ", account_name='" + account_name + '\'' +
                ", bank_code='" + bank_code + '\'' +
                ", account_status='" + account_status + '\'' +
                ", balance_total='" + balance_total + '\'' +
                ", balance_available='" + balance_available + '\'' +
                ", balance_tobe_settled='" + balance_tobe_settled + '\'' +
                '}';
    }
}
