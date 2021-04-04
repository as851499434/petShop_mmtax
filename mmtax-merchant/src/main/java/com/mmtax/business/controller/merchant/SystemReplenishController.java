package com.mmtax.business.controller.merchant;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.mapper.MerchantInfoMapper;
import com.mmtax.business.service.IMerchantInfoService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.RegexUtil;
import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author liangfan
 * @create 2020-12-21 17:16
 * @desc
 **/
@Api(tags = "后台商户退款（取款）")
@Controller
@RequestMapping("/manager/systemReplenish")
public class SystemReplenishController extends BaseController {
    @Autowired
    private IOnlineBankService onlineBankService;
    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    /**
     * 商户余额提现到商户公户
     * @param amount 提现金额
     * @param taxSourceId 税源地id 本地
     * @param uid merchantCode
     * @param payeeBankCard 到账卡号
     * @param bankName 银行名称
     * @param payeeName 公户公司名称
     * @return
     */
    @ApiOperation(value = "商户余额提现到商户公户")
    @GetMapping("/withdrawToCardMerchant")
    @ResponseBody
    public AjaxResult withdrawToCardMerchant(@RequestParam(required = false) String amount,
                                             @RequestParam(required = false) String taxSourceId,
                                             @RequestParam(required = false) String uid,
                                             @RequestParam(required = false) String payeeBankCard,
                                             @RequestParam(required = false) String bankName,
                                             @RequestParam(required = false) String bankLineNo,
                                             @RequestParam(required = false) String bankBranch,
                                             @RequestParam(required = false) String payeeName) {
        OnlineCommonResultDTO data;
        try {
            logger.info("manager/systemReplenish/withdrawToCardMerchant start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            logger.info("manager/systemReplenish/withdrawToCardMerchant amount:{}", amount);
            TrxOrder trxOrder = new TrxOrder();
            trxOrder.setOrderSerialNum(ChanPayUtil.generateOutTradeNo());
            logger.info("manager/systemReplenish/withdrawToCardMerchant orderSerialNum:{}",
                    trxOrder.getOrderSerialNum());
            if (!RegexUtil.regexBigDecimal(amount)) {
                throw new BusinessException("请填写正确金额数");
            }
            Integer merchantId = merchantInfoMapper.selectMerchantIdByMerchantCode(uid);
            BigDecimal usableAmount = onlineBankService.getUsableAmount(merchantId);
            BigDecimal rAmount = new BigDecimal(amount);
            if (rAmount.compareTo(usableAmount) > 0) {
                throw new BusinessException("请填写正确金额数");
            }
            if (!RegexUtil.regexBankNo(payeeBankCard)) {
                throw new BusinessException("请输入正确卡号");
            }
            if (!RegexUtil.regexNameTwo(bankName)) {
                throw new BusinessException("请输入正确银行名称");
            }
            if (!RegexUtil.regexBankLineNo(bankLineNo)) {
                throw new BusinessException("请输入正确支行编号");
            }
            if (!RegexUtil.regexNameTwo(bankBranch)) {
                throw new BusinessException("请输入正确支行名称");
            }
            if (!RegexUtil.regexNameTwo(payeeName)) {
                throw new BusinessException("请输入正确收款人姓名");
            }
            trxOrder.setPayeeBankCard(payeeBankCard.trim());
            trxOrder.setPayeeName(payeeName.trim());
            trxOrder.setBankName(bankName.trim());
            trxOrder.setActualAmount(new BigDecimal(amount));
            trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID.getCode());
            trxOrder.setPaymentChannel("BANK");
            trxOrder.setUpdateTime(new Date());
            trxOrder.setCreateTime(new Date());
            data = onlineBankService.payToTheCardForMerchant(trxOrder, Integer.valueOf(taxSourceId), uid,
                    bankLineNo.trim(), bankBranch.trim());
            if(!"T".equals(data.getIs_success())) {
                logger.info("提现失败，{}",data.getError_message());
                throw new BusinessException("提现失败"+data.getError_message());
            }
            onlineBankService.payToTheCardForMerchantAddInfo(uid, amount, trxOrder, taxSourceId);
            logger.info("manager/systemReplenish/withdrawToCardMerchant end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (BusinessException b) {
            logger.info("manager/systemReplenish/withdrawToCardMerchant", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("manager/systemReplenish/withdrawToCardMerchant", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }



    /**
     * 手动改变退款失败订单
     * @return
     */
    @ApiOperation(value = "手动改变退款失败订单")
    @PostMapping("/changeOrderInfo")
    @ResponseBody
    public AjaxResult changeOrderInfo(String orderSerialNum) {
        logger.info("手动改变退款失败订单start,流水号为{}",orderSerialNum);
        try {
            onlineBankService.changeOrderInfo(orderSerialNum);
        } catch (Exception e) {
            logger.info("手动改变退款失败订单失败,流水号为{}",orderSerialNum);
            return AjaxResult.error();
        }
        logger.info("手动改变退款失败订单end,流水号为{}",orderSerialNum);
        return AjaxResult.success();
    }
}
