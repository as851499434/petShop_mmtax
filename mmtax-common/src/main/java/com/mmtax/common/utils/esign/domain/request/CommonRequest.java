package com.mmtax.common.utils.esign.domain.request;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class CommonRequest {
    private Integer code;
    private String message;
    private JSONObject data;
}
