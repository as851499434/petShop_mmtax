package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 余额账单详情
 * @author yuanligang
 * @date 2019/7/23
 */
public class MerchantBalanceDetailDTO {

    /**
     * 打款流水记录表ID
     */
    @ApiModelProperty(value = "打款流水记录表ID")
    private Integer id;
    /**
     * 订单流水号
     */
    @Excel(name = "订单流水号")
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;
    /**
     * 商户流水号
     */
    @Excel(name = "商户流水号")
    @ApiModelProperty(value = "商户流水号")
    private String merchantSerialNum;
    /**
     * 创建订单时间
     */
    @Excel(name = "创建订单时间")
    @ApiModelProperty(value = "创建订单时间")
    private String createTime;
    /**
     * 最后更新时间
     */
    @Excel(name = "最后更新时间")
    @ApiModelProperty(value = "最后更新时间")
    private String updateTime;
    /**
     * 代征主体
     * todo 一期为常量
     */
    @Excel(name = "代征主体", defaultValue = "安薪宝平台")
    @ApiModelProperty(value = "代征主体")
    private String subjectConscription;
    /**
     * 收款账号
     */
    @Excel(name = "收款账号")
    @ApiModelProperty(value = "收款账号")
    private  String  payeeBankCard;
    /**
     * 打款渠道
     */
    @Excel(name = "打款渠道", readConverterExp = "BANK=银行,ALIPAY=支付宝,WECHAT=微信")
    @ApiModelProperty(value = "打款渠道--BANK-银行ALIPAY-支付宝WECHAT-微信打款渠道")
    private  String paymentChannel;
    /**
     * 打款请求金额
     */
    @Excel(name = "打款请求金额")
    @ApiModelProperty(value = "打款请求金额")
    private BigDecimal amount;
    /**
     * 服务费应收金额
     */
    @Excel(name = "服务费应收金额")
    @ApiModelProperty(value = "服务费应收金额")
    private BigDecimal charge;
    /**
     * 服务费抵扣金额
     * todo 一期为0
     */
    @Excel(name = "服务费抵扣金额")
    @ApiModelProperty(value = "服务费抵扣金额")
    private BigDecimal deductionCharge;
     /**
     * 服务费实收金额 = 服务费应收金额-抵扣
     */
     @Excel(name = "服务费实收金额")
     @ApiModelProperty(value = "服务费实收金额")
     private BigDecimal actualCharge;
    /**
     * 实际应收金额
     */
    @Excel(name = "实际应收金额")
    @ApiModelProperty(value = "实际应收金额")
    private BigDecimal actualAmount;
    /**
     * 订单状态
     */
    @Excel(name = "订单状态", readConverterExp = "0=未打款,1=已打款,2=打款挂起,3=调单")
    @ApiModelProperty(value = "订单状态 0-未打款1-已打款2-打款挂起3-调单状态")
    private Integer orderStatus;

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getMerchantSerialNum() {
        return merchantSerialNum;
    }

    public void setMerchantSerialNum(String merchantSerialNum) {
        this.merchantSerialNum = merchantSerialNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSubjectConscription() {
        return subjectConscription;
    }

    public void setSubjectConscription(String subjectConscription) {
        this.subjectConscription = subjectConscription;
    }

    public String getPayeeBankCard() {
        return payeeBankCard;
    }

    public void setPayeeBankCard(String payeeBankCard) {
        this.payeeBankCard = payeeBankCard;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getDeductionCharge() {
        return deductionCharge;
    }

    public void setDeductionCharge(BigDecimal deductionCharge) {
        this.deductionCharge = deductionCharge;
    }

    public BigDecimal getActualCharge() {
        return actualCharge;
    }

    public void setActualCharge(BigDecimal actualCharge) {
        this.actualCharge = actualCharge;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MerchantBalanceDetailDTO{" +
                "id=" + id +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", merchantSerialNum='" + merchantSerialNum + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", subjectConscription='" + subjectConscription + '\'' +
                ", payeeBankCard='" + payeeBankCard + '\'' +
                ", paymentChannel='" + paymentChannel + '\'' +
                ", amount=" + amount +
                ", charge=" + charge +
                ", deductionCharge=" + deductionCharge +
                ", actualCharge=" + actualCharge +
                ", actualAmount=" + actualAmount +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
