package com.mmtax.common.utils;

import java.util.Random;

public class RandomNoUtil {

    /**
     * 获取固定位数的编码
     * @param digit
     * @return
     */
    public static String getRandomNo(int digit){
        //获得当前时间的毫秒数
        long t = System.currentTimeMillis();
        //作为种子数传入到Random的构造器中
        Random rd = new Random(t);
        StringBuilder boundStr = new StringBuilder("1");
        StringBuilder addStr = new StringBuilder("9");
        for(int i=0;i < digit - 1;i++){
            boundStr.append("0");
            addStr.append("0");
        }
        int bound = Integer.parseInt(boundStr.toString());
        int code = rd.nextInt(bound)+Integer.parseInt(addStr.toString());
        return String.valueOf(code);
    }
}
