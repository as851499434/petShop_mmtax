package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 后台查询DTO
 * @author yuanligang
 * @date 2019/7/24
 */
public class ManagerAccountRecordDTO  extends BaseEntity {

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "商户号")
    private String merchantCode;

    @ApiModelProperty(value = "创建开始时间")
    private String startDate;

    @ApiModelProperty(value = "创建结束时间")
    private String endDate;

    @ApiModelProperty(value = "交易类型")
    private Integer type;

    @ApiModelProperty(value = "流水号")
    private String orderSerialNum;
    @ApiModelProperty(value = "销售名字")
    private String saleName;
    @ApiModelProperty(hidden = true)
    private String saleId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
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
        return "ManagerAccountRecordDTO{" +
                "merchantName='" + merchantName + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", type=" + type +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", saleName='" + saleName + '\'' +
                ", saleId='" + saleId + '\'' +
                '}';
    }
}
