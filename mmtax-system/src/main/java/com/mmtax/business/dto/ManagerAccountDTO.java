package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/18 17:20
 */
@ApiModel(description = "商户账户列表")
public class ManagerAccountDTO extends BaseEntity {

    @ApiModelProperty("账户ID")
    private String id;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "商户号")
    private String merchantNo;
    @ApiModelProperty(value = "商户手机号")
    private String merchantMobile;
    @ApiModelProperty(value = "账户余额")
    private String merchantAmount;
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

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantMobile() {
        return merchantMobile;
    }

    public void setMerchantMobile(String merchantMobile) {
        this.merchantMobile = merchantMobile;
    }

    public String getMerchantAmount() {
        return merchantAmount;
    }

    public void setMerchantAmount(String merchantAmount) {
        this.merchantAmount = merchantAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "ManagerAccountDTO{" +
                "id='" + id + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", merchantMobile='" + merchantMobile + '\'' +
                ", merchantAmount='" + merchantAmount + '\'' +
                ", saleName='" + saleName + '\'' +
                ", saleId='" + saleId + '\'' +
                '}';
    }
}
