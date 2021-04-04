package com.mmtax.business.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 账户配置表 tbl_merchant_account_config
 *
 * @author meimiao
 * @date 2019-07-16
 */
@Table(name = "tbl_merchant_account_config")
public class MerchantAccountConfig {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    /**
     * 预警及提醒类邮件发送邮箱地址
     */
    @ApiModelProperty(value = "预警及提醒类邮件发送邮箱地址", hidden = true)
    private String balanceRemindEmail;

    @ApiModelProperty(value = "预警及提醒类邮件发送邮箱地址")
    private List<String> balanceRemindEmails;
    /**
     * 账单类邮件发送范围
     */
    @ApiModelProperty(value = "账单类邮件发送范围", hidden = true)
    private String rechargeRemindEmail;

    @ApiModelProperty(value = "账单类邮件发送范围", required = true)
    private List<String> rechargeRemindEmails;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setBalanceRemindSwitch(String balanceRemindSwitch) {
        this.balanceRemindSwitch = balanceRemindSwitch;
    }

    public String getBalanceRemindSwitch() {
        return balanceRemindSwitch;
    }

    public void setBalanceEmailRemindSwitch(String balanceEmailRemindSwitch) {
        this.balanceEmailRemindSwitch = balanceEmailRemindSwitch;
    }

    public String getBalanceEmailRemindSwitch() {
        return balanceEmailRemindSwitch;
    }

    public void setBalanceSmsRemindSwitch(String balanceSmsRemindSwitch) {
        this.balanceSmsRemindSwitch = balanceSmsRemindSwitch;
    }

    public String getBalanceSmsRemindSwitch() {
        return balanceSmsRemindSwitch;
    }

    public void setBankRemindAmount(BigDecimal bankRemindAmount) {
        this.bankRemindAmount = bankRemindAmount;
    }

    public BigDecimal getBankRemindAmount() {
        return bankRemindAmount;
    }

    public void setAlipayRemindAmount(BigDecimal alipayRemindAmount) {
        this.alipayRemindAmount = alipayRemindAmount;
    }

    public BigDecimal getAlipayRemindAmount() {
        return alipayRemindAmount;
    }

    public void setWechatRemindAmount(BigDecimal wechatRemindAmount) {
        this.wechatRemindAmount = wechatRemindAmount;
    }

    public BigDecimal getWechatRemindAmount() {
        return wechatRemindAmount;
    }

    public void setRechargeRemindSwitch(String rechargeRemindSwitch) {
        this.rechargeRemindSwitch = rechargeRemindSwitch;
    }

    public String getRechargeRemindSwitch() {
        return rechargeRemindSwitch;
    }

    public void setRechargeEmailRemindSwitch(String rechargeEmailRemindSwitch) {
        this.rechargeEmailRemindSwitch = rechargeEmailRemindSwitch;
    }

    public String getRechargeEmailRemindSwitch() {
        return rechargeEmailRemindSwitch;
    }

    public void setRechargeSmsRemindSwitch(String rechargeSmsRemindSwitch) {
        this.rechargeSmsRemindSwitch = rechargeSmsRemindSwitch;
    }

    public String getRechargeSmsRemindSwitch() {
        return rechargeSmsRemindSwitch;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setBalanceRemindEmail(String balanceRemindEmail) {
        this.balanceRemindEmail = balanceRemindEmail;
    }

    public String getBalanceRemindEmail() {
        return balanceRemindEmail;
    }

    public void setRechargeRemindEmail(String rechargeRemindEmail) {
        this.rechargeRemindEmail = rechargeRemindEmail;
    }

    public String getRechargeRemindEmail() {
        return rechargeRemindEmail;
    }

    public void setLendingAuthority(String lendingAuthority) {
        this.lendingAuthority = lendingAuthority;
    }

    public String getLendingAuthority() {
        return lendingAuthority;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantId() {
        return merchantId;
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

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public List<String> getBalanceRemindEmails() {
        return balanceRemindEmails;
    }

    public void setBalanceRemindEmails(List<String> balanceRemindEmails) {
        this.balanceRemindEmails = balanceRemindEmails;
    }

    public List<String> getRechargeRemindEmails() {
        return rechargeRemindEmails;
    }

    public void setRechargeRemindEmails(List<String> rechargeRemindEmails) {
        this.rechargeRemindEmails = rechargeRemindEmails;
    }

    @Override
    public String toString() {
        return "MerchantAccountConfig{" +
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
                ", balanceRemindEmail='" + balanceRemindEmail + '\'' +
                ", balanceRemindEmails=" + balanceRemindEmails +
                ", rechargeRemindEmail='" + rechargeRemindEmail + '\'' +
                ", rechargeRemindEmails=" + rechargeRemindEmails +
                ", lendingAuthority='" + lendingAuthority + '\'' +
                ", merchantId=" + merchantId +
                ", providerId=" + providerId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
