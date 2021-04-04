package com.mmtax.business.dto;

import lombok.Data;

import java.util.List;

@Data
public class PayDataDTO {
    List<Integer> deleteIds;
    List<CheckDataDTO> checkDatas;
}
