package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 充值接口模拟测试用
 *
 * @author yuanligang
 * @date 2019/8/15
 */
public class RechargeSimulationDTO {
    @ApiModelProperty("商户id")
    private Integer merchantId;
    @ApiModelProperty("充值金额")
    private BigDecimal amount;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
