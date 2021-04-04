package com.mmtax.business.dto;

import com.mmtax.common.page.Page;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IndexInfoDTO {
    private String usableAmount;
    private BigDecimal yearIncome;
    private BigDecimal monthIncome;
    private String customerName;
    private Page page;
}
