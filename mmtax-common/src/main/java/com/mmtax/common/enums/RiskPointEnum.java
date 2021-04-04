package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/16 14:12
 */
public enum RiskPointEnum {


    PAYEE_SUSPECTS_IS_SUPERVISOR_OF_ENTERPRISE("收款人疑是企业（含关联企业）董高监");

    public String description;

    RiskPointEnum(String description) {
        this.description = description;
    }


}
