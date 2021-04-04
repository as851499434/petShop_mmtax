package com.mmtax.common.utils.onlinebank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SecurityVerifyUtils {
    private static final Logger log = LoggerFactory.getLogger(SecurityVerifyUtils.class);

    private static final String MD5_KEY = "lewistest";

    /**
     * 测试
     */
//    private static final String WALLET_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqtUCsiOf67Gln0VmKDTp6W7dBVUBcgD/ZNpbXptcLRgCSgWG7S2pocIBbBk1kQyDtFzMWIMQXpzM4Cjt05FY0fQDrVLOUcrS3euJy3JT4Z1nj0WHyF6uMZAwB1R0VeyKBKM3vVPGZWhvPT/C57WAiZ7Rbt3i2PPcxDn0yXhBUaQIDAQAB";
    /**
     * 验证签名
     * @param map
     * @param charset
     * @param signType
     * @return
     */
    public static boolean verify(Map<String, String> map, String charset, String sign, String signType,String walletPublicKey) {
        Map<String, String> tmp = MagCore.paraFilter(map);
        String str = MagCore.createLinkString(tmp, false);
        if ("MD5".equalsIgnoreCase(signType)) {
            return verifyMd5(str, sign, charset);
        } else if ("RSA".equalsIgnoreCase(signType)) {
            return verifyRSA(str, sign, charset,walletPublicKey);
        }
        return false;
    }

    private static boolean verifyMd5(String src, String sign, String charset) {
        boolean result = false;
        if (log.isInfoEnabled()) {
            log.info("verify sign with MD5:src ={},sign={}", new Object[] { src, sign });
        }
        try {
            result = OnlineMD5.verify(src, sign, MD5_KEY, charset);
        } catch (Exception e) {
            log.error("MD5 verify failure:src ={},sign={}", new Object[] { src, sign });
            log.error("MD5 verify failure", e);
        }
        return result;
    }

    private static boolean verifyRSA(String src, String sign, String charset,String walletPublicKey) {
        boolean result = false;
        if (log.isInfoEnabled()) {
            log.info("verify sign with RSA:src ={},sign={}", new Object[] { src, sign });
        }
        try {
            result = OnlineRSA.verify(src, sign, walletPublicKey, charset);
        } catch (Exception e) {
            log.error("RSA verify failure:src ={},sign={}", new Object[] { src, sign });
            log.error("RSA verify failure", e);
        }
        return result;
    }
}
