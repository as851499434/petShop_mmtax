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
import com.mmtax.business.domain.OrderStatusInfo;
import com.mmtax.business.service.IOrderStatusInfoService;

/**
 * 订单状态 信息操作处理
 * 
 * @author meimiao
 * @date 2020-11-26
 */
@Controller
@RequestMapping("/business/orderStatusInfo")
public class OrderStatusInfoController extends BaseController
{
    private String prefix = "business/orderStatusInfo";
	
	@Autowired
	private IOrderStatusInfoService orderStatusInfoService;
	
	@RequiresPermissions("business:orderStatusInfo:view")
	@GetMapping()
	public String orderStatusInfo()
	{
	    return prefix + "/orderStatusInfo";
	}
	
	/**
	 * 查询订单状态列表
	 */
	@RequiresPermissions("business:orderStatusInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OrderStatusInfo orderStatusInfo)
	{
		startPage();
        //List<OrderStatusInfo> list = orderStatusInfoService.selectOrderStatusInfoList(orderStatusInfo);
		return getDataTable(null);
	}
	
	
	/**
	 * 导出订单状态列表
	 */
	@RequiresPermissions("business:orderStatusInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderStatusInfo orderStatusInfo)
    {
    	//List<OrderStatusInfo> list = orderStatusInfoService.selectOrderStatusInfoList(orderStatusInfo);
        ExcelUtil<OrderStatusInfo> util = new ExcelUtil<OrderStatusInfo>(OrderStatusInfo.class);
        return util.exportExcel(null, "orderStatusInfo");
    }
	
	/**
	 * 新增订单状态
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存订单状态
	 */
	@RequiresPermissions("business:orderStatusInfo:add")
	@Log(title = "订单状态", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OrderStatusInfo orderStatusInfo)
	{		
		//return toAjax(orderStatusInfoService.insertOrderStatusInfo(orderStatusInfo));
		return null;
	}

	/**
	 * 修改订单状态
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		//OrderStatusInfo orderStatusInfo = orderStatusInfoService.selectOrderStatusInfoById(id);
		mmap.put("orderStatusInfo", null);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存订单状态
	 */
	@RequiresPermissions("business:orderStatusInfo:edit")
	@Log(title = "订单状态", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OrderStatusInfo orderStatusInfo)
	{		
		//return toAjax(orderStatusInfoService.updateOrderStatusInfo(orderStatusInfo));
		return null;
	}
	
	/**
	 * 删除订单状态
	 */
	@RequiresPermissions("business:orderStatusInfo:remove")
	@Log(title = "订单状态", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		//return toAjax(orderStatusInfoService.deleteOrderStatusInfoByIds(ids));
		return null;
	}
	
}
