package com.mmtax.business.service;

import com.mmtax.business.domain.BatchTaskRecord;
import com.mmtax.business.dto.BatchTaskRecordDTO;
import com.mmtax.business.dto.BatchTaskRecordQueryDTO;
import com.mmtax.business.dto.TaskInfoListDTO;
import com.mmtax.business.dto.UploadTaskDTO;
import com.mmtax.common.page.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 任务批次记录 服务层
 *
 * @author meimiao
 * @date 2020-10-15
 */
public interface IBatchTaskRecordService
{
    /**
     * 查询发布任务记录
     * @param queryDTO
     * @return
     */
    Page listBathTaskRecordInfo(BatchTaskRecordQueryDTO queryDTO);
    /**
     * 发布任务记录查询
     * @param queryDTO
     * @return
     */
    List<BatchTaskRecordDTO> getBathTaskRecordInfo(BatchTaskRecordQueryDTO queryDTO);

    /** 生成BatchTaskRecord */
    BatchTaskRecord covertBatchTaskRecord(Integer merchantId, Integer taskInfoId, Integer num
            , BigDecimal allAmount, String requireCompleteTime);

    /** 上传派单模板 */
    void uploadTask(MultipartFile file, UploadTaskDTO dto) throws IOException;

    /**
     * 上传派单任务(演示)
     * @param dto
     */
    void uploadTaskDemo(TaskInfoListDTO dto);
}
