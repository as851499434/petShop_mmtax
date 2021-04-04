package com.mmtax.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 客户支持表 tbl_customer_support
 *
 * @author meimiao
 * @date 2019-08-21
 */
@Table(name = "tbl_customer_support")
public class CustomerSupport {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    /**
     * 商户id
     */
    @JsonIgnore
    private Integer merchantId;
    /**
     *
     */
    @Transient
    @JsonIgnore
    private Integer providerId;
    /**
     *
     */
    @JsonIgnore
    private Date createTime;
    /**
     *
     */
    @JsonIgnore
    private Date updateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager;
    }

    public String getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManagerMobile(String customerManagerMobile) {
        this.customerManagerMobile = customerManagerMobile;
    }

    public String getCustomerManagerMobile() {
        return customerManagerMobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSuccessCustomerManager(String successCustomerManager) {
        this.successCustomerManager = successCustomerManager;
    }

    public String getSuccessCustomerManager() {
        return successCustomerManager;
    }

    public void setSuccessCustomerManagerMobile(String successCustomerManagerMobile) {
        this.successCustomerManagerMobile = successCustomerManagerMobile;
    }

    public String getSuccessCustomerManagerMobile() {
        return successCustomerManagerMobile;
    }

    public void setSuccessCustomerEmail(String successCustomerEmail) {
        this.successCustomerEmail = successCustomerEmail;
    }

    public String getSuccessCustomerEmail() {
        return successCustomerEmail;
    }

    public void setBusinessSupport(String businessSupport) {
        this.businessSupport = businessSupport;
    }

    public String getBusinessSupport() {
        return businessSupport;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("customerManager", getCustomerManager())
                .append("customerManagerMobile", getCustomerManagerMobile())
                .append("email", getEmail())
                .append("successCustomerManager", getSuccessCustomerManager())
                .append("successCustomerManagerMobile", getSuccessCustomerManagerMobile())
                .append("successCustomerEmail", getSuccessCustomerEmail())
                .append("businessSupport", getBusinessSupport())
                .append("merchantId", getMerchantId())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
