package com.mmtax.common.utils.redis;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/14 12:48
 */
public class RedisLockConstans {
    /**
     * 钱包锁
     */
    public static String ACCOUNT_LOCK = "account_lock";

    /**
     * 员工锁
     */
    public static String CUSTOMER_INFO_LOCK = "customer_info_lock";
    /**
     * 员工锁
     */
    public static String ESIGN_CUSTOMER_LOCK = "esign_customer_lock";

    /**
     * 员工银行卡锁
     */
    public static String CUSTOMER_INFO_BANK_LOCK = "customer_info_bank_lock";

    /**
     * 员工支付宝锁
     */
    public static String CUSTOMER_INFO_ALIPAY_LOCK = "customer_info_alipay_lock";
    /** 同一商户同一批次加锁 */
    public static String MERCHANTID_BATCHNO_LOCK = "merchantid_batchno_lock";
}
