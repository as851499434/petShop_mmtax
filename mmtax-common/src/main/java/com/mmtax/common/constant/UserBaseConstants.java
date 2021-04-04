package com.mmtax.common.constant;

import java.math.BigDecimal;

/**
 * 商户通用常量类
 * @author yuanligang
 * @date 2019/7/10
 */
public class UserBaseConstants {


    /** 税率*/
    public final static BigDecimal TAX_RATE = new BigDecimal("0.06");


    /** 初始化金额  */
    public final static BigDecimal INIT_AMOUNT = BigDecimal.ZERO;

    /**
     * 开票状态 -- 已开发票
     */
    public static final int INVOICED = 1;

    /**
     * 开票状态 -- 未开发票
     */
    public static final int UNINVOICED = 0;


    /**
     * 充值成功标识
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 充值失败标识
     */
    public static final String FAIL = "FAIL";


    /** 计算精度*/
    public final static int SCALE = 2;

    /** 初始化密码 */
    public final static String initPassword = "123456";

    /**
     * 冲补金额
     */
    public final static BigDecimal complementAmount = BigDecimal.ZERO;

    /**
     * 发票未作废
     */
    public final static Integer NOT_ABOLISHED = 0;

    /**
     * 发票已作废
     */
    public final static Integer Abandoned = 1;

    /**
     * 单张发票开票金额 10W
     */
    public final static BigDecimal INVOICE_MIN_AMOUNT = BigDecimal.valueOf(100000);

    /**
     * 审核通过
     */
    public final static Integer ADOPT = 1;

    /**
     * 驳回
     */
    public final static Integer REJECT = 0;

}
