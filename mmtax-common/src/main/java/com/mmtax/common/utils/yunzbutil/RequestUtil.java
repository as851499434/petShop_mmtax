package com.mmtax.common.utils.yunzbutil;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liyufeng
 */
public class RequestUtil {


    /**
     * 发送http请求
     *
     * @param url
     * @param reqData
     * @return
     * @throws Exception
     */
    public static String httpPost(String url, Object reqData) throws Exception {

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();

        HttpPost httpPost = new HttpPost(url);
        // 设置json请求头
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setHeader("Accept", "application/json");
        StringEntity entity = new StringEntity(
                new ObjectMapper().writeValueAsString(reqData), ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        int status = response.getStatusLine().getStatusCode();
        if (status == HttpStatus.SC_OK) {
            HttpEntity httpEntity = response.getEntity();
            String json = EntityUtils.toString(httpEntity);
            // 格式化输出
            ObjectMapper mapper = new ObjectMapper();
            Object obj = mapper.readValue(json, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }
        return null;

    }

    /**
     * 请求参数对
     *
     * @param obj
     * @return
     */
    public static String getPairs(Object obj) {
        // 获取对象键值
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                map.put(f.getName(), f.get(obj));
            }
            if (clazz.getSuperclass() != null) {
                for (Field f : clazz.getSuperclass().getDeclaredFields()) {
                    f.setAccessible(true);
                    map.put(f.getName(), f.get(obj));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return toPairs(map);
    }

    /**
     * 按参数名排序并生成参数对
     *
     * @param map
     * @return
     */
    private static String toPairs(Map<String, Object> map) {
        // 按key排序
        Map<String, Object> sorted = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(item -> sorted.put(item.getKey(), item.getValue()));
        // 拼接参数串，忽略空值
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, Object> entry : sorted.entrySet()) {
            if (!StringUtils.isEmpty(entry.getValue())) {
                // 参数名转换为snake case
                String key = CaseUtils.toSnakeCase(entry.getKey());
                str.append(key).append("=").append(entry.getValue()).append("&");
            }
        }
        return str.toString();
    }

    /**
     * 获取参数签名
     *
     * @param str
     * @return
     */
    public static String getSign(String str) throws Exception {
        return md5Encrypt(str);
    }

    /**
     * 返回结果签名验证
     *
     * @param respData
     * @return
     * @throws Exception
     */
    public static Boolean verify(String respData, String key) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(respData, Map.class);
        if (map.containsKey("sign")) {
            // 获取原始签名
            if (map.get("sign") == null) {
                return false;
            }
            String originSign = map.get("sign").toString();
            // 本地生成签名
            map.remove("sign");
            String respPairsWithKey = toPairs(map) + "key=" + key;
            String buildSign = getSign(respPairsWithKey);
            return originSign.equals(buildSign);
        }
        return null;
    }

    /**
     * 生成md5摘要
     *
     * @param str
     * @return
     * @throws Exception
     */
    private static String md5Encrypt(String str) throws Exception {
        String md5 = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
            bytes = digest.digest(bytes);
            md5 = toHexStr(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    private static String toHexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int var;
        for (byte b : bytes) {
            var = ((int) b) & 0xFF;
            if (var < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(var));
        }
        return sb.toString().toUpperCase();
    }
}
