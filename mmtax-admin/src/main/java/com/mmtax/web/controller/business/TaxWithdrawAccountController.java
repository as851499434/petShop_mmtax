package com.mmtax.web.controller.business;

import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.TaxWithdrawAccount;
import com.mmtax.business.dto.ManagerTrxOrderDTO;
import com.mmtax.business.dto.TaxWithdrawAccountDTO;
import com.mmtax.business.dto.TaxWithdrawAccountInfoDTO;
import com.mmtax.business.dto.WithdrawDTO;
import com.mmtax.business.mapper.OnlinePaymentInfoMapper;
import com.mmtax.business.mapper.TaxWithdrawAccountMapper;
import com.mmtax.business.service.ITaxWithdrawAccountService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.enums.TaxWithdrawAccountAllowEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.poi.ExcelUtil;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 税源地提现账户 信息操作处理
 * 
 * @author meimiao
 * @date 2020-06-29
 */
@Api(tags = "税源地提现账户管理")
@Controller
@RequestMapping("tax/taxWithdrawAccount")
public class TaxWithdrawAccountController extends BaseController {
	private String prefix = "business/taxWithdrawAccount";

	@Autowired
	private ITaxWithdrawAccountService taxWithdrawAccountService;
	@Autowired
	private TaxWithdrawAccountMapper taxWithdrawAccountMapper;
	@Autowired
	private OnlinePaymentInfoMapper onlinePaymentInfoMapper;

	@RequiresPermissions("tax:taxWithdrawAccount:account")
	@GetMapping("taxAccount")
	public String taxAccount() {
		return prefix + "/taxAccount";
	}

	@RequiresPermissions("tax:taxWithdrawAccount:record")
	@GetMapping("cashOutRecord")
	public String cashOutRecord() {
		return prefix + "/cashOutRecord";
	}

	@RequiresPermissions("tax:taxWithdrawAccount:init")
	@GetMapping("taxAccountInit")
	public String taxAccountInit() {
		return prefix + "/taxAccountInit";
	}

	@RequiresPermissions("tax:taxWithdrawAccount:taxAccountEdit")
	@GetMapping("taxAccountEdit")
	public String taxAccountEdit(int id, ModelMap mmap) {
		TaxWithdrawAccount taxWithdrawAccount = taxWithdrawAccountService.withdrawAccountView(id);
		mmap.put("accountInfo",taxWithdrawAccount);
		return prefix + "/taxAccountEdit";
	}

	@RequiresPermissions("tax:taxWithdrawAccount:init")
	@GetMapping("taxAccountInfo")
	public String taxAccountInfo() {
		return prefix + "/taxAccountInfo";
	}

	@RequiresPermissions("tax:taxWithdrawAccount:init")
	@GetMapping("taxAccountCash")
	public String taxAccountCash() {
		return prefix + "/taxAccountCash";
	}

	@RequiresPermissions("tax:taxWithdrawAccount:init")
	@GetMapping("cashOutRecordDetail")
	public String cashOutRecordDetail() {
		return prefix + "/cashOutRecordDetail";
	}


	@ApiOperation(value = "新增税源地提现账户")
	@Log(title = "新增税源地提现账户", businessType = BusinessType.INSERT)
	@RequiresPermissions("tax:taxWithdrawAccount:withdrawAccountAdd")
	@PostMapping(value = "/withdrawAccountAdd")
	@ResponseBody
	public AjaxResult withdrawAccountAdd(@RequestBody @Valid TaxWithdrawAccount taxWithdrawAccount){
		logger.info("新增税源地提现账户，入参：{}", JSON.toJSONString(taxWithdrawAccount));
		try {
			taxWithdrawAccountService.withdrawAccountAddOrUpdate(taxWithdrawAccount);
		} catch (BusinessException e) {
			logger.info("/tax/taxWithdrawAccount/withdrawAccountAdd", e);
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			logger.error("/tax/taxWithdrawAccount/withdrawAccountAdd", e);
			return AjaxResult.error("新增税源地提现账户异常");
		}
		return AjaxResult.success();
	}

	@ApiOperation(value = "修改税源地提现账户")
	@Log(title = "修改税源地提现账户", businessType = BusinessType.UPDATE)
	@RequiresPermissions("tax:taxWithdrawAccount:withdrawAccountUpdate")
	@PostMapping(value = "/withdrawAccountUpdate")
	@ResponseBody
	public AjaxResult withdrawAccountUpdate(@Valid @RequestBody TaxWithdrawAccount taxWithdrawAccount){
		logger.info("修改税源地提现账户，入参：{}", JSON.toJSONString(taxWithdrawAccount));
		try {
			taxWithdrawAccountService.withdrawAccountAddOrUpdate(taxWithdrawAccount);
		} catch (BusinessException e) {
			logger.info("/tax/taxWithdrawAccount/withdrawAccountUpdate", e);
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			logger.error("/tax/taxWithdrawAccount/withdrawAccountUpdate", e);
			return AjaxResult.error("修改税源地提现账户异常");
		}
		return AjaxResult.success();
	}

	@ApiOperation(value = "查询税源地提现账户列表")
	@Log(title = "查询税源地提现账户列表", businessType = BusinessType.SELECT)
	@RequiresPermissions("tax:taxWithdrawAccount:withdrawAccountListView")
	@PostMapping(value = "/withdrawAccountListView")
	@ResponseBody
	public TableDataInfo withdrawAccountListView(TaxWithdrawAccountDTO taxWithdrawAccountDTO){
		logger.info("查询税源地提现账户列表");
		startPage();
		List<TaxWithdrawAccountDTO> taxWithdrawAccounts;
		taxWithdrawAccounts = taxWithdrawAccountService.withdrawAccountListView(taxWithdrawAccountDTO);
		return getDataTable(taxWithdrawAccounts);
	}

	@ApiOperation(value = "导出税源地提现账户列表")
	@Log(title = "导出税源地提现账户列表", businessType = BusinessType.SELECT)
	@RequiresPermissions("tax:taxWithdrawAccount:exportWithdrawAccountList")
	@PostMapping(value = "/exportWithdrawAccountList")
	@ResponseBody
	public AjaxResult exportWithdrawAccountList(TaxWithdrawAccountDTO taxWithdrawAccountDTO){
		logger.info("导出税源地提现账户列表");
		AjaxResult result;
		List<TaxWithdrawAccountDTO> taxWithdrawAccounts;
		try {
			taxWithdrawAccounts = taxWithdrawAccountService.withdrawAccountListView(taxWithdrawAccountDTO);
			List<TaxWithdrawAccountInfoDTO> list = new ArrayList<>();
			taxWithdrawAccounts.forEach(one->{
				TaxWithdrawAccountInfoDTO value = new TaxWithdrawAccountInfoDTO();
				BeanUtils.copyProperties(one,value);
				TaxWithdrawAccountAllowEnum allowEnum = TaxWithdrawAccountAllowEnum.getBystatus(one.getAllowWithdraw());
				value.setAllowWithdraw(null != allowEnum?allowEnum.getDescription():"未知");
				list.add(value);
			});
			ExcelUtil<TaxWithdrawAccountInfoDTO> util = new ExcelUtil<>(TaxWithdrawAccountInfoDTO.class);
			result = util.exportExcel(list, "税源地提现账户列表");
		} catch (BusinessException e) {
			logger.info("/tax/taxWithdrawAccount/exportWithdrawAccountList", e);
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			logger.error("/tax/taxWithdrawAccount/exportWithdrawAccountList", e);
			return AjaxResult.error("导出税源地提现账户列表异常");
		}
		return result;
	}

	@ApiOperation(value = "查询税源地提现账户详情")
	@Log(title = "查询税源地提现账户详情", businessType = BusinessType.SELECT)
	@RequiresPermissions("tax:taxWithdrawAccount:withdrawAccountView")
	@GetMapping(value = "/withdrawAccountView")
	@ResponseBody
	public AjaxResult withdrawAccountView(@RequestParam("sourceId") Integer sourceId){
		logger.info("查询税源地提现账户详情");
		TaxWithdrawAccount taxWithdrawAccount;
		try {
			taxWithdrawAccount = taxWithdrawAccountService.withdrawAccountView(sourceId);
		} catch (BusinessException e) {
			logger.info("/tax/taxWithdrawAccount/withdrawAccountView", e);
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			logger.error("/tax/taxWithdrawAccount/withdrawAccountView", e);
			return AjaxResult.error("查询税源地提现账户详情异常");
		}
		return AjaxResult.success(taxWithdrawAccount);
	}

	@ApiOperation(value = "删除税源地提现账户")
	@Log(title = "删除税源地提现账户", businessType = BusinessType.DELETE)
	@RequiresPermissions("tax:taxWithdrawAccount:withdrawAccountDelete")
	@GetMapping(value = "/withdrawAccountDelete")
	@ResponseBody
	public AjaxResult withdrawAccountDelete(@RequestParam("id") Integer id){
		logger.info("删除税源地提现账户，入参：id="+id);
		try {
			taxWithdrawAccountService.withdrawAccountDelete(id);
		} catch (BusinessException e) {
			logger.info("/tax/taxWithdrawAccount/withdrawAccountView", e);
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			logger.error("/tax/taxWithdrawAccount/withdrawAccountView", e);
			return AjaxResult.error("删除税源地提现账户异常");
		}
		return AjaxResult.success();
	}

	@ApiOperation(value = "查看可提现余额")
	@Log(title = "查看可提现余额", businessType = BusinessType.SELECT)
	@RequiresPermissions("tax:taxWithdrawAccount:viewAccountMoney")
	@GetMapping(value = "/viewAccountMoney")
	@ResponseBody
	public AjaxResult viewAccountMoney(@RequestParam("sourceId") Integer sourceId){
		logger.info("查看可提现余额，入参：sourceId="+sourceId);
		BigDecimal money;
		try {
			money = taxWithdrawAccountService.viewAccountMoney(sourceId);
		} catch (BusinessException e) {
			logger.info("/tax/taxWithdrawAccount/viewAccountMoney", e);
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			logger.error("/tax/taxWithdrawAccount/viewAccountMoney", e);
			return AjaxResult.error("查看可提现余额异常");
		}
		return AjaxResult.success(money);
	}

	/**
	 * 该接口现已无效，不要用
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "提现")
	@Log(title = "提现", businessType = BusinessType.OTHER)
	@RequiresPermissions("tax:taxWithdrawAccount:withdraw")
	@PostMapping(value = "/withdraw")
	@ResponseBody
	public AjaxResult withdraw(@RequestBody WithdrawDTO dto){
		logger.info("提现，入参：{}",JSON.toJSONString(dto));
		String result;
		try {
			result = taxWithdrawAccountService.withdraw(dto.getSourceId(),dto.getMoney());
		} catch (BusinessException e) {
			logger.info("/tax/taxWithdrawAccount/withdraw", e);
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			logger.error("/tax/taxWithdrawAccount/withdraw", e);
			return AjaxResult.error("提现异常");
		}
		return AjaxResult.success(result);
	}

	@ApiOperation(value = "获取提现记录列表")
	@Log(title = "获取提现记录列表", businessType = BusinessType.SELECT)
	@RequiresPermissions("tax:taxWithdrawAccount:getListTrxOrderOfSource")
	@PostMapping("/getListTrxOrderOfSource")
	@ResponseBody
	public TableDataInfo getListTrxOrderOfSource(ManagerTrxOrderDTO managerTrxOrderDTO) {
		SysUser sysUser = ShiroUtils.getSysUser();
		startPage();
		List<ManagerTrxOrderDTO> list = taxWithdrawAccountService.getListTrxOrderOfSource(managerTrxOrderDTO);
		return getDataTable(list);
	}
}
