package com.mmtax.web.controller.business;

import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.MerchantAccountMapper;
import com.mmtax.business.mapper.MerchantInfoMapper;
import com.mmtax.business.mapper.OnlinePaymentInfoMapper;
import com.mmtax.business.mapper.TaxSounrceCompanyMapper;
import com.mmtax.business.service.IMerchantAccountService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.poi.ExcelUtil;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysUser;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/18 16:45
 */
@Api(tags = "系统后台账户管理")
@Controller
@RequestMapping("/manager/account")
public class ManagerAccountController extends BaseController {


    private String prefix = "business/account";

    @Autowired
    IMerchantAccountService merchantAccountService;
    @Resource
    OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Resource
    TaxSounrceCompanyMapper taxSounrceCompanyMapper;
    @Resource
    MerchantInfoMapper merchantInfoMapper;
    @Resource
    MerchantAccountMapper merchantAccountMapper;

    @RequiresPermissions("manager:account:view")
    @GetMapping()
    public String account() {
        return prefix + "/account";
    }

    @ApiOperation("账户列表入账详情--- accounting")
    @RequiresPermissions("manager:account:view")
    @GetMapping("/accounting")
    public String accounting() {
        return prefix + "/accounting";
    }

    @RequiresPermissions("manager:account:detail")
    @GetMapping("/detail")
    public String detail() {
        return prefix + "/detail";
    }

    @RequiresPermissions("manager:account:deduction")
    @GetMapping("/deduction")
    public String deduction() {
        return prefix + "/deduction";
    }

    @RequiresPermissions("manager:account:listCapitalFlow")
    @GetMapping("listCapitalFlow")
    public String listCapitalFlow() {
        return prefix + "/listCapitalFlow";
    }

    /**
     * 跳转到充值记录页面
     */
    @RequiresPermissions("manager:account:listRecharge")
    @GetMapping("listRecharge")
    public String listRecharge() {
        return prefix + "/listRecharge";
    }

    /**
     * 跳转到退款页面
     */
    @ApiOperation(value = "跳转到退款页面")
    @RequiresPermissions("manager:account:merchantDrawback")
    @GetMapping("merchantDrawback")
    public String merchantDrawback(@ApiParam(value = "商户id", required = true) Integer merchantId, ModelMap mmap) {
        OnlinePaymentInfo info = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
        Example example = new Example(TaxSounrceCompany.class);
        Example.Criteria cr = example.createCriteria();
        cr.andEqualTo("id",info.getTaxSourceCompanyId());
        List<TaxSounrceCompany> taxSounrceCompanies = taxSounrceCompanyMapper.selectByExample(example);
        MerchantInfo merchantInfoById = merchantInfoMapper.getMerchantInfoById(merchantId);
        MerchantAccount merchantAccountInfo = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        mmap.put("taxSourceCompanyId",info.getTaxSourceCompanyId());
        mmap.put("taxSourceCompanyName",taxSounrceCompanies.get(0).getTaxSounrceCompanyName());
        mmap.put("merchantCode",merchantInfoById.getMerchantCode());
        mmap.put("usableAmount",merchantAccountInfo.getUsableAmount().toString());
        return "business/merchant/merchantDrawback";
    }


    @ApiOperation(value = "账户列表", response = ManagerAccountDTO.class)
    @Log(title = "账户列表", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:account:listAccount")
    @PostMapping("/getListAccount")
    @ResponseBody
    public TableDataInfo getListAccount(ManagerAccountDTO dto) {
        startPage();
        List<ManagerAccountDTO> list = merchantAccountService.getListManagerAccount(dto);
        return getDataTable(list);
    }


    @ApiOperation(value = "账户列表--入账详情", response = ManagerCapitalFlowDTO.class)
    @Log(title = "账户列表", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:account:accountEntry")
    @PostMapping("/accountEntry")
    @ResponseBody
    public TableDataInfo accountEntry(ManagerAccountingDTO managerAccountingDTO) {
        startPage();
        List<ManagerCapitalFlowDTO> list = merchantAccountService.getListAccountingDetails(managerAccountingDTO);
        return getDataTable(list);
    }


    @ApiOperation(value = "账户管理--抵扣账户--返点金额", response = ManagerReturnPointDTO.class)
    @Log(title = "抵扣账户", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:account:deductionAccount")
    @GetMapping("/deductionAccount")
    @ResponseBody
    public AjaxResult deductionAccount(Integer merchantId) {
        ManagerReturnPointDTO managerReturnPointDTO = merchantAccountService.getAccountReturnPoint(merchantId);
        return AjaxResult.success(managerReturnPointDTO);
    }



    @ApiOperation(value = "账户管理--抵扣账户列表", response = MerchantReturnPointListDTO.class)
    @Log(title = "抵扣账户列表", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:account:deductionFlow")
    @PostMapping("/deductionFlow")
    @ResponseBody
    public TableDataInfo deductionFlow(ManagerReturnPointQueryDTO managerReturnPointQueryDTO) {
        startPage();
        List<MerchantReturnPointListDTO> list = merchantAccountService.getReturnPointList(managerReturnPointQueryDTO);
        return getDataTable(list);
    }


    @ApiOperation(value = "账户管理--抵扣账户列表下载", response = MerchantReturnPointListDTO.class)
    @Log(title = "抵扣账户列表", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:account:deductionFlow:download")
    @PostMapping("/deductionFlowExport")
    @ResponseBody
    public AjaxResult deductionFlowExport(ManagerReturnPointQueryDTO managerReturnPointQueryDTO) {
        List<MerchantReturnPointListDTO> list = merchantAccountService.getReturnPointList(managerReturnPointQueryDTO);
        ExcelUtil<MerchantReturnPointListDTO> util = new ExcelUtil<>(MerchantReturnPointListDTO.class);
        return util.exportExcel(list, "抵扣账户列表");
    }


    @ApiOperation(value = "充值接口模拟--测试用")
    @PostMapping("/simulationRechargeRecord")
    @ResponseBody
    public AjaxResult simulationRechargeRecord(@RequestBody RechargeSimulationDTO rechargeSimulationDTO) {
        try {
            merchantAccountService.rechargeSimulation(rechargeSimulationDTO);
        } catch (BusinessException b) {
            logger.info("/manager/account/simulationRechargeRecord", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/account/simulationRechargeRecord", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();

    }

    /**
     * 充值记录
     * @param managerRechargeDTO
     * @return
     */
    @ApiOperation(value = "充值记录", response = ManagerRechargeDTO.class)
    @PostMapping("/rechargeList")
    @ResponseBody
    public TableDataInfo rechargeList(ManagerRechargeDTO managerRechargeDTO) {
        startPage();
        List<ManagerRechargeDTO> list = merchantAccountService.getRechargeList(managerRechargeDTO);
        return getDataTable(list);
    }


    @ApiOperation(value = "充值审核")
    @PostMapping("/auditRecharge")
    @ResponseBody
    @RequiresPermissions("manager:account:auditRecharge")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value =
                    "充值记录id", required = true, paramType = "String"),
            @ApiImplicitParam(name = "status", value =
                    "审核状态：SUCCESS-成功FAIL-失败ALLPYING-申请中", required = true, paramType = "String")})
    public AjaxResult auditRecharge(@ApiIgnore @RequestBody RechargeRecord rechargeRecord) {
        try {
            merchantAccountService.auditRecharge(rechargeRecord);
        } catch (BusinessException b) {
            logger.info("/manager/account/auditRecharge", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/account/auditRecharge", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();

    }


    @ApiOperation(value = "资金流水", response = ManagerAccountDTO.class)
    @Log(title = "资金流水", businessType = BusinessType.SELECT)
    @RequiresPermissions("manager:account:listCapitalFlow")
    @PostMapping("/getListCapitalFlow")
    @ResponseBody
    public TableDataInfo getListAccountRecord(ManagerAccountRecordDTO managerAccountRecordDTO) {
        startPage();
        List<ManagerCapitalFlowDTO> list = merchantAccountService.getListAccountRecord(managerAccountRecordDTO);
        return  getDataTable(list);

    }

    @ApiOperation(value = "处理资金流水旧数据")
    @Log(title = "处理资金流水旧数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("manager:account:getIsNullMerchantAccountDetail")
    @PostMapping("/getIsNullMerchantAccountDetail")
    @ResponseBody
    public AjaxResult getIsNullMerchantAccountDetail() {
        try {
            merchantAccountService.getIsNullMerchantAccountDetail();
        } catch (Exception e) {
            return AjaxResult.error();
        }
        return  AjaxResult.success();
    }

}
