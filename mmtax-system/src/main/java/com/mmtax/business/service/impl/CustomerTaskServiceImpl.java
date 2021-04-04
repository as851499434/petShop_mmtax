package com.mmtax.business.service.impl;

import ch.qos.logback.core.CoreConstants;
import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.BetweenFormater.Level;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.BatchTaskDetail;
import com.mmtax.business.domain.MerchantTaskInfo;
import com.mmtax.business.dto.CustomerTaskDTO;
import com.mmtax.business.dto.CustomerTaskDetailDTO;
import com.mmtax.business.dto.CustomerTaskResultDTO;
import com.mmtax.business.mapper.BatchTaskDetailMapper;
import com.mmtax.business.mapper.CustomerTaskMapper;
import com.mmtax.business.mapper.MerchantTaskInfoMapper;
import com.mmtax.common.enums.DeleteEnum;
import com.mmtax.common.enums.TaskStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.DateUtils;
import com.mmtax.common.utils.redis.RedisLoaderUtils;
import com.mmtax.common.utils.redis.RedisUtil;
import com.mysql.cj.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.ICustomerTaskService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务接单记录 服务层实现
 *
 * @author meimiao
 * @date 2020-10-15
 */
@Service
@Slf4j
public class CustomerTaskServiceImpl implements ICustomerTaskService {

    private static String SHUISHUITONG = "shuishuitong";

    @Autowired
    CustomerTaskMapper customerTaskMapper;
    @Autowired
    BatchTaskDetailMapper batchTaskDetailMapper;
    @Autowired
    MerchantTaskInfoMapper merchantTaskInfoMapper;

    /**
     * 模糊搜索派单任务
     *
     * @return
     */
    @Override
    public List<CustomerTaskResultDTO> listTask(CustomerTaskDTO dto) {
        return customerTaskMapper.lsitTask(dto);
    }

    /**
     * 模糊搜索派单任务
     *
     * @param customerTaskId tbl_batch_task_detail 表中id
     * @return
     */
    @Override
    public CustomerTaskDetailDTO queryTaskDetail(Integer customerTaskId) {
        if (null == customerTaskId) {
            throw new BusinessException("非正常手段,查询失败");
        }
        CustomerTaskDetailDTO customerTaskDetailDTO = batchTaskDetailMapper.queryTaskDetail(customerTaskId);

        MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
        merchantTaskInfo.setMerchantId(customerTaskDetailDTO.getMerchantId());
        merchantTaskInfo.setTaskId(customerTaskDetailDTO.getTaskInfoId());
        merchantTaskInfo.setDelStatus(DeleteEnum.UN_DELETE.getCode());
        merchantTaskInfo = merchantTaskInfoMapper.selectOne(merchantTaskInfo);

        if (merchantTaskInfo == null) {
            customerTaskDetailDTO.setTask_info("");
        } else {
            customerTaskDetailDTO.setTask_info(merchantTaskInfo.getTaskInfo());
        }

        //设置剩余时间
        DateTime currentTime = DateUtil.parse(DateUtil.now(), DateUtils.YYYY_MM_DD_HH_MM_SS);
        DateTime endTime = DateUtil.parse(customerTaskDetailDTO.getTaskRequireCompleteTime(), DateUtils.YYYY_MM_DD_HH_MM_SS);
        String remainTime;
        if (endTime.isBeforeOrEquals(currentTime)
                || TaskStatusEnum.FINISH.getCode().equals(customerTaskDetailDTO.getTaskStatus())) {
            remainTime = "0";
        } else {
            remainTime = DateUtil.formatBetween(currentTime, endTime, Level.HOUR);
        }
        customerTaskDetailDTO.setRemainTime(remainTime);

        return customerTaskDetailDTO;
    }

    /**
     * 模糊搜索派单任务
     *
     * @param customerTaskId tbl_batch_task_detail 表中id
     * @return
     */
    @Override
    public void updateTaskStatusComplete(Integer customerTaskId) {
        if (null == customerTaskId) {
            throw new BusinessException("非正常手段,更新失败");
        }
        batchTaskDetailMapper.updateTaskDetailStatus(TaskStatusEnum.FINISH.getCode(), customerTaskId, DateUtil.now());
    }

    /**
     * 判断该身份证号是否下载过税税通
     * true:表示 没有下载过，保存下载痕迹并返回true
     * false:表示已经下载过了
     */
    public boolean isDownLoad(String certificateNo) {
        String key = "SHUISHUITONG_" + certificateNo;
        if (null == RedisLoaderUtils.get(key)) {
            RedisLoaderUtils.set(key, key);
            return true;
        }
        return false;
    }
}
