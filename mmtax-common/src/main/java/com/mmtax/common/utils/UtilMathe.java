package com.mmtax.common.utils;

import java.math.BigDecimal;

/**
 * 数字工具类
 * @author Ljd
 * @date 2020/7/29
 */
public class UtilMathe {

    /**
     * String 转 BigDecimal
     * @param numberStr 字符串数字
     * @return BigDecimal
     */
    public static BigDecimal strToBigDecimal(String numberStr) {
        if (StringUtils.isEmpty(numberStr)) {
            return null;
        }
        return new BigDecimal(numberStr.trim());
    }
}
