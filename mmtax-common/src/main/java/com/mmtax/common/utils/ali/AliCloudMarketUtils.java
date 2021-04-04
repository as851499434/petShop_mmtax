package com.mmtax.common.utils.ali;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.common.exception.BusinessException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 阿里云工具类
 * @author Ljd
 * @date 2020/4/24
 */
public class AliCloudMarketUtils {
    private static Logger logger = LoggerFactory.getLogger(AliCloudMarketUtils.class);
    /**
     * AppCode
     */
    private static final String APP_CODE = "dc270f8241914742b6a0aba28da3d03d";

    /**
     * 四要素验证
     * @param name 姓名
     * @param bankAccNo 卡号
     * @param idCardNo 身份证号
     * @param bindMobile 手机号
     */
    public static void checkBank4c(String name, String idCardNo, String bankAccNo, String bindMobile){
        JSONObject resultInfo;
        try {
            resultInfo = AliCloudMarketUtils.checkBank4cTYan(idCardNo, name, bankAccNo, bindMobile);
            if (!resultInfo.getString("code").equals("200")) {
                throw new BusinessException("四要素验证错误");
            }
            JSONObject data = resultInfo.getJSONObject("data");
            if (!"0".equals(data.getString("result"))) {
                throw new BusinessException("四要素验证错误");
            }
        } catch (IOException e) {
            throw new BusinessException("四要素验证失败");
        }
    }

    /**
     * 银行卡四要素验证 天眼数据
     * @param idNo 身份证号
     * @param name 姓名
     * @param cardNo 银行卡号
     * @param phone 手机号
     * @return 验证结果
     * @throws IOException
     */
    public static JSONObject checkBank4cTYan(String idNo, String name, String cardNo, String phone)
            throws IOException {
        String url = "https://bankcard4c.shumaidata.com/bankcard4c";

        Map<String, String> params = new HashMap<>();
        params.put("idcard", idNo);
        params.put("name", name);
        params.put("bankcard", cardNo);
        params.put("mobile",phone);
        logger.info("checkBank4cTianYan 4要素参数：{}", JSON.toJSONString(params));
        String result = get(APP_CODE, url, params);
        logger.info("checkBank4cTianYan 4要素返回结果：{}", result);
        return JSON.parseObject(result);
    }

    /**
     * 三要素验证
     * @param name 姓名
     * @param bankAccNo 卡号
     * @param idCardNo 身份证号
     */
    public static void checkBank3c(String name, String idCardNo, String bankAccNo){
        JSONObject resultInfo;
        try {
            resultInfo = AliCloudMarketUtils.checkBank3cTYan(idCardNo, name, bankAccNo);
            if (!resultInfo.getString("code").equals("200")) {
                throw new BusinessException("三要素验证错误");
            }
            JSONObject data = resultInfo.getJSONObject("data");
            if (!"0".equals(data.getString("result"))) {
                throw new BusinessException("三要素验证错误");
            }
        } catch (IOException e) {
            throw new BusinessException("三要素验证错误");
        }
    }

    /**
     * 银行卡三要素验证 天眼数据
     * @param idNo 身份证号
     * @param name 姓名
     * @param cardNo 银行卡 号
     * @return 验证结果
     * @throws IOException
     */
    public static JSONObject checkBank3cTYan(String idNo, String name, String cardNo)
            throws IOException {
        String url = "https://bankcard3c.shumaidata.com/bankcard3c";

        Map<String, String> params = new HashMap<>();
        params.put("idcard", idNo);
        params.put("name", name);
        params.put("bankcard", cardNo);
        logger.info("checkBank3cTYan 3要素参数：{}", JSON.toJSONString(params));
        String result = get(APP_CODE, url, params);
        logger.info("checkBank3cTYan 3要素参数：{},checkBank3cTYan 3要素返回结果：{}",JSON.toJSONString(params),
                result);
        return JSON.parseObject(result);
    }

    public static String get(String appCode, String url, Map<String, String> params) throws IOException {
        url = url + buildRequestUrl(params);
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE "
                + appCode).build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        return result;
    }

    public static String buildRequestUrl(Map<String, String> params) {
        StringBuilder url = new StringBuilder("?");
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            url.append(key).append("=").append(params.get(key)).append("&");
        }
        return url.toString().substring(0, url.length() - 1);
    }
}
