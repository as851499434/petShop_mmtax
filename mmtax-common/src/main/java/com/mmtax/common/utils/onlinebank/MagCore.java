package com.mmtax.common.utils.onlinebank;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/* *
 * 工具类
 *
 */

public class MagCore {
    
    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray
     *            签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    public static Map<String, String> paraFilter2(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (Map.Entry<String, String> mapEntry : sArray.entrySet()) {
        	String value = mapEntry.getValue();
        	String key = mapEntry.getKey();
            if (value == null || value.equals("")  || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    
    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @param encode 是否需要urlEncode
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params, boolean encode) {

        //        params = paraFilter(params);

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (encode) {
                try {
                    value = URLEncoder.encode(value, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * 生成MD5签名结果
     *
     * @param sPara
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestByMD5(Map<String, String> sPara, String key,
                                           String inputCharset) throws Exception {
        String prestr = createLinkString(sPara, false); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        mysign = OnlineMD5.sign(prestr, key, inputCharset);
        return mysign;
    }
    /**
     * 生成TWSIGN签名结果
     *
     * @param sPara
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestByTWSIGN(Map<String, String> sPara,
                                           String inputCharset,String keyStoreName) throws Exception {
        // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String prestr = createLinkString(sPara, false);
        return Twsign.sign(prestr, keyStoreName, inputCharset);
    }

    /**
     * 生成RSA签名结果
     *
     * @param sPara
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestByRSA(Map<String, String> sPara, String privateKey,
                                           String inputCharset) throws Exception {
        String prestr = createLinkString(sPara, false); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        mysign = OnlineRSA.sign(prestr, privateKey, inputCharset);
        return mysign;
    }
    /**
     * 生成要请求给钱包的参数数组
     *
     * @param sParaTemp         请求前的参数数组
     * @return                  要请求的参数数组
     */
    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp,
                                                        String signType, String key,String service,
                                                        String inputCharset) throws Exception {
        // 除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        // 生成签名结果
        String mysign = "";
        if ("MD5".equalsIgnoreCase(signType)) {
            mysign = buildRequestByMD5(sPara, key, inputCharset);
        } else if("RSA".equalsIgnoreCase(signType)){
            mysign = buildRequestByRSA(sPara, key, inputCharset);
        } 
//        else if("TWSIGN".equalsIgnoreCase(signType)) {
//        	mysign = buildRequestByTWSIGN(sPara, inputCharset);
//        }

        // 签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", signType);
        sPara.put("service", service);
        return sPara;
    }

    


}
