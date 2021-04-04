package com.mmtax.business.mapper;

import com.mmtax.business.domain.MerchantTaskInfo;
import com.mmtax.business.dto.MerchantTaskInfoQueryDTO;
import com.mmtax.business.dto.TaskCompletedDTO;
import com.mmtax.business.dto.TaskRecordDTO;
import com.mmtax.common.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务记录 数据层
 * 
 * @author meimiao
 * @date 2020-10-13
 */
@Repository
public interface MerchantTaskInfoMapper extends MyMapper<MerchantTaskInfo> {
    /**
     * 查询所有完成的任务信息
     * @return 结果
     */
    List<TaskCompletedDTO> selectAllTaskInfo(MerchantTaskInfoQueryDTO queryDTO);
    /**
     * 查询任务发布记录
     * @param queryDTO 查询条件
     * @return 结果
     */
    List<TaskRecordDTO> selectTaskRecord(MerchantTaskInfoQueryDTO queryDTO);
}