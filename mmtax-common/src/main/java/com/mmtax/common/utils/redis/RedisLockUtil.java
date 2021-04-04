package com.mmtax.common.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * redis 锁工具类
 * @author Ljd
 * @date 2020/7/31
 */
public class RedisLockUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisLockUtil.class);

    /**
     * 获取 redis 分布式锁
     * @param key 锁
     * @param value 请求标识
     * @param expireTime 超期时间(秒)
     */
    public static void getRedisLock(String key, String value, Integer expireTime) {
        boolean result = false;
        try {
            while (!result) {
                result = RedisUtil.tryGetDistributedLock(key, value, expireTime);
                if (!result) {
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            logger.error("redis lock fail key:{}", key);
        }
    }
}
