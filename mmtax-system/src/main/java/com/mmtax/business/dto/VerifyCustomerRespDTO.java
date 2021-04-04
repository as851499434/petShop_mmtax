package com.mmtax.business.dto;

import lombok.Data;

@Data
public class VerifyCustomerRespDTO {
    private Integer customerId;
    private Boolean isVerifyFlag;
    private Boolean isSignFlag;
}
