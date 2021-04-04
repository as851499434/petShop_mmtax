package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CusWithdrawDTO {
    private Integer customerId;
    private String withdrawPass;
    private BigDecimal amount;
    private String accountNo;
}
