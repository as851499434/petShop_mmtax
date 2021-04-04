package com.mmtax.business.dto;

/**
 * 商户合作信息列表查询DTO
 * @author yuanligang
 * @date 2019/7/19
 */
public class ManagerCooperationQueryDTO {
    /**
     * 联系人手机号
     */
    private  String contactsMobile;
    /**
     * 商户名称
     */
    private  String merchantName;
    /**
     * 起始日期
     */
    private  String startDate;
    /**
     * 结束日期
     */
    private  String endDate;

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    @Override
    public String toString() {
        return "ManagerCooperationQuery{" +
                "contactsMobile='" + contactsMobile + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
