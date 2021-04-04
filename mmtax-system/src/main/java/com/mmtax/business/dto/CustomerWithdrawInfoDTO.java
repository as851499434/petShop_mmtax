package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

public class CustomerWithdrawInfoDTO {
    @ApiModelProperty(value = "提现渠道")
    private String withdrawChannel;


    @ApiModelProperty(value = "微信号")
    private String wechatNumber;

    @ApiModelProperty(value = "微信名称")
    private String wechatName;

    @ApiModelProperty(value = "提现状态")
    private String withdrawStatus;

    @Excel(name = "姓名")
    @ApiModelProperty(value = "姓名")
    private String memberName;

    @Excel(name = "灵工号")
    @ApiModelProperty(value = "灵工号")
    private String customerNo;

    @Excel(name = "手机号")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @Excel(name = "提现金额")
    @ApiModelProperty(value = "提现金额")
    private String amount;

    @Excel(name = "提现账号")
    @ApiModelProperty(value = "提现账号")
    private String withdrawAccount;

    @Excel(name = "提现流水号")
    @ApiModelProperty(value = "提现流水号")
    private String withdrawSerialNum;

    @Excel(name = "提现时间")
    @ApiModelProperty(value = "提现时间")
    private String createTime;

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getWithdrawChannel() {
        return withdrawChannel;
    }

    public void setWithdrawChannel(String withdrawChannel) {
        this.withdrawChannel = withdrawChannel;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getWithdrawAccount() {
        return withdrawAccount;
    }

    public void setWithdrawAccount(String withdrawAccount) {
        this.withdrawAccount = withdrawAccount;
    }

    public String getWithdrawSerialNum() {
        return withdrawSerialNum;
    }

    public void setWithdrawSerialNum(String withdrawSerialNum) {
        this.withdrawSerialNum = withdrawSerialNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(String withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    @Override
    public String toString() {
        return "CustomerWithdrawInfoDTO{" +
                "withdrawChannel='" + withdrawChannel + '\'' +
                ", wechatNumber='" + wechatNumber + '\'' +
                ", wechatName='" + wechatName + '\'' +
                ", withdrawStatus='" + withdrawStatus + '\'' +
                ", memberName='" + memberName + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", mobile='" + mobile + '\'' +
                ", amount='" + amount + '\'' +
                ", withdrawAccount='" + withdrawAccount + '\'' +
                ", withdrawSerialNum='" + withdrawSerialNum + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
