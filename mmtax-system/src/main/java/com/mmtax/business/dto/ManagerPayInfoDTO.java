package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ManagerPayInfoDTO {


    @ApiModelProperty(value = "商户")
    private Integer id;



    @Excel(name = "流水号")
    @ApiModelProperty(value = "流水号")
    private String orderSerialNum;

//    @Excel(name = "付款金额")
    @ApiModelProperty(value = "付款金额")
    private Double amount;

    @Excel(name = "付款金额")
    @ApiModelProperty(value = "付款金额")
    private String  excelAmount;

    @Excel(name = "商户名称")
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @Excel(name = "付款渠道",readConverterExp = "ONLINE=线上,UNDERLINE=线下")
    @ApiModelProperty(value = "付款渠道")
    private String  rechargeChannel ;

    @Excel(name = "付款状态",readConverterExp = "SUCCESS=成功,FAIL=失败,ALLPYING=申请中")
    @ApiModelProperty(value = "付款状态")
    private String status;

    @Excel(name = "付款时间")
    @ApiModelProperty(value = "付款时间")
    private String  createTime;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public Double getAmount() {
        return amount;
    }

    public String getExcelAmount() {
        return excelAmount;
    }

    public void setExcelAmount(String excelAmount) {
        this.excelAmount = excelAmount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRechargeChannel() {
        return rechargeChannel;
    }

    public void setRechargeChannel(String rechargeChannel) {
        this.rechargeChannel = rechargeChannel;
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
}
