package com.mmtax.common.utils.yunzbutil;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/8 14:44
 */
public class YunZBConstants {

    public static String serviceUrl = "https://api.yunzb123.com";
    /**
     * 平台商户号
     */
    @Value("${mchid}")
    public static String mchId = "81001659134";

    /**
     * 商户密钥
     */
    @Value("${secret-key}")
    public static String secretKey = "7B88B46889C2C1DFC9CDD8CB06492E80";
    /**
     * 新增商户
     */
    public static String OPEN_ACC = "open_acc";
    /**
     * 上传图片
     */
    public static String UPLOAD_PIC = "open_acc/upload_pic";
    /**
     *查询渠道号
     */
    public static String CHANNEL = "query_channels";
    /**
     * 查询发票详情
     */
    public static String INVOICE = "query_invoice";
    /**
     * 商户开票账单查询
     */
    public static String BILLS = "query_bills";
    /**
     * 线下加账申请
     */
    public static String RECHARGE = "offline_recharge";
    /**
     * 云众包返回成功标识
     */
    public static final String RESULT_CODE = "SUCCESS";

    /**
     * 云众包通知成功标识
     */
    public static final String MCH_STATUS = "SUCC";
    /**
     * 云众包返回消息
     */
    public static final String RETURN_MSG = "return_msg";
    /**
     * 云众包返回编码
     */
    public static final String RETURN_CODE = "return_code";
    /**
     * 云众包注册返回商户id
     */
    public static final String SUB_MCH_ID = "sub_mch_id";
    /**
     * 返回结果标识
     */
    public static final String REQUEST_CODE = "result_code";
    /**
     * 充值接口返回订单交易流水号
     */
    public static final String SERIAL_NO = "serial_no";
    /**
     * 返回信息
     */
    public static final String REQUEST_MSG = "result_msg";
    /**
     * 余额查询
     */
    public static final String QUERY_BALANCE = "query_balance";
    /**
     * 打款请求
     */
    public static final String SETT = "sett";
    /**
     * 发票批次号
     */
    public static final String BATCH_ID = "batch_id";
    /**
     * 商户发票内容通知
     */
    public static final String INVOICE_CONTENT = "query_invoice_content";
    /**
     * 个人用户签约
     */
    public static final String SIGNED = "signed";
    /**
     * 发票代码
     */
    public static final String CONTENTS = "contents";
    /**
     * 出款通知异步回调地址
     */
//    public static final String SETT_NOTIFY = "http://39.98.153.30:8088/notify/sett_notify";
    public static final String SETT_NOTIFY = "http://api.wukongxinxiang.com/notify/sett_notify";

    public static final String UUID = cn.hutool.core.lang.UUID.randomUUID().toString().replace("-", "");
}
