package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.domain.PetFoods;
import com.mmtax.business.dto.AddPetFoodDTO;
import com.mmtax.business.dto.AddPetToyDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.business.service.IPetFoodsService;
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
 * 宠物食品 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Api(tags = "宠物食品管理")
@Controller
@RequestMapping("/business/petFoods")
public class PetFoodsController extends BaseController
{
    private String prefix = "business/petFoods";
	
	@Autowired
	private IPetFoodsService petFoodsService;

	@ApiOperation(value = "跳转到宠物食品页面")
	@RequiresPermissions("business:petFoods:view")
	@GetMapping()
	public String petFoods()
	{
	    return prefix + "/petFoods";
	}
	
	/**
	 * 查询宠物食品列表
	 */
	@ApiOperation(value = "查询宠物食品列表")
	@RequiresPermissions("business:petFoods:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetGoodQueryDTO dto)
	{
		startPage();
        List<PetGoodDTO> list = petFoodsService.selectPetFoodsList(dto);
		return getDataTable(list);
	}
	
	

	
	/**
	 * 新增宠物食品
	 */
	@ApiOperation(value = "跳转新增宠物食品页面")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存宠物食品
	 */
	@ApiOperation(value = "新增保存宠物食品接口")
	@RequiresPermissions("business:petFoods:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AddPetFoodDTO petFoods)
	{		
		return toAjax(petFoodsService.insertPetFoods(petFoods));
	}

	/**
	 * 修改宠物食品
	 */
	@ApiOperation(value = "修改宠物食品页面")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetFoods petFoods = petFoodsService.selectPetFoodsById(id);
		mmap.put("petFoods", petFoods);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存宠物食品
	 */
	@ApiOperation(value = "修改保存宠物食品")
	@RequiresPermissions("business:petFoods:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetFoods petFoods)
	{		
		return toAjax(petFoodsService.updatePetFoods(petFoods));
	}
	
	/**
	 * 删除宠物食品
	 */
	@ApiOperation(value = "删除宠物食品")
	@RequiresPermissions("business:petFoods:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petFoodsService.deletePetFoodsByIds(ids));
	}
	
}
