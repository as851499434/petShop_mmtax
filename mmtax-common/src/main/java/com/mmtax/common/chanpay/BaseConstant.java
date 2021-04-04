package com.mmtax.common.chanpay;

/**
 * <p>
 * 定义请求的参数名称
 * </p>
 *
 * @author yanghta@chenjet.com
 * @version $Id: BaseConstant.java, v 0.1 2017-05-03 下午5:25:44
 */
public class BaseConstant {

    // 基础参数
    public static final String SERVICE = "Service";
    public static final String VERSION = "Version";
    public static final String PARTNER_ID = "PartnerId";
    // 日期
    public static final String TRADE_DATE = "TradeDate";
    public static final String TRADE_TIME = "TradeTime";
    public static final String INPUT_CHARSET = "InputCharset";
    public static final String SIGN = "Sign";
    public static final String SIGN_TYPE = "SignType";
    public static final String MEMO = "Memo";
    public static final String RETMSG = "RetMsg";

    public static final String MD5 = "MD5";
    public static final String RSA = "RSA";
    /**
     * 转账到户
     */
    public static final String NMG_BALANCE_TRANSFER = "nmg_balance_transfer";
    /**
     * 畅捷代收付service编码
     */
    public static final String CJT_DSF = "cjt_dsf";
    /**
     * 转账到账查询编码
     */
    public static final String NMG_QUERY_TRANSFER = "nmg_query_transfer";

    /**
     * 卡BIN信息code
     */
    public static final String CARD_BIN = "C00016";
    public static final String CARD_NAME = "CardName";
    /**
     * 商户余额
     */
    public static final String BALANCE = "C00005";

    public static final String BASICE = "200005900180";
    public static final String TEST = "200005900003";

    /**
     * 畅捷支付平台公钥
     */
    public static final String MERCHANT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPq3oXX5aFeBQGf3Ag/86zNu0VICXmkof85r+DDL46w3vHcTnkEWVbp9DaDurcF7DMctzJngO0u9OG1cb4mn+Pn/uNC1fp7S4JH4xtwST6jFgHtXcTG9uewWFYWKw/8b3zf4fXyRuI/2ekeLSstftqnMQdenVP7XCxMuEnnmM1RwIDAQAB";
    //生产环境公钥


    /**
     * 商户私钥
     */
//	public static final String MERCHANT_PRIVATE_KEY= "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAN+5w7Fp94BRe+F02fKYjScY4f2AaeCrz4izNaVszO+pXQuoQlqdc3BydJHzWotWgx1Wmic0AM/1BBpcGLMAm1fgNDbGkF1bT2IKpp5qrfo5fQ8lnSUVJOinx+wjyKPSx00srymoQsMlVrcE4Jdz8OKYBXee9hHr9qe+69Z/176rAgMBAAECgYEAyHfIJlzxbYWWH3t5H6waLwJY3bIHLU5GdBNA6q4HJGj5+swWSOP04LluQqlwZJJsgglrr90lLTI7LvN/HkZY6kQPx2YygnZVBzLeoNG/gJp+E05FOdSXge+L6KNz4zsVdnbL3/qNUsUxibYNIfnNBkp/5yoBw9wco+RbApeWieECQQDxnS0pijwMcer+XapQ6B5ryo0LeLDEhgEWD3GFUCSw6i6lS5aNTkhb/ktm0lS8ekS/37VSzYrxA4YM6zn0XhmvAkEA7QvsahSnFb33WoTjODMud0zbbtWDq2CJewbBUD5mjMUizMewTXLGEhfjkmCrjjpenjXmGxXKj1LCReCQ4p91xQJBALsCs0Ah1NS1H/R+PyOcZl98SauFYRzbs9+FWJKdM6kTucMLPJ5m5y79JxOQ3TF9WdVBrDFJEq/10D4Tm6n/Y9UCQQCMGqMF4+UJfbkIAEkH+4A4NO3xKTNkhG9djpWNZwRXqWCzJkd2zyJwqTvxVT+wlaE842B4m72YTnLSuDMKLvN9AkEAvVbF8Sc/QTS9Is9EXB2oHzJJPxXzF6Y94U48hGOSifqDOOe0KAC0DGI2PKk/Rrpl+3StZOELHxdcrIiGKhEFwg==";
//	public static final String MERCHANT_PRIVATE_KEY= "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANfDezBqEwkrbldsOZVFT9VfgflAB/I45GxI8JgAeV5RNpuEI9wKG3T6M6HMlGllHhWyeo2SDbtRRb0cjt62KAQUlNwvytk0RPL622m3cJxXuZrse/LyDFbzFeE43oqdOQi1Vkev1kzVbZrfF6/Tw7pc3ffcKXSbY0Uw33RDaUmrAgMBAAECgYBH33c+xmb26//CoWuBHOieFEWQiNLczjTRGW8Dw0yqrXT6Uwc6+ibxTZM7d5GMlkFyStTmKdvnJQiNGWFCMP2c2cPFI0SZC7nJVC5SxJqzVNdLU4wpMBTYVDmLWSXvBQnYoKu2YmAdBnd9bV6dd3Fg5IPaQTXkgKZ3IT6NH3uWQQJBAO2V+ZtMnwqQsjOYqLMQ95posKpSWUY1UhdRCqzt4rXA+kspaTYyyQ4V1peLRJYImvUawtri6/kuqnVQMBVbV4sCQQDofIX50wp3uNxjfwpL5kCK3JA2gy6DHRRlK0wJmOqpxBgwRB83P97W5dV12xHxjsrz5YJ1GIvFGpAEoX8j2xphAkAl7GOMW32jdRdMzxhWhOXQ+tcASObBIy+4UxJOKiae9m+9YZ3OreqT2f6H8A4BmNwLNbtWrxQVkVJaKXOTZSIjAkBgZpoLQGY7xIymORxk4oHq0pB9+iFEPr+hP7XU2PKdGHHLOcQT3ckiJMVBjGhBWKtxPL6+nUH/pgcH1Itqq8ShAkB5o8RpYUJWczDtmv+fhFnmmq1l/AzPzEPs9Fr0uEGzRHiTKYtc2qLC5LMHfPWBQGXq+6gTalvAZqSZ7xnONPYh";

    public static final String MERCHANT_PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMU8d5z4mbdNyZbvU8b1MiafPpR11Oxu7rxGj2l40BrdHdqwSM1qs/oU4+KWWDlwbJJks4RySjywKhStnvuenxn66a1jG7e3Fl6rhj4cs7IiJDgTk0mx3I0IeSbo/Uht1DdAyGaHPAYw9iYJHiy8a+U7JSvVP9U7vAdcObQaq4HDAgMBAAECgYEAmum9QOr5QMG1/fiHopLfCvq0WkKaNAGFPRdmeH7NZOwbELK4265dIaTawBM5xBOLEwhI/1QM/tGWm5sqbZLOfmX/YupMWY1Eeo2lg+4jABQjoapQkOmkjIc2/nDUpiwNTgdvkXzW206PfBZfF/M4ZrDzuNR6Psx78t0N85tS4kECQQDrFpOrCkQjE3PQh8eNI1tsmN78EO3JbwKs77qawx35ETNNiYEPXj4DXgT3Qi2724agkA3Qj45N9EJrM2OAl6ORAkEA1sfs+uJfyTdtYuLsMWm79iuAfcLHM+MCzd4KvRQawbFUZV3yEZoVYLySJ8GbEMFq4aFVPeWlJBiPabb3V9l+EwJBAJyc8ctkj3f+oY0up5YzE9kJDqRGcuNa/SFQo7QugG1JNDBXho9CbXW40P4YLHeWC94zmcBzoo30R7s7BPLKEsECQBMX3sdGzIEUvlKmHZ0e6yitIVUMtay1J5CphobcfKxvWTNRsK1D51acLNn/6gsJRrkcIHnKKm4VCNq6+y4CFaUCQQCRuEYZfJPepR5sjLefUkXtanW47b+MvU5P2jeAoBqjpW4dX3yZf/fVII6nQiI38TCg/rwWepbdJzE7vqQ4RFf0";

    public static final String MERCHANT_PRIVATE_KEY_TEMP = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAOTmPPd4G4I4S2rH5YIs9oKhdLa/stOL/qPGl7hb6fpSaxQDEU9hCr/dJp4XnT5619KLSMmNf1+C+I8ajkG8wnfW1zr7ho1g2zPa6vWl3FnhZo0H7etfmhI71bAJ1zMKIIevYpom1UuXXrRxWl99edg7iK04fEr8QEn5CByYuvfVAgMBAAECgYEA2pM0wobRld6VvaiWc2DmIEV2ww94/uMezFtWA7jZS6yqDVkEjY04C5BtUOdxwvjg4G8amURhzd2t5lDQaNuLwb8u5wNkVZUBmNnJy0mCfohI+o4RGJ313edeznkiK/bu/YduMTws3P4L1y6BBBiTdr0ELhWohKosHRlKRMn9U50CQQD6xwrDqH+5CuaiUF6uH2U3CXfudbogbXfcTSO9EIS5m0kHBosOpwMvm98nZTpq80yPjnFiHfmHAh8NABme/purAkEA6aqO6JR+mB77k860dNH+d7seu59DkI+PyRUw5LwW9lyW6aom2grtHh75p185+CV+E5EKgAgiUF7E+NJHrHU6fwJBANl8p7w6NNLBPnYEZ6hw9/G8V3PKS+p1pZ68k8MTQVhK8RGTviOt+lhewTDEXN6GNJ7HtVOXlWFeGJnIvW+jdlMCQQCWFRTBUk+ViaDxKP1DY3XclXWB8SPGgE9MSq4i3KSrDIFqB/34Yrg2dpLH7CQhvIMkMi7aWV8g3B4L65RZstolAkEA6Q9PCkK6UkMFN3vZeAZR27OvTuXJGly5xSg3xVFPXVmGOFZyavzQGYh1IFiqub8ymOO/Hw5zKfvujOwH9bNJQA==";

    /**
     * 编码类型
     */
    public static final String CHARSET = "UTF-8";
    public final static String GATEWAY_URL = "https://pay.chanpay.com/mag-unify/gateway/receiveOrder.do";
    public final static String BATCH_FILE_GATEWAY_URL = "https:/pay.chanpay.com/mag-unify/gateway/batchOrder.do";
}
