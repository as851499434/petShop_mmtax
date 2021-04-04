package com.mmtax.common.utils;

import java.util.Random;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/9 16:38
 */
public class MerchantIdUtil {

    public static String getMerchantId(){
        //获得当前时间的毫秒数
        long t = System.currentTimeMillis();
        //作为种子数传入到Random的构造器中
        Random rd = new Random(t);
        int code = rd.nextInt(10000000)+90000000;
        return String.valueOf(code);
    }
}
