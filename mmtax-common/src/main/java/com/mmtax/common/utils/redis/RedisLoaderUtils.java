package com.mmtax.common.utils.redis;

import com.mmtax.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.Properties;

/**
 * redis连接池工具类
 * @author  ocean
 */
public class RedisLoaderUtils {

    private static JedisPool jedisPool = null;

    private static String  ENV_TYPE_TEST = "test";

    private static String  ENV_TYPE_DEV = "dev";

    private static String  ENV_TYPE_PRO = "pro";


    private static final String CONFIG_FILE = "application";
    private static final String FILE_TYPE = ".properties";

    private static final String REDIS_CONFIG_FILE_START = "application-";

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLoaderUtils.class);

    //把redis连接对象放到本地线程中
    private static ThreadLocal<Jedis> local=new ThreadLocal<Jedis>();

    //单例
    private RedisLoaderUtils() {
    }


    public static void set(String key, String value) {
        try {
            value = StringUtils.isEmpty(value) ? "" : value;
            getJedis().set(key, value);
        } catch (Exception e) {
            LOGGER.error("Set key error : " + e);
        }finally {
            closeJedis();
        }
    }

    public static void set(String key, String value,int seconds) {
        try {
            value = StringUtils.isEmpty(value) ? "" : value;
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.expire(key,seconds);
        } catch (Exception e) {
            LOGGER.error("Set key error : " + e);
        }finally {
            closeJedis();
        }
    }

    /**
     * app token 设置
     * @param key
     * @param value
     * @param seconds
     */
    public static void setApp(String key, String value, long seconds) {
        try {
            value = StringUtils.isEmpty(value) ? "" : value;
            Jedis jedis = getJedis();
            jedis.set(key, value);
//            jedis.expire(key,seconds);
            jedis.pexpireAt(key, seconds);
        } catch (Exception e) {
            LOGGER.error("Set key error : " + e);
        }finally {
            closeJedis();
        }
    }

    public static String get(String key) {
        if(key==null){
            return null;
        }
        Jedis jedis = getJedis();
        String value = null;
        if (jedis.exists(key)) {
            value = jedis.get(key);
        }
        closeJedis();
        return value;
    }

    public static String get(String key,int seconds) {
        if(key==null){
            return null;
        }
        Jedis jedis = getJedis();
        String value = null;
        if (jedis.exists(key)) {
            value = jedis.get(key);
            //重新设置过期时间
            jedis.expire(key,seconds);
        }
        closeJedis();
        return value;
    }

    /**
     * 初始化Redis连接池
     */
    public static void initialPool() {
        try {
            Properties props = new Properties();
            //加载连接池配置文件
            InputStream inputStream = RedisLoaderUtils.class.getClassLoader()
                    .getResourceAsStream(CONFIG_FILE+FILE_TYPE);
            props.load(inputStream);
            if(ENV_TYPE_TEST.equals(props.getProperty("spring.profiles.active"))){
                InputStream inputStreamDev = RedisLoaderUtils.class.getClassLoader()
                        .getResourceAsStream(REDIS_CONFIG_FILE_START+ ENV_TYPE_TEST + FILE_TYPE);
                props.load(inputStreamDev);

            }else if(ENV_TYPE_DEV.equals(props.getProperty("spring.profiles.active"))){
                InputStream inputStreamDev = RedisLoaderUtils.class.getClassLoader()
                        .getResourceAsStream(REDIS_CONFIG_FILE_START + ENV_TYPE_DEV + FILE_TYPE);
                props.load(inputStreamDev);

            }else{
                InputStream inputStreamPro = RedisLoaderUtils.class.getClassLoader()
                        .getResourceAsStream(REDIS_CONFIG_FILE_START + ENV_TYPE_PRO + FILE_TYPE);
                props.load(inputStreamPro);

            }
            // 创建jedis池配置实例
            JedisPoolConfig config = new JedisPoolConfig();
            // 设置池配置项值
            config.setMaxTotal(Integer.valueOf(props.getProperty("spring.redis.jedis.pool.max-active")));
            config.setMaxIdle(Integer.valueOf(props.getProperty("spring.redis.jedis.pool.max-idle")));
            config.setMaxWaitMillis(Long.valueOf(props.getProperty("spring.redis.jedis.pool.max-wait")));
            config.setTestOnBorrow(Boolean.valueOf(props.getProperty("spring.redis.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(props.getProperty("spring.redis.testOnReturn")));
            // 根据配置实例化jedis池
            jedisPool = new JedisPool(config, props.getProperty("spring.redis.host"),
                                    Integer.valueOf(props.getProperty("spring.redis.port")),
                                    Integer.valueOf(props.getProperty("spring.redis.timeout")),
                    props.getProperty("spring.redis.password"),
                    Integer.valueOf(props.getProperty("spring.redis.database")));
        } catch (Exception e) {
            LOGGER.error("加载redis配置文件出错!",e);
            throw new RuntimeException("加载redis配置文件出错!");
        }
    }

    /**
     * 获得连接
     * @return Jedis
     */
    public static Jedis getJedis() {
        //Redis对象
        Jedis jedis =local.get();
        if(jedis==null){
            if (jedisPool == null) {
                initialPool();
            }
            jedis = jedisPool.getResource();
            local.set(jedis);
        }
        return jedis;
    }

    /**
     * 归还连接
     */
    public static void closeJedis(){
        //从本地线程中获取
        Jedis jedis =local.get();
        if(jedis!=null){
            jedis.close();
        }
        local.set(null);
    }

    //关闭池
    public static void closePool(){
        if(jedisPool!=null){
            jedisPool.close();
        }
    }

    public  static   JedisPool getJedisPool(){
        if (jedisPool == null) {
            initialPool();
        }
         return jedisPool;
    }


}
