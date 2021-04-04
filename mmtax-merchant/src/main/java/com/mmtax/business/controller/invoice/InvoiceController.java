package com.mmtax.business.controller.invoice;

import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.InvoiceInfo;
import com.mmtax.business.domain.InvoiceRecord;
import com.mmtax.business.domain.MerchantInfo;
import com.mmtax.business.domain.RechargeRecord;
import com.mmtax.business.dto.*;
import com.mmtax.business.service.IMerchantInvoiceService;
import com.mmtax.business.yunzbdto.YunZBInvoiceBillResultDTO;
import com.mmtax.business.yunzbdto.YunZBInvoiceContentResultDTO;
import com.mmtax.business.yunzbdto.YunZBInvoiceInfoDTO;
import com.mmtax.business.yunzbdto.YunZbNewInvoiceApplyDTO;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.constant.RequestContans;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.utils.redis.RedisUtil;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 商户后台 发票管理
 *
 * @author yuanligang
 * @date 2019/7/12
 */
@Api(tags = "商户端发票管理")
@Controller
@RequestMapping("merchant/invoice")
public class InvoiceController extends BaseController {

    @Autowired
    private IMerchantInvoiceService merchantInvoiceService;



    @ApiOperation(value = "更改发票默认内容")
    @Log(title = "发票管理-->开票信息-->更改发票默认内容", businessType = BusinessType.SELECT)
    @GetMapping("/updateInvoiceDefaultContent")
    @ResponseBody
    public AjaxResult updateInvoiceDefaultContent(@ApiParam(value = "商户ID", required = true) Integer merchantId,
                                     @ApiParam(value = "发票默认内容", required = true) Integer invoiceDefaultContent) {


        try {
            merchantInvoiceService.updateInvoiceDefaultContent(merchantId,invoiceDefaultContent);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/updateInvoiceDefaultContent", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/updateInvoiceDefaultContent", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }


    @ApiOperation(value = "开票信息", response = InvoiceInfo.class)
    @Log(title = "发票管理-->开票信息", businessType = BusinessType.SELECT)
    @GetMapping("/billInfo")
    @ResponseBody
    public AjaxResult billInfo(@ApiParam(value = "商户ID", required = true) Integer merchantId) {

        InvoiceInfo invoiceInfo;
        try {
            invoiceInfo = merchantInvoiceService.billInfo(merchantId);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/billInfo", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/billInfo", e);
            return error("开票信息查询失败");
        }
        return AjaxResult.success(invoiceInfo);
    }

    @ApiOperation(value = "发票退回")
    @Log(title = "发票管理-->申请记录-->退票", businessType = BusinessType.SELECT)
    @GetMapping("/returnInvoice")
    @ResponseBody
    public AjaxResult returnInvoice(RefundInvoiceDTO refundInvoiceDTO) {

        try {
            merchantInvoiceService.returnInvoice(refundInvoiceDTO);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/returnInvoice", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/returnInvoice", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "发票作废")
    @Log(title = "发票管理-->发票申请-->作废", businessType = BusinessType.SELECT)
    @GetMapping("/invoiceInvalid")
    @ResponseBody
    public AjaxResult invoiceInvalid(@ApiParam(value = "商户ID", required = true) Integer merchantId,
                                     @ApiParam(value = "发票申请编号", required = true) String invoiceSerialum) {


        try {
            merchantInvoiceService.invoiceInvalid(merchantId,invoiceSerialum);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/invoiceInvalid", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/invoiceInvalid", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }


    @ApiOperation(value = "申请详情", response = InvoiceApplicationDetailDTO.class)
    @Log(title = "发票管理-->申请记录-->申请详情", businessType = BusinessType.SELECT)
    @GetMapping("/invoiceApplicationDetail")
    @ResponseBody
    public AjaxResult invoiceApplicationDetail(@ApiParam(value = "商户ID", required = true) Integer merchantId,
                                               @ApiParam(value = "发票申请编号", required = true) String invoiceSerialum) {

        InvoiceApplicationDetailDTO invoiceApplicationDetailDTO;
        try {
            invoiceApplicationDetailDTO = merchantInvoiceService.invoiceApplicationDetail(merchantId,invoiceSerialum);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/invoiceApplicationDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/invoiceApplicationDetail", e);
            return error("发票申请详情查询失败");
        }
        return AjaxResult.success(invoiceApplicationDetailDTO);
    }


    @ApiOperation(value = "获取发票申请记录", response = InvoiceRecord.class)
    @Log(title = "发票管理-->申请记录", businessType = BusinessType.SELECT)
    @GetMapping("/queryInvoiceApplyRecords")
    @ResponseBody
    public AjaxResult queryInvoiceApplyRecords(InvoiceApplyRecordDTO invoiceApplyRecordDTO) {
        Page page ;
        List<InvoiceRecord> list = new ArrayList<>();
        try {
           page = merchantInvoiceService.queryInvoiceApplyRecords(invoiceApplyRecordDTO);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/queryInvoiceApplyRecords", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/queryInvoiceApplyRecords", e);
            return error("发票申请记录查询失败");
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "获取可开发票记录")
    @Log(title = "发票管理-->充值记录", businessType = BusinessType.SELECT)
    @GetMapping("/queryInvoiceBills")
    @ResponseBody
    public AjaxResult queryInvoiceBills(InvoiceBillDTO invoiceBillDTO) {
        HashMap<String,Object> map;
        try {
            map = merchantInvoiceService.queryInvoiceBills(invoiceBillDTO);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/queryInvoiceBills", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/queryInvoiceBills", e);
            return error("充值记录查询失败");
        }
        return AjaxResult.success(map);
    }

    @ApiOperation(value = "获取开票服务名称", response = InvoiceServiceNameDTO.class)
    @Log(title = "发票管理-->开票校验", businessType = BusinessType.SELECT)
    @GetMapping("/getInvoiceServiceName")
    @ResponseBody
    public AjaxResult getInvoiceServiceName(@ApiParam(value = "商户id", required = true) Integer merchantId) {
        InvoiceServiceNameDTO invoiceServiceNameDTO = new InvoiceServiceNameDTO();

        try {
            invoiceServiceNameDTO = merchantInvoiceService.getInvoiceServiceName(merchantId);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/getInvoiceServiceName", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/getInvoiceServiceName", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(invoiceServiceNameDTO);
    }

    @ApiOperation(value = "获取核对发票信息", response = CheckingInvoiceDTO.class)
    @Log(title = "发票管理-->发票申请-->核对发票", businessType = BusinessType.SELECT)
    @GetMapping("/checkingInvoice")
    @ResponseBody
    public AjaxResult checkingInvoice(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                      @ApiParam(value = "发票类型", required = true) String invocieType,
                                      @ApiParam(value = "发票备注", required = true) String invoiceNote,
                                      @ApiParam(value = "货物或应税劳务、服务名称", required = true) Integer serviceName) {

        CheckingInvoiceDTO checkingInvoiceDTO= new CheckingInvoiceDTO();
        try {
            checkingInvoiceDTO = merchantInvoiceService.checkingInvoice(merchantId,invocieType,invoiceNote,serviceName);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/checkingInvoice", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/checkingInvoice", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(checkingInvoiceDTO);
    }


    @ApiOperation(value = "保存发票申请信息")
    @Log(title = "发票管理-->发票申请-->核对发票-->确认开票", businessType = BusinessType.SELECT)
    @PostMapping("/saveInvoiceRecord")
    @ResponseBody
    public AjaxResult saveInvoiceRecord(@RequestBody InvoiceRecordDTO invoiceRecordDTO) {


        try {
            merchantInvoiceService.saveInvoiceRecord(invoiceRecordDTO);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/saveInvoiceRecord", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/saveInvoiceRecord", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }



    /**
     * 开票校验
     *
     * @param records 充值记录流水集
     * @return 返回开票金额相关
     */
    @ApiOperation(value = "开票校验:发票申请-->纸质发票（暂时废弃）", response = InvoiceApplyCheckDTO.class)
    @Log(title = "发票管理-->开票校验", businessType = BusinessType.SELECT)
    @PostMapping("/applyInvoiceCheck")
    @ResponseBody
    public AjaxResult applyInvoiceCheck(@RequestBody IdListDTO records) {
        InvoiceApplyCheckDTO apply;
        try {
            apply = merchantInvoiceService.invoiceApplyCheck(records.getIds());
        } catch (BusinessException e) {
            logger.info("merchant/invoice/applyInvoiceCheck", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/applyInvoiceCheck", e);
            return AjaxResult.error("开票异常");
        }

        return AjaxResult.success(apply);
    }



    /**
     * 发票申请
     *
     * @param invoiceApplyDTO
     * @return
     */
    @ApiOperation(value = "开票申请：发票申请-->纸质发票-->提交申请(暂时废弃)")
    @Log(title = "发票管理-->发票申请", businessType = BusinessType.INSERT)
    @PostMapping("/applyInvoice")
    @ResponseBody
    public AjaxResult addInvoice(@RequestBody InvoiceApplyDetailDTO invoiceApplyDTO) {
        try {
            merchantInvoiceService.invoiceApply(invoiceApplyDTO);
        } catch (BusinessException e) {
            logger.info("merchant/invoice/applyInvoice", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/applyInvoice", e);
            return AjaxResult.error("发票申请异常");
        }

        return AjaxResult.success();
    }

    @ApiOperation(value = "开票申请：发票申请-->纸质发票-->提交申请(新)")
    @Log(title = "发票管理-->发票申请", businessType = BusinessType.INSERT)
    @PostMapping("/applyYunZbInvoice")
    @ResponseBody
    public AjaxResult applyYunZbInvoice(@RequestBody YunZbNewInvoiceApplyDTO yunZbNewInvoiceApplyDTO) {
        try {
            merchantInvoiceService.yunZbInvoiceApply(yunZbNewInvoiceApplyDTO);
        } catch (BusinessException e) {
            logger.info("merchant/invoice/applyInvoice", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/applyInvoice", e);
            return AjaxResult.error("发票申请异常");
        }

        return AjaxResult.success();
    }


    @ApiOperation(value = "申请记录：申请记录发票申请记录查询", response = InvoiceQueryDTO.class)
    @Log(title = "发票管理-->开票记录查询", businessType = BusinessType.SELECT)
    @GetMapping("/queryInvoices")
    @ResponseBody
    public AjaxResult queryInvoices(@ApiParam(value = "商户id", required = true) int merchantId,
                                    @ApiParam(value = "每页大小", required = true) int pageSize,
                                    @ApiParam(value = "页码", required = true) int currentPage,
                                    @ApiParam(value = "发票申请编号", required = false) String invoiceSerialNum,
                                    @ApiParam(value = "发票代码", required = false) String invoiceCode,
                                    @ApiParam(value = "起始时间", required = false) String startDate,
                                    @ApiParam(value = "结束时间", required = false) String endDate) {
        Page page;
        try {
            page = merchantInvoiceService.listInvoiceApplications(merchantId, pageSize, currentPage,
                    invoiceSerialNum, invoiceCode, startDate, endDate);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/queryInvoices", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/queryInvoices", e);
            return error("查询失败");
        }
        return AjaxResult.success(page);
    }


    @ApiOperation(value = "申请记录：查看驳回发票", response = InvoiceCheckDetailDTO.class)
    @Log(title = "发票管理-->开票记录查询-->查看驳回发票", businessType = BusinessType.SELECT)
    @GetMapping("/reviewRefuseInvoice")
    @ResponseBody
    public AjaxResult reviewRefuseInvoice(@ApiParam("发票ID") Integer invoiceId) {
        InvoiceCheckDetailDTO detail;
        try {
            detail = merchantInvoiceService.reviewRefuseInvoice(invoiceId);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/reviewRefuseInvoice", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/reviewRefuseInvoice", e);
            return error("查询失败");
        }
        return AjaxResult.success(detail);
    }

    @ApiOperation(value = "申请记录：编辑重开驳回发票")
    @Log(title = "发票管理-->开票记录查询-->编辑重开驳回发票", businessType = BusinessType.SELECT)
    @PostMapping("/modifyRefuseInvoice")
    @ResponseBody
    public AjaxResult modifyRefuseInvoice(@RequestBody MerchantInvoiceRefuseDTO dto) {
        try {
            merchantInvoiceService.updateAndRestartRefuseInvoice(dto);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/modifyRefuseInvoice", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/modifyRefuseInvoice", e);
            return error("保存失败");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "发票管理-->发票详情", response = MerchantInvoicedDetail.class)
    @Log(title = "发票管理-->发票详情", businessType = BusinessType.SELECT)
    @GetMapping("/querySuccessInvoices")
    @ResponseBody
    public AjaxResult querySuccessInvoices(@ApiParam(value = "商户id", required = true) int merchantId,
                                           @ApiParam(value = "每页大小", required = true) int pageSize,
                                           @ApiParam(value = "页码", required = true) int currentPage,
                                           @ApiParam(value = "发票申请编号", required = false) String invoiceSerialNum,
                                           @ApiParam(value = "发票代码", required = false) String invoiceCode,
                                           @ApiParam(value = "起始时间", required = false) String startDate,
                                           @ApiParam(value = "结束时间", required = false) String endDate) {
        Page page;
        try {
            page = merchantInvoiceService.listSuccessInvoiceApplications(merchantId, pageSize, currentPage,
                    invoiceSerialNum, invoiceCode, startDate, endDate);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/querySuccessInvoices", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/querySuccessInvoices", e);
            return error("查询失败");
        }
        return AjaxResult.success(page);
    }


    @ApiOperation(value = "发票管理-->发票详情-->开票记录、金额、税额、价税合计总计",
            response = MerchantInvoiceTotalAmountDTO.class)
    @Log(title = "发票管理-->发票详情-->金额合计", businessType = BusinessType.SELECT)
    @GetMapping("/queryTotalAmount")
    @ResponseBody
    public AjaxResult querySuccessInvoices(@ApiParam(value = "商户id", required = true) int merchantId,
                                           @ApiParam(value = "发票申请编号", required = false) String invoiceSerialNum,
                                           @ApiParam(value = "发票代码", required = false) String invoiceCode,
                                           @ApiParam(value = "起始时间", required = false) String startDate,
                                           @ApiParam(value = "结束时间", required = false) String endDate) {
        MerchantInvoiceTotalAmountDTO detail;

        try {
            detail = merchantInvoiceService.getTotalAmount(merchantId, invoiceSerialNum, invoiceCode, startDate, endDate);

        } catch (BusinessException b) {
            logger.info("merchant/invoice/queryTotalAmount", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/queryTotalAmount", e);
            return error("查询失败");
        }
        return AjaxResult.success(detail);
    }

    @ApiOperation(value = "发票管理-->申请记录-->查看-->开票申请单", response = YunZBInvoiceInfoDTO.class)
    @Log(title = "发票管理-->发票详情", businessType = BusinessType.SELECT)
    @GetMapping("/queryInvoiceDetail")
    @ResponseBody
    public AjaxResult queryInvoiceDetail(@ApiParam(value = "发票id", required = true) int id,
                                         HttpServletRequest request) {
        YunZBInvoiceInfoDTO invoiceDetailDTO;
        String token = request.getHeader(RequestContans.TOKEN_NAME);
        logger.info("merchantInfo:{}",RedisUtil.get(token));
        MerchantInfo merchantInfo = JSON.parseObject(RedisUtil.get(token),MerchantInfo.class);
        try {
            invoiceDetailDTO = merchantInvoiceService.getInvoiceApplyDetail(id,merchantInfo.getMerchantName());
        } catch (BusinessException b) {
            logger.info("merchant/invoice/queryInvoiceDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/queryInvoiceDetail", e);
            return error("发票详情查询失败");
        }
        return AjaxResult.success(invoiceDetailDTO);
    }

    @ApiOperation(value = "发票管理-->申请记录-->查看-->已申请发票")
    @Log(title = "发票管理-->发票详情", businessType = BusinessType.SELECT)
    @GetMapping("/queryInvoiceImg")
    @ResponseBody
    public AjaxResult queryInvoiceImg(@ApiParam(value = "发票详情id", required = true) Integer invoiceDetailId) {
        Object object = new Object();
        try {
            object = merchantInvoiceService.getInvoiceImage(invoiceDetailId);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/queryInvoiceDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/queryInvoiceDetail", e);
            return error("发票详情查询失败");
        }
        return AjaxResult.success(object);
    }

    @ApiOperation(value = "发票充值流水：申请记录-->查看-->充值流水", response = RechargeRecord.class)
    @Log(title = "发票管理-->发票充值流水", businessType = BusinessType.SELECT)
    @GetMapping("/queryRecharge")
    @ResponseBody
    public AjaxResult queryRecharge(@ApiParam(value = "每页大小", required = true) int pageSize,
                                    @ApiParam(value = "页码", required = true) int currentPage,
                                    @ApiParam(value = "发票id", required = true) Integer invoiceId) {
        Page page;
        try {
            page = merchantInvoiceService.getRechargeByInvoiceId(pageSize, currentPage, invoiceId);

        } catch (BusinessException b) {
            logger.info("merchant/invoice/queryRecharge", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/queryRecharge", e);
            return error("发票信息查询失败");
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "发票充值流水：申请记录-->查看-->开票申请总额", response = RechargeRecord.class)
    @Log(title = "发票管理-->开票申请总额", businessType = BusinessType.SELECT)
    @GetMapping("/queryRechargeTotalAmount")
    @ResponseBody
    public AjaxResult queryRecharge(@ApiParam(value = "发票id", required = true) Integer invoiceId) {
        BigDecimal totalAmount;
        try {
            totalAmount = merchantInvoiceService.getInvoiceRechargeAmount(invoiceId);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/queryRechargeTotalAmount", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/queryRechargeTotalAmount", e);
            return error("发票信息查询失败");
        }
        return AjaxResult.success(totalAmount);
    }


    @ApiOperation(value = "发票申请-->充值记录（暂时废弃）", response = MerchantRechargeRecordInvoiceDTO.class)
    @Log(title = "发票管理-->充值记录", businessType = BusinessType.SELECT)
    @GetMapping("/queryRechargeForInvoice")
    @ResponseBody
    public AjaxResult queryRecharge(@ApiParam(value = "商户ID", required = true) int merchantId,
                                    @ApiParam(value = "每页大小", required = true) int pageSize,
                                    @ApiParam(value = "页码", required = true) int currentPage,
                                    @ApiParam(value = "起始时间", required = false) String startDate,
                                    @ApiParam(value = "结束时间", required = false) String endDate) {
        Page page;
        try {
            page = merchantInvoiceService.getRechargerForInvoice(startDate, endDate, merchantId, pageSize, currentPage);

        } catch (BusinessException b) {
            logger.info("merchant/invoice/queryRechargeForInvoice", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/queryRechargeForInvoice", e);
            return error("充值记录查询失败");
        }
        return AjaxResult.success(page);
    }



    @ApiOperation(value = "发票充值流水：申请记录-->查看-->开票总额")
    @Log(title = "发票管理-->发票充值流水", businessType = BusinessType.SELECT)
    @GetMapping("/queryRechargeAmount")
    @ResponseBody
    public AjaxResult queryRechargeAmount(@ApiParam(value = "发票id", required = true) Integer invoiceId) {
        BigDecimal amount;
        try {
            amount = merchantInvoiceService.getAllAmountByInvoiceId(invoiceId);

        } catch (BusinessException b) {
            logger.info("merchant/invoice/queryRechargeAmount", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/queryRechargeAmount", e);
            return error("发票信息查询失败");
        }
        return AjaxResult.success(amount);
    }

    @ApiOperation(value = "发票详情--重开校验", response = InvoiceRestartDetailDTO.class)
    @Log(title = "发票详情-->重开校验", businessType = BusinessType.SELECT)
    @GetMapping("/restartCheck")
    @ResponseBody
    public AjaxResult restartCheck(@ApiParam("待重开的发票ID") Integer invoiceId) {
        InvoiceRestartDetailDTO invoiceDetailDTO;
        try {
            invoiceDetailDTO = merchantInvoiceService.checkRestartInvoice(invoiceId);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/restartCheck", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/restartCheck", e);
            return error("重开校验失败");
        }
        return AjaxResult.success(invoiceDetailDTO);

    }


    @ApiOperation(value = "发票详情--错误重开")
    @Log(title = "发票详情-->错误重开", businessType = BusinessType.SELECT)
    @PostMapping("/restartApply")
    @ResponseBody
    public AjaxResult restartApply(@RequestBody InvoiceRestartInfoDTO invoiceRestartInfoDTO) {
        try {
            merchantInvoiceService.restartApplyInvoice(invoiceRestartInfoDTO);
        } catch (BusinessException b) {
            logger.info("manager/invoice/restartApply", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("manager/invoice/restartApply", e);
            return error("发票重开异常");
        }
        return AjaxResult.success();

    }

    @ApiOperation(value = "发票管理--开票申请--根据开票金额返回单价税额等",
            response = MerchantInvoiceAmountCalculationDTO.class)
    @Log(title = "发票详情-->开票申请", businessType = BusinessType.SELECT)
    @GetMapping("/getAmountDetail")
    @ResponseBody
    public AjaxResult getAmountDetail(BigDecimal amount) {
        MerchantInvoiceAmountCalculationDTO dto;
        try {
            dto = merchantInvoiceService.getAmountDetail(amount);
        } catch (BusinessException b) {
            logger.info("merchant/invoice/getAmountDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/invoice/getAmountDetail", e);
            return error("金额信息生成异常");
        }
        return AjaxResult.success(dto);

    }
}
