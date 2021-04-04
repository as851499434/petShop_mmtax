package com.mmtax.business.controller.customer.sign;

import com.mmtax.business.dto.*;
import com.mmtax.business.service.IBatchPaymentRecordService;
import com.mmtax.business.service.ISignCustomerInfoService;
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
 * 商户端员工批量签约控制器
 * @author: ljd
 * @Date: 2020/8/1
 */
@Api(tags = "商户端员工批量签约")
@Controller
@RequestMapping("/merchant/customerBatchSign")
public class CustomerBatchSignController extends BaseController {

    @Autowired
    private ISignCustomerInfoService signCustomerInfoService;

    @ApiOperation(value = "员工批量签约列表", response = CustomerBatchSignRecordDTO.class)
    @GetMapping("list")
    @ResponseBody
    public AjaxResult list(@ApiParam(value = "起始时间", required = false) String startDate,
                           @ApiParam(value = "结束时间", required = false) String endDate,
                           @ApiParam(value = "状态", required = false) Integer status,
                           @ApiParam(value = "批次号", required = false) String batchNo,
                           @ApiParam(value = "商户id", required = true) Integer merchantId,
                           @ApiParam(value = "页码", required = true) Integer currentPage,
                           @ApiParam(value = "每页大小", required = true) Integer pageSize) {
        Page page;
        try {
            page = signCustomerInfoService.pageBatchSignRecord(startDate, endDate, status, batchNo, merchantId,
                    currentPage, pageSize);
        } catch (BusinessException b) {
            logger.info("/merchant/customerBatchSign/list", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/customerBatchSign/list", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "员工批量签约详情列表", response = CustomerBatchSignRecordDetailDTO.class)
    @GetMapping("detailList")
    @ResponseBody
    public AjaxResult detailList(@ApiParam(value = "商户id", required = true) Integer merchantId,
                             @ApiParam(value = "记录id", required = true) Integer id,
                             @ApiParam(value = "页码", required = true) Integer currentPage,
                             @ApiParam(value = "每页大小", required = true) Integer pageSize) {
        Page page;
        try {
            page = signCustomerInfoService.pageBatchSignRecordDetail(merchantId, id, currentPage, pageSize);
        } catch (BusinessException b) {
            logger.info("/merchant/customerBatchSign/detailList", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/customerBatchSign/detailList", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "员工批量签约详情导出")
    @GetMapping("/detailExport")
    @ResponseBody
    public AjaxResult detailExport(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                   @ApiParam(value = "记录id", required = true) Integer id) {
        List<CustomerBatchSignRecordDetailDTO> list = signCustomerInfoService.listBatchSignRecordDetail(merchantId,
                id);
        ExcelUtil<CustomerBatchSignRecordDetailDTO> util = new ExcelUtil(CustomerBatchSignRecordDetailDTO.class);
        AjaxResult result = util.exportExcel(list, "员工批量签约详情");
        return result;
    }

    @ApiOperation(value = "员工签约详情列表", response = CustomerSignRecordDetailDTO.class)
    @GetMapping("customerList")
    @ResponseBody
    public AjaxResult detail(@ApiParam(value = "商户id", required = true) Integer merchantId,
                             @ApiParam(value = "签约员工", required = false) String name,
                             @ApiParam(value = "手机号", required = false) String mobile,
                             @ApiParam(value = "签约状态", required = false) Integer signStatus,
                             @ApiParam(value = "起始时间", required = false) String startDate,
                             @ApiParam(value = "终止时间", required = false) String endDate,
                             @ApiParam(value = "页码", required = true) Integer currentPage,
                             @ApiParam(value = "每页大小", required = true) Integer pageSize) {
        Page page;
        try {
            page = signCustomerInfoService.pageCustomerSignRecordDetailDTO(merchantId, name, mobile, signStatus,
                    endDate, startDate, currentPage, pageSize);
        } catch (BusinessException b) {
            logger.info("/merchant/customerBatchSign/customerList", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/customerBatchSign/customerList", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "员工签约详情导出")
    @GetMapping("/customerExport")
    @ResponseBody
    public AjaxResult customerExport(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                     @ApiParam(value = "签约员工", required = false) String name,
                                     @ApiParam(value = "手机号", required = false) String mobile,
                                     @ApiParam(value = "签约状态", required = false) Integer signStatus,
                                     @ApiParam(value = "起始时间", required = false) String startDate,
                                     @ApiParam(value = "终止时间", required = false) String endDate) {
        List<CustomerSignRecordDetailDTO> list = signCustomerInfoService.listCustomerSignRecordDetailDTO(merchantId,
                name, mobile, signStatus, endDate, startDate);
        ExcelUtil<CustomerSignRecordDetailDTO> util = new ExcelUtil(CustomerSignRecordDetailDTO.class);
        AjaxResult result = util.exportExcel(list, "员工签约详情");
        return result;
    }

    @ApiOperation(value = "催签")
    @GetMapping("rushSign")
    @ResponseBody
    public AjaxResult rushSign(@ApiParam(value = "商户id", required = true) Integer merchantId,
                             @ApiParam(value = "签约员工id", required = true) Integer id) {
        try {
            signCustomerInfoService.rushSign(merchantId, id);
        } catch (BusinessException b) {
            logger.info("/merchant/customerBatchSign/rushSign", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/customerBatchSign/rushSign", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

}
