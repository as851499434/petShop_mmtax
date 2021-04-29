package com.mmtax.business.dto;

import io.swagger.models.auth.In;
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
    /** 成本 */
    private String cost;
    /** 生产日期 */
    private Date productionTime;
    /** 最后日期 */
    private Date endTime;
    /** 保质期/天 */
    private Integer shelfLife;
    /** 剩余时间 */
    private String remainTime;
    /** 删除状态 0 未删除 1 已删除 */
    private String delStatus;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
