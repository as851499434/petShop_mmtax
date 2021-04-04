package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mmtax.business.domain.TaxType;
import com.mmtax.business.service.ITaxTypeService;

/**
 * 税务类型 信息操作处理
 * 
 * @author meimiao
 * @date 2020-11-26
 */
@Controller
@RequestMapping("/business/taxType")
public class TaxTypeController extends BaseController
{
    private String prefix = "business/taxType";
	
	@Autowired
	private ITaxTypeService taxTypeService;
	
	@RequiresPermissions("business:taxType:view")
	@GetMapping()
	public String taxType()
	{
	    return prefix + "/taxType";
	}
	
	/**
	 * 查询税务类型列表
	 */
	@RequiresPermissions("business:taxType:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TaxType taxType)
	{
		startPage();
        //List<TaxType> list = taxTypeService.selectTaxTypeList(taxType);
		return getDataTable(null);
	}
	
	
	/**
	 * 导出税务类型列表
	 */
	@RequiresPermissions("business:taxType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TaxType taxType)
    {
    	//List<TaxType> list = taxTypeService.selectTaxTypeList(taxType);
        ExcelUtil<TaxType> util = new ExcelUtil<TaxType>(TaxType.class);
        return util.exportExcel(null, "taxType");
    }
	
	/**
	 * 新增税务类型
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存税务类型
	 */
	@RequiresPermissions("business:taxType:add")
	@Log(title = "税务类型", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TaxType taxType)
	{		
		return null;
	}

	/**
	 * 修改税务类型
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		//TaxType taxType = taxTypeService.selectTaxTypeById(id);
		mmap.put("taxType", null);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存税务类型
	 */
	@RequiresPermissions("business:taxType:edit")
	@Log(title = "税务类型", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TaxType taxType)
	{		
		return null;
	}
	
	/**
	 * 删除税务类型
	 */
	@RequiresPermissions("business:taxType:remove")
	@Log(title = "税务类型", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return null;
	}
	
}
