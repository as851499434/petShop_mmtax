package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BatchWithDrawToCardDTO {
    private String fileName;
    private String batchDate;
    private BigDecimal totalAmount;
    private Integer totalCount;

}
