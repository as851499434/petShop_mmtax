package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.domain.PetMasterInfo;
import com.mmtax.business.service.IPetMasterInfoService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
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
 * 宠物主人 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-10
 */
@Controller
@RequestMapping("/business/petMasterInfo")
public class PetMasterInfoController extends BaseController
{
    private String prefix = "business/petMasterInfo";
	
	@Autowired
	private IPetMasterInfoService petMasterInfoService;
	
	@RequiresPermissions("business:petMasterInfo:view")
	@GetMapping()
	public String petMasterInfo()
	{
	    return prefix + "/petMasterInfo";
	}
	
	/**
	 * 查询宠物主人列表
	 */
	@RequiresPermissions("business:petMasterInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetMasterInfo petMasterInfo)
	{
		startPage();
        List<PetMasterInfo> list = petMasterInfoService.selectPetMasterInfoList(petMasterInfo);
		return getDataTable(list);
	}

	
	/**
	 * 新增宠物主人
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存宠物主人
	 */
	@RequiresPermissions("business:petMasterInfo:add")
	@Log(title = "宠物主人", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PetMasterInfo petMasterInfo)
	{		
		return toAjax(petMasterInfoService.insertPetMasterInfo(petMasterInfo));
	}

	/**
	 * 修改宠物主人
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetMasterInfo petMasterInfo = petMasterInfoService.selectPetMasterInfoById(id);
		mmap.put("petMasterInfo", petMasterInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存宠物主人
	 */
	@RequiresPermissions("business:petMasterInfo:edit")
	@Log(title = "宠物主人", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetMasterInfo petMasterInfo)
	{		
		return toAjax(petMasterInfoService.updatePetMasterInfo(petMasterInfo));
	}
	
	/**
	 * 删除宠物主人
	 */
	@RequiresPermissions("business:petMasterInfo:remove")
	@Log(title = "宠物主人", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petMasterInfoService.deletePetMasterInfoByIds(ids));
	}
	
}
