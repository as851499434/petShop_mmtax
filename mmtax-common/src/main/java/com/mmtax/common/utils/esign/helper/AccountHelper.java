package com.mmtax.common.utils.esign.helper;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.esign.comm.ConfigConstant;
import com.mmtax.common.utils.esign.comm.HttpHelper;
import com.mmtax.common.utils.esign.comm.RequestHelper;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.enums.RequestType;
import com.mmtax.common.utils.esign.param.AccountParamUtil;

/**
 * @description 账号相关 辅助类
 * @author 宫清
 * @date 2019年7月20日 下午3:29:34
 * @since JDK1.7
 */
public class AccountHelper {

	/**
	 * 不允许外部创建实例
	 */
	private AccountHelper() {
	}


	// --------------------------------------------------------------------------------------------------个人账号相关start-----------

	/**
	 * @description 创建个人账号
	 * 
	 *              说明：
	 *              <p>
	 *              对接方调用本接口在 e 签宝平台中创建个人账号，生成的accountId请妥善保管,后续有关该用户的所有 操作都需使用该用户的
	 *              accountId。如提供用户证件信息，则将根据提供的用户证件 信息申请数字证书
	 * 
	 *              <p>
	 *              组装参数：
	 *              {@link AccountParamUtil#createPersonAcctParam(String, String, String, String, String, String)}
	 * 
	 * @return
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午4:22:37
	 */
	public static CommonRequest createPersonAcct(String thirdPartyUserId, String name, String idType, String idNumber,
													String mobile, String email) throws BusinessException {

		String param = AccountParamUtil.createPersonAcctParam(thirdPartyUserId, name, idType, idNumber, mobile, email);
		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.createPeraccUrl(), param);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 个人账号修改【 按照账号 ID】
	 *
	 *              说明：
	 *              <p>
	 *              对接方在 e 签宝平台中使用账号 id 更新个人账号，如变更姓 名，则系统根据变更后的身份信息自动申请新数字证书，
	 *              如账号已实名则会变更为未 实名状态。
	 * 
	 *              <p>
	 *              组装参数：
	 *              {@link AccountParamUtil#updatePersonAcctParam(String, String, String)}
	 *
	 * @return
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午4:58:01
	 */
	public static CommonRequest updatePersonAcctByAcctId(String accountId, String email, String mobile, String name)
			throws BusinessException {

		String param = AccountParamUtil.updatePersonAcctParam(email, mobile, name);
		JSONObject json = HttpHelper.doCommHttp(RequestType.PUT, ConfigConstant.modifyPerAccByIdUrl(accountId), param);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 个人账号修改【按照第三方用户 ID】
	 *
	 *              说明：
	 *              <p>
	 *              对接方在 e 签宝平台中使用第三方用户 id 更新个人账号 ，如 变更姓名，则系统根据变更后的身份信息自动申请新数字证书，
	 *              如账号已实名则会变 更为未实名状态。
	 * 
	 *              <p>
	 *              组装参数：
	 *              {@link AccountParamUtil#updatePersonAcctParam(String, String, String)}
	 *
	 * @return
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午4:58:01
	 */
	public static CommonRequest updatePersonAcctByThirdId(String thirdId, String email, String mobile, String name)
			throws BusinessException {

		String param = AccountParamUtil.updatePersonAcctParam(email, mobile, name);
		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.modifyPerAccByThirdIdUrl(thirdId), param);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 查询个人账号【按照账号ID】
	 * 
	 *              说明：
	 *              <p>
	 *              使用创建账号返回的账号 id 查询用户的账号
	 *
	 * @param accountId 创建返回的账号id
	 * @return
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午5:09:49
	 */
	public static CommonRequest qryPersonAcctByAcctId(String accountId) throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.GET, ConfigConstant.queryAccByIdUrl(accountId), null);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 查询个人账号【按照第三方用户 ID】
	 * 
	 *              说明：
	 *              <p>
	 *              当对接方创建账户时传入第三方平台的 userId 时，支持使用第三方平台的 userId 查询用户的账号
	 *
	 * @param thirdId 创建账户时传入的第三方用户id
	 * @return
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午5:09:49
	 */
	public static CommonRequest qryPersonAcctByThirdId(String thirdId) throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.GET, ConfigConstant.queryAccByThirdIdUrl(thirdId), null);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 注销个人账号【按照账号 ID】
	 * 
	 *              说明：
	 *              <p>
	 *              1. 通过账号 id 注销 e 签宝平台的个人账号
	 *              <p>
	 *              2. 注销后的账号不能再发起签署，已发起的流程也无法继续签署
	 *
	 * @param accountId 创建返回的账号id
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午5:13:57
	 */
	public static CommonRequest delPersonAcctByAcctId(String accountId) throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.DELETE, ConfigConstant.logoutAccByIdUrl(accountId), null);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 注销个人账号【按照第三方用户 ID】
	 * 
	 *              说明： 当对接方创建账户时传入第三方平台的 userId 时，
	 *              <p>
	 *              1. 通过第三方平台的 userId 注销 e 签宝平台的个人账号
	 *              <p>
	 *              2. 注销后的账号不能再发起签署，但可以在已发起的流程中继续进行签署
	 *
	 * @param thirdId 创建账户时传入的第三方用户id
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午5:15:54
	 */
	public static void delPersonAcctByThirdId(String thirdId) throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.DELETE, ConfigConstant.logoutAccByThirdIdUrl(thirdId), null);
		RequestHelper.castDataJson(json);
	}

	// --------------------------------------------------------------------------------------------------个人账号相关end-------------

	// --------------------------------------------------------------------------------------------------机构账号相关start-------------

	/**
	 * @description 创建机构账号
	 * 
	 *              说明：
	 *              <p>
	 *              1. 对接方调用本接口在 e 签宝平台中创建机构账号，后续有关该机构的所有操作都 需使用该机构的
	 *              accountId。如提供机构证件信息，则将根据提供的机构证件信 息申请数字证书。
	 * 
	 *              <p>
	 *              2. 由于机构是一个组织实体，机构账号时需要机构创建人执行，同时如该机构创建
	 *              人完成机构实名认证，则在签署和管理操作时需要由创建人执行，故在创建机构
	 *              时需要先调用{@link AccountHelper#createPersonAcct}接口完成机构创建人创建，并将机构创建人的账号
	 *              Id 传入接口
	 * 
	 *              组装参数：
	 *              <p>
	 *              {@link AccountParamUtil#createOrgAcctParam(String, String, String, String, String)}
	 *
	 * @return
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午5:23:31
	 */
	public static CommonRequest createOrgAcct(String thirdPartyUserId, String creatorId, String name, String idType,
                                           String idNumber) throws BusinessException {

		String param = AccountParamUtil.createOrgAcctParam(thirdPartyUserId, creatorId, name, idType, idNumber);
		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.createOrgAccUrl(), param);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 机构账号修改【按照账号 ID】
	 * 
	 *              说明：
	 *              <p>
	 *              对接方调用本接口在 e 签宝平台中使用账号 id 更新机构账号，如变更名 称后，则系统根据变更后的信息自动申请新数字证书，
	 *              如账号已实名则会变更为未实 名状态。
	 *
	 *
	 *              组装参数：
	 *              <p>
	 *              {@link AccountParamUtil#updateOrgAcct(String)}
	 * @return
	 * @author 宫清
	 * @date 2019年7月20日 下午5:43:43
	 */
	public static CommonRequest updateOrgAcctByOrgId(String orgId, String name) throws BusinessException {
		String param = AccountParamUtil.updateOrgAcct(name);
		JSONObject json = HttpHelper.doCommHttp(RequestType.PUT, ConfigConstant.modifyOrgAccByIdUrl(orgId), param);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 机构账号修改【按照第三方用户 ID】
	 * 
	 *              说明：
	 *              <p>
	 *              对接方调用本接口在 e 签宝平台中使用账号 id 更新机构账号，如变更名 称后，则系统根据变更后的信息自动申请新数字证书，
	 *              如账号已实名则会变更为未实 名状态。
	 *
	 *
	 *              组装参数：
	 *              <p>
	 *              {@link AccountParamUtil#updateOrgAcct(String)}
	 * @return
	 * @author 宫清
	 * @date 2019年7月20日 下午5:43:43
	 */
	public static CommonRequest updateOrgAcctByThirdId(String thirdId, String name) throws BusinessException {
		String param = AccountParamUtil.updateOrgAcct(name);
		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.modifyOrgAccByThirdIdUrl(thirdId), param);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 查询机构账号【按照机构ID】
	 *
	 *              说明：
	 *              <p>
	 *              使用创建账号返回的账号 id 查询机构账号
	 *
	 * @param orgId 创建机构账号返回的机构ID
	 * @return
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午5:52:06
	 */
	public static CommonRequest qryOrgAcctByOrgId(String orgId) throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.GET, ConfigConstant.queryOrgAccByIdUrl(orgId), null);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 查询机构账号【按照第三方用户 ID】
	 *
	 *              说明：
	 *              <p>
	 *              当对接方创建账户时传入第三方平台的 userId 时，支持使用第三方平台的 userId 查询机构的账号
	 *
	 * @param thirdId 第三方平台的 userId
	 * @return
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午5:52:06
	 */
	public static CommonRequest qryOrgAcctByThirdId(String thirdId) throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.GET, ConfigConstant.queryOrgAccByThirdId_URL(thirdId), null);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 注销机构账号【按照账号 ID】
	 *
	 *              说明：
	 *              <p>
	 *              通过账号 id 注销 e 签宝平台的机构账号，注销后的账号不能再发起签署， 已发起的流程也无法继续签署
	 *
	 * @param orgId 创建机构时返回的账号
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午5:55:39
	 */
	public static CommonRequest delOrgAcctByOrgId(String orgId) throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.DELETE, ConfigConstant.logoutOrgAccById_URL(orgId), null);
		return RequestHelper.castDataJson(json);
	}

	/**
	 * @description 注销机构账号【按照第三方用户 ID】
	 *
	 *              说明：
	 *              <p>
	 *              通过账号 id 注销 e 签宝平台的机构账号，注销后的账号不能再发起签署， 已发起的流程也无法继续签署
	 *
	 * @param thirdId 按照第三方用户 ID
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午5:55:39
	 */
	public static void delOrgAcctByThirdId(String thirdId) throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.DELETE, ConfigConstant.logoutOrgAccByThirdId_URL(thirdId),
				null);
		RequestHelper.castDataJson(json);
	}
	// --------------------------------------------------------------------------------------------------机构账号相关end---------------

	// --------------------------------------------------------------------------------------------------静默签署设置相关start---------

	/**
	 * @description 设置静默签署
	 * 
	 *              组装参数：
	 *              <p>
	 *              {@link AccountParamUtil#setAutoSignUrl(String)}
	 *              
	 * @param accountId 授权人 id，即个人账 号 id 或机构账号 id
	 * @param deadLine  授权截止时间, 格式为 yyyy-MM-dd HH:mm:ss
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午6:37:37
	 */
	public static CommonRequest setAutoSign(String accountId, String deadLine) throws BusinessException {
		String param = null;
		if(null != deadLine) {
			param = AccountParamUtil.setAutoSignUrl(deadLine);
		}
		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.signAuth_URL(accountId), param);
		return RequestHelper.castDataJson(json);
	}

	
	/**
	 * @description 撤销静默签署
	 *
	 * @param accountId
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午7:03:37
	 */
	public static CommonRequest revokeAutoSign(String accountId)throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.DELETE, ConfigConstant.signAuth_URL(accountId), null);
		return RequestHelper.castDataJson(json);
	}
	// --------------------------------------------------------------------------------------------------静默签署设置相关end-----------


}
