package com.mmtax.quartz.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.BatchTaskDetail;
import com.mmtax.business.domain.BatchTaskRecord;
import com.mmtax.business.domain.Cooperation;
import com.mmtax.business.mapper.BatchTaskDetailMapper;
import com.mmtax.business.mapper.BatchTaskRecordMapper;
import com.mmtax.business.mapper.CooperationMapper;
import com.mmtax.common.enums.SendOrderModeEnum;
import com.mmtax.common.enums.SwitchEnum;
import com.mmtax.common.enums.TaskStatusEnum;
import com.mmtax.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：YH
 * @Date：2020/10/16 14:50
 */
@Component("HandleChangeTaskStatusJob")
@Slf4j
public class HandleChangeTaskStatusJob {

    @Autowired
    BatchTaskDetailMapper batchTaskDetailMapper;
    @Autowired
    BatchTaskRecordMapper batchTaskRecordMapper;
    @Autowired
    CooperationMapper cooperationMapper;

    public void handleChangeTaskStatusJob() {
        log.info("开始处理handleChangeTaskStatusJob");
        //tbl_batch_task_detail将超过时间 待完成的记录 修改为 未完成
        BatchTaskDetail batchTaskDetail= new BatchTaskDetail();
        batchTaskDetail.setTaskStatus(TaskStatusEnum.UNFINISH.getCode());
        List<BatchTaskDetail> batchTaskDetails = batchTaskDetailMapper.select(batchTaskDetail);
        for (BatchTaskDetail taskDetail : batchTaskDetails) {
            DateTime currentTime = DateUtil.parse(DateUtil.now(), DateUtils.YYYY_MM_DD_HH_MM_SS);
            DateTime taskRequireCompleteTime = DateUtil.parse(taskDetail.getTaskRequireCompleteTime(),
                    DateUtils.YYYY_MM_DD_HH_MM_SS);
            if (taskRequireCompleteTime.isBeforeOrEquals(currentTime)) {
                batchTaskDetailMapper.updateTaskDetailStatus(TaskStatusEnum.UNCOMPLETE.getCode(),taskDetail.getId(),DateUtil.now());
            }
        }

        //tbl_batch_task_record 将超过时间 待完成的记录 修改为 已完成
        //若派单开关打开，派单模式 为自动,更新关联tbl_batch_task_detail的 的任务状态 为 已完成
        BatchTaskRecord batchTaskRecord = new BatchTaskRecord();
        batchTaskRecord.setTaskStatus(TaskStatusEnum.UNFINISH.getCode());
        List<BatchTaskRecord> batchTaskRecords = batchTaskRecordMapper.select(batchTaskRecord);
        for(BatchTaskRecord batchTask:batchTaskRecords){
            DateTime currentTime = DateUtil.parse(DateUtil.now(), DateUtils.YYYY_MM_DD_HH_MM_SS);
            DateTime endTime = DateUtil.parse(batchTask.getTaskRequireCompleteTime(), DateUtils.YYYY_MM_DD_HH_MM_SS);
            if(endTime.isBeforeOrEquals(currentTime)){
                batchTaskRecordMapper.updateTaskRecordStatus(TaskStatusEnum.FINISH.getCode(),batchTask.getId(),DateUtil.now());

                Cooperation cooperation = new Cooperation();
                cooperation.setMerchantId(batchTask.getMerchantId());
                cooperation =  cooperationMapper.selectOne(cooperation);
                if(SwitchEnum.ON.getCode().equals(cooperation.getSendOrderSwitch()) &&
                        SendOrderModeEnum.AUTOMATION.getCode().equals(cooperation.getSendOrderMode()) ){
                    BatchTaskDetail batchTaskDetailQuery = new BatchTaskDetail();
                    batchTaskDetailQuery.setBatchTaskRecordId(batchTask.getId());
                    List<BatchTaskDetail> taskDetails = batchTaskDetailMapper.select(batchTaskDetailQuery);
                    for(BatchTaskDetail taskDetail:taskDetails){
                        batchTaskDetailMapper.updateTaskDetailStatus(TaskStatusEnum.FINISH.getCode(),taskDetail.getId(),DateUtil.now());
                    }
                }
            }

        }
        log.info("handleChangeTaskStatusJob处理完成");
    }
}
