package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankcardAlipayInfoDTO {
    private String accountNo;
    private String bankCode;
    private String bankName;
    private BigDecimal withdrawAmount;
    private String bankImage;
}
