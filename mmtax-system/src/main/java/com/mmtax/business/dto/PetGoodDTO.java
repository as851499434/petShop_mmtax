package com.mmtax.business.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PetGoodDTO {
    private Integer id;
    /** 名字 */
    private String name;
    /** 数量 */
    private Integer number;
    /** 照片 */
    private String photo;
    /** 备注 */
    private String remake;
    /** 厂家 */
    private String factory;
    /** 售价 */
    private String price;
    /** 生产日期 */
    private Date productionTime;
    /** 剩余时间 */
    private String remainTime;
    /** 删除状态 0 未删除 1 已删除 */
    private String delStatus;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
