package com.mmtax.common.enums;

/**
 * @Author：YH
 * @Date：2020/11/4 17:40
 */
public enum PromotionEnum {

    NOT_PROMOTION(0),

    IS_PROMOTION(1),

    FAIR_STATUS(2),

    SUCCESS_STATUS(1),

    MIDDLE_STATUS(0),

    NOT_USE_BIG_RATE(0),

    USE_BIG_RATE(1),

    NOT_DEL_STATUS(0),

    DEL_STATUS(1);

    private Integer code;

    PromotionEnum(Integer code){
        this.code =code;
    }

    public Integer getCode() {
        return code;
    }
}
