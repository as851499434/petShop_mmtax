package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 模糊匹配DTO
 *
 * @author yuanligang
 * @date 2019/8/7
 */
public class FuzzyMatchingDTO {
    @ApiModelProperty("商户名称")
    private String merchantName;
    @ApiModelProperty("商户ID")
    private Integer merchantId;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return "FuzzyMatchingDTO{" +
                "merchantName='" + merchantName + '\'' +
                ", merchantId=" + merchantId +
                '}';
    }
}
