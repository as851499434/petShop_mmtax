package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/28 10:39
 */
public class ManagerInvoiceAddressDTO extends BaseEntity {
    @ApiModelProperty(value = "邮寄地址ID")
    private Integer id;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址", required = true)
    private String address;
    /**
     * 收件人姓名
     */
    @ApiModelProperty(value = "收件人姓名", required = true)
    private String addresseeName;
    /**
     * 联系人电话
     */
    @ApiModelProperty(value = "联系人电话", required = true)
    private String mobile;
    @ApiModelProperty(value = "所属销售", required = true)
    private String saleName;
    @ApiModelProperty(hidden = true)
    private String saleId;
    @ApiModelProperty(value = "商户名称", required = true)
    private String merchantName;
    @ApiModelProperty(value = "商户id")
    private Integer merchantId;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
