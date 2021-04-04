package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class OnlineCommonResultDTO {
    private String is_success;
    private String charset;
    private String error_code;
    private String error_message;
    private String memo;
}
