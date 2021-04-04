package com.mmtax.web.controller.business;

import java.util.List;

import com.mmtax.business.dto.CustomerWithdrawInfoDTO;
import com.mmtax.business.dto.ManagerCompanyInfoDTO;
import com.mmtax.business.dto.QueryCustomerWithdrawInfoDTO;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mmtax.business.domain.CustomerWithdraw;
import com.mmtax.business.service.ICustomerWithdrawService;


/**
 * 灵工提现记录 信息操作处理
 * 
 * @author meimiao
 * @date 2020-09-11
 */
@Api(tags = "提现管理")
@Controller
@RequestMapping("/business/customerWithdraw")
public class CustomerWithdrawController extends BaseController
{
    private String prefix = "business/customerWithdraw";
	
	@Autowired
	private ICustomerWithdrawService customerWithdrawService;

	@ApiOperation(value = "跳转到支付宝提现记录页面")
	@RequiresPermissions("business:customerWithdraw:customerWithdrawAlipayInfoView")
	@GetMapping("/customerWithdrawAlipayInfoView")
	public String customerWithdrawAlipayInfoView()
	{
		return prefix + "/customerWithdrawAlipayInfoView";
	}

	@ApiOperation(value = "跳转到银行卡提现记录页面")
	@RequiresPermissions("business:customerWithdraw:customerWithdrawBankInfoView")
	@GetMapping("/customerWithdrawBankInfoView")
	public String customerWithdrawBankInfoView()
	{
	    return prefix + "/customerWithdrawBankInfoView";
	}

	/**
	 * 查询灵工支付宝提现记录列表
	 */
	@ApiOperation(value = "查询灵工支付宝提现记录列表",response = CustomerWithdrawInfoDTO.class)
	@RequiresPermissions("business:customerWithdraw:listCustomerWithdrawAlipayInfo")
	@PostMapping("/listCustomerWithdrawAlipayInfo")
	@ResponseBody
	public TableDataInfo listCustomerWithdrawAlipayInfo(QueryCustomerWithdrawInfoDTO queryDTO) {
		startPage();
		List<CustomerWithdrawInfoDTO> withdrawList = customerWithdrawService.listCustomerWithdrawAlipayInfo(queryDTO);
		return getDataTable(withdrawList);
	}
	/**
	 * 查询灵工银行卡提现记录列表
	 */
	@ApiOperation(value = "查询灵工银行卡提现记录列表",response = CustomerWithdrawInfoDTO.class)
	@RequiresPermissions("business:customerWithdraw:listCustomerWithdrawBankInfo")
	@PostMapping("/listCustomerWithdrawBankInfo")
	@ResponseBody
	public TableDataInfo listCustomerWithdrawBankInfo(QueryCustomerWithdrawInfoDTO queryDTO) {
		startPage();
		List<CustomerWithdrawInfoDTO> withdrawList = customerWithdrawService.listCustomerWithdrawBankInfo(queryDTO);
		return getDataTable(withdrawList);
	}

	/**
	 * 导出灵工提现记录列表
	 */
	@ApiOperation(value = "导出灵工支付宝提现记录列表")
	@RequiresPermissions("business:customerWithdraw:customerWithdrawAlipayInfoExport")
    @PostMapping("/customerWithdrawAlipayInfoExport")
    @ResponseBody
	public AjaxResult customerWithdrawAlipayInfoExport(QueryCustomerWithdrawInfoDTO queryDTO)
	{
		List<CustomerWithdrawInfoDTO> list = customerWithdrawService.listCustomerWithdrawAlipayInfo(queryDTO);
		ExcelUtil<CustomerWithdrawInfoDTO> util = new ExcelUtil<CustomerWithdrawInfoDTO>(CustomerWithdrawInfoDTO.class);
		return util.exportExcel(list, "支付宝提现记录信息");
	}
	/**
	 * 导出灵工银行卡提现记录列表
	 */
	@ApiOperation(value = "导出灵工银行卡提现记录列表")
	@RequiresPermissions("business:customerWithdraw:customerWithdrawBankInfoExport")
	@PostMapping("/customerWithdrawBankInfoExport")
	@ResponseBody
	public AjaxResult customerWithdrawBankInfoExport(QueryCustomerWithdrawInfoDTO queryDTO)
	{
		List<CustomerWithdrawInfoDTO> list = customerWithdrawService.listCustomerWithdrawAlipayInfo(queryDTO);
		ExcelUtil<CustomerWithdrawInfoDTO> util = new ExcelUtil<CustomerWithdrawInfoDTO>(CustomerWithdrawInfoDTO.class);
		return util.exportExcel(list, "银行卡提现记录信息");
	}
}
