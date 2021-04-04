package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MakeUpChargeDTO {
    private String customerName;
    private String merchantName;
    private String idCardNo;
    private BigDecimal monthPayAmount;
    private BigDecimal amount;
    private List<MakeUpChargeDetailDTO> makeUpChargeDetailDTOs;
}
