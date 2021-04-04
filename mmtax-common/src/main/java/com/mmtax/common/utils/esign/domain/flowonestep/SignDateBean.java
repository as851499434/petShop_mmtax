package com.mmtax.common.utils.esign.domain.flowonestep;

/**
 * @description 签署日期信息
 * @author 宫清
 * @date 2019年11月19日 下午2:46:03
 * @since JDK1.7
 */
public class SignDateBean {
	
	//是否添加签署日期
	private Boolean addSignTime;
	
	//签章日期字体大小
	private Integer fontSize;
	
	//签章日期格式
	private String format;

	public Boolean getAddSignTime() {
		return addSignTime;
	}

	public void setAddSignTime(Boolean addSignTime) {
		this.addSignTime = addSignTime;
	}

	public Integer getFontSize() {
		return fontSize;
	}

	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public SignDateBean(Boolean addSignTime, Integer fontSize, String format) {
		this.addSignTime = addSignTime;
		this.fontSize = fontSize;
		this.format = format;
	}

	public SignDateBean() {
	}
	
	
}
