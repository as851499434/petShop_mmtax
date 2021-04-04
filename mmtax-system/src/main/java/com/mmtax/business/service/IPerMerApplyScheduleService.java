package com.mmtax.business.service;

import com.mmtax.business.domain.PerMerApplySchedule;
import com.mmtax.business.dto.ApplyScheduleDTO;
import com.mmtax.common.enums.PerMerApplySchEnum;

import java.util.List;

/**
 * 个体工商户申请流程日志 服务层
 * 
 * @author meimiao
 * @date 2020-11-30
 */
public interface IPerMerApplyScheduleService
{
    /** 记录力流程信息 type 0-未知 1-提交资料审核 2-办理营业执照 3-办理完成 */
    void insertInfo(Integer permerId,PerMerApplySchEnum action, Integer type);

    List<ApplyScheduleDTO> getPerMerApplySchedule(Integer perMerId);
}
