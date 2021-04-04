package com.mmtax.common.utils;

import com.mmtax.common.enums.NameLengthEnum;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/27 11:17
 */
public class DealInfoUtils {
    /**
     * 处理手机号显示
     * @param mobile 手机号
     * @return
     */
    public static String dealWithMobileNumber(String mobile){
        if (mobile == null) {
            return "";
        }
        if (mobile.length() == 11) {
            mobile = mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4);
        }
        return mobile;
    }

    /**
     * 处理名字显示
     * @param name 名字
     * @return
     */
    public static String dealWithName(String name){
        if (name == null) {
            return "";
        }
        //名字长度为两位数时、和名字长度大于两位时
        if (name.length() == NameLengthEnum.NAME_LENGTH.getLength()) {
            name = name.substring(0, 1) + "*";
        } else {
            int length = name.length();
            String str = "*";
            name = name.substring(0, 1) + str + name.substring(length - 1);
        }
        return name;
    }

    /**
     * 处理身份证号显示
     * @param idCard 身份证号
     * @return
     */
    public static String dealWithIdCardNumber(String idCard){
        if (idCard == null) {
            return "";
        }
        if (idCard.length() >= 8) {
            idCard = idCard.substring(0, 4) + "******" + idCard.substring(idCard.length() - 4);
        }
        return idCard;
    }

    /**
     * 处理交易账号显示
     * @param paymentNumber 交易账号
     * @return
     */
    public static String dealWithPaymentNumber(String paymentNumber){
        if (paymentNumber == null) {
            return "";
        }
        if (paymentNumber.length() >= 8) {
            //支付宝、银行卡
            if (paymentNumber.length() >= 11 && paymentNumber.contains("@")) {
                paymentNumber = paymentNumber.substring(0, 4) + "******" + paymentNumber.substring(paymentNumber.length() - 8);
            } else {
                paymentNumber = paymentNumber.substring(0, 4) + "******" + paymentNumber.substring(paymentNumber.length() - 4);
            }
        }
        return paymentNumber;
    }
}
