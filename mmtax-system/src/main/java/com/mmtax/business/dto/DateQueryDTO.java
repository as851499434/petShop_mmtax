package com.mmtax.business.dto;

/**
 * 封装起止时间搜索条件值
 * @author Ljd
 * @date 2020/7/10
 */
public class DateQueryDTO {

    private String startDate;

    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
