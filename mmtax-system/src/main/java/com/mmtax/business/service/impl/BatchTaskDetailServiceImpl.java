package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.BatchTaskDetail;
import com.mmtax.business.domain.BatchTaskRecord;
import com.mmtax.business.domain.OnlinePaymentInfo;
import com.mmtax.business.dto.BatchTaskDetailDTO;
import com.mmtax.business.dto.BatchTaskRecordQueryDTO;
import com.mmtax.business.mapper.BatchTaskDetailMapper;
import com.mmtax.business.mapper.OnlinePaymentInfoMapper;
import com.mmtax.business.service.IBatchTaskDetailService;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.enums.TakeTaskStatusEnum;
import com.mmtax.common.enums.TaskStatusEnum;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 任务详细记录 服务层实现
 * 
 * @author meimiao
 * @date 2020-10-15
 */
@Service
public class BatchTaskDetailServiceImpl extends CommonServiceImpl implements IBatchTaskDetailService
{
    @Autowired
    OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    BatchTaskDetailMapper batchTaskDetailMapper;

    @Override
    public BatchTaskDetail covertBatchTaskDetail(BatchTaskRecord taskRecord,BigDecimal amount,Integer num,BigDecimal allmount) {
        BatchTaskDetail batchTaskDetail = new BatchTaskDetail();
        batchTaskDetail.setBatchTaskRecordId(taskRecord.getId());
        batchTaskDetail.setBatchNo(taskRecord.getBatchNo());
        batchTaskDetail.setTaskSerialNum(ChanPayUtil.generateOutTradeNo());
        batchTaskDetail.setTaskInfoId(taskRecord.getTaskInfoId());
        batchTaskDetail.setTaskName(taskRecord.getTaskName());
        batchTaskDetail.setAmount(amount);
        batchTaskDetail.setAllAmount(allmount);
        batchTaskDetail.setTaskNum(num);
        batchTaskDetail.setTaskStatus(TaskStatusEnum.UNFINISH.getCode());
        batchTaskDetail.setTaskRequireCompleteTime(taskRecord.getTaskRequireCompleteTime());
        batchTaskDetail.setMerchantId(taskRecord.getMerchantId());
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(taskRecord.getMerchantId());
        batchTaskDetail.setTaxSourceId(onlinePaymentInfo.getTaxSourceCompanyId());
        batchTaskDetail.setTakeTaskStatus(TakeTaskStatusEnum.TAKE.getCode());
        batchTaskDetail.setCreateTime(DateUtil.date());
        batchTaskDetail.setUpdateTime(DateUtil.date());
        batchTaskDetailMapper.insertSelective(batchTaskDetail);
        return batchTaskDetail;
    }

    @Override
    public Page listBatchTaskRecordDetailCompleted(BatchTaskRecordQueryDTO queryDTO) {
        QueryPage queryPage = convertQueryPage(queryDTO.getCurrentPage(),queryDTO.getPageSize());
        queryDTO.setStartIndex(queryPage.getStartIndex());
        queryDTO.setCurrentPage(queryPage.getPageSize());
        int count = batchTaskDetailMapper.countBatchTaskRecordDetailCompleted(queryDTO);
        List<BatchTaskDetailDTO> taskCompleteds = batchTaskDetailMapper.listBatchTaskRecordDetailCompleted(queryDTO);
        //隐藏信息
        for (BatchTaskDetailDTO taskCompleted : taskCompleteds) {
            String name = taskCompleted.getName();
            if(StringUtils.isNotEmpty(name)){
                int length = name.length();
                if(length == 2){
                    name = name.substring(0, 1) + "*";
                }else {
                    name = name.substring(0, 1) + "*" + name.substring(name.length() - 1);
                }
                taskCompleted.setName(name);
            }
            String mobileNo = taskCompleted.getMobileNo();
            if(StringUtils.isNotEmpty(mobileNo) && mobileNo.length()>7){
                mobileNo = mobileNo.substring(0,3)+"****"+mobileNo.substring(7);
                taskCompleted.setMobileNo(mobileNo);
            }
        }
        return new Page(count, taskCompleteds, queryPage.getCurrentPage(), queryPage.getPageSize());
    }

    @Override
    public List<BatchTaskDetailDTO> batchTaskRecordDetailCompletedList(BatchTaskRecordQueryDTO queryDTO) {
        return batchTaskDetailMapper.batchTaskRecordDetailCompletedList(queryDTO);
    }
}
