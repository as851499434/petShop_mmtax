package com.mmtax.common.enums;

/**
 * 税源地信息枚举
 * @author Ljd
 * @date 2020/8/11
 */
public enum TaxSourceInfoEnum {

    //3-泰宁博瑞
    TAI_NING_BO_RUI(3,"泰宁县博瑞企业管理有限公司"),
    //4-海南浚聚
    HAN_NAN_JUN_JU(4,"海南浚聚科技有限公司"),
    //5-江西启博
    JIANG_XI_QI_BO(5,"江西启博信息科技有限公司");

    private Integer id;
    private String description;

    TaxSourceInfoEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
