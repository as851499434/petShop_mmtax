package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 首页代付金额展示
 * @author yuanligang
 * @date 2019/7/16
 */
public class MainPagePayDTO {

    /**
     * 年度代付总金额
     */
    @ApiModelProperty(value = "年度代付总金额")
    private BigDecimal yearAmount;

    /**
     *税务抵扣
     */
    @ApiModelProperty(value = "税务抵扣")
    private  BigDecimal tax;

    public BigDecimal getYearAmount() {
        return yearAmount;
    }

    public void setYearAmount(BigDecimal yearAmount) {
        this.yearAmount = yearAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "MainPagePayDTO{" +
                "yearAmount=" + yearAmount +
                ", tax=" + tax +
                '}';
    }
}
