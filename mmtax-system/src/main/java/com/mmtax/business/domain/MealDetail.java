package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 套餐内容表 tbl_meal_detail
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Table(name = "tbl_meal_detail")
public class MealDetail {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 区间起始金额
     */
    private BigDecimal startAmount;
    /**
     * 区间结束金额
     */
    private BigDecimal endAmount;
    /**
     * 返回服务费费率
     */
    private BigDecimal backRate;
    /**
     * 套餐信息表id
     */
    private Integer mealInfoId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(BigDecimal startAmount) {
        this.startAmount = startAmount;
    }

    public BigDecimal getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(BigDecimal endAmount) {
        this.endAmount = endAmount;
    }

    public BigDecimal getBackRate() {
        return backRate;
    }

    public void setBackRate(BigDecimal backRate) {
        this.backRate = backRate;
    }

    public Integer getMealInfoId() {
        return mealInfoId;
    }

    public void setMealInfoId(Integer mealInfoId) {
        this.mealInfoId = mealInfoId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id" , getId())
                .append("startAmount" , getStartAmount())
                .append("endAmount" , getEndAmount())
                .append("backRate" , getBackRate())
                .append("mealInfoId" , getMealInfoId())
                .append("providerId" , getProviderId())
                .append("createTime" , getCreateTime())
                .append("updateTime" , getUpdateTime())
                .toString();
    }
}
