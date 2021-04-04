package com.mmtax.common.utils;

import com.jcraft.jsch.*;
import com.mmtax.common.config.Global;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

@Slf4j
public class SftpUtil {
    static ChannelSftp sftp = null;
    static Session session = null;

    /**
     * 登录sftp服务器
     * @param host
     * @param port
     * @param username
     * @param password
     */
    public static void loginSftp(String host, Integer port, String username, String password){
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            log.info("sfpt链接{}成功",host);
        } catch (JSchException e) {
            log.info("连接{}失败",host+":"+port.toString());
            e.printStackTrace();
        }
    }

    /**
     * 登出sftp服务器
     */
    public static void logoutSftp(){
        if (null != session && session.isConnected()) {
            session.disconnect();
        }
        if (null != sftp && sftp.isConnected()) {
            sftp.disconnect();
        }
    }

    /**
     * 下载sftp文件
     * @param directory 下载的sfpt目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     */
    public static void downloadFile(String directory,String downloadFile,String saveFile){
        if(null == sftp) {
            log.info("sftp为null");
            return;
        }
        try{
            log.info("begin download file \"{}\"",directory+downloadFile);
            sftp.cd(directory);
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
            log.info("sftp download file \"{}\" success",downloadFile);
        }catch(FileNotFoundException | SftpException e){
            e.printStackTrace();
            throw new BusinessException("没有这个文件");
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件
     * @param directory
     * @param fileName
     * @param input
     * @throws SftpException
     */
    public static void upLoadFile(String directory, String fileName, InputStream input) throws SftpException{
        try {
            log.info("begin upload file \"{}\"",directory+fileName);
            sftp.cd(directory);
        } catch (SftpException e) {
            log.warn("directory \"{}\" is not exist",directory);
            sftp.mkdir(directory);
            sftp.cd(directory);
        }
        sftp.put(input, fileName);
        log.info("file\"{}\" is upload successful" , directory+fileName);
    }

    /**
     * 删除文件
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public static void delete(String directory, String deleteFile){
        try {
            log.info("begin delete file \"{}\"",directory+deleteFile);
            sftp.cd(directory);
            sftp.rm(deleteFile);
            log.info("file\"{}\" delete successful" , directory+deleteFile);
        }catch (SftpException e){
            e.printStackTrace();
        }
    }

    /**
     * 上传单个文件到sftp
     * @param directory 上传的sftp目录
     * @param uploadFile 要上传的文件,包括路径
     */
    public static void upload(String directory, String uploadFile){
        try {
            File file = new File(uploadFile);
            upLoadFile(directory, file.getName(), new FileInputStream(file));
        }catch(FileNotFoundException el){
            log.info("uploadFile \"{}\" not found",uploadFile);
        }catch ( SftpException ep){
            ep.printStackTrace();
        }
    }

    /**
     * sftp上传指定文件到指定服务器的指定目录
     * @param file 要上传的文件(带路径)
     * @param host
     * @param port
     * @param delete 是否删除已上传的文件
     * @param username
     * @param password
     * @param directory 上传sfpt的路径
     */
    public static void uploadFileToSeverBySftp(String file,String host,int port,boolean delete,
                                                     String username,String password,String directory){
        SftpUtil.loginSftp(host,port,username,password);
        SftpUtil.upload(directory,file);
        SftpUtil.logoutSftp();
        if(delete) {
//            FileUtils.deleteFile(file);
        }
    }

    /**
     * sftp冲指定服务器指定目录下载文件
     * @param fileName 下载文件名
     * @param host
     * @param port
     * @param delete 是否删除sfpt服务器上文件
     * @param username
     * @param password
     * @param directory sfpt下载目录
     * @param savePath 本地保存目录(带路径和文件名)
     */
    public static void downloadFilefromSeverBySftp(String fileName,String host,int port,boolean delete,
                                               String username,String password,String directory,String savePath){
        SftpUtil.loginSftp(host,port,username,password);
        SftpUtil.downloadFile(directory,fileName,savePath);
        if(delete){
            SftpUtil.delete(directory,fileName);
        }
        SftpUtil.logoutSftp();
    }

    public static void download(String fileName,String filePath,boolean delete,HttpServletRequest request,
                                  HttpServletResponse response){
        try{
            if (StringUtils.isEmpty(fileName)) {
                log.info("文件名为空");
                return;
            }

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }
}
