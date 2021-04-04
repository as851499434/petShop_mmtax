package com.mmtax.common.utils.tianjin;

import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.constant.tianjin.TianJinConstants;
import com.mmtax.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/19 11:15 上午
 */
public class TianJinUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TianJinUtil.class);
    /**
     * 连接超时时间
     */
    private static final int connectTimeout = 1000 * 120;
    /**
     * 读取数据超时时间
     */
    private static final int socketTimeout = 1000 * 180;

    /**
     * 签名方法
     *
     * @param input 输入字符串
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static String signatureString(String input) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        // digest() method is called
        // to calculate message digest of the input string
        // returned as array of byte
        byte[] messageDigest = md.digest(input.getBytes("UTF-8"));

        byte[] textByte1 = Base64.getEncoder().encode(messageDigest);
        return new String(textByte1, StandardCharsets.UTF_8);
    }

    /**
     * 获取账户信息
     *
     * @param url    请求地址
     * @param userId 用户id
     * @param token  秘钥
     * @return
     */
    public static String getAccountId(String url, String userId, String token) {
        String orderNo = ChanPayUtil.generateOutTradeNo();
        String result = get(url, orderNo, token, userId, null);
        return result;
    }

    /**
     * 获取供应商信息
     *
     * @param url    请求地址
     * @param userId 用户id
     * @param token  秘钥
     * @return
     */
    public static String getServerInfo(String url, String userId, String token) {
        String orderNo = ChanPayUtil.generateOutTradeNo();
        String result = get(url, orderNo, token, userId, null);
        return result;
    }

    /**
     * 向指定 URL 发送 POST请求
     *
     * @param strUrl     发送请求的 URL
     * @param param      请求参数，格式 name1=value1&name2=value2
     * @param sequenceNo 订单号
     * @param token      token
     * @param userId     userId
     * @param signature  签名
     * @return 远程资源的响应结果
     */
    public static String sendPost(String url, String param, String sequenceNo, String token, String userId, String signature) {
        LOGGER.info("请求参数为：{}",param);
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Length", param.length() + "");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setRequestProperty(TianJinConstants.SEQUENCE_NO, sequenceNo);
            conn.setRequestProperty(TianJinConstants.TOKEN, token);
            conn.setRequestProperty(TianJinConstants.XINBADA_USER_UUID, userId);
            if (StringUtils.isNotEmpty(signature)) {
                conn.setRequestProperty(TianJinConstants.SIGNATURE, signature);
            }

            conn.connect();
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        LOGGER.info("返回结果为：{}",result);
        return result;
    }


    /**
     * 向指定URL发送GET方法的请求
     *
     * @param path
     * @param sequenceNo 订单号
     * @param token      token
     * @param userId     userId
     * @param signature  签名
     * @return
     */
    public static String get(String path, String sequenceNo, String token, String userId, String signature) {
        String result = "";
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            /**设置URLConnection的参数和普通的请求属性****start***/

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty(TianJinConstants.SEQUENCE_NO, sequenceNo);
            conn.setRequestProperty(TianJinConstants.TOKEN, token);
            conn.setRequestProperty(TianJinConstants.XINBADA_USER_UUID, userId);
            if (StringUtils.isNotEmpty(signature)) {
                conn.setRequestProperty(TianJinConstants.SIGNATURE, signature);
            }

            /**设置URLConnection的参数和普通的请求属性****end***/

            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod("GET");
            /**GET方法请求*****start*/
            conn.connect();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }

            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("返回结果为：{}",result);
        return result;
    }

    public static void main(String[] args) {
        String testUrl = "https://sandbox-open.sinbaad.com";
        String url = testUrl + TianJinConstants.ACCOUNT_URL + "b866c83e-6407-11ea-b05e-0242c0a8900a";
        String orderNo = ChanPayUtil.generateOutTradeNo();
        System.out.println(orderNo);
        String userId = "a35325b4-6407-11ea-b091-0242c0a8900a";
        String token = "k9ysnPcSRjcZHBkKdYOVwbAXirPGDVqMmeIcS+t/TIgF5DFZWifJxnn8hi9T+wiK1mbp3OLgSK7/38/TAtFiUy8xDS6LaIBFBtun7bjjD5HS9MFmZLPP6o6tmPUlSv7jr00+C4PQtW4PK9F/pJsDWJZTBzFshInvCbSSODi38HM=";
        String result = get(url, orderNo, token, userId, null);
        System.out.println(result);

    }


}
