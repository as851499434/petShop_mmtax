package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.dto.AddPetMedicineDTO;
import com.mmtax.business.dto.PetGoodDTO;
import com.mmtax.business.dto.PetGoodQueryDTO;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mmtax.business.domain.PetMedicine;
import com.mmtax.business.service.IPetMedicineService;


/**
 * 宠物药品 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Controller
@RequestMapping("/business/petMedicine")
public class PetMedicineController extends BaseController
{
    private String prefix = "business/petMedicine";
	
	@Autowired
	private IPetMedicineService petMedicineService;
	
	@RequiresPermissions("business:petMedicine:view")
	@GetMapping()
	public String petMedicine()
	{
	    return prefix + "/petMedicine";
	}
	
	/**
	 * 查询宠物药品列表
	 */
	@RequiresPermissions("business:petMedicine:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetGoodQueryDTO dto)
	{
		startPage();
        List<PetGoodDTO> list = petMedicineService.selectPetMedicineList(dto);
		return getDataTable(list);
	}
	
	

	
	/**
	 * 新增宠物药品
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存宠物药品
	 */
	@RequiresPermissions("business:petMedicine:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AddPetMedicineDTO petMedicine)
	{		
		return toAjax(petMedicineService.insertPetMedicine(petMedicine));
	}

	/**
	 * 修改宠物药品
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetMedicine petMedicine = petMedicineService.selectPetMedicineById(id);
		mmap.put("petMedicine", petMedicine);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存宠物药品
	 */
	@RequiresPermissions("business:petMedicine:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetMedicine petMedicine)
	{		
		return toAjax(petMedicineService.updatePetMedicine(petMedicine));
	}
	
	/**
	 * 删除宠物药品
	 */
	@RequiresPermissions("business:petMedicine:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petMedicineService.deletePetMedicineByIds(ids));
	}
	
}
