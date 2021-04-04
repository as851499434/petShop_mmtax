package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ManagerCompanyInfoDTO {
    @ApiModelProperty(value = "子公司id")
    private Integer id;
    @ApiModelProperty(value = "子公司名称")
    private String companyName;
    @ApiModelProperty(value = "公司名称")
    private String taxSounrceCompanyName;

    @ApiModelProperty(value = "公司全名")
    private String taxSounrceCompanyFullName;


    @ApiModelProperty(value = "创建时间")
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxSounrceCompanyName() {
        return taxSounrceCompanyName;
    }

    public void setTaxSounrceCompanyName(String taxSounrceCompanyName) {
        this.taxSounrceCompanyName = taxSounrceCompanyName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTaxSounrceCompanyFullName() {
        return taxSounrceCompanyFullName;
    }

    public void setTaxSounrceCompanyFullName(String taxSounrceCompanyFullName) {
        this.taxSounrceCompanyFullName = taxSounrceCompanyFullName;
    }
}
