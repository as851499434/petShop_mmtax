package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 账户资金变动
 * 商户资金流水DTO
 * @author yuanligang
 * @date 2019/7/24
 */
public class MerchantCapitalFlowDTO {
    /**
     * 入账时间
     */
    @Excel(name = "入账时间")
    @ApiModelProperty("入账时间")
    private String createTime;
    /**
     * 商户编码
     * 展示给用户实为商户编码
     */
    @ApiModelProperty("商户编码")
    private String merchantCode;
    /**
     * 代征主体
     * todo 一期--安薪宝
     */
    @ApiModelProperty("代征主体")
    private String subject;

    /**
     * 业务订单流水号
     */
    @Excel(name = "业务订单流水号")
    @ApiModelProperty("业务订单流水号")
    private String orderSerialNum;

    /**
     * 业务类型 0-打款 1-充值
     */
    @Excel(name = "业务类型", readConverterExp = "0=打款,1=充值,2=返点,3=转入,4=转出 ")
    @ApiModelProperty("业务类型 0-打款 1-充值")
    private Integer type;

    /**
     * 业务分类 0-计提商户服务费 1-充值金额
     */
    @Excel(name = "业务分类", readConverterExp = "0=计提商户服务费,1=充值金额,2=返点金额")
    @ApiModelProperty("业务分类 0-计提商户服务费 1-充值金额")
    private Integer typeClass;

    /**
     * 商户账户类型
     * todo 一期--银行卡余额账户
     */
    @ApiModelProperty("商户账户类型")
    private String accountType;
    /**
     * 收支类型  0-支出 1-收入
     */
    @Excel(name = "收支类型", readConverterExp = "0=支出,1=收入,2=返点,3=转入,4=转出")
    @ApiModelProperty("收支类型  0-支出 1-收入")
    private  Integer trxType;

    /**
     * 交易金额
     */
    @Excel(name = "交易金额")
    @ApiModelProperty("交易金额")
    private BigDecimal amount;
    /**
     * 账户余额
     */
    @ApiModelProperty("账户余额")
    private BigDecimal balance;

    //    @Excel(name = "订单状态", readConverterExp = "0=失败,1=成功")
    @ApiModelProperty("订单状态 0-失败 1-成功")
    private Integer status;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(Integer typeClass) {
        this.typeClass = typeClass;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getTrxType() {
        return trxType;
    }

    public void setTrxType(Integer trxType) {
        this.trxType = trxType;
    }

    public BigDecimal getAmonut() {
        return amount;
    }

    public void setAmonut(BigDecimal amonut) {
        this.amount = amonut;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "MerchantCapitalFlowDTO{" +
                "createTime='" + createTime + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", subject='" + subject + '\'' +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", type=" + type +
                ", typeClass=" + typeClass +
                ", accountType='" + accountType + '\'' +
                ", trxType=" + trxType +
                ", amount=" + amount +
                ", balance=" + balance +
                ", status=" + status +
                '}';
    }
}
