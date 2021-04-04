package com.mmtax.common.constant;

import java.math.BigDecimal;

/**
 * 商户常量类
 * @author yuanligang
 * @date 2019/7/9
 */
public class AddMerchantInfoConstants {
    /** 初始化金额  */
    public final static BigDecimal INIT_AMOUNT = BigDecimal.ZERO;
    /** 商户信息保存标识 */
    public final static String MERCHANT_INFO_SAVE_SUCCESS ="1";
    public final static String MERCHANT_INFO_SAVE_FAIL ="0";

    /** 商户编码是否唯一的返回结果码 */
    public final static String MERCHANT_CODE_UNIQUE = "1";
    public final static String MERCHANT_CODE_NOT_UNIQUE = "0";

    /** 打款误差调整 */
    public final  static int ARTIFICIAL_ADJUSTMENT = 0;
    /** 结算类型 */
    public final  static int INTELLIGENT_SETTLEMENT = 0;
    /** 业务类型 */
    public final  static int TAXFILING = 0;
    /** 服务付费模式 */
    public final  static int REAL_TIME_PAYMENT = 0;

    /** 平台简称 */
    public final  static String WkxxSimpleName = "悟空薪享";
}
