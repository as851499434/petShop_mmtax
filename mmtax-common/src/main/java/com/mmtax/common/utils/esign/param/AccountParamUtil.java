package com.mmtax.common.utils.esign.param;

import com.alibaba.fastjson.JSONObject;

/**
 * @description 账号相关参数 组装工具类
 * @author 宫清
 * @date 2019年7月15日 上午10:58:37
 * @since JDK1.7
 */
public class AccountParamUtil {

	/**
	 * 不允许外部创建实例
	 */
	private AccountParamUtil() {
	}

	// --------------------------------------------------------------------------------------------------个人账号相关start-----------

	/**
	 * @description 创建个人账号 参数
	 *
	 *              待填充参数：
	 *              <p>
	 *              thirdPartyUserId: 用户唯一标识，可传入第三方平台的用户 id、证件号、手机号、邮箱等，
	 *              如果设置则作为账号唯一性字段，相同信息不可重复创建。（个人用户与机构的唯一标识不可重复）【必填】
	 *              <p>
	 *              name:姓名【必填】
	 *              <p>
	 *              idType:证件类型，默认大陆身份证【可空】
	 *              <p>
	 *              idNumber:证件号【可空】
	 *              <p>
	 *              mobile:手机号【可空】
	 *              <p>
	 *              email:邮箱地址【可空】
	 * @return
	 * @author 宫清
	 * @date 2019年7月13日 下午2:18:58
	 */
	public static String createPersonAcctParam(String thirdPartyUserId, String name, String idType, String idNumber,
			String mobile, String email) {
		JSONObject json = new JSONObject();
		json.put("thirdPartyUserId", thirdPartyUserId);
		json.put("name", name);
		json.put("idType", idType);
		json.put("idNumber", idNumber);
		json.put("mobile", mobile);
		json.put("email", email);
		return json.toString();
	}

	/**
	 * @description 个人账号修改 参数
	 * 
	 *              待填充参数：
	 *              <p>
	 *              email：邮箱地址【可空】
	 *              <p>
	 *              mobile:联系手机号【可空】
	 *              <p>
	 *              name:姓名【可空】
	 * @return
	 * @author 宫清
	 * @date 2019年7月13日 下午3:15:10
	 */
	public static String updatePersonAcctParam(String email, String mobile, String name) {
		JSONObject json = new JSONObject();
		json.put("email", email);
		json.put("mobile", mobile);
		json.put("name", name);
		return json.toString();
	}

	// --------------------------------------------------------------------------------------------------个人账号相关end-------------

	// --------------------------------------------------------------------------------------------------机构账号相关start-------------

	/**
	 * @description 创建机构账号 参数
	 * 
	 *              待填充参数：
	 *              <p>
	 *              thirdPartyUserId:机构唯一标识，可传入第三 方平台机构 id、企业证件 号、企业邮箱等如果设置则
	 *              作为账号唯一性字段，相同 id 不可重复创建。（个人用 户与机构的唯一标识不可重 复）【必填】
	 *              <p>
	 *              creatorId:创建人个人账号 id（调用个 人账号创建接口返回的 accountId）【必填】
	 *              <p>
	 *              name：机构名称【必填】
	 *              <p>
	 *              idType:机构证件类型【可空】
	 *              <p>
	 *              idNumber:机构证件号【可空】
	 * 
	 * 
	 * @return
	 * @author 宫清
	 * @date 2019年7月13日 下午3:29:37
	 */
	public static String createOrgAcctParam(String thirdPartyUserId, String creatorId, String name, String idType,
			String idNumber) {
		JSONObject json = new JSONObject();
		json.put("thirdPartyUserId", thirdPartyUserId);
		json.put("creator", creatorId);
		json.put("name", name);
		json.put("idType", idType);
		json.put("idNumber", idNumber);
		return json.toString();
	}

	/**
	 * @description 机构账号修改 参数
	 *
	 *              待填充参数：
	 *              <p>
	 *              name:机构名称【可空】
	 * @return
	 * @author 宫清
	 * @date 2019年7月13日 下午3:36:58
	 */
	public static String updateOrgAcct(String name) {
		JSONObject json = new JSONObject();
		json.put("name", name);
		return json.toString();
	}

	// --------------------------------------------------------------------------------------------------机构账号相关end---------------

	// --------------------------------------------------------------------------------------------------静默签署设置相关start---------

	/**
	 * @description 设置静默签署 参数
	 * 
	 *              待填充参数：
	 *              <p>
	 *              deadline:授权截止时间, 格式为 yyyy-MM-dd HH:mm:ss【可空】
	 * @return
	 * @author 宫清
	 * @date 2019年7月13日 下午3:50:44
	 */
	public static String setAutoSignUrl(String deadLine) {
		JSONObject json = new JSONObject();
		json.put("deadline", deadLine);
		return json.toString();
	}

	// --------------------------------------------------------------------------------------------------静默签署设置相关end-----------
}
