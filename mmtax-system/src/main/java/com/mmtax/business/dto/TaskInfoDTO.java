package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TaskInfoDTO {
    private Integer taskInfoId;
    private String taskInfo;
    private List<TaskDetailDTO> details;
    private String taskRequireCompleteTime;
    private Integer allTaskNum;
    private BigDecimal allAmount;

    @Data
    public static class TaskDetailDTO {
        //单价
        private BigDecimal amount;
        private Integer taskNum;
        //总价
        private BigDecimal detailAmount;
    }
}
