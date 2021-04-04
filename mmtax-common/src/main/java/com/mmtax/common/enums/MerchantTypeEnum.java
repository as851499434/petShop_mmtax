package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/14 16:03
 */
public enum MerchantTypeEnum {
    /**
     * 个体工商户
     */
    INDIVIDUAL_BUSINESSMEN(0),
    /**
     * 正式商户
     */
    OFFICIAL_MERCHANTS(1);

    public Integer code;

    MerchantTypeEnum(int code){
        this.code = code;
    }


}
