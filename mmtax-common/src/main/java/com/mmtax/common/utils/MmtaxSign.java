package com.mmtax.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.common.chanpay.BaseConstant;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/16 20:48
 */
public class MmtaxSign {

    public static String addSignByJsonStr(String content, String appKey) {
        Map map = JSON.parseObject(content, Map.class);
        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        String sign = MmtaxSign.signByMap(appKey, treeMap, BaseConstant.CHARSET);
        map.put("sign", sign);
        JSONObject jsonObject = new JSONObject(map);
        content = jsonObject.toJSONString();
        return content;
    }

    /**
     * 签名
     *
     * @param map
     * @return
     */
    public static String signByMap(String channelKey, TreeMap<String, Object> map, String charsetName) {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator<String> iterator = map.keySet().iterator();
            sb.append(channelKey);
            while (iterator.hasNext()) {
                Object key = iterator.next();
                Object valueObj = map.get(key);
                if (valueObj != null) {
                    //并将获取的值进行拼接
                    String value = valueObj.toString();
                    sb.append(value);
                }
            }
            String signData = md5Encrypt(sb.toString(), charsetName);
            return signData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String md5Encrypt(String message, String charsetName) {
        if (StringUtils.isBlank(charsetName)) {
            charsetName = "UTF-8";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] md5Bytes = new byte[0];
        try {
            md5Bytes = md5.digest(message.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
//            log.error("签名数据编码格式转换出错", e);
            return "";
        }
        String hexValue = ByteUtil.toHexString(md5Bytes);
        return hexValue;

    }

}
