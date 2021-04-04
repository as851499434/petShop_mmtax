package com.mmtax.business.controller.merchant;

import com.mmtax.business.domain.MerchantKey;
import com.mmtax.business.service.IMerchantKeyService;
import com.mmtax.business.tianjindto.TianJinSecretDTO;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/16 19:33
 */
@Api(tags = "秘钥操作")
@Controller
@RequestMapping("/merchant/secret")
public class SecretController extends BaseController {

    @Autowired
    private IMerchantKeyService merchantKeyService;

    @ApiOperation(value = "获取对接信息", response = MerchantKey.class)
    @GetMapping(value = "/getSecret")
    @ResponseBody
    public AjaxResult getSecret(@ApiParam(value = "商户id", required = true) Integer merchantId) {
        MerchantKey merchantKey = new MerchantKey();
        try {
            merchantKey = merchantKeyService.getMerchantKeyByMerchantId(merchantId);
        } catch (Exception e) {
            logger.error("/merchant/secret/getAppKey", e);
        }
        return AjaxResult.success(merchantKey);
    }

    @ApiOperation(value = "添加或更新ip白名单和订单状态回调地址")
    @PostMapping(value = "/addWhiteUrl")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value =
                    "记录id", required = true, paramType = "String"),
            @ApiImplicitParam(name = "callBackAddress", value =
                    "订单回调地址", required = false, paramType = "String"),
            @ApiImplicitParam(name = "whiteUrl", value =
                    "ip地址", required = false, paramType = "String")})
    public AjaxResult updateMerchantKey(@RequestBody @ApiIgnore MerchantKey merchantKey) {
        merchantKeyService.updateMerchantKey(merchantKey);
        return success();
    }

    @ApiOperation(value = "获取天津渠道对接信息", response = TianJinSecretDTO.class)
    @GetMapping(value = "/getTianJinSecret")
    @ResponseBody
    public AjaxResult getTianJinSecret(@ApiParam(value = "商户id", required = true) Integer merchantId) {
        TianJinSecretDTO tianJinSecretDTO = new TianJinSecretDTO();
        try {
            tianJinSecretDTO = merchantKeyService.getTianJinSecret(merchantId);
        } catch (Exception e) {
            logger.error("/merchant/secret/getTianJinSecret", e);
        }
        return AjaxResult.success(tianJinSecretDTO);
    }

    @ApiOperation(value = "添加或更新ip白名单和订单状态回调地址")
    @PostMapping(value = "/updateTianJinSecret")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantId", value =
                    "商户id", required = true, paramType = "String"),
            @ApiImplicitParam(name = "callBackAddress", value =
                    "订单回调地址", required = false, paramType = "String"),
            @ApiImplicitParam(name = "whiteUrl", value =
                    "ip地址", required = false, paramType = "String"),
            @ApiImplicitParam(name = "serverUserUuid", value =
                    "供应商id", required = false, paramType = "String"),
            @ApiImplicitParam(name = "customerAccountUuid", value =
                    "账号id", required = false, paramType = "String"),})
    public AjaxResult updateTianJinSecret(@RequestBody @ApiIgnore TianJinSecretDTO tianJinSecretDTO) {
        merchantKeyService.updateTianJinSecret(tianJinSecretDTO);
        return success();
    }


}
