package com.mmtax.common.chanpay;

import com.mmtax.common.chanpay.extend.DESUtil;

/**
 * 银行卡加解密
 * @author xiaojiang.gao
 * @date 2018年1月25日
 */
public class MerchantInfoEncryptUtil {

	public static final String key1 = "1122334455667788";

	public static final String key2 = "2018012620180126";

	/**
	 * 银行卡号解密
	 * @param bankAccontNo
	 * @return
	 * @throws Exception
	 */
	public static String desDecryptBankAccountNo(String bankAccontNo){
		try {
			return DESUtil.desDecrypt(bankAccontNo, key1);
		} catch (Exception e) {
			throw new RuntimeException("银行卡号解密异常", e);
		}
	}

	/**
	 * 银行卡号加密
	 * @param bankAccontNo
	 * @return
	 * @throws Exception
	 */
	public static String desEncryptBankAccountNo(String bankAccontNo){
		try {
			return DESUtil.desEncrypt(bankAccontNo, key1);
		} catch (Exception e) {
			throw new RuntimeException("银行卡号加密异常", e);
		}
	}

	/**
	 * 身份证解密
	 * @param idCard
	 * @return
	 * @throws Exception
	 */
	public static String desDecryptIdCard(String idCard){
		try {
			return DESUtil.desDecrypt(idCard, key2);
		} catch (Exception e) {
			throw new RuntimeException("身份证解密异常", e);
		}
	}

	/**
	 * 身份证加密
	 * @param idCard
	 * @return
	 * @throws Exception
	 */
	public static String desEncryptIdCard(String idCard){
		try {
			return DESUtil.desEncrypt(idCard, key2);
		} catch (Exception e) {
			throw new RuntimeException("身份证加密异常", e);
		}
	}

	/**
	 * 手机号解密
	 * @param mobile
	 * @return
	 */
	public static String desDecryptMobile(String mobile) {
		try {
			return DESUtil.desDecrypt(mobile, key2);
		} catch (Exception e) {
			throw new RuntimeException("手机号解密异常", e);
		}
	}

	/**
	 * 手机号加密
	 * @param mobile
	 * @return
	 */
	public static String desEncryptMobile(String mobile) {
		try {
			return DESUtil.desEncrypt(mobile, key2);
		} catch (Exception e) {
			throw new RuntimeException("手机号加密异常", e);
		}
	}

	public static void main(String[] args) {
		String str ="302837a85edc9a01d0374d9007c2dc7351d57f923ee29886";
		System.out.println(MerchantInfoEncryptUtil.desDecryptBankAccountNo(str));
	}

}
