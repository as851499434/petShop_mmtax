package com.mmtax.common.utils.yunzbutil;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

/**
 * @author liyufeng
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseRequest {

    /**
     * 平台商户号
     */
    private String mchId = YunZBConstants.mchId;

    /**
     * 随机字符
     */
    private String nonceStr = buildNonceStr();

    /**
     * 请求时间
     */
    private String reqTime = buildReqTime();

    /**
     * 参数签名
     */
    private String sign;

    public BaseRequest() {
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 生成随机数
     *
     * @return
     */
    private String buildNonceStr() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 生成请求时间
     *
     * @return
     */
    private String buildReqTime() {
        return DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN);
    }
}
