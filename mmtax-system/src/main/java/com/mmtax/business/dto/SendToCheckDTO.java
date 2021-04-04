package com.mmtax.business.dto;

import lombok.Data;

import java.util.List;

@Data
public class SendToCheckDTO {
    private Integer merchantId;
    private String batchNo;
    List<CheckDataDTO> checkDatas;
}
