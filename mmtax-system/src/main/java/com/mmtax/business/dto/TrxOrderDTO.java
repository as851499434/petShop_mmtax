package com.mmtax.business.dto;

import com.mmtax.business.domain.TrxOrder;

/**
 * 交易详情dto
 * @author yuanligang
 * @date 2019/7/15
 */
public class TrxOrderDTO extends TrxOrder {

    /**
     * 商户编码
     */
    private String merchantCode;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    @Override
    public String toString() {
        return "TrxOrderDTO{" +
                "merchantCode='" + merchantCode + '\'' +
                '}';
    }
}
