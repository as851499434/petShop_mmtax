package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 后台商户信息查询DTO
 *
 * @author yuanligang
 * @date 2019/7/19
 */
public class ManagerMerchantQueryDTO   extends BaseEntity {
    /**
     * 联系人手机号
     */
    @ApiModelProperty("联系人手机号")
    private String contactsMobile;
    /**
     * 商户名称
     */
    @ApiModelProperty("商户名称")
    private String merchantName;
    /**
     * 起始日期
     */
    @ApiModelProperty("起始日期")
    private String startDate;
    /**
     * 结束日期
     */
    @ApiModelProperty("结束日期")
    private String endDate;
    @ApiModelProperty("销售名字")
    private String saleName;
    /**
     * 所属销售id
     */
    @ApiModelProperty(hidden = true)
    private String saleId;
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

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    @Override
    public String toString() {
        return "ManagerMerchantQueryDTO{" +
                "contactsMobile='" + contactsMobile + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", saleName='" + saleName + '\'' +
                '}';
    }
}

