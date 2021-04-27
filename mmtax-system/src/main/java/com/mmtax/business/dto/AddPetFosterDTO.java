package com.mmtax.business.dto;

import lombok.Data;

@Data
public class AddPetFosterDTO {
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
    /** 售价*/
    private String price;
    /** 是否带回家*/
    private Integer status;
    /** 期限 */
    private Integer day;
    /** 手机号 */
    private String phonenumber;
}
