package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.dto.ManagerCustomerAccountDTO;
import com.mmtax.business.mapper.TaskInfoMapper;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mmtax.business.domain.TaskInfo;
import com.mmtax.business.service.ITaskInfoService;
/**
 * 任务初始化记录 信息操作处理
 * 
 * @author meimiao
 * @date 2020-10-13
 */
@Api(tags = "任务信息")
@Controller
@RequestMapping("/manager/taskInfo")
public class TaskInfoController extends BaseController
{
    private String prefix = "manager/taskInfo";

	@Autowired
	private ITaskInfoService taskInfoService;

	@RequiresPermissions("manager:taskInfo:view")
	@GetMapping()
	public String taskInfo()
	{
	    return prefix + "/taskInfo";
	}

	@RequiresPermissions("manager:taskInfo:selectAllTaskInfo")
	@ApiOperation(value = "查询所有任务信息", response = TaskInfo.class)
	@PostMapping("/selectAllTaskInfo")
	@ResponseBody
	public AjaxResult selectAllTaskInfo(){
		try {
			List<TaskInfo> taskInfoList = taskInfoService.listAllTaskInfo();
			return AjaxResult.success(taskInfoList);
		} catch (BusinessException b) {
			logger.error("manager/taskInfo/selectAllTaskInfo", b);
			return AjaxResult.error(b.getMessage());
		} catch (Exception e) {
			logger.error("manager/taskInfo/selectAllTaskInfo", e);
			return AjaxResult.error(e.getMessage());
		}
	}
	@GetMapping("/getTaskInfoById")
	@ApiOperation("根据任务Id获取任务简介")
	@ResponseBody
	public AjaxResult getTaskInfoById(@ApiParam(value = "任务id", required = true) Integer id){
		if (StringUtils.isNull(id)||id.equals(0)){
			return AjaxResult.success("请选择");
		}
		try {
			String  taskInfo = taskInfoService.getTaskInfoById(id);
			return AjaxResult.success(taskInfo);
		} catch (BusinessException b) {
			logger.error("manager/taskInfo/getTaskInfoById", b);
			return AjaxResult.error(b.getMessage());
		} catch (Exception e) {
			logger.error("manager/taskInfo/getTaskInfoById", e);
			return AjaxResult.error(e.getMessage());
		}
	}

}
