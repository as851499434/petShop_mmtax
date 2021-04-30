package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.dto.AddPetClothesDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
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
import com.mmtax.business.domain.PetClothes;
import com.mmtax.business.service.IPetClothesService;


/**
 * 宠物服饰 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Api(tags = "宠物服饰管理")
@Controller
@RequestMapping("/business/petClothes")
public class PetClothesController extends BaseController
{
    private String prefix = "business/petClothes";
	
	@Autowired
	private IPetClothesService petClothesService;

	@ApiOperation(value = "跳转到宠物服饰列表页面")
	@RequiresPermissions("business:petClothes:view")
	@GetMapping()
	public String petClothes()
	{
	    return prefix + "/petClothes";
	}
	
	/**
	 * 查询宠物服饰列表
	 */
	@ApiOperation(value = "查询宠物服饰列表")
	@RequiresPermissions("business:petClothes:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetGoodQueryDTO dto)
	{
		startPage();
        List<PetGoodDTO> list = petClothesService.selectPetClothesList(dto);
		return getDataTable(list);
	}
	
	

	
	/**
	 * 新增宠物服饰
	 */
	@ApiOperation(value = "跳转到新增宠物服饰页面")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存宠物服饰
	 */
	@ApiOperation(value = "新增保存宠物服饰")
	@RequiresPermissions("business:petClothes:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AddPetClothesDTO petClothes)
	{		
		return toAjax(petClothesService.insertPetClothes(petClothes));
	}

	/**
	 * 修改宠物服饰
	 */
	@ApiOperation(value = "跳转修改宠物服饰页面")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetClothes petClothes = petClothesService.selectPetClothesById(id);
		mmap.put("petClothes", petClothes);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存宠物服饰
	 */
	@ApiOperation(value = "修改保存宠物服饰接口")
	@RequiresPermissions("business:petClothes:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetClothes petClothes)
	{		
		return toAjax(petClothesService.updatePetClothes(petClothes));
	}
	
	/**
	 * 删除宠物服饰
	 */
	@ApiOperation(value = "删除保存宠物服饰接口")
	@RequiresPermissions("business:petClothes:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petClothesService.deletePetClothesByIds(ids));
	}
	
}
