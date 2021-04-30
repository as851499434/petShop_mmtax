package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.domain.PetFosterRecord;
import com.mmtax.business.dto.AddPetFosterDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.business.service.IPetFosterRecordService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 宠物寄养记录 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-27
 */
@Api(tags = "宠物寄养记录管理")
@Controller
@RequestMapping("/business/petFosterRecord")
public class PetFosterRecordController extends BaseController
{
    private String prefix = "business/petFosterRecord";
	
	@Autowired
	private IPetFosterRecordService petFosterRecordService;

	@ApiOperation(value = "跳转到宠物寄样页面")
	@RequiresPermissions("business:petFosterRecord:view")
	@GetMapping()
	public String petFosterRecord()
	{
	    return prefix + "/petFosterRecord";
	}
	
	/**
	 * 查询宠物寄养记录列表
	 */
	@ApiOperation(value = "查询宠物寄养记录列表")
	@RequiresPermissions("business:petFosterRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetInfoQueryDTO dto)
	{
		startPage();
        List<PetInfoDTO> list = petFosterRecordService.selectPetFosterRecordList(dto);
		return getDataTable(list);
	}
	
	

	
	/**
	 * 新增宠物寄养记录
	 */
	@ApiOperation(value = "跳转新增宠物寄养记录页面")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存宠物寄养记录
	 */
	@ApiOperation(value = "新增保存宠物寄养记录")
	@RequiresPermissions("business:petFosterRecord:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AddPetFosterDTO dto)
	{		
		return toAjax(petFosterRecordService.insertPetFosterRecord(dto));
	}

	/**
	 * 修改宠物寄养记录
	 */
	@ApiOperation(value = "跳转修改宠物寄养记录页面")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetFosterRecord petFosterRecord = petFosterRecordService.selectPetFosterRecordById(id);
		mmap.put("petFosterRecord", petFosterRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存宠物寄养记录
	 */
	@ApiOperation(value = "修改保存宠物寄养记录")
	@RequiresPermissions("business:petFosterRecord:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetFosterRecord petFosterRecord)
	{		
		return toAjax(petFosterRecordService.updatePetFosterRecord(petFosterRecord));
	}
	
	/**
	 * 删除宠物寄养记录
	 */
	@ApiOperation(value = "删除宠物寄养记录")
	@RequiresPermissions("business:petFosterRecord:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petFosterRecordService.deletePetFosterRecordByIds(ids));
	}
	
}
