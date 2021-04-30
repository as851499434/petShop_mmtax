package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.dto.AddPetSaleDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
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
import com.mmtax.business.domain.PetSaleRecord;
import com.mmtax.business.service.IPetSaleRecordService;
import com.mmtax.common.core.controller.BaseController;

/**
 * 销售宠物记录 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-27
 */
@Api(tags = " 销售宠物记录管理")
@Controller
@RequestMapping("/business/petSaleRecord")
public class PetSaleRecordController extends BaseController
{
    private String prefix = "business/petSaleRecord";
	
	@Autowired
	private IPetSaleRecordService petSaleRecordService;

	@ApiOperation(value = "销售宠物记录列表页面")
	@RequiresPermissions("business:petSaleRecord:view")
	@GetMapping()
	public String petSaleRecord()
	{
	    return prefix + "/petSaleRecord";
	}
	
	/**
	 * 查询销售宠物记录列表
	 */
	@ApiOperation(value = "查询销售宠物记录列表")
	@RequiresPermissions("business:petSaleRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetInfoQueryDTO queryDTO)
	{
		startPage();
        List<PetInfoDTO> list = petSaleRecordService.selectPetSaleRecordList(queryDTO);
		return getDataTable(list);
	}
	

	
	/**
	 * 新增销售宠物记录
	 */
	@ApiOperation(value = "新增销售宠物记录页面")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存销售宠物记录
	 */
	@ApiOperation(value = "新增保存销售宠物记录")
	@RequiresPermissions("business:petSaleRecord:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AddPetSaleDTO dto)
	{		
		return toAjax(petSaleRecordService.insertPetSaleRecord(dto));
	}

	/**
	 * 修改销售宠物记录
	 */
	@ApiOperation(value = "修改销售宠物记录页面")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetSaleRecord petSaleRecord = petSaleRecordService.selectPetSaleRecordById(id);
		mmap.put("petSaleRecord", petSaleRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存销售宠物记录
	 */
	@ApiOperation(value = "修改保存销售宠物记录")
	@RequiresPermissions("business:petSaleRecord:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetSaleRecord petSaleRecord)
	{		
		return toAjax(petSaleRecordService.updatePetSaleRecord(petSaleRecord));
	}
	
	/**
	 * 删除销售宠物记录
	 */
	@ApiOperation(value = "删除销售宠物记录")
	@RequiresPermissions("business:petSaleRecord:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petSaleRecordService.deletePetSaleRecordByIds(ids));
	}
	
}
