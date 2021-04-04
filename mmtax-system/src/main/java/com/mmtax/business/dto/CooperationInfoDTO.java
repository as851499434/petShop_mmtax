package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 合作信息DTO
 * @author yuanligang
 * @date 2019/7/16
 */
public class CooperationInfoDTO  extends BaseEntity {

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Integer id;

    /**
     * 平台商户名
     */
    @ApiModelProperty(value = "平台商户名")
    private String merchantName;
    /**
     * 账户余额
     */
    @ApiModelProperty(value = "账户余额")
    private BigDecimal balance;
    /**
     * 冻结金额
     */
    @ApiModelProperty(value = "冻结金额")
    private BigDecimal frozenAmount;
    /**
     * 税源地商户
     */
    private String taxSounrceCompanyName;
    /**
     * 银行打款方式支持标识
     */
    private Integer bankChannel;
    /**
     * 支付宝打款方式支持标识
     */
    private Integer alipayChannel;
    /**
     * 微信打款方式支持标识
     */
    private Integer wechatChannel;

    /**
     * 云资金充值账号
     */
    @ApiModelProperty("云资金充值账号")
    private String onlineBankAccount;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty(value = "销售名字")
    private String saleName;
    @ApiModelProperty(hidden = true)
    private String saleId;
    @ApiModelProperty(value = "签约开关")
    private String signSwitch;

    public String getSignSwitch() {
        return signSwitch;
    }

    public void setSignSwitch(String signSwitch) {
        this.signSwitch = signSwitch;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
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

    public String getOnlineBankAccount() {
        return onlineBankAccount;
    }

    public void setOnlineBankAccount(String onlineBankAccount) {
        this.onlineBankAccount = onlineBankAccount;
    }

    public String getTaxSounrceCompanyName() {
        return taxSounrceCompanyName;
    }

    public void setTaxSounrceCompanyName(String taxSounrceCompanyName) {
        this.taxSounrceCompanyName = taxSounrceCompanyName;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CooperationInfoDTO{" +
                "id=" + id +
                ", merchantName='" + merchantName + '\'' +
                ", balance=" + balance +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
