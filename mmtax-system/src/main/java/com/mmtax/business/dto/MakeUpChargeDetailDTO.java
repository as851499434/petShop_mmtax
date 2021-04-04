package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MakeUpChargeDetailDTO {
    private String name;
    private BigDecimal chargeMakeUp;
    private BigDecimal paymentMount;
    private String createTime;
}
