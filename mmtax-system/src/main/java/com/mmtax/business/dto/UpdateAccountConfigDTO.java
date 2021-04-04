package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户设置更新DTO
 * @author yuanligang
 * @date 2019/7/16
 */
public class UpdateAccountConfigDTO {

    @ApiModelProperty(value = "记录ID", required = true)
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

    @ApiModelProperty("预警及提醒类邮件发送邮箱地址")
    private List<String> balanceRemindEmailList;

    @ApiModelProperty("账单类邮件发送范围")
    private List<String> rechargeRemindEmailList;







    @ApiModelProperty(hidden = true)
    private Integer merchantId;

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

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
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

    @Override
    public String toString() {
        return "UpdateAccountConfigDTO{" +
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
                ", merchantId=" + merchantId +
                '}';
    }
}
