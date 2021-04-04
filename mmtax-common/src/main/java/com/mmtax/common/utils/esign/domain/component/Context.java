package com.mmtax.common.utils.esign.domain.component;

/**
 * @description 组件上下文信息  javabean，包含了名称， 填充格式，样式以及坐标 
 * @author 宫清
 * @date 2019年7月14日 下午2:43:12
 * @since JDK1.7
 */
public class Context {

	// 组件显示名称
	private String label;

	//是否必填，默认true
	private Boolean required;

	//组件格式校验规则;数字格式如：#,#00.0#   日期格式如：yyyy-MM-dd
	private String limit;

	//组件样式
	private Style style;

	//组件位置
	private Pos pos;
	
	public Context(String label, Boolean required, String limit, Style style, Pos pos) {
		this.label = label;
		this.required = required;
		this.limit = limit;
		this.style = style;
		this.pos = pos;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Pos getPos() {
		return pos;
	}

	public void setPos(Pos pos) {
		this.pos = pos;
	}

}
