package com.mmtax.business.controller.merchant;

import com.mmtax.business.dto.*;
import com.mmtax.business.service.IMerchantAccountService;
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
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/15 16:15
 */
@Api(tags = "商户端商户账户详情")
@Controller
@RequestMapping("merchant/merchantAccount")
public class MerchantAccountController extends BaseController {


    @Autowired
    private IMerchantAccountService merchantAccountService;
    @Autowired
    private IMerchantTransactionService merchantTransactionService;

    @ApiOperation(value = "获取商户当月代付总笔数和总金额", response = MerchantSumPaymentDetailDTO.class)
    @GetMapping("/getMerchantMonthPayment")
    @ResponseBody
    public AjaxResult getMerchantMonthPayment(@ApiParam(value = "商户id", required = true) int merchantId) {
        MerchantSumPaymentDetailDTO detailDTO = new MerchantSumPaymentDetailDTO();
        try {
            detailDTO = merchantAccountService.getMerchantSumPaymentDetail(merchantId);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/getMerchantMonthPayment", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/getMerchantMonthPayment", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(detailDTO);
    }

    @ApiOperation(value = "获取商户余额")
    @GetMapping("/getAccount")
    @ResponseBody
    public AjaxResult getAccount(@ApiParam(value = "商户id", required = true) int merchantId) {
        BigDecimal amount = BigDecimal.ZERO;
        try {
            amount = merchantAccountService.getAccountByMerchantId(merchantId);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/getAccount", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/getAccount", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(amount);
    }

    @ApiOperation(value = "获取充值记录", response = MerchantRechargeRecordDTO.class)
    @GetMapping("/getRechargeRecord")
    @ResponseBody
    public AjaxResult getRechargeRecord(@ApiParam(value = "商户id", required = true) int merchantId,
                                        @ApiParam(value = "起始时间", required = false) String startDate,
                                        @ApiParam(value = "结束时间", required = false) String endDate,
                                        @ApiParam(value = "页码", required = true) Integer currentPage,
                                        @ApiParam(value = "每页大小", required = true) Integer pageSize,
                                        @ApiParam(value = "充值渠道BANK-银行ALIPAY-支付宝WECHAT-微信",
                                                required = false) String rechargeChannel,
                                        @ApiParam(value = "ONLINE-线上UNDERLINE-线下", required = false) String rechargeType,
                                        @ApiParam(value = "充值状态SUCCESS-成功FAIL-失败", required = false) String status) {
        Page page = new Page();
        try {
            page = merchantAccountService.getRechargeRecord(merchantId, rechargeChannel, rechargeType, status,
                    pageSize, currentPage, startDate, endDate);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/getRechargeRecord", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/getRechargeRecord", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "获取户户转账记录", response = MerchantRechargeRecordDTO.class)
    @GetMapping("/getTransferAccounts")
    @ResponseBody
    public AjaxResult getTransferAccounts(@ApiParam(value = "商户id", required = true) int merchantId,
                                          @ApiParam(value = "起始时间", required = false) String startDate,
                                          @ApiParam(value = "结束时间", required = false) String endDate,
                                          @ApiParam(value = "0-转入1-转出", required = true) Integer type,
                                          @ApiParam(value = "页码", required = true) Integer currentPage,
                                          @ApiParam(value = "每页大小", required = true) Integer pageSize) {
        Page page = new Page();
        try {
            page = merchantAccountService.getPageTransferAccounts(merchantId, pageSize, currentPage,
                    startDate, endDate, type);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/getTransferAccounts", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/getTransferAccounts", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "获取商户年度代付总金额", response = MainPagePayDTO.class)
    @GetMapping("/getMerchantYearPayment")
    @ResponseBody
    public AjaxResult getMerchantYearPayment(@ApiParam(value = "商户id", required = true) int merchantId) {
        MainPagePayDTO mainPagePayDTO;
        try {
            mainPagePayDTO = merchantAccountService.getPayTrend(merchantId);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/getMerchantYearPayment", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/getMerchantYearPayment", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(mainPagePayDTO);
    }

    @ApiOperation(value = "月份消费走势图", response = PayTrendDTO.class)
    @GetMapping("/getMerchantMonthPaymentDetail")
    @ResponseBody
    public AjaxResult getMerchantMonthPaymentDetail(@ApiParam(value = "起始日期", required = false) String startDate,
                                                    @ApiParam(value = "结束日期", required = false) String endDate,
                                                    @ApiParam(value = "商户id", required = true) Integer merchantId) {
        List<PayTrendDTO> list = new ArrayList<>();
        try {
            list = merchantAccountService.getMerchantMonthPaymentDetail(merchantId, startDate, endDate);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/getMerchantMonthPaymentDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/getMerchantMonthPaymentDetail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "日消费走势图", response = DayPayTrendDTO.class)
    @GetMapping("/getMerchantDayPaymentDetail")
    @ResponseBody
    public AjaxResult getMerchantDayPaymentDetail(@ApiParam(value = "起始日期", required = false) String startDate,
                                                  @ApiParam(value = "结束日期", required = false) String endDate,
                                                  @ApiParam(value = "商户id", required = true) Integer merchantId) {
        List<DayPayTrendDTO> list = new ArrayList<>();
        try {
            list = merchantAccountService.getMerchantDayPaymentDetail(merchantId, startDate, endDate);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/getMerchantYearPaymentDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/getMerchantYearPaymentDetail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(list);
    }


    @ApiOperation(value = "资金流水查询", response = MerchantCapitalFlowDTO.class)
    @GetMapping("/getCapitalFlow")
    @ResponseBody
    public AjaxResult getCapitalFlow(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                     @ApiParam(value = "每页大小", required = true) Integer pageSize,
                                     @ApiParam(value = "当前页码", required = true) Integer currentPage,
                                     @ApiParam(value = "起始日期", required = false) String startDate,
                                     @ApiParam(value = "结束日期", required = false) String endDate,
                                     @ApiParam(value = "订单流水号", required = false) String orderSerialNum,
                                     @ApiParam(value = "交易类型0-打款1-充值", required = false) Integer type) {
        Page page;
        try {
            page = merchantTransactionService.getCapitalFlow(merchantId, pageSize, currentPage, startDate, endDate,
                    orderSerialNum, type);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/getCapitalFlow", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/getCapitalFlow", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "账户抵扣--返点金额", response = ManagerReturnPointDTO.class)
    @Log(title = "账户抵扣--返点金额", businessType = BusinessType.SELECT)
    @GetMapping("/deductionAmount")
    @ResponseBody
    public AjaxResult getCapitalFlow(@ApiParam(value = "商户id", required = true) Integer merchantId) {
        ManagerReturnPointDTO managerReturnPointDTO;
        try {
            managerReturnPointDTO = merchantAccountService.getAccountReturnPoint(merchantId);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/deductionAmount", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/deductionAmount", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(managerReturnPointDTO);
    }

    @ApiOperation(value = "账户抵扣--抵扣账户列表", response = MerchantReturnPointListDTO.class)
    @Log(title = "账户抵扣--返点流水", businessType = BusinessType.SELECT)
    @GetMapping("/backPointFlow")
    @ResponseBody
    public AjaxResult backPointFlow(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                    @ApiParam(value = "每页大小", required = true) Integer pageSize,
                                    @ApiParam(value = "当前页码", required = true) Integer currentPage,
                                    @ApiParam(value = "起始日期", required = false) String startDate,
                                    @ApiParam(value = "结束日期", required = false) String endDate,
                                    @ApiParam(value = "审核状态", required = false) Integer status) {
        Page page;
        try {
            page = merchantAccountService.getMerchantReturnPointList(merchantId, pageSize, currentPage,
                    startDate, endDate, status);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/backPointFlow", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/backPointFlow", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }


    @ApiOperation(value = "导出资金流水")
    @GetMapping("/exportCapitalFlow")
    @ResponseBody
    public AjaxResult exportCapitalFlow(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                        @ApiParam(value = "起始日期", required = false) String startDate,
                                        @ApiParam(value = "结束日期", required = false) String endDate,
                                        @ApiParam(value = "订单流水号", required = false) String orderSerialNum,
                                        @ApiParam(value = "交易类型", required = false) Integer type) {
        List<MerchantCapitalFlowDTO> list = merchantTransactionService.exportCapitalFlow(merchantId, startDate,
                endDate, orderSerialNum, type);
        ExcelUtil<MerchantCapitalFlowDTO> util = new ExcelUtil<MerchantCapitalFlowDTO>(MerchantCapitalFlowDTO.class);
        AjaxResult result = util.exportExcel(list, "资金流水");
        return result;
    }

    @ApiOperation(value = "导出充值记录")
    @GetMapping("/exportRechargeRecord")
    @ResponseBody
    public AjaxResult exportRechargeRecord(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                           @ApiParam(value = "起始日期", required = false) String startDate,
                                           @ApiParam(value = "结束日期", required = false) String endDate,
                                           @ApiParam(value = "充值渠道BANK-银行ALIPAY-支付宝WECHAT-微信",
                                                   required = false) String rechargeChannel,
                                           @ApiParam(value = "ONLINE-线上UNDERLINE-线下", required = false)
                                                   String rechargeType,
                                           @ApiParam(value = "充值状态SUCCESS-成功FAIL-失败", required = false)
                                                   String status) {
        List<MerchantRechargeRecordDTO> list = merchantAccountService.exportMerchantRechargeRecord(startDate,
                endDate, merchantId, rechargeChannel, rechargeType, status);
        ExcelUtil<MerchantRechargeRecordDTO> util = new ExcelUtil<MerchantRechargeRecordDTO>(MerchantRechargeRecordDTO.class);
        AjaxResult result = util.exportExcel(list, "充值记录");
        return result;
    }

    @ApiOperation(value = "充值接口模拟--测试用")
    @PostMapping("/simulationRechargeRecord")
    @ResponseBody
    public AjaxResult simulationRechargeRecord(@RequestBody RechargeSimulationDTO rechargeSimulationDTO) {
        try {
            merchantAccountService.rechargeSimulation(rechargeSimulationDTO);
        } catch (BusinessException b) {
            logger.info("merchant/merchantAccount/simulationRechargeRecord", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/merchantAccount/simulationRechargeRecord", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();

    }


}
