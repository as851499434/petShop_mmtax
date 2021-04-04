package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.OrderStatusInfo;
import com.mmtax.business.domain.PerMerApplySchedule;
import com.mmtax.business.domain.PersonalMerchant;
import com.mmtax.business.domain.WorkOrder;
import com.mmtax.business.dto.ApplyScheduleDTO;
import com.mmtax.business.mapper.OrderStatusInfoMapper;
import com.mmtax.business.mapper.PerMerApplyScheduleMapper;
import com.mmtax.business.mapper.PersonalMerchantMapper;
import com.mmtax.business.mapper.WorkOrderMapper;
import com.mmtax.common.enums.PerMerApplySchEnum;
import com.mmtax.common.enums.PerMerStatusEnum;
import com.mmtax.common.enums.WorkOrderStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPerMerApplyScheduleService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 个体工商户申请流程日志 服务层实现
 * 
 * @author meimiao
 * @date 2020-11-30
 */
@Service
public class PerMerApplyScheduleServiceImpl implements IPerMerApplyScheduleService
{
    private static final String WORK_ORDER_PUBLISH = "生成待处理工单";
    private static final String WORK_ORDER_FEEDBACK = "反馈工单";
    private static final String WORK_ORDER_DISPIOSE = "处理工单";
    private static final String WORK_ORDER_CLOSE = "关闭工单";
    private static final Integer SUBMIT_APPLY_CODE = 1;
    private static final Integer SUBMI_AUDIT_CODE = 2;
    @Autowired
    PerMerApplyScheduleMapper perMerApplyScheduleMapper;
    @Autowired
    PersonalMerchantMapper personalMerchantMapper;
    @Autowired
    OrderStatusInfoMapper orderStatusInfoMapper;
    @Autowired
    WorkOrderMapper workOrderMapper;



    @Override
    public void insertInfo(Integer permerId, PerMerApplySchEnum action, Integer type) {
        PersonalMerchant personalMerchant = personalMerchantMapper.selectByPrimaryKey(permerId);
        PerMerApplySchedule perMerApplySchedule = new PerMerApplySchedule();
        perMerApplySchedule.setPerMerId(permerId);
        perMerApplySchedule.setApplyNumber(personalMerchant.getApplyNumber());
        perMerApplySchedule.setSubmitTime(DateUtil.formatDateTime(DateUtil.date()));
        perMerApplySchedule.setAction(action.getAction());
        perMerApplySchedule.setType(type);
        perMerApplySchedule.setCreateTime(DateUtil.date());
        perMerApplySchedule.setUpdateTime(DateUtil.date());
        perMerApplyScheduleMapper.insertSelective(perMerApplySchedule);
    }

    @Override
    public List<ApplyScheduleDTO> getPerMerApplySchedule(Integer perMerId) {
        if(perMerId == null){
            throw new BusinessException("没有perMerId");
        }

        PerMerApplySchedule query = new PerMerApplySchedule();
        query.setPerMerId(perMerId);
        List<PerMerApplySchedule> perMerApplySchedules = perMerApplyScheduleMapper.select(query);
        List<ApplyScheduleDTO> result = new ArrayList<>();

        //查询入网编号下的所有工单信息,为了添加工单的流程信息做准备
        List<WorkOrder> workOrderList = null;
        if(perMerApplySchedules != null && perMerApplySchedules.size() > 0 ){

            Example example = new Example(WorkOrder.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("applyNumber",perMerApplySchedules.get(0).getApplyNumber());

            List<Integer> list = new ArrayList<>();
            list.add(WorkOrderStatusEnum.PUBILSHED.getCode());
            list.add(WorkOrderStatusEnum.FEEDBACK.getCode());
            list.add(WorkOrderStatusEnum.DISPOSE.getCode());
            list.add(WorkOrderStatusEnum.CLOSE.getCode());
            criteria.andIn("status",list);
            workOrderList  = workOrderMapper.selectByExample(example);

        }
        //查询需要去提交的工单
        Example example = new Example(WorkOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("applyNumber",perMerApplySchedules.get(0).getApplyNumber());
        criteria.andEqualTo("status",WorkOrderStatusEnum.PUBILSHED.getCode());
        List<WorkOrder> finalWorkOrderList = workOrderMapper.selectByExample(example);
        perMerApplySchedules.forEach(one->{
            ApplyScheduleDTO applySchedule = new ApplyScheduleDTO();
            BeanUtils.copyProperties(one,applySchedule);
            OrderStatusInfo orderStatusInfo = new OrderStatusInfo();
            orderStatusInfo.setApplyId(one.getPerMerId());
            orderStatusInfo = orderStatusInfoMapper.selectOne(orderStatusInfo);
            applySchedule.setRemark(orderStatusInfo.getRemark());
            applySchedule.setWorkOrderList(finalWorkOrderList);
            result.add(applySchedule);
        });
        //快速返回
        if(workOrderList == null || workOrderList.size() == 0){
            return result;
        }
        //添加工单的流程信息
        for(WorkOrder workOrder:workOrderList){
            //工单发布
            ApplyScheduleDTO applySchedule = new ApplyScheduleDTO();
            applySchedule.setAction(WORK_ORDER_PUBLISH);
            applySchedule.setType(SUBMIT_APPLY_CODE);
            applySchedule.setSubmitTime(DateUtil.format(workOrder.getPublishTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
            result.add(applySchedule);
            //工单反馈
            if(workOrder.getFeedbackTime() != null){
                applySchedule = new ApplyScheduleDTO();
                applySchedule.setAction(WORK_ORDER_FEEDBACK);
                applySchedule.setType(SUBMIT_APPLY_CODE);
                applySchedule.setSubmitTime(DateUtil.format(workOrder.getFeedbackTime(),DateUtils.YYYY_MM_DD_HH_MM_SS));
                result.add(applySchedule);
            }
            //工单处理
            if(workOrder.getStatus().equals(WorkOrderStatusEnum.DISPOSE.getCode())){
                applySchedule = new ApplyScheduleDTO();
                applySchedule.setAction(WORK_ORDER_DISPIOSE);
                applySchedule.setType(SUBMIT_APPLY_CODE);
                applySchedule.setSubmitTime(DateUtil.format(workOrder.getUpdateTime(),DateUtils.YYYY_MM_DD_HH_MM_SS));
                result.add(applySchedule);
            }
            //工单关闭
            if(workOrder.getStatus().equals(WorkOrderStatusEnum.CLOSE.getCode())){
                applySchedule = new ApplyScheduleDTO();
                applySchedule.setAction(WORK_ORDER_CLOSE);
                applySchedule.setType(SUBMIT_APPLY_CODE);
                applySchedule.setSubmitTime(DateUtil.format(workOrder.getUpdateTime(),DateUtils.YYYY_MM_DD_HH_MM_SS));
                result.add(applySchedule);
            }

        }
        return result;
    }
}
