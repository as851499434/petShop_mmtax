package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户设置
 *
 * @author yuanligang
 * @date 2019/7/26
 */
public class MerchantAccountDTO {

    /**
     * 主键
     */
    @ApiModelProperty(value = "记录ID")
    private Integer id;
    /**
     * 余额提醒开关ON-开OFF-关
     */
    @ApiModelProperty(value = "余额提醒开关ON-开OFF-关", required = true)
    private String balanceRemindSwitch;
    /**
     * 余额邮件提醒开关ON-开OFF-关
     */
    @ApiModelProperty(value = "余额邮件提醒开关ON-开OFF-关", required = true)
    private String balanceEmailRemindSwitch;
    /**
     * 余额短信开关ON-开OFF-关
     */
    @ApiModelProperty(value = "余额短信开关ON-开OFF-关", required = true)
    private String balanceSmsRemindSwitch;
    /**
     * 银行卡余额提醒阈值
     */
    @ApiModelProperty(value = "银行卡余额提醒阈值", required = true)
    private BigDecimal bankRemindAmount;
    /**
     * 支付宝余额提醒阈值
     */
    @ApiModelProperty(value = "支付宝余额提醒阈值", required = true)
    private BigDecimal alipayRemindAmount;
    /**
     * 微信余额提醒阈值
     */
    @ApiModelProperty(value = "微信余额提醒阈值", required = true)
    private BigDecimal wechatRemindAmount;
    /**
     * 线下充值到账提醒开关ON-开OFF-关
     */
    @ApiModelProperty(value = "线下充值到账提醒开关ON-开OFF-关", required = true)
    private String rechargeRemindSwitch;
    /**
     * 线下充值到账邮件提醒开关ON-开OFF-关
     */
    @ApiModelProperty(value = "线下充值到账邮件提醒开关ON-开OFF-关", required = true)
    private String rechargeEmailRemindSwitch;
    /**
     * 线下充值到账短信提醒开关ON-开OFF-关
     */
    @ApiModelProperty(value = "线下充值到账短信提醒开关ON-开OFF-关", required = true)
    private String rechargeSmsRemindSwitch;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String comment;

    @ApiModelProperty(value = "预警邮箱集")
    private List<String> balanceRemindEmailList;

    @ApiModelProperty(value = "账单邮箱集")
    private List<String> rechargeRemindEmailList;

    /**
     * 商户放款权限ON-开OFF-关
     */
    @ApiModelProperty(value = "商户放款权限ON-开OFF-关", required = true)
    private String lendingAuthority;
    /**
     *
     */
    @ApiModelProperty(hidden = true)
    private Integer merchantId;

    @ApiModelProperty("商户打款模式,0-普通模式 1-自动模式")
    private Integer moneyModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBalanceRemindSwitch() {
        return balanceRemindSwitch;
    }

    public void setBalanceRemindSwitch(String balanceRemindSwitch) {
        this.balanceRemindSwitch = balanceRemindSwitch;
    }

    public String getBalanceEmailRemindSwitch() {
        return balanceEmailRemindSwitch;
    }

    public void setBalanceEmailRemindSwitch(String balanceEmailRemindSwitch) {
        this.balanceEmailRemindSwitch = balanceEmailRemindSwitch;
    }

    public String getBalanceSmsRemindSwitch() {
        return balanceSmsRemindSwitch;
    }

    public void setBalanceSmsRemindSwitch(String balanceSmsRemindSwitch) {
        this.balanceSmsRemindSwitch = balanceSmsRemindSwitch;
    }

    public BigDecimal getBankRemindAmount() {
        return bankRemindAmount;
    }

    public void setBankRemindAmount(BigDecimal bankRemindAmount) {
        this.bankRemindAmount = bankRemindAmount;
    }

    public BigDecimal getAlipayRemindAmount() {
        return alipayRemindAmount;
    }

    public void setAlipayRemindAmount(BigDecimal alipayRemindAmount) {
        this.alipayRemindAmount = alipayRemindAmount;
    }

    public BigDecimal getWechatRemindAmount() {
        return wechatRemindAmount;
    }

    public void setWechatRemindAmount(BigDecimal wechatRemindAmount) {
        this.wechatRemindAmount = wechatRemindAmount;
    }

    public String getRechargeRemindSwitch() {
        return rechargeRemindSwitch;
    }

    public void setRechargeRemindSwitch(String rechargeRemindSwitch) {
        this.rechargeRemindSwitch = rechargeRemindSwitch;
    }

    public String getRechargeEmailRemindSwitch() {
        return rechargeEmailRemindSwitch;
    }

    public void setRechargeEmailRemindSwitch(String rechargeEmailRemindSwitch) {
        this.rechargeEmailRemindSwitch = rechargeEmailRemindSwitch;
    }

    public String getRechargeSmsRemindSwitch() {
        return rechargeSmsRemindSwitch;
    }

    public void setRechargeSmsRemindSwitch(String rechargeSmsRemindSwitch) {
        this.rechargeSmsRemindSwitch = rechargeSmsRemindSwitch;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getBalanceRemindEmailList() {
        return balanceRemindEmailList;
    }

    public void setBalanceRemindEmailList(List<String> balanceRemindEmailList) {
        this.balanceRemindEmailList = balanceRemindEmailList;
    }

    public List<String> getRechargeRemindEmailList() {
        return rechargeRemindEmailList;
    }

    public void setRechargeRemindEmailList(List<String> rechargeRemindEmailList) {
        this.rechargeRemindEmailList = rechargeRemindEmailList;
    }

    public String getLendingAuthority() {
        return lendingAuthority;
    }

    public void setLendingAuthority(String lendingAuthority) {
        this.lendingAuthority = lendingAuthority;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMoneyModel() {
        return moneyModel;
    }

    public void setMoneyModel(Integer moneyModel) {
        this.moneyModel = moneyModel;
    }

    @Override
    public String toString() {
        return "MerchantAccountDTO{" +
                "id=" + id +
                ", balanceRemindSwitch='" + balanceRemindSwitch + '\'' +
                ", balanceEmailRemindSwitch='" + balanceEmailRemindSwitch + '\'' +
                ", balanceSmsRemindSwitch='" + balanceSmsRemindSwitch + '\'' +
                ", bankRemindAmount=" + bankRemindAmount +
                ", alipayRemindAmount=" + alipayRemindAmount +
                ", wechatRemindAmount=" + wechatRemindAmount +
                ", rechargeRemindSwitch='" + rechargeRemindSwitch + '\'' +
                ", rechargeEmailRemindSwitch='" + rechargeEmailRemindSwitch + '\'' +
                ", rechargeSmsRemindSwitch='" + rechargeSmsRemindSwitch + '\'' +
                ", comment='" + comment + '\'' +
                ", balanceRemindEmailList=" + balanceRemindEmailList +
                ", rechargeRemindEmailList=" + rechargeRemindEmailList +
                ", lendingAuthority='" + lendingAuthority + '\'' +
                ", merchantId=" + merchantId +
                ", moneyModel=" + moneyModel +
                '}';
    }
}
