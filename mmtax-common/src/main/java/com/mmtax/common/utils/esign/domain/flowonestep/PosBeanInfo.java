package com.mmtax.common.utils.esign.domain.flowonestep;

/**
 * @description 签署区位置信息
 * @author 宫清
 * @date 2019年11月19日 下午2:48:29
 * @since JDK1.7
 */
public class PosBeanInfo {
	
	//页码信息
	private String posPage;
	
	//x坐标
	private Float posX;
	
	//y坐标
	private Float posY;

	public String getPosPage() {
		return posPage;
	}

	public void setPosPage(String posPage) {
		this.posPage = posPage;
	}

	public Float getPosX() {
		return posX;
	}

	public void setPosX(Float posX) {
		this.posX = posX;
	}

	public Float getPosY() {
		return posY;
	}

	public void setPosY(Float posY) {
		this.posY = posY;
	}

	public PosBeanInfo(String posPage, Float posX, Float posY) {
		this.posPage = posPage;
		this.posX = posX;
		this.posY = posY;
	}

	public PosBeanInfo() {
	}
	
	
}
