package com.mmtax.business.tianjindto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/26 2:17 下午
 */
public class TianJinSecretDTO {
    @ApiModelProperty("商户id")
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
     * 供应商 UUID
     */
    @ApiModelProperty("供应商 UUID")
    private String serverUserUuid;
    /**
     * 账户 UUID
     */
    @ApiModelProperty("账户 UUID")
    private String customerAccountUuid;

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

    public String getServerUserUuid() {
        return serverUserUuid;
    }

    public void setServerUserUuid(String serverUserUuid) {
        this.serverUserUuid = serverUserUuid;
    }

    public String getCustomerAccountUuid() {
        return customerAccountUuid;
    }

    public void setCustomerAccountUuid(String customerAccountUuid) {
        this.customerAccountUuid = customerAccountUuid;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
    @Override
    public String toString() {
        return "TianJinSecretDTO{" +
                "merchantId=" + merchantId +
                ", appKey='" + appKey + '\'' +
                ", desKey='" + desKey + '\'' +
                ", whiteUrl='" + whiteUrl + '\'' +
                ", callBackAddress='" + callBackAddress + '\'' +
                ", serverUserUuid='" + serverUserUuid + '\'' +
                ", customerAccountUuid='" + customerAccountUuid + '\'' +
                '}';
    }
}
