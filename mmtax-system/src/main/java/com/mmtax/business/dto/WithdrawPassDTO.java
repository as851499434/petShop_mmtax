package com.mmtax.business.dto;

import lombok.Data;

@Data
public class WithdrawPassDTO {
    private Integer customerId;
    private String mobile;
    private String smsCode;
    private String oldPass;
    private String newPass;
    private String confirmPass;
}
