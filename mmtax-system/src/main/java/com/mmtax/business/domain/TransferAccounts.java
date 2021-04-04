package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 转账记录表 tbl_transfer_accounts
 *
 * @author meimiao
 * @date 2019-07-15
 */
@Table(name = "tbl_transfer_accounts")
public class TransferAccounts {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 转账申请人
     */
    private String applicant;
    /**
     * 申请单号
     */
    private String trxExtenlNo;
    /**
     * 收款账户
     */
    private String payeeAccount;
    /**
     * 转出商户
     */
    private String transferMerchants;
    /**
     * 收款商户
     */
    private String payeeMerchants;
    /**
     * 转出账户
     */
    private String transferAccount;
    /**
     * 转账金额
     */
    private BigDecimal amount;
    /**
     * SUCCESS-成功FAIL-失败
     */
    private String status;
    /**
     * 0-转入1-转出
     */
    private Integer type;
    /**
     *
     */
    private Integer merchantId;
    /**
     *
     */
    @Transient
    private Integer providerId;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setTrxExtenlNo(String trxExtenlNo) {
        this.trxExtenlNo = trxExtenlNo;
    }

    public String getTrxExtenlNo() {
        return trxExtenlNo;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setTransferMerchants(String transferMerchants) {
        this.transferMerchants = transferMerchants;
    }

    public String getTransferMerchants() {
        return transferMerchants;
    }

    public void setPayeeMerchants(String payeeMerchants) {
        this.payeeMerchants = payeeMerchants;
    }

    public String getPayeeMerchants() {
        return payeeMerchants;
    }

    public void setTransferAccount(String transferAccount) {
        this.transferAccount = transferAccount;
    }

    public String getTransferAccount() {
        return transferAccount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("applicant", getApplicant())
                .append("trxExtenlNo", getTrxExtenlNo())
                .append("payeeAccount", getPayeeAccount())
                .append("transferMerchants", getTransferMerchants())
                .append("payeeMerchants", getPayeeMerchants())
                .append("transferAccount", getTransferAccount())
                .append("amount", getAmount())
                .append("status", getStatus())
                .append("type", getType())
                .append("providerId", getProviderId())
                .append("merchantId", getMerchantId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
