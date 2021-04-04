package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

public class QueryCustomerWithdrawInfoDTO {
    @ApiModelProperty(value = "提现渠道")
    private String withdrawChannel;
    @ApiModelProperty(value = "提现状态")
    private String withdrawStatus;
    @ApiModelProperty(value = "姓名")
    private String memberName;
    @ApiModelProperty(value = "灵工号")
    private String customerNo;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "提现流水号")
    private String withdrawSerialNum;
    @ApiModelProperty(value = "开始时间")
    private String startDate;
    @ApiModelProperty(value = "结束时间")
    private String endDate;

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

    public String getWithdrawSerialNum() {
        return withdrawSerialNum;
    }

    public void setWithdrawSerialNum(String withdrawSerialNum) {
        this.withdrawSerialNum = withdrawSerialNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getWithdrawChannel() {
        return withdrawChannel;
    }

    public void setWithdrawChannel(String withdrawChannel) {
        this.withdrawChannel = withdrawChannel;
    }

    public String getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(String withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    @Override
    public String toString() {
        return "QueryCustomerWithdrawInfoDTO{" +
                "withdrawChannel='" + withdrawChannel + '\'' +
                ", withdrawStatus='" + withdrawStatus + '\'' +
                ", memberName='" + memberName + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", mobile='" + mobile + '\'' +
                ", withdrawSerialNum='" + withdrawSerialNum + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
