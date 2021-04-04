package com.mmtax.business.tianjindto;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/19 3:08 下午
 */
public class TianJinRequestResultDTO {

    private String resp_code;
    private String resp_message;
    private Object data;

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getResp_message() {
        return resp_message;
    }

    public void setResp_message(String resp_message) {
        this.resp_message = resp_message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TianJinRequestResultDTO{" +
                "resp_code='" + resp_code + '\'' +
                ", resp_message='" + resp_message + '\'' +
                ", data=" + data +
                '}';
    }
}
