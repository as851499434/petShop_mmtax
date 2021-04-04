package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/28 14:12
 */
public class ManagerSaleStatisticsDTO {
    @ApiModelProperty("今日交易金额")
    private String todayAmount;

    @ApiModelProperty("今日服务费")
    private String todayServiceAmount;

    @ApiModelProperty("今日订单笔数")
    private Integer todayOrderCount;

    @ApiModelProperty("当月交易金额")
    private String monthAmount;

    @ApiModelProperty("当月服务费金额")
    private String monthServiceAmount;

    @ApiModelProperty("当月订单笔数")
    private Integer monthOrderCount;

    public String getTodayAmount() {
        return todayAmount;
    }

    public void setTodayAmount(String todayAmount) {
        this.todayAmount = todayAmount;
    }

    public String getTodayServiceAmount() {
        return todayServiceAmount;
    }

    public void setTodayServiceAmount(String todayServiceAmount) {
        this.todayServiceAmount = todayServiceAmount;
    }

    public String getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(String monthAmount) {
        this.monthAmount = monthAmount;
    }

    public String getMonthServiceAmount() {
        return monthServiceAmount;
    }

    public void setMonthServiceAmount(String monthServiceAmount) {
        this.monthServiceAmount = monthServiceAmount;
    }

    public Integer getTodayOrderCount() {
        return todayOrderCount;
    }

    public void setTodayOrderCount(Integer todayOrderCount) {
        this.todayOrderCount = todayOrderCount;
    }

    public Integer getMonthOrderCount() {
        return monthOrderCount;
    }

    public void setMonthOrderCount(Integer monthOrderCount) {
        this.monthOrderCount = monthOrderCount;
    }

    @Override
    public String toString() {
        return "ManagerSaleStatisticsDTO{" +
                "todayAmount='" + todayAmount + '\'' +
                ", todayServiceAmount='" + todayServiceAmount + '\'' +
                ", todayOrderCount=" + todayOrderCount +
                ", monthAmount='" + monthAmount + '\'' +
                ", monthServiceAmount='" + monthServiceAmount + '\'' +
                ", monthOrderCount=" + monthOrderCount +
                '}';
    }
}
