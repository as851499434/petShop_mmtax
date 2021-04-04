package com.mmtax.common.utils.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.common.utils.StringUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 天瑞云工具类
 * @author Ljd
 * @date 2019/12/5
 */
public class TiRuiYunUtil {

    private static Logger logger = LoggerFactory.getLogger(TiRuiYunUtil.class);

    public static final String SEND_SMS_URL = "http://api.1cloudsp.com/api/v2/single_send";

    public static final String KEY = "gOXzUGEj8KwkHNRh";

    public static final String SECRET = "nlvdH4bgqdGLDTfopXtwhUGt80NpVfKV";

    public static final String SIGN_MMTAX = "【悟空薪享】";
    public static final String SIGN_QIYINGYUN = "【启盈云】";

    public static final String TEMPLATE_ID = "";

    public static JSONObject sendSms(String sign, String templateId, String mobile, String content) throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(SEND_SMS_URL);
        postMethod.getParams().setContentCharset("UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        NameValuePair[] data = {
                new NameValuePair("accesskey", KEY),
                new NameValuePair("secret", SECRET),
                new NameValuePair("sign", sign),
                new NameValuePair("templateId", templateId),
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", URLEncoder.encode(content, "utf-8"))
        };
        postMethod.setRequestBody(data);
        postMethod.setRequestHeader("Connection", "close");
        logger.info("天瑞云发送短信请求参数，url：{}，params：{}", SEND_SMS_URL, data.toString());
        int statusCode = httpClient.executeMethod(postMethod);
        String response = postMethod.getResponseBodyAsString();
        logger.info("天瑞云发送短信响应结果，statusCode：{}，body：{}", statusCode, response);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("statusCode", statusCode);
        if (StringUtils.isNotBlank(response)) {
            JSONObject parseObject = JSONObject.parseObject(response);
            jsonObject.put("response", parseObject);
        }
        logger.info("jsonObject:" + jsonObject.toJSONString());
        return jsonObject;
    }

    /**
     * 天瑞云发送短信 新接口
     * @param phoneNo
     * @param sign
     * @param templateId
     * @param content
     * @return
     */
    public static JSONObject sendSmsTRYNew(String phoneNo, String sign, String templateId, String content){
        Map<String, String> params = new HashMap<>();
        params.put("phoneNo", phoneNo);
        params.put("sign", sign);
        params.put("templateId", templateId);
        params.put("content", content);
        logger.info("天瑞云发送短信参数:" + JSON.toJSONString(params));
        JSONObject result = null;
        try {
            result = TiRuiYunUtil.sendSms(sign, templateId, phoneNo, content);
        } catch (Exception e) {
            logger.info("天瑞云发送短信失败：" + result);
        }
        logger.info("天瑞云发送短信结果:" + JSON.toJSONString(result));
        return result;
    }
}
