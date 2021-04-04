package com.mmtax.business.controller.merchant;

import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.MerchantOrderSheetDTO;
import com.mmtax.business.dto.MerchantSaveOrderSheetFeedBackDTO;
import com.mmtax.business.service.IOrderSheetService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/14 13:33
 */
@Api(tags = "商户端调单管理")
@Controller
@RequestMapping("/merchant/orderSheet")
public class MerchantOrderSheetController extends BaseController {


    @Autowired
    private IOrderSheetService orderSheetService;

    @ApiOperation(value = "商户端调单列表", response = MerchantOrderSheetDTO.class)
    @GetMapping("list")
    @ResponseBody
    public AjaxResult list(@ApiParam(value = "商户id", required = true) int merchantId,
                           @ApiParam(value = "起始时间", required = false) String startDate,
                           @ApiParam(value = "结束时间", required = false) String endDate,
                           @ApiParam(value = "页码", required = true) Integer currentPage,
                           @ApiParam(value = "每页大小", required = true) Integer pageSize,
                           @ApiParam(value = "订单流水号", required = true) String orderSerialNum,
                           @ApiParam(value = "收款用户", required = true) String name,
                           @ApiParam(value = "调单流水号", required = true) String orderNo,
                           @ApiParam(value = "状态0-未处理1-已处理2-已拒绝", required = true) Integer status,
                           @ApiParam(value = "审核结论", required = true) String auditResult
    ) {
        Page page = new Page();
        try {
            page = orderSheetService.getPageMerchantOrderSheet(merchantId, currentPage, pageSize, startDate, endDate,
                    orderSerialNum, name, orderNo, status, auditResult);
        } catch (BusinessException b) {
            logger.info("/merchant/orderSheet/list", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/orderSheet/list", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "商户端调单列表详情", response = TrxOrder.class)
    @GetMapping("detail")
    @ResponseBody
    public AjaxResult detail(@ApiParam(value = "订单id", required = true) int trxOrderId) {
        return AjaxResult.success(orderSheetService.getDetail(trxOrderId));
    }

    @ApiOperation(value = "反馈信息")
    @PostMapping("saveFeedBackInfo")
    @ResponseBody
    public AjaxResult saveFeedInfo(MerchantSaveOrderSheetFeedBackDTO dto) {
        try {
            orderSheetService.saveFeedBackInfo(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/orderSheet/saveFeedBackInfo", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/orderSheet/saveFeedBackInfo", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }


}
