package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/22 11:24
 */
@ApiModel(description = "首页月度累计交易金额")
public class ManagerIndexYearDataDTO {
    @ApiModelProperty(value = "月份")
    private String month;

    @ApiModelProperty(value = "金额")
    private String amount;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ManagerIndexYearDataDTO{" +
                "month='" + month + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
