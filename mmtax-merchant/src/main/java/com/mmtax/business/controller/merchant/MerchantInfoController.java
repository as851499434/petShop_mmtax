package com.mmtax.business.controller.merchant;

import com.mmtax.business.domain.Address;
import com.mmtax.business.domain.MerchantAccountConfig;
import com.mmtax.business.domain.MerchantInfo;
import com.mmtax.business.dto.*;
import com.mmtax.business.service.IMerchantInfoService;
import com.mmtax.business.service.IModifyMerchantInfoService;
import com.mmtax.business.service.IQueryMerchantInfoService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 商户后台商户信息管理模块
 * @author yuanligang
 * @date 2019/7/14
 */
@Api(tags = "商户端商户信息管理模块")
@Controller
@RequestMapping("merchant/merchantInfo")
public class MerchantInfoController extends BaseController {

    @Autowired
    private IModifyMerchantInfoService modifyMerchantInfoService;

    @Autowired
    private IQueryMerchantInfoService queryMerchantInfoService;

    @Autowired
    private IMerchantInfoService merchantInfoService;

    @ApiOperation(value = "商户管理--商户信息", response = MerchantInfo.class)
    @Log(title = "商户详细查询", businessType = BusinessType.SELECT)
    @GetMapping("/queryMerchantInfo")
    @ResponseBody
    public AjaxResult queryById(@ApiParam(value = "商户id", required = true) int merchantId) {
        MerchantInfoDTO merchantInfo;
        try {
            merchantInfo = queryMerchantInfoService.getMerchantInfoById(merchantId);
        }catch (BusinessException e){
            logger.info("/merchant/merchantInfo/queryMerchantInfo",e);
            return  AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/queryMerchantInfo",e);
            return  AjaxResult.error("查询失败");
        }
        return  AjaxResult.success(merchantInfo);
    }

    @ApiOperation(value = "发票管理--开票信息", response = InvoiceInfoDTO.class)
    @Log(title = "商户个人开票信息获取", businessType = BusinessType.SELECT)
    @GetMapping("/queryInvoiceInfo")
    @ResponseBody
    public AjaxResult queryInvoiceInfo(@ApiParam(value = "商户id", required = true) int merchantId) {
        InvoiceInfoDTO invoiceInfo;
        try {
            invoiceInfo = queryMerchantInfoService.getInvoiceInfoByMerchantId(merchantId);
        }catch (BusinessException b){
            logger.info("/merchant/merchantInfo/queryInvoiceInfo",b);
            return  AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/queryInvoiceInfo",e);
            return  AjaxResult.error("查询失败");
        }
        return  AjaxResult.success(invoiceInfo);
    }

    @ApiOperation(value = "开票信息--编辑")
    @Log(title = "商户个人默认发票内容修改", businessType = BusinessType.UPDATE)
    @PostMapping("/updateInvoiceInfo")
    @ResponseBody
    public AjaxResult updateInvoiceInfo(@RequestBody UpdateInvoiceInfoDTO dto) {
        try {
             queryMerchantInfoService.updateInvoiceDefaultContent(dto);
        }catch (BusinessException b){
            logger.info("/merchant/merchantInfo/updateInvoiceInfo",b);
            return  AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/updateInvoiceInfo",e);
            return  AjaxResult.error("修改发票内容失败");
        }
        return  AjaxResult.success();
    }


    @ApiOperation(value = "商户密码修改")
    @Log(title = "商户管理-->密码修改", businessType = BusinessType.UPDATE)
    @PostMapping("/updatePassword")
    @ResponseBody
    public AjaxResult updatePassword(@RequestBody UpdatePasswordDTO dto) {
        try {
            modifyMerchantInfoService.updateMerchantPassword(dto);
        } catch (BusinessException e) {
            logger.info("/merchant/merchantInfo/updatePassword",e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/updatePassword",e);
            return AjaxResult.error("密码修改失败");
        }

        return AjaxResult.success("密码修改成功");

    }

    @ApiOperation(value = "发票管理--邮寄地址", response = Address.class)
    @Log(title = "商户个人开票信息获取", businessType = BusinessType.SELECT)
    @GetMapping("/queryAddressInfo")
    @ResponseBody
    public AjaxResult queryAddressInfo(@ApiParam(value = "商户id", required = true) int merchantId) {
        Address address;
        try {
            address = queryMerchantInfoService.getAddressInfoByMerchantId(merchantId);
        }catch (BusinessException b){
            logger.info("/merchant/merchantInfo/queryAddressInfo", b);
            return  AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/queryAddressInfo", e);
            return  AjaxResult.error("查询失败");
        }
        return  AjaxResult.success(address);
    }

    @ApiOperation(value = "发票管理--邮寄地址--编辑")
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
            logger.error("/merchant/merchantInfo/updateAddress",b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/updateAddress",e);
            return AjaxResult.error("修改地址失败");
        }
        return AjaxResult.success();
    }


    @ApiOperation(value = "合作信息--查看详情--报税风险--编辑")
    @Log(title = "合作信息--查看详情--报税风险--编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/updateRisk")
    @ResponseBody
    public AjaxResult updateRisk(@RequestBody UpdateRiskDTO updateRiskDTO) {

        try {
            modifyMerchantInfoService.updateRiskConfig(updateRiskDTO);
        }catch (BusinessException b){
            logger.info("/merchant/merchantInfo/updateRisk",b);
            return  AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/updateRisk",e);
            return  AjaxResult.error("修改失败");
        }
        return  AjaxResult.success();
    }


    @ApiOperation(value = "商务中心--账户设置", response = MerchantAccountConfig.class)
    @Log(title = "商务中心--账户设置", businessType = BusinessType.SELECT)
    @GetMapping("/queryAccountConfig")
    @ResponseBody
    public AjaxResult queryAccountConfig(@ApiParam(value = "商户id", required = true) int merchantId) {
        MerchantAccountDTO merchantAccountDTO;
        try {
            merchantAccountDTO = queryMerchantInfoService.getMerchantAccountConfig(merchantId);
        }catch (BusinessException b){
            logger.info("/merchant/merchantInfo/queryAccountConfig",b);
            return  AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/queryAccountConfig",e);
            return  AjaxResult.error("查询失败");
        }
        return AjaxResult.success(merchantAccountDTO);
    }

    @ApiOperation(value = "账户设置--编辑")
    @Log(title = "账户设置--编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAccountConfig")
    @ResponseBody
    public AjaxResult updateAccountConfig(@RequestBody UpdateAccountConfigDTO dto) {
        try {
            modifyMerchantInfoService.updateAccountConfig(dto);
        }catch (BusinessException b){
            logger.info("/merchant/merchantInfo/updateAccountConfig",b);
            return  AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/updateAccountConfig",e);
            return  AjaxResult.error("修改失败");
        }
        return  AjaxResult.success();
    }

    @ApiOperation(value = "账户设置--邮箱")
    @Log(title = "账户设置--编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAccountEmail")
    @ResponseBody
    public AjaxResult updateAccountConfig(@RequestBody UpdateAccountEmailDTO dto) {
        try {
            modifyMerchantInfoService.updateAccountEmail(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/merchantInfo/updateAccountEmail", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/updateAccountEmail", e);
            return AjaxResult.error("修改失败");
        }
        return AjaxResult.success();
    }



    @ApiOperation(value = "合作信息", response = CooperationInfoDTO.class)
    @Log(title = "商户个人账户合作信息获取", businessType = BusinessType.SELECT)
    @GetMapping("/queryCooperationInfo")
    @ResponseBody
    public AjaxResult queryCooperationInfo(@ApiParam(value = "商户ID", required = true) int merchantId) {
        CooperationInfoDTO cooperationInfoDTO;
        try {
            cooperationInfoDTO = queryMerchantInfoService.getCooperationInfo(merchantId);
        }catch (BusinessException e){
            logger.info("/merchant/merchantInfo/queryCooperationInfo",e);
            return  AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/queryCooperationInfo",e);
            return  AjaxResult.error("查询失败");
        }
        return  AjaxResult.success(cooperationInfoDTO);
    }

    @ApiOperation(value = "合作信息--查看详情", response = CooperationInfoDetailDTO.class)
    @Log(title = "商户合作信息查询", businessType = BusinessType.SELECT)
    @GetMapping("/queryCooperationInfoDetail")
    @ResponseBody
    public AjaxResult queryCooperationInfoDetail(@ApiParam(value = "商户ID", required = true) int merchantId) {
        CooperationInfoDetailDTO detailDTO;
        try {
            detailDTO = queryMerchantInfoService.getCooperationInfoDetail(merchantId);
        }catch (BusinessException e){
            logger.info("/merchant/merchantInfo/queryCooperationInfoDetail",e);
            return  AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/queryCooperationInfoDetail",e);
            return  AjaxResult.error("查询失败");
        }
        return  AjaxResult.success(detailDTO);
    }

    /**
     * 获得商户的合作信息开关
     */
    @ApiOperation(value = "获得商户的合作信息开关接口", response = MerchantSwitchDTO.class)
    @GetMapping("/getMerchantSwitch")
    @ResponseBody
    public AjaxResult getMerchantSwitch(Integer merchantId) {
        MerchantSwitchDTO merchantSwitchDTO = new MerchantSwitchDTO();
        try {
            merchantSwitchDTO = merchantInfoService.getMerchantSwitch(merchantId);
        } catch (BusinessException b) {
            logger.info("/merchant/merchantInfo/getMerchantSwitch", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantInfo/getMerchantSwitch", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(merchantSwitchDTO);
    }


}
