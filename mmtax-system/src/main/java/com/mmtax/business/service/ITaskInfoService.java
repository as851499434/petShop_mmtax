package com.mmtax.business.service;

import com.mmtax.business.domain.TaskInfo;
import java.util.List;

/**
 * 任务初始化记录 服务层
 * 
 * @author meimiao
 * @date 2020-10-13
 */
public interface ITaskInfoService
{

    /**
     * 获取所有任务数据列表
     * @return 列表
     */
    List<TaskInfo> listAllTaskInfo();

    /**
     * 根据id获取任务简介
     * @param id 任务id
     * @return 简介
     */
    String getTaskInfoById(Integer id);
}
