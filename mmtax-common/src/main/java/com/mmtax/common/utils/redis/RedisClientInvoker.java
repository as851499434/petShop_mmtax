package com.mmtax.common.utils.redis;

import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * @Auther: Moon
 * @Date: 2019/4/12 09:43
 * @Description:
 */
public interface RedisClientInvoker<T> {
    T invoke(Jedis jedis) throws IOException;
}
