package com.mmtax.business.service;

import com.mmtax.business.domain.CustomerTask;
import com.mmtax.business.dto.CustomerTaskDTO;
import com.mmtax.business.dto.CustomerTaskDetailDTO;
import com.mmtax.business.dto.CustomerTaskResultDTO;

import java.util.List;

/**
 * 任务接单记录 服务层
 * 
 * @author meimiao
 * @date 2020-10-15
 */
public interface ICustomerTaskService
{
    /**
     * 模糊搜索派单任务
     * @return
     */
    List<CustomerTaskResultDTO> listTask(CustomerTaskDTO dto);

    /**
     *  搜索派单任务 细节
     * @param customerTaskId tbl_batch_task_detail 表中id
     * @return
     */
    CustomerTaskDetailDTO queryTaskDetail(Integer customerTaskId);

    /**
     *  更新派单任务状态
     * @param customerTaskId tbl_batch_task_detail 表中id
     * @return
     */
    void updateTaskStatusComplete(Integer customerTaskId);

    /**
     * 判断该身份证号是否下载过税税通
     * true:表示 没有下载过，保存下载痕迹并返回true
     * false:表示已经下载过了
     */
    boolean isDownLoad(String certificateNo);

}
