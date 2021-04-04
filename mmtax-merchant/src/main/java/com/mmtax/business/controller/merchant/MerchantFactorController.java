package com.mmtax.business.controller.merchant;

import com.mmtax.business.dto.MerchantFactorListDTO;
import com.mmtax.business.service.IFactorOrderService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/12 14:35
 */
@Api(tags = "要素认证")
@Controller
@RequestMapping("/merchant/factor")
public class MerchantFactorController extends BaseController {

    @Autowired
    private IFactorOrderService factorOrderService;


    @ApiOperation(value = "要素认证订单", response = MerchantFactorListDTO.class)
    @GetMapping("list")
    @ResponseBody
    public AjaxResult list(@ApiParam(value = "商户id", required = true) Integer merchantId,
                           @ApiParam(value = "每页大小", required = true) int pageSize,
                           @ApiParam(value = "页码", required = true) int currentPage,
                           @ApiParam(value = "起始时间", required = false) String startDate,
                           @ApiParam(value = "结束时间", required = false) String endDate,
                           @ApiParam(value = "订单编号", required = false) String orderNo
    ) {
        Page page = new Page();
        try {
            page = factorOrderService.getPageFactorOrder(merchantId, pageSize, currentPage, startDate, endDate, orderNo);
        } catch (BusinessException b) {
            logger.error("/merchant/factor/list", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/factor/list", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

}
