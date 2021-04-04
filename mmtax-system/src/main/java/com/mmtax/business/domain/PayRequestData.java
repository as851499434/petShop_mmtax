package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 打款请求数据表 tbl_pay_request_data
 *
 * @author meimiao
 * @date 2020-11-04
 */
@Table(name = "tbl_pay_request_data")
public class PayRequestData
{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 批量打款记录id */
	private Integer batchPaymentRecordId;
	/** 商户id */
	private Integer merchantId;
	/** 请求打款金额 */
	private BigDecimal amount;
	/** 税率 */
	private BigDecimal taxRate;
	/** 手续费 */
	private BigDecimal charge;
	/** 0-内扣 1-外扣 */
	private Integer inOutDeduction;
	/** 商户流水号 */
	private String merchantSerialNum;
	/** 姓名 */
	private String name;
	/** 手机号 */
	private String mobile;
	/** 银行卡号 */
	private String bankCard;
	/** 收款人身份证号 */
	private String idCardNo;
	/** 0-校验中 1-成功 2-失败 */
	private Integer status;
	/** BANK-银行ALIPAY-支付宝WECHAT-微信打款渠道 */
	private String paymentChannel;
	/** 打款备注 */
	private String remark;
	/** 备注 */
	private String comment;
	/** 使用大额费率 0否 1是 */
	private Integer useBigRate;
	/**  */
	private Integer providerId;
	/** 0-未删除 1-已删除 */
	private Integer delStatus;
	/** 是否是上传模板 0 不是 1 是 */
	private Integer promotionStatus;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 区 */
	private String area;
	/** 街道 */
	private String street;
	/** 社区 */
	private String community;
	/** 单价 */
	private BigDecimal unitPrice;
	/** 数量 */
	private Integer quantity;
	/** 推广开始时间 */
	private String promotionStartTime;
	/** 推广结束时间 */
	private String promotionEndTine;
	/**  */
	private Date createTime;
	/**  */
	private Date updateTime;


	/** 派单批次号*/
	private String taskRecordBatchNo;

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setBatchPaymentRecordId(Integer batchPaymentRecordId)
	{
		this.batchPaymentRecordId = batchPaymentRecordId;
	}

	public Integer getBatchPaymentRecordId()
	{
		return batchPaymentRecordId;
	}

	public void setMerchantId(Integer merchantId)
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
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

	public void setInOutDeduction(Integer inOutDeduction)
	{
		this.inOutDeduction = inOutDeduction;
	}

	public Integer getInOutDeduction()
	{
		return inOutDeduction;
	}

	public void setMerchantSerialNum(String merchantSerialNum)
	{
		this.merchantSerialNum = merchantSerialNum;
	}

	public String getMerchantSerialNum()
	{
		return merchantSerialNum;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setBankCard(String bankCard)
	{
		this.bankCard = bankCard;
	}

	public String getBankCard()
	{
		return bankCard;
	}

	public void setIdCardNo(String idCardNo)
	{
		this.idCardNo = idCardNo;
	}

	public String getIdCardNo()
	{
		return idCardNo;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setPaymentChannel(String paymentChannel)
	{
		this.paymentChannel = paymentChannel;
	}

	public String getPaymentChannel()
	{
		return paymentChannel;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}

	public void setUseBigRate(Integer useBigRate)
	{
		this.useBigRate = useBigRate;
	}

	public Integer getUseBigRate()
	{
		return useBigRate;
	}

	public void setProviderId(Integer providerId)
	{
		this.providerId = providerId;
	}

	public Integer getProviderId()
	{
		return providerId;
	}

	public void setDelStatus(Integer delStatus)
	{
		this.delStatus = delStatus;
	}

	public Integer getDelStatus()
	{
		return delStatus;
	}

	public void setPromotionStatus(Integer promotionStatus)
	{
		this.promotionStatus = promotionStatus;
	}

	public Integer getPromotionStatus()
	{
		return promotionStatus;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getProvince()
	{
		return province;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCity()
	{
		return city;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getArea()
	{
		return area;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getStreet()
	{
		return street;
	}

	public void setCommunity(String community)
	{
		this.community = community;
	}

	public String getCommunity()
	{
		return community;
	}

	public void setUnitPrice(BigDecimal unitPrice)
	{
		this.unitPrice = unitPrice;
	}

	public BigDecimal getUnitPrice()
	{
		return unitPrice;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setPromotionStartTime(String promotionStartTime)
	{
		this.promotionStartTime = promotionStartTime;
	}

	public String getPromotionStartTime()
	{
		return promotionStartTime;
	}

	public void setPromotionEndTine(String promotionEndTine)
	{
		this.promotionEndTine = promotionEndTine;
	}

	public String getPromotionEndTine()
	{
		return promotionEndTine;
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

	public String getTaskRecordBatchNo() {
		return taskRecordBatchNo;
	}

	public void setTaskRecordBatchNo(String taskRecordBatchNo) {
		this.taskRecordBatchNo = taskRecordBatchNo;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("batchPaymentRecordId", getBatchPaymentRecordId())
				.append("merchantId", getMerchantId())
				.append("amount", getAmount())
				.append("taxRate", getTaxRate())
				.append("charge", getCharge())
				.append("inOutDeduction", getInOutDeduction())
				.append("merchantSerialNum", getMerchantSerialNum())
				.append("name", getName())
				.append("mobile", getMobile())
				.append("bankCard", getBankCard())
				.append("idCardNo", getIdCardNo())
				.append("status", getStatus())
				.append("paymentChannel", getPaymentChannel())
				.append("remark", getRemark())
				.append("comment", getComment())
				.append("useBigRate", getUseBigRate())
				.append("providerId", getProviderId())
				.append("delStatus", getDelStatus())
				.append("promotionStatus", getPromotionStatus())
				.append("province", getProvince())
				.append("city", getCity())
				.append("area", getArea())
				.append("street", getStreet())
				.append("community", getCommunity())
				.append("unitPrice", getUnitPrice())
				.append("quantity", getQuantity())
				.append("promotionStartTime", getPromotionStartTime())
				.append("promotionEndTine", getPromotionEndTine())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime())
				.toString();
	}
}
