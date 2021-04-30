package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.domain.PetServiceRecord;
import com.mmtax.business.dto.AddPetServiceDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.business.service.IPetServiceRecordService;
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
 * 宠物服务记录 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-28
 */
@Api(tags = "宠物服务记录管理")
@Controller
@RequestMapping("/business/petServiceRecord")
public class PetServiceRecordController extends BaseController
{
    private String prefix = "business/petServiceRecord";
	
	@Autowired
	private IPetServiceRecordService petServiceRecordService;

	@ApiOperation(value = "宠物服务记录页面")
	@RequiresPermissions("business:petServiceRecord:view")
	@GetMapping()
	public String petServiceRecord()
	{
	    return prefix + "/petServiceRecord";
	}
	
	/**
	 * 查询宠物服务记录列表
	 */
	@ApiOperation(value = "查询宠物服务记录列表")
	@RequiresPermissions("business:petServiceRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetInfoQueryDTO queryDTO)
	{
		startPage();
        List<PetInfoDTO> list = petServiceRecordService.selectPetServiceRecordList(queryDTO);
		return getDataTable(list);
	}
	

	
	/**
	 * 新增宠物服务记录
	 */
	@ApiOperation(value = "新增宠物服务记录页面")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存宠物服务记录
	 */
	@ApiOperation(value = "新增保存宠物服务记录")
	@RequiresPermissions("business:petServiceRecord:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AddPetServiceDTO dto)
	{		
		return toAjax(petServiceRecordService.insertPetServiceRecord(dto));
	}

	/**
	 * 修改宠物服务记录
	 */
	@ApiOperation(value = "修改宠物服务记录页面")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetServiceRecord petServiceRecord = petServiceRecordService.selectPetServiceRecordById(id);
		mmap.put("petServiceRecord", petServiceRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存宠物服务记录
	 */
	@ApiOperation(value = "修改保存宠物服务记录")
	@RequiresPermissions("business:petServiceRecord:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetServiceRecord petServiceRecord)
	{		
		return toAjax(petServiceRecordService.updatePetServiceRecord(petServiceRecord));
	}
	
	/**
	 * 删除宠物服务记录
	 */
	@ApiOperation(value = "删除宠物服务记录")
	@RequiresPermissions("business:petServiceRecord:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petServiceRecordService.deletePetServiceRecordByIds(ids));
	}
	
}
