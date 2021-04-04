package com.mmtax.common.chanpay;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * 3DES加密
 *
 * @author：kai.yang 这个类的作者你就是个傻逼
 * @since：2017年4月17日 下午4:25:07
 * @version:
 */
public class _3DESUtil {

    // 算法名称
    public static final String KEY_ALGORITHM = "Desede";

    // 算法名称/加密模式/填充方式
    public static final String CIPHER_ALGORITHM = "Desede/ECB/PKCS5Padding";

    public static final String CHAR_ENCODING = "UTF-8";

    /**
     * CBC加密
     *
     * @param key
     *            密钥
     * @param data
     *            明文
     * @return Base64编码的密文
     * @throws Exception
     */
    public static byte[] des3EncodeCBC(String key, byte[] data) throws Exception {
        Key deskey = keyGenerator(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        return cipher.doFinal(data);
    }

    /**
     *
     * 生成密钥key对象
     *
     * @param keyStr
     *            密钥字符串
     * @return 密钥对象
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws Exception
     */
    private static Key keyGenerator(String keyStr) throws Exception {
        keyStr = keyStr.length() > 24 ? keyStr.substring(0, 24) : keyStr;
        byte input[] = keyStr.getBytes(CHAR_ENCODING);
        DESedeKeySpec KeySpec = new DESedeKeySpec(input);
        SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return ((Key) (KeyFactory.generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
    }

    /**
     * 3des解密
     *
     * @param key
     *            密钥
     * @param data
     *            Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    public static byte[] des3DecodeCBC(String key, byte[] data) throws Exception {
        Key deskey = keyGenerator(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);
        return bOut;
    }

    /**
     * 3des加密
     *
     * @param key
     *            密钥
     * @param data
     *            Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    public static String des3EecodeToString(String key, String data) {
        try {
            return new String(Base64.encode(des3EncodeCBC(key, data.getBytes(CHAR_ENCODING))), CHAR_ENCODING);
        } catch (Exception e) {
            throw new RuntimeException("3des加密失败key=" + key + "   data=" + data);
        }
    }

    public static String des3DecodeToString(String key, String data) {
        try {
            return new String(des3DecodeCBC(key, Base64.decode(data.getBytes(CHAR_ENCODING))));
        } catch (Exception e) {
            throw new RuntimeException("3des解密失败key=" + key + "   data=" + data);
        }
    }
}
