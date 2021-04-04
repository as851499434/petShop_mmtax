package com.mmtax.common.enums;

/**
 * @Author：YH
 * @Date：2020/12/10 16:13
 */
public enum WorkOrderStatusEnum {

    PUBILSHED(0,"已发布"),

    FEEDBACK(1,"已反馈"),

    DISPOSE(2,"已处理"),

    CLOSE(3,"关闭工单");

    private Integer code;
    private String description;

    WorkOrderStatusEnum(Integer code,String description){
       this.code = code;
       this.description = description;
   }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
