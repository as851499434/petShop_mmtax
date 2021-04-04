package com.mmtax.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author：YH
 * @Date：2020/9/16 9:17
 */
public enum InternetBusinessFailReasonEnum {


    //同步失败原因
    SYSTEM_ERROR("系统内部错误","系统内部错误"),

    DEFAULT_ERROR("银行卡或支付宝鉴权失败","银行卡或支付宝鉴权失败"),

    SESSION_TIMEOUT("session超时","session超时"),

    ILLEGAL_ACCESS_SWITCH_SYSTEM("商户不允许访问该类型的接口","商户不允许访问该类型的接口"),

    REQUIRED_FIELD_NOT_EXIST("必填字段未填","模板中有必填项未填写"),

    FIELD_LENGTH_EXCEEDS_LIMIT("字段长度超过限制","字段长度超过限制"),

    FIELD_TYPE_ERROR("字段类型错误","字段类型错误"),

    PARTNER_ID_NOT_EXIST("合作方Id不存在","商户不存在"),

    TRADE_DATA_MATCH_ERROR("交易信息有误","交易信息有误"),

    TRADE_AMOUNT_MATCH_ERROR("交易内金额不匹配","打款金额错误"),

    TRADE_PAY_MATCH_ERROR("交易与支付金额不匹配","交易与支付金额不匹配"),

    TRADE_NO_MATCH_ERROR("交易号信息有误","交易号信息有误"),

    ILLEGAL_REQUEST("风控未通过","存在打款风险"),

    SELLER_NOT_EXIST("交易卖家不存在","收款人不存在"),

    TRADE_BUYER_NOT_MATCH("输入的买家与交易买家不匹配","打款商户错误"),

    TRADE_SELLER_NOT_MATCH("输入的卖家与交易卖家不匹配","收款人错误"),

    TOTAL_FEE_LESSEQUAL_ZERO("交易总额小于等于0","打款金额必须大于0"),

    TOTAL_FEE_GREATER_THAN_MAX("担保交易单笔总金额不得超过1000000(100万)","担保交易单笔总金额超过限制"),

    EXTERFACE_INVOKE_CONTEXT_EXPIRED("接口调用上下文过期","接口调用上下文过期"),

    ILLEGAL_SIGN_TYPE("签名类型不正确","签名类型不正确"),

    ILLEGAL_SIGN("验签未通过","验签未通过"),

    ILLEGAL_ARGUMENT("参数校验未通过","参数校验未通过"),

    ILLEGAL_SERVICE("服务接口不存在","服务接口不存在"),

    ILLEGAL_ID_TYPE("ID类型不存在","ID类型不存在"),

    USER_ACCOUNT_NOT_EXIST("用户账号不存在","用户账号不存在"),

    MEMBER_ID_NOT_EXIST("用户MemberId不存在","打款商户账号不存在"),

    MEMBER_OR_ACCOUNT_QUERY_FAIL("会员/账户询失败(可能是uid不存在也可能是账户不存在)","打款商户账号不存在"),

    MOBILE_NOT_EXIST("用户手机号不存在","打款商户手机号不存在"),

    ILLEGAL_BUYER_INFO("买家内部Id，外部Id或手机号不匹配","打款商户账号或手机号不存在"),

    ILLEGAL_SELLER_INFO("卖家内部Id，外部Id或手机号不匹配","收款人账号或手机号不存在"),

    ILLEGAL_ROYALTY_PARAMETERS("分润账号集错误","分润账号集错误"),

    ILLEGAL_SUBSCRIPTION_ORDER_NO("订金下订单号错误","订金下订单号错误"),

    ILLEGAL_SUBSCRIPTION("订金金额错误","订金金额错误"),

    ILLEGAL_REFUND_AMOUNT("退款金额信息错误","退款金额信息错误"),

    PAY_METHOD_ERROR("支付方式错误","支付方式错误"),

    ILLEGAL_PAY_METHOD("支付方式未授权","支付方式未授权"),

    DUPLICATE_REQUEST_NO("重复的请求号","交易订单重复"),

    ILLEGAL_OUTER_TRADE_NO("交易订单号不存在","交易订单号不存在"),

    ILLEGAL_DATE_FORMAT("日期格式错误","日期格式错误"),

    ILLEGAL_AMOUNT_FORMAT("金额格式错误","金额格式错误"),

    OPERATOR_ID_NOT_EXIST("操作员Id不存在","操作员Id不存在"),

    ILLEGAL_ENSURE_AMOUNT("担保金额信息错误","担保金额信息错误"),

    ILLEGAL_TIME_INTERVAL("时间区间错误","时间区间错误"),

    ILLEGAL_PAY_ERROR("支付方式错误","支付方式错误"),

    ILLEGAL_ORDER_NO_FORMAT("订单号格式错误","订单号格式错误"),

    ORIGINAL_VOUCHER_INEXISTENCE("原始凭证号不存在","原始凭证号不存在"),

    PARSE_DISCOUNT_PAY_METHOD_ERROR("折扣支付信息有误","折扣支付信息有误"),

    NOT_SUPPORT_DISCOUNT_PAY_METHOD("不支持的折扣方式","不支持的折扣方式"),

    ACCOUNT_NAME_DECRYPT_ERROR("银行卡账户名解密失败","银行卡账户名解密失败"),

    CARD_NO_DECRYPT_ERROR("银行卡号解密失败","银行卡号解密失败"),

    CHARSET_ERROR("编码类型错误","编码类型错误"),

    ACCOUNT_TYPE_NOT_SUPPORTED("账户类型不支持","账户类型不支持"),

    TRADE_TYPE_NOT_SUPPORTED("交易类型不支持","交易类型不支持"),

    ILLEGAL_FUND_TYPE("资金类型不支持","资金类型不支持"),

    TRANSFER_TYPE_NOT_SUPPORTED("转账类型不支持","转账类型不支持"),

    CARD_ATTRIBUTE_NOT_SUPPORTED("卡属性不支持，只支持 B对公，C对私","卡属性不支持，只支持 B对公，C对私"),

    CARD_TYPE_NOT_SUPPORTED("卡类型不支持，只支持 D借记，C贷记","卡类型不支持，只支持 D借记，C贷记"),

    CHECK_PAY_CHANNEL_FAIL("网银支付渠道校验失败","网银支付渠道校验失败"),

    PARTNER_ID_ERROR("商户信息错误","商户信息错误"),

    CERT_ERROR("签名证书错误","签名证书错误"),

    CERT_EXPIRED("签名证书过期","签名证书过期"),

    FIELD_LENGTH_EXCEEDS_ERROR("字段长度不合法","字段长度不合法"),

    FIELD_CANNOT_BE_ZERO("字段值不允许为0","字段值不允许为0"),

    FIELD_CHECK_ERROR("参数类型校验错误","参数类型校验错误"),

    CERT_DOWNLOAD_FAIL("证书下载失败","证书下载失败"),

    BANK_CARD_NOT_MEMBERID("信息不匹配","信息不匹配"),

    WHITE_CARD_IS_EXIST("白名单卡已存在","白名单卡已存在"),

    WHITE_CARD_IS_NOT_EXIST("白名单卡不存在","白名单卡不存在"),

    BUSINESS_VOUCHER_INEXISTENCE("查询无此交易记录","查询无此交易记录"),

    UPLOAD_IMAGE_COUNT_LIMIT("上传图片数量超出限制","上传图片数量超出限制"),

    FILE_NAME_ERROR("文件名格式错误","文件名格式错误"),

    AMOUNT_NOT_NULL("支付方式不为空时，充值金额不能为空","支付方式不为空时，充值金额不能为空"),

    ILLEGAL_INVALIDDATE_FORMAT("过期时间格式错误","过期时间格式错误"),

    UFS_ERROR("UFS调用失败","UFS调用失败"),

    CERT_ADDREPEAT_FAIL("证书重复添加","证书重复添加"),

    ILLEGAL_BANKCARD("明细中部分银行卡验证不通过","明细中部分银行卡验证不通过"),

    CARRYOVER_DATA_MATCH_ERROR("结转信息有误","结转信息有误"),

    UID_NOT_EXIST("用户uId不存在","用户uId不存在"),

    ILLEGAL_ACCOUNT_TYPE("账户信息不正确","账户信息不正确"),

    UPLOAD_FILE_ERROR("上传文件错误","上传文件错误"),

    PAY_ATTRIBUTE_ERROR("银行卡支付属性错误","银行卡支付属性错误"),

    ILLEGAL_ARGUMENT_MEMBER("会员校验未通过","会员校验未通过"),

    ILLEGAL_ARGUMENT_ACCOUNT("账户校验未通过","账户校验未通过"),

    ILLEGAL_ARGUMENT_BANK_CARD("银行卡校验未通过","银行卡校验未通过"),

    BANK_WITHDRAWAL_FAIL("银行出款失败","银行出款失败"),

    BATCH_ILLEGAL_ARGUMENT("批次校验不通过","批次校验不通过"),

    BATCH_DETAIL_ILLEGAL_ARGUMENT("批次明细校验不通过","批次明细校验不通过"),

    PERSON_REAL_NAME_NO_PASS("结果返回信息【网商认证系统返回参数错误】，返回code【AE13112000540001】",
            "姓名和身份证号不匹配"),

    ORDER_STATUS_TO_CONFIRMED("订单状态待确认","系统错误"),

    CHECK_VERIFY_FACTOR_INFO_FAIL("姓名，身份证号或银行卡错误，请重新上传","姓名，身份证号或银行卡错误，请重新上传"),
    //异步失败原因
    AE15112059999043("AE15112059999043:卡号户名不匹配","卡号户名不匹配"),

    AE15112059999000("AE15112059999000:参数非法","姓名和身份证不匹配"),

    AE15011014207055("AE15011014207055:[SAL_INVOK_EXCEPTION]SAL调用异常","网络错误"),

    AE15999999999999("AE15999999999999:远程接口调用异常","网络错误"),

    //自定义
    //errorCodeF999
    ALIPAY_NO_EXIST("不存在此支付宝账户","支付宝账户不存在"),

    NO_PAYMENT_CHANNEL("无可用付款渠道","无可用付款渠道"),

    DEAL_FAIL("交易失败","交易失败"),

    DEAL_FAIL_SUGGEST_CHANGE_ANOTHER("交易失败,建议换一张卡重试","交易失败,建议换一张卡重试"),

    RECIPROCAL_ACCOUNT_NUMBER_EXCEPTION("对方账户状态异常","对方账户状态异常"),

    DEAL_FAIL_SUGGEST("交易处理失败，建议换一张卡重试或稍后再试","交易处理失败，建议换一张卡重试或稍后再试"),

    //errorCodeF0201
    INSUFFICIENT_BALANCE("余额不足","余额不足"),

    //memo
    MEMO_CARDBIN_NOT_ACCESS("卡bin校验不通过","银行卡校验没通过");

    private String description;

    private String modifyDescription;

    InternetBusinessFailReasonEnum(String description, String modifyDescription) {
        this.description = description;
        this.modifyDescription = modifyDescription;
    }

    public static  Map<String,String> errorCodeF999;

    public static  Map<String,String> errorCodeF0201;

    public static  Map<String,String> errorCodeMemo;


    static {
        errorCodeF999 = new HashMap<>();
        errorCodeF999.put("交易失败","DEAL_FAIL");
        errorCodeF999.put("交易失败,建议换一张卡重试","DEAL_FAIL_SUGGEST_CHANGE_ANOTHER");
        errorCodeF999.put("无可用付款渠道","NO_PAYMENT_CHANNEL");
        errorCodeF999.put("不存在此支付宝账户","ALIPAY_NO_EXIST");
        errorCodeF999.put("订单状态待确认","ORDER_STATUS_TO_CONFIRMED");
        errorCodeF999.put("对方账户状态异常","RECIPROCAL_ACCOUNT_NUMBER_EXCEPTION");
        errorCodeF999.put("交易处理失败，建议换一张卡重试或稍后再试","DEAL_FAIL_SUGGEST");

        errorCodeF0201 = new HashMap<>();
        errorCodeF0201.put("余额不足"," INSUFFICIENT_BALANCE");

        errorCodeMemo = new HashMap<>();
        errorCodeMemo.put("卡bin校验不通过","MEMO_CARDBIN_NOT_ACCESS");
    }
    public static InternetBusinessFailReasonEnum getByEnumName(String enumName){
        if(enumName.contains("余额不足")){
            enumName = "余额不足";
        }

        if(errorCodeF999.containsKey(enumName)){
            return InternetBusinessFailReasonEnum.valueOf(errorCodeF999.get(enumName));
        }
        if(errorCodeF0201.containsKey(enumName)){
            return InternetBusinessFailReasonEnum.valueOf(errorCodeF0201.get(enumName));
        }
        if(errorCodeMemo.containsKey(enumName)){
            return InternetBusinessFailReasonEnum.valueOf(errorCodeMemo.get(enumName));
        }
        for (InternetBusinessFailReasonEnum one:InternetBusinessFailReasonEnum.values()) {
            if(enumName.contains(one.name())){
                return one;
            }
        }
        return InternetBusinessFailReasonEnum.DEFAULT_ERROR;
    }

    public String getDescription() {
        return description;
    }


    public String getModifyDescription() {
        return modifyDescription;
    }



}
