package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 员工批量签约记录
 * @author ljd
 * @date 2020-08-02
 */
@ApiModel(description = "员工批量签约详情记录")
public class CustomerBatchSignRecordDetailDTO {

    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @Excel(name = "签约甲方ID")
    @ApiModelProperty(value = "签约甲方ID")
    private Integer taxSourceId;

    @Excel(name = "签约甲方")
    @ApiModelProperty(value = "签约甲方")
    private String taxSourceName;

    @Excel(name = "签约员工ID")
    @ApiModelProperty(value = "签约员工ID")
    private Integer id;

    @Excel(name = "签约员工")
    @ApiModelProperty(value = "签约员工")
    private String name;

    @Excel(name = "证件号码")
    @ApiModelProperty(value = "证件号码")
    private String idNumber;

    @Excel(name = "手机号")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @Excel(name = "信息发送状态", readConverterExp = "0=未发送,1=已发送")
    @ApiModelProperty(value = "信息发送状态")
    private String sendStatus;

    @Excel(name = "信息发送时间")
    @ApiModelProperty(value = "信息发送时间")
    private String sendTime;

    @Excel(name = "失败原因")
    @ApiModelProperty(value = "失败原因")
    private String comment;

    @Excel(name = "备注")
    @ApiModelProperty(value = "备注")
    private String remark;

    @Excel(name = "签约岗位")
    @ApiModelProperty(value = "签约岗位")
    private String post;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getTaxSourceId() {
        return taxSourceId;
    }

    public void setTaxSourceId(Integer taxSourceId) {
        this.taxSourceId = taxSourceId;
    }

    public String getTaxSourceName() {
        return taxSourceName;
    }

    public void setTaxSourceName(String taxSourceName) {
        this.taxSourceName = taxSourceName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
