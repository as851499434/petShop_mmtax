package com.mmtax.business.service;

import com.mmtax.business.domain.BatchTaskDetail;
import com.mmtax.business.domain.BatchTaskRecord;
import com.mmtax.business.dto.BatchTaskDetailDTO;
import com.mmtax.business.dto.BatchTaskRecordQueryDTO;
import com.mmtax.common.page.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * 任务详细记录 服务层
 * 
 * @author meimiao
 * @date 2020-10-15
 */
public interface IBatchTaskDetailService
{

    BatchTaskDetail covertBatchTaskDetail(BatchTaskRecord taskRecord, BigDecimal amount, Integer num, BigDecimal allmount);

    /**
     * 发布完成记录查询
     * @param queryDTO
     * @return
     */
    Page listBatchTaskRecordDetailCompleted(BatchTaskRecordQueryDTO queryDTO);
    /**
     * 发布完成记录查询
     * @param queryDTO
     * @return
     */
    List<BatchTaskDetailDTO> batchTaskRecordDetailCompletedList(BatchTaskRecordQueryDTO queryDTO);
}
