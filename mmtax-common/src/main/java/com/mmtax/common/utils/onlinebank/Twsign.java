package com.mmtax.common.utils.onlinebank;

import com.alibaba.fastjson.JSON;
import com.wxmlabs.aurora.CMSSigner;
import com.wxmlabs.aurora.SignatureService;
import com.wxmlabs.aurora.compatible.TCACompatibleSignatureService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.spring.web.json.Json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * <p>证书签名工具类</p>
 * @author Fei Jiutong
 * @version $Id: TWSIGN.java, v 0.1 2018年9月7日 下午3:58:03 feijiutong Exp $
 */
public class Twsign {
    private static final Logger logger = LoggerFactory.getLogger(Twsign.class);
    private static SignatureService signatureService;

    static {
        // 加载证书配置
        String json = loadCertConfig();
        // 初始化证书
        signatureService = TCACompatibleSignatureService.getInstance(json);
    }

    private static String loadCertConfig() {
        String configFilePath = Twsign.class.getResource("/conf/aa.json").getPath();
        InputStream inputStream= Twsign.class.getResourceAsStream("/conf/aa.json");
        FileInputStream fis = null;
        String json = "";
        try {
            // 读取证书配置
//            fis = new FileInputStream(new File(configFilePath));
//            fis = (FileInputStream) inputStream;
//            byte[] buf = new byte[fis.available()];
//            fis.read(buf);
            byte[] buf = new byte[inputStream.available()];
            inputStream.read(buf);
            json = new String(buf, StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            logger.error("配置文件不存在。文件路径：{}", configFilePath, e);
        } catch (IOException e) {
            logger.error("配置文件读取失败。文件路径：{}", configFilePath, e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("文件流关闭失败。文件路径：{}", configFilePath, e);
            }
        }
        return json;
    }

    /**
     * 加签，不含原文
     * @param plainData 原文
     * @param keyStoreName 
     * @param charset 
     * @return
     * @throws Exception 
     */
    public static String sign(String plainData, String keyStoreName, String charset) throws Exception {
        logger.info("原文：{}", plainData);
        CMSSigner cmsSigner = getSigner(keyStoreName);

        // 不带原文
        byte[] signedData = cmsSigner.sign(plainData.getBytes(charset));
        String signedDataB64 = Base64.encodeBase64String(signedData);
        logger.info("签名：{}", signedDataB64);
        return signedDataB64;
    }

    /**
     * 加密
     * @param plainData 原文
     * @param keyStoreName
     * @return
     * @throws Exception
     */
    public static byte[] encode(byte[] plainData, String keyStoreName) throws Exception {
        CMSSigner cmsSigner = getSigner(keyStoreName);

        // 带原文
        byte[] signedData = cmsSigner.sign(plainData, true);
        byte[] signedDataB64 = Base64.encodeBase64(signedData);
        return signedDataB64;
    }

    private static CMSSigner getSigner(String keyStoreName) throws Exception {
        CMSSigner cmsSigner = null;
        logger.info("查看加签时加载的配置,{}", JSON.toJSONString(signatureService.listSigner()));
        for (String signerName : signatureService.listSigner()) {
            String[] signerNames = StringUtils.split(signerName, "_");
            if (StringUtils.equals(signerNames[0], keyStoreName)) {
                cmsSigner = (CMSSigner) signatureService.findSignerByName(signerName);
                break;
            }
        }
        
        if (cmsSigner == null) {
            throw new Exception("证书不存在。keyStoreName：" + keyStoreName);
        }
        return cmsSigner;
    }
}