package com.mmtax.web.controller.business;

import com.mmtax.business.dto.*;
import com.mmtax.business.service.IMerchantTransactionService;
import com.mmtax.business.service.IPaymentService;
import com.mmtax.business.service.ITrxOrderService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.poi.ExcelUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/12 16:15
 */
@Api(tags = "交易管理")
@Controller
@RequestMapping("/manager/trxOrder")
public class ManagerTrxOrderController extends BaseController {

    private String prefix = "business/trxorder";

    @Autowired
    private ITrxOrderService trxOrderService;

    @Autowired
    private IMerchantTransactionService merchantTransactionService;

    @Autowired
    private IPaymentService paymentService;

    @RequiresPermissions("manager:trxOrder:view")
    @GetMapping()
    public String trxorder() {
        return prefix + "/trxorder";
    }

    @RequiresPermissions("manager:trxOrder:hangingTrxorder")
    @GetMapping("/hangingTrxorder")
    public String hangingTrxorder() {
        return prefix + "/hangingTrxorder";
    }

    @RequiresPermissions("manager:trxOrder:orderSheet")
    @GetMapping("/orderSheet")
    public String orderSheet() {
        return prefix + "/orderSheet";
    }

    @RequiresPermissions("manager:trxOrder:saleStatistic")
    @GetMapping("/saleStatistic")
    public String saleStatistic() {
        return prefix + "/saleStatistic";
    }

    @ApiOperation(value = "电子凭证", response = ElectronicVoucherDTO.class)
    @RequiresPermissions("manager:trxOrder:voucher")
    @GetMapping("/voucher")
    public String voucher(@ApiParam(value = "订单ID", required = true) int id, ModelMap mmap) {
        ElectronicVoucherDTO electronicVoucherDTO = merchantTransactionService.getVoucher(id);
        mmap.put("voucher", electronicVoucherDTO);
        return prefix + "/voucher";
    }


    /**
     * 跳转到退还记录列表
     * @return
     */
    @RequiresPermissions("manager:trxOrder:returnListTrxOrderView")
    @GetMapping("/returnListTrxOrderView")
    public String returnListTrxOrderView() {
        return prefix + "/returnListTrxOrderView";
    }
    /**
     * 跳转到补交记录列表
     * @return
     */
    @RequiresPermissions("manager:trxOrder:makeUpListTrxOrderView")
    @GetMapping("/makeUpListTrxOrderView")
    public String makeUpListTrxOrderView() {
        return prefix + "/makeUpListTrxOrderView";
    }



    /**
     * 查看服务费退还记录
     */
    @ApiOperation(value = "查看服务费退还记录",response = ManagerReturnListTrxOrderDTO.class)
    @RequiresPermissions("manager:trxOrder:returnListTrxOrder")
    @PostMapping("/returnListTrxOrder")
    @ResponseBody
    public TableDataInfo  returnListTrxOrder(String startDate,
                                                    String endDate,
                                                    String payeeName){
        startPage();
        List<ManagerReturnListTrxOrderDTO> returnListTrxOrder =
                trxOrderService.returnListTrxOrder(startDate, endDate, payeeName);
        return getDataTable(returnListTrxOrder);
    }
    /**
     * 查看服务费补交记录
     */
    @ApiOperation(value = "查看服务费补交记录",response = ManagerMakeUpListTrxOrderDTO.class)
    @RequiresPermissions("manager:trxOrder:makeUpListTrxOrder")
    @PostMapping("/makeUpListTrxOrder")
    @ResponseBody
    public TableDataInfo  makeUpListTrxOrder(String startDate,
                                             String endDate,
                                             String payeeName){
        startPage();
        List<ManagerMakeUpListTrxOrderDTO> makeUpListTrxOrder =
                trxOrderService.makeUpListTrxOrder(startDate, endDate, payeeName);
        return getDataTable(makeUpListTrxOrder);
    }


    /**
     * 导出服务费退还记录
     */
    @ApiOperation(value = "导出服务费退还记录")
    @RequiresPermissions("manager:companyInfo:returnListTrxOrderExport")
    @PostMapping("/returnListTrxOrderExport")
    @ResponseBody
    public AjaxResult returnListTrxOrderExport(String startDate,
                                                      String endDate,
                                                      String payeeName) {

        List<ManagerReturnListTrxOrderDTO> returnAndMakeListTrxOrder =
                trxOrderService.returnListTrxOrder(startDate, endDate, payeeName);

        ExcelUtil<ManagerReturnListTrxOrderDTO> util = new ExcelUtil<>(ManagerReturnListTrxOrderDTO.class);
        return util.exportExcel(returnAndMakeListTrxOrder, "服务费退还记录");
    }
    /**
     * 导出服务费退还记录
     */
    @ApiOperation(value = "导出服务费补交记录")
    @RequiresPermissions("manager:companyInfo:makeUpListTrxOrderExport")
    @PostMapping("/makeUpListTrxOrderExport")
    @ResponseBody
    public AjaxResult makeUpListTrxOrderExport(String startDate,
                                               String endDate,
                                               String payeeName) {

        List<ManagerMakeUpListTrxOrderDTO> makeUpListTrxOrder =
                trxOrderService.makeUpListTrxOrder(startDate, endDate, payeeName);

        ExcelUtil<ManagerMakeUpListTrxOrderDTO> util = new ExcelUtil<>(ManagerMakeUpListTrxOrderDTO.class);
        return util.exportExcel(makeUpListTrxOrder, "服务费补交记录");
    }

    @ApiOperation(value = "代付列表查询", response = ManagerTrxOrderDTO.class)
    @RequiresPermissions("manager:trxOrder:getListTrxOrder")
    @Log(title = "代付记录查询", businessType = BusinessType.SELECT)
    @PostMapping("/getListTrxOrder")
    @ResponseBody
    public TableDataInfo getListTrxOrder(ManagerTrxOrderDTO managerTrxOrderDTO) {
        startPage();
        List<ManagerTrxOrderDTO> list = trxOrderService.getListTrxOrder(managerTrxOrderDTO);
        return getDataTable(list);
    }

    @GetMapping("/detail")
    @RequiresPermissions("manager:trxOrder:detail")
    public String detail() {
        return prefix + "/trxOrderDetail";
    }

    @ApiOperation(value = "挂起订单再次支付")
    @PostMapping("payment")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true, paramType = "int")})
    public AjaxResult payment(@ApiIgnore ManagerTrxOrderDTO managerTrxOrderDTO) {
        try {
            paymentService.hangingOrderPaid(managerTrxOrderDTO);
        } catch (BusinessException b) {
            logger.info("/manager/trxOrder/payment", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/trxOrder/payment", e);
            return error(e.getMessage());
        }
        return success();
    }


    @ApiOperation(value = "调单列表")
    @PostMapping("/orderSheetList")
    @ResponseBody
    public TableDataInfo getOrderSheetList(ManagerOrderSheetDTO dto) {
        startPage();
        List<ManagerOrderSheetDTO> list = trxOrderService.getListOrderSheet(dto);
        return getDataTable(list);
    }

    @ApiOperation(value = "交易统计", response = ManagerSaleStatisticDTO.class)
    @PostMapping("/getSaleStatistic")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantName", value = "商户名称", required = true, paramType = "int"),
            @ApiImplicitParam(name = "merchantCode", value = "商户编号", required = true, paramType = "int"),
            @ApiImplicitParam(name = "startDate", value = "起始时间", required = true, paramType = "int"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = true, paramType = "int")})
    public TableDataInfo getSaleStatistic(@ApiIgnore ManagerSaleStatisticDTO dto) {
        startPage();
        List<ManagerSaleStatisticDTO> list = trxOrderService.getSaleStatistic(dto);
        return getDataTable(list);
    }

    /**
     * 跳转到推广列表页面
     * @return
     */
    @RequiresPermissions("manager:trxOrder:customerPromotion")
    @GetMapping("/customerPromotion")
    public String customerPromotion() {
        return prefix + "/customerPromotion";
    }

    /**
     * 推广列表
     */
    @ApiOperation(value = "推广列表",response = CustomerPromotionDTO.class)
    @RequiresPermissions("manager:trxOrder:getPromotionList")
    @PostMapping("/getPromotionList")
    @ResponseBody
    public TableDataInfo  getPromotionList(QueryCustomerPromotionDTO queryCustomerPromotionDTO){
        startPage();
        List<CustomerPromotionDTO> promotionList = trxOrderService.getPromotionList(queryCustomerPromotionDTO);
        return getDataTable(promotionList);
    }

    /**
     * 导出推广列表
     */
    @ApiOperation(value = "导出推广列表")
    @RequiresPermissions("manager:companyInfo:customerPromotionExport")
    @PostMapping("/customerPromotionExport")
    @ResponseBody
    public AjaxResult customerPromotionExport(QueryCustomerPromotionDTO queryCustomerPromotionDTO) {
        List<CustomerPromotionDTO> promotionList = trxOrderService.getPromotionList(queryCustomerPromotionDTO);
        ExcelUtil<CustomerPromotionDTO> util = new ExcelUtil<>(CustomerPromotionDTO.class);
        return util.exportExcel(promotionList, "推广列表");
    }

    @RequiresPermissions("manager:trxOrder:reimburseView")
    @GetMapping("/reimburseView")
    public String reimburseView() {
        return prefix + "/orderReimburse";
    }

    /**
     * 服务费补偿记录
     */
    @ApiOperation(value = "服务费补偿记录",response = ChargeReimburseInfoDTO.class)
    @RequiresPermissions("manager:trxOrder:rechargeReimburse")
    @PostMapping("/rechargeReimburse")
    @ResponseBody
    public TableDataInfo getRechargeReimburseList(QueryChargeReimburseInfoDTO queryCustomerPromotionDTO){
        startPage();
        List<ChargeReimburseInfoDTO> rechargeReimburseList = trxOrderService.getRechargeReimburseList(queryCustomerPromotionDTO);
        return getDataTable(rechargeReimburseList);
    }

}
