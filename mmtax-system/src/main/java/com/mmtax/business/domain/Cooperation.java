package com.mmtax.business.domain;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 合作表 tbl_cooperation
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Table(name = "tbl_cooperation")
public class Cooperation {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;
    /**
     * 签约类型0-微信签约1-线上签约2-纸质签约3-线上签约(可打款)
     */
    @ApiModelProperty(value = "签约类型0-微信签约1-线上签约2-纸质签约3-线上签约(可打款)", required = true)
    private Integer signingType;
    /**
     * 0-手动调整
     */
    @ApiModelProperty(value = "0-手动调整", required = true)
    private Integer paymentAdjustType;
    /**
     * 0-只能结算
     */
    @ApiModelProperty(value = "0-智能结算", required = true)
    private Integer settleType;
    /**
     * 0-业务类型
     */
    @ApiModelProperty(value = "0-业务类型", required = true)
    private Integer businessType;
    /**
     * 0-实时付费
     */
    @ApiModelProperty(value = "0-实时付费", required = true)
    private Integer paymentModel;
    /**
     * 付款要求
     */
    @ApiModelProperty(value = "付款要求", required = true)
    private String paymentRequirement;
    /**
     * 商户单笔服务费费率
     */
    @ApiModelProperty(value = "商户单笔服务费费率", required = true)
    private BigDecimal singleServiceRate;

    /**
     * 0-内扣 1-外扣
     */
    @ApiModelProperty(value = "0-内扣 1-外扣", required = true)
    private Integer inOrOutDeduction;

    /**
     * 套餐id
     */
    @ApiModelProperty(value = "套餐", required = true)
    private Integer mealInfoId;
    /**
     * 用户服务费费率开关
     */
    @ApiModelProperty(value = "用户服务费费率开关", required = true)
    private Integer rateSwitch;

    /** 用户是否签约开关0-关，1-开 */
    @ApiModelProperty(value = "用户是否签约开关0-关，1-开", required = true)
    private Integer signSwitch;

    /** 用户静默签约开关0-关（非静默），1-开（静默） */
    private Integer silentSignSwitch;

    /** 大额费率开关 0关 1开*/
    @ApiModelProperty(value = "大额费率开关 0-关，1-开", required = true)
    private Integer bigRateSwitch;

    /** 商户单笔大额服务费费率 */
    @ApiModelProperty(value = "商户单笔大额服务费费率", required = true)
    private BigDecimal singleServiceBigRate;

    /**
     * BANK-银行ALIPAY-支付宝WECHAT-微信
     */
    @ApiModelProperty(value = "BANK-银行ALIPAY-支付宝WECHAT-微信", required = true)
    private String paymentChannel;
    /**
     * 用户单笔限额
     */
    @ApiModelProperty(value = "用户单笔限额", required = true)
    private BigDecimal singleQuota;
    /**
     * 银行渠道是否支持 0-否 1-是
     */
    @ApiModelProperty(value = "银行渠道是否支持 0-否 1-是", required = true)
    private Integer bankChannel;
    /**
     * 银行渠道单笔限额
     */
    @ApiModelProperty(value = "银行渠道单笔限额", required = true)
    private BigDecimal bankSingleQuota;
    /**
     * 支付宝渠道是否支持 0-否 1-是
     */
    @ApiModelProperty(value = "支付宝渠道是否支持 0-否 1-是", required = true)
    private Integer alipayChannel;
    /**
     * 支付宝渠道单笔限额
     */
    @ApiModelProperty(value = "支付宝渠道单笔限额", required = true)
    private BigDecimal alipaySingleQuota;
    /**
     * 微信渠道是否支持 0-否 1-是
     */
    @ApiModelProperty(value = "微信渠道是否支持 0-否 1-是", required = true)
    private Integer wechatChannel;
    /**
     * 微信渠道单笔限额
     */
    @ApiModelProperty(value = "微信渠道单笔限额", required = true)
    private BigDecimal wechatSingleQuota;
    /**
     * 是否推广 0-否 1-是
     */
    @ApiModelProperty(value = "用户推广开关 0-关 1-开", required = true)
    private Integer promotionSwitch;
    /**
     * 商户主键
     */
    @ApiModelProperty(hidden = true)
    private Integer merchantId;
    /** 是否为派单商户开关 0-关（非派单），1-开（派单） */
    @ApiModelProperty(value = "是否为派单商户开关 0-关（非派单），1-开（派单）", required = true)
    private Integer sendOrderSwitch;
    /** 派单模式 0-手动，1-自动 */
    @ApiModelProperty(value = "派单模式 0-手动，1-自动", required = true)
    private Integer sendOrderMode;

    @ApiModelProperty(value = "灵工提现开关", required = true)
    private Integer customerWithdrawSwitch;

    @ApiModelProperty(value = "异步通知开关", required = true)
    private Integer asynSwitch;

    @ApiModelProperty(value = "异步通知是否检验开关", required = true)
    private Integer asynSignSwitch;


    /**
     *
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private Integer providerId;
    /**
     *
     */
    @ApiModelProperty(hidden = true)
    private Date createTime;
    /**
     *
     */
    @ApiModelProperty(hidden = true)
    private Date updateTime;




    @ApiModelProperty(value = "打款模式：0-普通模式 1-自动模式", required = true)
    private Integer moneyModel;

    public Integer getInOrOutDeduction() {
        return inOrOutDeduction;
    }

    public void setInOrOutDeduction(Integer inOrOutDeduction) {
        this.inOrOutDeduction = inOrOutDeduction;
    }

    public Integer getPromotionSwitch() {
        return promotionSwitch;
    }

    public void setPromotionSwitch(Integer promotionSwitch) {
        this.promotionSwitch = promotionSwitch;
    }

    public Integer getBigRateSwitch() {
        return bigRateSwitch;
    }

    public void setBigRateSwitch(Integer bigRateSwitch) {
        this.bigRateSwitch = bigRateSwitch;
    }

    public BigDecimal getSingleServiceBigRate() {
        return singleServiceBigRate;
    }

    public void setSingleServiceBigRate(BigDecimal singleServiceBigRate) {
        this.singleServiceBigRate = singleServiceBigRate;
    }

    public Integer getBankChannel() {
        return bankChannel;
    }

    public void setBankChannel(Integer bankChannel) {
        this.bankChannel = bankChannel;
    }

    public BigDecimal getBankSingleQuota() {
        return bankSingleQuota;
    }

    public void setBankSingleQuota(BigDecimal bankSingleQuota) {
        this.bankSingleQuota = bankSingleQuota;
    }

    public Integer getAlipayChannel() {
        return alipayChannel;
    }

    public void setAlipayChannel(Integer alipayChannel) {
        this.alipayChannel = alipayChannel;
    }

    public BigDecimal getAlipaySingleQuota() {
        return alipaySingleQuota;
    }

    public void setAlipaySingleQuota(BigDecimal alipaySingleQuota) {
        this.alipaySingleQuota = alipaySingleQuota;
    }

    public Integer getWechatChannel() {
        return wechatChannel;
    }

    public void setWechatChannel(Integer wechatChannel) {
        this.wechatChannel = wechatChannel;
    }

    public BigDecimal getWechatSingleQuota() {
        return wechatSingleQuota;
    }

    public void setWechatSingleQuota(BigDecimal wechatSingleQuota) {
        this.wechatSingleQuota = wechatSingleQuota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSigningType() {
        return signingType;
    }

    public void setSigningType(Integer signingType) {
        this.signingType = signingType;
    }

    public Integer getPaymentAdjustType() {
        return paymentAdjustType;
    }

    public void setPaymentAdjustType(Integer paymentAdjustType) {
        this.paymentAdjustType = paymentAdjustType;
    }

    public Integer getSettleType() {
        return settleType;
    }

    public void setSettleType(Integer settleType) {
        this.settleType = settleType;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getPaymentModel() {
        return paymentModel;
    }

    public void setPaymentModel(Integer paymentModel) {
        this.paymentModel = paymentModel;
    }

    public String getPaymentRequirement() {
        return paymentRequirement;
    }

    public void setPaymentRequirement(String paymentRequirement) {
        this.paymentRequirement = paymentRequirement;
    }

    public BigDecimal getSingleServiceRate() {
        return singleServiceRate;
    }

    public void setSingleServiceRate(BigDecimal singleServiceRate) {
        this.singleServiceRate = singleServiceRate;
    }

    public Integer getMealInfoId() {
        return mealInfoId;
    }

    public void setMealInfoId(Integer mealInfoId) {
        this.mealInfoId = mealInfoId;
    }

    public Integer getRateSwitch() {
        return rateSwitch;
    }

    public void setRateSwitch(Integer rateSwitch) {
        this.rateSwitch = rateSwitch;
    }

    public void setSignSwitch(Integer signSwitch)
    {
        this.signSwitch = signSwitch;
    }

    public Integer getSignSwitch()
    {
        return signSwitch;
    }

    public void setSilentSignSwitch(Integer silentSignSwitch)
    {
        this.silentSignSwitch = silentSignSwitch;
    }

    public Integer getSilentSignSwitch()
    {
        return silentSignSwitch;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public BigDecimal getSingleQuota() {
        return singleQuota;
    }

    public void setSingleQuota(BigDecimal singleQuota) {
        this.singleQuota = singleQuota;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
    public void setSendOrderSwitch(Integer sendOrderSwitch)
    {
        this.sendOrderSwitch = sendOrderSwitch;
    }

    public Integer getSendOrderSwitch()
    {
        return sendOrderSwitch;
    }

    public void setSendOrderMode(Integer sendOrderMode)
    {
        this.sendOrderMode = sendOrderMode;
    }

    public Integer getSendOrderMode()
    {
        return sendOrderMode;
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


    public Integer getCustomerWithdrawSwitch() {
        return customerWithdrawSwitch;
    }

    public void setCustomerWithdrawSwitch(Integer customerWithdrawSwitch) {
        this.customerWithdrawSwitch = customerWithdrawSwitch;
    }

    public Integer getMoneyModel() {
        return moneyModel;
    }

    public void setMoneyModel(Integer moneyModel) {
        this.moneyModel = moneyModel;
    }

    public Integer getAsynSwitch() {
        return asynSwitch;
    }

    public void setAsynSwitch(Integer asynSwitch) {
        this.asynSwitch = asynSwitch;
    }

    public Integer getAsynSignSwitch() {
        return asynSignSwitch;
    }

    public void setAsynSignSwitch(Integer asynSignSwitch) {
        this.asynSignSwitch = asynSignSwitch;
    }

    @Override
    public String toString() {
        return "Cooperation{" +
                "id=" + id +
                ", signingType=" + signingType +
                ", paymentAdjustType=" + paymentAdjustType +
                ", settleType=" + settleType +
                ", businessType=" + businessType +
                ", paymentModel=" + paymentModel +
                ", paymentRequirement='" + paymentRequirement + '\'' +
                ", singleServiceRate=" + singleServiceRate +
                ", inOrOutDeduction=" + inOrOutDeduction +
                ", mealInfoId=" + mealInfoId +
                ", rateSwitch=" + rateSwitch +
                ", signSwitch=" + signSwitch +
                ", silentSignSwitch=" + silentSignSwitch +
                ", bigRateSwitch=" + bigRateSwitch +
                ", singleServiceBigRate=" + singleServiceBigRate +
                ", paymentChannel='" + paymentChannel + '\'' +
                ", singleQuota=" + singleQuota +
                ", bankChannel=" + bankChannel +
                ", bankSingleQuota=" + bankSingleQuota +
                ", alipayChannel=" + alipayChannel +
                ", alipaySingleQuota=" + alipaySingleQuota +
                ", wechatChannel=" + wechatChannel +
                ", wechatSingleQuota=" + wechatSingleQuota +
                ", promotionSwitch=" + promotionSwitch +
                ", merchantId=" + merchantId +
                ", sendOrderSwitch=" + sendOrderSwitch +
                ", sendOrderMode=" + sendOrderMode +
                ", customerWithdrawSwitch=" + customerWithdrawSwitch +
                ", asynSwitch=" + asynSwitch +
                ", asynSignSwitch=" + asynSignSwitch +
                ", providerId=" + providerId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
