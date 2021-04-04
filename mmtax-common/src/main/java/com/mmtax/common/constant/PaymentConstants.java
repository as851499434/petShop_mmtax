package com.mmtax.common.constant;

import java.math.BigDecimal;

/**
 * 打款常量
 * @author Ljd
 * @date 2020/6/2
 */
public class PaymentConstants {

    /**
     * 打款单笔限额
     */
    public static final BigDecimal SINGLE_MAX_QUOTA = BigDecimal.valueOf(49999);
    /**
     * 打款单笔限额
     */
    public static final BigDecimal BIG_RATE_LIMIT = BigDecimal.valueOf(100000);

    /**
     * 打款单月限额
     */
    public static final BigDecimal SINGLE_MONTH_LIMIT = BigDecimal.valueOf(400000);

    /**
     * 预警限额
     */
    public static final BigDecimal WARN_LIMIT = BigDecimal.valueOf(100000);

}
