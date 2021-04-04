package com.mmtax.business.dto;

import com.mmtax.business.domain.WorkOrder;
import lombok.Data;

import java.util.List;

@Data
public class ApplyScheduleDTO {
    private Integer perMerId;
    /** 申请编号 */
    private String applyNumber;
    /** 时间 */
    private String submitTime;
    /** 动作(写死) */
    private String action;
    /** 第几个步骤 0-未知 1-提交资料审核 2-办理营业执照 */
    private Integer type;
    private String remark;

    private List<WorkOrder> workOrderList;
}
