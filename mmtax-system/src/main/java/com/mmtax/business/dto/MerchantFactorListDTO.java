package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/12 14:37
 */
@ApiModel(description = "要素认证订单")
public class MerchantFactorListDTO {
    @ApiModelProperty(value = "账单id，隐藏字段")
    private Integer id;
    @ApiModelProperty(value = "账单编号")
    private String orderNo;
    @ApiModelProperty(value = "商户平台")
    private String merchantName;
    @ApiModelProperty(value = "订单笔数")
    private Integer orderNum;
    @ApiModelProperty(value = "服务费实收金额")
    private String serviceAmount;
    @ApiModelProperty(value = "冲补金额")
    private String offsetAmount;
    @ApiModelProperty(value = "订单总额")
    private String totalAmount;
    @ApiModelProperty(value = "账单日期")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public String getOffsetAmount() {
        return offsetAmount;
    }

    public void setOffsetAmount(String offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
