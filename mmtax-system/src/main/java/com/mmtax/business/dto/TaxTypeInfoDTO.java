package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/12/1 15:04
 */
@Data
public class TaxTypeInfoDTO {
    /**
     * 税务类型Id
     */
    @ApiModelProperty("税务类型Id")
    private Integer taxTypeId;
    /**
     * 申请编号
     */
    @Excel(name = "编号")
    @ApiModelProperty("编号")
    private String taxTypeNumber;
    /**
     * 税源地Id
     */
    @ApiModelProperty("税源地Id")
    private Integer taxSounrceCompanyId;
    /**
     * 税源地公司名称
     */
    @Excel(name = "税源地")
    @ApiModelProperty("税源地公司名称")
    private String taxSounrceCompanyName;
    /**
     * 行业类型
     */
    @Excel(name = "行业类型")
    @ApiModelProperty("行业类型")
    private String businessType;
    /**
     * 税率类型
     */
    @Excel(name = "核定税种")
    @ApiModelProperty("税率类型")
    private String taxType;
    /**
     * 科目内容（用于发票内容）
     */
    @Excel(name = "开票类目")
    @ApiModelProperty("科目内容（用于发票内容）")
    private String invoiceContent;
    /**
     * 经营范围
     */
    @Excel(name = "经营范围")
    @ApiModelProperty("经营范围")
    private String businessScope;
    /**
     * 个体工商户税率
     */
    @Excel(name = "个税政策")
    @ApiModelProperty("个体工商户税率")
    private String taxPerson;
    /**
     * 添加时间
     */
    @Excel(name = "添加时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("添加时间")
    private Date createTime;
}
