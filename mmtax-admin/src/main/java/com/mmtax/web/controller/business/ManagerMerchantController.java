package com.mmtax.web.controller.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.CooperationMapper;
import com.mmtax.business.service.ICheckAndChangeRateService;
import com.mmtax.business.service.IMerchantInfoService;
import com.mmtax.business.service.IModifyMerchantInfoService;
import com.mmtax.business.service.IQueryMerchantInfoService;
import com.mmtax.business.service.impl.INvoiceHandleOldDataServiceImpl;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.constant.WkycConstants;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.http.HttpUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import java.io.IOException;
import java.util.List;


/**
 * 后台商户信息管理模块
 *
 * @author yuanligang
 * @date 2019/7/9
 */
@Api(tags = "商户管理")
@Controller
@RequestMapping("manager/merchant")
public class ManagerMerchantController extends BaseController {
    private String prefix = "business/merchant";

    @Autowired
    private IMerchantInfoService addMerchantInfoService;

    @Autowired
    private IModifyMerchantInfoService modifyMerchantInfoService;

    @Autowired
    private IQueryMerchantInfoService queryMerchantInfoService;

    @Autowired
    private INvoiceHandleOldDataServiceImpl iNvoiceHandleOldDataService;

    @Autowired
    private ICheckAndChangeRateService iCheckAndChangeRateService;

    @Autowired
    private CooperationMapper cooperationMapper;

    @RequiresPermissions("manager:merchant:view")
    @GetMapping()
    public String info() {
        return prefix + "/merchantInfo";
    }

    @RequiresPermissions("manager:merchant:cooperation")
    @GetMapping("cooperation")
    public String cooperation() {
        return prefix + "/cooperation";
    }

    @RequiresPermissions("manager:merchant:account")
    @GetMapping("account")
    public String accountConfig() {
        return prefix + "/accountConfig";
    }

    @RequiresPermissions("manager:merchant:password")
    @GetMapping("password")
    public String password() {
        return prefix + "/password";
    }

    @RequiresPermissions("manager:merchant:customerSupport")
    @GetMapping("customerSupport")
    public String customerSupport() {
        return prefix + "/customerSupport";
    }

    @RequiresPermissions("manager:merchant:settleMentInfo")
    @GetMapping("settleMentInfo")
    public String settleMentInfo() {
        return prefix + "/settlementInfo";
    }

    @RequiresPermissions("manager:merchant:settlementInfoDetail")
    @GetMapping("settlementInfoDetail")
    public String settlementInfoDetail() {
        return prefix + "/settlementInfoDetail";
    }

    @RequiresPermissions("manager:merchant:customerSupportDetail")
    @GetMapping("customerSupportDetail")
    public String customerSupportDetail() {
        return prefix + "/customerSupportDetail";
    }


    @ApiOperation("基本信息初始化--> baseInfoInit")
    @RequiresPermissions("manager:merchant:init")
    @GetMapping("baseInfoInit")
    public String baseInfo() {
        return prefix + "/baseInfoInit";
    }

    @ApiOperation("基本信息初始化--> merchantInit")
    @RequiresPermissions("manager:merchant:init")
    @GetMapping("merchantInit")
    public String merchantInit() {
        return prefix + "/merchantInit";
    }

    @ApiOperation("基本信息初始化--> cooperationInit")
    @RequiresPermissions("manager:merchant:init")
    @GetMapping("cooperationInit")
    public String cooperationInit() {
        return prefix + "/cooperationInit";
    }

    @ApiOperation("基本信息初始化--> invoiceInfoInit")
    @RequiresPermissions("manager:merchant:init")
    @GetMapping("invoiceInfoInit")
    public String invoiceInfoInit() {
        return prefix + "/invoiceInfoInit";
    }

    @ApiOperation("基本信息初始化--> accountConfigInit")
    @RequiresPermissions("manager:merchant:init")
    @GetMapping("accountConfigInit")
    public String accountConfigInit() {
        return prefix + "/accountConfigInit";
    }

    @ApiOperation("费率变更审核--> checkAndChangeRate")
    @RequiresPermissions("manager:merchant:chackAndChangeRate")
    @GetMapping("/chackAndChangeRate")
    public String chackAndChangeRatePage(){
        return prefix + "/checkAndChangeRate";
    }

    @RequiresPermissions("manager:merchant:info")
    @GetMapping("info")
    public String baseInfo(@ApiParam(value = "商户id", required = true) int merchantId, ModelMap mmap) {
        MerchantInfoDTO merchantInfo = queryMerchantInfoService.getMerchantInfoById(merchantId);
        mmap.put("merchantInfo", merchantInfo);
        return prefix + "/baseInfo";
    }

    @RequiresPermissions("manager:merchant:cooperationDetail")
    @GetMapping("cooperationDetail")
    public String cooperationDetail(@ApiParam(value = "商户id", required = true) int merchantId, ModelMap mmap) {
        CooperationInfoDetailDTO detailDTO = queryMerchantInfoService.getCooperationInfoDetail(merchantId);
        mmap.put("detail", detailDTO);
        return prefix + "/cooperationDetail";
    }

    @RequiresPermissions("manager:merchant:accountDetail")
    @GetMapping("accountDetail")
    public String accountDetail(@ApiParam(value = "商户id", required = true) int merchantId, ModelMap mmap) {
        MerchantAccountDTO config = queryMerchantInfoService.getMerchantAccountConfig(merchantId);
        mmap.put("config", config);
        return prefix + "/accountDetail";
    }


    @ApiOperation(value = "新增保存 商户、合作、发票、地址、风控等信息")
    @RequiresPermissions("manager:merchant:addSave")
    @Log(title = "商户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody AddMerchantInfoDTO addMerchantInfoDTO) {
        try {
            addMerchantInfoService.addMerchantInfo(addMerchantInfoDTO);
        } catch (BusinessException e) {
            logger.info("manager/merchant/add", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/add", e);
            return AjaxResult.error("新增商户异常");
        }

        return AjaxResult.success();
    }


    @ApiOperation(value = "套餐信息获取", response = MealInfo.class)
    @RequiresPermissions("manager:merchant:queryMeal")
    @Log(title = "套餐信息获取", businessType = BusinessType.SELECT)
    @GetMapping("/queryMeal")
    @ResponseBody
    public AjaxResult queryMeal(@ApiParam(value = "商户id", required = true) int merchantId) {
        MealInfo mealInfo = new MealInfo();
        try {
            mealInfo = addMerchantInfoService.getMealInfoByMerchantId(merchantId);
        } catch (BusinessException b) {
            logger.error("manager/merchant/queryMeal", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/queryMeal", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(mealInfo);
    }

    @ApiOperation(value = "商户列表查询", response = ManagerMerchantInfoDTO.class)
    @RequiresPermissions("manager:merchant:queryMerchantList")
    @Log(title = "商户列表查询", businessType = BusinessType.SELECT)
    @PostMapping("/query")
    @ResponseBody
    public TableDataInfo query(ManagerMerchantQueryDTO managerMerchantQueryDTO) {
        startPage();
        List<ManagerMerchantInfoDTO> list = queryMerchantInfoService.listMerchantInfos(managerMerchantQueryDTO, null);
        return getDataTable(list);
    }

    @ApiOperation(value = "商户列表导出")
    @RequiresPermissions("manager:merchant:queryMerchantList")
    @Log(title = "商户列表查询", businessType = BusinessType.SELECT)
    @PostMapping("/exportMerchant")
    @ResponseBody
    public AjaxResult exportMerchants(ManagerMerchantQueryDTO managerMerchantQueryDTO) {
        List<ManagerMerchantInfoDTO> list = queryMerchantInfoService.listMerchantInfos(managerMerchantQueryDTO, null);
        ExcelUtil<ManagerMerchantInfoDTO> util = new ExcelUtil<>(ManagerMerchantInfoDTO.class);
        AjaxResult result = util.exportExcel(list, "商户列表");
        return result;
    }

    @ApiOperation(value = "商户详细查询")
    @RequiresPermissions("manager:merchant:queryMerchantDetail")
    @Log(title = "商户详细查询", businessType = BusinessType.SELECT)
    @GetMapping("/queryById")
    @ResponseBody
    public String queryById(@ApiParam(value = "商户id", required = true) int merchantId, ModelMap mmap) {
        MerchantInfoDTO merchantInfo = queryMerchantInfoService.getMerchantInfoById(merchantId);
        mmap.put("merchantInfo", merchantInfo);
        return prefix + "baseInfo";
    }

    @ApiOperation(value = "商户基本信息修改")
    @RequiresPermissions("manager:merchant:updateInfo")
    @Log(title = "商户基本信息修改", businessType = BusinessType.SELECT)
    @PostMapping("/modifyMerchantInfo")
    @ResponseBody
    public AjaxResult modifyMerchantInfo(@RequestBody ModifyMerchantDTO merchantInfo) {
        try {
            modifyMerchantInfoService.updateMerchantInfo(merchantInfo);

        } catch (BusinessException e) {
            logger.info("manager/merchant/modifyMerchantInfo", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/modifyMerchantInfo", e);
            return AjaxResult.error("修改失败");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "商户状态变更 激活ACTIVE锁定LOCKED注销CANCEL")
    @RequiresPermissions("manager:merchant:updateInfo")
    @Log(title = "商户基本信息修改", businessType = BusinessType.SELECT)
    @PostMapping("/modifyStatus")
    @ResponseBody
    public AjaxResult modifyStatus(@RequestBody MerchantStatusDTO merchantStatusDTO) {
        try {
            modifyMerchantInfoService.updateMerchantStatus(merchantStatusDTO);
        } catch (BusinessException e) {
            logger.info("manager/merchant/modifyStatus", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/modifyStatus", e);
            return AjaxResult.error("更新失败");
        }
        return AjaxResult.success();
    }

    @ApiOperation("费率变更审核信息查询")
    @RequiresPermissions("manager:merchant:chackAndChangeRate")
    @Log(title = "费率变更审核信息查询",businessType = BusinessType.SELECT)
    @PostMapping("/chackAndChangeRate")
    @ResponseBody
    public TableDataInfo chackAndChangeRate(CheckAndChangeRateDTO changeRateDTO){
        startPage();
        List<CheckAndChangeRate> checkAndChangeRates  = iCheckAndChangeRateService.selectAllChangeRateInfo(changeRateDTO);
        return getDataTable(checkAndChangeRates);
    }

    @ApiOperation("费率变更审核通过")
    @RequiresPermissions("manager:merchant:changeRateSuccess")
    @Log(title = "费率变更通过",businessType = BusinessType.UPDATE)
    @PostMapping("/changeRateSuccess")
    @ResponseBody
    public AjaxResult changeRateSuccess(@RequestBody ChangeRateSuccessDTO changeRateSuccessDTO){
        boolean result = false;
        try {
            result = iCheckAndChangeRateService.updateRateSuccess(changeRateSuccessDTO);
            if (result){
                //修改合作信息中的费率
                Cooperation cooperation = new Cooperation();
                cooperation.setSingleServiceRate(changeRateSuccessDTO.getNewOrdinaryServiceRate());
                cooperation.setSingleServiceBigRate(changeRateSuccessDTO.getNewBigServiceRate());
                Example example = new Example(Cooperation.class);
                example.createCriteria().andEqualTo("merchantId",changeRateSuccessDTO.getMerchantId());
                int update = cooperationMapper.updateByExampleSelective(cooperation, example);
                if(update<=0){
                    throw new BusinessException("修改合作信息中的费率失败");
                }
            }
        } catch (BusinessException b) {
            logger.info("manager/merchant/changeRateSuccess", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/changeRateSuccess", e);
            return AjaxResult.error("费率变更失败");
        }
        return AjaxResult.success(result);
    }
    @ApiOperation("费率变更审驳回")
    @RequiresPermissions("manager:merchant:changeRateFail")
    @Log(title = "费率变更驳回",businessType = BusinessType.UPDATE)
    @PostMapping("/changeRateFail")
    @ResponseBody
    public AjaxResult changeRateFail(Integer merchantId){
        boolean result;
        try {
            result = iCheckAndChangeRateService.updateRateFail(merchantId);
        } catch (BusinessException b) {
            logger.info("manager/merchant/changeRateFail", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/changeRateFail", e);
            return AjaxResult.error("费率变更失败");
        }
        return AjaxResult.success(result);
    }
    @ApiOperation(value = "获取税源地信息", response = ManagerTaxSourceCompanyDTO.class)
    @RequiresPermissions("manager:merchant:getTaxSourceCompany")
    @Log(title = "商户基本信息修改", businessType = BusinessType.SELECT)
    @GetMapping("/getTaxSourceCompany")
    @ResponseBody
    public AjaxResult getTaxSourceCompany() {
        List<ManagerTaxSourceCompanyDTO> list = queryMerchantInfoService.getTaxSourceCompany();
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "获取代征主体", response = SubjectInfo.class)
    @Log(title = "获取代征主体", businessType = BusinessType.SELECT)
    @GetMapping("/getSubjectInfo")
    @ResponseBody
    public AjaxResult getSubjectInfo() {
        List<SubjectInfo> list = queryMerchantInfoService.getSubjectInfo();
        return AjaxResult.success(list);
    }


    @ApiOperation(value = "商户密码重置")
    @RequiresPermissions("manager:merchant:resetPassword")
    @Log(title = "商户管理-->密码修改", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPassword")
    @ResponseBody
    public AjaxResult updatePassword(@RequestBody IdListDTO ids) {
        try {
            modifyMerchantInfoService.resetMerchantPassword(ids.getIds());
        } catch (BusinessException e) {
            logger.info("manager/merchant/resetPassword", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/resetPassword", e);
            return AjaxResult.error("密码修改失败");
        }
        return AjaxResult.success("密码修改成功");

    }

    @ApiOperation(value = "商户开票信息获取")
    @RequiresPermissions("manager:merchant:queryInvoiceInfo")
    @Log(title = "商户开票信息获取", businessType = BusinessType.SELECT)
    @GetMapping("/queryInvoiceInfo")
    @ResponseBody
    public AjaxResult queryInvoiceInfo(@ApiParam(value = "商户id", required = true) int merchantId) {
        SysInvoiceInfoDTO invoiceInfo;
        try {
            invoiceInfo = queryMerchantInfoService.getSysInvoiceInfoByMerchantId(merchantId);
        } catch (BusinessException b) {
            logger.info("manager/merchant/queryInvoiceInfo", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/queryInvoiceInfo", e);
            return AjaxResult.error("查询失败");
        }
        return AjaxResult.success(invoiceInfo);
    }

    @ApiOperation(value = "商户收货地址")
    @RequiresPermissions("manager:merchant:queryAddressInfo")
    @Log(title = "商户个人开票信息获取", businessType = BusinessType.SELECT)
    @GetMapping("/queryAddressInfo")
    @ResponseBody
    public AjaxResult queryAddressInfo(@ApiParam(value = "商户id", required = true) int merchantId) {
        Address address;
        try {
            address = queryMerchantInfoService.getAddressInfoByMerchantId(merchantId);
        } catch (BusinessException b) {
            logger.info("manager/merchant/queryAddressInfo", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/queryAddressInfo", e);
            return AjaxResult.error("查询失败");
        }
        return AjaxResult.success(address);
    }

    @ApiOperation(value = "修改送发票收货地址")
    @RequiresPermissions("manager:merchant:updateAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value =
                    "地址id", required = true, paramType = "String"),
            @ApiImplicitParam(name = "address", value =
                    "收货地址", required = true, paramType = "String"),
            @ApiImplicitParam(name = "mobile", value =
                    "收货人手机号", required = true, paramType = "String"),
            @ApiImplicitParam(name = "addresseeName", value =
                    "收货人名字", required = true, paramType = "String")})
    @Log(title = "地址修改", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAddress")
    @ResponseBody
    public AjaxResult updateAddress(@ApiIgnore @RequestBody Address address) {
        try {
            modifyMerchantInfoService.updateAddressInfo(address);
        } catch (BusinessException b) {
            logger.error("manager/merchant/updateAddress", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/updateAddress", e);
            return AjaxResult.error("修改地址失败");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "商户个人发票内容修改")
    @RequiresPermissions("manager:merchant:updateInvoiceInfo")
    @Log(title = "商户个人发票内容修改", businessType = BusinessType.UPDATE)
    @PostMapping("/updateInvoiceInfo")
    @ResponseBody
    public AjaxResult updateInvoiceInfo(@RequestBody SysInvoiceInfoDTO sysInvoiceInfoDTO) {
        try {
            modifyMerchantInfoService.updateInvoiceInfo(sysInvoiceInfoDTO);
        } catch (BusinessException b) {
            logger.info("manager/merchant/updateInvoiceInfo", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/updateInvoiceInfo", e);
            return AjaxResult.error("修改发票内容失败");
        }
        return AjaxResult.success();
    }
    @ApiOperation(value = "商户个人账户设置信息获取")
    @RequiresPermissions("manager:merchant:queryAccountConfig")
    @Log(title = "商户个人账户设置信息获取", businessType = BusinessType.SELECT)
    @GetMapping("/queryAccountConfig")
    @ResponseBody
    public AjaxResult queryAccountConfig(@ApiParam(value = "商户id", required = true) int merchantId) {
        MerchantAccountDTO config;
        try {
            config = queryMerchantInfoService.getMerchantAccountConfig(merchantId);
        } catch (BusinessException b) {
            logger.info("manager/merchant/queryAccountConfig", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/queryAccountConfig", e);
            return AjaxResult.error("查询失败");
        }
        return AjaxResult.success(config);
    }

    @ApiOperation(value = "商户个人账户设置信息编辑")
    @RequiresPermissions("manager:merchant:updateAccountConfig")
    @Log(title = "商户个人账户设置信息编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAccountConfig")
    @ResponseBody
    public AjaxResult updateAccountConfig(@RequestBody UpdateAccountConfigDTO dto) {
        try {
            modifyMerchantInfoService.updateAccountConfig(dto);
        } catch (BusinessException b) {
            logger.info("manager/merchant/updateAccountConfig", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/updateAccountConfig", e);
            return AjaxResult.error("修改失败");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "商户合作信息列表", response = CooperationInfoDTO.class)
    @RequiresPermissions("manager:merchant:queryCooperationInfo")
    @Log(title = "商户合作信息列表", businessType = BusinessType.SELECT)
    @PostMapping("/queryCooperationInfo")
    @ResponseBody
    public TableDataInfo queryCooperationInfo(ManagerMerchantQueryDTO managerMerchantQueryDTO) {
        startPage();
        List<CooperationInfoDTO> list = queryMerchantInfoService.getCooperationInfoList(managerMerchantQueryDTO, null);
        return getDataTable(list);
    }


    @ApiOperation(value = "商户个人账户合作信息详情获取")
    @RequiresPermissions("manager:merchant:queryCooperationInfoDetail")
    @Log(title = "商户合作信息查询", businessType = BusinessType.SELECT)
    @GetMapping("/queryCooperationInfoDetail")
    @ResponseBody
    public AjaxResult queryCooperationInfoDetail(@ApiParam(value = "商户ID", required = true) int merchantId) {
        CooperationInfoDetailDTO detailDTO;
        try {
            detailDTO = queryMerchantInfoService.getCooperationInfoDetail(merchantId);
        } catch (BusinessException e) {
            logger.info("manager/merchant/queryCooperationInfoDetail", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant /queryCooperationInfoDetail", e);
            return AjaxResult.error("查询失败");
        }
        return AjaxResult.success(detailDTO);
    }


    @ApiOperation(value = "商户合作信息详情编辑")
    @RequiresPermissions("manager:merchant:updateCooperationInfoDetail")
    @Log(title = "商户合作信息查询", businessType = BusinessType.UPDATE)
    @PostMapping("/updateCooperationInfoDetail")
    @ResponseBody
    public AjaxResult updateCooperationInfoDetail(@RequestBody ManagerCooperationDTO managerCooperationDTO) {
        try {
            modifyMerchantInfoService.updateCooperationDetail(managerCooperationDTO);
        } catch (BusinessException e) {
            logger.info("manager/merchant/updateCooperationInfoDetail", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant/updateCooperationInfoDetail", e);
            return AjaxResult.error("更新失败");
        }
        return AjaxResult.success();
    }


    @ApiOperation(value = "风险控制编辑")
    @RequiresPermissions("manager:merchant:updateRiskDetail")
    @Log(title = "商户合作信息查询", businessType = BusinessType.UPDATE)
    @PostMapping("/updateRiskConfigDetail")
    @ResponseBody
    public AjaxResult updateCooperationInfoDetail(ManagerRiskConfigDTO managerRiskConfigDTO) {
        try {
            modifyMerchantInfoService.updateRiskDetail(managerRiskConfigDTO);
        } catch (BusinessException e) {
            logger.info("manager/merchant/updateRiskConfigDetail", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant /updateRiskConfigDetail", e);
            return AjaxResult.error("更新失败");
        }
        return AjaxResult.success();
    }


    @ApiOperation(value = "商户搜索-->模糊匹配")
    @Log(title = "商户模糊匹配", businessType = BusinessType.SELECT)
    @GetMapping("/fuzzyMatching")
    @ResponseBody
    public AjaxResult fuzzyMatching(String merchantName) {
        List<FuzzyMatchingDTO> list;
        try {
            list = queryMerchantInfoService.getMerchantNameFuzzyMatching(merchantName);
        } catch (BusinessException e) {
            logger.info("manager/merchant/fuzzyMatching", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant /fuzzyMatching", e);
            return AjaxResult.error("匹配失败");
        }
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "校验商户名称")
    @PostMapping("checkMerchantName")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantName", value =
                    "商户名称", required = true, paramType = "String")})
    public AjaxResult checkMerchantName(@ApiIgnore @RequestBody MerchantInfo merchantInfo) {
        try {
            queryMerchantInfoService.checkMerchantName(merchantInfo.getMerchantName());
        } catch (BusinessException b) {
            logger.info("manager/merchant/checkMerchantName", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.info("manager/merchant/checkMerchantName", e);
            return AjaxResult.error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "校验邮箱")
    @PostMapping("checkEmail")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value =
                    "商户名称", required = true, paramType = "String")})
    public AjaxResult checkEmail(@ApiIgnore @RequestBody MerchantInfo merchantInfo) {
        try {
            queryMerchantInfoService.checkEmail(merchantInfo.getEmail());
        } catch (BusinessException b) {
            logger.info("manager/merchant/email", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.info("manager/merchant/email", e);
            return AjaxResult.error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "校验联系人手机号")
    @PostMapping("checkContactsMobile")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contactsMobile", value =
                    "联系人手机号", required = true, paramType = "String")})
    public AjaxResult checkContactsMobile(@ApiIgnore @RequestBody MerchantInfo merchantInfo) {
        try {
            queryMerchantInfoService.checkContactsMobile(merchantInfo.getContactsMobile());
        } catch (BusinessException b) {
            logger.info("manager/merchant/checkContactsMobile", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.info("manager/merchant/checkContactsMobile", e);
            return AjaxResult.error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "校验企业名称")
    @PostMapping("checkCompanyName")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyName", value =
                    "企业名称", required = true, paramType = "String")})
    public AjaxResult checkCompanyName(@ApiIgnore @RequestBody MerchantInfo merchantInfo) {
        try {
            queryMerchantInfoService.checkCompanyName(merchantInfo.getCompanyName());
        } catch (BusinessException b) {
            logger.info("manager/merchant/checkCompanyName", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.info("manager/merchant/checkCompanyName", e);
            return AjaxResult.error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "校验营业执照编码")
    @PostMapping("checkBusinessLicenseCode")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessLicenseCode", value =
                    "营业执照编码", required = true, paramType = "String")})
    public AjaxResult checkBusinessLicenseCode(@ApiIgnore @RequestBody MerchantInfo merchantInfo) {
        try {
            queryMerchantInfoService.checkBusinessLicenseCode(merchantInfo.getBusinessLicenseCode());
        } catch (BusinessException b) {
            logger.info("manager/merchant/checkBusinessLicenseCode", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.info("manager/merchant/checkBusinessLicenseCode", e);
            return AjaxResult.error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "校验开户名称")
    @PostMapping("checkAccountName")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountName", value =
                    "校验开户名称", required = true, paramType = "String")})
    public AjaxResult checkAccountName(@ApiIgnore @RequestBody SettlementInfo settlementInfo) {

        try {
            queryMerchantInfoService.checkAccountName(settlementInfo.getAccountName());
        } catch (BusinessException b) {
            logger.info("manager/merchant/checkAccountName", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.info("manager/merchant/checkAccountName", e);
            return AjaxResult.error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "校验对公账户")
    @PostMapping("checkAccountNo")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountNo", value =
                    "对公账户", required = true, paramType = "String")})
    public AjaxResult checkAccountNo(@ApiIgnore @RequestBody SettlementInfo settlementInfo) {
        try {
            queryMerchantInfoService.checkAccountNo(settlementInfo.getAccountNo());
        } catch (BusinessException b) {
            logger.info("manager/merchant/checkAccountNo", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.info("manager/merchant/checkAccountNo", e);
            return AjaxResult.error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "客户支持列表", response = ManagerCustomerSupportDTO.class)
    @RequiresPermissions("manager:merchant:customerSupportList")
    @Log(title = "商户合作信息列表", businessType = BusinessType.SELECT)
    @PostMapping("/customerSupportList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantName", value =
                    "商户名称", required = true, paramType = "String"),
            @ApiImplicitParam(name = "saleName", value =
                    "销售名称", required = true, paramType = "String")})
    public TableDataInfo customerSupportList(@ApiIgnore ManagerCustomerSupportDTO dto) {
        startPage();
        List<ManagerCustomerSupportDTO> list = queryMerchantInfoService.customerSupportList(dto, null);
        return getDataTable(list);
    }

    @ApiOperation(value = "客户支持更新")
    @RequiresPermissions("manager:merchant:updateCustomerSupport")
    @Log(title = "商户合作信息查询", businessType = BusinessType.UPDATE)
    @PostMapping("/updateCustomerSupport")
    @ResponseBody
    public AjaxResult updateCustomerSupport(@RequestBody ManagerCustomerSupportDTO dto) {
        try {
            modifyMerchantInfoService.updateCustomerSupport(dto);
        } catch (BusinessException e) {
            logger.info("manager/merchant/updateCustomerSupport", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant /updateCustomerSupport", e);
            return AjaxResult.error("更新失败");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "结算信息列表", response = ManagerSettleMentInfoDTO.class)
    @RequiresPermissions("manager:merchant:settleMentInfoList")
    @Log(title = "结算信息列表", businessType = BusinessType.SELECT)
    @PostMapping("/settleMentInfoList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantName", value =
                    "商户名称", required = true, paramType = "String"),
            @ApiImplicitParam(name = "saleName", value =
                    "销售名称", required = true, paramType = "String")})
    public TableDataInfo settleMentInfoList(@ApiIgnore ManagerSettleMentInfoDTO dto) {
        startPage();
        List<ManagerSettleMentInfoDTO> list = queryMerchantInfoService.settleMentInfoList(dto, null);
        return getDataTable(list);
    }


    @ApiOperation(value = "结算信息更新")
    @RequiresPermissions("manager:merchant:updateSettleMentInfo")
    @Log(title = "结算信息更新", businessType = BusinessType.UPDATE)
    @PostMapping("/updateSettleMentInfo")
    @ResponseBody
    public AjaxResult updateSettleMentInfo(@RequestBody ManagerSettleMentInfoDTO dto) {
        try {
            modifyMerchantInfoService.updateSettleMentInfo(dto);
        } catch (BusinessException e) {
            logger.info("manager/merchant/updateSettleMentInfo", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("manager/merchant /updateSettleMentInfo", e);
            return AjaxResult.error("更新失败");
        }
        return AjaxResult.success();
    }
    @ApiOperation(value = "处理bank_number旧数据，把原始数据拆分开，分别添加到bank_number、bank_name")
    @RequiresPermissions("manager:merchant:updateInvoice")
    @Log(title = "处理旧数据",businessType = BusinessType.UPDATE)
    @PostMapping("/updateInvoiceOld")
    @ResponseBody
    public AjaxResult updateInvoiceOld(){
        iNvoiceHandleOldDataService.noviceHandleOldData();
        return AjaxResult.success();
    }
}
