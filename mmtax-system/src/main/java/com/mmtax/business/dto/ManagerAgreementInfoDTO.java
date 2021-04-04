package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Data
public class ManagerAgreementInfoDTO {

    @ApiModelProperty(value = "商户id")
    private Integer id;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @Excel(name = "姓名")
    @ApiModelProperty(value = "真实名字")
    private String name;

    @Excel(name = "身份证号")
    @ApiModelProperty(value = "证件号")
    private String payeeIdCardNo;

    @ApiModelProperty(value = "签约时间")
    private String signTime;

    @ApiModelProperty(value = "税源地id")
    private Integer taxSourceId;

    @ApiModelProperty(value = "税源地名称")
    private String taxSourceName;
    /** 行业名称 */
    private String industryName;
    /** 任务名称(岗位) */
    private String taskName;
    /** 第几项 */
    private Integer whichItem;
    /** 第一项 */
    private String itemOne = "";
    /** 第二项 */
    private String itemtwo = "";
    /** 账号类型 */
    private String paymentChannel;
    /** 账号 */
    private String accountNo;
    private static List<String> oneItems;
    private static List<String> twoItems;
    private static List<String> threeItems;

    static {
       oneItems = Arrays.asList("装卸搬运服务", "音视频服务", "家政服务（非员工制）", "家居产品维保", "技术咨询"
               , "咨询服务", "教育信息咨询");
       twoItems = Arrays.asList("专业设计服务", "文印晒图服务", "人才委托招聘", "市场推广", "房屋销售代理服务", "渠道搭建及使用", "运输代理服务"
               , "国内货物运输代理服务", "国际货物运输代理服务", "港澳台货物运输代理服务", "其他货物运输代理服务", "代理报关服务", "婚姻介绍服务"
               , "人力资源外包服务", "代理进口免税货物货款");
       threeItems = Arrays.asList("软件开发服务", "软件维护服务", "软件测试服务", "电路设计服务", "电路测试服务", "信息系统服务", "信息系统增值服务"
               , "业务流程管理服务", "其他企业管理服务", "其他人力资源服务", "其他经纪代理服务");
    }

    public void setTaskNameItemOneItemTwo(String taskName, String itemOne, String itemTwo){
        this.taskName = taskName;
        Random random = new Random();
        if(oneItems.contains(taskName)){
            this.whichItem = 1;
            this.itemOne = null == itemOne?String.valueOf((random.nextInt(9)+1)*10):itemOne;
        }else if(twoItems.contains(taskName)){
            this.whichItem = 2;
            this.itemtwo = null == itemTwo?String.valueOf(random.nextInt(35)+5):itemTwo;
        }else {
            this.whichItem = 3;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayeeIdCardNo() {
        return payeeIdCardNo;
    }

    public void setPayeeIdCardNo(String payeeIdCardNo) {
        this.payeeIdCardNo = payeeIdCardNo;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
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
}

