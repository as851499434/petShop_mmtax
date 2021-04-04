package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author：YH
 * @Date：2020/11/27 18:36
 */
@Data
public class PromotionBatchErrorResultDTO   {

    @ApiModelProperty(value = "商户订单号")
    private String merchantSerialNum;
    @ApiModelProperty(value = "收款银行卡")
    private String bankCard;
    @ApiModelProperty(value = "真实姓名")
    private String name;
    @ApiModelProperty(value = "身份证号")
    private String idCardNo;
    @ApiModelProperty(value = "银行卡预留手机号")
    private String mobile;
    @ApiModelProperty(value = "打款金额")
    private BigDecimal amount;
    @ApiModelProperty(value = "校验结果")
    private String checkResult;
    @ApiModelProperty(value = "打款备注")
    private String remark;
    @ApiModelProperty(value = "失败原因")
    private String comment;


    /** 省 */
    @ApiModelProperty(value = "省")
    private String province;
    /** 市 */
    @ApiModelProperty(value = "市")
    private String city;
    /** 区 */
    @ApiModelProperty(value = "区")
    private String area;
    /** 街道 */
    @ApiModelProperty(value = "街道")
    private String street;
    /** 社区 */
    @ApiModelProperty(value = "社区")
    private String community;
    /** 单价 */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    /** 数量 */
    @ApiModelProperty(value = "数量")
    private Integer quantity;
    /** 推广开始时间 */
    @ApiModelProperty(value = "推广开始时间")
    private String promotionStartTime;
    /** 推广结束时间 */
    @ApiModelProperty(value = "推广结束时间")
    private String promotionEndTine;



}
