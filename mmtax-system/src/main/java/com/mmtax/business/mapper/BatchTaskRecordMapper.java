package com.mmtax.business.mapper;

import com.mmtax.business.domain.BatchTaskRecord;
import com.mmtax.business.dto.BatchTaskDetailDTO;
import com.mmtax.business.dto.BatchTaskRecordDTO;
import com.mmtax.business.dto.BatchTaskRecordQueryDTO;
import com.mmtax.business.dto.TaskCompletedDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务批次记录 数据层
 *
 * @author meimiao
 * @date 2020-10-15
 */
@Repository
public interface BatchTaskRecordMapper extends MyMapper<BatchTaskRecord>
{
    /**
     * 查询任务记录
     * @param queryDTO
     * @return
     */
    List<BatchTaskRecordDTO> listBathTaskRecordInfo(BatchTaskRecordQueryDTO queryDTO);
    /**
     * 获得任务记录条数
     * @param queryDTO
     * @return
     */
    Integer countBathTaskRecord(BatchTaskRecordQueryDTO queryDTO);

    /**
     * 任务记录查询
     * @param queryDTO
     * @return
     */
    List<BatchTaskRecordDTO> getBathTaskRecordInfo(BatchTaskRecordQueryDTO queryDTO);

    /**
     * 更新 任务状态根据 任务记录id
     * @param taskStatus
     * @param taskDetailId
     * @return
     */
    int updateTaskRecordStatus(@Param("taskStatus")Integer taskStatus, @Param("taskRecordId")Integer taskDetailId,
                               @Param("currentTime")String currentTime);

    /**
     * 查询任务简介
     * @param merchantId
     * @param taskId
     * @return
     */
    String getTaskInfo(@Param("merchantId")Integer merchantId, @Param("taskId")Integer taskId);
}