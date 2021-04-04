package com.mmtax.quartz.task;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.OnlineAccountConfig;
import com.mmtax.business.mapper.OnlineAccountConfigMapper;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.DateUtils;
import com.mmtax.common.utils.DeCompressUtil;
import com.mmtax.common.utils.IpUtils;
import com.mmtax.common.utils.SftpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

import static com.mmtax.common.utils.SftpUtil.uploadFileToSeverBySftp;

/**
 * @Author：YH
 * @Date：2020/9/27 11:59
 */
@Component("HandleTransactionVoucherJob")
@Slf4j
public class HandleTransactionVoucherJob {

    @Autowired
    OnlineAccountConfigMapper onlineAccountConfigMapper;

    /**
     * 下载凭证压缩包 zip 后缀拼接
     */
    public static final String CREDENTIAL_ZIP_SUFFIX = "+000001.zip";

    /**
     * 凭证下载路径
     */
    public static final String CREDENTIAL_DOWNLOAD_PATH = "/home/mmtax/credential/";

    /**
     * 悟空服务器内网ip
     */
    public static final String THIS_MERCHANT_IP = "172.26.196.141";

    /**
     * 上传服务器 主机IP、端口、用户名、密码
     */
    public static final String UPLOAD_HOST = "172.26.196.141";
    public static final Integer UPLOAD_PORT = 22;
    public static final String UPLOAD_USERNAME = "root";
    public static final String UPLOAD_PASSWORD = "!mmci@888";

    /** 启动时的环境变量中的内网ip */
    private static final String HOST_Q = "HOST_Q";

    /**
     * 上传服务器 base 路径
     */
    public static final String UPLOAD_BASE_DIRECTORY = "/home/mmtax/credential/";

    public void handleTransactionVoucherJob() {
        log.info("开始处理handleTransactionVoucherJob");
        List<OnlineAccountConfig> onlineAccountConfigs = onlineAccountConfigMapper.selectAll();
        for (OnlineAccountConfig onlineAccountConfig : onlineAccountConfigs) {
            String pid = null;
            String downloadFile = null;
            try {
                String sftpIp = onlineAccountConfig.getSftpIp();
                Integer sftpPort = Integer.parseInt(onlineAccountConfig.getSftpPort());
                String sftpPass = onlineAccountConfig.getSftpPass();
                String sftpUser = onlineAccountConfig.getSftpUser();

                pid = onlineAccountConfig.getPid();
                String yesterdayDate = DateUtil.format(DateUtil.yesterday(), DateUtils.YYYYMMDD);
                //网商 交易凭证 sftp 路径
                String directory = File.separator + pid + File.separator + yesterdayDate + File.separator;

                //网商 交易凭证 压缩文件名
                 downloadFile = pid + "+" + yesterdayDate + CREDENTIAL_ZIP_SUFFIX;

                //要下载的位置
                String saveFile = CREDENTIAL_DOWNLOAD_PATH + pid + File.separator + yesterdayDate + File.separator + downloadFile;


                String saveFilePath = saveFile.substring(0, saveFile.lastIndexOf(File.separator));
                File file = new File(saveFilePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                SftpUtil.loginSftp(sftpIp, sftpPort, sftpUser, sftpPass);
                SftpUtil.downloadFile(directory, downloadFile, saveFile);
                SftpUtil.logoutSftp();

                DeCompressUtil.unzipOrRar(saveFile, saveFilePath, DeCompressUtil.ZIP);

                //判断是否在悟空服务器
                log.info("本机内网ip：{}", System.getenv(HOST_Q));
                if (System.getenv(HOST_Q).equals(THIS_MERCHANT_IP)) {
                    log.info("就在该服务器，不用传送");
                    continue;
                }
                File uploadFileDirectory = new File(saveFilePath);
                File[] files = uploadFileDirectory.listFiles();
                for (File uploadFile : files) {
                    try {
                        if (uploadFile.isDirectory()) {
                            continue;
                        }
                        //向服务器 上传 文件
                        uploadFileToSeverBySftp(saveFilePath + File.separator + uploadFile.getName(), UPLOAD_HOST,
                                UPLOAD_PORT, false, UPLOAD_USERNAME, UPLOAD_PASSWORD,
                                UPLOAD_BASE_DIRECTORY + pid + File.separator + yesterdayDate);

                    } catch (Exception e) {
                            log.error("压缩文件:{},上传文件:{},上传失败,失败原因:",downloadFile,
                                    saveFilePath + File.separator + uploadFile.getName(),e);
                    }
                }

            } catch (BusinessException e){
                log.info("{},网商不存在这个文件",downloadFile);
            } catch (Exception e) {
                log.error("文件下载出现异常,pid:{},下载文件:{},原因：",pid,downloadFile,e);
            }

        }

        log.info("handleTransactionVoucherJob处理结束");
    }

}
