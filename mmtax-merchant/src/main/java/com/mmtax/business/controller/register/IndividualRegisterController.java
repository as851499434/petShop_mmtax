package com.mmtax.business.controller.register;

import com.mmtax.business.dto.MerchantSupplementDTO;
import com.mmtax.business.service.IMerchantInfoService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 个体用户注册
 *
 * @author yuanligang
 * @date 2019/7/30
 */
@Api(tags = "个体用户注册")
@Controller
@RequestMapping("/merchant/init")
public class IndividualRegisterController extends BaseController {
    @Autowired
    private IMerchantInfoService merchantInfoService;

    @ApiOperation(value = "商户信息补全")
    @PostMapping("/completionInfo")
    @ResponseBody
    public AjaxResult completionInfo(@RequestBody MerchantSupplementDTO merchantSupplementDTO) {
        try {
            merchantInfoService.completionMerchantInfo(merchantSupplementDTO);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/querySuccessInvoices", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/querySuccessInvoices", e);
            return error("注册失败");
        }
        return success();
    }


}
