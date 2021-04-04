package com.mmtax.common.chanpay;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * Description:
 *
 * @author yingjie.wang
 * @since 17/4/14 上午11:28
 */
public class ApiResponse {

    private Map<String, String> resultMap;

    private ApiStateEnum state;

    private String code;

    private String message;

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public ApiStateEnum getState() {
        return state;
    }

    public void setState(ApiStateEnum state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
