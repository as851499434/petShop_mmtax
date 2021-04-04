package com.mmtax.common.utils.file;

import com.mmtax.common.config.Global;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/26 18:14
 */
public class DownLoadUtils {

    /**
     *
     * @param response
     * @param downloadPath 下载地址
     */
    public static void downloadByByte(HttpServletResponse response,String downloadPath) {
        File file = new File(downloadPath);
        //文件名
        String fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/")+1);
        //文件存能下载
        if (!file.exists()) {
            throw new RuntimeException("下载地址已失效");
        }
        //设置application/octet-stream ： 二进制流数据（如常见的文件下载）
        response.setContentType("application/octet-stream");
        //Content-disposition其实可以控制用户请求所得的内容存为一个文件的时候提供一个默认的文件名
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        //输出流
        OutputStream os = null;
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            //获得响应输出流
            os = response.getOutputStream();
            //把文件输入到缓冲中
            int i = bis.read(buffer);
            while (i != -1) {
                //把缓冲的字节输出到响应中
                os.write(buffer);
                //把文件输入到缓冲中，直到读取完毕
                i = bis.read(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param downloadPath 下载地址
     * @param userAgent 用户代理
     * @return
     */
    public ResponseEntity<byte[]> downloadFile(String downloadPath,
                                               String userAgent) {
        File file = new File(downloadPath);
        //文件名
        String fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/")+1);
        //构建响应
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();
        //响应内容大小
        bodyBuilder.contentLength(file.length());
        //二进制数据流
        bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        //文件名编码
        try {
            String encodeFileName = URLEncoder.encode(fileName, "UTF-8");
            //IE
            if (userAgent.indexOf("MSIE") > 0) {
                bodyBuilder.header("Content-Disposition", "attachment;filename=" + encodeFileName);
            } else {
                //其他浏览器, 直接下载
                bodyBuilder.header("Content-Disposition", "attachment;filename*=UTF-8''" + encodeFileName);

            }
            //下载成功返回二进制流
            return bodyBuilder.body(FileUtils.readFileToByteArray(file));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //下载失败直接返回错误的请求
        return (ResponseEntity<byte[]>) ResponseEntity.badRequest();
    }
}
