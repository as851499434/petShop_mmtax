package com.mmtax.web.controller.business;

import com.mmtax.business.dto.ManagerIndexPaymentDTO;
import com.mmtax.business.dto.ManagerIndexYearDataDTO;
import com.mmtax.business.dto.ManagerSaleStatisticsDTO;
import com.mmtax.business.dto.MangerMerchantStatisticsDTO;
import com.mmtax.business.dto.SysUserDTO;
import com.mmtax.business.service.ITrxOrderService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/22 10:37
 */
@Api(tags = "后台管理系统首页")
@Controller
@RequestMapping("/manager/index")
public class ManagerIndexController extends BaseController {

    @Autowired
    private ITrxOrderService trxOrderService;


    @ApiOperation(value = "月度打款详情（废弃）", response = ManagerIndexPaymentDTO.class)
    @GetMapping("monthPayment")
    @ResponseBody
    public AjaxResult getMonthPayment() {
        ManagerIndexPaymentDTO dto;
        try {
            dto = trxOrderService.getMonthPayment(new SysUserDTO());
        } catch (BusinessException b) {
            logger.info("/manager/index/monthPayment", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/index/monthPayment", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(dto);
    }

    @ApiOperation(value = "累计打款详情（废弃）", response = ManagerIndexPaymentDTO.class)
    @GetMapping("cumulativePayment")
    @ResponseBody
    public AjaxResult getCumulativePayment() {
        ManagerIndexPaymentDTO dto = null;
        try {
//            dto = trxOrderService.getMonthPayment(false);
        } catch (BusinessException b) {
            logger.info("/manager/index/cumulativePayment", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/index/cumulativePayment", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(dto);
    }

    @ApiOperation(value = "每月打款金额（废弃）", response = ManagerIndexYearDataDTO.class)
    @GetMapping("monthAmount")
    @ResponseBody
    public AjaxResult getMonthAmount() {
        List<ManagerIndexYearDataDTO> list = new ArrayList<>();
        try {
            list = trxOrderService.getMonthAmount();
        } catch (BusinessException b) {
            logger.info("/manager/index/monthAmount", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/index/monthAmount", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "交易数据汇总", response = ManagerSaleStatisticsDTO.class)
    @GetMapping("getSaleStatistics")
    @ResponseBody
    public AjaxResult getSaleStatistics(SysUserDTO sysUser) {
        ManagerSaleStatisticsDTO managerSaleStatisticsDTO = new ManagerSaleStatisticsDTO();
        try {
            managerSaleStatisticsDTO = trxOrderService.getSaleStatistics(sysUser);
        } catch (BusinessException b) {
            logger.info("/manager/index/getSaleStatistics", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/index/getSaleStatistics", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(managerSaleStatisticsDTO);
    }

    @ApiOperation(value = "累计打款详情", response = ManagerIndexPaymentDTO.class)
    @GetMapping("cumulativePaymentBySale")
    @ResponseBody
    public AjaxResult cumulativePaymentBySale(SysUserDTO sysUser) {
        ManagerIndexPaymentDTO dto = new ManagerIndexPaymentDTO();
        try {
            dto = trxOrderService.cumulativePaymentBySale(sysUser);
        } catch (BusinessException b) {
            logger.info("/manager/index/cumulativePaymentBySale", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/index/cumulativePaymentBySale", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(dto);
    }

    @ApiOperation(value = "当月交易走势图", response = MangerMerchantStatisticsDTO.class)
    @GetMapping("getMerchantMonthPaidStatistics")
    @ResponseBody
    public AjaxResult getMerchantMonthPaidStatistics(SysUserDTO sysUser) {
        List<MangerMerchantStatisticsDTO> list = new ArrayList<>();
             try {
            list = trxOrderService.getMerchantMonthPaidStatistics(sysUser);
        } catch (BusinessException b) {
            logger.info("/manager/index/getMerchantMonthPaidStatistics", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/index/getMerchantMonthPaidStatistics", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "当年交易走势图", response = MangerMerchantStatisticsDTO.class)
    @GetMapping("getMerchantYearPaidStatistics")
    @ResponseBody
    public AjaxResult getMerchantYearPaidStatistics(SysUserDTO sysUser) {
        List<MangerMerchantStatisticsDTO> list = new ArrayList<>();
        try {
            list = trxOrderService.getMerchantYearPaidStatistics(sysUser);
        } catch (BusinessException b) {
            logger.info("/manager/index/getMerchantYearPaidStatistics", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/index/getMerchantYearPaidStatistics", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(list);
    }


}
