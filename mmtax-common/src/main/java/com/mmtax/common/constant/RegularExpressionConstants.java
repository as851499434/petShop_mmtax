package com.mmtax.common.constant;

public class RegularExpressionConstants {

    /**
     * 正则表达式 将开户银行及账号中的 银行名称 取出
     */
    public static final String  BANK_NAME= "[(\\\\u4e00-\\\\u9fa5)]";
    /**
     * 正则表达式 将开户银行及账号中的 银行账号 取出
     */
    public static final String  BANK_NUMBER= "\\D";

}
