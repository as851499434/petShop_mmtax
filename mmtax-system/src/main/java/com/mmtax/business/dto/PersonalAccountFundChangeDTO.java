package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * create by
 * 个人资金流水DTO
 *
 * @author zouyuanpeng
 * @date 2020/9/25 15:24
 */
@ApiModel("个人资金流水")
public class PersonalAccountFundChangeDTO {
    /**
     * 员工账户明细id
     */
    @ApiModelProperty("员工账户明细id")
    private String id;
    /**
     * 员工id
     */
    @ApiModelProperty("员工id")
    private String customerId;
    /**
     * 流水号
     */
    @ApiModelProperty("流水号")
    @Excel(name = "流水号")
    private String orderSerialNum;
    /**
     * 账户名称
     */
    @ApiModelProperty("账户名称")
    @Excel(name = "账户名称")
    private String customerName;
    /**
     * 交易类型：0-账户收款 1-收取佣金
     */
    @ApiModelProperty("交易类型：0-账户收款 1-收取佣金")
    @Excel(name = "交易类型",readConverterExp = "0=账户收款,1=收取佣金")
    private String paymentType;
    /**
     * 增加（元）
     */
    @ApiModelProperty("增加（元）")
    @Excel(name = "增加（元）")
    private BigDecimal addPaymentAmount;
    /**
     * 减少（元）
     */
    @ApiModelProperty("减少（元）")
    @Excel(name = "减少（元）")
    private BigDecimal reducePaymentAmount;
    /**
     * 变化（元）
     */
    @ApiModelProperty("变化（元）")
    private BigDecimal changePaymentAmount;
    /**
     * 变动类型 0-出账冻结 1-入账冻结 2出账解冻 3入账解冻
     */
    private Integer type;
    /**
     * 账户余额（元）
     */
    @ApiModelProperty("账户余额（元）")
    @Excel(name = "账户余额（元）")
    private BigDecimal paymentAmount;
    /**
     * 交易时间
     */
    @ApiModelProperty("交易时间")
    @Excel(name = "交易时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getAddPaymentAmount() {
        return addPaymentAmount;
    }


    public void setAddPaymentAmount(BigDecimal addPaymentAmount) {
        this.addPaymentAmount = addPaymentAmount;
    }

    public BigDecimal getReducePaymentAmount() {
        return reducePaymentAmount;
    }

    public void setReducePaymentAmount(BigDecimal reducePaymentAmount) {
        this.reducePaymentAmount = reducePaymentAmount;
    }

    public BigDecimal getChangePaymentAmount() {
        return changePaymentAmount;
    }

    public void setChangePaymentAmount(BigDecimal changePaymentAmount) {
        this.changePaymentAmount = changePaymentAmount;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
