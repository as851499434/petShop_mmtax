package com.mmtax.business.controller.settle;

import com.mmtax.business.dto.MerchantBalanceDTO;
import com.mmtax.business.dto.MerchantBalanceDetailDTO;
import com.mmtax.business.service.IMerchantSettleService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 余额日账单返回
 * @author yuanligang
 * @date 2019/7/23
 */
@Api(tags = "结算管理")
@Controller
@RequestMapping("merchant/settle")
public class BalanceBillController extends BaseController {
    @Autowired
    private IMerchantSettleService merchantSettleService;



    @ApiOperation(value = "余额日账单查询", response = MerchantBalanceDTO.class)
    @Log(title = "结算管理-->余额日账单", businessType = BusinessType.SELECT)
    @GetMapping("balanceBill")
    @ResponseBody
    public AjaxResult balanceBill(@ApiParam(value = "商户id", required = true) int merchantId,
                                  @ApiParam(value = "起始时间", required = false) String startDate,
                                  @ApiParam(value = "结束时间", required = false) String endDate,
                                  @ApiParam(value = "页码", required = true) Integer currentPage,
                                  @ApiParam(value = "每页大小", required = true) Integer pageSize,
                                  @ApiParam(value = "是否包含零元服务费 0--不包含 1--包含" , required = false) Integer free) {
        Page page;
        try {
           page =  merchantSettleService.listBatchPaymentRecord(merchantId,pageSize,currentPage,startDate,endDate,free);
        } catch (BusinessException e) {
            logger.info("merchant/settle/balanceBill", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("merchant/settle/balanceBill", e);
            return AjaxResult.error("余额账单查询失败");
        }

        return AjaxResult.success(page);
    }

    @ApiOperation(value = "余额日账单详情", response = MerchantBalanceDetailDTO.class)
    @Log(title = "结算管理-->余额日账单", businessType = BusinessType.SELECT)
    @GetMapping("balanceBillDetail")
    @ResponseBody
    public AjaxResult balanceBill(@ApiParam(value = "批量打款记录id", required = true) Integer batchPaymentRecordId,
                                  @ApiParam(value = "页码", required = true) Integer currentPage,
                                  @ApiParam(value = "每页大小", required = true) Integer pageSize) {
        Page page;
        try {
            page =  merchantSettleService.listTrxOrderByBatchId(batchPaymentRecordId,pageSize,currentPage);
        } catch (BusinessException e) {
            logger.info("merchant/settle/balanceBillDetail", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("merchant/settle/balanceBillDetail", e);
            return AjaxResult.error("账单详情获取失败");
        }

        return AjaxResult.success(page);
    }

    @ApiOperation(value = "导出日账单")
    @GetMapping("/exportList")
    @ResponseBody
    public AjaxResult exportList(@ApiParam(value = "批量打款记录id", required = true) int batchPaymentRecordId) {
        List<MerchantBalanceDetailDTO> list = merchantSettleService.exportList(batchPaymentRecordId);
        ExcelUtil<MerchantBalanceDetailDTO> util = new ExcelUtil<MerchantBalanceDetailDTO>
                (MerchantBalanceDetailDTO.class);
        AjaxResult result = util.exportExcel(list, "日账单");
        return result;
    }
}
