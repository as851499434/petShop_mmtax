package com.mmtax.business.dto;

import lombok.Data;

import java.util.Date;
@Data
public class PetInfoDTO {
    private Integer id;
    /** 主人名字 */
    private String name;
    /** 主人手机号 */
    private String phonenumber;
    /** 宠物名字 */
    private String petName;
    /** 宠物种类 */
    private String petType;
    /** 宠物性别 */
    private Integer petSex;
    /** 宠物年龄 */
    private Integer petAge;
    /** 宠物信息类型 0 店养宠物 1 医疗宠物 2 销售宠物 3寄养宠物 */
    private Integer petInfoType;
    /** 照片 */
    private String photo;
    /** 备注 */
    private String remake;
    /** 疾病 */
    private String disease;
    /** 治疗方法 */
    private String method;
    /** 售价 */
    private String price;
    /** 成本 */
    private String cost;
    /** 是否标识 0 是 1 否 */
    private String status;
    /** 天数 */
    private String day;
    /** 结束时间时间 */
    private Date endTime;
    /** 剩余时间 */
    private String remainTime;
    /** 服务 */
    private String service;
    /** 删除状态 0 未删除 1 已删除 */
    private String delStatus;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
