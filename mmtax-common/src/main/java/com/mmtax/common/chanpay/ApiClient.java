package com.mmtax.common.chanpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Description:
 *
 * @author yingjie.wang
 * @since 17/4/14 上午11:28
 */
public class ApiClient {

    private static final String PARAM_SIGN_MARK = "hmac";
    private static final String PARAM_MERCHANT_NO = "appKey";
    private static final String PARAM_DATA = "data";
    private static final String RESPONSE_PARAM_CODE = "code";
    private static final String RESPONSE_PARAM_MESSAGE = "message";
    private static final String SUCCESS_CODE = "200";
    private static final String REMOTE_SERVER_UPLOAD_PARAM = "file";
    private static final int API_BUFFER_SIZE = 10*1024;
    private static final int API_POST_TIME_OUT = 600*1000;
    private static final int API_DOWNLOAD_TIME_OUT = 30*60*1000;
    private static final int API_UPLOAD_TIME_OUT = 5*60*1000;

    /**
     * post请求, 超时时间默认为: API_POST_TIME_OUT
     * @param url
     * @param apiRequest
     * @return
     */
    public static ApiResponse post(String url, ApiRequest apiRequest) {
        return post(url, apiRequest, API_POST_TIME_OUT);
    }

    /**
     * post请求(可指定超时时间)
     * @param url
     * @param apiRequest
     * @param timeoutMillis
     *           超时时间,单位:毫秒
     * @return
     */
    public static ApiResponse post(String url, ApiRequest apiRequest, int timeoutMillis) {
        /** 参数校验 */
        checkParams(url, apiRequest, timeoutMillis);
        /** 组装参数 */
        Map<String, String> postParamMap = buildHttpRequestParamMap(apiRequest);
//        System.out.println("postParamMap:" + postParamMap.toString());
        /** 发送post请求 */
        String responseText = HttpClient4Utils.sendHttpRequest(url, postParamMap, HttpClient4Utils.DEFAULT_CHARSET, true, timeoutMillis);

//        System.out.println("responseParamMap:" + postParamMap.toString());
        /** 解析响应串,并返回 */
        return parseResponse(responseText, apiRequest);
    }

    /**
     * 文件下载至filePath, 超时时间时间默认为: API_DOWNLOAD_TIME_OUT
     * @param url
     * @param apiRequest
     * @param filePath
     */
    public static void downloadFile(String url, ApiRequest apiRequest, String filePath) {
        downloadFile(url, apiRequest, filePath, API_DOWNLOAD_TIME_OUT);
    }

    /**
     * 文件下载至filePath, 可指定超时时间
     * @param url
     * @param apiRequest
     * @param filePath
     * @param timeoutMillis
     *          超时时间,单位:毫秒
     * @return
     */
    public static void downloadFile(String url, ApiRequest apiRequest, String filePath, int timeoutMillis) {
        /** 参数校验 */
        checkParams(url, apiRequest, timeoutMillis);
        /** 组装参数 */
        Map<String, String> postParamMap = buildHttpRequestParamMap(apiRequest);
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            /** 根据path创建文件 */
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            /** 发起请求获取文件流 */
//            InputStream responseStream = HttpClient4Utils.sendHttpRequestStream(url, postParamMap, HttpClient4Utils.DEFAULT_CHARSET, true, timeoutMillis);
            /** 写文件 */
//            fileOutputStream = new FileOutputStream(file);
//            bufferedInputStream = new BufferedInputStream(responseStream);
//            byte[] by = new byte[API_BUFFER_SIZE];
//            while (true) {
//                int i = bufferedInputStream.read(by);
//                if (i == -1) {
//                    break;
//                }
//                fileOutputStream.write(by, 0, i);
//            }
            String content = HttpClient4Utils.sendHttpRequest(url, postParamMap, HttpClient4Utils.DEFAULT_CHARSET, true, timeoutMillis);
            InputStream input = new ByteArrayInputStream(content.getBytes());// 数据输入流处理
            OutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));
            IOUtils.copy(input, out);
            out.flush();
        } catch(Exception e) {
            throw new RuntimeException(e);
        } finally {
            CloseUtils.close(fileOutputStream, bufferedInputStream);
        }
    }

    /**
     * 将filePath指定的文件上传至服务器, 默认超时时间为: API_UPLOAD_TIME_OUT
     * @param baseUrl
     * @param apiRequest
     * @param filePath
     * @return
     */
    public static ApiResponse uploadFile(String baseUrl, ApiRequest apiRequest, String filePath) {
        return uploadFile(baseUrl, apiRequest, filePath, API_UPLOAD_TIME_OUT);
    }

    /**
     * 将filePath指定的文件上传至服务器, 可指定超时时间
     * @param baseUrl
     * @param apiRequest
     * @param filePath
     * @param timeoutMillis
     *          超时时间,单位:毫秒
     * @return
     */
    public static ApiResponse uploadFile(String baseUrl, ApiRequest apiRequest, String filePath, int timeoutMillis) {
        /** 参数校验 */
        checkParams(baseUrl, apiRequest, timeoutMillis);
        /** 组装参数 */
        Map<String, String> postParamMap = buildHttpRequestParamMap(apiRequest);
        try {
            File file = new File(filePath);
            if (!file.exists() || file.isDirectory()) {
                throw new IllegalArgumentException("filePath is illegal.");
            }
            /** 组装uploadUrl,将相关参数添加至baseUrl中 */
            String uploadUrl = constructUploadUrl(baseUrl, postParamMap);
            /** 上传文件 */
            String responseText = HttpClient4Utils.sendUploadFileHttpRequest(uploadUrl, file, REMOTE_SERVER_UPLOAD_PARAM,
                                                    HttpClient4Utils.DEFAULT_CHARSET, timeoutMillis);
            /** 响应数据解析 */
            if (responseText.indexOf(PARAM_DATA) == -1) {
                throw new RuntimeException("upload file failed, response text:[" + responseText + "]");
            }
            return parseResponse(responseText, apiRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> buildHttpRequestParamMap( ApiRequest apiRequest) {
        /** 生成加密data */
        String data = constructData(apiRequest);
        Map<String, String> httpRequestParamMap = new HashMap<String, String>();
        httpRequestParamMap.put(PARAM_DATA, data);
        httpRequestParamMap.put(PARAM_MERCHANT_NO, apiRequest.getMerchantNo());
        return httpRequestParamMap;
    }

    /**
     * 参数校验
     * @param url
     * @param apiRequest
     * @param timeoutMillis
     */
    private static void checkParams(String url, ApiRequest apiRequest, int timeoutMillis) {
        if (timeoutMillis <= 0) {
            throw new IllegalArgumentException("超时时间设置有误,必须大于0");
        }
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("url不能为空");
        }
        if (apiRequest == null) {
            throw new IllegalArgumentException("apiRequest不能为null");
        }
        if (StringUtils.isBlank(apiRequest.getMerchantNo())) {
            throw new IllegalArgumentException("[merchantNo]不能为空");
        }
        if (apiRequest.getParamMap() == null || apiRequest.getParamMap().size() == 0) {
            throw new IllegalArgumentException("apiRequest.paramMap不能为空");
        }
        if (StringUtils.isBlank(apiRequest.getSecretKey())) {
            throw new IllegalArgumentException("商户密钥[secretKey]不能为空");
        }
        if (apiRequest.getEncryptType() == null) {
            throw new IllegalArgumentException("加密算法[encryptType]不能为null");
        }
    }

    /**
     * 生成签名sign
     * @param apiRequest
     * @return
     */
    private static String constructSign(ApiRequest apiRequest) {
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> entry : apiRequest.getParamMap().entrySet()) {
            buffer.append(entry.getValue());
        }
        return Digest.hmacSign(buffer.toString(), apiRequest.getSecretKey());
    }

    /**
     * 生成加密data
     * @param apiRequest
     * @return
     */
    private static String constructData(ApiRequest apiRequest) {
        /** 若支持签名,则生成签名 */
        if (apiRequest.isSupportSign()) {
            String sign = constructSign(apiRequest);
            apiRequest.addParam(PARAM_SIGN_MARK, sign);
        }
        String paramJsonStr = JSON.toJSONString(apiRequest.getParamMap());
        if (EncryptTypeEnum.AES.equals(apiRequest.getEncryptType())) {
            /** AESUtil 加密与解密的密钥，截取商户密钥的前16位 */
            return AESUtil.encrypt(paramJsonStr, apiRequest.getSecretKey().substring(0, 16));
        } else {
           /** _3DESUtil 加密与解密的密钥，使用商户密钥的前24位 */
           return _3DESUtil.des3EecodeToString(apiRequest.getSecretKey().substring(0, 24), paramJsonStr);
        }
    }

    /**
     * 解析响应串
     * @param responseText
     * @return
     */
    private static ApiResponse parseResponse(String responseText, ApiRequest apiRequest) {
        if (StringUtils.isBlank(responseText)) {
            throw new RuntimeException("responseText is blank.");
        }
        try {
            ApiResponse apiResponse = new ApiResponse();
            if (responseText.contains(RESPONSE_PARAM_CODE)) {
                Map<String, String> responseMap = JSON.parseObject(responseText, new TypeReference<TreeMap<String, String>>() {});
                if(SUCCESS_CODE.equals(responseMap.get(RESPONSE_PARAM_CODE))){
                    apiResponse.setResultMap(responseMap);
                    apiResponse.setState(ApiStateEnum.SUCCESS);
                    return apiResponse;
                }
                apiResponse.setState(ApiStateEnum.FAIL);
                apiResponse.setCode(responseMap.get(RESPONSE_PARAM_CODE));
                apiResponse.setMessage(responseMap.get(RESPONSE_PARAM_MESSAGE));
                return apiResponse;
            }

            String responseJSONStr = null;

            if (EncryptTypeEnum.AES.equals(apiRequest.getEncryptType())) {
                /** AES解密data, 使用secretKey的前16位 */
                responseJSONStr = AESUtil.decrypt(responseText, apiRequest.getSecretKey().substring(0, 16));
            } else {
                responseJSONStr = _3DESUtil.des3DecodeToString(apiRequest.getSecretKey().substring(0, 24), responseText);
            }
//            System.out.println("===> responseJSONStr: " + responseJSONStr);
            Map<String, String> resultMap = JSON.parseObject(responseJSONStr,new TypeReference<TreeMap<String, String>>() {});

            /** 请求失败时,仅返回错误码code及错误信息msg */
            if (!SUCCESS_CODE.equals(resultMap.get(RESPONSE_PARAM_CODE))) {
                apiResponse.setState(ApiStateEnum.FAIL);
                apiResponse.setCode(resultMap.get(RESPONSE_PARAM_CODE));
                apiResponse.setMessage(resultMap.get(RESPONSE_PARAM_MESSAGE));
                return apiResponse;
            }

            /** 验签 */
            if (apiRequest.isSupportSign()) {
                /** 获取响应签名 */
                String signRemote = resultMap.get(PARAM_SIGN_MARK);
                if (StringUtils.isBlank(signRemote)) {
                    throw new RuntimeException("远程服务器端未返回" + PARAM_SIGN_MARK);
                }

                /** 暂且去掉响应参数中的签名, 主要是为了下面的循环遍历 */
                resultMap.remove(PARAM_SIGN_MARK);
                StringBuffer buffer = new StringBuffer();
                for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                    buffer.append(entry.getValue());
                }
                resultMap.put(PARAM_SIGN_MARK, signRemote);

                /** 本地生成签名, 然后验签 */
                String signLocal = Digest.hmacSign(buffer.toString(), apiRequest.getSecretKey());
                if (!signRemote.equals(signLocal)) {
                    throw new RuntimeException("验签未通过, 本地:[" + signLocal + "], 服务器返回:[" + signRemote + "].");
                }
            }
            apiResponse.setState(ApiStateEnum.SUCCESS);
            apiResponse.setResultMap(resultMap);
            return apiResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String constructUploadUrl(String url, Map<String, String> postParamMap) {
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> entry : postParamMap.entrySet()) {
            buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        if (StringUtils.isNotBlank(buffer)) {
            String paramUrl = buffer.substring(0, buffer.lastIndexOf("&"));
            if (url.indexOf("?") > 0) {
                url = url + "&" + paramUrl;
            } else {
                url = url + "?" + paramUrl;
            }
        }
        return url;
    }
}
