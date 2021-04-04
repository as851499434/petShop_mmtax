package com.mmtax.common.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密 工具类
 * @author yuanligang
 * @date 2019/7/10
 */
public class PasswordUtil {

    /**
     * 加密方式
     */
    private  static final String algorithmName = "md5";
    /**
     * 定义迭代
     */
    private static final  int hashIterations = 2;
    /**
     * 随机盐生成
     * @return 盐
     */
    public static String randomSalt() {
       return new SecureRandomNumberGenerator().nextBytes().toHex();
    }

    /**
     * 加密
     * @param  password
     * @param  merchantCode
     * @param  salt
     * @return 密码
     *
     */
    public static String password(String password, String merchantCode, String salt) {
        String newPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(merchantCode + salt), hashIterations).toHex();
        return  newPassword;
    }

}
