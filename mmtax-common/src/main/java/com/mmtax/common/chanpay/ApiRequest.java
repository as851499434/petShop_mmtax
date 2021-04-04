package com.mmtax.common.chanpay;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * Description:
 *
 * @author yingjie.wang
 * @since 17/4/14 上午11:04
 */
public class ApiRequest {

    /** 是否支持签名,默认为不支持 */
    private boolean supportSign = false;

    /** 加密算法类型: AES/3DES */
    private EncryptTypeEnum encryptType;

    /** 商户秘钥,用于签名及加密 */
    private String secretKey;

    /** 商户编号 */
    private String merchantNo;

    /** 请求参数, 按字典顺序排序 */
    private Map<String, String> paramMap = new TreeMap<String, String>();

    public ApiRequest() {}

    public ApiRequest(String merchantNo, String secretKey) {
        this.merchantNo = merchantNo;
        this.secretKey = secretKey;
    }

    public void addParam(String paramKey, String paramValue) {
        if (StringUtils.isBlank(paramKey)) {
            throw new IllegalArgumentException("paramKey不能为空");
        }
        /** 若为null,则转为"";若非null,则trim */
        this.paramMap.put(paramKey.trim(), (paramValue==null)?"":paramValue.trim());
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public boolean isSupportSign() {
        return supportSign;
    }

    public void setSupportSign(boolean supportSign) {
        this.supportSign = supportSign;
    }

    public Map<String, String> getParamMap() {
        return this.paramMap;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public EncryptTypeEnum getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(EncryptTypeEnum encryptType) {
        this.encryptType = encryptType;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("supportSign:").append(supportSign).append(", ")
                .append("encryptType:").append(encryptType.name()).append(", ")
                .append("merchantNo:").append(merchantNo).append(", ")
                .append("paramMap:").append(JSON.toJSONString(paramMap));
        return buffer.toString();
    }
}
