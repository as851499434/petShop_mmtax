package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tk.mybatis.mapper.annotation.Version;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户账户表 tbl_merchant_account
 *
 * @author meimiao
 * @date 2020-05-06
 */
@Table(name = "tbl_merchant_account")
public class MerchantAccount
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /** 商户id */
    private Integer merchantId;
    /** 总余额 */
    private BigDecimal amount;
    /** 冻结金额 */
    private BigDecimal frozenAmount;
    /** 可用余额 */
    private BigDecimal usableAmount;
    /** 累计返点金额 */
    private BigDecimal accumulatedReturnAmount;
    /** 可用累计返点金额 */
    private BigDecimal usableAccumulatedReturnAmount;
    /** 状态 ACTIVE-激活状态，正常使用FROZED-冻结（不可提现）CANCEL-注销 */
    private String status;
    /** 版本号 */
    @Version
    private Integer version;
    /**  */
    @Transient
    private Integer providerId;
    /**  */
    private Date createTime;
    /**  */
    private Date updateTime;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setMerchantId(Integer merchantId)
    {
        this.merchantId = merchantId;
    }

    public Integer getMerchantId()
    {
        return merchantId;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount)
    {
        this.frozenAmount = frozenAmount;
    }

    public BigDecimal getFrozenAmount()
    {
        return frozenAmount;
    }

    public void setUsableAmount(BigDecimal usableAmount)
    {
        this.usableAmount = usableAmount;
    }

    public BigDecimal getUsableAmount()
    {
        return usableAmount;
    }

    public void setAccumulatedReturnAmount(BigDecimal accumulatedReturnAmount)
    {
        this.accumulatedReturnAmount = accumulatedReturnAmount;
    }

    public BigDecimal getAccumulatedReturnAmount()
    {
        return accumulatedReturnAmount;
    }

    public void setUsableAccumulatedReturnAmount(BigDecimal usableAccumulatedReturnAmount)
    {
        this.usableAccumulatedReturnAmount = usableAccumulatedReturnAmount;
    }

    public BigDecimal getUsableAccumulatedReturnAmount()
    {
        return usableAccumulatedReturnAmount;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }

    public Integer getVersion()
    {
        return version;
    }

    public void setProviderId(Integer providerId)
    {
        this.providerId = providerId;
    }

    public Integer getProviderId()
    {
        return providerId;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("merchantId", getMerchantId())
                .append("amount", getAmount())
                .append("frozenAmount", getFrozenAmount())
                .append("usableAmount", getUsableAmount())
                .append("accumulatedReturnAmount", getAccumulatedReturnAmount())
                .append("usableAccumulatedReturnAmount", getUsableAccumulatedReturnAmount())
                .append("status", getStatus())
                .append("version", getVersion())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
