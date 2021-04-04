package com.mmtax.common.utils;

import com.mmtax.common.exception.BusinessException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 加密工具类
 * @author Ljd
 * @date 2019/6/16
 */
public class EncrpytionUtil {

    /**
     * 生成密码
     * @param loginName
     * @param password
     * @param salt
     * @return
     * @throws BusinessException
     */
    public static String generatePassword(String loginName, String password, String salt) throws BusinessException {
        if (StringUtils.isEmpty(loginName)){
            throw new BusinessException("密码加密时用户名为空");
        }
        if (StringUtils.isEmpty(password)){
            throw new BusinessException("密码加密密码为空");
        }
        if (StringUtils.isEmpty(salt)){
            throw new BusinessException("密码加密参数为空");
        }
        return new SimpleHash(BusinessEncryptionConstants.ALGORITHM_NAME, password, ByteSource.Util.bytes(loginName + salt), BusinessEncryptionConstants.HASH_ITERATIONS).toHex();
    }

    /**
     * 校验登录密码 loginPassword
     * @param loginName
     * @param loginPassword
     * @param password
     * @param salt
     * @return
     * @throws BusinessException
     */
    public static boolean checkPassword(String loginName, String loginPassword, String password, String salt) throws BusinessException {
        boolean result = false;
        if (StringUtils.isEmpty(loginName)) {
            throw new BusinessException("用户名不可为空");
        }
        if (StringUtils.isEmpty(loginPassword)) {
            throw new BusinessException("密码不可为空");
        }
        if (StringUtils.isEmpty(salt)) {
            throw new BusinessException("加密参数不可为空");
        }
        String encryptedPwd = new SimpleHash(BusinessEncryptionConstants.ALGORITHM_NAME, loginPassword, ByteSource.Util.bytes(loginName + salt), BusinessEncryptionConstants.HASH_ITERATIONS).toHex();
        if (encryptedPwd.equals(password)) {
            result = true;
        }
        return result;
    }
}
