package com.mmtax.common.utils.esign.domain.component;

/**
 * @description 坐标 javabean
 * @author 宫清
 * @date 2019年7月14日 下午2:37:18
 * @since JDK1.7
 */
public class Pos {
	//页码
	private int page;
	
	//坐标X,左下角为原点，
	private float x;
	
	//坐标Y，左下角为原点
	private float y;
	
	public Pos(int page, float x, float y) {
		this.page = page;
		this.x = x;
		this.y = y;
	}
	
	public Pos() {
	}


	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}
