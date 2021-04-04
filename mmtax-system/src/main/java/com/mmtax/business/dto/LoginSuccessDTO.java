package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/12 14:20
 */
@ApiModel(value = "登录成功欢迎语")
public class LoginSuccessDTO {
    @ApiModelProperty(value = "商户id")
    private String merchantId;
    @ApiModelProperty(value = "欢迎语")
    private String welCome;
    @ApiModelProperty(value = "商户编码")
    private String merchantCode;
    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "商户注册渠道YUNZB-云众包，TIANJIN-天津")
    private String channel;
    @ApiModelProperty(value = "税源地id")
    private Integer taxSourceId;
    @ApiModelProperty(value = "税源地名称")
    private String taxSourceName;
    @ApiModelProperty(value = "银行渠道是否支持")
    private Integer bankChannel;
    @ApiModelProperty(value = "支付宝渠道是否支持")
    private Integer alipayChannel;
    @ApiModelProperty(value = "微信渠道是否支持")
    private Integer wechatChannel;
    @ApiModelProperty(value = "推广列表是否支持")
    private Integer promotion;
    @ApiModelProperty(value = "签约开关是否开启")
    private Integer signSwitch;

    public Integer getTaxSourceId() {
        return taxSourceId;
    }

    public void setTaxSourceId(Integer taxSourceId) {
        this.taxSourceId = taxSourceId;
    }

    public String getTaxSourceName() {
        return taxSourceName;
    }

    public void setTaxSourceName(String taxSourceName) {
        this.taxSourceName = taxSourceName;
    }

    public Integer getBankChannel() {
        return bankChannel;
    }

    public void setBankChannel(Integer bankChannel) {
        this.bankChannel = bankChannel;
    }

    public Integer getAlipayChannel() {
        return alipayChannel;
    }

    public void setAlipayChannel(Integer alipayChannel) {
        this.alipayChannel = alipayChannel;
    }

    public Integer getWechatChannel() {
        return wechatChannel;
    }

    public void setWechatChannel(Integer wechatChannel) {
        this.wechatChannel = wechatChannel;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getWelCome() {
        return welCome;
    }

    public void setWelCome(String welCome) {
        this.welCome = welCome;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getPromotion() {
        return promotion;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }

    public Integer getSignSwitch() {
        return signSwitch;
    }

    public void setSignSwitch(Integer signSwitch) {
        this.signSwitch = signSwitch;
    }

    @Override
    public String toString() {
        return "LoginSuccessDTO{" +
                "merchantId='" + merchantId + '\'' +
                ", welCome='" + welCome + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", token='" + token + '\'' +
                ", channel='" + channel + '\'' +
                ", taxSourceId=" + taxSourceId +
                ", taxSourceName='" + taxSourceName + '\'' +
                ", bankChannel=" + bankChannel +
                ", alipayChannel=" + alipayChannel +
                ", wechatChannel=" + wechatChannel +
                ", promotion=" + promotion +
                ", signSwitch=" + signSwitch +
                '}';
    }
}
