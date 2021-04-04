package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ListTrxOrdersDTO {
    private Integer id;
    /** 商户名称 */
    @Excel(name = "商户名称")
    private String merchantName;
    /** 请求打款金额 */
    @Excel(name = "商户打款请求(元)")
    private BigDecimal payAmount;
    /** 税率 */
    private BigDecimal taxRate;
    /** 手续费 */
    @Excel(name = "服务费(元)")
    private BigDecimal charge;
    /** 0-普通费率内扣 1-普通费率外扣 */
    private Integer inOutDeduction;
    /** 大额税率 */
    private BigDecimal trxRateBig;
    /** 大额手续费 */
    @Excel(name = "服务费(元)")
    private BigDecimal chargeBig;
    /** 0-大额费率内扣 1-大额费率外扣 */
    private Integer bigInOutDeduction;
    /** 实发金额 */
    private BigDecimal actualAmount;
    /** 订单流水号 */
    @Excel(name = "订单流水号")
    private String orderSerialNum;
    /** 商户流水号 */
    @Excel(name = "商户订单号")
    private String merchantSerialNum;
    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;
    /** 打款凭证 */
    private String paymentVoucher;
    /** 收款人姓名 */
    @Excel(name = "收款人姓名")
    private String payeeName;
    /** 收款人手机号 */
    @Excel(name = "银行预留手机号")
    private String payeeMobile;
    /** 银行卡号 */
    @Excel(name = "收款账号")
    private String payeeBankCard;
    /** 银行名称 */
    private String bankName;
    /** 收款人身份证号 */
    @Excel(name = "证件号码")
    private String payeeIdCardNo;
    /** 0-未打款(进行中)1-已打款2-打款挂起3-调单状态4-失败订单结束 */
    @Excel(name = "打款状态", readConverterExp = "-1=进行中,0=进行中,1=已打款,2=打款挂起,4=订单失败,9=失败")
    private Integer orderStatus;
    /** 批量打款记录id */
    private Integer batchPaymentRecordId;
    /** BANK-银行ALIPAY-支付宝WECHAT-微信打款渠道 */
    @Excel(name = "打款渠道", readConverterExp = "BANK=银行,ALIPAY=支付宝,WECHAT=微信")
    private String paymentChannel;
    /** 网商提现银行卡或支付宝id */
    private String bankId;
    /** 商户id */
    private Integer merchantId;
    /** 员工id */
    private Integer customerId;

    @Excel(name = "打款备注")
    private String remark;
    /** 备注 */
    @Excel(name = "失败原因")
    private String comment;
    /** 天津渠道打款时保存的账号和供应商id，挂起订单再次打款时直接从该字段取 */
    private String orderInfo;
    /** 代征主体YUNZB-云众包 */
    private String subjectConscription;
    /** 后续异步动作是否处理 0否 1是 */
    private Integer asyncStatus;
    /** 是否使用大额费率 0否 1是 */
    private Integer useBigRate;
    /**  */
    @Transient
    private Integer providerId;
    /**  */
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**  */
    @Excel(name = "最后更新时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
