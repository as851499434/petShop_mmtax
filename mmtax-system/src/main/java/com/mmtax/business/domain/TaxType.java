package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 税务类型表 tbl_tax_type
 *
 * @author meimiao
 * @date 2020-11-26
 */
@Table(name = "tbl_tax_type")
public class TaxType {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 编号
     */
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
    @ApiModelProperty("税源地公司名称")
    private String taxSounrceCompanyName;
    /**
     * 行业类型
     */
    @ApiModelProperty("行业类型")
    private String businessType;
    /**
     * 税率类型
     */
    @ApiModelProperty("税率类型")
    private String taxType;
    /**
     * 科目内容（用于发票内容）
     */
    @ApiModelProperty("科目内容（用于发票内容）")
    private String invoiceContent;
    /**
     * 经营范围
     */
    @ApiModelProperty("经营范围")
    private String businessScope;
    /**
     * 个体工商户税率
     */
    @ApiModelProperty("个体工商户税率")
    private String taxPerson;
    /**
     * 0-未删除 1-已删除
     */
    private Integer delStatus;
    /**
     *
     */
    private Integer providerId;
    /**
     *
     */
    private String reservedFieldOne;
    /**
     *
     */
    private String reservedFieldTwo;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTaxTypeNumber() {
        return taxTypeNumber;
    }

    public void setTaxTypeNumber(String taxTypeNumber) {
        this.taxTypeNumber = taxTypeNumber;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getTaxSounrceCompanyId() {
        return taxSounrceCompanyId;
    }

    public void setTaxSounrceCompanyId(Integer taxSounrceCompanyId) {
        this.taxSounrceCompanyId = taxSounrceCompanyId;
    }

    public void setTaxSounrceCompanyName(String taxSounrceCompanyName) {
        this.taxSounrceCompanyName = taxSounrceCompanyName;
    }

    public String getTaxSounrceCompanyName() {
        return taxSounrceCompanyName;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setTaxPerson(String taxPerson) {
        this.taxPerson = taxPerson;
    }

    public String getTaxPerson() {
        return taxPerson;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setReservedFieldOne(String reservedFieldOne) {
        this.reservedFieldOne = reservedFieldOne;
    }

    public String getReservedFieldOne() {
        return reservedFieldOne;
    }

    public void setReservedFieldTwo(String reservedFieldTwo) {
        this.reservedFieldTwo = reservedFieldTwo;
    }

    public String getReservedFieldTwo() {
        return reservedFieldTwo;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taxTypeNumber",getTaxTypeNumber())
                .append("taxSounrceCompanyName", getTaxSounrceCompanyName())
                .append("taxSounrceCompanyName", getTaxSounrceCompanyName())
                .append("businessType", getBusinessType())
                .append("taxType", getTaxType())
                .append("invoiceContent", getInvoiceContent())
                .append("businessScope", getBusinessScope())
                .append("taxPerson", getTaxPerson())
                .append("providerId", getProviderId())
                .append("reservedFieldOne", getReservedFieldOne())
                .append("reservedFieldTwo", getReservedFieldTwo())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
