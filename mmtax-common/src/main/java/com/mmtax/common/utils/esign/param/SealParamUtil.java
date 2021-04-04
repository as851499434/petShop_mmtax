package com.mmtax.common.utils.esign.param;

import com.alibaba.fastjson.JSONObject;

public class SealParamUtil {

	/**
	 * 不允许外部创建实例
	 */
	private SealParamUtil() {
	}

	// ------------------------------公有方法start--------------------------------------------
	// -----------------------------------------------------------------------------------------------印章管理相关start---------------

	/**
	 * @description 创建个人模板印章
	 * 
	 *              待填充参数：
	 *              <p>
	 *              alias: 印章别名【可空】
	 *              <p>
	 *              color: 印章颜色【必填】
	 *              <p>
	 *              height:印章高度，默认95px【可空】
	 *              <p>
	 *              width：印章宽度，默认95px【可空】
	 *              <p>
	 *              type:模板类型【必填】
	 * 
	 * @return
	 * @date 2019年7月15日 上午10:40:50
	 * @author 宫清
	 */
	public static String addPersonTemplateSealParam(String alias, String color, Integer height, Integer width,
			String type) {
		JSONObject json = new JSONObject();
		json.put("alias", alias);
		json.put("color", color);
		json.put("height", height);
		json.put("width", width);
		json.put("type", type);
		return json.toString();
	}

	/**
	 * @description 创建机构模板印章
	 * 
	 *              待填充参数：
	 *              <p>
	 *              alias: 印章别名【可空】
	 *              <p>
	 *              color:印章颜色【必填】
	 *              <p>
	 *              height：印章高度，默认159【可空】
	 *              <p>
	 *              width:印章宽度，默认159【可空】
	 *              <p>
	 *              htext：横向文【可空】
	 *              <p>
	 *              qtext:下弦文【可空】
	 *              <p>
	 *              type: 模板类型【必填】
	 *              <p>
	 *              central：中心图案类型【必填】
	 * 
	 * @return
	 * @date 2019年7月15日 上午10:50:56
	 * @author 宫清
	 */
	public static String addOrgTemplateSealParam(String alias, String color, Integer height, Integer width,
			String htext, String qtext, String type, String central) {
		JSONObject json = new JSONObject();
		json.put("alias", alias);
		json.put("color", color);
		json.put("height", height);
		json.put("width", width);
		json.put("htext", htext);
		json.put("qtext", qtext);
		json.put("type", type);
		json.put("central", central);
		return json.toString();
	}

	/**
	 * @description 创建个人/机构图片印章
	 * 
	 *              待填充参数：
	 *              <p>
	 *              alias: 印章别名【可空】
	 *              <p>
	 *              height: 印章高度，个人印章默认95，企业印章默认159【可空】
	 *              <p>
	 *              width: 印章宽度，个人印章默认95，企业印章默认159【可空】
	 *              <p>
	 *              width: 印章宽度【可空】
	 *              <p>
	 *              type: 印章数据类型【可空】
	 *              <p>
	 *              data: 印章数据【可空】
	 *              <p>
	 *              transparentFlag:
	 *              是否对图片进行透明化处理，如图片有背景颜色，直接使用会遮挡文字，建议进行透明化处理，默认false，不做任何处理【可空】
	 * 
	 * @return
	 * @date 2019年7月15日 上午11:10:39
	 * @author 宫清
	 */
	public static String addPicSealParam(String alias, Integer height, Integer width, String type, String data,
			Boolean transparentFlag) {
		JSONObject json = new JSONObject();
		json.put("alias", alias);
		json.put("height", height);
		json.put("width", width);
		json.put("type", type);
		json.put("data", data);
		json.put("transparentFlag", transparentFlag);
		return json.toString();
	}

	// -----------------------------------------------------------------------------------------------印章管理相关end-----------------

	// ------------------------------公有方法end----------------------------------------------

}
