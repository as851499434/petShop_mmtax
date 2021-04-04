package com.mmtax.business.tianjindto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/24 9:12 上午
 */
public class TianJinAccountInfoDTO {
    @ApiModelProperty("账户 uuid")
    private String account_uuid;
    @ApiModelProperty("账户号")
    private String account_no;
    @ApiModelProperty("供应商实体户名称（平安户/网商户返回）")
    private String Service_mother_bizeff_account_name;
    @ApiModelProperty("账户名称")
    private String account_name;
    @ApiModelProperty("账户类型, PinganMother = 30 #平安户 PinganSon = 35#平安子户 MyBankMother - 40 #网商户 MyBankSon - 45 #网商子户_")
    private String account_type;
    @ApiModelProperty("银行简称")
    private String bank_code;
    @ApiModelProperty("账户状态， 1 启用 2 禁用")
    private String account_status;

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

    public String getService_mother_bizeff_account_name() {
        return Service_mother_bizeff_account_name;
    }

    public void setService_mother_bizeff_account_name(String service_mother_bizeff_account_name) {
        Service_mother_bizeff_account_name = service_mother_bizeff_account_name;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
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

    @Override
    public String toString() {
        return "TianJinAccountInfoDTO{" +
                "account_uuid='" + account_uuid + '\'' +
                ", account_no='" + account_no + '\'' +
                ", Service_mother_bizeff_account_name='" + Service_mother_bizeff_account_name + '\'' +
                ", account_name='" + account_name + '\'' +
                ", account_type='" + account_type + '\'' +
                ", bank_code='" + bank_code + '\'' +
                ", account_status='" + account_status + '\'' +
                '}';
    }
}
