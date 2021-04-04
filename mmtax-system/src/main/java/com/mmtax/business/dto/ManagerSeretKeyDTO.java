package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/19 17:31
 */
@ApiModel(description = "商户秘钥")
public class ManagerSeretKeyDTO  extends BaseEntity {
    @ApiModelProperty(value = "秘钥id")
    private Integer id;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "appkey")
    private String appKey;
    @ApiModelProperty(value = "3deskey")
    private String desKey;
    @ApiModelProperty(value = "商户代码")
    private String merchantCode;
    @ApiModelProperty(value = "起始时间")
    private String startDate;
    @ApiModelProperty(value = "结束时间")
    private String endDate;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "所属销售" , required = true)
    private String saleName;
    @ApiModelProperty(hidden = true)
    private String saleId;
    public String getMerchantName() {
        return merchantName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
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

    @Override
    public String
    toString() {
        return "ManagerSeretKeyDTO{" +
                "id=" + id +
                ", merchantName='" + merchantName + '\'' +
                ", appKey='" + appKey + '\'' +
                ", desKey='" + desKey + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
