package com.mmtax.business.dto;

import com.mmtax.business.domain.PetInfo;
import com.mmtax.business.domain.PetMasterInfo;
import lombok.Data;

import java.util.Date;

/**
 * @author liangfan
 */

@Data
public class AddPetAndMasterInfoDto {
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
    /** 姓名 */
    private String name;
    /** 年龄 */
    private Integer age;
    /** 性别 0 男 1 女 2 未知 */
    private String sex;
    /** 手机号 */
    private String phonenumber;
    /** 邮箱地址 */
    private String email;
    /** 居住地址 */
    private String adress;


}
