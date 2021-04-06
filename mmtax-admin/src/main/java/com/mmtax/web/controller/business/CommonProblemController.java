package com.mmtax.web.controller.business;

import com.mmtax.business.domain.CommonProblem;
import com.mmtax.business.service.ICommonProblemService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 常见问题 信息操作处理
 * 
 * @author meimiao
 * @date 2020-11-27
 */
@Api(tags = "常见问题管理")
@Controller
@RequestMapping("/business/commonProblem")
public class CommonProblemController extends BaseController
{
    private String prefix = "business/commonProblem";

	@Autowired
	private ICommonProblemService commonProblemService;
    /**
     * 跳转到问题列表页面
     */
	@ApiOperation(value = "跳转到问题列表页面")
	@RequiresPermissions("business:commonProblem:view")
	@GetMapping()
	public String commonProblem()
	{
	    return prefix + "/commonProblem";
	}

	/**
	 * 查询常见问题列表
	 */
	@ApiOperation(value = "查询问题列表")
	@RequiresPermissions("business:commonProblem:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(String problem,String startDate,String endDate)
	{
		startPage();
        List<CommonProblem> list = commonProblemService.selectCommonProblemList(problem, startDate, endDate);
		return getDataTable(list);
	}

	/**
	 * 新增常见问题
	 */
	@ApiOperation(value = "新增常见问题页面")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	/**
	 * 新增保存常见问题
	 */
	@ApiOperation(value = "新增保存常见问题")
	@RequiresPermissions("business:commonProblem:add")
	@Log(title = "常见问题", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(CommonProblem commonProblem)
	{
		return toAjax(commonProblemService.insertCommonProblem(commonProblem));
	}

	/**
	 * 修改常见问题
	 */
	@ApiOperation(value = "修改常见问题页面")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		CommonProblem commonProblem = commonProblemService.selectCommonProblemById(id);
		mmap.put("commonProblem", commonProblem);
	    return prefix + "/edit";
	}

	/**
	 * 修改保存常见问题
	 */
	@ApiOperation(value = "修改保存常见问题")
	@RequiresPermissions("business:commonProblem:edit")
	@Log(title = "常见问题", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(CommonProblem commonProblem)
	{
		return toAjax(commonProblemService.updateCommonProblem(commonProblem));
	}

	/**
	 * 删除常见问题
	 */
	@ApiOperation(value = "删除常见问题")
	@RequiresPermissions("business:commonProblem:remove")
	@Log(title = "常见问题", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(commonProblemService.deleteCommonProblemByIds(ids));
	}
	
}
