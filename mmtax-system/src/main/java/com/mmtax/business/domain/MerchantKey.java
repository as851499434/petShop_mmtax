package com.mmtax.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 商户秘钥表 tbl_merchant_key
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Table(name = "tbl_merchant_key")
public class MerchantKey {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("记录id")
    private Integer id;
    /**
     * 商户id
     */
    @ApiModelProperty(hidden = true)
    private Integer merchantId;
    /**
     * 接口秘钥
     */
    @ApiModelProperty("appkey")
    private String appKey;
    /**
     * 3重加密之后的秘钥
     */
    @ApiModelProperty("3des Key")
    private String desKey;
    /**
     * ip白名单
     */
    @ApiModelProperty("ip白名单")
    private String whiteUrl;
    /**
     * 订单回调地址
     */
    @ApiModelProperty("订单回调地址")
    private String callBackAddress;
    /**
     *
     */
    @JsonIgnore
    @Transient
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
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

    public String getWhiteUrl() {
        return whiteUrl;
    }

    public void setWhiteUrl(String whiteUrl) {
        this.whiteUrl = whiteUrl;
    }

    public String getCallBackAddress() {
        return callBackAddress;
    }

    public void setCallBackAddress(String callBackAddress) {
        this.callBackAddress = callBackAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("merchantId" , getMerchantId())
                .append("whiteUrl", getWhiteUrl())
                .append("callBackAddress", getCallBackAddress())
                .append("appKey" , getAppKey())
                .append("desKey" , getDesKey())
                .append("providerId" , getProviderId())
                .append("createTime" , getCreateTime())
                .append("updateTime" , getUpdateTime())
                .toString();
    }
}
