package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/22 19:58
 */
public class ManagerCustomerSupportDTO extends BaseEntity {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("商户名称")
    private String merchantName;
    /**
     * 客户经理
     */
    @ApiModelProperty("客户经理")
    private String customerManager;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String customerManagerMobile;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;
    /**
     * 客户成功经理
     */
    @ApiModelProperty("客户成功经理")
    private String successCustomerManager;
    /**
     * 客户成功经理手机号
     */
    @ApiModelProperty("客户成功经理手机号")
    private String successCustomerManagerMobile;
    /**
     * 客户成功经理邮箱
     */
    @ApiModelProperty("客户成功经理邮箱")
    private String successCustomerEmail;
    /**
     * 商户支持邮箱
     */
    @ApiModelProperty("商户支持邮箱")
    private String businessSupport;

    @ApiModelProperty("销售名字")
    private String saleName;
    /**
     * 所属销售id
     */
    @ApiModelProperty(hidden = true)
    private String saleId;

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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager;
    }

    public String getCustomerManagerMobile() {
        return customerManagerMobile;
    }

    public void setCustomerManagerMobile(String customerManagerMobile) {
        this.customerManagerMobile = customerManagerMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuccessCustomerManager() {
        return successCustomerManager;
    }

    public void setSuccessCustomerManager(String successCustomerManager) {
        this.successCustomerManager = successCustomerManager;
    }

    public String getSuccessCustomerManagerMobile() {
        return successCustomerManagerMobile;
    }

    public void setSuccessCustomerManagerMobile(String successCustomerManagerMobile) {
        this.successCustomerManagerMobile = successCustomerManagerMobile;
    }

    public String getSuccessCustomerEmail() {
        return successCustomerEmail;
    }

    public void setSuccessCustomerEmail(String successCustomerEmail) {
        this.successCustomerEmail = successCustomerEmail;
    }

    public String getBusinessSupport() {
        return businessSupport;
    }

    public void setBusinessSupport(String businessSupport) {
        this.businessSupport = businessSupport;
    }

    @Override
    public String toString() {
        return "ManagerCustomerSupportDTO{" +
                "id=" + id +
                ", merchantName='" + merchantName + '\'' +
                ", customerManager='" + customerManager + '\'' +
                ", customerManagerMobile='" + customerManagerMobile + '\'' +
                ", email='" + email + '\'' +
                ", successCustomerManager='" + successCustomerManager + '\'' +
                ", successCustomerManagerMobile='" + successCustomerManagerMobile + '\'' +
                ", successCustomerEmail='" + successCustomerEmail + '\'' +
                ", businessSupport='" + businessSupport + '\'' +
                '}';
    }
}
