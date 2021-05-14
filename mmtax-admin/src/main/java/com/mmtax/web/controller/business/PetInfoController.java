package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.domain.PetInfo;
import com.mmtax.business.dto.AddPetAndMasterInfoDTO;
import com.mmtax.business.dto.PetInfoDTO;
import com.mmtax.business.dto.PetInfoQueryDTO;
import com.mmtax.business.service.IPetInfoService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


/**
 * 宠物 信息操作处理
 * 
 * @author meimiao
 * @date 2021-04-10
 */
@Controller
@Api(tags = "宠物管理")
@RequestMapping("/business/petInfo")
public class PetInfoController extends BaseController
{
    private String prefix = "business/petInfo";
	
	@Autowired
	private IPetInfoService petInfoService;

	@ApiOperation(value = "跳转到宠物信息列表页面")
	@RequiresPermissions("business:petInfo:view")
	@GetMapping()
	public String petInfo()
	{
	    return prefix + "/petInfo";
	}
	
	/**
	 * 查询宠物列表
	 */
	@ApiOperation(value = "查询宠物列表")
	@RequiresPermissions("business:petInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PetInfoQueryDTO queryDTO)
	{
		startPage();
        List<PetInfoDTO> list = petInfoService.selectPetInfoList(queryDTO);
		return getDataTable(list);
	}

	
	/**
	 * 新增保存宠物
	 */
	@ApiOperation(value = "新增保存宠物页面")
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存宠物
	 */
	@ApiOperation(value = "新增保存宠物")
	@RequiresPermissions("business:petInfo:add")
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AddPetAndMasterInfoDTO dto)
	{
		try {
			petInfoService.insertPetInfo(dto);
		} catch (BusinessException e) {
			logger.info("business/petInfo/add", e);
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			logger.error("business/petInfo/add", e);
			return AjaxResult.error("新增宠物信息异常");
		}
		return AjaxResult.success();
	}

	/**
	 * 修改宠物
	 */
	@ApiOperation(value = "跳转修改宠物页面")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PetInfo petInfo = petInfoService.selectPetInfoById(id);
		mmap.put("petInfo", petInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存宠物
	 */
	@ApiOperation(value = "修改保存宠物")
	@RequiresPermissions("business:petInfo:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PetInfo petInfo)
	{		
		return toAjax(petInfoService.updatePetInfo(petInfo));
	}
	
	/**
	 * 删除宠物
	 */
	@ApiOperation(value = "删除宠物")
	@RequiresPermissions("business:petInfo:remove")
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(petInfoService.deletePetInfoByIds(ids));
	}
	
}
