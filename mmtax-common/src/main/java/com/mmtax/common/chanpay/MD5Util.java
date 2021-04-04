package com.mmtax.common.chanpay;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

public class MD5Util {
    /**
     * 生成含有随机盐的密码
     */
    public static String generate(String password, String salt) {
        password = md5Hex(password + salt);
        return password;
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    public static String md5Hex(String src) {
        System.out.println(src);
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String password = generate("{\"appId\":\"069e1d236bb941f188b6af66549210ab\",\"areaCode\":\"中国\"}", "31246ea36f7744a69d45e1385a02f3ae");
        String sdf = md5Hex("{\"appId\":\"069e1d236bb941f188b6af66549210ab\",\"areaCode\":\"中国\"}31246ea36f7744a69d45e1385a02f3ae");
        System.out.println(password + sdf);

    }
}  