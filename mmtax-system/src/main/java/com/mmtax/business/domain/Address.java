package com.mmtax.business.domain;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 邮寄地址表 tbl_address
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Table(name = "tbl_address")
public class Address {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    /**
     * 0-正常1-删除
     */
    @ApiModelProperty(hidden = true)
    private Integer status;
    /**
     * 商户id
     */
    @ApiModelProperty(hidden = true)
    private Integer merchantId;
    /**
     *
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private Integer providerId;
    /**创建时间
     *
     */
    @ApiModelProperty(hidden = true)
    private Date createTime;
    /**
     *更新时间
     */
    @ApiModelProperty(hidden = true)
    private Date updateTime;

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

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("address" , getAddress())
                .append("addresseeName" , getAddresseeName())
                .append("mobile" , getMobile())
                .append("status", getStatus())
                .append("merchantId" , getMerchantId())
                .append("providerId" , getProviderId())
                .append("createTime" , getCreateTime())
                .append("updateTime" , getUpdateTime())
                .toString();
    }
}
