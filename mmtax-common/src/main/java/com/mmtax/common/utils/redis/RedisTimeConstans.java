package com.mmtax.common.utils.redis;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/14 13:32
 */
public class RedisTimeConstans {

    public static final Integer ONE_MINUTE = 60;
    public static final Integer FIVE_MINUTE = 60*5;
    public static final Integer THIRTY = 30;

    public static final Integer TWENTY = 20;

    /**
     * 网商员工 银行卡 支付宝锁时间
     */
    public static final Integer ONLINE_LOCK_TIME = 10;
    /**
     * 签约判断获取灵工当前签约协议
     */
    public static final Integer ESIGN_CUSTOMER_LOCK_TIME = 10;

}
