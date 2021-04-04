package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 费率变更表 tbl_check_and_change_rate
 * 
 * @author meimiao
 * @date 2020-11-18
 */
@Data
@ToString
@Table(name = "tbl_check_and_change_rate")
public class CheckAndChangeRate
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 商户id */
	private Integer merchantId;
	/** 商户名 */
	private String merchantName;
	/** 商户编号 */
	private String merchantCode;
	/** 直属代理商名 */
	private String agentName;
	/** 直属代理商编号 */
	private String agentNumber;
	/** 0-待审核, 1-审核通过, 2-驳回审核 */
	private Integer checkState;
	/** 原单笔普通服务费费率 */
	private BigDecimal oldOrdinaryServiceRate;
	/** 新单笔普通服务费费率 */
	private BigDecimal newOrdinaryServiceRate;
	/** 原单笔大额服务费费率 */
	private BigDecimal oldServiceBigRate;
	/** 新单笔大额服务费费率 */
	private BigDecimal newServiceBigRate;
	/**  */
	private Integer providerId;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	/** 签名*/
	private String sign;
}
