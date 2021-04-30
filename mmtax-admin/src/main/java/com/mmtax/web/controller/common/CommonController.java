package com.mmtax.web.controller.common;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.util.UUID;



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
