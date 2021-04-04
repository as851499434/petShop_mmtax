package com.mmtax.business.controller.merchant;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.dto.*;
import com.mmtax.business.service.ICheckAndChangeRateService;
import com.mmtax.business.service.ICustomerProtocolService;
import com.mmtax.business.service.IEsignFlowService;
import com.mmtax.business.service.IPaymentService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.enums.ResultEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.onlinebank.IOnlineBankUtils;
import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/16 19:56
 */
@Controller
@RequestMapping("/mmtax/payment")
public class OpenPaymentController extends BaseController {

    @Autowired
    IPaymentService paymentService;
    @Autowired
    private IEsignFlowService esignFlowService;
    @Autowired
    private ICustomerProtocolService customerProtocolService;

    @Autowired
    private ICheckAndChangeRateService iCheckAndChangeRateService;
    /**
     * 查询企业子账户信息
     * @param uid
     * @return
     */
    @GetMapping("/queryEnterprise")
    @ResponseBody
    public JSONObject queryEnterprise(@RequestParam String uid) {
        JSONObject resultDTO;
        resultDTO = IOnlineBankUtils.queryEnterprise(uid);
        return resultDTO;
    }

    /**
     * 费率变更请求
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/changeMerchantTax")
    @ResponseBody
    public ChangeTaxRateResultDTO changeMerchantRate(@Context HttpServletRequest request, @Context HttpServletResponse response){
        ChangeTaxRateResultDTO resultDTO = new ChangeTaxRateResultDTO();
        try {
            String code = iCheckAndChangeRateService.insertChangeRateInfo(request);
            resultDTO.setCode(code);
        } catch (BusinessException be) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(be.getMessage());
            logger.info("/mmtax/payment/changeMerchantTax:{}", be);
        }catch (Exception e) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(ResultEnum.FAIL.description);
            logger.error("/mmtax/payment/changeMerchantTax", e);
        }
        return resultDTO;
    }

    /**
     * 签约
     * @param dto
     * @return
     */
    @PostMapping("/notifySign")
    @ResponseBody
    public NotifySignDTO notifySign(HttpServletRequest request, @RequestBody NotifySignReqDTO dto) {
        NotifySignDTO resultDTO = new NotifySignDTO();
        try {
            resultDTO = esignFlowService.apiSign(request, dto);
        } catch (BusinessException be) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(be.getMessage());
            logger.info("/mmtax/payment/notifySign:{}", be);
        }catch (Exception e) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(ResultEnum.FAIL.description);
            logger.error("/mmtax/payment/notifySign", e);
        }
        return resultDTO;
    }

    /**
     * 查询签约状态
     * @param dto
     * @return
     */
    @PostMapping("/querySignStatus")
    @ResponseBody
    public ApiSignStatusDTO querySignStatus(@RequestBody NotifySignReqDTO dto) {
        ApiSignStatusDTO resultDTO = new ApiSignStatusDTO();
        try {
            resultDTO = customerProtocolService.queryNotifySignStatus(dto);
        } catch (BusinessException be) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(be.getMessage());
            logger.info("/mmtax/payment/querySignStatus:{}", be);
        }catch (Exception e) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(ResultEnum.FAIL.description);
            logger.error("/mmtax/payment/querySignStatus", e);
        }
        return resultDTO;
    }

    /**
     * 单笔代付
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/notify")
    @ResponseBody
    public PaymentResultDTO payment(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        PaymentResultDTO resultDTO = new PaymentResultDTO();
        try {
            resultDTO = paymentService.getResult(request);
        } catch (BusinessException be) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(be.getMessage());
            logger.info("/mmtax/payment/notify be:{}", be);
        }catch (Exception e) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(ResultEnum.FAIL.description);
            logger.error("/mmtax/payment/notify", e);
        }
        return resultDTO;
    }

    /**
     * 单笔代付支付宝
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/notifyAlipay")
    @ResponseBody
    public PaymentResultDTO notifyAlipay(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        PaymentResultDTO resultDTO = new PaymentResultDTO();
        try {
            resultDTO = paymentService.getResultAlipay(request);
        } catch (BusinessException be) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(be.getMessage());
            logger.info("/mmtax/payment/notifyAlipay be:{}", be);
        }catch (Exception e) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(ResultEnum.FAIL.description);
            logger.error("/mmtax/payment/notifyAlipay", e);
        }
        return resultDTO;
    }

    /**
     * 查询订单信息
     *
     * @param request
     * @return
     */
    @PostMapping("/getOrderInfo")
    @ResponseBody
    public NotifyTrxOrderDTO getOrderInfo(@Context HttpServletRequest request) {
        NotifyTrxOrderDTO trxOrderDTO = new NotifyTrxOrderDTO();

        try {
            trxOrderDTO = paymentService.getOrderInfo(request);
        } catch (Exception e) {
            logger.error("/mmtax/payment/getOrderInfo", e);
        }
        return trxOrderDTO;
    }

    /**
     * 查询余额
     *
     * @param request
     * @return
     */
    @PostMapping("/balance")
    @ResponseBody
    public PaymentBalanceDTO balance(@Context HttpServletRequest request) {
        PaymentBalanceDTO paymentBalanceDTO = new PaymentBalanceDTO();

        try {
            paymentBalanceDTO = paymentService.getBalance(request);
        } catch (Exception e) {
            logger.error("/mmtax/payment/balance", e);
        }
        return paymentBalanceDTO;
    }

    /**
     * 测试网商批量打款接口
     * @param
     * @return
     */
    @GetMapping("/testBatchPayment")
    @ResponseBody
    public void testBatchPayment(@RequestParam String merchantCode,@RequestParam String fileName
            ,@RequestParam String outerTradeNo) {
        try {
            IOnlineBankUtils.preOnlineBatchPayment(merchantCode,fileName,outerTradeNo);
        } catch (Exception e) {
            logger.error("/mmtax/payment/testBatchPayment", e);
        }
    }
}
