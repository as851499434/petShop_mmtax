package com.mmtax.business.yunzbdto;

import com.mmtax.common.utils.yunzbutil.BaseRequest;

/**
 * 云众包要素认证
 *
 * @author wangzhaoxu
 */
public class YunZBSignDTO extends BaseRequest {
    /**
     * 姓名
     */
    private String cardName;
    /**
     * 卡号
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
     * 二级子商户号
     */
    private String subMchId;

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

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    @Override
    public String toString() {
        return "YunZBSignDTO{" +
                "cardName='" + cardName + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", subMchId='" + subMchId + '\'' +
                '}';
    }
}
