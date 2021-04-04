package com.mmtax.business.dto;

import lombok.Data;

@Data
public class UpdateMobileDTO {
    private Integer customerId;
    private String newMobile;
    private String newMobileSms;
}
