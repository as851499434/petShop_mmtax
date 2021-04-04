package com.mmtax.business.service.impl;

import com.mmtax.business.domain.TaskInfo;
import com.mmtax.business.mapper.TaskInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.ITaskInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务初始化记录 服务层实现
 * 
 * @author meimiao
 * @date 2020-10-13
 */
@Service
public class TaskInfoServiceImpl implements ITaskInfoService {
    @Resource
    private TaskInfoMapper taskInfoMapper;

    @Override
    public List<TaskInfo> listAllTaskInfo() {
        return taskInfoMapper.selectAll();
    }

    @Override
    public String getTaskInfoById(Integer id) {
        TaskInfo info = taskInfoMapper.selectByPrimaryKey(id);
        return info.getTaskInfo();
    }
}
