package com.mmtax.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/13 13:34
 */
@ApiModel(value = "批量打款记录")
public class ManagerBatchPaymentRecordDTO extends BaseEntity {

    @ApiModelProperty(value = "批量代付id")
    private Integer id;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "请求打款总金额")
    private String amount;
    @ApiModelProperty(value = "订单流水号")
    private String trxExternalNo;
    @ApiModelProperty(value = "打款渠道")
    private String paymentChannel;
    @ApiModelProperty(value = "0-待打款 3-已取消 2-打款中 1-已完成")
    private String status;
    @ApiModelProperty(value = "订单创建时间")
    private String createTime;
    @ApiModelProperty(value = "批次号")
    private String batchNo;
    @ApiModelProperty(value = "实际打款金额")
    private String actualAmount;
    @ApiModelProperty(value = "打款记录数")
    private Integer paymentCount;
    @ApiModelProperty(value = "创建者")
    private String creater;
    @ApiModelProperty(value = "操作者")
    private String operator;
    @ApiModelProperty(value = "商户ID")
    private Integer merchantId;
    @ApiModelProperty(value = "所属销售", required = true)
    private String saleName;
    @ApiModelProperty(hidden = true)
    private String saleId;
    @JsonIgnore
    private String startDate;
    @JsonIgnore
    private String endDate;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
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

    public String getTrxExternalNo() {
        return trxExternalNo;
    }

    public void setTrxExternalNo(String trxExternalNo) {
        this.trxExternalNo = trxExternalNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(Integer paymentCount) {
        this.paymentCount = paymentCount;
    }


    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }
}
