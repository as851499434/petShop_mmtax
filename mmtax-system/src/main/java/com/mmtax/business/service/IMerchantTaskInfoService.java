package com.mmtax.business.service;

import com.mmtax.business.domain.MerchantTaskInfo;
import com.mmtax.business.dto.MerchantTaskInfoQueryDTO;
import com.mmtax.business.dto.TaskCompletedDTO;
import com.mmtax.business.dto.TaskRecordDTO;

import java.util.List;

/**
 * 任务记录 服务层
 * 
 * @author meimiao
 * @date 2020-10-13
 */
public interface IMerchantTaskInfoService {
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

    /**
     * 根据商户id查询商户任务信息
     * @param merchantId 商户id
     * @return 结果
     */
    List<MerchantTaskInfo> selectMerchantTaskInfoByMerchantId(Integer merchantId);

    /**
     * 获取商户任务信息
     * @param merchantId
     * @return
     */
    List<MerchantTaskInfo> queryTaskInfo(Integer merchantId);

    /**
     * 处理数据 开启线上签约和线上签约可打款的用户，岗位统一为“市场推广”
     */
    void updateTaskInfo();
}
