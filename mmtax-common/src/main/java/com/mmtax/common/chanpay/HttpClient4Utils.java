package com.mmtax.common.chanpay;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author yingjie.wang
 * @since 17/4/14 下午2:01
 */
public class HttpClient4Utils {

    /** 默认编码格式 */
    public static final String DEFAULT_CHARSET= "UTF-8";
    /** 默认超时时间 */
    private static final int DEFAULT_TIME_OUT = 30*1000;
    /** 默人文件下载超时时间 */
    private static final int DEFAULT_DOWNLOAD_TIME_OUT = 30*60*1000;
    /** 默认文件上传超时时间 */
    private static final int DEFAULT_UPLOAD_TIME_OUT = 5*60*1000;

    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    private static SSLContextBuilder builder = null;
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    static {
        try {
            builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(),
                    new String[]{"SSLv2Hello", "SSLv3", "TLSv1"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /** 返回String */
    public static String sendHttpRequest(String url, Map<String, String> paramMap, String charset, boolean isPost) {
        return sendHttpRequest(url, paramMap, charset, isPost, DEFAULT_TIME_OUT);
    }

    /** 返回String */
    public static String sendHttpRequest(String url, Map<String, String> paramMap, String charset, boolean isPost, int timeoutMillis) {
        return isPost ? post(url, paramMap, charset, timeoutMillis) : httpGet(url, paramMap, charset, timeoutMillis);
    }

    /** 返回String */
    private static String post(String url, Map<String, String> params, String charset, int timeoutMillis) {
        CloseableHttpClient httpClient = null;
        if (url.startsWith(HTTPS)) {
            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .setConnectionManager(cm)
                    .setConnectionManagerShared(true)
                    .build();
        } else {
            httpClient 	= HttpClients.createDefault();
        }
        return (String) executePost(httpClient, url, params, charset, timeoutMillis, true);
    }

    /** 返回InputStream */
//    public static InputStream sendHttpRequestStream(String url, Map<String, String> paramMap, String charset, boolean isPost) {
//        return sendHttpRequestStream(url, paramMap, charset, isPost, DEFAULT_DOWNLOAD_TIME_OUT);
//    }

    /** 返回InputStream */
//    public static InputStream sendHttpRequestStream(String url, Map<String, String> paramMap, String charset, boolean isPost, int timeoutMillis) {
//        return isPost ? httpPostStream(url, paramMap, charset, timeoutMillis) : httpGetStream(url, paramMap, charset, timeoutMillis);
//    }

    /** 返回InputStream */
//    private static InputStream httpPostStream(String url, Map<String, String> params, String charset, int timeoutMillis) {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        return (InputStream) executePost(httpClient, url, params, charset, timeoutMillis, false);
//    }

    private static Object executePost(CloseableHttpClient httpClient, String url, Map<String, String> params, String charset, int timeoutMillis, boolean isReturnString) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        /** 设置超时 */
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeoutMillis).setSocketTimeout(timeoutMillis).build();
        /** 组装请求参数 */
        List<NameValuePair> pairs	= new ArrayList<NameValuePair>();
        for(Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            pairs.add(new BasicNameValuePair(key, (value==null?"":value.trim())));
        }
        CloseableHttpResponse response	= null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(new UrlEncodedFormEntity(pairs));
            /** 执行请求 */
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                httpPost.abort();
                throw new RuntimeException("HttpClient failed, status code is " + response.getStatusLine().getStatusCode());
            }
            return isReturnString ? EntityUtils.toString(response.getEntity(), charset) : response.getEntity().getContent();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        } finally {
            CloseUtils.close(response, httpClient);
        }
    }

    public static String sendUploadFileHttpRequest(String url, File file, String remoteServerUploadParam, String charset) {
        return sendUploadFileHttpRequest(url, file, remoteServerUploadParam, charset, DEFAULT_UPLOAD_TIME_OUT);
    }

    public static String sendUploadFileHttpRequest(String url, File file, String remoteServerUploadParam,
                                                   String charset, int timeOutMillis) {
        CloseableHttpClient httpClient = null;
        if (url.startsWith(HTTPS)) {
            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .setConnectionManager(cm)
                    .setConnectionManagerShared(true)
                    .build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeOutMillis).setSocketTimeout(timeOutMillis).build();
            httpPost.setConfig(requestConfig);

            FileBody fileBody = new FileBody(file);
            HttpEntity httpEntity = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart(remoteServerUploadParam, fileBody) // uploadFile对应服务端类的同名属性<File类型>
                    .setCharset(CharsetUtils.get(charset)).build();
            httpPost.setEntity(httpEntity);
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                httpPost.abort();
                throw new RuntimeException("HttpClient failed, status code is " + response.getStatusLine().getStatusCode());
            }
            return EntityUtils.toString(response.getEntity(), charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            CloseUtils.close(response, httpClient);
        }
    }

    // TODO: 17/4/14 暂未提供get相关方法
    public static String httpGet(String url, Map<String, String> params, String charset, int timeout) {
        throw new RuntimeException("暂未提供get相关方法");
    }
    public static InputStream httpGetStream(String url, Map<String, String> params, String charset, int timeout) {
        throw new RuntimeException("暂未提供get相关方法");
    }
}
