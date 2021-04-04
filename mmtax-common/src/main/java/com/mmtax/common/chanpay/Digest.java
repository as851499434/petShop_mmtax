package com.mmtax.common.chanpay;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/**
 * hmac的签名算法
 * 
 * @author blakeyuan
 *
 */
public class Digest {
	public static final String ENCODE = "UTF-8"; // UTF-8


	/**
	 * 直接用MD5签名对数据签名，不需要密钥
	 *
	 * @param aValue
	 * @return
	 */
	public static String signMD5(String aValue) {
		return signMD5(aValue,ENCODE);
	}
	/**
	 * 直接用MD5签名对数据签名，不需要密钥
	 * 
	 * @param aValue
	 * @return
	 */
	public static String signMD5(String aValue, String encoding) {
		try {
			byte[] input = aValue.getBytes(encoding);
			MessageDigest md = MessageDigest.getInstance("MD5");
			return ConvertUtils.toHex(md.digest(input));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 直接用MD5签名对数据签名，不需要密钥
	 * 
	 * @param aValue
	 * @return
	 */
	public static String hmacSign(String aValue) {
		try {
			byte[] input = aValue.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			return ConvertUtils.toHex(md.digest(input));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * tao.wu 2012-09-06 直接用MD5签名对数据签名，不需要密钥(使用指定编码)
	 * 
	 * @param aValue
	 * @return
	 */
	public static String hmacSignWithCharset(String aValue, String charset) {
		try {
			byte[] input = null;
			if (StringUtils.isBlank(charset)) {
				input = aValue.getBytes();
			} else {
				input = aValue.getBytes(charset);
			}
			MessageDigest md = MessageDigest.getInstance("MD5");
			return ConvertUtils.toHex(md.digest(input));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对报文进行hmac签名，字符串按照UTF-8编码
	 * 
	 * @param aValue
	 *            - 字符串
	 * @param aKey
	 *            - 密钥
	 * @return - 签名结果，hex字符串
	 */
	public static String hmacSign(String aValue, String aKey) {
		return hmacSign(aValue, aKey, ENCODE);
	}

	/**
	 * 对报文进行采用MD5进行hmac签名
	 * 
	 * @param aValue
	 *            - 字符串
	 * @param aKey
	 *            - 密钥
	 * @param encoding
	 *            - 字符串编码方式
	 * @return - 签名结果，hex字符串
	 */
	public static String hmacSign(String aValue, String aKey, String encoding) {
		byte k_ipad[] = new byte[64];
		byte k_opad[] = new byte[64];
		byte keyb[];
		byte value[];
		try {
			keyb = aKey.getBytes(encoding);
			value = aValue.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			keyb = aKey.getBytes();
			value = aValue.getBytes();
		}
		Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
		Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5c);
		}

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		md.update(k_ipad);
		md.update(value);
		byte dg[] = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 16);
		dg = md.digest();
		return ConvertUtils.toHex(dg);
	}

	/**
	 * 对报文进行采用SHA进行hmac签名
	 * 
	 * @param aValue
	 *            - 字符串
	 * @param aKey
	 *            - 密钥
	 * @param encoding
	 *            - 字符串编码方式
	 * @return - 签名结果，hex字符串
	 */
	public static String hmacSHASign(String aValue, String aKey, String encoding) {
		byte k_ipad[] = new byte[64];
		byte k_opad[] = new byte[64];
		byte keyb[];
		byte value[];
		try {
			keyb = aKey.getBytes(encoding);
			value = aValue.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			keyb = aKey.getBytes();
			value = aValue.getBytes();
		}
		Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
		Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5c);
		}

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		md.update(k_ipad);
		md.update(value);
		byte dg[] = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 20);
		dg = md.digest();
		return ConvertUtils.toHex(dg);
	}

	/**
	 * 对报文进行SHA签名
	 * 
	 * @param aValue
	 *            - 待签名的字符串（编码：UTF-8）
	 * @return - 签名结果，hex字符串
	 */
	public static String digest(String aValue) {
		return digest(aValue, ENCODE);

	}

	/**
	 * 对报文进行SHA签名
	 * 
	 * @param aValue
	 *            - 待签名的字符串
	 * @param encoding
	 *            - 字符串编码方式
	 * @return - 签名结果，hex字符串
	 */
	public static String digest(String aValue, String encoding) {
		aValue = aValue.trim();
		byte value[];
		try {
			value = aValue.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			value = aValue.getBytes();
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return ConvertUtils.toHex(md.digest(value));
	}

	/**
	 * 对字符串进行签名
	 * 
	 * @param aValue
	 *            - 待签名字符串
	 * @param alg
	 *            - 签名算法名称（如SHA, MD5等）
	 * @param encoding
	 *            - 字符串编码方式
	 * @return - 签名结果，hex字符串
	 */
	public static String digest(String aValue, String alg, String encoding) {
		aValue = aValue.trim();
		byte value[];
		try {
			value = aValue.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			value = aValue.getBytes();
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(alg);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return ConvertUtils.toHex(md.digest(value));
	}

	public static String udpSign(String aValue) {
		try {
			byte[] input = aValue.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA1");
			return new String(Base64.encode(md.digest(input)), ENCODE);
		} catch (Throwable e) {
			return null;
		}
	}

}
