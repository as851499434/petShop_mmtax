package com.mmtax.business.controller.merchant;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.service.*;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.constant.PaymentConstants;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.onlinebank.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 补充处理控制器
 * @author Ljd
 * @date 2020/6/19
 */
@Api(tags = "补充处理控制器")
@Controller
@RequestMapping("merchant/replenish")
public class ReplenishController extends BaseController {

    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private IMerchantInfoService merchantInfoService;
    @Autowired
    ITradeRefundOrderService tradeRefundOrderService;
    @Autowired
    private ICusLinkMerInfoService cusLinkMerInfoService;

    @ApiOperation(value = "回退转账成功但订单一直失败的记录(已无效，可保留可删除)")
    @GetMapping("/returnTranferAmount")
    @ResponseBody
    public AjaxResult returnTranferAmount(@RequestParam(value = "orderSerialNum",required = false) String orderSerialNum) {
        logger.info("回退转账成功但订单一直失败的记录，入参：订单编号={}",orderSerialNum);
        try {
//            transferOrderService.returnTranferAmount(orderSerialNum);
        } catch (BusinessException b) {
            logger.info("merchant/replenish/returnTranferAmount", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/replenish/returnTranferAmount", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "回退成功的转账记录(后台的退款按钮，别删)")
    @GetMapping("/refundTranferOrder")
    @ResponseBody
    public AjaxResult refundTranferOrder(@RequestParam(value = "orderSerialNum",required = false) String orderSerialNum) {
        logger.info("回退转账记录，入参：订单编号={}",orderSerialNum);
        String refundSerialNum;
        try {
            refundSerialNum = tradeRefundOrderService.refundTranferOrder(orderSerialNum);
        } catch (BusinessException b) {
            logger.info("merchant/replenish/refundTranferOrder", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/replenish/refundTranferOrder", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(refundSerialNum);
    }

    @ApiOperation(value = "重新回退的失败的退款记录")
    @GetMapping("/refundFail")
    @ResponseBody
    public AjaxResult refundFail(@RequestParam(value = "refundSerialNum") String refundSerialNum) {
        logger.info("退款记录，入参：退款编号={}",refundSerialNum);
        String resultRefundSerialNum;
        try {
            resultRefundSerialNum = tradeRefundOrderService.refundFail(refundSerialNum);
        } catch (BusinessException b) {
            logger.info("merchant/replenish/refundFail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("merchant/replenish/refundFail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(resultRefundSerialNum);
    }

    /**
     * 商户切换税源地
     * @param merchantId 商户id
     * @param taxSourceId 新的税源地id
     * @return
     */
    @GetMapping("/changeTaxSource")
    @ResponseBody
    public AjaxResult changeTaxSource(@RequestParam("merchantId") Integer merchantId,
                                   @RequestParam("taxSourceId") Integer taxSourceId) {
        Integer newMerchantId;
        try {
            logger.info("切换商户税源地 merchantId={},taxSourceId={}", merchantId,taxSourceId);
            newMerchantId = merchantInfoService.changeTaxSource(merchantId,taxSourceId);
        } catch (Exception b) {
            logger.info("merchant/replenish/changeTaxSource", b);
            return error(b.getMessage());
        }
        return AjaxResult.success(newMerchantId);
    }

    /**
     * 查询变动明细
     * @param merchantId
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    @GetMapping("/accountQuery")
    @ResponseBody
    public AjaxResult accountQuery(@RequestParam("merchantId") Integer merchantId,
                                   @RequestParam("startTime") String startTime,
                                   @RequestParam("endTime") String endTime,
                                   @RequestParam("page") String page) {
        AccountQueryResultDTO data;
        try {
            logger.info("merchant/replenish/accountQuery start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            logger.info("merchant/replenish/accountQuery merchantId:{}", merchantId);
            data = onlineBankService.accountQuery(merchantId,startTime,endTime,page);
            logger.info("merchant/replenish/accountQuery end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (Exception b) {
            logger.info("merchant/replenish/accountQuery", b);
            return error(b.getMessage());
        }
        return AjaxResult.success(data);
    }

    @GetMapping("/infoQuery")
    @ResponseBody
    public AjaxResult infoQuery(@RequestParam("orderSerialNum") String orderSerialNum,
                                @RequestParam(value = "merchantId") Integer merchantId) {
        InfoQueryResultDTO data;
        try {
            logger.info("merchant/replenish/infoQuery start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            logger.info("merchant/replenish/infoQuery orderSerialNum:{}", orderSerialNum);
            data = onlineBankService.infoQuery(merchantId,orderSerialNum);
            logger.info("merchant/replenish/infoQuery end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (Exception b) {
            logger.info("merchant/replenish/infoQuery", b);
            return error(b.getMessage());
        }
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "查询网商子账户余额", response = AccountBalanceResultDTO.class)
    @GetMapping("/querySubAccountBalance")
    @ResponseBody
    public AjaxResult querySubAccountBalance(@RequestParam(required = false) Integer merchantId,
                                             @RequestParam(required = false) Integer customerId,
                                             @RequestParam(required = false) Integer taxSourceId,
                                             @RequestParam(required = false) String accountType) {
        AccountBalanceResultDTO data;
        try {
            logger.info("当前服务器ip:{}",System.getenv("HOST_Q"));
            logger.info("merchant/replenish/querySubAccountBalance start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            logger.info("merchant/replenish/querySubAccountBalance merchantId:{}", merchantId);
            data = onlineBankService.accountBalance(merchantId,customerId,taxSourceId,accountType);
            logger.info("merchant/replenish/querySubAccountBalance end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (BusinessException b) {
            logger.info("merchant/replenish/querySubAccountBalance", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/replenish/querySubAccountBalance", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "平台基本户转账至商户子账户")
    @GetMapping("/transferAmountFromPbcsicToBasic")
    @ResponseBody
    public AjaxResult transferAmountFromPbcsicToMer(@RequestParam(required = false) Integer merchantId,
                                                    @RequestParam(required = false) Integer taxSourceId,
                                                    @RequestParam(required = false) BigDecimal money) {
        OnlineCommonResultDTO data;
        try {
            logger.info("merchant/replenish/transferAmountFromPbcsicToBasic start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            data = onlineBankService.transferAmountFromPbcsicToMer(taxSourceId,merchantId,money);
            logger.info("merchant/replenish/transferAmountFromPbcsicToBasic end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (BusinessException b) {
            logger.info("merchant/replenish/transferAmountFromPbcsicToBasic", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/replenish/transferAmountFromPbcsicToBasic", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "子账户余额核对网商", response = AccountCheckOnlineDTO.class)
    @GetMapping("/accountCheckOnline")
    @ResponseBody
    public AjaxResult accountCheckOnline() {
        AccountCheckOnlineDTO data;
        try {
            logger.info("merchant/replenish/accountCheckOnline start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            logger.info("merchant/replenish/accountCheckOnline merchantId:{}", "null");
            data = onlineBankService.accountCheckOnline(null);
            logger.info("merchant/replenish/accountCheckOnline end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (BusinessException b) {
            logger.info("merchant/replenish/accountCheckOnline", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/replenish/accountCheckOnline", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "税源地手续费提现到卡南璟天")
    @GetMapping("/withdrawToCardNanJingTian")
    @ResponseBody
    public AjaxResult withdrawToCardNanJingTian(@RequestParam(required = false) String amount) {
        OnlineCommonResultDTO data;
        try {
            logger.info("merchant/replenish/withdrawToCardNanJingTian start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            logger.info("merchant/replenish/withdrawToCardNanJingTian amount:{}", amount);
            TrxOrder trxOrder = new TrxOrder();
            trxOrder.setOrderSerialNum(ChanPayUtil.generateOutTradeNo());
            logger.info("merchant/replenish/withdrawToCardNanJingTian orderSerialNum:{}",
                    trxOrder.getOrderSerialNum());
            trxOrder.setPayeeBankCard("5006145100014");
            trxOrder.setPayeeName("河南南璟天电子科技有限公司");
            trxOrder.setBankName("焦作中旅银行股份有限公司营业部");
            trxOrder.setActualAmount(new BigDecimal(amount));
            Integer taxSourceId = 4;
            String uid = "99892667";
            data = onlineBankService.payToTheCardForMerchant(trxOrder, taxSourceId, uid, "", "");
            logger.info("merchant/replenish/withdrawToCardNanJingTian end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (BusinessException b) {
            logger.info("merchant/replenish/withdrawToCardNanJingTian", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/replenish/withdrawToCardNanJingTian", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

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
            logger.info("merchant/replenish/withdrawToCardMerchant start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            logger.info("merchant/replenish/withdrawToCardMerchant amount:{}", amount);
            TrxOrder trxOrder = new TrxOrder();
            trxOrder.setOrderSerialNum(ChanPayUtil.generateOutTradeNo());
            logger.info("merchant/replenish/withdrawToCardMerchant orderSerialNum:{}",
                    trxOrder.getOrderSerialNum());
            trxOrder.setPayeeBankCard(payeeBankCard.trim());
            trxOrder.setPayeeName(payeeName.trim());
            trxOrder.setBankName(bankName.trim());
            trxOrder.setActualAmount(new BigDecimal(amount));
            data = onlineBankService.payToTheCardForMerchant(trxOrder, Integer.valueOf(taxSourceId), uid,
                    bankLineNo.trim(), bankBranch.trim());
            logger.info("merchant/replenish/withdrawToCardMerchant end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (BusinessException b) {
            logger.info("merchant/replenish/withdrawToCardMerchant", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/replenish/withdrawToCardMerchant", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "税源地手续费提现到卡海南")
    @GetMapping("/withdrawToCardHaiNan")
    @ResponseBody
    public AjaxResult withdrawToCard(@RequestParam(required = false) String amount) {
        OnlineCommonResultDTO data;
        try {
            logger.info("merchant/replenish/withdrawToCardHaiNan start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            logger.info("merchant/replenish/withdrawToCardHaiNan amount:{}", amount);
            TrxOrder trxOrder = new TrxOrder();
            trxOrder.setOrderSerialNum(ChanPayUtil.generateOutTradeNo());
            trxOrder.setPayeeBankCard("571914934210801");
            trxOrder.setPayeeName("海南浚聚科技有限公司");
            trxOrder.setBankName("招商银行股份有限公司");
            trxOrder.setActualAmount(new BigDecimal(amount));
            Integer taxSourceId = 4;
            String uid = "97799716";
            data = onlineBankService.payToTheCardForTaxSource(trxOrder, taxSourceId, uid);
            logger.info("merchant/replenish/withdrawToCardHaiNan end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (BusinessException b) {
            logger.info("merchant/replenish/withdrawToCardHaiNan", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/replenish/withdrawToCardHaiNan", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "税源地手续费提现到卡泰宁")
    @GetMapping("/withdrawToCardTaiNing")
    @ResponseBody
    public AjaxResult withdrawToCardTaiNing(@RequestParam(required = false) String amount) {
        OnlineCommonResultDTO data;
        try {
            logger.info("merchant/replenish/withdrawToCardTaiNing start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            logger.info("merchant/replenish/withdrawToCardTaiNing amount:{}", amount);
            TrxOrder trxOrder = new TrxOrder();
            trxOrder.setOrderSerialNum(ChanPayUtil.generateOutTradeNo());
            trxOrder.setPayeeBankCard("13885401040007924");
            trxOrder.setPayeeName("泰宁县博瑞企业管理有限公司");
            trxOrder.setBankName("中国农业银行股份有限公司");
            trxOrder.setActualAmount(new BigDecimal(amount));
            Integer taxSourceId = 3;
            String uid = "98763087";
            data = onlineBankService.payToTheCardForTaxSource(trxOrder, taxSourceId, uid);
            logger.info("merchant/replenish/withdrawToCardTaiNing end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (BusinessException b) {
            logger.info("merchant/replenish/withdrawToCardTaiNing", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/replenish/withdrawToCardTaiNing", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "银行列表查询网商")
    @GetMapping("/bankQueryOnline")
    @ResponseBody
    public AjaxResult bankQueryOnline(@ApiParam(value = "父联行号", required = true) String parentBranchNO,
                                      @ApiParam(value = "区域编码", required = true) String areaCode,
                                      @ApiParam(value = "查询关键字", required = true) String keyWords,
                                      @ApiParam(value = "税源地id", required = true) String sourceCompanyId) {
        String data;
        try {
            logger.info("merchant/replenish/bankQueryOnline start:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
            data = onlineBankService.bankQueryOnline(parentBranchNO, areaCode, keyWords, sourceCompanyId);
            logger.info("merchant/replenish/bankQueryOnline end:{}",
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        } catch (BusinessException b) {
            logger.info("merchant/replenish/bankQueryOnline", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/replenish/bankQueryOnline", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "测试模拟充值来账")
    @GetMapping("/subaccount")
    @ResponseBody
    public String subaccount(@RequestParam("cardNo") String cardNo,@RequestParam("amount") String amount) {
        return IOnlineBankUtils.subaccount(cardNo,amount);
    }

    /**
     * 解绑银行卡
     * @param bankInfoId
     * @return
     */
    @GetMapping("/bankcardUnbind")
    @ResponseBody
    public AjaxResult bankcardUnbind(@RequestParam Integer bankInfoId){
        Boolean result;
        try {
            result = onlineBankService.bankcardOrAlipayUnbind(bankInfoId, null);
        } catch (BusinessException b) {
            logger.info("merchant/replenish/bankcardUnbind", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/replenish/bankcardUnbind", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 解绑支付宝
     * @param alipayInfoId
     * @return
     */
    @GetMapping("/alipayUnbind")
    @ResponseBody
    public AjaxResult alipayUnbind(@RequestParam Integer alipayInfoId){
        Boolean result;
        try {
            result = onlineBankService.bankcardOrAlipayUnbind(null, alipayInfoId);
        } catch (BusinessException b) {
            logger.info("merchant/replenish/alipayUnbind", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("merchant/replenish/alipayUnbind", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 查询绑定的银行卡或支付宝
     * @param dto
     * @return
     */
    @PostMapping("/testqu")
    @ResponseBody
    public BankcardQueryResultDTO test(@RequestBody BankcardQueryDTO dto){
        //注册个人会员
        dto.setCharset("UTF-8");
        dto.setSignType("TWSIGN");
        dto.setVersion("2.1");
        dto.setPartnerId("200002007807");
        dto.setNotifyUrl("http://47.111.144.23:8099/notify/receiveTranctionNotify");
        dto.setHost("http://test.tc.mybank.cn/gop/gateway.do");
        dto.setKeyStoreName("testKeyStore1");
        return IOnlineBankUtils.bankcardQuery(dto);
    }

    @ApiOperation(value = "初始化initTblCusLinkMerInfo")
    @PostMapping("/initTblCusLinkMerInfo")
    @ResponseBody
    public AjaxResult initTblCusLinkMerInfo(){
        cusLinkMerInfoService.initTblCusLinkMerInfo();
        return AjaxResult.success();
    }
}
