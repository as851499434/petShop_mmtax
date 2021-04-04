package com.mmtax.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 结算表 tbl_settlement_info
 *
 * @author meimiao
 * @date 2019-11-08
 */
@Table(name = "tbl_settlement_info")
public class SettlementInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 开户名称
     */
    @ApiModelProperty("开户名称")
    private String accountName;
    /**
     * 对公账户
     */
    @ApiModelProperty("对公账户")
    private String accountNo;
    /**
     * 开户银行名称
     */
    @ApiModelProperty("开户银行")
    private String bankName;
    /**
     * 银行代码
     */
    @ApiModelProperty("银行代码")
    private String bankCode;
    /**
     * 提现银行卡开户行行号
     */
    @ApiModelProperty("提现银行卡开户行行号")
    private String settBankcardNo;
    /**
     * 商户id
     */
    @JsonIgnore
    private Integer merchantId;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setSettBankcardNo(String settBankcardNo) {
        this.settBankcardNo = settBankcardNo;
    }

    public String getSettBankcardNo() {
        return settBankcardNo;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
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


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("accountName", getAccountName())
                .append("accountNo", getAccountNo())
                .append("bankName", getBankName())
                .append("bankCode", getBankCode())
                .append("settBankcardNo", getSettBankcardNo())
                .append("merchantId", getMerchantId())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
