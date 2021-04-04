package com.mmtax.common.utils.esign.domain.component;

/**
 * @description 模板文件组件 javabean
 * @author 宫清
 * @date 2019年7月14日 下午3:14:57
 * @since JDK1.7
 */
public class StructComponent {
	
	//输入项组件 id，使用时可用 id 填充，为空时表示添加，不为空时表示修改
	private String id;
	
	//模板下输入项组件唯一标识，使用模板时也可用根据 key 值填充
	private String key;
	
	//输入项组件类型，1-文本，2-数字,3-日期，6-签约区
	private int type;
	
	//组件上下文信息
	private Context context;

	public StructComponent(String id, String key, int type, Context context) {
		this.id = id;
		this.key = key;
		this.type = type;
		this.context = context;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
	
	
}
