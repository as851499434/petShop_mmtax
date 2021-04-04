package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 网商渠道税源地配置表 tbl_online_account_config
 * 
 * @author meimiao
 * @date 2020-05-20
 */
@Table(name = "tbl_online_account_config")
public class OnlineAccountConfig
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 税源地id */
	private Integer taxSounrceId;
				/** 税源地公司名称 */
	private String taxSounrceCompanyName;
	/** 网商注册邮箱号 */
	private String registerEmail;
				/** 充值账号前缀 */
	private String accountPrefix;
				/** 合作id */
	private String partnerId;
				/** 渠道代码 */
	private String whiteChannelCode;
				/** rsa公钥 */
	private String walletPublicKey;
				/** 加载证书名称 */
	private String keyStoreName;
				/** 调用sftp的IP地址 */
	private String sftpIp;
				/** 调用sftp的端口 */
	private String sftpPort;
				/** sfpt文件目录 */
	private String fileDirectory;
				/** sftp用户名 */
	private String sftpUser;
				/** sftp密码 */
	private String sftpPass;
				/** 相关pid */
	private String pid;
				/** 服务费成本收取商户id */
	private Integer chargeCostMerId;
				/** 服务费成本收取比例(不加%) */
	private BigDecimal chargeServiceCostRate;
				/** 服务费利率收取商户id */
	private Integer chargeInterestMerId;
				/**  */
	private Integer providerId;
				/** 预留字段1 */
	private String reservedFieldOne;
				/** 预留字段2 */
	private String reservedFieldTwo;
				/** 预留字段3 */
	private String reservedFieldThree;
				/**  */
	private Date createTime;
				/**  */
	private Date updateTime;

	public String getRegisterEmail() {
		return registerEmail;
	}

	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setTaxSounrceId(Integer taxSounrceId) 
	{
		this.taxSounrceId = taxSounrceId;
	}

	public Integer getTaxSounrceId()
	{
		return taxSounrceId;
	}

	public void setTaxSounrceCompanyName(String taxSounrceCompanyName) 
	{
		this.taxSounrceCompanyName = taxSounrceCompanyName;
	}

	public String getTaxSounrceCompanyName()
	{
		return taxSounrceCompanyName;
	}

	public void setAccountPrefix(String accountPrefix) 
	{
		this.accountPrefix = accountPrefix;
	}

	public String getAccountPrefix()
	{
		return accountPrefix;
	}

	public void setPartnerId(String partnerId) 
	{
		this.partnerId = partnerId;
	}

	public String getPartnerId()
	{
		return partnerId;
	}

	public void setWhiteChannelCode(String whiteChannelCode) 
	{
		this.whiteChannelCode = whiteChannelCode;
	}

	public String getWhiteChannelCode()
	{
		return whiteChannelCode;
	}

	public void setWalletPublicKey(String walletPublicKey) 
	{
		this.walletPublicKey = walletPublicKey;
	}

	public String getWalletPublicKey()
	{
		return walletPublicKey;
	}

	public void setKeyStoreName(String keyStoreName) 
	{
		this.keyStoreName = keyStoreName;
	}

	public String getKeyStoreName()
	{
		return keyStoreName;
	}

	public void setSftpIp(String sftpIp) 
	{
		this.sftpIp = sftpIp;
	}

	public String getSftpIp()
	{
		return sftpIp;
	}

	public void setSftpPort(String sftpPort) 
	{
		this.sftpPort = sftpPort;
	}

	public String getSftpPort()
	{
		return sftpPort;
	}

	public void setFileDirectory(String fileDirectory) 
	{
		this.fileDirectory = fileDirectory;
	}

	public String getFileDirectory()
	{
		return fileDirectory;
	}

	public void setSftpUser(String sftpUser) 
	{
		this.sftpUser = sftpUser;
	}

	public String getSftpUser()
	{
		return sftpUser;
	}

	public void setSftpPass(String sftpPass) 
	{
		this.sftpPass = sftpPass;
	}

	public String getSftpPass()
	{
		return sftpPass;
	}

	public void setPid(String pid) 
	{
		this.pid = pid;
	}

	public String getPid()
	{
		return pid;
	}

	public void setChargeCostMerId(Integer chargeCostMerId) 
	{
		this.chargeCostMerId = chargeCostMerId;
	}

	public Integer getChargeCostMerId()
	{
		return chargeCostMerId;
	}

	public void setChargeServiceCostRate(BigDecimal chargeServiceCostRate) 
	{
		this.chargeServiceCostRate = chargeServiceCostRate;
	}

	public BigDecimal getChargeServiceCostRate()
	{
		return chargeServiceCostRate;
	}

	public void setChargeInterestMerId(Integer chargeInterestMerId) 
	{
		this.chargeInterestMerId = chargeInterestMerId;
	}

	public Integer getChargeInterestMerId()
	{
		return chargeInterestMerId;
	}

	public void setProviderId(Integer providerId) 
	{
		this.providerId = providerId;
	}

	public Integer getProviderId()
	{
		return providerId;
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
            .append("taxSounrceId", getTaxSounrceId())
            .append("taxSounrceCompanyName", getTaxSounrceCompanyName())
            .append("accountPrefix", getAccountPrefix())
            .append("partnerId", getPartnerId())
            .append("whiteChannelCode", getWhiteChannelCode())
            .append("walletPublicKey", getWalletPublicKey())
            .append("keyStoreName", getKeyStoreName())
            .append("sftpIp", getSftpIp())
            .append("sftpPort", getSftpPort())
            .append("fileDirectory", getFileDirectory())
            .append("sftpUser", getSftpUser())
            .append("sftpPass", getSftpPass())
            .append("pid", getPid())
            .append("chargeCostMerId", getChargeCostMerId())
            .append("chargeServiceCostRate", getChargeServiceCostRate())
            .append("chargeInterestMerId", getChargeInterestMerId())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
