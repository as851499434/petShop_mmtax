package com.mmtax.business.dto;

import com.mmtax.business.domain.MerchantTaskInfo;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商户合作信息详情DTO
 *
 * @author yuanligang
 * @date 2019/7/16
 */
public class CooperationInfoDetailDTO {
    /**
     * tbl_cooperation表序列号
     */
    @ApiModelProperty(value = "tbl_cooperation表序列号")
    private Integer cooperationId;

    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 代征主体
     */
    @ApiModelProperty(value = "代征主体")
    private String mainSubject;
    /**
     * 签约方式
     */
    @ApiModelProperty(value = "签约方式")
    private Integer signingType;
    /**
     * 打款核算误差
     */
    @ApiModelProperty(value = "打款核算误差")
    private Integer paymentAdjustType;

    /**
     * 结算类型
     */
    @ApiModelProperty(value = "结算类型")
    private Integer settleType;
    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    private Integer businessType;
    /**
     * 服务费模式
     */
    @ApiModelProperty(value = "服务")
    private Integer paymentModel;

    /**
     * 付款要求
     */
    @ApiModelProperty(value = "付款要求")
    private String paymentRequirement;

    /**
     * 商户服务费
     */
    @ApiModelProperty(value = "商户服务费")
    private BigDecimal singleServiceRate;

    /**
     * 0-内扣 1-外扣
     */
    @ApiModelProperty(value = "0-内扣 1-外扣", required = true)
    private Integer inOrOutDeduction;

    /**
     * 套餐ID
     */
    @ApiModelProperty(value = "套餐ID")
    private Integer mealInfoId;
    /**
     * 用户服务费开关
     */
    @ApiModelProperty(value = "用户服务费开关")
    private Integer rateSwitch;

    /**
     * 用户服务费开关
     */
    @ApiModelProperty(value = "用户签约开关")
    private Integer signSwitch;


    /**
     * 用户服务费开关
     */
    @ApiModelProperty(value = "灵工提现开关")
    private Integer customerWithdrawSwitch;
    /**
     * 用户静默签约开关0-关（非静默），1-开（静默）
     */
    @ApiModelProperty(value = "用户静默签约开关0-关（非静默），1-开（静默）")
    private Integer silentSignSwitch;

    /**
     * 打款渠道
     * BANK-银行ALIPAY-支付宝WECHAT-微信
     */
    @ApiModelProperty(value = "打款渠道 BANK-银行ALIPAY-支付宝WECHAT-微信")
    private String paymentChannel;

    /**
     * 用户单笔限额
     */
    @ApiModelProperty(value = "用户单笔限额")
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
     * risk表序列号
     */
    @ApiModelProperty(value = "risk表序列号")
    private Integer riskId;
    /**
     * 全网限额ON-开OFF-关
     */
    @ApiModelProperty(value = "全网限额ON-开OFF-关")
    private String quotaConfig;
    /**
     * 全网单人月累计金额限制
     */
    @ApiModelProperty(value = "全网单人月累计金额限制")
    private BigDecimal singleQuotaConfig;
    /**
     * 临时额度
     */
    @ApiModelProperty(value = "临时额度")
    private BigDecimal temporaryQuota;
    /**
     * 临时额度开始时间
     */
    @ApiModelProperty(value = "临时额度开始时间")
    private String temporaryQuotaBegin;
    /**
     * 临时额度结束时间
     */
    @ApiModelProperty(value = "临时额度结束时间")
    private String temporaryQuotaEnd;
    /**
     * 商户id
     */
    @ApiModelProperty(value = "商户id")
    private Integer merchantId;

    /**
     * 是否为派单商户开关 0-关（非派单），1-开（派单）
     */
    @ApiModelProperty(value = "是否为派单商户开关 0-关（非派单），1-开（派单）", required = true)
    private Integer sendOrderSwitch;
    /** 派单模式 0-手动，1-自动 */
    @ApiModelProperty(value = "派单模式 0-手动，1-自动", required = true)
    private Integer sendOrderMode;
    /**
     * 单商户日限额ON开OFF关
     */
    @ApiModelProperty(value = "单商户日限额ON开OFF关")
    private String singleDayQuota;
    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String contractNo;

    /**
     * 单商户累计限额
     */
    @ApiModelProperty(value = "单商户累计限额")
    private BigDecimal cumulativeQuota;
    /**
     * 大额费率开关
     */
    @ApiModelProperty(value = "大额费率开关 0关 1开")
    private Integer bigRateSwitch;
    /**
     * 商户单笔大额服务费费率
     */
    @ApiModelProperty(value = "商户单笔大额服务费费率")
    private BigDecimal singleServiceBigRate;
    /**
     * 是否推广 0-否 1-是
     */
    @ApiModelProperty(value = "用户推广开关 0-关 1-开", required = true)
    private Integer promotionSwitch;

    /**
     * 用户派单任务信息
     */
    @ApiModelProperty(value = "用户派单任务信息")
    private List merchantTaskInfo;
    /**
     * 打款模式
     */
    @ApiModelProperty(value = "打款模式：0-普通模式 1-自动模式", required = true)
    private Integer moneyModel;

    public Integer getMoneyModel() {
        return moneyModel;
    }

    public void setMoneyModel(Integer moneyModel) {
        this.moneyModel = moneyModel;
    }

    @ApiModelProperty(value = "异步通知开关", required = true)
    private Integer asynSwitch;

    @ApiModelProperty(value = "异步通知是否检验开关", required = true)
    private Integer asynSignSwitch;

    public List getMerchantTaskInfo() {
        return merchantTaskInfo;
    }

    public void setMerchantTaskInfo(List merchantTaskInfo) {
        this.merchantTaskInfo = merchantTaskInfo;
    }

    public Integer getPromotionSwitch() {
        return promotionSwitch;
    }

    public void setPromotionSwitch(Integer promotionSwitch) {
        this.promotionSwitch = promotionSwitch;
    }

    public Integer getInOrOutDeduction() {
        return inOrOutDeduction;
    }

    public void setInOrOutDeduction(Integer inOrOutDeduction) {
        this.inOrOutDeduction = inOrOutDeduction;
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

    public Integer getSignSwitch() {
        return signSwitch;
    }

    public void setSignSwitch(Integer signSwitch) {
        this.signSwitch = signSwitch;
    }

    public Integer getSilentSignSwitch() {
        return silentSignSwitch;
    }

    public void setSilentSignSwitch(Integer silentSignSwitch) {
        this.silentSignSwitch = silentSignSwitch;
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

    public BigDecimal getCumulativeQuota() {
        return cumulativeQuota;
    }

    public void setCumulativeQuota(BigDecimal cumulativeQuota) {
        this.cumulativeQuota = cumulativeQuota;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMainSubject() {
        return mainSubject;
    }

    public void setMainSubject(String mainSubject) {
        this.mainSubject = mainSubject;
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


    public String getQuotaConfig() {
        return quotaConfig;
    }

    public void setQuotaConfig(String quotaConfig) {
        this.quotaConfig = quotaConfig;
    }

    public BigDecimal getSingleQuotaConfig() {
        return singleQuotaConfig;
    }

    public void setSingleQuotaConfig(BigDecimal singleQuotaConfig) {
        this.singleQuotaConfig = singleQuotaConfig;
    }

    public BigDecimal getTemporaryQuota() {
        return temporaryQuota;
    }

    public void setTemporaryQuota(BigDecimal temporaryQuota) {
        this.temporaryQuota = temporaryQuota;
    }

    public String getTemporaryQuotaBegin() {
        return temporaryQuotaBegin;
    }

    public void setTemporaryQuotaBegin(String temporaryQuotaBegin) {
        this.temporaryQuotaBegin = temporaryQuotaBegin;
    }

    public String getTemporaryQuotaEnd() {
        return temporaryQuotaEnd;
    }

    public void setTemporaryQuotaEnd(String temporaryQuotaEnd) {
        this.temporaryQuotaEnd = temporaryQuotaEnd;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getSendOrderSwitch() {
        return sendOrderSwitch;
    }

    public void setSendOrderSwitch(Integer sendOrderSwitch) {
        this.sendOrderSwitch = sendOrderSwitch;
    }

    public Integer getSendOrderMode() {
        return sendOrderMode;
    }

    public void setSendOrderMode(Integer sendOrderMode) {
        this.sendOrderMode = sendOrderMode;
    }

    public String getSingleDayQuota() {
        return singleDayQuota;
    }

    public void setSingleDayQuota(String singleDayQuota) {
        this.singleDayQuota = singleDayQuota;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public Integer getRiskId() {
        return riskId;
    }

    public void setRiskId(Integer riskId) {
        this.riskId = riskId;
    }

    public Integer getCustomerWithdrawSwitch() {
        return customerWithdrawSwitch;
    }

    public void setCustomerWithdrawSwitch(Integer customerWithdrawSwitch) {
        this.customerWithdrawSwitch = customerWithdrawSwitch;
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
        return "CooperationInfoDetailDTO{" +
                "cooperationId=" + cooperationId +
                ", merchantName='" + merchantName + '\'' +
                ", mainSubject='" + mainSubject + '\'' +
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
                ", customerWithdrawSwitch=" + customerWithdrawSwitch +
                ", silentSignSwitch=" + silentSignSwitch +
                ", paymentChannel='" + paymentChannel + '\'' +
                ", singleQuota=" + singleQuota +
                ", bankChannel=" + bankChannel +
                ", bankSingleQuota=" + bankSingleQuota +
                ", alipayChannel=" + alipayChannel +
                ", alipaySingleQuota=" + alipaySingleQuota +
                ", wechatChannel=" + wechatChannel +
                ", wechatSingleQuota=" + wechatSingleQuota +
                ", riskId=" + riskId +
                ", quotaConfig='" + quotaConfig + '\'' +
                ", singleQuotaConfig=" + singleQuotaConfig +
                ", temporaryQuota=" + temporaryQuota +
                ", temporaryQuotaBegin='" + temporaryQuotaBegin + '\'' +
                ", temporaryQuotaEnd='" + temporaryQuotaEnd + '\'' +
                ", merchantId=" + merchantId +
                ", singleDayQuota='" + singleDayQuota + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", cumulativeQuota=" + cumulativeQuota +
                ", bigRateSwitch=" + bigRateSwitch +
                ", singleServiceBigRate=" + singleServiceBigRate +
                ", promotionSwitch=" + promotionSwitch +
                ", merchantTaskInfo=" + merchantTaskInfo +
                '}';
    }
}
