package com.mmtax.web.controller.common;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.PersonalMerchant;
import com.mmtax.business.dto.DownloadCredentialDTO;
import com.mmtax.business.mapper.PersonalMerchantMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.common.config.Global;
import com.mmtax.common.config.ServerConfig;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.DateUtils;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.file.DownLoadUtils;
import com.mmtax.common.utils.file.FileUploadUtils;
import com.mmtax.common.utils.file.FileUtils;
import com.mmtax.common.utils.file.PictureUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 通用请求处理
 *
 * @author mmtax
 */
@Api(tags = "文件上传下载")
@Controller
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    /**
     * 文件上传路径
     */
    public static final String UPLOAD_PATH = "/profile/upload/";

    /**
     * 图片上传路径
     */
    public static final String PICTURE_UPLOAD_PATH = "/profile/pictures/";

    /**
     * 凭证下载路径
     */
    public static final String CREDENTIAL_DOWNLOAD_PATH =  "/home/mmtax/credential/";

    /**
     * pdf文件后缀
     */
    public static final String SUFFIX_PDF = ".pdf";

    /**
     * zip文件后缀
     */
    public static final String SUFFIX_ZIP = ".zip";

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    TrxOrderMapper trxOrderMapper;

    @Resource
    private PersonalMerchantMapper personalMerchantMapper;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     * @param name     输出文件名称
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, String name, HttpServletResponse response,
                             HttpServletRequest request,Boolean neverDownFromServer) {
        try {
            String realFileName = null;
            if (StringUtils.isEmpty(name)) {
                realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            } else {
                realFileName = name + fileName.substring(fileName.indexOf("."));
            }
            String filePath = Global.getDownloadPath() + fileName;
            if (fileName.contains("/")) {
                filePath = Global.getUploadPath() + fileName;
            }

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @ApiOperation("文件通用上传")
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + UPLOAD_PATH + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }



    /**
     * 工单专用上传
     */
    @ApiOperation("工单专用上传")
    @PostMapping("/common/uploadWithWorkOrder")
    @ResponseBody
    public AjaxResult uploadFileWithWorkeOrder(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.uploadWithWorkOrder(filePath, file);
            String url = serverConfig.getUrl() + UPLOAD_PATH + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 上传身份证照请求
     */
    @ApiOperation("上传身份证照请求")
    @PostMapping("/common/uploadIdCard")
    @ResponseBody
    public AjaxResult uploadIdCard(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = Global.getPictures();
            // 上传并返回新文件名称
            String fileName = PictureUploadUtils.uploadIdCardPicture(filePath, file);
            String url = serverConfig.getUrl() + PICTURE_UPLOAD_PATH + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 上传营业执照请求
     */
    @ApiOperation("上传营业执照请求")
    @PostMapping("/common/uploadLicense")
    @ResponseBody
    public AjaxResult uploadLicense(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = Global.getPictures();
            // 上传并返回新文件名称
            String fileName = PictureUploadUtils.uploadLicensePicture(filePath, file);
            String url = serverConfig.getUrl() + PICTURE_UPLOAD_PATH + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 下载身份证和营业执照图片请求
     */
    @ApiOperation("营业执照下载")
    @PostMapping("/common/downloadLicense")
    @ResponseBody
    public AjaxResult downloadLicense(HttpServletResponse response,Integer applyId) throws Exception {
        try {
            PersonalMerchant personalMerchant = personalMerchantMapper.selectByPrimaryKey(applyId);
            DownLoadUtils.downloadByByte(response,personalMerchant.getBusinessLicense());
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 下载交易凭证
     * @param response
     * @param request
     */
    @GetMapping("common/downloadCredential")
    public void credentialDownload(String orderSerialNum,HttpServletResponse response, HttpServletRequest request) {
        if(StringUtils.isEmpty(orderSerialNum)){
            return;
        }
        DownloadCredentialDTO downloadCredential = trxOrderMapper.selectCreateTimeAndPidByorderSerialNum(orderSerialNum);
        String pid = downloadCredential.getPid();
        String createDate= DateUtil.format(DateUtil.parse(downloadCredential.getCreateTime()), DateUtils.YYYYMMDD);


        String filePath = CREDENTIAL_DOWNLOAD_PATH + pid + File.separator + createDate + File.separator  ;
        orderSerialNum = orderSerialNum + SUFFIX_PDF;
        String fileName = "";
        //测试用 上线删
        //  String filePath = "E:\\download\\xx\\xxxx\\download\\";
        File file = new File(filePath);
        File files[] = file.listFiles();
        if(null == files){
            throw new BusinessException("系统暂未生成你想要的交易凭证，请联系管理员");
        }
        boolean isFind = true;
        for (int i = 0; i < files.length; i++) {
            File fs = files[i];
            fileName = fs.getName();

            if(!SUFFIX_ZIP.equals(fileName.substring(fileName.lastIndexOf("."))) &&
                    fileName.substring(41).equals(orderSerialNum)){
                filePath = filePath + File.separator + fileName;
                isFind = false;
                break;
            }

        }
        if(isFind){
            throw new BusinessException("系统暂未生成你想要的交易凭证，请联系管理员");
        }


        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());

        } catch (Exception e) {
            log.error("下载文件失败", e);
        }

    }


    @ApiOperation("测试下载图片")
    @PostMapping(value = "common/downloadImgTest")
    @ResponseBody
    public void downloadImgTest(HttpServletResponse response,HttpServletRequest request, String url) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            if (StringUtils.isNotEmpty(url)) {
                //logger.info("读取图片中");
                response.setContentType("application/force-download");
                response.setCharacterEncoding("utf-8");
                String fileName="test"+".png";
                //判断是否为火狐
                if(request.getHeader("user-agent").contains("Firefox")) {
                    fileName=new String(fileName.getBytes("utf-8"), "ISO8859-1");
                }
                else {
                    fileName= URLEncoder.encode(fileName, "utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");

                //图片下载
                URL rUrl = new URL(url);
                URLConnection conn = rUrl.openConnection();
                outputStream = response.getOutputStream();
                inputStream = conn.getInputStream();
                IOUtils.copy(inputStream, outputStream);
                }
            }
        } catch (Exception e) {
            //logger.error("下载图片失败",e);
        }finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }


    @ApiOperation("测试下载图片2")
    @PostMapping(value = "common/downloadImgTest2")
    @ResponseBody
    public void downloadImgTest2(HttpServletResponse response,HttpServletRequest request, String url)  {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, "test.png"));
            //图片下载
            URL rUrl = new URL(url);
            URLConnection conn = rUrl.openConnection();
            outputStream = response.getOutputStream();
            inputStream = conn.getInputStream();
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }


}
