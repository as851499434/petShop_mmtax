package com.mmtax.common.constant;

/**
 * 通用常量信息
 * 
 * @author mmtax
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";
    /**
     * 版本号
     */
    public static final String VERSION = "1.0";

    /**
     * 开关 - 开
     */
    public static final String SWITCH_ON = "Y";

    /**
     * 开关 - 关
     */
    public static final String SWITCH_OFF = "N";

    /**
     * 打款业务开启标志
     */
    public static final String PAYMENT_ALLOW = "payment_allow";
    /**
     * 注册个人账户时是否实名认证
     */
    public static final String VERIFY_REALNAME = "verify_realname";
    /**
     * 绑定银行卡是的3要素四要素验证
     */
    public static final String VERIFY_TYPE = "verify_type";
    /**
     * 绑定支付宝时是否鉴权
     */
    public static final String IS_VERIFY = "is_verify";
    /**
     * api打款时的验签标识
     */
    public static final String SIGN_FLAG = "sign_flag";
    /**
     * 公众号发送短信是否为测试
     */
    public static final String IS_SMS_TEST = "is_sms_test";
    /**
     * 打款redis所需key
     */
    public static final String PAYMENT_FLAG = "payment-";
    /**
     * 记录当前商户批次身份证打款金额redis所需key
     */
    public static final String IDCARDNO_AMOUNT = "idcardNoMap-";
    /**
     * 记录当前商户批次大额是否已补交redis所需key
     */
    public static final String HAVE_MAKEUP = "haveMakeUp-";
    /**
     * 记录当前商户批次大额是否已补交redis所需key
     */
    public static final String PERSON_MER_APPLY = "person_mer_apply-";
}
