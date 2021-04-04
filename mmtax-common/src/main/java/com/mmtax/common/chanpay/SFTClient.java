package com.mmtax.common.chanpay;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ljd on 2019/2/26
 */
public class SFTClient {

    private static Logger logger = LoggerFactory.getLogger(SFTClient.class);

    // 基础参数
    public static final String SERVICE = "Service";
    public static final String VERSION = "Version";
    public static final String PARTNER_ID = "PartnerId";
    // 日期
    public static final String TRADE_DATE = "TradeDate";
    public static final String TRADE_TIME = "TradeTime";
    public static final String INPUT_CHARSET = "InputCharset";
    public static final String SIGN = "Sign";
    public static final String SIGN_TYPE = "SignType";
    public static final String MEMO = "Memo";
    public static final String TRANSCODE = "TransCode";

    public static final String MD5 = "MD5";
    public static final String RSA = "RSA";
    /**
     * 转账类型实时到账
     */
    public static final String INSTANT = "INSTANT";


    /**
     * 畅捷代付
     * @param requestNo 订单号
     * @param settleAmount 代付金额
     * @param bankName 银行名称
     * @param bankNo 银行卡号
     * @param name 代付名字
     * @param merchantNo 商户编号
     * @param publicKey 商户公钥
     * @param privateKey 商户私钥
     * @return
     */
    public static JSONObject dfChan(String requestNo, String settleAmount, String bankName,String bankNo,String name,
                                    String merchantNo,String publicKey,String privateKey) {

        Map<String, String> map = requestBaseParameter(merchantNo);
        // 交易码
        map.put("TransCode", "T10000");
//        map.put("OutTradeNo", ChanPayUtil.generateOutTradeNo()); // 商户网站唯一订单号
        // 商户网站唯一订单号
        map.put("OutTradeNo", requestNo);
        // 业务类型
        map.put("BusinessType", "0");
        // 通用银行名称
        map.put("BankCommonName", bankName);
        // 账户类型
        map.put("AccountType", "00");
        map.put("AcctNo",
                ChanPayUtil.encrypt(bankNo, publicKey, BaseConstant.CHARSET));
        // 对手人账号(此处需要用真实的账号信息)
        map.put("AcctName", ChanPayUtil.encrypt(name, publicKey, BaseConstant.CHARSET));
        // 对手人账户名称
        map.put("TransAmt", settleAmount);
//        map.put("CorpPushUrl", requestVO.getCorpPushUrl());

        logger.warn("请求畅捷代付加密参数:{}", JSON.toJSONString(map));
        String result = ChanPayUtil.sendPost(map, BaseConstant.CHARSET,privateKey);
        logger.warn("请求畅捷代付返回结果:{}",result);

        return JSON.parseObject(result);
    }

    /**
     * 户户转账
     * @param payerMemberId 代付商户id
     * @param amount 金额
     * @param requestNo 订单号
     * @param appSecret 秘钥
     * @param accountType 账户类型
     * @return
     */
    public static JSONObject transferAccounts(String payerMemberId, String payeeMemberId, String amount,
                                              String requestNo, String appSecret, String accountType, String privateKey){
        Map<String, String> map = transferParameter(payerMemberId, BaseConstant.NMG_BALANCE_TRANSFER);
        map.put("TrxId",requestNo);
        map.put("TradeType", INSTANT);
        map.put("PayerMemberId", payerMemberId);
        map.put("PayerAccountType","201");
        map.put("PayeeMemberId", payeeMemberId);
        map.put("PayeeAccountType",accountType);
        map.put("Amount",amount);
        String result = ChanPayUtil.sendPost(map, BaseConstant.CHARSET,privateKey);
        logger.info("请求参数：{}", net.sf.json.JSONObject.fromObject(map));
        logger.warn("请求畅捷代付返回结果:{}",result);
        return JSON.parseObject(result);
    }

    /**
     * 查询卡BIN信息
     *
     * @param payerMemberId 商户编码
     * @param requestNo     请求订单号
     * @param acctNo        待查账号
     * @param appSecret     公钥
     * @param privateKey    私钥
     * @return
     */
    public static JSONObject getCardBinInfo(String payerMemberId, String requestNo, String acctNo, String appSecret,
                                            String privateKey) {
        Map<String, String> map = transferParameter(payerMemberId, BaseConstant.CJT_DSF);
        map.put(TRANSCODE, BaseConstant.CARD_BIN);
        map.put("OutTradeNo", requestNo);
        map.put("AcctNo", ChanPayUtil.encrypt(acctNo, appSecret, BaseConstant.CHARSET));
        String result = ChanPayUtil.sendPost(map, BaseConstant.CHARSET, privateKey);
        logger.warn("请求畅捷代付查询卡BIN信息返回结果:{}", result);
        return JSON.parseObject(result);
    }

    /**
     * 查询商户余额信息
     *
     * @param payerMemberId 商户编码
     * @param requestNo     请求订单号
     * @param appSecret     公钥
     * @param privateKey    私钥
     * @return
     */
    public static JSONObject getAccountInfo(String payerMemberId, String requestNo, String appSecret, String privateKey) {
        Map<String, String> map = transferParameter(payerMemberId, BaseConstant.CJT_DSF);
        map.put(TRANSCODE, BaseConstant.BALANCE);
        map.put("OutTradeNo", requestNo);
        String result = ChanPayUtil.sendPost(map, BaseConstant.CHARSET, privateKey);
        logger.warn("请求畅捷代付查询余额信息返回结果:{}", result);
        return JSON.parseObject(result);
    }

    /**
     * 查询户户转账结果
     *
     * @param payerMemberId 商户编码
     * @param requestNo     请求订单号
     * @param appSecret     公钥
     * @param privateKey    私钥
     * @return
     */
    public static JSONObject getTransferAccountsResult(String payerMemberId, String requestNo, String appSecret,
                                                       String privateKey) {
        Map<String, String> map = transferParameter(payerMemberId, BaseConstant.NMG_QUERY_TRANSFER);
        map.put("TrxId", requestNo);
        map.put("TradeType", INSTANT);
        String result = ChanPayUtil.sendPost(map, BaseConstant.CHARSET, privateKey);
        logger.warn("请求畅捷转账信息返回结果:{}", result);
        return JSON.parseObject(result);
    }


    /**
     * 拼接map参数
     * @param object
     * @return
     */
    private static String getMapString(Map object) {
        StringBuilder sb = new StringBuilder();
        for (Object key : object.keySet()) {
            sb.append(key).append("=").append(object.get(key)).append(",");
        }
        return sb.toString();
    }

    /**
     *
     * @param merchantNo
     * @return
     */
    public static Map<String,String> requestBaseParameter(String merchantNo){
        Map<String, String> origMap = new HashMap<String, String>();
        // 2.1 基本参数
        origMap.put(SERVICE, "cjt_dsf");// 鉴权绑卡确认的接口名
        origMap.put(VERSION, "1.0");
        origMap.put(PARTNER_ID, merchantNo); //生产环境测试商户号
        origMap.put(TRADE_DATE, DateUtil.format(DateUtil.date(), DatePattern.PURE_DATE_PATTERN));
        origMap.put(TRADE_TIME, DateUtil.format(DateUtil.date(), DatePattern.PURE_DATE_PATTERN));
        origMap.put(INPUT_CHARSET, BaseConstant.CHARSET);// 字符集
        origMap.put(MEMO, "");// 备注
        return origMap;
    }

    /**
     *户户转账公共参数
     *
     * @param merchantNo 商户编号
     * @param serviceName 服务名称
     * @return
     */
    public static Map<String, String> transferParameter(String merchantNo,String serviceName){
        Map<String, String> origMap = new HashMap<String, String>(16);
        origMap.put(SERVICE,serviceName);
        origMap.put(VERSION,"1.0");
        origMap.put(PARTNER_ID,merchantNo);
        origMap.put(TRADE_DATE, DateUtil.format(DateUtil.date(), DatePattern.PURE_DATE_PATTERN));
        origMap.put(TRADE_TIME,DateUtil.format(DateUtil.date(), DatePattern.PURE_TIME_PATTERN));
        origMap.put(MEMO,"");

        origMap.put(INPUT_CHARSET,BaseConstant.CHARSET);
        return origMap;
    }

    public static void main(String[] args) {
//        JSON json = transferAccounts(BaseConstant.BASICE, BaseConstant.TEST, "0.01", ChanPayUtil.generateOutTradeNo(),
//                BaseConstant.MERCHANT_PUBLIC_KEY, "202", BaseConstant.MERCHANT_PRIVATE_KEY_TEMP);
        //代付
//        JSON json= dfChan(ChanPayUtil.generateOutTradeNo(),"0.01","工商银行",)
        JSON json = getCardBinInfo(BaseConstant.TEST, ChanPayUtil.generateOutTradeNo(), "6212261812008454636",
                BaseConstant.MERCHANT_PUBLIC_KEY, BaseConstant.MERCHANT_PRIVATE_KEY);

        System.out.println(json);

    }

}
