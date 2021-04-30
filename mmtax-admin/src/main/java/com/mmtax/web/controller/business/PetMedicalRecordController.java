package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.dto.AddPetMedicalDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mmtax.business.domain.PetMedicalRecord;
import com.mmtax.business.service.IPetMedicalRecordService;

/**
 * 医疗宠物病历 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-26
 */
@Api(tags = "医疗宠物病历管理")
@Controller
@RequestMapping("/business/petMedicalRecord")
public class PetMedicalRecordController extends BaseController
{
    private String prefix = "business/petMedicalRecord";
	
	@Autowired
	private IPetMedicalRecordService petMedicalRecordService;

	@ApiOperation(value = "医疗宠物病历列表页面")
	@RequiresPermissions("business:petMedicalRecord:view")
	@GetMapping()
	public String petMedicalRecord()
	{
	    return prefix + "/petMedicalRecord";
	}
	
	/**
	 * 查询医疗宠物病历列表
	 */
	@ApiOperation(value = "查询医疗宠物病历列表")
	@RequiresPermissions("business:petMedicalRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetInfoQueryDTO queryDTO)
	{
		startPage();
        List<PetInfoDTO> list = petMedicalRecordService.selectPetMedicalRecordList(queryDTO);
		return getDataTable(list);
	}

	
	/**
	 * 新增医疗宠物病历
	 */
	@ApiOperation(value = "新增医疗宠物病历页面")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存医疗宠物病历
	 */
	@ApiOperation(value = "新增保存医疗宠物病历")
	@RequiresPermissions("business:petMedicalRecord:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AddPetMedicalDTO dto)
	{		
		return toAjax(petMedicalRecordService.insertPetMedicalRecord(dto));
	}

	/**
	 * 修改医疗宠物病历
	 */
	@ApiOperation(value = "修改医疗宠物病历页面")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetMedicalRecord petMedicalRecord = petMedicalRecordService.selectPetMedicalRecordById(id);
		mmap.put("petMedicalRecord", petMedicalRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存医疗宠物病历
	 */
	@ApiOperation(value = "修改保存医疗宠物病历")
	@RequiresPermissions("business:petMedicalRecord:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetMedicalRecord petMedicalRecord)
	{		
		return toAjax(petMedicalRecordService.updatePetMedicalRecord(petMedicalRecord));
	}
	
	/**
	 * 删除医疗宠物病历
	 */
	@ApiOperation(value = "删除医疗宠物病历")
	@RequiresPermissions("business:petMedicalRecord:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petMedicalRecordService.deletePetMedicalRecordByIds(ids));
	}
	
}
