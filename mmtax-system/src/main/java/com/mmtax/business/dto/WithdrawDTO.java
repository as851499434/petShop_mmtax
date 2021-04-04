package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawDTO {
    private Integer sourceId;
    private BigDecimal money;
}
