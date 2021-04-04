package com.mmtax.common.utils.esign.domain.signarea;

/**
 * @description 签署区位置信息
 * @author 宫清
 * @date 2019年7月18日 下午3:41:47
 * @since JDK1.7
 */
public class PosBean {

	//页码信息
	private String posPage;
	
	//x坐标，默认空
	private Float posX;
	
	//y坐标
	private Float posY;
	
	//签署区宽，默认印章宽度
	private Float width;
	
	//是否添加签署时间戳，默认不添加，格式 yyyy-MM-dd
	private Boolean addSignTime;

	public PosBean(String posPage, Float posX, Float posY, Float width, Boolean addSignTime) {
		this.posPage = posPage;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.addSignTime = addSignTime;
	}
	
	public PosBean() {
	}

	public String getPosPage() {
		return posPage;
	}

	public void setPosPage(String posPage) {
		this.posPage = posPage;
	}

	public Float getPosX() {
		return posX;
	}

	public void setPoxX(Float posX) {
		this.posX = posX;
	}

	public Float getPosY() {
		return posY;
	}

	public void setPosY(Float posY) {
		this.posY = posY;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Boolean getAddSignTime() {
		return addSignTime;
	}

	public void setAddSignTime(Boolean addSignTime) {
		this.addSignTime = addSignTime;
	}
	
	
}
