package com.mmtax.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @Author：YH
 * @Date：2020/9/9 18:34
 */
public class RegexUtil {

    public static final String REGEX_DATE= "^\\d{4}\\-\\d{2}\\-\\d{2} 00:00:00$";

    public static final String REGEX_BIGDECIMAL="^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";

    public static final String REGEX_Number ="^(([1-9]{1}\\d*))?$";

    public static final String REGEX_NAME = "^[\\u4e00-\\u9fa5·•]{2,16}$";

    public static final String REGEX_BANKNO = "^[1-9]{1}\\d{12,18}$";

    public static final String REGEX_PHONELEGAL =
                     "^((13[0-9])|(14[5-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$";

    public static final String REGEX_PHONE = "^[1]\\d{10}$";

    public static final String REGEX_ID_CARD_NO = "^\\d{17}([0-9]|X)$";

    public static final String REGEX_NAME_TWO = "^[\\u4e00-\\u9fa5·•]{1,40}$";

    public static final String REGEX_BANKLINKNO = "^.{1,40}$";

    /**
     * 匹配 日期格式 (YYYY-MM-)
     * @param in
     * @return
     */
    public static boolean regexDate(String in){
        Pattern pattern = Pattern.compile(REGEX_DATE);
        return pattern.matcher(in).matches();
    }

    /**
     * 匹配 正整数.两位小数
     * @param in
     */
    public static boolean regexBigDecimal(String in) {
        Pattern pattern = Pattern.compile(REGEX_BIGDECIMAL);
        return pattern.matcher(in).matches();
    }

    /**
     * 匹配 正整数
     * @param in
     * @return
     */
    public static boolean regexNumber(String in) {
        Pattern pattern = Pattern.compile(REGEX_Number);
        return pattern.matcher(in).matches();
    }

    /**
     * 正则: 姓名，2-16个汉字
     */
    public static boolean regexName(String in) {
        Pattern pattern = Pattern.compile(REGEX_NAME);
        return pattern.matcher(in).matches();
    }

    /**
     * 正则: 姓名，1-40个汉字
     */
    public static boolean regexNameTwo(String in) {
        Pattern pattern = Pattern.compile(REGEX_NAME_TWO);
        return pattern.matcher(in).matches();
    }

    /**
     * 正则: 编号，1-40个字符
     */
    public static boolean regexBankLineNo(String in) {
        Pattern pattern = Pattern.compile(REGEX_BANKLINKNO);
        return pattern.matcher(in).matches();
    }

    /**
     * 正则: 银行卡号，13-19位数字
     */
    public static boolean regexBankNo(String in){
        Pattern pattern = Pattern.compile(REGEX_BANKNO);
        return pattern.matcher(in).matches();
    }

    /**
     * 正则: 手机号码正则(较为严格)
     */
    public static boolean regexPhoneLegalNo(String in) throws PatternSyntaxException {
        Pattern pattern = Pattern.compile(REGEX_PHONELEGAL);
        return pattern.matcher(in).matches();
    }

    /**
     *  正则：手机号（简单）, 1字头＋10位数字即可.
     */
    public static boolean regexPhoneNo(String in) {
        Pattern pattern = Pattern.compile(REGEX_PHONE);
        return pattern.matcher(in).matches();
    }

    /**
     *  正则：身份证（简单）, 17位数字+1位数字或字母即可.
     */
    public static boolean regexIdCardNo(String in) {
        Pattern pattern = Pattern.compile(REGEX_ID_CARD_NO);
        return pattern.matcher(in).matches();
    }

}
