package com.mmtax.web.controller.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.common.chanpay.BaseConstant;
import com.mmtax.common.utils.MmtaxSign;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ljd
 * API签名测试类
 * 模拟了api接口中生成签名的方法
 */
public class SignTest {
    public static void main(String[] args) {
        System.out.println("getJsonString:" + getJsonString());
    }
    public static void testSign() {
        String url = "http://localhost:80/payment/notify";
        String appkey = "8795ca9a55b842b6ac1fa9a77c976081";
        Map<String, String> map = new HashMap<>();
        map.put("orderId", "a027841a6cb6491884dea60c825042f9");
        map.put("merchantNo", "97363908");
        map.put("requireTime", "2020-11-06 15:17:16");
        map.put("bankCardNo", "6212263602024562290");
        map.put("idCardNo", "441723199008025234");
        map.put("mobileNo", "18138545110");
        map.put("name", "曾大哥");
        map.put("paymentChannel", "BANK");
        map.put("version", "1.0");
        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        String sign = MmtaxSign.signByMap(appkey, treeMap, "UTF-8");
        System.out.println("sign:" + sign);
        map.put("sign", sign);
        map.put("signType", "UTF-8");
        map.put("desKey", "aeef6ada1a9c455cbcecbd053177adc4");
    }

    public static String getJsonString() {
        String appkey = "8795ca9a55b842b6ac1fa9a77c976081";
        String content = "{\"amount\":\"6266.37\",\"code\":\"SUCCESS\",\"merchantNo\":\"99405095\"," +
                "\"message\":\"SUCCESS\",\"settleTime\":\"2020-06-05 13:43:02\",\"tradeNo\":" +
                "\"T20200605134052541353983\"}";
        Map map = JSON.parseObject(content, Map.class);
        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        String sign = MmtaxSign.signByMap(appkey, treeMap, BaseConstant.CHARSET);
        map.put("sign", sign);
        JSONObject jsonObject = new JSONObject(map);
        content = jsonObject.toJSONString();
        return content;
    }
}
