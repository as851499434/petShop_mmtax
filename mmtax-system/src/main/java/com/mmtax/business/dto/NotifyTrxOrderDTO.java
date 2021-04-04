package com.mmtax.business.dto;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/4/7 3:11 下午
 */
public class NotifyTrxOrderDTO extends PaymentResultDTO {

    /**
     * 名字
     */
    private String name;
    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 身份证号
     */
    private String idCardNo;
    /**
     * 代付金额
     */
    private String amount;
    /**
     * 银行卡号
     */
    private String bankCardNo;
    /**
     * 请求时间，精确到秒
     */
    private String orderTime;
    /**
     * 订单编号
     */
    private String queryOrderNo;
    /**
     * 预留手机号
     */
    private String mobileNo;
    /**
     * 打款时间
     */
    private String paymentTime;
    /**
     * 单笔手续费
     */
    private String charge;
    /**
     * 订单备注
     */
    private String remark;

    private String orderStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getQueryOrderNo() {
        return queryOrderNo;
    }

    public void setQueryOrderNo(String queryOrderNo) {
        this.queryOrderNo = queryOrderNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "NotifyTrxOrderDTO{" +
                "name='" + name + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", amount='" + amount + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", queryOrderNo='" + queryOrderNo + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", charge='" + charge + '\'' +
                ", remark='" + remark + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
