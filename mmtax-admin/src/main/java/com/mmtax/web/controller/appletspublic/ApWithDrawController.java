package com.mmtax.web.controller.appletspublic;

import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.CustomerProtocol;
import com.mmtax.business.dto.CusWithdrawDTO;
import com.mmtax.business.dto.VerifyCustomerDTO;
import com.mmtax.business.dto.WithdrawPassDTO;
import com.mmtax.business.service.ICustomerProtocolService;
import com.mmtax.business.service.ICustomerWithdrawService;
import com.mmtax.business.service.IEsignFlowService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.enums.PaymentEnum;
import com.mmtax.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "小程序和公众号员工提现")
@Controller
@RequestMapping("/appletspublic/customerWithDraw")
@Slf4j
public class ApWithDrawController extends BaseController {

    @Autowired
    IEsignFlowService esignFlowService;
    @Autowired
    ICustomerWithdrawService customerWithdrawService;
    @Autowired
    ICustomerProtocolService customerProtocolService;


    /**
     * 判断提现开关状态
     * @return
     */
    @ApiOperation(value = "判断提现开关状态")
    @GetMapping("/withDrawIsOpen")
    @ResponseBody
    public AjaxResult withDrawIsOpen(@RequestParam Integer customerId){
        log.info("/appletspublic/customerWithDraw/withDrawIsOpen，入参：customerId={}", customerId);
        Boolean isOpen;
        try {
            isOpen = customerWithdrawService.withDrawIsOpen(customerId);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerWithDraw/withDrawIsOpen", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerWithDraw/withDrawIsOpen", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(isOpen);
    }

    /**
     * 提现到银行卡
     * @return
     */
    @ApiOperation(value = "提现到银行卡")
    @PostMapping("/withDrawBankCard")
    @ResponseBody
    public AjaxResult withDrawBankCard(@RequestBody CusWithdrawDTO dto){
        log.info("/appletspublic/customerWithDraw/withDrawBankCard，入参：{}", JSON.toJSONString(dto));
        try {
            customerWithdrawService.withDraw(dto, PaymentEnum.BANK);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerWithDraw/withDrawBankCard", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerWithDraw/withDrawBankCard", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 提现到支付宝
     * @return
     */
    @ApiOperation(value = "提现到支付宝")
    @PostMapping("/withDrawAlipay")
    @ResponseBody
    public AjaxResult withDrawAlipay(@RequestBody CusWithdrawDTO dto){
        log.info("/appletspublic/customerWithDraw/withDrawAlipay，入参：{}", JSON.toJSONString(dto));
        try {
            customerWithdrawService.withDraw(dto,PaymentEnum.ALIPAY);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerWithDraw/withDrawAlipay", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerWithDraw/withDrawAlipay", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 判断是否设定过支付密码
     * @return
     */
    @ApiOperation(value = "判断是否设定过支付密码")
    @PostMapping("/judgeHaveSetWithdrawPass")
    @ResponseBody
    public AjaxResult judgeHaveSetWithdrawPass(@RequestBody WithdrawPassDTO dto){
        log.info("/appletspublic/customerWithDraw/judgeHaveSetWithdrawPass，入参：{}", JSON.toJSONString(dto));
        Boolean data;
        try {
            data = customerWithdrawService.judgeHaveSetWithdrawPass(dto.getCustomerId());
        } catch (BusinessException b) {
            log.info("/appletspublic/customerWithDraw/judgeHaveSetWithdrawPass", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerWithDraw/judgeHaveSetWithdrawPass", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

    /**
     * 设定支付密码
     * @return
     */
    @ApiOperation(value = "设定支付密码")
    @PostMapping("/setWithdrawPass")
    @ResponseBody
    public AjaxResult setWithdrawPass(@RequestBody WithdrawPassDTO dto){
        log.info("/appletspublic/customerWithDraw/setWithdrawPass，入参：{}", JSON.toJSONString(dto));
        try {
            customerWithdrawService.setWithdrawPass(dto);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerWithDraw/setWithdrawPass", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerWithDraw/setWithdrawPass", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 修改支付密码
     * @return
     */
    @ApiOperation(value = "修改支付密码")
    @PostMapping("/changeWithdrawPass")
    @ResponseBody
    public AjaxResult changeWithdrawPass(@RequestBody WithdrawPassDTO dto){
        log.info("/appletspublic/customerWithDraw/changeWithdrawPass，入参：{}", JSON.toJSONString(dto));
        try {
            customerWithdrawService.changeWithdrawPass(dto);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerWithDraw/changeWithdrawPass", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerWithDraw/changeWithdrawPass", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 忘记密码发短信
     * @return
     */
    @ApiOperation(value = "忘记密码发短信")
    @GetMapping(value = "/sendSmsCode")
    @ResponseBody
    public AjaxResult sendSmsCode(@RequestParam String mobile){
        logger.info("/appletspublic/customerWithDraw/sendSmsCode，入参：mobile={}", mobile);
        try{
            customerWithdrawService.sendSmsCode(mobile);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerWithDraw/sendSmsCode",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerWithDraw/sendSmsCode",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 忘记支付密码
     * @return
     */
    @ApiOperation(value = "忘记支付密码")
    @PostMapping("/resetWithdrawPass")
    @ResponseBody
    public AjaxResult resetWithdrawPass(@RequestBody WithdrawPassDTO dto){
        log.info("/appletspublic/customerWithDraw/resetWithdrawPass，入参：{}", JSON.toJSONString(dto));
        try {
            customerWithdrawService.resetWithdrawPass(dto);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerWithDraw/resetWithdrawPass", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerWithDraw/resetWithdrawPass", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 判断是否已签约完成
     * @return
     */
    @ApiOperation(value = "判断是否已签约完成")
    @GetMapping("/haveSign")
    @ResponseBody
    public AjaxResult haveSign(@RequestParam Integer customerId){
        Boolean message;
        try {
            message = customerProtocolService.haveSign(customerId);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerWithDraw/haveSign", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerWithDraw/haveSign", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(message);
    }

    /**
     * 个人签约
     * @return
     */
    @ApiOperation(value = "个人签约")
    @GetMapping("/customerSign")
    @ResponseBody
    public AjaxResult customerSign(@RequestParam Integer customerId){
        String message;
        try {
            message = esignFlowService.customerSign(customerId);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerWithDraw/customerSign", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerWithDraw/customerSign", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(message);
    }
}
