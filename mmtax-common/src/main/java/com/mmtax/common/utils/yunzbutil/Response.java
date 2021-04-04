package com.mmtax.common.utils.yunzbutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author liyufeng
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Response {

    /**
     * 待签名参数对
     */
    private String pairsWithKey;

    /**
     * 签名值
     */
    private String signValue;

    /**
     * 请求数据
     */
    private String reqData;

    /**
     * 返回数据
     */
    private String respData;

    /**
     * 返回参数验签结果
     */
    private Boolean verifyResult;


    public Response() {
    }

    public String getPairsWithKey() {
        return pairsWithKey;
    }

    public void setPairsWithKey(String pairsWithKey) {
        this.pairsWithKey = pairsWithKey;
    }

    public String getSignValue() {
        return signValue;
    }

    public void setSignValue(String signValue) {
        this.signValue = signValue;
    }

    public String getReqData() {
        return reqData;
    }

    public void setReqData(String reqData) {
        this.reqData = reqData;
    }

    public void setReqData(Object reqData) throws JsonProcessingException {
        this.reqData = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(reqData);
    }

    public String getRespData() {
        return respData;
    }

    public void setRespData(String respData) {
        this.respData = respData;
    }

    public Boolean getVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(Boolean verifyResult) {
        this.verifyResult = verifyResult;
    }
}
