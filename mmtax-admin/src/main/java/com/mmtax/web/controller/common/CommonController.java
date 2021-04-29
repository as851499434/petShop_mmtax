package com.mmtax.web.controller.common;

import com.mmtax.common.config.Global;
import com.mmtax.common.config.ServerConfig;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.file.FileUploadUtils;
import com.mmtax.common.utils.file.PictureUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.io.File.createTempFile;

/**
 * 通用请求处理
 *
 * @author mmtax
 */
@Api(tags = "文件上传下载")
@Controller
@RequestMapping("/upload")
public class CommonController {

    @ApiOperation(value = "上传")
    @PostMapping("/putImg")
    @ResponseBody
    public String  putImg(@RequestParam("file") MultipartFile file) throws IOException {
        String pictureName = "";
        if (file != null){
            String filename=file.getOriginalFilename();
            String path  = "/mydockervolumes/nginx/html" ;
            FileOutputStream fs = new FileOutputStream(path+"/"+filename);
            String fileType = filename.substring(filename.lastIndexOf("."));
            pictureName = UUID.randomUUID().toString() + fileType;
            file.transferTo(new File(path+"/"+pictureName));
        }
        return "http://116.62.141.102:8070/"+pictureName;
    }



}
