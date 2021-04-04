package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

public class ManagerMakeUpListTrxOrderDTO {
    @Excel(name = "收款户名")
    @ApiModelProperty(value = "收款户名")
    private String payeeName;

    @Excel(name = "收款账号")
    @ApiModelProperty(value = "收款账号")
    private String payeeBankNo;

    @Excel(name = "所属商户")
    @ApiModelProperty(value = "所属商户")
    private String merchantName;

    @Excel(name = "商户打款请求金额")
    @ApiModelProperty(value = "商户打款请求金额")
    private String amount;


    @Excel(name = "补交服务费")
    @ApiModelProperty(value = "补交服务费")
    private String chargeMakeUp;

    @Excel(name = "补交流水号")
    @ApiModelProperty(value = "补交流水号")
    private String makeUpSerialNum;
    @Excel(name = "订单流水号")
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;

    @Excel(name = "状态")
    @ApiModelProperty(value = "状态")
    private String status;


    @Excel(name = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private String createTime;



    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getChargeMakeUp() {
        return chargeMakeUp;
    }

    public void setChargeMakeUp(String chargeMakeUp) {
        this.chargeMakeUp = chargeMakeUp;
    }

    public String getMakeUpSerialNum() {
        return makeUpSerialNum;
    }

    public void setMakeUpSerialNum(String makeUpSerialNum) {
        this.makeUpSerialNum = makeUpSerialNum;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeBankNo() {
        return payeeBankNo;
    }

    public void setPayeeBankNo(String payeeBankNo) {
        this.payeeBankNo = payeeBankNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ManagerMakeUpListTrxOrderDTO{" +
                "payeeName='" + payeeName + '\'' +
                ", payeeBankNo='" + payeeBankNo + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", amount='" + amount + '\'' +
                ", chargeMakeUp='" + chargeMakeUp + '\'' +
                ", makeUpSerialNum='" + makeUpSerialNum + '\'' +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
