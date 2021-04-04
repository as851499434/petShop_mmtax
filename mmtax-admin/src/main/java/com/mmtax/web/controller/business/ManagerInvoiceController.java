package com.mmtax.web.controller.business;


import com.mmtax.business.domain.Address;
import com.mmtax.business.domain.InvoiceInfo;
import com.mmtax.business.dto.*;
import com.mmtax.business.service.*;
import com.mmtax.business.yunzbdto.YunZBInvoiceInfoDTO;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.poi.ExcelUtil;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * 后台发票管理
 * @author yuanligang
 * @date 2019/7/11
 */
@Controller
@RequestMapping("manager/invoice")
public class ManagerInvoiceController extends BaseController {

    private String prefix = "business/invoice";

    @Autowired
    private ISysInvoiceService sysInvoiceService;
    @Autowired
    private IQueryMerchantInfoService queryMerchantInfoService;
    @Autowired
    private IModifyMerchantInfoService modifyMerchantInfoService;
    @Autowired
    private IMerchantInvoiceService merchantInvoiceService;
    @Autowired
    private IMerchantInfoService merchantInfoService;

    @RequiresPermissions("manager:invoice:view")
    @GetMapping()
    public String invoice() {
        return prefix + "/invoice";
    }

    @RequiresPermissions("manager:invoice:addressList")
    @GetMapping("addressList")
    public String addressList() {
        return prefix + "/addressList";
    }

    @ApiOperation("发票审核详情查看页面跳转")
    @RequiresPermissions("manager:invoice:view")
    @GetMapping("invoiceApplyView")
    public String invoiceApplyView() {
        return prefix + "/invoiceApplyView";
    }

    @RequiresPermissions("manager:invoice:detail")
    @GetMapping("detail")
    public String detail() {
        return prefix + "/invoiceDetail";
    }

    @RequiresPermissions("manager:invoice:info")
    @GetMapping("info")
    public String info() {
        return prefix + "/invoiceInfo";
    }

    @RequiresPermissions("manager:invoice:infoDetail")
    @GetMapping("infoDetail")
    public String invoiceInfoDetail(@ApiParam(value = "商户ID", required = true) Integer merchantId, ModelMap mmap) {
        SysInvoiceInfoDTO invoiceInfo = new SysInvoiceInfoDTO();
        try {
            invoiceInfo = queryMerchantInfoService.getSysInvoiceInfoByMerchantId(merchantId);
            mmap.put("info", invoiceInfo);
        } catch (Exception e) {
            logger.error("/infoDetail/invoiceInfoDetail", e);
        }
        return prefix + "/invoiceInfoDetail";
    }

    @RequiresPermissions("manager:invoice:address")
    @GetMapping("address")
    public String address() {
        return prefix + "/invoiceAddress";
    }

    @RequiresPermissions("manager:invoice:restart")
    @GetMapping("restart")
    public String restart() {
        return prefix + "/restart";
    }

    @RequiresPermissions("manager:invoice:imgView")
    @GetMapping("imgView")
    public String imgView() {
        return prefix + "/imgView";
    }

    @RequiresPermissions("manager:invoice:addressDetail")
    @GetMapping("addressDetail")
    public String invoiceAddressDetail(@ApiParam(value = "merchantId",required = true)Integer merchantId,
                                       ModelMap mmap) {
        Address address = queryMerchantInfoService.getAddressInfoByMerchantId(merchantId);
        mmap.put("address",address);
        return prefix + "/invoiceAddressDetail";

    }


    @ApiOperation(value = "后台发票申请记录查询", response = InvoiceQueryDTO.class)
    @Log(title = "发票管理-->开票记录查询", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:invoice:queryInvoice")
    @PostMapping("/queryInvoice")
    @ResponseBody
    public TableDataInfo queryInvoice(ManagerInvoiceApplyDTO managerInvoiceApplyDTO) {
        startPage();
        List<InvoiceQueryDTO> list = sysInvoiceService.listInvoiceApplications(managerInvoiceApplyDTO);
        return getDataTable(list);
    }

    @ApiOperation(value = "导出发票申请记录")
    @RequiresPermissions("manager:invoice:invoiceApplyExport")
    @PostMapping("/invoiceApplyExport")
    @ResponseBody
    public AjaxResult invoiceApplyExport(ManagerInvoiceApplyDTO managerInvoiceApplyDTO) {
        List<InvoiceQueryDTO> list = sysInvoiceService.listInvoiceApplications(managerInvoiceApplyDTO);
        ExcelUtil<InvoiceQueryDTO> util = new ExcelUtil<>(InvoiceQueryDTO.class);
        return util.exportExcel(list, "发票申请记录");
    }

    @ApiOperation(value = "后台商户邮寄地址管理", response = ManagerInvoiceAddressDTO.class)
    @Log(title = "后台商户邮寄地址查询", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:invoice:queryAddress")
    @PostMapping("/queryAddress")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "saleName", value =
                    "销售名字", required = true, paramType = "String"),
            @ApiImplicitParam(name = "merchantName", value =
                    "商户名称", required = true, paramType = "String"),
            @ApiImplicitParam(name = "mobile", value =
                    "收货人手机号", required = true, paramType = "String")})
    public TableDataInfo queryAddress(@ApiIgnore ManagerInvoiceAddressDTO managerInvoiceAddressDTO) {
        startPage();
        List<ManagerInvoiceAddressDTO> list = sysInvoiceService.listInvoiceAddress(managerInvoiceAddressDTO);
        return getDataTable(list);
    }

    @ApiOperation(value = "发票管理-->申请记录-->查看-->开票申请单", response = YunZBInvoiceInfoDTO.class)
    @Log(title = "发票管理-->发票详情", businessType = BusinessType.SELECT)
    @GetMapping("/queryInvoiceDetail")
    @ResponseBody
    public AjaxResult queryInvoiceDetail(@ApiParam(value = "发票id", required = true) int id) {
        YunZBInvoiceInfoDTO invoiceDetailDTO;
        try {
            invoiceDetailDTO = merchantInvoiceService.getInvoiceApplyDetail(id,null);
        } catch (BusinessException b) {
            logger.info("manager/invoice/queryInvoiceDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/queryInvoiceDetail", e);
            return error("发票详情查询失败");
        }
        return AjaxResult.success(invoiceDetailDTO);
    }

    @ApiOperation(value = "后台商户邮寄地址更新")
    @Log(title = "后台商户邮寄地址更新", businessType = BusinessType.UPDATE)
    @RequiresPermissions("manager:invoice:updateAddress")
    @PostMapping("/updateAddress")
    @ResponseBody
    public AjaxResult updateAddress(@RequestBody Address address) {
        modifyMerchantInfoService.updateAddressInfo(address);
        return success();
    }


    @ApiOperation(value = "发票审核")
    @Log(title = "发票管理-->发票审核", businessType = BusinessType.UPDATE)
    @RequiresPermissions("manager:invoice:checkInvoice")
    @PostMapping("/checkInvoice")
    @ResponseBody
    public AjaxResult checkInvoice(CheckInvoiceDTO checkInvoiceDTO) {
        try {
            sysInvoiceService.checkInvoiceStatus(checkInvoiceDTO);
        } catch (BusinessException b) {
            logger.info("manager/invoice/checkInvoice", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/checkInvoice", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "审核查看发票信息", response = ManagerInvoiceCheckDTO.class)
    @Log(title = "发票管理-->审核查看发票信息", businessType = BusinessType.SELECT)
    @GetMapping("/checkInvoiceDetail")
    @ResponseBody
    public AjaxResult checkInvoiceDetail(Integer invoiceApplyId) {
        ManagerInvoiceCheckDTO managerInvoiceCheckDTO;
        try {
            managerInvoiceCheckDTO = sysInvoiceService.getCheckDetail(invoiceApplyId);
        } catch (BusinessException b) {
            logger.info("manager/invoice/checkInvoiceDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/checkInvoiceDetail", e);
            return error("查询失败");
        }
        return AjaxResult.success(managerInvoiceCheckDTO);
    }

    @ApiOperation(value = "开票详情查看", response =  QueryInvoiceDetailsDTO.class)
    @Log(title = "发票管理-->开票详情查看", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:invoice:querySuccessInvoices")
    @PostMapping("/querySuccessInvoices")
    @ResponseBody
    public TableDataInfo querySuccessInvoices(ManagerInvoiceApplyDTO managerInvoiceApplyDTO) {
        startPage();
        List<QueryInvoiceDetailsDTO> list = sysInvoiceService.listSysInvoicedApplications(managerInvoiceApplyDTO);
        return  getDataTable(list);
    }

    /**
     * 导出开票详情
     * @param
     * @return
     */
    @ApiOperation(value = "导出开票详情")
    @RequiresPermissions("manager:invoice:invoiceDetailsExport")
    @PostMapping("/invoiceDetailsExport")
    @ResponseBody
    public AjaxResult invoiceDetailsExport(ManagerInvoiceApplyDTO managerInvoiceApplyDTO) {
        List<QueryInvoiceDetailsDTO> list = sysInvoiceService.listSysInvoicedApplications(managerInvoiceApplyDTO);
        ExcelUtil<QueryInvoiceDetailsDTO> util = new ExcelUtil<>(QueryInvoiceDetailsDTO.class);
        return util.exportExcel(list, "开票详情");
    }

    @ApiOperation(value = "发票影印件及其他线下开票信息上传更新")
    @Log(title = "发票管理-->发票影印件及其他线下开票信息上传更新", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:invoice:upload")
    @PostMapping("/updateApplyInfo")
    @ResponseBody
    public AjaxResult upload(@RequestBody InvoiceUploadDTO invoiceUploadDTO) {
        try {
            sysInvoiceService.updateInvoiceInfo(invoiceUploadDTO);
        } catch (BusinessException b) {
            logger.info("manager/invoice/updateApplyInfo", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/updateApplyInfo", e);
            return error("发票信息更新");
        }
        return AjaxResult.success();
    }

    /**
     * 获取错误重开的发票列表
     */
    @ApiOperation(value = "获取错误重开列表", response = InvoiceDetailDTO.class)
    @Log(title = "发票管理-->错误重开审核", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:invoice:viewRestartList")
    @PostMapping("/restartList")
    @ResponseBody
    public TableDataInfo restartList(ManagerInvoiceApplyDTO managerInvoiceApplyDTO) {
        List<ManagerInvoiceDetailDTO> list = sysInvoiceService.listRestartInvoice(managerInvoiceApplyDTO);
        return getDataTable(list);
    }


    @ApiOperation("跳转接口--查看作废发票-->failInvoice")
    @RequiresPermissions("manager:invoice:restart")
    @GetMapping("reviewFailInvoice")
    public String reviewFailInvoice() {
        return prefix + "/failInvoice";
    }


    /**
     * 根据待作废发票ID获取开票信息
     *
     * @return
     */
    @ApiOperation(value = "错误重开审核查看", response = RestartCheckDTO.class)
    @Log(title = "发票管理-->错误重开审核查看--page1", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:invoice:restartCheckView")
    @GetMapping("/restartCheckView")
    @ResponseBody
    public AjaxResult restartCheck(@ApiParam("待作废发票ID") Integer invoiceId) {
        RestartCheckDTO restart;
        try {
            restart = sysInvoiceService.getRestartDetail(invoiceId);
        } catch (BusinessException b) {
            logger.info("manager/invoice/restartCheckView", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/restartCheckView", e);
            return error("重开发票获取异常");
        }
        return AjaxResult.success(restart);

    }


    @ApiOperation("跳转接口--查看代替发票-->replaceInvoice")
    @RequiresPermissions("manager:invoice:restart")
    @GetMapping("reviewReplaceInvoice")
    public String reviewReplaceInvoice() {
        return prefix + "/replaceInvoice";
    }


    /**
     * 根据待作废发票ID获取重开发票列表
     *
     * @return
     */
    @ApiOperation(value = "根据待作废发票ID获取重开发票列表", response = InvoiceCheckDetailDTO.class)
    @Log(title = "发票管理-->错误重开审核查看--page2", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:invoice:restartCheckView")
    @GetMapping("/restartInvoiceView")
    @ResponseBody
    public AjaxResult restartInvoiceView(@ApiParam("待作废发票ID") Integer invoiceId) {
        List<InvoiceCheckDetailDTO> list;
        try {
            list = sysInvoiceService.getRestartInvoiceListByInvoiceId(invoiceId);
        } catch (BusinessException b) {
            logger.info("manager/invoice/restartCheckView", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/restartCheckView", e);
            return error("重开发票列表");
        }
        return AjaxResult.success(list);

    }

    /**
     * 发票错误重开审核
     *
     * @return
     */
    @ApiOperation(value = "发票错误重开审核")
    @Log(title = "发票管理-->发票错误重开审核", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:invoice:auditRestartInvoice")
    @PostMapping("/auditRestartInvoice")
    @ResponseBody
    public AjaxResult auditRestartInvoice(AuditInvoicesDTO auditInvoicesDTO) {
        try {
            sysInvoiceService.auditInvoiceStatus(auditInvoicesDTO);
        } catch (BusinessException b) {
            logger.info("manager/invoice/auditRestartInvoice", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/auditRestartInvoice", e);
            return AjaxResult.error("发票错误重开审核");
        }
        return AjaxResult.success();

    }

    @ApiOperation("跳转接口--查看开票审核信息-->invoiceCheckView")
    @RequiresPermissions("manager:invoice:invoiceCheckView")
    @GetMapping("invoiceCheckView")
    public String invoiceCheckView(@ApiParam(value = "发票id", required = true) String id, ModelMap mmap) {
        InvoiceCheckDTO invoiceCheckInfo = sysInvoiceService.getInvoiceCheckByInvoiceId(id);
        mmap.put("invoiceCheckInfo", invoiceCheckInfo);
        return prefix + "/invoiceCheckView";
    }

    @ApiOperation(value = "驳回和同意申请开票状态审核")
    @RequiresPermissions("manager:invoice:agreeAndRefuseInvoiceChangeState")
    @PostMapping("/agreeAndRefuseInvoiceChangeState")
    @ResponseBody
    public AjaxResult agreeAndRefuseInvoiceChangeState(AgreeAndRefuseCheckDTO agreeAndRefuseCheckDTO) {
        try {
            sysInvoiceService.agreeAndRefuseInvoiceChangeState(agreeAndRefuseCheckDTO);
        } catch (BusinessException b) {
            logger.info("manager/invoice/agreeAndRefuseInvoiceChangeState", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/agreeAndRefuseInvoiceChangeState", e);
            return AjaxResult.error("发票状态错误");
        }
        return AjaxResult.success("保存成功");
    }


    /**
     * 开票按钮
     * @param agreeAndRefuseCheckDTO
     * @return
     */
    @ApiOperation(value = "开票审核")
    @RequiresPermissions("manager:invoice:toInvoiceChangeState")
    @PostMapping("/toInvoiceChangeState")
    @ResponseBody
    public AjaxResult toInvoiceChangeState(AgreeAndRefuseCheckDTO agreeAndRefuseCheckDTO) {
        try {
            sysInvoiceService.toInvoiceChangeState(agreeAndRefuseCheckDTO);
        } catch (BusinessException b) {
            logger.info("manager/invoice/toInvoiceChangeState", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/toInvoiceChangeState", e);
            return AjaxResult.error("发票状态错误");
        }
        return AjaxResult.success("保存成功");
    }

    @ApiOperation("跳转接口--开票审核-发票详情")
    @RequiresPermissions("manager:invoice:invoiceAmountDetailsCheckView")
    @GetMapping("invoiceAmountDetailsCheckView")
    public String invoiceAmountDetailsCheckView(@ApiParam(value = "发票id", required = true)
                                                            String id, ModelMap mmap) {
        mmap.put("id", id);
        return prefix + "/invoiceAmountDetailsCheckView";
    }
    /**
     * 发票详情
     * @param id
     * @return
     */
    @ApiOperation(value = "审核--发票详情",  response = InvoiceAmountDetailsDTO.class)
    @RequiresPermissions("manager:invoice:invoiceAmountDetailsCheck")
    @PostMapping("/invoiceAmountDetailsCheck")
    @ResponseBody
    public TableDataInfo invoiceAmountDetailsCheck(String id) {
        startPage();
        List<InvoiceAmountDetailsDTO> invoiceAmountDetailsDTOS
            = sysInvoiceService.invoiceAmountDetailsCheck(id);
        return  getDataTable(invoiceAmountDetailsDTOS);
    }

    /**
     * 根据税源地id查找科目内容
     * @param taxSounrceCompanyId
     * @return
     */
    @ApiOperation(value = "根据税源地id查找科目内容",  response = InvoiceSubjectWithTaxCompanyCorrelationDTO.class)
    @RequiresPermissions("manager:invoice:selectContent")
    @PostMapping("/selectContent")
    @ResponseBody
    public TableDataInfo selectContent(Integer taxSounrceCompanyId) {
        startPage();
        List<InvoiceSubjectWithTaxCompanyCorrelationDTO> list =
                merchantInfoService.selectContent(taxSounrceCompanyId);
        return getDataTable(list);

    }
    /**
     * 根据商户id查询所包含的科目内容
     * @param merchantId
     * @return
     */
    @ApiOperation(value = "根据商户id查询所包含的科目内容",  response = InvoiceSubjectWithTaxCompanyCorrelationDTO.class)
    @RequiresPermissions("manager:invoice:selectInvoiceContentInfoByMerchanId")
    @PostMapping("/selectInvoiceContentInfoByMerchanId")
    @ResponseBody
    public TableDataInfo selectInvoiceContentInfoByMerchanId(Integer merchantId) {
        startPage();
        List<InvoiceSubjectWithTaxCompanyCorrelationDTO> list =
                merchantInfoService.selectInvoiceContentInfoByMerchanId(merchantId);
        return getDataTable(list);

    }
    /**
     * 根据商户id查询税源地对应的科目内容
     * @param merchantId
     * @return
     */
    @ApiOperation(value = "根据商户id查询税源地对应的科目内容",  response = InvoiceSubjectWithTaxCompanyCorrelationDTO.class)
    @RequiresPermissions("manager:invoice:selectInvoiceContent")
    @PostMapping("/selectInvoiceContent")
    @ResponseBody
    public TableDataInfo selectInvoiceContent(Integer merchantId) {
        startPage();
        List<InvoiceSubjectWithTaxCompanyCorrelationDTO> list =
                merchantInfoService.selectInvoiceContent(merchantId);
        return getDataTable(list);

    }
    /**
     * 修改发票科目信息
     * @param
     * @return
     */
    @ApiOperation(value = "修改发票信息")
    @RequiresPermissions("manager:invoice:updateInvoiceContentInfo")
    @PostMapping("/updateInvoiceContentInfo")
    @ResponseBody
    public AjaxResult updateInvoiceContentInfo(@RequestBody UpdateInvoiceAllInfoDTO invoiceAllInfoDTO) {
        try {
            logger.info("manager/invoice/updateInvoiceContentInfo:{}", invoiceAllInfoDTO);
            merchantInfoService.updateInvoiceContentInfo(invoiceAllInfoDTO,invoiceAllInfoDTO.getInvoiceSubjectIds(),
                    Integer.valueOf(invoiceAllInfoDTO.getMerchantId()));
        } catch (BusinessException b) {
            logger.info("manager/invoice/updateInvoiceContentInfo", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/updateInvoiceContentInfo", e);
            return AjaxResult.error("发票信息错误");
        }
        return AjaxResult.success("保存成功");
    }

}
