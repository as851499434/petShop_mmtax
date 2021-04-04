package com.mmtax.common.utils.redis;

/**
 * @Auther: Moon
 * @Date: 2019/4/12 09:28
 * @Description:
 */

import com.alibaba.fastjson.JSON;

import java.nio.charset.Charset;

/**
 * json  Serializer util
 */
public class JsonSerializer {
    public static final String UTF_8 = "UTF-8";

    /**
     * @param obj
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T obj) {
        return JSON.toJSONString(obj).getBytes(Charset.forName(UTF_8));
    }

    /**
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(data, clazz);
    }
}
