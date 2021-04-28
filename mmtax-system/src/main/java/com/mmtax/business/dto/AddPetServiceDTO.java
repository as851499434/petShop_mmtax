package com.mmtax.business.dto;

import lombok.Data;

@Data
public class AddPetServiceDTO {
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
    /** 备注 */
    private String remake;
    /** 费用*/
    private String price;
    /** 服务 */
    private String service;
    /** 手机号 */
    private String phonenumber;
}
