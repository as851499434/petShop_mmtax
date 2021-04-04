package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/15 9:39
 */
@ApiModel(description = "商户批量打款记录")
public class MerchantBatchPaymentRecordDTO {

    @ApiModelProperty(value = "记录id,隐藏字段")
    private Integer id;
    @ApiModelProperty(value = "商户打款请求金额")
    private String amount;
    @ApiModelProperty(value = "商户实际打款金额")
    private String actualAmount;
    @ApiModelProperty(value = "批量打款订单编号")
    private String trxExternalNo;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "批次号")
    private String batchNo;
    @ApiModelProperty(value = "打款渠道BANK-银行ALIPAY-支付宝WECHAT-微信")
    private String paymentChannel;
    @ApiModelProperty(value = "打款记录数量")
    private Integer count;
    @ApiModelProperty(value = "0-未处理1-已处理")
    private Integer status;
    @ApiModelProperty(value = "创建者")
    private String creater;
    @ApiModelProperty(value = "操作者")
    private String operator;
    @ApiModelProperty(value = "处理成功数量")
    private Integer successCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    @Override
    public String toString() {
        return "MerchantBatchPaymentRecordDTO{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", actualAmount='" + actualAmount + '\'' +
                ", trxExternalNo='" + trxExternalNo + '\'' +
                ", createTime='" + createTime + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", paymentChannel='" + paymentChannel + '\'' +
                ", count=" + count +
                ", status=" + status +
                ", creater='" + creater + '\'' +
                ", operator='" + operator + '\'' +
                ", successCount=" + successCount +
                '}';
    }
}
