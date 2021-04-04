package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChangeTaxSourceDTO {
    private Integer customerId;
    private String customerNo;
    private Integer merchantId;
    private String merchantName;
    private Integer taxSourceId;
    private String taxSourceName;
    private BigDecimal balance;
}
