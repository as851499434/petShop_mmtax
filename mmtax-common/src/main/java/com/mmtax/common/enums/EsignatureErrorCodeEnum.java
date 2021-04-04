package com.mmtax.common.enums;

public enum EsignatureErrorCodeEnum {
    //e签宝报错信息
    SYSTEM_ERROR("系统错误", "系统错误，开启签约流程失败", "开启签约流程失败"),

    NOT_ENOUGHONE("1435404",
            "机构账户中签署份数不足，请购买套餐后再操作  当前机构签署份数不足且当前操作用户是机构管理员",
            "签署账户余额不足，请联系管理员"),

    NOT_ENOUGHTWO("1435405",
            "机构账户中签署份数不足，请联系管理员（%s，%s）购买套餐后再操作  当前机构签署份数不足且当前操作用户不是机构管理员",
            "签署账户余额不足，请联系管理员"),

    NOT_ENOUGHTHREE("1435403","账户中签署份数不足，请购买套餐后再操作  当前个人账户签署份数不足",
            "签署账户余额不足，请联系管理员"),

    NOT_ALL("1437113","签署区没有全部完成  签署区没有全部完成签署, 不能归档","签署未完成");

    private String code;

    private String description;

    private String modifyDescription;

    EsignatureErrorCodeEnum(String code,String description,String modifyDescription) {
        this.code = code;
        this.description = description;
        this.modifyDescription = modifyDescription;
    }

    public static EsignatureErrorCodeEnum getByEnumCode(String enumCode){
        for (EsignatureErrorCodeEnum one : EsignatureErrorCodeEnum.values()) {
            if (enumCode.equals(one.code)){
                return one;
            }
        }
        return EsignatureErrorCodeEnum.SYSTEM_ERROR;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getModifyDescription() {
        return modifyDescription;
    }
}
