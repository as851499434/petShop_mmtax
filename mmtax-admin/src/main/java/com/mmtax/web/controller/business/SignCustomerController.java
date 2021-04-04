package com.mmtax.web.controller.business;

import com.mmtax.business.dto.*;
import com.mmtax.business.service.*;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
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
 * 签约员工 信息操作处理
 * 
 * @author ljd
 * @date 2020-07-27
 */
@Api(tags = "后端签约员工管理")
@Controller
@RequestMapping("/manager/signCustomerInfo")
public class SignCustomerController extends BaseController {
    private String prefix = "business/signCustomer";

    @Autowired
    private ISignCustomerInfoService signCustomerInfoService;

    @ApiOperation(value = "跳转员工签约列表页面")
    @RequiresPermissions("manager:sign:customerInfo:view")
    @GetMapping()
    public String customerInfo()
    {
        return prefix + "/signCustomer";
    }

    /**
     * 查询员工签约列表
     */
    @RequiresPermissions("manager:sign:customerInfo:list")
    @ApiOperation(value = "员工签约列表", response = ManagerSignCustomerInfoDTO.class)
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ManagerSignCustomerInfoQueryDTO queryDTO) {
        startPage();
        List<ManagerSignCustomerInfoDTO> list = signCustomerInfoService.listManagerSignCustomerInfoDTO(queryDTO);
        return getDataTable(list);
    }

    /**
     * 导出员工签约列表
     */
    @ApiOperation(value = "导出员工签约列表")
    @RequiresPermissions("manager:sign:customerInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ManagerSignCustomerInfoQueryDTO queryDTO) {
        List<ManagerSignCustomerInfoDTO> list = signCustomerInfoService.listManagerSignCustomerInfoDTO(queryDTO);
        ExcelUtil<ManagerSignCustomerInfoDTO> util = new ExcelUtil<>(ManagerSignCustomerInfoDTO.class);
        return util.exportExcel(list, "员工签约列表");
    }

    @ApiOperation(value = "跳转协议管理列表页面")
    @RequiresPermissions("manager:sign:customerInfo:agreement")
    @GetMapping("/agreement")
    public String customerAccount() {
        return prefix + "/agreement";
    }

    /**
     * 查询协议管理列表
     */
    @RequiresPermissions("manager:sign:customerInfo:agreementList")
    @ApiOperation(value = "协议管理列表", response = ManagerSignCustomerInfoAgreementDTO.class)
    @PostMapping("/agreementList")
    @ResponseBody
    public TableDataInfo list(ManagerSignCustomerInfoAgreementQueryDTO queryDTO) {
        startPage();
        List<ManagerSignCustomerInfoAgreementDTO> list = signCustomerInfoService
                .listManagerSignCustomerInfoAgreementDTO(queryDTO);
        return getDataTable(list);
    }

    /**
     * 导出协议管理列表
     */
    @ApiOperation(value = "导出协议管理列表")
    @RequiresPermissions("manager:sign:customerInfo:agreementExport")
    @PostMapping("/agreementExport")
    @ResponseBody
    public AjaxResult export(ManagerSignCustomerInfoAgreementQueryDTO queryDTO) {
        List<ManagerSignCustomerInfoAgreementDTO> list = signCustomerInfoService
                .listManagerSignCustomerInfoAgreementDTO(queryDTO);
        ExcelUtil<ManagerSignCustomerInfoAgreementDTO> util = new ExcelUtil<>(ManagerSignCustomerInfoAgreementDTO.class);
        return util.exportExcel(list, "协议管理列表");
    }

}
