package com.mmtax.web.controller.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mmtax.business.domain.MerchantTaskInfo;
import com.mmtax.business.dto.BatchTaskRecordDTO;
import com.mmtax.business.dto.MerchantTaskInfoQueryDTO;
import com.mmtax.business.dto.TaskCompletedDTO;
import com.mmtax.business.dto.TaskRecordDTO;
import com.mmtax.business.mapper.MerchantTaskInfoMapper;
import com.mmtax.business.service.IMerchantTaskInfoService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务记录 信息操作处理
 *
 * @author meimiao
 * @date 2020-10-13
 */
@Api(tags = "任务管理")
@Controller
@RequestMapping("/manager/merchantTaskInfo")
public class MerchantTaskInfoController extends BaseController {
    @Autowired
    private IMerchantTaskInfoService merchantTaskInfoService;

    private String prefix = "business/taskManager";

    @ApiOperation(value = "跳转到发布记录页面")
    @RequiresPermissions("manager:merchantTaskInfo:publishRecord")
    @GetMapping("/publishRecord")
    public String merchantPublishRecord() {
        return prefix + "/publishRecord";
    }

    @ApiOperation(value = "跳转到任务完成记录页面")
    @RequiresPermissions("manager:merchantTaskInfo:taskCompleted")
    @GetMapping("/taskCompleted")
    public String merchantTaskCompleted() {
        return prefix + "/taskCompleted";
    }

    @ApiOperation(value = "任务发布记录",response = TaskRecordDTO.class)
    @RequiresPermissions("manager:merchantTaskInfo:selectTaskRecord")
    @PostMapping("/selectTaskRecord")
    @ResponseBody
    public TableDataInfo selectTaskRecord(MerchantTaskInfoQueryDTO queryDTO) {
        startPage();
        List<TaskRecordDTO> taskCompletedDTOS = merchantTaskInfoService.selectTaskRecord(queryDTO);
        return getDataTable(taskCompletedDTOS);
    }

    @ApiOperation(value = "查询所有任务完成记录",response = TaskCompletedDTO.class)
    @RequiresPermissions("manager:merchantTaskInfo:selectMerchantTaskCompleted")
    @PostMapping("/selectMerchantTaskCompleted")
    @ResponseBody
    public TableDataInfo selectMerchantTaskCompleted(MerchantTaskInfoQueryDTO queryDTO) {
        startPage();
        List<TaskCompletedDTO> taskCompletedDTOS = merchantTaskInfoService.selectAllTaskInfo(queryDTO);
        return getDataTable(taskCompletedDTOS);
    }
    /**
     * 导出任务记录列表
     */
    @ApiOperation(value = "导出任务发布记录列表",response = TaskRecordDTO.class)
    @RequiresPermissions("manager:merchantTaskInfo:exportTaskRecord")
    @PostMapping("/exportTaskRecord")
    @ResponseBody
    public AjaxResult exportTaskRecord(MerchantTaskInfoQueryDTO queryDTO) {
        List<TaskRecordDTO> taskRecordDTOS = null;
        ExcelUtil<TaskRecordDTO> util = new ExcelUtil<>(TaskRecordDTO.class);
        try {
            taskRecordDTOS = merchantTaskInfoService.selectTaskRecord(queryDTO);
        } catch (BusinessException b) {
            logger.info("/manager/merchantTaskInfo/exportTaskRecord:{}", b);
            return error(b.getMessage());
        }catch (Exception e) {
            logger.info("/manager/merchantTaskInfo/exportTaskRecord:{}", e);
            logger.error("/manager/merchantTaskInfo/exportTaskRecord:{}", e);
            return error(e.getMessage());
        }
        return util.exportExcel(taskRecordDTOS, "任务发布记录");
    }

    @RequiresPermissions("manager:merchantTaskInfo:selectMerchantTaskInfo")
    @ApiOperation(value = "查询商户任务信息", response = MerchantTaskInfo.class)
    @PostMapping("/selectMerchantTaskInfo")
    @ResponseBody
    public AjaxResult selectMerchantTaskInfo(Integer merchantId){
        try {
            List<MerchantTaskInfo> merchantTaskInfos = merchantTaskInfoService.selectMerchantTaskInfoByMerchantId(merchantId);
            return AjaxResult.success(merchantTaskInfos);
        } catch (BusinessException b) {
            logger.error("manager/taskInfo/selectAllTaskInfo", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/taskInfo/selectAllTaskInfo", e);
            return AjaxResult.error(e.getMessage());
        }
    }
    /**
     * 导出任务发布记录列表
     */
    @ApiOperation(value = "导出任务记录列表",response = TaskCompletedDTO.class)
    @RequiresPermissions("manager:merchantTaskInfo:exportMerchantTaskCompleted")
    @PostMapping("/exportMerchantTaskCompleted")
    @ResponseBody
    public AjaxResult exportMerchantTaskCompleted(MerchantTaskInfoQueryDTO queryDTO) {
        List<TaskCompletedDTO> taskCompletedDTOS = null;
        ExcelUtil<TaskCompletedDTO> util = new ExcelUtil<>(TaskCompletedDTO.class);
        try {
            taskCompletedDTOS = merchantTaskInfoService.selectAllTaskInfo(queryDTO);
        } catch (BusinessException b) {
            logger.info("/manager/merchantTaskInfo/exportMerchantTaskCompleted:{}", b);
            return error(b.getMessage());
        }catch (Exception e) {
            logger.info("/manager/merchantTaskInfo/exportMerchantTaskCompleted:{}", e);
            logger.error("/manager/merchantTaskInfo/exportMerchantTaskCompleted:{}", e);
            return error(e.getMessage());
        }
        return util.exportExcel(taskCompletedDTOS, "完成的任务记录");
    }

    /**
     * 处理数据 开启线上签约和线上签约可打款的用户，岗位统一为“市场推广”
     */
    @ApiOperation(value = "旧数据处理，开启线上签约的用户，岗位统一为“市场推广")
    @PostMapping("/updateTaskInfo")
    @ResponseBody
    public void updateTaskInfo() {
       merchantTaskInfoService.updateTaskInfo();
    }
}
