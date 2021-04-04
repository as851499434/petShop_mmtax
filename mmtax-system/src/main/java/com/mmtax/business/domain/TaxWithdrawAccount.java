package com.mmtax.business.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 税源地提现账户表 tbl_tax_withdraw_account
 * 
 * @author meimiao
 * @date 2020-06-29
 */
@Table(name = "tbl_tax_withdraw_account")
public class TaxWithdrawAccount
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 对应merchantId */
	private Integer chargeMerchantId;
				/** 公司账户名称 */
				@NotBlank(message = "提现账户名称不能为空")
	private String companyName;
				/** 税源地公司id */
	private Integer taxSourceCompanyId;
				/** 税源地名称 */
	private String taxSourceName;
				/** 税号 */
	private String taxIdNumber;
				/** 地址 */
	private String companyAddress;
				/** 电话 */
	private String companyTel;
				/** 对公银行名称 */
				@NotBlank(message = "银行名称不能为空")
	private String bankPublic;
				/** 对公开户行 */
	private String bankNamePublic;
				/** 对公银行户名 */
				@NotBlank(message = "户名不能为空")
	private String bankAccountPublic;
				/** 对公银行账号 */
				@NotBlank(message = "银行卡号不能为空")
	private String bankCardPublic;
				/** 对私银行名称 */
	private String bankPrivate;
				/** 对私开户行 */
	private String bankNamePrivate;
				/** 对私银行户名 */
	private String bankAccountPrivate;
				/** 对私银行账号 */
	private String bankCardPrivate;
	/**对私银行卡户主手机号*/
	private String mobilePrivate;
				/** 对私身份证号 */
	private String idCardPrivate;
				/** 是否允许提现 0否 1是 */
	private Integer allowWithdraw;
				/** 对公或对私 0对公 1对私 */
	private Integer publicOrPrivate;
				/** 预留字段一 */
				@NotBlank(message = "支行行号不能为空")
	private String reservedFieldOne;
				/** 预留字段二 */
	private String reservedFieldTwo;
				/** 预留字段三 */
	private String reservedFieldThree;
	/**  */
	@Transient
	private Integer providerId;
				/**  */
	private Date createTime;
				/**  */
	private Date updateTime;

	public String getMobilePrivate() {
		return mobilePrivate;
	}

	public void setMobilePrivate(String mobilePrivate) {
		this.mobilePrivate = mobilePrivate;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setChargeMerchantId(Integer chargeMerchantId) 
	{
		this.chargeMerchantId = chargeMerchantId;
	}

	public Integer getChargeMerchantId()
	{
		return chargeMerchantId;
	}

	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setTaxSourceCompanyId(Integer taxSourceCompanyId) 
	{
		this.taxSourceCompanyId = taxSourceCompanyId;
	}

	public Integer getTaxSourceCompanyId()
	{
		return taxSourceCompanyId;
	}

	public void setTaxSourceName(String taxSourceName) 
	{
		this.taxSourceName = taxSourceName;
	}

	public String getTaxSourceName()
	{
		return taxSourceName;
	}

	public void setTaxIdNumber(String taxIdNumber) 
	{
		this.taxIdNumber = taxIdNumber;
	}

	public String getTaxIdNumber()
	{
		return taxIdNumber;
	}

	public void setCompanyAddress(String companyAddress) 
	{
		this.companyAddress = companyAddress;
	}

	public String getCompanyAddress()
	{
		return companyAddress;
	}

	public void setCompanyTel(String companyTel) 
	{
		this.companyTel = companyTel;
	}

	public String getCompanyTel()
	{
		return companyTel;
	}

	public void setBankPublic(String bankPublic) 
	{
		this.bankPublic = bankPublic;
	}

	public String getBankPublic()
	{
		return bankPublic;
	}

	public void setBankNamePublic(String bankNamePublic) 
	{
		this.bankNamePublic = bankNamePublic;
	}

	public String getBankNamePublic()
	{
		return bankNamePublic;
	}

	public void setBankAccountPublic(String bankAccountPublic) 
	{
		this.bankAccountPublic = bankAccountPublic;
	}

	public String getBankAccountPublic()
	{
		return bankAccountPublic;
	}

	public void setBankCardPublic(String bankCardPublic) 
	{
		this.bankCardPublic = bankCardPublic;
	}

	public String getBankCardPublic()
	{
		return bankCardPublic;
	}

	public void setBankPrivate(String bankPrivate) 
	{
		this.bankPrivate = bankPrivate;
	}

	public String getBankPrivate()
	{
		return bankPrivate;
	}

	public void setBankNamePrivate(String bankNamePrivate) 
	{
		this.bankNamePrivate = bankNamePrivate;
	}

	public String getBankNamePrivate()
	{
		return bankNamePrivate;
	}

	public void setBankAccountPrivate(String bankAccountPrivate) 
	{
		this.bankAccountPrivate = bankAccountPrivate;
	}

	public String getBankAccountPrivate()
	{
		return bankAccountPrivate;
	}

	public void setBankCardPrivate(String bankCardPrivate) 
	{
		this.bankCardPrivate = bankCardPrivate;
	}

	public String getBankCardPrivate()
	{
		return bankCardPrivate;
	}

	public void setIdCardPrivate(String idCardPrivate) 
	{
		this.idCardPrivate = idCardPrivate;
	}

	public String getIdCardPrivate()
	{
		return idCardPrivate;
	}

	public void setAllowWithdraw(Integer allowWithdraw) 
	{
		this.allowWithdraw = allowWithdraw;
	}

	public Integer getAllowWithdraw()
	{
		return allowWithdraw;
	}

	public void setPublicOrPrivate(Integer publicOrPrivate) 
	{
		this.publicOrPrivate = publicOrPrivate;
	}

	public Integer getPublicOrPrivate()
	{
		return publicOrPrivate;
	}

	public void setReservedFieldOne(String reservedFieldOne) 
	{
		this.reservedFieldOne = reservedFieldOne;
	}

	public String getReservedFieldOne()
	{
		return reservedFieldOne;
	}

	public void setReservedFieldTwo(String reservedFieldTwo) 
	{
		this.reservedFieldTwo = reservedFieldTwo;
	}

	public String getReservedFieldTwo()
	{
		return reservedFieldTwo;
	}

	public void setReservedFieldThree(String reservedFieldThree) 
	{
		this.reservedFieldThree = reservedFieldThree;
	}

	public String getReservedFieldThree()
	{
		return reservedFieldThree;
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


	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("chargeMerchantId", getChargeMerchantId())
            .append("companyName", getCompanyName())
            .append("taxSourceCompanyId", getTaxSourceCompanyId())
            .append("taxSourceName", getTaxSourceName())
            .append("taxIdNumber", getTaxIdNumber())
            .append("companyAddress", getCompanyAddress())
            .append("companyTel", getCompanyTel())
            .append("bankPublic", getBankPublic())
            .append("bankNamePublic", getBankNamePublic())
            .append("bankAccountPublic", getBankAccountPublic())
            .append("bankCardPublic", getBankCardPublic())
            .append("bankPrivate", getBankPrivate())
            .append("bankNamePrivate", getBankNamePrivate())
            .append("bankAccountPrivate", getBankAccountPrivate())
            .append("bankCardPrivate", getBankCardPrivate())
            .append("idCardPrivate", getIdCardPrivate())
            .append("allowWithdraw", getAllowWithdraw())
            .append("publicOrPrivate", getPublicOrPrivate())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
