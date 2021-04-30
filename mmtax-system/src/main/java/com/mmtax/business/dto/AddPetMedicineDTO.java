package com.mmtax.business.dto;

import lombok.Data;

@Data
public class AddPetMedicineDTO {
    /** 宠物名字 */
    private String name;
    /** 宠物种类 */
    private Integer number;
    /** 备注 */
    private String remake;
    /** 售价*/
    private String price;
    /** 成本*/
    private String cost;
    /** 生产日期 */
    private String productionTime;
    /** 保质期/天 */
    private Integer shelfLife;
    /** 生产厂家 */
    private String factory;
    /** 图片 */
    private String photo;
}
