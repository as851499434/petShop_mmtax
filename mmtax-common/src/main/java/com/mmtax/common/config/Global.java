package com.mmtax.common.config;

import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.YamlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类
 * 
 * @author mmtax
 */
@Component
public class Global
{
    private static final Logger log = LoggerFactory.getLogger(Global.class);

    private static String NAME = "application.properties";

    /**
     * 当前对象实例
     */
    private static Global global;

    /**
     * 图片保存路径
     */
    private static  String pictures;

    private static String profile;
    @Value("${mmtax.profile}")
    public void setProfile(String profile){
        Global.profile = profile;
    }


    public static String getPictures() {
        return pictures;
    }

    @Value("${mmtax.pictures}")
    public void setPictures(String pictures) {
        Global.pictures = pictures;
    }

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<String, String>();

    public Global()
    {
    }

    /**
     * 静态工厂方法
     */
    public static synchronized Global getInstance()
    {
        if (global == null)
        {
            global = new Global();
        }
        return global;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key)
    {
        String value = map.get(key);
        if (value == null)
        {
            Map<?, ?> yamlMap = null;
            try
            {
                yamlMap = YamlUtil.loadYaml(NAME);
                value = String.valueOf(YamlUtil.getProperty(yamlMap, key));
                map.put(key, value != null ? value : StringUtils.EMPTY);
            }
            catch (FileNotFoundException e)
            {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return value;
    }


    /**
     * 获取项目版本
     */
//    public static String getVersion()
//    {
//        return StringUtils.nvl(getConfig("mmtax.version"), "3.3.0");
//    }


    /**
     * 获取ip地址开关
     */
    public static Boolean isAddressEnabled()
    {
        return Boolean.valueOf(profile);
    }

    /**
     * 获取文件上传路径
     */
    public static String getProfile()
    {
        return profile;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return profile + "avatar/";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return profile + "download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return profile + "upload/";
    }
}
