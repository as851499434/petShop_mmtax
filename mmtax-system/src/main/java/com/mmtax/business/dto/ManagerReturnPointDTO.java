package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 返点金额
 *
 * @author yuanligang
 * @date 2019/7/31
 */
public class ManagerReturnPointDTO {
    @ApiModelProperty("累计返点金额")
    private BigDecimal accumulatedReturnAmount;
    @ApiModelProperty("可用累计返点金额")
    private BigDecimal usableAccumulatedReturnAmount;

    public BigDecimal getAccumulatedReturnAmount() {
        return accumulatedReturnAmount;
    }

    public void setAccumulatedReturnAmount(BigDecimal accumulatedReturnAmount) {
        this.accumulatedReturnAmount = accumulatedReturnAmount;
    }

    public BigDecimal getUsableAccumulatedReturnAmount() {
        return usableAccumulatedReturnAmount;
    }

    public void setUsableAccumulatedReturnAmount(BigDecimal usableAccumulatedReturnAmount) {
        this.usableAccumulatedReturnAmount = usableAccumulatedReturnAmount;
    }

    @Override
    public String toString() {
        return "ManagerReturnPointDTO{" +
                "accumulatedReturnAmount=" + accumulatedReturnAmount +
                ", usableAccumulatedReturnAmount=" + usableAccumulatedReturnAmount +
                '}';
    }
}
