package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/13 14:21
 */
public enum TaxPaymentCertificateStatusEnum {


    /**
     * 未下载
     */
    NOT_DOWNLOAD(0),
    /**
     * 已下载
     */
    DOWNLOAD(1),
    /**
     * 已删除
     */
    DELETE(2);

    public Integer code;

    TaxPaymentCertificateStatusEnum(Integer code) {
        this.code = code;
    }

}
