package com.mmtax.business.yunzbdto;

import com.mmtax.common.utils.yunzbutil.BaseRequest;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/11 13:36
 */
public class YunZBSettDTO extends BaseRequest {

    /**
     * 二级子商户号
     */
    private String subMchId;

    /**
     * 商户订单号
     */
    private String tradeNo;

    /**
     * 渠道号
     */
    private String channelNo;

    /**
     * 出款金额
     */
    private String amt;

    /**
     * 持卡人姓名
     */
    private String cardName;

    /**
     * 银行卡号
     */
    private String cardNo;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 手机号
     */
    private String mobileNo;

    /**
     * 商户通知地址
     */
    private String notifyUrl;

    /**
     * 备注
     */
    private String remark;

    /**
     * 商户参数
     */
    private String merParam;

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMerParam() {
        return merParam;
    }

    public void setMerParam(String merParam) {
        this.merParam = merParam;
    }
}
