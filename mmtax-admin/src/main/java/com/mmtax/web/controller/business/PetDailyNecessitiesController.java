package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.dto.AddPetDailyNecessitiesDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
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

import com.mmtax.business.domain.PetDailyNecessities;
import com.mmtax.business.service.IPetDailyNecessitiesService;


/**
 * 宠物日用品 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Api(tags = "宠物日用品管理")
@Controller
@RequestMapping("/business/petDailyNecessities")
public class PetDailyNecessitiesController extends BaseController
{
    private String prefix = "business/petDailyNecessities";
	
	@Autowired
	private IPetDailyNecessitiesService petDailyNecessitiesService;
	@ApiOperation(value = "跳转到宠物日用品列表")
	@RequiresPermissions("business:petDailyNecessities:view")
	@GetMapping()
	public String petDailyNecessities()
	{
	    return prefix + "/petDailyNecessities";
	}
	
	/**
	 * 查询宠物日用品列表
	 */
	@ApiOperation(value = "查询到宠物日用品列表")
	@RequiresPermissions("business:petDailyNecessities:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetGoodQueryDTO dto)
	{
		startPage();
        List<PetGoodDTO> list = petDailyNecessitiesService.selectPetDailyNecessitiesList(dto);
		return getDataTable(list);
	}
	
	

	
	/**
	 * 新增宠物日用品
	 */
	@ApiOperation(value = "跳转新增到宠物日用品页面")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存宠物日用品
	 */
	@ApiOperation(value = "新增到宠物日用品接口")
	@RequiresPermissions("business:petDailyNecessities:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AddPetDailyNecessitiesDTO dto)
	{		
		return toAjax(petDailyNecessitiesService.insertPetDailyNecessities(dto));
	}

	/**
	 * 修改宠物日用品
	 */
	@ApiOperation(value = "跳转修改到宠物日用品页面")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetDailyNecessities petDailyNecessities = petDailyNecessitiesService.selectPetDailyNecessitiesById(id);
		mmap.put("petDailyNecessities", petDailyNecessities);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存宠物日用品
	 */
	@ApiOperation(value = "修改宠物日用品接口")
	@RequiresPermissions("business:petDailyNecessities:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetDailyNecessities petDailyNecessities)
	{		
		return toAjax(petDailyNecessitiesService.updatePetDailyNecessities(petDailyNecessities));
	}
	
	/**
	 * 删除宠物日用品
	 */
	@ApiOperation(value = "删除宠物日用品接口")
	@RequiresPermissions("business:petDailyNecessities:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petDailyNecessitiesService.deletePetDailyNecessitiesByIds(ids));
	}
	
}
