package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.domain.PetToys;
import com.mmtax.business.dto.AddPetToyDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.business.service.IPetToysService;
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
 * 宠物玩具 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Api(tags = "宠物玩具管理")
@Controller
@RequestMapping("/business/petToys")
public class PetToysController extends BaseController
{
    private String prefix = "business/petToys";
	
	@Autowired
	private IPetToysService petToysService;

	@ApiOperation(value = "宠物玩具页面")
	@RequiresPermissions("business:petToys:view")
	@GetMapping()
	public String petToys()
	{
	    return prefix + "/petToys";
	}
	
	/**
	 * 查询宠物玩具列表
	 */
	@ApiOperation(value = "查询宠物玩具列表")
	@RequiresPermissions("business:petToys:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetGoodQueryDTO dto)
	{
		startPage();
        List<PetToys> list = petToysService.selectPetToysList(dto);
		return getDataTable(list);
	}
	

	/**
	 * 新增宠物玩具
	 */
	@ApiOperation(value = "增宠物玩具页面")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存宠物玩具
	 */
	@ApiOperation(value = "新增保存宠物玩具")
	@RequiresPermissions("business:petToys:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AddPetToyDTO dto)
	{		
		return toAjax(petToysService.insertPetToys(dto));
	}

	/**
	 * 修改宠物玩具
	 */
	@ApiOperation(value = "修改宠物玩具页面")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetToys petToys = petToysService.selectPetToysById(id);
		mmap.put("petToys", petToys);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存宠物玩具
	 */
	@ApiOperation(value = "修改保存宠物玩具")
	@RequiresPermissions("business:petToys:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetToys petToys)
	{		
		return toAjax(petToysService.updatePetToys(petToys));
	}
	
	/**
	 * 删除宠物玩具
	 */
	@ApiOperation(value = "删除宠物玩具")
	@RequiresPermissions("business:petToys:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petToysService.deletePetToysByIds(ids));
	}
	
}
