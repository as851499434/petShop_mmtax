package com.mmtax.web.controller.business;

import com.mmtax.business.dto.*;
import com.mmtax.business.service.*;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 员工 信息操作处理
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Api(tags = "员工管理")
@Controller
@RequestMapping("/manager/customerInfo")
public class CustomerInfoController extends BaseController
{
    private String prefix = "business/customer";
	
	@Autowired
	private ICustomerInfoService customerInfoService;

	@Autowired
	private ICustomerAccountService customerAccountService;

	@Autowired
    private ICustomerBankcardInfoService customerBankcardInfoService;

	@Autowired
    private ICustomerAlipayInfoService customerAlipayInfoService;

	@Autowired
	private ICusLinkMerInfoService cusLinkMerInfoService;


	@ApiOperation(value = "跳转员工列表页面")
	@RequiresPermissions("manager:customerInfo:view")
	@GetMapping()
	public String customerInfo()
	{
	    return prefix + "/customerInfo";
	}

	@ApiOperation(value = "跳转员工账户管理列表页面")
	@RequiresPermissions("manager:customerInfo:customerAccount")
	@GetMapping("/customerAccount")
	public String customerAccount()
	{
		return prefix + "/customerAccount";
	}

	@ApiOperation(value = "跳转银行卡信息管理列表页面")
	@RequiresPermissions("manager:customerInfo:customerAlipayInfo")
	@GetMapping("/customerAlipayInfo")
	public String customerAlipayInfo()
	{
		return prefix + "/customerAlipayInfo";
	}

	@ApiOperation(value = "跳转支付宝信息管理列表页面")
	@RequiresPermissions("manager:customerInfo:customerBankcardInfo")
	@GetMapping("/customerBankcardInfo")
	public String customerBankcardInfo()
	{
		return prefix + "/customerBankcardInfo";
	}
	
	/**
	 * 查询员工列表
	 */
	@RequiresPermissions("manager:customerInfo:list")
	@ApiOperation(value = "员工列表", response = ManagerCustomerInfoDTO.class)
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ManagerCustomerInfoQueryDTO queryDTO) {
		startPage();
		List<ManagerCustomerInfoDTO> list = customerInfoService.listManagerCustomerInfoDTO(queryDTO);
		return getDataTable(list);
	}

    /**
     * 导出员工列表
     */
    @ApiOperation(value = "导出员工列表")
    @RequiresPermissions("manager:customerInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ManagerCustomerInfoQueryDTO queryDTO) {
        List<ManagerCustomerInfoDTO> list = customerInfoService.listManagerCustomerInfoDTO(queryDTO);
        ExcelUtil<ManagerCustomerInfoDTO> util = new ExcelUtil<>(ManagerCustomerInfoDTO.class);
        return util.exportExcel(list, "员工列表");
    }

	/**
	 * 查询员工账户列表
	 */
	@RequiresPermissions("manager:customerInfo:accountList")
	@ApiOperation(value = "员工账户列表", response = ManagerCustomerAccountDTO.class)
	@PostMapping("/accountList")
	@ResponseBody
	public TableDataInfo list(ManagerCustomerAccountQueryDTO queryDTO) {
		startPage();
		List<ManagerCustomerAccountDTO> list = customerAccountService.listManagerCustomerAccountDTO(queryDTO);
		return getDataTable(list);
	}

    /**
     * 导出员工账户列表
     */
    @RequiresPermissions("manager:customerInfo:exportAccount")
    @ApiOperation(value = "导出员工账户列表")
    @PostMapping("/exportAccount")
    @ResponseBody
    public AjaxResult exportAccount(ManagerCustomerAccountQueryDTO queryDTO) {
        List<ManagerCustomerAccountDTO> list = customerAccountService.listManagerCustomerAccountDTO(queryDTO);
        ExcelUtil<ManagerCustomerAccountDTO> util = new ExcelUtil<>(ManagerCustomerAccountDTO.class);
        return util.exportExcel(list, "员工账户列表");
    }

	/**
	 * 查询员工银行卡列表
	 */
	@RequiresPermissions("manager:customerInfo:bankcardList")
    @ApiOperation(value = "员工银行卡列表", response = ManagerCustomerBankCardDTO.class)
	@PostMapping("/BankcardList")
	@ResponseBody
	public TableDataInfo list(ManagerCustomerBankCardQueryDTO queryDTO) {
		startPage();
		List<ManagerCustomerBankCardDTO> list = customerBankcardInfoService.listManagerCustomerBankCardDTO(queryDTO);
		return getDataTable(list);
	}

    /**
     * 导出员工银行卡列表
     */
    @RequiresPermissions("manager:customerInfo:exportBankcard")
    @ApiOperation(value = "导出员工银行卡列表")
    @PostMapping("/exportBankcard")
    @ResponseBody
    public AjaxResult exportBankcard(ManagerCustomerBankCardQueryDTO queryDTO) {
        List<ManagerCustomerBankCardDTO> list = customerBankcardInfoService.listManagerCustomerBankCardDTO(queryDTO);
        ExcelUtil<ManagerCustomerBankCardDTO> util = new ExcelUtil<>(ManagerCustomerBankCardDTO.class);
        return util.exportExcel(list, "员工银行卡列表");
    }

	/**
	 * 查询员工支付宝列表
	 */
	@RequiresPermissions("manager:customerInfo:alipayInfoList")
    @ApiOperation(value = "员工支付宝列表", response = ManagerCustomerAlipayDTO.class)
	@PostMapping("/alipayInfoList")
	@ResponseBody
	public TableDataInfo list(ManagerCustomerAlipayQueryDTO queryDTO) {
		startPage();
		List<ManagerCustomerAlipayDTO> list = customerAlipayInfoService.listManagerCustomerAlipayDTO(queryDTO);
		return getDataTable(list);
	}

	/**
	 * 导出员工支付宝列表
	 */
	@RequiresPermissions("manager:customerInfo:exportAlipay")
    @ApiOperation(value = "导出员工支付宝列表")
	@PostMapping("/exportAlipay")
	@ResponseBody
	public AjaxResult exportAlipay(ManagerCustomerAlipayQueryDTO queryDTO) {
        List<ManagerCustomerAlipayDTO> list = customerAlipayInfoService.listManagerCustomerAlipayDTO(queryDTO);
        ExcelUtil<ManagerCustomerAlipayDTO> util = new ExcelUtil<>(ManagerCustomerAlipayDTO.class);
        return util.exportExcel(list, "员工支付宝列表");
	}

}
