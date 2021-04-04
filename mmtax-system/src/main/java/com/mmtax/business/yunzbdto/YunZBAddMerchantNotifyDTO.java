package com.mmtax.business.yunzbdto;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/12 15:44
 */
public class YunZBAddMerchantNotifyDTO {

    private String mch_id;

    private String mch_status;

    private String nonce_str;

    private String reason;

    private String result_code;

    private String result_msg;

    private String return_code;

    private String return_msg;

    private String sign;

    private String sub_mch_id;

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getMch_id() {
        return this.mch_id;
    }

    public void setMch_status(String mch_status) {
        this.mch_status = mch_status;
    }

    public String getMch_status() {
        return this.mch_status;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getNonce_str() {
        return this.nonce_str;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_code() {
        return this.result_code;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    public String getResult_msg() {
        return this.result_msg;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_code() {
        return this.return_code;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getReturn_msg() {
        return this.return_msg;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    public String getSub_mch_id() {
        return this.sub_mch_id;
    }

}
