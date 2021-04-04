package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/15 19:14
 */
public class MerchantRechargeRecordDTO {

    @Excel(name = "账户时间")
    @ApiModelProperty(value = "账户时间")
    private String createTime;
    @Excel(name = "审核时间")
    @ApiModelProperty(value = "审核时间")
    private String updateTime;
    @Excel(name = "订单流水号")
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;
    @Excel(name = "充值金额")
    @ApiModelProperty(value = "充值金额")
    private String amount;
    @Excel(name = "实际到账金额")
    @ApiModelProperty(value = "实际到账金额")
    private String actualAmount;
    @Excel(name = "充值类型", readConverterExp = "ONLINE=线上,UNDERLINE=线下")
    @ApiModelProperty("充值类型 ONLINE-线上UNDERLINE-线下")
    private String rechargeType;
    @Excel(name = "充值状态", readConverterExp = "充值状态=SUCCESS,成功FAIL=失败")
    @ApiModelProperty("充值状态SUCCESS-成功FAIL-失败")
    private String status;
    @ApiModelProperty("充值渠道")
    private String rechargeChannel;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRechargeChannel() {
        return rechargeChannel;
    }

    public void setRechargeChannel(String rechargeChannel) {
        this.rechargeChannel = rechargeChannel;
    }

    @Override
    public String toString() {
        return "MerchantRechargeRecordDTO{" +
                "createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", amount='" + amount + '\'' +
                ", actualAmount='" + actualAmount + '\'' +
                ", rechargeType='" + rechargeType + '\'' +
                ", status='" + status + '\'' +
                ", rechargeChannel='" + rechargeChannel + '\'' +
                '}';
    }
}
