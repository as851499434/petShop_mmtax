package com.mmtax.common.utils.onlinebank;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.constant.onlinebank.OnlineConstants;
import com.mmtax.common.enums.VerifyTypeEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.SftpUtil;
import com.mmtax.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static com.mmtax.common.utils.onlinebank.OnlineBankUtils.doHttpClientPost;

/**
 * 云资金工具类
 * @author Ljc
 * @date 2020/4/26
 */
@Slf4j
public class IOnlineBankUtils {

    /**
     * 企业用户注册会员
     * @param dto
     */
    public static EnterPriseRegisterResultDTO enterpriseRegister(EnterpriseRegisterDTO dto) {
        log.info("开始注册企业子账户");
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.user.enterprise.register");
        params.put("uid", dto.getUid());
        params.put("enterprise_name", dto.getEnterpriseName());
        params.put("unified_social_credit_code",dto.getUnifiedSocialCreditCode());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("注册企业子账户uid:{}，返回：{}", dto.getUid(), result);
        EnterPriseRegisterResultDTO resultDTO = JSON.parseObject(result,EnterPriseRegisterResultDTO.class);
        if(!"T".equals(resultDTO.getIs_success())){
            throw new BusinessException("注册企业子账户失败"+resultDTO.getError_message());
        }
        return resultDTO;
    }

    /**
     * 企业用户修改
     * @param dto
     */
    public static OnlineCommonResultDTO enterpriseModify(EnterpriseRegisterDTO dto) {
        log.info("开始修改企业子账户信息");
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.user.enterprise.info.modify");
        params.put("uid", dto.getUid());
        if(!StringUtils.isEmpty(dto.getEnterpriseName())) {
            params.put("enterprise_name", dto.getEnterpriseName());
        }
        if(!StringUtils.isEmpty(dto.getUnifiedSocialCreditCode())) {
            params.put("unified_social_credit_code", dto.getUnifiedSocialCreditCode());
        }
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("修改企业子账户信息，返回：{}",result);
        OnlineCommonResultDTO resultDTO = JSON.parseObject(result,OnlineCommonResultDTO.class);
        if(!"T".equals(resultDTO.getIs_success())){
            throw new BusinessException("修改企业子账户信息失败："+resultDTO.getError_message());
        }
        return resultDTO;
    }

    /**
     * 企业用户信息查询
     * @param uid
     */
    public static JSONObject queryEnterprise(String uid) {
        log.info("开始查询企业子账户信息");
        OnlineBankCommonDTO dto = new OnlineBankCommonDTO();
        dto.setKeyStoreName("testKeyStore1");
        dto.setHost("http://test.tc.mybank.cn/gop/gateway.do");
        dto.setCharset("UTF-8");
        dto.setSignType("TWSIGN");
        dto.setPartnerId("200002007807");
        dto.setVersion("2.1");
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.user.enterprise.info.query");
        params.put("uid", uid);
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        JSONObject resultDTO = JSONObject.parseObject(result);
        log.info("查询企业子账户信息，返回：{}",result);
        if(!"T".equals(resultDTO.getString("is_success"))){
            throw new BusinessException("查询企业子账户信息失败："+resultDTO.getString("error_message"));
        }
        return resultDTO;
    }

    /**
     * 注册员工子账户
     * @param dto
     * @return
     */
    public static PersonalRegisterResultDTO personalRegister(PersonalRegisterDTO dto){
        log.info("开始注册员工子账户");
        Map<String, String> params = getCommonParams(dto);
        params.put("service","mybank.tc.user.personal.register");
        params.put("uid",dto.getUid());
        params.put("mobile",dto.getMobile());
        params.put("real_name",dto.getRealName());
        params.put("member_name",dto.getMemberName());
        params.put("certificate_type",dto.getCertificateType());
        params.put("certificate_no",dto.getCertificateNo());
        params.put("is_verify",dto.getIsVerify());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("注册员工子账户,身份证号：{}，返回：{}", dto.getCertificateNo(), result);
        //        if(!"T".equals(resultDTO.getIs_success())){
//            throw new BusinessException("注册员工子账户失败"+resultDTO.getError_message());
//        }
        return JSON.parseObject(result,PersonalRegisterResultDTO.class);
    }

    /**
     * 卡BIN查询
     * @param dto
     * @return
     */
    public static CardbinQueryResultDTO cardbinQuery(CardbinQueryDTO dto){
        log.info("卡{}的BIN查询",dto.getBankAccountNo());
        Map<String, String> params = getCommonParams(dto);
        params.put("service","mybank.tc.user.cardbin.query");
        params.put("bank_account_no",dto.getBankAccountNo());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("卡{}的BIN查询，返回：{}",dto.getBankAccountNo(),result);
        CardbinQueryResultDTO resultDTO = JSON.parseObject(result,CardbinQueryResultDTO.class);
        return resultDTO;
    }

    /**
     * 绑定银行卡
     * @param dto
     * @return
     */
    public static BankcardBindResultDTO bankcardBind(BankcardBindDTO dto){
        log.info("开始绑定银行卡");
        Map<String, String> params = getCommonParams(dto);
        params.put("service","mybank.tc.user.bankcard.bind");
        params.put("uid",dto.getUid());
//        params.put("bank_name",dto.getBankName());
        params.put("bank_account_no",dto.getBankAccountNo());
        params.put("account_name",dto.getAccountName());
        params.put("card_type",dto.getCardType());
        params.put("card_attribute",dto.getCardAttribute());
        if(null != dto.getVerifyType() && !VerifyTypeEnum.NOVERIFY.getType().toString().equals(dto.getVerifyType())) {
            params.put("verify_type", dto.getVerifyType());
        }
        params.put("certificate_type",dto.getCertificateType());
        params.put("certificate_no",dto.getCertificateNo());
        params.put("reserved_mobile",dto.getReservedMobile());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("绑定银行卡，返回：{}",result);
        BankcardBindResultDTO resultDTO = JSON.parseObject(result,BankcardBindResultDTO.class);
//        if(!"T".equals(resultDTO.getIs_success())){
//            throw new BusinessException("绑定银行卡失败"+resultDTO.getError_message()+","+resultDTO.getMemo());
//        }
        return resultDTO;
    }

    /**
     * 绑定支付宝
     * @param dto
     * @return
     */
    public static AlipayBindResultDTO alipayBind(AlipayBindDTO dto){
        log.info("开始绑定支付宝");
        Map<String, String> params = getCommonParams(dto);
        params.put("service","mybank.tc.user.alipay.bind");
        params.put("uid",dto.getUid());
        params.put("account_no",dto.getAccountNo());
        params.put("account_name",dto.getAccountName());
        params.put("is_verify",dto.getIsVerify());
        params.put("certificate_no",dto.getCertificateNo());
        params.put("mobile_no",dto.getMobileNo());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("绑定支付宝，返回：{}",result);
        AlipayBindResultDTO resultDTO = JSON.parseObject(result,AlipayBindResultDTO.class);
//        if(!"T".equals(resultDTO.getIs_success())){
//            throw new BusinessException("绑定支付宝失败"+resultDTO.getError_message()+","+resultDTO.getMemo());
//        }
        return resultDTO;
    }

    /**
     * 解绑银行卡或支付宝
     * @param dto
     * @return
     */
    public static OnlineCommonResultDTO bankcardOrAlipayUnbind(BankcardUnbindDTO dto){
        log.info("开始解绑银行卡或支付宝");
        Map<String, String> params = getCommonParams(dto);
        params.put("service","mybank.tc.user.bankcard.unbind");
        params.put("uid",dto.getUid());
        params.put("bank_id",dto.getBankId());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("绑定支付宝，返回：{}",result);
        OnlineCommonResultDTO resultDTO = JSON.parseObject(result,OnlineCommonResultDTO.class);
        if(!"T".equals(resultDTO.getIs_success())){
            throw new BusinessException("解绑银行卡或支付宝失败"+resultDTO.getError_message()+","+resultDTO.getMemo());
        }
        return resultDTO;
    }

    /**
     * 查询绑定的银行卡
     * @param dto
     * @return
     */
    public static BankcardQueryResultDTO bankcardQuery(BankcardQueryDTO dto){
        log.info("开始查询子账户绑定的银行卡");
        Map<String, String> params = getCommonParams(dto);
        params.put("service","mybank.tc.user.bankcard.query");
        params.put("uid",dto.getUid());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        BankcardQueryResultDTO resultDTO = JSON.parseObject(result,BankcardQueryResultDTO.class);
        return resultDTO;
    }

    /**
     * 单笔提现
     * @param dto
     * @return
     */
    public static OnlineCommonResultDTO tradePaytocard(TradePaytocardDTO dto){
        log.info("单号{}开始单笔提现",dto.getOuterTradeNo());
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.trade.paytocard");
        params.put("uid", dto.getUid());
        params.put("outer_trade_no", dto.getOuterTradeNo());
        params.put("outer_inst_order_no", dto.getOuterInstOrderNo());
        params.put("white_channel_code", dto.getWhiteChannelCode());
        params.put("account_type", dto.getAccountType());
        params.put("bank_id", dto.getBankId());
        params.put("amount", dto.getAmount());
        if (!StringUtils.isEmpty(dto.getMemo())) {
            params.put("memo", dto.getMemo());
        }
        if(!StringUtils.isEmpty(dto.getFeeInfo())) {
            params.put("fee_info", dto.getFeeInfo());
        }
        params.put("notify_url", dto.getNotifyUrl());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("单号{}单笔提现，返回：{}",dto.getOuterTradeNo(),result);
        OnlineCommonResultDTO resultDTO;
        if(null == result){
            log.info("单笔提现返回的结果为空，可能网络异常或第三方服务维护，订单需手动处理");
            resultDTO = new OnlineCommonResultDTO();
            resultDTO.setIs_success("M");
            resultDTO.setError_message("单笔提现失败,网络异常或第三方服务维护，订单需手动处理");
            return resultDTO;
        }
        resultDTO = JSON.parseObject(result,OnlineCommonResultDTO.class);
        if(!"T".equals(resultDTO.getIs_success())){
            log.info("单笔提现失败"+resultDTO.getError_message());
        }
        return resultDTO;
    }

    /**
     * 单笔提现到卡
     * @param dto
     * @return
     */
    public static OnlineCommonResultDTO withdrawtocard(WithdrawToCardDTO dto) {
        log.info("开始单笔提现到卡");
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.trade.withdrawtocard");
        params.put("uid", dto.getUid());
        params.put("outer_trade_no", dto.getOuterTradeNo());
        params.put("outer_inst_order_no", dto.getOuterInstOrderNo());
        params.put("white_channel_code", dto.getWhiteChannelCode());
        params.put("account_type", dto.getAccountType());
        params.put("bank_account_no", dto.getBankAccountNo());
        params.put("account_name", dto.getAccountName());
        params.put("card_type", dto.getCardType());
        params.put("card_attribute", dto.getCardAttribute());
        if(OnlineConstants.CARD_ATTRIBUTE_PUBLIC.equals(dto.getCardAttribute())){
            params.put("bank_line_no", dto.getBankLineNo());
            params.put("bank_branch", dto.getBankBranch());
        }
        params.put("amount", dto.getAmount());
        params.put("notify_url", dto.getNotifyUrl());
        params.put("bank_name", dto.getBankName());
        params.put("bank_code", dto.getBankCode());
        params.put("fee_info",dto.getFeeInfo());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("单笔提现到卡，返回：{}",result);
        OnlineCommonResultDTO resultDTO;
        if(null == result){
            log.info("单笔提现到卡返回的结果为空，可能网络异常或第三方服务维护，订单需手动处理");
            resultDTO = new OnlineCommonResultDTO();
            resultDTO.setIs_success("M");
            resultDTO.setError_message("单笔提现到银行卡失败,网络异常或第三方服务维护，订单需手动处理");
            return resultDTO;
        }
        resultDTO = JSON.parseObject(result,OnlineCommonResultDTO.class);
        if(!"T".equals(resultDTO.getIs_success())){
            log.info("单笔提现到银行卡失败"+resultDTO.getError_message());
        }
        return resultDTO;
    }

    /**
     * 单笔提现到卡 税源地专用
     * @param dto
     * @return
     */
    public static OnlineCommonResultDTO withdrawtocardForTaxSource(WithdrawToCardDTO dto) {
        log.info("开始单笔提现到卡");
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.trade.withdrawtocard");
        params.put("uid", dto.getUid());
        params.put("outer_trade_no", dto.getOuterTradeNo());
        params.put("outer_inst_order_no", dto.getOuterInstOrderNo());
        params.put("white_channel_code", dto.getWhiteChannelCode());
        params.put("account_type", dto.getAccountType());
        params.put("bank_account_no", dto.getBankAccountNo());
        params.put("account_name", dto.getAccountName());
        params.put("card_type", dto.getCardType());
        params.put("card_attribute", dto.getCardAttribute());
        if(OnlineConstants.CARD_ATTRIBUTE_PUBLIC.equals(dto.getCardAttribute())){
            params.put("bank_line_no", dto.getBankLineNo());
            params.put("bank_branch", dto.getBankBranch());
        }
        params.put("amount", dto.getAmount());
        params.put("notify_url", dto.getNotifyUrl());
        params.put("bank_name", dto.getBankName());
        params.put("bank_code", dto.getBankCode());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("单笔提现到卡，返回：{}",result);
        OnlineCommonResultDTO resultDTO;
        if(null == result){
            log.info("单笔提现到卡返回的结果为空，可能网络异常或第三方服务维护，订单需手动处理");
            resultDTO = new OnlineCommonResultDTO();
            resultDTO.setIs_success("M");
            resultDTO.setError_message("单笔提现到银行卡失败,网络异常或第三方服务维护，订单需手动处理");
            return resultDTO;
        }
        resultDTO = JSON.parseObject(result,OnlineCommonResultDTO.class);
        if(!"T".equals(resultDTO.getIs_success())){
            log.info("单笔提现到银行卡失败"+resultDTO.getError_message());
        }
        return resultDTO;
    }

    /**
     * 即时交易入账
     * @param dto
     * @return
     */
    public static OnlineCommonResultDTO payInstant(PayInstantDTO dto,String memo) {
        log.info("转账单号{}开始转账",dto.getOuterTradeNo());
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.trade.pay.instant");
        params.put("buyer_id", dto.getBuyerId());
        params.put("outer_trade_no", dto.getOuterTradeNo());
        if(StringUtils.isEmpty(memo)) {
            memo = "";
        }
        params.put("pay_method", getPayMethod(dto.getPrice().setScale(2, RoundingMode.HALF_UP).toString(),memo));
        if(!StringUtils.isEmpty(dto.getFeeInfo())) {
            params.put("fee_info",dto.getFeeInfo());
        }
        params.put("subject", dto.getSubject());
        params.put("price", dto.getPrice().setScale(2, RoundingMode.HALF_UP).toString());
        params.put("quantity", dto.getQuantity().toString());
        params.put("total_amount", dto.getPrice().multiply(dto.getQuantity())
                .setScale(2, RoundingMode.HALF_UP).toString());
        params.put("seller_id", dto.getSellerId());
        params.put("account_type", dto.getAccountType());
        params.put("notify_url", dto.getNotifyUrl());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("转账单号{}转账，返回：{}",dto.getOuterTradeNo(),result);
        OnlineCommonResultDTO resultDTO;
        if(null == result){
            log.info("转账返回的结果为空，可能网络异常或第三方服务维护，转账失败");
            resultDTO = new OnlineCommonResultDTO();
            resultDTO.setIs_success("F");
            resultDTO.setError_message("转账失败,网络异常或第三方服务维护");
            return resultDTO;
        }
        return JSON.parseObject(result,OnlineCommonResultDTO.class);
    }

    /**
     * 退款入账
     * @param dto
     * @return
     */
    public static OnlineCommonResultDTO tradeRefund(TradeRefundDTO dto) {
        log.info("退款单号{}开始退款",dto.getOuterTradeNo());
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.trade.refund");
        params.put("outer_trade_no", dto.getOuterTradeNo());
        params.put("orig_outer_trade_no",dto.getOrigOuterTradeNo());
        if(!StringUtils.isEmpty(dto.getRefundFeeInfo())) {
            params.put("refund_fee_info",dto.getRefundFeeInfo());
        }
        params.put("refund_amount", dto.getRefundAmount());
        params.put("notify_url", dto.getNotifyUrl());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("退款单号{}退款，返回：{}",dto.getOuterTradeNo(),result);
        OnlineCommonResultDTO resultDTO;
        if(null == result){
            log.info("返回的结果为空，可能网络异常或第三方服务维护，退款失败");
            resultDTO = new OnlineCommonResultDTO();
            resultDTO.setIs_success("F");
            resultDTO.setError_message("转账失败,网络异常或第三方服务维护");
            return resultDTO;
        }
        return JSON.parseObject(result,OnlineCommonResultDTO.class);
    }

    /**
     * 构造payMethod
     * @param amount
     * @return
     */
    private static String getPayMethod(String amount,String memo){
        Map<String,String> payMethod = new HashMap<>(8);
        payMethod.put("pay_method","BALANCE");
        payMethod.put("amount",amount);
        payMethod.put("memo",memo);
        payMethod.put("extension","");
        return JSON.toJSONString(payMethod);
    }

    /**
     * 退票
     * @param dto
     * @return
     */
    public OnlineCommonResultDTO refundticket(RefundticketDTO dto) {
        log.info("开始退票");
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.trade.refundticket");
        params.put("request_no", dto.getRequestNo());
        params.put("orig_outer_inst_order_no", dto.getOrigOuterInstOrderNo());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("退票，返回：{}",result);
        return JSON.parseObject(result,OnlineCommonResultDTO.class);
    }

    /**
     * 查询子账户余额
     * @param dto
     * @return
     */
    public static AccountBalanceResultDTO accountBalance(AccountBalanceDTO dto) {
        log.info("开始查询子账户余额");
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.user.account.balance");
        params.put("uid", dto.getUid());
        if(null != dto.getAccountType()) {
            params.put("account_type", dto.getAccountType());
        }
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("查询子账户余额，返回：{}",result);
        return JSON.parseObject(result,AccountBalanceResultDTO.class);
    }

    /**
     * 银行列表查询 网商
     * @param dto
     * @return
     */
    public static String bankQueryOnline(BankQueryDTO dto) {
        log.info("开始查询银行列表");
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.user.area.bank.query");
        params.put("parent_branch_no", dto.getParentBranchNo());
        params.put("area_code", dto.getAreaCode());
        params.put("key_words", dto.getKeyWords());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("查询银行列表，parent_branch_no:{}返回：{}", dto.getParentBranchNo(), result);
        return result;
    }

    /**
     * 结算户余额查询
     * @param dto
     * @return
     */
    public BalanceQueryResultDTO balanceQuery(OnlineBankCommonDTO dto) {
        log.info("开始结算户余额查询");
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.user.partner.balance.query");
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("结算户余额查询，返回：{}",result);
        return JSON.parseObject(result,BalanceQueryResultDTO.class);
    }

    /**
     * 查询订单状态
     * @param dto
     * @return
     */
    public static InfoQueryResultDTO infoQuery(InfoQueryDTO dto){
        log.info("开始查询订单{}状态",dto.getOuterTradeNo());
        Map<String, String> params = getCommonParams(dto);
        params.put("service","mybank.tc.trade.info.query");
        params.put("outer_trade_no",dto.getOuterTradeNo());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("查询订单{}状态结束，返回：{}",dto.getOuterTradeNo(),result);
        return JSON.parseObject(result,InfoQueryResultDTO.class);
    }

    /**
     * 子账户余额变动明细查询
     * @param dto
     * @return
     */
    public static AccountQueryResultDTO accountQuery(AccountQueryDTO dto){
        log.info("开始查询子账户余额变动明细");
        Map<String,String> params = getCommonParams(dto);
        params.put("service","mybank.tc.trade.account.query");
        params.put("uid",dto.getUid());
        params.put("start_time",dto.getStartTime());
        params.put("end_time",dto.getEndTime());
        params.put("account_type",dto.getAccountType());
        params.put("current_page",dto.getCurrentPage());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("查询子账户余额变动明细，返回：{}",result);
        AccountQueryResultDTO resultDTO = JSON.parseObject(result,AccountQueryResultDTO.class);
        return resultDTO;
    }

    /**
     * 模拟子账户汇入的接口，正式环境不存在该接口
     * @return
     */
    public static String subaccount(String payeeCardNo,String amount){
        log.info("开始汇款子账户");
        OnlineBankCommonDTO dto = new OnlineBankCommonDTO();
        dto.setPartnerId("200002007807");
        dto.setNotifyUrl("http://47.111.144.23:8099/notify/receiveTranctionNotify");
        dto.setHost("http://test.tc.mybank.cn/gop/gateway.do");
        dto.setKeyStoreName("testKeyStore1");
        Map<String, String> params = new HashMap<>();
        params.put("charset", "UTF-8");
        params.put("version", "2.1");
        params.put("partner_id", "200002007807");
        params.put("sign_type", "TWSIGN");
        params.put("service", "mybank.tc.trade.remit.subaccount");
        params.put("payee_card_no",payeeCardNo);
        params.put("payee_card_name","新增企业八");
        params.put("amount", amount);
        params.put("payer_remark","汇款");
        params.put("notify_url",dto.getNotifyUrl());
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("汇款子账户，返回：{}",result);
        return result;
    }

    private static Map<String,String> getCommonParams(OnlineBankCommonDTO dto){
        Map<String, String> params = new HashMap<>();
        params.put("charset", dto.getCharset());
        params.put("version", dto.getVersion());
        params.put("partner_id", dto.getPartnerId());
        params.put("sign_type", dto.getSignType());
        return params;
    }

    private static String postSendTheRequest(Map<String,String> params,String keyStoreName,String signtype,String host){
        Map<String,String> send;
        send = MagCore.paraFilter2(params);
        try {
            String sign = MagCore.buildRequestByTWSIGN(send, "utf-8", keyStoreName);
            send.put("sign", sign);
        }catch(Exception e){
            log.error("加签失败：{}",e.getMessage());
            throw new BusinessException("系统错误,请重新打款");
        }
        send.put("sign_type", signtype);
        log.info("入参：{}",JSON.toJSONString(send));
        return doHttpClientPost(host, send);
    }

    /**
     * 网商 批量代付
     * @param dto
     * @return
     */
    public static OnlineCommonResultDTO onlineBatchPayment(BatchPaymentDTO dto) {
        log.info("批量代付单号：{}",dto.getOuterTradeNo());
        Map<String, String> params = getCommonParams(dto);
        params.put("service", "mybank.tc.trade.batchwithdrawtocard");

        params.put("outer_trade_no", dto.getOuterTradeNo());
        params.put("file_path",dto.getFilePath());
        params.put("file_name",dto.getFileName());
        params.put("uid",dto.getUid());
        params.put("white_channel_code",dto.getWhiteChannelCode());
        params.put("total_count",dto.getTotalCount());
        params.put("total_amount",dto.getTotalAmount());
        params.put("notify_url",dto.getNotifyUrl());


        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("批量代付单号{}，返回：{}",dto.getOuterTradeNo(),result);
        OnlineCommonResultDTO resultDTO;
        if(null == result){
            log.info("返回的结果为空，可能网络异常或第三方服务维护，批量打款失败");
            resultDTO = new OnlineCommonResultDTO();
            resultDTO.setIs_success("F");
            resultDTO.setError_message("批量打款失败,网络异常或第三方服务维护");
            return resultDTO;
        }
        return JSON.parseObject(result,OnlineCommonResultDTO.class);
    }

    /*
        封装准备发送数据
     */
    public static void preOnlineBatchPayment(String merchantCode,String fileName,String outerTradeNo) {
        BatchPaymentDTO batchPaymentDTO = new BatchPaymentDTO();
        batchPaymentDTO.setCharset("UTF-8");
        batchPaymentDTO.setVersion("2.1");
        batchPaymentDTO.setPartnerId("200002007807");
        batchPaymentDTO.setSignType("TWSIGN");
        batchPaymentDTO.setKeyStoreName("testKeyStore1");
        batchPaymentDTO.setWhiteChannelCode("MYBANK00125");
        batchPaymentDTO.setHost("http://test.tc.mybank.cn/gop/gateway.do");
        batchPaymentDTO.setOuterTradeNo(outerTradeNo);
        batchPaymentDTO.setFilePath("/216610001123747598400/20201202/");
        batchPaymentDTO.setFileName(fileName);
        batchPaymentDTO.setUid(merchantCode);
        batchPaymentDTO.setTotalCount("7");
        batchPaymentDTO.setTotalAmount("0.07");

        OnlineCommonResultDTO resultDTO = onlineBatchPayment(batchPaymentDTO);
        log.info("结果：{}",JSON.toJSONString(resultDTO));
    }

    public static void test(){
        log.info("开始测试");
        OnlineBankCommonDTO dto = new OnlineBankCommonDTO();
        dto.setPartnerId("200002007807");
        dto.setHost("http://test.tc.mybank.cn/gop/gateway.do");
        dto.setKeyStoreName("testKeyStore");
        Map<String, String> params = new HashMap<>();
        params.put("charset", "UTF-8");
        params.put("version", "2.1");
        params.put("partner_id", "200002007807");
        params.put("sign_type", "TWSIGN");
        params.put("service", "mybank.tc.trade.refundticket");
        params.put("request_no", "20200429144202");
        params.put("orig_outer_inst_order_no", "20200428213203485907855");
        params.put("notify_url","http://47.111.144.23:8099/notify/receiveTranctionNotify");
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("测试，返回：{}",result);
    }

    public static void test2(){
        log.info("开始测试");
        OnlineBankCommonDTO dto = new OnlineBankCommonDTO();
        dto.setPartnerId("200002007807");
        dto.setHost("http://test.tc.mybank.cn/gop/gateway.do");
        dto.setKeyStoreName("testKeyStore1");
        Map<String, String> params = new HashMap<>();
        params.put("charset", "UTF-8");
        params.put("version", "2.1");
        params.put("partner_id", "200002007807");
        params.put("sign_type", "TWSIGN");
        params.put("service", "mybank.tc.trade.info.query");
        params.put("outer_trade_no", "20200528185920396431945");//20200428212702920537329
        String result = postSendTheRequest(params,dto.getKeyStoreName(),dto.getSignType(),dto.getHost());
        log.info("测试，返回：{}",result);
    }

    public void readEncryptUploadOnline(String filePath,String fileName,Integer taxSourceId) throws Exception {
        String host = "139.224.233.66";
        Integer port = 2121;
        String userName = "bwzptrsvfx";
        String pw = "BoG5dfFiU4c6";

        String pid = "216610001123747598400";

        String baseDirect = "/216610001123747598400";
        //
        String uploadDirect = baseDirect + "/" + DateUtil.now().substring(0,10) + "/";
        //要上传的文件路径
        filePath = "C:\\Users\\lenovo\\Desktop\\";
        fileName = "h2h_batchPay_216610001123747598400_20201201160024688625713123456789.xls";
        String keyStoreName = "testKeyStore1";
        byte[] bytes = FileUtils.fileConvertToByteArray(new File(filePath + fileName));


        String enCodeFilePath = "E:\\download\\";
        OutputStream fos = new FileOutputStream(enCodeFilePath + fileName);
        fos.write(Twsign.encode(bytes, keyStoreName));
        fos.flush();

        SftpUtil.loginSftp(host,port,userName,pw);
        SftpUtil.upLoadFile(uploadDirect,fileName,new FileInputStream(enCodeFilePath + fileName));
        SftpUtil.logoutSftp();

        log.info("上传成功");


    }

    public static void main(String[] args) throws Exception {
    //上传批量代付文件
//      testUploadFileToOnlineBussiness();
//        IOnlineBankUtils.testload();
    }



}
