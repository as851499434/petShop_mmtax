package com.mmtax.business.controller.transaction;

import com.mmtax.business.dto.MerchantBatchPaymentAmountDetailDTO;
import com.mmtax.business.dto.MerchantBatchPaymentDetailDTO;
import com.mmtax.business.dto.MerchantBatchPaymentRecordDTO;
import com.mmtax.business.dto.RecordNumberDTO;
import com.mmtax.business.service.IBatchPaymentRecordService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
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
 * 商户端批量代付列表
 *
 * @Author: wangzhaoxu
 * @Date: 2019/7/15 9:26
 */
@Api(tags = "商户端批量代付列表")
@Controller
@RequestMapping("/merchant/merchantBatchPaymentRecord")
public class MerchantBatchPaymentRecordController extends BaseController {

    @Autowired
    private IBatchPaymentRecordService batchPaymentRecordService;

    @ApiOperation(value = "商户端批量代付列表", response = MerchantBatchPaymentRecordDTO.class)
    @GetMapping("list")
    @ResponseBody
    public AjaxResult list(@ApiParam(value = "起始时间", required = false) String startDate,
                           @ApiParam(value = "结束时间", required = false) String endDate,
                           @ApiParam(value = "状态", required = false) Integer status,
                           @ApiParam(value = "批次号", required = false) String batchNo,
                           @ApiParam(value = "代付通道BANK-银行ALIPAY-支付宝WECHAT-微信", required = false)
                                   String paymentChannel,
                           @ApiParam(value = "商户id", required = true) Integer merchantId,
                           @ApiParam(value = "页码", required = true) Integer currentPage,
                           @ApiParam(value = "每页大小", required = true) Integer pageSize) {
        Page page = new Page();
        try {
            page = batchPaymentRecordService.getPageBatchPaymentRecord(startDate, endDate, status, batchNo,
                    paymentChannel, merchantId, currentPage, pageSize);
        } catch (BusinessException b) {
            logger.info("/merchant/merchantBatchPaymentRecord/list", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantBatchPaymentRecord/list", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "商户端批量代付记录详情", response = MerchantBatchPaymentDetailDTO.class)
    @GetMapping("detail")
    @ResponseBody
    public AjaxResult detail(@ApiParam(value = "商户id", required = true) Integer merchantId,
                             @ApiParam(value = "记录id", required = true) Integer id,
                             @ApiParam(value = "页码", required = true) Integer currentPage,
                             @ApiParam(value = "每页大小", required = true) Integer pageSize) {
        Page page = new Page();
        try {
            page = batchPaymentRecordService.getPageBatchPaymentDetail(merchantId, id, currentPage, pageSize);
        } catch (BusinessException b) {
            logger.info("/merchant/merchantBatchPaymentRecord/detail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantBatchPaymentRecord/detail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "批量代付详情")
    @GetMapping("/exportDetail")
    @ResponseBody
    public AjaxResult exportDetail(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                   @ApiParam(value = "记录id", required = true) Integer id) {
        List<MerchantBatchPaymentDetailDTO> list = batchPaymentRecordService.exportBatchPaymentDetail(merchantId, id);
        ExcelUtil<MerchantBatchPaymentDetailDTO> util = new ExcelUtil<MerchantBatchPaymentDetailDTO>
                (MerchantBatchPaymentDetailDTO.class);
        AjaxResult result = util.exportExcel(list, "批量代付详情");
        return result;
    }


    @ApiOperation(value = "商户端批量代付记录金额详情", response = MerchantBatchPaymentAmountDetailDTO.class)
    @GetMapping("amountDetail")
    @ResponseBody
    public AjaxResult amountDetail(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                   @ApiParam(value = "记录id", required = true) Integer id) {
        MerchantBatchPaymentAmountDetailDTO detailDTO = new MerchantBatchPaymentAmountDetailDTO();
        try {
            detailDTO = batchPaymentRecordService.getAmountDetail(merchantId, id);
        } catch (BusinessException b) {
            logger.info("/merchant/merchantBatchPaymentRecord/amountDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantBatchPaymentRecord/amountDetail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(detailDTO);
    }

    @ApiOperation(value = "批量打款金额记录数", response = RecordNumberDTO.class)
    @GetMapping("totalRecord")
    @ResponseBody
    public AjaxResult totalRecord(@ApiParam(value = "记录id", required = true) Integer recordId) {
        RecordNumberDTO recordNumberDTO;
        try {
            recordNumberDTO = batchPaymentRecordService.getTotalRecord(recordId);
        } catch (BusinessException b) {
            logger.info("/merchant/merchantBatchPaymentRecord/totalRecord", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/merchantBatchPaymentRecord/totalRecord", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(recordNumberDTO);
    }


}
