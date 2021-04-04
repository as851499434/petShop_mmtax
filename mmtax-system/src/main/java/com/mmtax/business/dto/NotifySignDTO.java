package com.mmtax.business.dto;

import lombok.Data;

@Data
public class NotifySignDTO {
    /**
     * 返回描述
     */
    private String description;
    /**
     * 签名
     */
    private String sign;

    /**
     * 响应码
     */
    private String code;
    /**
     * 请求时间
     */
    private String transTime;
    /**
     * api签约流水号
     */
    private String orderId;
}
