package com.mmtax.common.utils.onlinebank;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 网商账户余额对账结果
 * @author Ljd
 * @date 2020/6/20
 */
public class AccountCheckResultOnLineDTO {
    private String merchantName;
    private String subAccountNo;
    private BigDecimal localBalance;
    private BigDecimal onlineBalance;
    private BigDecimal onlineAvailableBalance;
    private Date createTime;
    private Integer merchantId;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 差额
     */
    private BigDecimal balance;
    /**
     * 余额是否相同
     */
    private Boolean resultFlag;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getSubAccountNo() {
        return subAccountNo;
    }

    public void setSubAccountNo(String subAccountNo) {
        this.subAccountNo = subAccountNo;
    }

    public BigDecimal getLocalBalance() {
        return localBalance;
    }

    public void setLocalBalance(BigDecimal localBalance) {
        this.localBalance = localBalance;
    }

    public BigDecimal getOnlineBalance() {
        return onlineBalance;
    }

    public void setOnlineBalance(BigDecimal onlineBalance) {
        this.onlineBalance = onlineBalance;
    }

    public BigDecimal getOnlineAvailableBalance() {
        return onlineAvailableBalance;
    }

    public void setOnlineAvailableBalance(BigDecimal onlineAvailableBalance) {
        this.onlineAvailableBalance = onlineAvailableBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(Boolean resultFlag) {
        this.resultFlag = resultFlag;
    }
}
