package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/6 14:30
 */
@ApiModel(description = "订单参数")
public class BatchOrderDTO {
    @ApiModelProperty(value = "批次号")
    private String batchNo;
    @ApiModelProperty(value = "商户id")
    private Integer merchantId;
    private Integer batchRecordId;
    private PaymentInfoDTO paymentInfo;

    public PaymentInfoDTO getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoDTO paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Integer getBatchRecordId() {
        return batchRecordId;
    }

    public void setBatchRecordId(Integer batchRecordId) {
        this.batchRecordId = batchRecordId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
}
