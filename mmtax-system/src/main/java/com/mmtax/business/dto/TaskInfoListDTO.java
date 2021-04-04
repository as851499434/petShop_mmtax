package com.mmtax.business.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskInfoListDTO {
    private Integer merchantId;
    private List<TaskInfoDTO> taskInfos;
}
