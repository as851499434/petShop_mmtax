//package com.mmtax.common.utils.esign.helper;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mmtax.common.exception.BusinessException;
//import com.mmtax.common.utils.esign.comm.ConfigConstant;
//import com.mmtax.common.utils.esign.comm.HttpHelper;
//import com.mmtax.common.utils.esign.comm.RequestHelper;
//import com.mmtax.common.utils.esign.domain.request.CommonRequest;
//import com.mmtax.common.utils.esign.enums.RequestType;
//import com.mmtax.common.utils.esign.param.SealParamUtil;
//
///**
// * @description 印章服务相关 辅助类
// * @author 宫清
// * @date 2019年7月21日 下午3:41:02
// * @since JDK1.7
// */
//public class SealHelper {
//
//	private SealHelper() {
//	}
//
//	/**
//	 * @description 创建个人模板印章
//	 *
//	 *              说明：
//	 *              <p>
//	 *              通过模版参数，创建个人印章
//	 *
//	 *              组装参数：
//	 *              <p>
//	 *              {@link SealParamUtil#addPersonTemplateSealParam(String, String, Integer, Integer, String)}
//	 *
//	 * @param accountId 用户账号ID
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月21日 下午3:42:42
//	 */
//	public static CommonRequest createPersonTemplateSeal(String accountId, String alias, String color, Integer height,
//														 Integer width, String type) throws BusinessException {
//
//		String param = SealParamUtil.addPersonTemplateSealParam(alias, color, height, width, type);
//		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.createPerSeal_URL(accountId), param);
//		return RequestHelper.castDataJson(json);
//	}
//
//	/**
//	 * @description 创建机构模板印章
//	 *
//	 *              说明：
//	 *              <p>
//	 *              通过模版参数，创建机构印章
//	 *
//	 *              组装参数：
//	 *              <p>
//	 *              {@link SealParamUtil#addOrgTemplateSealParam(String, String, Integer, Integer, String, String, String, String)}
//	 *
//	 * @param orgId 机构账号ID
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月21日 下午3:51:56
//	 */
//	public static CommonRequest createOrgTemplateSeal(String orgId, String alias, String color, Integer height,
//                                                   Integer width, String htext, String qtext, String type, String central) throws BusinessException {
//
//		String param = SealParamUtil.addOrgTemplateSealParam(alias, color, height, width, htext, qtext, type, central);
//		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.createOfficialSeal_URL(orgId), param);
//		return RequestHelper.castDataJson(json);
//	}
//
//	/**
//	 * @description 创建个人/机构图片印章
//	 *
//	 *              组装参数：
//	 *              <p>
//	 *              {@link SealParamUtil#addPicSealParam(String, Integer, Integer, String, String, Boolean)}
//	 *
//	 *
//	 * @param accountId 个人/机构账号ID
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月21日 下午4:02:50
//	 */
//	public static CommonRequest createPicSeal(String accountId, String alias, Integer height, Integer width, String type,
//                                           String data, Boolean transparentFlag) throws BusinessException {
//
//		String param = SealParamUtil.addPicSealParam(alias, height, width, type, data, transparentFlag);
//		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.createImageSeal_URL(accountId), param);
//		return RequestHelper.castDataJson(json);
//	}
//
//	/**
//	 * @description 查询个人印章
//	 *
//	 *              说明:
//	 *              <p>
//	 *              查询个人所有印章
//	 *
//	 * @param accountId 个人账号ID
//	 * @param offset    分页起始位置
//	 * @param size      单页数量
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月21日 下午5:12:51
//	 */
//	public static CommonRequest qryPersonSeal(String accountId, int offset, int size) throws BusinessException {
//
//		JSONObject json = HttpHelper.doCommHttp(RequestType.GET,
//				ConfigConstant.queryPerSeal_URL(accountId, offset, size), null);
//		return RequestHelper.castDataJson(json);
//	}
//
//	/**
//	 * @description 查询机构印章
//	 *
//	 *              说明：
//	 *              <p>
//	 *              查询机构所有印章
//	 *
//	 * @param orgId  机构ID
//	 * @param offset 分页起始位置
//	 * @param size   单页数量
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月21日 下午5:12:51
//	 */
//	public static CommonRequest qryOrgSeal(String orgId, int offset, int size) throws BusinessException {
//
//		JSONObject json = HttpHelper.doCommHttp(RequestType.GET, ConfigConstant.queryOrgSeal_URL(orgId, offset, size),
//				null);
//		return RequestHelper.castDataJson(json);
//	}
//
//}
