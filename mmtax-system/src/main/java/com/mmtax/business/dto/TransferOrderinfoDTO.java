package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TransferOrderinfoDTO {
    @ApiModelProperty(value = "商户名称")
    private String id;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "所属销售")
    private String userName;
    @ApiModelProperty(value = "请求打款金额")
    private String amount;
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;
    @ApiModelProperty(value = "商户流水号")
    private String merchantSerialNum;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "打款状态")
    private Integer status;
    @ApiModelProperty(value = "员工名称")
    private String realName;
    @ApiModelProperty(value = "员工编号")
    private String customerNo;
    @ApiModelProperty(value = "商户编号")
    private String merchantCode;
    @ApiModelProperty(value = "打款渠道")
    private String paymentChannel;
    @ApiModelProperty(value = "员工身份证")
    private String payeeIdCardNo;
    @ApiModelProperty(value = "收款人手机号")
    private String payeeMobile;
    @ApiModelProperty(value = "银行卡号或支付宝账号")
    private String payeeBankCard;
    @ApiModelProperty(value = "批次号")
    private String batchNo;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "打款凭证号")
    private String paymentVoucher;
    @ApiModelProperty(value = "服务费")
    private String charge;
    @ApiModelProperty(value = "代征主体打款请求")
    private String subjectConscription;
    @ApiModelProperty(value = "商户id")
    private String merchantId;
    @ApiModelProperty(value = "收款银行名称")
    private String bankName;
}
