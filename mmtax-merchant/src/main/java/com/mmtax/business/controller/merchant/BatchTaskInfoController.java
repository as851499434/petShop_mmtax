package com.mmtax.business.controller.merchant;


import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.MerchantTaskInfo;
import com.mmtax.business.dto.*;
import com.mmtax.business.service.IBatchTaskDetailService;
import com.mmtax.business.service.IBatchTaskRecordService;
import com.mmtax.business.service.ICustomerTaskService;
import com.mmtax.business.service.IMerchantTaskInfoService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Api(tags = "商户端派单管理")
@Controller
@RequestMapping("/merchant/taskInfo")
public class BatchTaskInfoController extends BaseController {
    @Autowired
    IBatchTaskRecordService batchTaskRecordService;
    @Autowired
    IBatchTaskDetailService batchTaskDetailService;
    @Autowired
    ICustomerTaskService customerTaskService;
    @Autowired
    IMerchantTaskInfoService merchantTaskInfoService;

    @ApiOperation(value = "任务发布记录")
    @GetMapping("/listBatchTaskRecord")
    @ResponseBody
    public AjaxResult listBathTaskRecordInfo(BatchTaskRecordQueryDTO queryDTO){
        Page page;
        try {
            page = batchTaskRecordService.listBathTaskRecordInfo(queryDTO);
        } catch (BusinessException b) {
            logger.info("/merchant/taskInfo/listBathTaskRecordInfo:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/taskInfo/listBathTaskRecordInfo:{}", e);
            logger.error("/merchant/taskInfo/listBathTaskRecordInfo:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "导出任务发布记录")
    @GetMapping("/exportBathTaskRecordInfo")
    @ResponseBody
    public AjaxResult exportBathTaskRecordInfo(BatchTaskRecordQueryDTO queryDTO){
        List<BatchTaskRecordDTO> list = null;
        ExcelUtil<BatchTaskRecordDTO> util = new ExcelUtil<>(BatchTaskRecordDTO.class);
        try {
            list = batchTaskRecordService.getBathTaskRecordInfo(queryDTO);
        } catch (BusinessException b) {
            logger.info("/merchant/taskInfo/exportBathTaskRecordInfo:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/taskInfo/exportBathTaskRecordInfo:{}", e);
            logger.error("/merchant/taskInfo/exportBathTaskRecordInfo:{}", e);
            return error(e.getMessage());
        }
        return util.exportExcel(list,"任务发布记录");
    }

    @ApiOperation(value = "获取初始任务名称和简介")
    @GetMapping("/queryTaskInfo")
    @ResponseBody
    public AjaxResult queryTaskInfo(@RequestParam Integer merchantId){
        logger.info("/merchant/taskInfo/queryTaskInfo,入参：merchantId={}", merchantId);
        List<MerchantTaskInfo> result;
        try {
            result = merchantTaskInfoService.queryTaskInfo(merchantId);
        } catch (BusinessException b) {
            logger.info("/merchant/taskInfo/queryTaskInfo:", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/taskInfo/queryTaskInfo:", e);
            logger.error("/merchant/taskInfo/queryTaskInfo:", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    @ApiOperation(value = "上传派单信息")
    @PostMapping("/uploadTask")
    @ResponseBody
    public AjaxResult uploadTask(MultipartFile file,UploadTaskDTO dto){
        logger.info("/merchant/taskInfo/uploadTask,入参：{}", JSON.toJSONString(dto));
        try {
            batchTaskRecordService.uploadTask(file,dto);
        } catch (BusinessException b) {
            logger.info("/merchant/taskInfo/uploadTask:", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/taskInfo/uploadTask:", e);
            logger.error("/merchant/taskInfo/uploadTask:", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "派单模板上传汇总数据", response = MerchantBatchPaymentAmountDetailDTO.class)
    @GetMapping("getDetail")
    @ResponseBody
    public AjaxResult getDetail(@ApiParam(required = true, value = "商户id") Integer merchantId) {
//        MerchantBatchPaymentAmountDetailDTO detailDTO;
        try {
//            detailDTO = customerTaskService.getDetail(merchantId);
        } catch (BusinessException b) {
            logger.info("/merchant/taskInfo/getDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/taskInfo/getDetail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "上传派单信息(演示用)")
    @PostMapping("/uploadTaskDemo")
    @ResponseBody
    public AjaxResult uploadTaskDemo(@RequestBody TaskInfoListDTO dto){
        logger.info("/merchant/taskInfo/uploadTaskDemo,入参：{}", JSON.toJSONString(dto));
        try {
            batchTaskRecordService.uploadTaskDemo(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/taskInfo/uploadTaskDemo:", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/taskInfo/uploadTaskDemo:", e);
            logger.error("/merchant/taskInfo/uploadTaskDemo:", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "任务完成记录")
    @GetMapping("/listBatchTaskRecordDetialCompleted")
    @ResponseBody
    public AjaxResult listBatchTaskRecordDetailCompleted(BatchTaskRecordQueryDTO queryDTO){
        Page page;
        try {
            page = batchTaskDetailService.listBatchTaskRecordDetailCompleted(queryDTO);
        } catch (BusinessException b) {
            logger.info("/merchant/taskInfo/listBatchTaskRecordDetialCompleted:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/taskInfo/listBatchTaskRecordDetialCompleted:{}", e);
            logger.error("/merchant/taskInfo/listBatchTaskRecordDetialCompleted:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "导出任务完成记录")
    @GetMapping("/exportBatchTaskRecordDetailCompleted")
    @ResponseBody
    public AjaxResult exportBatchTaskRecordDetailCompleted(BatchTaskRecordQueryDTO queryDTO){
        List<BatchTaskDetailDTO> list = null;
        ExcelUtil<BatchTaskDetailDTO> util = new ExcelUtil<>(BatchTaskDetailDTO.class);
        try {
            list = batchTaskDetailService.batchTaskRecordDetailCompletedList(queryDTO);
        } catch (BusinessException b) {
            logger.info("/merchant/taskInfo/exportBatchTaskRecordDetailCompleted:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/taskInfo/exportBatchTaskRecordDetailCompleted:{}", e);
            logger.error("/merchant/taskInfo/exportBatchTaskRecordDetailCompleted:{}", e);
            return error(e.getMessage());
        }
        return util.exportExcel(list,"任务完成记录");
    }

}
