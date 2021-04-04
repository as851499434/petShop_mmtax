package com.mmtax.business.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiSignStatusDTO {
    private String notifyType;
    private String code;
    private String message;
    private String description;
    private String sign;
    private String name;
    private String idCardNo;
    private String status;
    private String mobileNo;
    private String orderId;
}
