package com.mmtax.business.dto;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/16 20:19
 */
public class CallBackDTO {

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
     * 支付宝账号
     */
    private String accountNo;
    /**
     * 请求时间，精确到秒
     */
    private String requireTime;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名方式
     */
    private String signType;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 预留手机号
     */
    private String mobileNo;
    /**
     * 私钥
     */
    private String desKey;

    /**
     * 版本号
     */
    private String version;

    /**
     * 备注信息
     * 备注中不能包含尖括号、英文单引号、英文双引号、与逻辑运算符及其转义字符
     * 支付宝打款非空，显示为订单商品说明
     */
    private String memo;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

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

    public String getRequireTime() {
        return requireTime;
    }

    public void setRequireTime(String requireTime) {
        this.requireTime = requireTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "CallBackDTO{" +
                "name='" + name + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", amount='" + amount + '\'' +
                ", bankCardNo='" + bankCardNo + '\'' +
                ", requireTime='" + requireTime + '\'' +
                ", sign='" + sign + '\'' +
                ", signType='" + signType + '\'' +
                ", orderId='" + orderId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", desKey='" + desKey + '\'' +
                ", version='" + version + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
