package com.mmtax.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PatternVerifyUtil {
    /**
     *  正则：手机号（简单）, 1字头＋10位数字即可.
     * @param in
     * @return
     */
    public static boolean validateMobilePhone(String in){
        Pattern pattern = Pattern.compile("^[1]\\d{10}$");
        return pattern.matcher(in).matches();
    }

    /**
     *  正则：身份证（简单）, 17位数字+1位数字或字母即可.
     * @param in
     * @return
     */
    public static boolean validateIdCard(String in){
        Pattern pattern = Pattern.compile("^\\d{17}([0-9]|X)$");
        return pattern.matcher(in).matches();
    }

    /**
     *  正则：验证全是数字.
     * @param in
     * @return
     */
    public static boolean validateNumber(String in){
        Pattern pattern = Pattern.compile("^\\d{6}$");
        return pattern.matcher(in).matches();
    }

    /**
     * 校验是否中国的手机号
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isChinaPhoneLegal(String str) {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
