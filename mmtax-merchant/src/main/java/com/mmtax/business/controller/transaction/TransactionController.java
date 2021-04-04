package com.mmtax.business.controller.transaction;

import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.ElectronicVoucherDTO;
import com.mmtax.business.dto.ListTrxOrdersDTO;
import com.mmtax.business.dto.MerchantStatisticalDataDTO;
import com.mmtax.business.dto.ReturnMakeUpChargeOrderDTO;
import com.mmtax.business.service.IMerchantTransactionService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 交易管理控制层
 *
 * @author yuanligang
 * @date 2019/7/12
 */
@Api(tags = "商户端交易管理")
@Controller
@RequestMapping("/merchant/transaction")
public class TransactionController extends BaseController {
    @Autowired
    private IMerchantTransactionService merchantTransactionService;



    @ApiOperation(value = "退还服务费订单列表查询", response = ReturnMakeUpChargeOrderDTO.class)
    @Log(title = "交易管理-->退还服务费订单列表查询", businessType = BusinessType.SELECT)
    @GetMapping("/queryReturnOrders")
    @ResponseBody
    public AjaxResult queryReturnOrders(@ApiParam(value = "商户id", required = true) int merchantId,
                                  @ApiParam(value = "每页大小", required = true) int pageSize,
                                  @ApiParam(value = "页码", required = true) int currentPage,
                                  @ApiParam(value = "起始时间", required = false) String startDate,
                                  @ApiParam(value = "结束时间", required = false) String endDate,
                                  @ApiParam(value = "收款户名", required = false) String payeeName,
                                  @ApiParam(value = "订单流水号", required = false) String orderSerialNum,
                                  @ApiParam(value = "退还流水号", required = false) String returnSerialum,
                                  @ApiParam(value = "收款账号", required = false) String payeeBankCard
                                        ) {
        Page page;
        try {
            page = merchantTransactionService.listReturnOrders(merchantId, pageSize, currentPage, startDate, endDate,
                    payeeName, orderSerialNum, returnSerialum ,payeeBankCard);
        } catch (BusinessException b) {
            logger.info("/merchant/transaction/queryReturnOrders", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/transaction/queryReturnOrders", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }


    @ApiOperation(value = "退还订单列表导出", response = ReturnMakeUpChargeOrderDTO.class)
    @Log(title = "交易管理-->退还订单列表导出", businessType = BusinessType.SELECT)
    @GetMapping("/exportReturnOrders")
    @ResponseBody
    public AjaxResult exportRerurnOrders(@ApiParam(value = "商户id", required = true) int merchantId,
                                         @ApiParam(value = "每页大小", required = true) int pageSize,
                                         @ApiParam(value = "页码", required = true) int currentPage,
                                         @ApiParam(value = "起始时间", required = false) String startDate,
                                         @ApiParam(value = "结束时间", required = false) String endDate,
                                         @ApiParam(value = "收款户名", required = false) String payeeName,
                                         @ApiParam(value = "订单流水号", required = false) String orderSerialNum,
                                         @ApiParam(value = "退还流水号", required = false) String returnSerialum,
                                         @ApiParam(value = "收款账号", required = false) String payeeBankCard
    ) {
        List<ReturnMakeUpChargeOrderDTO> list = null;
        ExcelUtil<ReturnMakeUpChargeOrderDTO> util = new ExcelUtil<ReturnMakeUpChargeOrderDTO>
                (ReturnMakeUpChargeOrderDTO.class);
        try {
            list = merchantTransactionService.exportReturnOrder(merchantId,startDate, endDate,
                    payeeName, orderSerialNum, returnSerialum ,payeeBankCard);
        } catch (BusinessException b) {
            logger.info("/merchant/transaction/exportReturnOrders", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/transaction/exportReturnOrders", e);
            return error(e.getMessage());
        }
        AjaxResult result = util.exportExcel(list, "退还订单");
        return result;
    }



    @ApiOperation(value = "交易订单列表查询", response = TrxOrder.class)
    @Log(title = "交易管理-->交易订单列表查询", businessType = BusinessType.SELECT)
    @GetMapping("/queryOrders")
    @ResponseBody
    public AjaxResult queryOrders(@ApiParam(value = "商户id", required = true) int merchantId,
                                  @ApiParam(value = "每页大小", required = true) int pageSize,
                                  @ApiParam(value = "页码", required = true) int currentPage,
                                  @ApiParam(value = "起始时间", required = false) String startDate,
                                  @ApiParam(value = "结束时间", required = false) String endDate,
                                  @ApiParam(value = "商户名称", required = false) String merchantName,
                                  @ApiParam(value = "订单流水号", required = false) String orderSerialNum,
                                  @ApiParam(value = "商户订单号", required = false) String merchantSerialNum,
                                  @ApiParam(value = "收款账号", required = false) String payeeBankCard,
                                  @ApiParam(value = "收款户名", required = false) String payeeName,
                                  @ApiParam(value = "证件号码", required = false) String payeeIdCardNo,
                                  @ApiParam(value = "订单状态", required = false) Integer orderStatus,
                                  //TODO 暂时无法区分交易类型和打款渠道的差异
                                  // @ApiParam(value = "交易类型", required = false) String merchantName,
                                  @ApiParam(value = "打款渠道", required = false) String paymentChannel) {
        Page page;
        try {
            page = merchantTransactionService.listOrders(merchantId, pageSize, currentPage, startDate, endDate,
                    merchantName, orderSerialNum, merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo,
                    orderStatus, paymentChannel);
        } catch (BusinessException b) {
            logger.info("/merchant/transaction/queryOrders", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/transaction/queryOrders", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }



    @ApiOperation(value = "补缴服务费订单列表查询", response = ReturnMakeUpChargeOrderDTO.class)
    @Log(title = "交易管理-->补缴服务费订单列表查询", businessType = BusinessType.SELECT)
    @GetMapping("/queryMakeUpOrders")
    @ResponseBody
    public AjaxResult queryMakeUpOrders(@ApiParam(value = "商户id", required = true) int merchantId,
                                        @ApiParam(value = "每页大小", required = true) int pageSize,
                                        @ApiParam(value = "页码", required = true) int currentPage,
                                        @ApiParam(value = "起始时间", required = false) String startDate,
                                        @ApiParam(value = "结束时间", required = false) String endDate,
                                        @ApiParam(value = "收款户名", required = false) String payeeName,
                                        @ApiParam(value = "订单流水号", required = false) String orderSerialNum,
                                        @ApiParam(value = "补缴流水号", required = false) String makeUpSerialNum,
                                        @ApiParam(value = "收款账号", required = false) String payeeBankCard
    ) {
        Page page;
        try {
            page = merchantTransactionService.listMakeUpOrders(merchantId, pageSize, currentPage, startDate, endDate,
                    payeeName, orderSerialNum, makeUpSerialNum ,payeeBankCard);
        } catch (BusinessException b) {
            logger.info("/merchant/transaction/queryMakeUpOrders", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/transaction/queryMakeUpOrders", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }


    @ApiOperation(value = "补缴订单列表导出", response = TrxOrder.class)
    @Log(title = "交易管理-->补缴订单列表导出", businessType = BusinessType.SELECT)
    @GetMapping("/exportMakeUpOrders")
    @ResponseBody
    public AjaxResult exportMakeUpOrders(@ApiParam(value = "商户id", required = true) int merchantId,
                                         @ApiParam(value = "每页大小", required = true) int pageSize,
                                         @ApiParam(value = "页码", required = true) int currentPage,
                                         @ApiParam(value = "起始时间", required = false) String startDate,
                                         @ApiParam(value = "结束时间", required = false) String endDate,
                                         @ApiParam(value = "收款户名", required = false) String payeeName,
                                         @ApiParam(value = "订单流水号", required = false) String orderSerialNum,
                                         @ApiParam(value = "补缴流水号", required = false) String makeUpSerialNum,
                                         @ApiParam(value = "收款账号", required = false) String payeeBankCard
                                        ) {
        List<ReturnMakeUpChargeOrderDTO> list = null;
        ExcelUtil<ReturnMakeUpChargeOrderDTO> util = new ExcelUtil<ReturnMakeUpChargeOrderDTO>
                (ReturnMakeUpChargeOrderDTO.class);
        try {
            list = merchantTransactionService.exportMakeUpOrder(merchantId,startDate, endDate,
                    payeeName, orderSerialNum, makeUpSerialNum ,payeeBankCard);
        } catch (BusinessException b) {
            logger.info("/merchant/transaction/exportMakeUpOrders", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/transaction/exportMakeUpOrders", e);
            return error(e.getMessage());
        }
        AjaxResult result = util.exportExcel(list, "补缴订单");
        return result;
    }


    @ApiOperation(value = "交易订单列表导出", response = TrxOrder.class)
    @Log(title = "交易管理-->交易订单列表导出", businessType = BusinessType.SELECT)
    @GetMapping("/exportOrders")
    @ResponseBody
    public AjaxResult exportOrders(@ApiParam(value = "商户id", required = true) int merchantId,
                                   @ApiParam(value = "起始时间", required = false) String startDate,
                                   @ApiParam(value = "结束时间", required = false) String endDate,
                                   @ApiParam(value = "商户名称", required = false) String merchantName,
                                   @ApiParam(value = "订单流水号", required = false) String orderSerialNum,
                                   @ApiParam(value = "商户订单号", required = false) String merchantSerialNum,
                                   @ApiParam(value = "收款账号", required = false) String payeeBankCard,
                                   @ApiParam(value = "收款户名", required = false) String payeeName,
                                   @ApiParam(value = "证件号码", required = false) String payeeIdCardNo,
                                   @ApiParam(value = "订单状态", required = false) Integer orderStatus,
                                   //TODO 暂时无法区分交易类型和打款渠道的差异
                                   // @ApiParam(value = "交易类型", required = false) String merchantName,
                                   @ApiParam(value = "打款渠道", required = false) String paymentChannel,
                                   HttpServletResponse response, HttpServletRequest request) {
        List<ListTrxOrdersDTO> list = null;
        ExcelUtil<ListTrxOrdersDTO> util = new ExcelUtil<ListTrxOrdersDTO>
                (ListTrxOrdersDTO.class);
        try {
            list = merchantTransactionService.exportOrder(merchantId, startDate, endDate, merchantName, orderSerialNum,
                    merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo, orderStatus, paymentChannel);
        } catch (BusinessException b) {
            logger.info("/merchant/transaction/exportOrders", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/transaction/exportOrders", e);
            return error(e.getMessage());
        }
        AjaxResult result = util.exportExcel(list, "交易订单");
        return result;
    }

    @ApiOperation(value = "挂起订单列表查询", response = TrxOrder.class)
    @Log(title = "交易管理-->挂起订单列表查询", businessType = BusinessType.SELECT)
    @GetMapping("/queryOrdersByType")
    @ResponseBody
    public AjaxResult queryOrdersByType(@ApiParam(value = "商户id", required = true) int merchantId,
                                        @ApiParam(value = "每页大小", required = true) int pageSize,
                                        @ApiParam(value = "页码", required = true) int currentPage,
                                        @ApiParam(value = "起始时间", required = false) String startDate,
                                        @ApiParam(value = "结束时间", required = false) String endDate,
                                        @ApiParam(value = "商户名称", required = false) String merchantName,
                                        @ApiParam(value = "订单流水号", required = false) String orderSerialNum,
                                        @ApiParam(value = "商户订单号", required = false) String merchantSerialNum,
                                        @ApiParam(value = "收款账号", required = false) String payeeBankCard,
                                        @ApiParam(value = "收款户名", required = false) String payeeName,
                                        @ApiParam(value = "证件号码", required = false) String payeeIdCardNo,
                                        @ApiParam(value = "订单状态", required = true) Integer orderStatus,
                                        //TODO 暂时无法区分交易类型和打款渠道的差异
                                        // @ApiParam(value = "交易类型", required = false) String merchantName,
                                        @ApiParam(value = "打款渠道", required = false) String paymentChannel) {
        Page page;
        try {
            page = merchantTransactionService.listHangsOrders(merchantId, pageSize, currentPage, startDate, endDate,
                    merchantName, orderSerialNum, merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo,
                    orderStatus, paymentChannel);
        } catch (BusinessException b) {
            logger.info("/merchant/transaction/queryOrdersByType", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/transaction/queryOrdersByType", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "交易列表详情", response = TrxOrder.class)
    @Log(title = "交易管理-->交易详情", businessType = BusinessType.SELECT)
    @GetMapping("/orderDetail")
    @ResponseBody
    public AjaxResult updateAddress(@ApiParam(value = "订单ID", required = true) int id) {
        TrxOrder trxOrder;
        try {
            trxOrder = merchantTransactionService.getOrderDetail(id);
        } catch (BusinessException b) {
            logger.info("/merchant/transaction/orderDetail", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/transaction/orderDetail", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(trxOrder);
    }

    @ApiOperation(value = "电子凭单", response = ElectronicVoucherDTO.class)
    @Log(title = "交易管理-->电子凭单", businessType = BusinessType.SELECT)
    @GetMapping("/electronicVoucher")
    @ResponseBody
    public AjaxResult getVoucher(@ApiParam(value = "订单ID", required = true) int id) {
        ElectronicVoucherDTO electronicVoucherDTO;
        try {
            electronicVoucherDTO = merchantTransactionService.getVoucher(id);
        } catch (BusinessException b) {
            logger.info("/merchant/transaction/electronicVoucher", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/transaction/electronicVoucher", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(electronicVoucherDTO);
    }

    @ApiOperation(value = "商户统计数据", response = MerchantStatisticalDataDTO.class)
    @GetMapping("getMerchantStatisticalData")
    @ResponseBody
    public AjaxResult getMerchantStatisticalData(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                                 @ApiParam(value = "起始时间", required = false) String startDate,
                                                 @ApiParam(value = "结束时间", required = false) String endDate,
                                                 @ApiParam(value = "商户名称", required = false) String merchantName,
                                                 @ApiParam(value = "订单流水号", required = false) String orderSerialNum,
                                                 @ApiParam(value = "商户订单号", required = false) String merchantSerialNum,
                                                 @ApiParam(value = "收款账号", required = false) String payeeBankCard,
                                                 @ApiParam(value = "收款户名", required = false) String payeeName,
                                                 @ApiParam(value = "证件号码", required = false) String payeeIdCardNo,
                                                 @ApiParam(value = "订单状态", required = true) Integer orderStatus) {
        MerchantStatisticalDataDTO dataDTO = new MerchantStatisticalDataDTO();
        try {
            dataDTO = merchantTransactionService.getMerchantStatisticalData(startDate, endDate, merchantName
                    , orderSerialNum, merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo, orderStatus, merchantId);
        } catch (BusinessException b) {
            logger.info("/merchant/transaction/getMerchantStatisticalData", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/transaction/getMerchantStatisticalData", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(dataDTO);
    }


}
