package com.mmtax.web.controller.business;

import com.mmtax.business.domain.WechatInfo;
import com.mmtax.business.service.IWechatInfoService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.utils.poi.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 个人微信 信息操作处理
 * 
 * @author meimiao
 * @date 2020-11-26
 */
@Controller
@RequestMapping("/business/wechatInfo")
@Slf4j
public class WechatInfoController extends BaseController
{
    private String prefix = "business/wechatInfo";
	
	@Autowired
	private IWechatInfoService wechatInfoService;
	
	@RequiresPermissions("business:wechatInfo:view")
	@GetMapping()
	public String wechatInfo()
	{
	    return prefix + "/wechatInfo";
	}
	
	/**
	 * 查询个人微信列表
	 */
	@RequiresPermissions("business:wechatInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatInfo wechatInfo)
	{
		startPage();
        //List<WechatInfo> list = wechatInfoService.selectWechatInfoList(wechatInfo);
		return getDataTable(null);
	}
	
	
	/**
	 * 导出个人微信列表
	 */
	@RequiresPermissions("business:wechatInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatInfo wechatInfo)
    {
    	//List<WechatInfo> list = wechatInfoService.selectWechatInfoList(wechatInfo);
        ExcelUtil<WechatInfo> util = new ExcelUtil<WechatInfo>(WechatInfo.class);
        return util.exportExcel(null, "wechatInfo");
    }
	
	/**
	 * 新增个人微信
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存个人微信
	 */
	@RequiresPermissions("business:wechatInfo:add")
	@Log(title = "个人微信", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatInfo wechatInfo)
	{		
		//return toAjax(wechatInfoService.insertWechatInfo(wechatInfo));
		return null;
	}

	/**
	 * 修改个人微信
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		//WechatInfo wechatInfo = wechatInfoService.selectWechatInfoById(id);
		//mmap.put("wechatInfo", wechatInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存个人微信
	 */
	@RequiresPermissions("business:wechatInfo:edit")
	@Log(title = "个人微信", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatInfo wechatInfo)
	{		
		//return toAjax(wechatInfoService.updateWechatInfo(wechatInfo));
		return null;
	}
	
	/**
	 * 删除个人微信
	 */
	@RequiresPermissions("business:wechatInfo:remove")
	@Log(title = "个人微信", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		//return toAjax(wechatInfoService.deleteWechatInfoByIds(ids));
		return null;
	}
	
}
