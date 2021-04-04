package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 发票开具充值记录依据
 * @author yuanligang
 * @date 2019/7/20
 */
public class MerchantRechargeRecordInvoiceDTO {

    @ApiModelProperty(value = "记录ID")
    private Integer id;
    @ApiModelProperty(value = "账户时间")
    private String createTime;
    @ApiModelProperty(value = "审核时间")
    private String updateTime;
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;
    @ApiModelProperty(value = "充值金额")
    private String amount;
    @ApiModelProperty(value = "实际到账金额")
    private String actualAmount;
    @ApiModelProperty(value = "充值状态SUCCESS-成功FAIL-失败")
    private String status;
    @ApiModelProperty(value = "开票状态 0-未开发票1-已开发票")
    private String invoiceStatus;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MerchantRechargeRecordInvoiceDTO{" +
                "id=" + id +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", amount='" + amount + '\'' +
                ", actualAmount='" + actualAmount + '\'' +
                ", status='" + status + '\'' +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                '}';
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
}
