package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 商户状态DTO
 *
 * @author yuanligang
 * @date 2019/8/7
 */
public class MerchantStatusDTO {
    @ApiModelProperty("商户ID")
    private List<Integer> merchantId;
    @ApiModelProperty("商户账户状态")
    private String status;

    public List<Integer> getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(List<Integer> merchantId) {
        this.merchantId = merchantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MerchantStatusDTO{" +
                "merchantId=" + merchantId +
                ", status='" + status + '\'' +
                '}';
    }
}
