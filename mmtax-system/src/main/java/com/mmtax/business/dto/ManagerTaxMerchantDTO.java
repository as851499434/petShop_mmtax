package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/15 9:08
 */
@ApiModel(description = "后台完税证明商户信息")
public class ManagerTaxMerchantDTO  {

    @ApiModelProperty(value = "商户id")
    private Integer merchantId;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "商户编码")
    private String merchantCode;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }
}
