package com.mmtax.common.utils;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/15 14:33
 */
public class Base64Util {
    /**
     * 将网络链接图片或者本地图片文件转换成Base64编码字符串
     *
     * @param imgStr 网络图片Url/本地图片目录路径
     * @return
     */
    public static String getImgStrToBase64(String imgStr) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] buffer = null;
        String head = imgStr.substring(imgStr.lastIndexOf(".") + 1, imgStr.length());
        try {
            //判断网络链接图片文件/本地目录图片文件
            if (imgStr.startsWith("http://") || imgStr.startsWith("https://")) {
                // 创建URL
                URL url = new URL(imgStr);
                // 创建链接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                inputStream = conn.getInputStream();
                outputStream = new ByteArrayOutputStream();
                // 将内容读取内存中
                buffer = new byte[1024];
                int len = -1;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                buffer = outputStream.toByteArray();
            } else {
                inputStream = new FileInputStream(imgStr);
                int count = 0;
                while (count == 0) {
                    count = inputStream.available();
                }
                buffer = new byte[count];
                inputStream.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // 关闭inputStream流
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // 关闭outputStream流
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对字节数组Base64编码
        String result = new BASE64Encoder().encode(buffer);

        return "data:image/" + head + ";base64," + result;
    }
}
