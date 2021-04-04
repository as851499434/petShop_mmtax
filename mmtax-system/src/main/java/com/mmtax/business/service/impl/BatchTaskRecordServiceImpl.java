package com.mmtax.business.service.impl;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IBatchTaskDetailService;
import com.mmtax.business.service.IBatchTaskRecordService;
import com.mmtax.business.service.ICustomerInfoService;
import com.mmtax.common.enums.DelStatusEnum;
import com.mmtax.common.enums.TakeTaskStatusEnum;
import com.mmtax.common.enums.TaskStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.exception.PaymentException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.DateUtils;
import com.mmtax.common.utils.RandomNoUtil;
import com.mmtax.common.utils.RegexUtil;
import com.mmtax.system.domain.SysConfig;
import com.mmtax.system.domain.SysDictData;
import com.mmtax.system.service.ISysConfigService;
import com.mmtax.system.service.ISysDictDataService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 任务批次记录 服务层实现
 *
 * @author meimiao
 * @date 2020-10-15
 */
@Service
public class BatchTaskRecordServiceImpl extends CommonServiceImpl implements IBatchTaskRecordService
{
    @Autowired
    BatchTaskRecordMapper batchTaskRecordMapper;
    @Autowired
    TaskInfoMapper taskInfoMapper;
    @Autowired
    OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    BatchTaskDetailMapper batchTaskDetailMapper;
    @Autowired
    IBatchTaskDetailService batchTaskDetailService;
    @Autowired
    MerchantInfoMapper merchantInfoMapper;
    @Autowired
    ICustomerInfoService customerInfoService;
    @Autowired
    CustomerTaskMapper customerTaskMapper;
    @Autowired
    CustomerInfoMapper customerInfoMapper;
    @Autowired
    MerchantTaskInfoMapper merchantTaskInfoMapper;
    @Autowired
    ISysConfigService sysConfigService;

    @Override
    public Page listBathTaskRecordInfo(BatchTaskRecordQueryDTO queryDTO) {
        QueryPage queryPage = convertQueryPage(queryDTO.getCurrentPage(),queryDTO.getPageSize());
        queryDTO.setStartIndex(queryPage.getStartIndex());
        queryDTO.setCurrentPage(queryPage.getPageSize());
        int count = batchTaskRecordMapper.countBathTaskRecord(queryDTO);
        List<BatchTaskRecordDTO> batchTaskRecords = batchTaskRecordMapper.listBathTaskRecordInfo(queryDTO);
        for (BatchTaskRecordDTO batchTaskRecord : batchTaskRecords) {
            //设置任务简介
            String taskInfo =
                    batchTaskRecordMapper.getTaskInfo(batchTaskRecord.getMerchantId(), batchTaskRecord.getTaskInfoId());
            batchTaskRecord.setTaskInfo(taskInfo);
            //设置剩余时间
            DateTime currentTime = DateUtil.parse(DateUtil.now(), DateUtils.YYYY_MM_DD_HH_MM_SS);
            DateTime endTime = DateUtil.parse(batchTaskRecord.getTaskRequireCompleteTime(), DateUtils.YYYY_MM_DD_HH_MM_SS);
            String remainTime ;
            if(endTime.isBeforeOrEquals(currentTime)){
                remainTime = "0";
            }else{
                remainTime = DateUtil.formatBetween(currentTime, endTime, BetweenFormater.Level.HOUR);
            }
            batchTaskRecord.setRemainTime(remainTime);
        }
        return new Page(count, batchTaskRecords, queryPage.getCurrentPage(), queryPage.getPageSize());
    }

    @Override
    public List<BatchTaskRecordDTO> getBathTaskRecordInfo(BatchTaskRecordQueryDTO queryDTO) {
        return batchTaskRecordMapper.getBathTaskRecordInfo(queryDTO);
    }

    @Override
    public BatchTaskRecord covertBatchTaskRecord(Integer merchantId, Integer taskInfoId, Integer num
            ,BigDecimal allAmount, String requireCompleteTime){
        BatchTaskRecord taskRecord = new BatchTaskRecord();
        taskRecord.setMerchantId(merchantId);
        taskRecord.setTaskInfoId(taskInfoId);
        taskRecord.setBatchNo(RandomNoUtil.getRandomNo(7));
        taskRecord.setTaskTotalNum(num);
        taskRecord.setTaskAmount(allAmount);
        taskRecord.setTaskBeginTime(DateUtil.formatDateTime(DateUtil.date()));
        taskRecord.setTaskRequireCompleteTime(requireCompleteTime);
        taskRecord.setTaskStatus(TaskStatusEnum.UNFINISH.getCode());
        TaskInfo taskInfo = taskInfoMapper.selectByPrimaryKey(taskInfoId);
        taskRecord.setTaskName(taskInfo.getTaskName());
        taskRecord.setCreateTime(DateUtil.date());
        taskRecord.setUpdateTime(DateUtil.date());
        batchTaskRecordMapper.insertSelective(taskRecord);
        return taskRecord;
    }

    @Override
    public void uploadTaskDemo(TaskInfoListDTO dto) {
        Integer merchantId = dto.getMerchantId();
        String idStr = sysConfigService.selectConfigByKey("dispatch_customer");
        String[] ids = idStr.split(",");
//        Integer[] ids = {114,113,112};
        List<Integer> taskIds = new ArrayList<>();
        dto.getTaskInfos().forEach(one->{
            if(null == one.getTaskInfoId()){
                throw new BusinessException("任务名称不可为空");
            }
            if(StringUtils.isEmpty(one.getTaskInfo())){
                throw new BusinessException("任务介绍不可为空");
            }
            if(one.getTaskInfo().length() > 200){
                throw new BusinessException("任务介绍不可超过200字");
            }
            if(taskIds.contains(one.getTaskInfoId())){
                throw new BusinessException("请上传不同的任务名称");
            }
            if(null == one.getAllTaskNum()){
                throw new BusinessException("请填写总数");
            }
            if(StringUtils.isEmpty(one.getTaskRequireCompleteTime())){
                throw new BusinessException("请填写要求完成时间");
            }
            for(TaskInfoDTO.TaskDetailDTO detailDTO:one.getDetails()){
                if(null == detailDTO.getAmount() || null == detailDTO.getTaskNum()
                        || null == detailDTO.getDetailAmount()){
                    throw new BusinessException("请填写任务单价/数量");
                }
            }
            taskIds.add(one.getTaskInfoId());
        });
        dto.getTaskInfos().forEach(one->{
            DateTime dateTime = DateUtil.date();
            if(DateUtil.parseDateTime(one.getTaskRequireCompleteTime()).compareTo(dateTime) <= 0){
                throw new BusinessException("要求完成时间需大于当前时间");
            }
            int count = one.getDetails().stream().reduce(0,(a,b)->a+b.getTaskNum(),(a,b)->0);
            BatchTaskRecord taskRecord = covertBatchTaskRecord(merchantId,one.getTaskInfoId(),count
                    ,one.getAllAmount(),one.getTaskRequireCompleteTime());
            //更新任务介绍
            MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
            merchantTaskInfo.setMerchantId(merchantId);
            merchantTaskInfo.setTaskId(one.getTaskInfoId());
            merchantTaskInfo.setDelStatus(DelStatusEnum.NORMAL.code);
            merchantTaskInfo = merchantTaskInfoMapper.selectOne(merchantTaskInfo);
            merchantTaskInfo.setTaskInfo(one.getTaskInfo());
            merchantTaskInfoMapper.updateByPrimaryKey(merchantTaskInfo);
            int i = ids.length;
            for(TaskInfoDTO.TaskDetailDTO detailDTO:one.getDetails()){
                BatchTaskDetail batchTaskDetail = batchTaskDetailService.covertBatchTaskDetail(taskRecord
                        ,detailDTO.getAmount(),detailDTO.getTaskNum()
                        ,detailDTO.getDetailAmount());
                i = i - 1;
                if(i < 0){
                    batchTaskDetail.setTakeTaskStatus(TakeTaskStatusEnum.NOTAKE.getCode());
                    batchTaskDetailMapper.updateByPrimaryKeySelective(batchTaskDetail);
                    continue;
                }
                Integer customerId = Integer.parseInt(ids[i]);
                CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
                CustomerTask customerTask = new CustomerTask();
                customerTask.setTaskDetailId(batchTaskDetail.getId());
                customerTask.setCustomerId(customerInfo.getId());
                customerTask.setName(customerInfo.getRealName());
                customerTask.setCertificateNo(customerInfo.getCertificateNo());
                customerTask.setMobileNo(customerInfo.getMobile());
                customerTask.setCreateTime(DateUtil.date());
                customerTask.setUpdateTime(DateUtil.date());
                customerTaskMapper.insertSelective(customerTask);
            }
        });
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void uploadTask(MultipartFile file, UploadTaskDTO dto) throws IOException {
        if (file == null) {
            throw new BusinessException("派单上传文件不能为空");
        }

        Integer merchantId = dto.getMerchantId();
        //查询商户信息
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);

        BatchTaskRecord batchTaskRecord = new BatchTaskRecord();
        //解析excel
        ExcelReader excelReader = ExcelUtil.getReader(file.getInputStream());
        //读取第三行到第三列
        List<List<Object>> read = excelReader.read(2, 2);
        Integer recordNum = 0;
        for (List<Object> objects : read) {
            if (objects.get(0) == null || StringUtils.isEmpty(objects.get(0).toString())) {
                throw new BusinessException("请填写批次号");
            }
            BatchTaskRecord query = new BatchTaskRecord();
            query.setBatchNo(objects.get(0).toString());
//            query.setMerchantId(merchantId);
            int count = batchTaskRecordMapper.selectCount(query);
            if (count > 0) {
                throw new BusinessException("批次号已存在");
            }
            if(objects.get(4) == null || StringUtils.isEmpty(objects.get(4).toString())){
                throw new BusinessException("请填写任务总金额");
            }
            if(objects.get(2) == null || StringUtils.isEmpty(objects.get(2).toString())){
                throw new BusinessException("请填写任务数量");
            }
            if(objects.get(3) == null || StringUtils.isEmpty(objects.get(3).toString())){
                throw new BusinessException("请填写任务条数");
            }
            batchTaskRecord.setTaskAmount(BigDecimal.valueOf(Double.parseDouble(objects.get(4).toString())));
            batchTaskRecord.setBatchNo(objects.get(0).toString());
            logger.info("任务数量：{},总额：{}",objects.get(2).toString(),objects.get(4).toString());
            batchTaskRecord.setTaskTotalNum(Integer.parseInt(objects.get(2).toString()));
            recordNum = Integer.parseInt(objects.get(3).toString());
        }
        TaskInfo taskInfo = taskInfoMapper.selectByPrimaryKey(dto.getTaskInfoId());
        batchTaskRecord.setTaskStatus(TaskStatusEnum.UNFINISH.getCode());
        batchTaskRecord.setTaskInfoId(taskInfo.getId());
        batchTaskRecord.setTaskName(taskInfo.getTaskName());
        batchTaskRecord.setTaskBeginTime(DateUtil.formatDateTime(DateUtil.date()));
        //完成时间写死三天后
        batchTaskRecord.setTaskRequireCompleteTime(
                DateUtil.formatDateTime(DateUtil.offsetDay(DateUtil.date(),3)));
        batchTaskRecord.setMerchantId(merchantId);
        batchTaskRecord.setCreateTime(DateUtil.date());
        batchTaskRecord.setUpdateTime(DateUtil.date());
        batchTaskRecordMapper.insertSelective(batchTaskRecord);

        List<List<Object>> detailRead = excelReader.read(4, excelReader.getRowCount());
        List<Map<BatchTaskDetail, CustomerTask>> detailLinkCustomer = new ArrayList<>();
        if (CollectionUtils.isEmpty(detailRead)) {
            throw new BusinessException("批量代付上传文件内容不能为空");
        }
        List<String> idCardNos = new ArrayList<>();
        for (int begin = 0 ; begin < recordNum;begin++) {
            List<Object> objects = detailRead.get(begin);
            logger.info("该行：{}",JSON.toJSONString(objects));
            int subcript = begin + 1;
            if (objects.get(0) == null || "".equals(objects.get(0).toString().replace(" ", ""))) {
                throw new BusinessException("第"+subcript+"条数据的单价不可为空");
            }
            //任务单价
            BigDecimal taskPrice = new  BigDecimal(objects.get(0).toString());
            if(taskPrice.compareTo(BigDecimal.ZERO)<=0){
                throw new BusinessException("第"+subcript+"条数据的单价小于等于0");
            }
            if (objects.get(1) == null || "".equals(objects.get(1).toString().replace(" ", ""))) {
                throw new BusinessException("第"+subcript+"条数据的任务数量不可为空");
            }
            //任务数量
            Integer taskNum = Integer.parseInt(objects.get(1).toString());
            if(taskNum.compareTo(1)<0){
                throw new BusinessException("第"+subcript+"条数据的任务数量小于1");
            }
            if (objects.get(2) == null || "".equals(objects.get(2).toString().replace(" ", ""))) {
                throw new BusinessException("第"+subcript+"条数据的任务总价不可为空");
            }
            //任务总价
            BigDecimal taskTotalPrice = new  BigDecimal(objects.get(2).toString());
            if(taskTotalPrice.compareTo(BigDecimal.ZERO)<=0){
                throw new BusinessException("第"+subcript+"条数据的任务总价小于等于0");
            }
            if (objects.get(3) == null || "".equals(objects.get(3).toString().replace(" ", ""))) {
                throw new BusinessException("第"+subcript+"条数据的接任务人不可为空");
            }
            if (objects.get(4) == null || "".equals(objects.get(4).toString().replace(" ", ""))) {
                throw new BusinessException("第"+subcript+"条数据的身份证不可为空");
            }
            //接任务人身份证
            String idCardNoTemp = com.mmtax.common.utils.StringUtils.trimAll(objects.get(4).toString());
            if (!IdcardUtil.isValidCard(idCardNoTemp)) {
                logger.info("身份证号格式为17位数字+X或数字idCardNo:{}", idCardNoTemp);
                throw new BusinessException("姓名为" + objects.get(3) + "的接任务人,身份证号填写错误！");
            }
            if (objects.get(5) == null || "".equals(objects.get(5).toString().replace(" ", ""))) {
                throw new BusinessException("第"+subcript+"条数据的手机号不可为空");
            }
            //承接人手机号
            String mobileTemp = com.mmtax.common.utils.StringUtils.trimAll(objects.get(5).toString());
            boolean mobileMatches = RegexUtil.regexPhoneLegalNo(mobileTemp);
            if (!mobileMatches) {
                throw new BusinessException("姓名为" + objects.get(3).toString() + "的接任务人,手机号错误！");
            }
            String name = objects.get(3).toString(),idCardNo = objects.get(4).toString().trim()
                    ,mobile = objects.get(5).toString();
            if(idCardNos.contains(idCardNo)) {
                throw new BusinessException("一个批次中不可有身份证相同的灵工");
            }
            logger.info("已存的身份证：{}",JSON.toJSONString(idCardNos));
            idCardNos.add(idCardNo);
            //获取员工(未注册的新建注册后获取)
            CustomerInfo customerInfo = null;
            try {
                customerInfo = customerInfoService.queryCustomerInfo(name, mobile, idCardNo, merchantId,null
                        ,null);
            } catch (BusinessException e) {
                logger.info("灵工账号注册失败:", e);
                throw new BusinessException(e.getMessage());
            } catch (PaymentException e) {
                logger.error(e.getMessage());
                throw new BusinessException(e.getMessage());
            } catch (Exception e) {
                logger.error("灵工账号注册系统错误：" ,e);
                throw new BusinessException("灵工账号注册系统错误,请联系开发人员");
            }
            if(null == customerInfo){
                throw new BusinessException("灵工账号未注册成功,请联系开发人员");
            }

            //记录BatchTaskDetail和CustomerTask表
            BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(objects.get(0).toString()));
            Integer num = Integer.parseInt(objects.get(1).toString());
            BigDecimal allAmount = BigDecimal.valueOf(Double.parseDouble(objects.get(2).toString()));
            BatchTaskDetail batchTaskDetail = batchTaskDetailService.covertBatchTaskDetail(batchTaskRecord,amount
                    ,num,allAmount);
            CustomerTask customerTask = new CustomerTask();
            customerTask.setTaskDetailId(batchTaskDetail.getId());
            customerTask.setCustomerId(customerInfo.getId());
            customerTask.setName(customerInfo.getRealName());
            customerTask.setCertificateNo(customerInfo.getCertificateNo());
            customerTask.setMobileNo(customerInfo.getMobile());
            customerTask.setCreateTime(DateUtil.date());
            customerTask.setUpdateTime(DateUtil.date());
            customerTaskMapper.insertSelective(customerTask);
        }
    }
}
