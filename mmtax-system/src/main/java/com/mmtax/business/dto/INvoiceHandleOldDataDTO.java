package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("处理tbl_invoice_info表的旧数据")
public class INvoiceHandleOldDataDTO {
    @ApiModelProperty("商户id")
    private Integer merchantId;
    @ApiModelProperty("银行卡号")
    private String bankNumber;
    @ApiModelProperty("开户行")
    private String bankName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    @Override
    public String toString() {
        return "INvoiceHandleOldDataDTO{" +
                "merchantId=" + merchantId +
                ", bankNumber='" + bankNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
