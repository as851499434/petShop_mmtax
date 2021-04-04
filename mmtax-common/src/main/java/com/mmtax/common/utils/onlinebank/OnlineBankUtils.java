package com.mmtax.common.utils.onlinebank;

import com.mmtax.common.exception.BusinessException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网银api的远程调用工具
 * @author ljc
 * @date 2020-4-21
 */
public class OnlineBankUtils {
    private static final Logger log = LoggerFactory.getLogger(OnlineBankUtils.class);

    public static String doHttpClientPost(String url, Map<String, String> params) {
        CloseableHttpResponse response=null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String rst=null;
        try {
            //创建一个post对象
            List<NameValuePair> ps = buildPostParams(params);
            HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(ps, "UTF-8"));
            //执行post请求
            response =httpClient.execute(post);
            // 网关调用成功
            if (response.getStatusLine().getStatusCode() == 200) {
                rst = inputStreamToStr(response.getEntity().getContent(), "UTF-8");
                log.info("=======================================");
                log.info("httpClient Post调用结果：{}", rst);
                log.info("=======================================");
            }
        } catch (Exception e) {
            log.error("=======================================");
            log.error("httpClient Post 请求失败:",e);
            log.error("=======================================");
            throw new BusinessException("网络错误,请重新打款");
        }finally {
            try {
                if (null != response) {
                    response.close();
                }
                if(null != httpClient) {
                    httpClient.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rst;
    }

    private static List<NameValuePair> buildPostParams(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return null;
        }
        List<NameValuePair> results = new ArrayList<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            results.add(new BasicNameValuePair(key, value));
        }

        return results;
    }

    private static String inputStreamToStr(InputStream is, String charset) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return new String(buffer.toString().getBytes("ISO-8859-1"), charset);
    }
}
