package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import com.mmtax.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 交易订单表 tbl_trx_order
 * 
 * @author meimiao
 * @date 2020-06-29
 */
@Table(name = "tbl_trx_order")
public class TrxOrder
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 商户名称 */
	@Excel(name = "商户名称")
	private String merchantName;
	/** 请求打款金额 */
	@Excel(name = "商户打款请求(元)")
	private BigDecimal amount;
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
	@Excel(name = "打款状态", readConverterExp = "-1=进行中,0=进行中,1=已打款,2=打款挂起,4=订单失败")
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
	/** 是否需要直接打到卡 0-否 1-是 */
	private Integer needPayCard;
	/** 任务批次记录批次号 */
	private String taskRecordBatchNo;
	/**  */
	@Transient
	private Integer providerId;
	/**  */
	@Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**  */
	@Excel(name = "最后更新时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/** 备注信息，支付宝打款非空，显示为订单商品说明 */
	private String memo;

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	public String getMemo()
	{
		return memo;
	}

	public Integer getNeedPayCard() {
		return needPayCard;
	}

	public void setNeedPayCard(Integer needPayCard) {
		this.needPayCard = needPayCard;
	}

	public Integer getInOutDeduction() {
		return inOutDeduction;
	}

	public void setInOutDeduction(Integer inOutDeduction) {
		this.inOutDeduction = inOutDeduction;
	}

	public Integer getBigInOutDeduction() {
		return bigInOutDeduction;
	}

	public void setBigInOutDeduction(Integer bigInOutDeduction) {
		this.bigInOutDeduction = bigInOutDeduction;
	}

	public BigDecimal getTrxRateBig() {
		return trxRateBig;
	}

	public void setTrxRateBig(BigDecimal trxRateBig) {
		this.trxRateBig = trxRateBig;
	}

	public BigDecimal getChargeBig() {
		return chargeBig;
	}

	public void setChargeBig(BigDecimal chargeBig) {
		this.chargeBig = chargeBig;
	}

	public Integer getUseBigRate() {
		return useBigRate;
	}

	public void setUseBigRate(Integer useBigRate) {
		this.useBigRate = useBigRate;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setMerchantName(String merchantName) 
	{
		this.merchantName = merchantName;
	}

	public String getMerchantName()
	{
		return merchantName;
	}

	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setTaxRate(BigDecimal taxRate) 
	{
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxRate()
	{
		return taxRate;
	}

	public void setCharge(BigDecimal charge) 
	{
		this.charge = charge;
	}

	public BigDecimal getCharge()
	{
		return charge;
	}

	public void setActualAmount(BigDecimal actualAmount) 
	{
		this.actualAmount = actualAmount;
	}

	public BigDecimal getActualAmount()
	{
		return actualAmount;
	}

	public void setOrderSerialNum(String orderSerialNum) 
	{
		this.orderSerialNum = orderSerialNum;
	}

	public String getOrderSerialNum()
	{
		return orderSerialNum;
	}

	public void setMerchantSerialNum(String merchantSerialNum) 
	{
		this.merchantSerialNum = merchantSerialNum;
	}

	public String getMerchantSerialNum()
	{
		return merchantSerialNum;
	}

	public void setBatchNo(String batchNo) 
	{
		this.batchNo = batchNo;
	}

	public String getBatchNo()
	{
		return batchNo;
	}

	public void setPaymentVoucher(String paymentVoucher) 
	{
		this.paymentVoucher = paymentVoucher;
	}

	public String getPaymentVoucher()
	{
		return paymentVoucher;
	}

	public void setPayeeName(String payeeName) 
	{
		this.payeeName = payeeName;
	}

	public String getPayeeName()
	{
		return payeeName;
	}

	public void setPayeeMobile(String payeeMobile) 
	{
		this.payeeMobile = payeeMobile;
	}

	public String getPayeeMobile()
	{
		return payeeMobile;
	}

	public void setPayeeBankCard(String payeeBankCard) 
	{
		this.payeeBankCard = payeeBankCard;
	}

	public String getPayeeBankCard()
	{
		return payeeBankCard;
	}

	public void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setPayeeIdCardNo(String payeeIdCardNo) 
	{
		this.payeeIdCardNo = payeeIdCardNo;
	}

	public String getPayeeIdCardNo()
	{
		return payeeIdCardNo;
	}

	public void setOrderStatus(Integer orderStatus) 
	{
		this.orderStatus = orderStatus;
	}

	public Integer getOrderStatus()
	{
		return orderStatus;
	}

	public void setBatchPaymentRecordId(Integer batchPaymentRecordId) 
	{
		this.batchPaymentRecordId = batchPaymentRecordId;
	}

	public Integer getBatchPaymentRecordId()
	{
		return batchPaymentRecordId;
	}

	public void setPaymentChannel(String paymentChannel) 
	{
		this.paymentChannel = paymentChannel;
	}

	public String getPaymentChannel()
	{
		return paymentChannel;
	}

	public void setMerchantId(Integer merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
	}

	public void setComment(String comment) 
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}

	public void setOrderInfo(String orderInfo) 
	{
		this.orderInfo = orderInfo;
	}

	public String getOrderInfo()
	{
		return orderInfo;
	}

	public void setSubjectConscription(String subjectConscription) 
	{
		this.subjectConscription = subjectConscription;
	}

	public String getSubjectConscription()
	{
		return subjectConscription;
	}

	public void setAsyncStatus(Integer asyncStatus) 
	{
		this.asyncStatus = asyncStatus;
	}

	public Integer getAsyncStatus()
	{
		return asyncStatus;
	}

	public void setTaskRecordBatchNo(String taskRecordBatchNo)
	{
		this.taskRecordBatchNo = taskRecordBatchNo;
	}

	public String getTaskRecordBatchNo()
	{
		return taskRecordBatchNo;
	}

	public void setProviderId(Integer providerId) 
	{
		this.providerId = providerId;
	}

	public Integer getProviderId()
	{
		return providerId;
	}

	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime()
	{
		return updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merchantName", getMerchantName())
            .append("amount", getAmount())
            .append("taxRate", getTaxRate())
            .append("charge", getCharge())
            .append("actualAmount", getActualAmount())
            .append("orderSerialNum", getOrderSerialNum())
            .append("merchantSerialNum", getMerchantSerialNum())
            .append("batchNo", getBatchNo())
            .append("paymentVoucher", getPaymentVoucher())
            .append("payeeName", getPayeeName())
            .append("payeeMobile", getPayeeMobile())
            .append("payeeBankCard", getPayeeBankCard())
            .append("bankName", getBankName())
            .append("payeeIdCardNo", getPayeeIdCardNo())
            .append("orderStatus", getOrderStatus())
            .append("batchPaymentRecordId", getBatchPaymentRecordId())
            .append("paymentChannel", getPaymentChannel())
            .append("merchantId", getMerchantId())
			.append("remark",remark)
            .append("comment", getComment())
            .append("orderInfo", getOrderInfo())
            .append("subjectConscription", getSubjectConscription())
            .append("asyncStatus", getAsyncStatus())
				.append("taskRecordBatchNo", getTaskRecordBatchNo())
				.append("memo", getMemo())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
