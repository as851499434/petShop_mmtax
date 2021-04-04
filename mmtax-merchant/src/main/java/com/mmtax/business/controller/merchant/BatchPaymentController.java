package com.mmtax.business.controller.merchant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.BatchPaymentRecord;
import com.mmtax.business.domain.PayRequestData;
import com.mmtax.business.dto.*;
import com.mmtax.business.service.*;
import com.mmtax.business.tianjindto.TianJinAccountDTO;
import com.mmtax.business.tianjindto.TianJinAccountInfoDTO;
import com.mmtax.business.tianjindto.TianJinServerInfoDTO;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/9 17:13
 */
@Api(tags = "商户代付请求")
@Controller
@RequestMapping("/merchant/payment")
public class BatchPaymentController extends BaseController {

    @Autowired
    IPaymentService paymentService;
    @Autowired
    private IEsignFlowService esignFlowService;
    @Autowired
    private ITrxOrderService trxOrderService;
    @Autowired
    IPayRequestDataService payRequestDataService;


    @ApiOperation(value = "批量签约,上传文件")
    @PostMapping("uploadFileSign")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file, SignCheckPassDTO dto) {
        Map result;
        try {
            result = esignFlowService.batchUploadFile(file,dto);
            Object batchErrorResultDTOS = result.get("batchErrorResultDTOS");
            if(batchErrorResultDTOS != null){
                return AjaxResult.error("出错了",result);
            }
        } catch (BusinessException b) {
            logger.info("/merchant/payment/uploadFileSign:{}", b);
            return AjaxResult.error(JSON.toJSONString(b.getMessage()));
        } catch (Exception e) {
            logger.info("/merchant/payment/uploadFileSign:{}", e);
            logger.error("/merchant/payment/uploadFileSign:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    @ApiOperation(value = "获取批量签约错误数据")
    @GetMapping("getSignErrorData")
    @ResponseBody
    public AjaxResult getSignErrorData(@ApiParam("当前页")Integer currentPage,
                                            @ApiParam("每页大小")Integer pageSize,
                                            @ApiParam("key")String key) {
        Map errorData;
        try {
            errorData = esignFlowService.getSignErrorData(currentPage, pageSize, key);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/getSignErrorData", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/getSignErrorData", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(errorData);
    }

    @ApiOperation(value = "修改校验记录")
    @PostMapping("updatePayData")
    @ResponseBody
    public AjaxResult updatePayData(@RequestBody PayDataDTO dto) {
        try {
            payRequestDataService.updatePayData(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/updatePayData:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/updatePayData:{}", e);
            logger.error("/merchant/payment/updatePayData:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "查询校验信息")
    @GetMapping("queryCheckInfo")
    @ResponseBody
    public AjaxResult queryCheckInfo(@ApiParam("批次号") String batchNo,
                                     @ApiParam("商户ID") Integer merchantId) {
        QueryCheckInfoResultDTO queryCheckInfoResultDTO;
        try {
            queryCheckInfoResultDTO = payRequestDataService.queryCheckInfo(batchNo,merchantId);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/queryCheckInfo:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/queryCheckInfo:{}", e);
            logger.error("/merchant/payment/queryCheckInfo:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(queryCheckInfoResultDTO);
    }

    @ApiOperation(value = "查询是否校验完")
    @GetMapping("queryCheckOver")
    @ResponseBody
    public AjaxResult queryCheckOver(@ApiParam("批次号") String batchNo,
                                     @ApiParam("商户ID") Integer merchantId) {
        Boolean result;
        try {
            result = payRequestDataService.queryCheckOver(batchNo,merchantId);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/queryCheckOver:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/queryCheckOver:{}", e);
            logger.error("/merchant/payment/queryCheckOver:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    @ApiOperation(value = "删除校验记录")
    @PostMapping("deletePayData")
    @ResponseBody
    public AjaxResult deletePayData(@RequestBody PayDataDTO dto) {
        try {
            payRequestDataService.deletePayData(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/deletePayData:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/deletePayData:{}", e);
            logger.error("/merchant/payment/deletePayData:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "获取打款数据列表")
    @GetMapping("listPayData")
    @ResponseBody
    public AjaxResult listPayData(@RequestParam Integer merchantId,@RequestParam String batchNo,
                                  @RequestParam(required = false,defaultValue = "10") Integer pagSize,
                                  @RequestParam(required = false,defaultValue = "1") Integer currentPage) {
        Page<PayRequestData> results;
        try {
            results = payRequestDataService.listPayData(merchantId, batchNo,pagSize,currentPage);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/listPayData:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/listPayData:{}", e);
            logger.error("/merchant/payment/listPayData:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(results);
    }

    @ApiOperation(value = "批量代付,上传文件")
    @PostMapping("uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file, CheckPasswordDTO checkPasswordDTO) {
        Map map;
        try {
            map = paymentService.batchPayment(file, checkPasswordDTO);
            Object batchErrorResultDTOS = map.get("batchErrorResultDTOS");
            if(batchErrorResultDTOS != null){
                return AjaxResult.error("出错了",map);
            }
        } catch (BusinessException b) {
            logger.info("/merchant/payment/uploadFile:{}", b);
            return AjaxResult.error(JSON.toJSONString(b.getMessage()));
        } catch (Exception e) {
            logger.info("/merchant/payment/uploadFile:{}", e);
            logger.error("/merchant/payment/uploadFile:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(map);
    }

    @ApiOperation(value = "发去生产者校验")
    @PostMapping("sendToProuce")
    @ResponseBody
    public AjaxResult sendToProuce(@RequestBody SendToCheckDTO dto) {
        try {
            paymentService.sendToProuce(dto.getMerchantId(), dto.getBatchNo());
        } catch (BusinessException b) {
            logger.info("/merchant/payment/sendToProuce:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/sendToProuce:{}", e);
            logger.error("/merchant/payment/sendToProuce:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "校验")
    @PostMapping("sendToCheck")
    @ResponseBody
    public AjaxResult sendToCheck(@RequestBody SendToCheckDTO dto) {
        try {
            payRequestDataService.sendToCheck(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/sendToCheck:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/sendToCheck:{}", e);
            logger.error("/merchant/payment/sendToCheck:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }


    @ApiOperation(value = "推广列表->批量代付,上传文件")
    @PostMapping("uploadFilePromotion")
    @ResponseBody
    public AjaxResult uploadFilePromotion(MultipartFile file, CheckPasswordDTO checkPasswordDTO) {
        Map map;
        try {
            map = paymentService.uploadFilePromotion(file, checkPasswordDTO);
            Object batchErrorResultDTOS = map.get("batchErrorResultDTOS");
            if(batchErrorResultDTOS != null){
                return AjaxResult.error("出错了",map);
            }
        } catch (BusinessException b) {
            logger.info("/merchant/payment/uploadFile:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/uploadFile:{}", e);
            logger.error("/merchant/payment/uploadFile:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(map);
    }

    @ApiOperation(value = "推广列表")
    @GetMapping("/listPromotions")
    @ResponseBody
    public AjaxResult listPromotions(QueryCustomerPromotionDTO queryCustomerPromotionDTO){
        Page page;
        try {
            page = trxOrderService.listPromotions(queryCustomerPromotionDTO);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/listPromotions:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/listPromotions:{}", e);
            logger.error("/merchant/payment/listPromotions:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }


    @ApiOperation(value = "推广列表列表导出")
    @GetMapping("/exportPromotion")
    @ResponseBody
    public AjaxResult exportPromotion(QueryCustomerPromotionDTO queryCustomerPromotionDTO) {
        List<CustomerPromotionDTO> list = null;
        ExcelUtil<CustomerPromotionDTO> util = new ExcelUtil<>(CustomerPromotionDTO.class);
        try {
            list = trxOrderService.getPromotionList(queryCustomerPromotionDTO);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/exportPromotion:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/payment/exportPromotion:{}", e);
            return error(e.getMessage());
        }
        return util.exportExcel(list, "推广列表");
    }

    @ApiOperation(value = "充值计算器")
    @PostMapping("rechargeCalculate")
    @ResponseBody
    public AjaxResult rechargeCalculate(@RequestBody BatchOrderDTO dto) {
        Map map;
        try {
            map = paymentService.rechargeCalculate(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/rechargeCalculate:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/rechargeCalculate:{}", e);
            logger.error("/merchant/payment/rechargeCalculate:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(map);
    }

    @ApiOperation(value = "判断批次是否锁定已打款")
    @PostMapping("judgePaymentCache")
    @ResponseBody
    public AjaxResult judgePaymentCache(@RequestBody BatchOrderDTO dto) {
        Map havePaymentCache;
        try {
            havePaymentCache = paymentService.judgePaymentCache(dto.getMerchantId());
        } catch (BusinessException b) {
            logger.info("/merchant/payment/judgePaymentCache:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/judgePaymentCache:{}", e);
            logger.error("/merchant/payment/judgePaymentCache:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(havePaymentCache);
    }
    @ApiOperation(value = "打款查看详情列表")
    @GetMapping("watchPaymentDetail")
    @ResponseBody
    public AjaxResult watchPaymentDetail(@ApiParam(value = "商户id", required = true) int merchantId,
                                         @ApiParam(value = "批次号", required = true) String batchNo,
                                         @ApiParam(value = "每页大小", required = true) int pageSize,
                                         @ApiParam(value = "页码", required = true) int currentPage) {
        Page page;
        try {
            page = paymentService.listPaymentDetail(merchantId,batchNo,pageSize,currentPage);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/watchPaymentDetail:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/watchPaymentDetail:{}", e);
            logger.error("/merchant/payment/watchPaymentDetail:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "打款查看详情总数据")
    @GetMapping("watchPaymentData")
    @ResponseBody
    public AjaxResult watchPaymentData(@RequestParam int merchantId,@RequestParam String batchNo) {
        Map map;
        try {
            map = paymentService.summaryPaymentData(merchantId,batchNo);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/watchPaymentData:{}", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/watchPaymentData:{}", e);
            logger.error("/merchant/payment/watchPaymentData:{}", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(map);
    }

    @ApiOperation(value = "锁定批次", response = BatchPaymentRecord.class)
    @GetMapping("/getBatchRecord")
    @ResponseBody
    public AjaxResult getBatchRecord(@ApiParam(required = true, value = "商户id") Integer merchantId,
                                     @ApiParam(required = true, value = "商户id") String batchNo) {
        BatchPaymentRecord batchPaymentRecord;
        try {
            batchPaymentRecord = paymentService.getRecord(merchantId,batchNo);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/getBatchRecord", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/getBatchRecord", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(batchPaymentRecord);
    }

    @ApiOperation(value = "取消订单")
    @PostMapping("cancelBatchOrder")
    @ResponseBody
    public AjaxResult cancelBatchOrder(@RequestBody BatchOrderDTO dto) {
        try {
            paymentService.cancelOrder(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/cancelBatchOrder", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/cancelBatchOrder", e);
            return error(e.getMessage());
        }
        return success();
    }


    @ApiOperation(value = "获取校验出错的数据")
    @GetMapping("getErrorData")
    @ResponseBody
    public AjaxResult getErrorData(@ApiParam("当前页")Integer currentPage,
                                   @ApiParam("每页大小")Integer pageSize,
                                   @ApiParam("key")String key) {
        Map errorData;
        try {
             errorData = paymentService.getErrorData(currentPage, pageSize, key);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/getErrorData", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/getErrorData", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(errorData);
    }

    @ApiOperation(value = "获取推广列表校验出错的数据")
    @GetMapping("getPromotionErrorData")
    @ResponseBody
    public AjaxResult getPromotionErrorData(@ApiParam("当前页")Integer currentPage,
                                   @ApiParam("每页大小")Integer pageSize,
                                   @ApiParam("key")String key) {
        Map errorData;
        try {
            errorData = paymentService.getPromotionErrorData(currentPage, pageSize, key);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/getPromotionErrorData", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/getPromotionErrorData", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(errorData);
    }

    @ApiOperation(value = "批量代付")
    @PostMapping("batchPayment")
    @ResponseBody
    public AjaxResult batchPayment(@RequestBody BatchOrderDTO dto) {
        try {
            paymentService.batchPayment(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/batchPayment", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/batchPayment", e);
            return error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "执行打款(发送到生产者)")
    @PostMapping("paymentExecute")
    @ResponseBody
    public AjaxResult paymentExecute(@RequestBody BatchOrderDTO dto) {
        try {
            paymentService.paymentExecute(dto.getMerchantId(),dto.getBatchNo());
        } catch (BusinessException b) {
            logger.info("/merchant/payment/paymentExecute", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/payment/paymentExecute", e);
            return error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "测试", response = BatchPaymentRecord.class)
    @PostMapping("/paymentTest")
    @ResponseBody
    public AjaxResult paymentTest(@Context HttpServletRequest request) {
        try {
            String data = IOUtils.toString(request.getInputStream(), "UTF-8");
            JSONObject json = JSON.parseObject(data);
            logger.info("请求参数data:{}", json);
        } catch (Exception e) {
            logger.error("/merchant/payment/paymentTest",e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "批量代付记录汇总数据", response = MerchantBatchPaymentAmountDetailDTO.class)
    @GetMapping("getDetail")
    @ResponseBody
    public AjaxResult getDetail(Integer merchantId,String batchNo) {
        MerchantBatchPaymentAmountDetailDTO detailDTO;
        try {
            detailDTO = paymentService.getDetail(merchantId,batchNo);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/getDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/payment/getDetail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(detailDTO);
    }

    @ApiOperation(value = "单个代付")
    @PostMapping("/singlePayment")
    @ResponseBody
    public AjaxResult singlePayment(@RequestBody PaymentInfoDTO paymentInfoDTO) {
        try {
            paymentService.singlePayment(paymentInfoDTO, null, true, null);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/batchPayment", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/payment/batchPayment", e);
            return error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "校验密码")
    @PostMapping("/checkPassword")
    @ResponseBody
    public AjaxResult checkPassword(@RequestBody(required = false) CheckPasswordDTO checkPasswordDTO) {
        try {
            paymentService.checkPassword(checkPasswordDTO);
        } catch (BusinessException b) {
            logger.error("/merchant/payment/checkPassword", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/payment/checkPassword", e);
            return error(e.getMessage());
        }
        return success();
    }
    @ApiOperation(value = "获取充值账户信息",response = RechargeAccountInfoDTO.class)
    @GetMapping("/getAccountInfo")
    @ResponseBody
    public AjaxResult getAccountInfo(@ApiParam(value = "商户id", required = true) Integer merchantId) {
        RechargeAccountInfoDTO data;
        try {
            data = paymentService.getAccountInfo(merchantId);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/getAccountInfo", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/payment/getAccountInfo", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "获取账户余额",response = TianJinAccountDTO.class)
    @GetMapping("/getAccountDetail")
    @ResponseBody
    public AjaxResult getAccountDetail(@ApiParam(value = "账户id", required = true) String  accountUuid,
                                       @ApiParam(value = "商户id", required = true) Integer merchantId) {
        TianJinAccountDTO detail = new TianJinAccountDTO();
        try {
            detail  =  paymentService.getAccountDetail(accountUuid,merchantId);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/getAccountDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/payment/getAccountDetail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(detail);
    }
    @ApiOperation(value = "获取供应商信息",response = TianJinServerInfoDTO.class)
    @GetMapping("/getServerInfo")
    @ResponseBody
    public AjaxResult getServerInfo(@ApiParam(value = "商户id", required = true) Integer merchantId) {
        List<TianJinServerInfoDTO> list = new ArrayList<>();
        try {
            list =  paymentService.getServerInfo(merchantId);
        } catch (BusinessException b) {
            logger.info("/merchant/payment/getAccountDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/payment/getAccountDetail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(list);
    }


}
