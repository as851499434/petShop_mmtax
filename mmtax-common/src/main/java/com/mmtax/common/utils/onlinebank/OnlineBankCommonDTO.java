package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class OnlineBankCommonDTO {
    private String version = "2.1";
    private String charset = "UTF-8";
    private String signType = "TWSIGN";
    private String partnerId;
    private String notifyUrl;
    private String host;
    private String keyStoreName;
}
