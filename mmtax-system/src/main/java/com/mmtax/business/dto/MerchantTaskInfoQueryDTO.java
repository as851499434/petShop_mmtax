package com.mmtax.business.dto;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/10/15 14:53
 */
public class MerchantTaskInfoQueryDTO {
    /** 发任务方 */
    private String merchantName;
    /** 承接方 */
    private String taxSourceName;
    /** 任务名称 */
    private String taskName;
    /** 完成人 */
    private String name;
    /** 任务单状态 */
    private String taskStatus;
    /** 起始日期 */
    private  String startDate;
    /** 结束日期 */
    private  String endDate;
    /** 商户Id */
    private  String merchantId;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTaxSourceName() {
        return taxSourceName;
    }

    public void setTaxSourceName(String taxSourceName) {
        this.taxSourceName = taxSourceName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
