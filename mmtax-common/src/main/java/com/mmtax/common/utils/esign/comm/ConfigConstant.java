//package com.mmtax.common.utils.esign.comm;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * @version 2.0
// * @author description: 服务地址配置样例
// */
//@Component
//public class ConfigConstant {
//
//	public static final String PDF_TYPE = "application/pdf";
//
//	/**
//	 * 催签通知方式，逗号分割，1-短信，2-邮件 3-支付宝 4-钉钉，默认按照走流程设置
//	 */
//	public static final String NOTICE_TYPES_MESSAGE = "1";
//
//	//域名
//	public static String host;
//	@Value("${esign.host}")
//	public void setHost(String host){
//		ConfigConstant.host= host;
//	}
//
//	// 项目Id(应用Id）
//	public static String appId;
//	@Value("${esign.app.id}")
//	public void setAppId(String appId){
//		ConfigConstant.appId = appId;
//	}
//	// 项目密钥(应用密钥）
//	public static String APP_SECRET;
//
//	// ------Token相关地址------
//	// 获取Token
//	public static String getTokenUrl(String appId, String secret) {
//		return host + "/v1/oauth2/access_token?appId=" + appId + "&secret=" + secret + "&grantType=client_credentials";
//	}
//
//	// 刷新Token
//	public static String refreshTokenUrl(String appId, String refreshToken) {
//		return host + "/v1/oauth2/refresh_token?appId=" + appId + "&refreshToken=" + refreshToken
//				+ "&grantType=refresh_token";
//	}
//
//	// ------账号相关地址------
//
//	// ---个人账号---
//
//	// 创建个人账号
//	public static String createPeraccUrl() {
//		return host + "/v1/accounts/createByThirdPartyUserId";
//	}
//
//	// 修改个人账号(根据账号ID)
//	public static String modifyPerAccByIdUrl(String accountId) {
//		return host + "/v1/accounts/" + accountId;
//	}
//
//	// 修改个人账号（根据第三方用户ID）
//	public static String modifyPerAccByThirdIdUrl(String thirdPartyUserId) {
//		return host + "/v1/accounts/updateByThirdId?thirdPartyUserId=" + thirdPartyUserId;
//	}
//
//	// 查询个人账号(根据账号ID)
//	public static String queryAccByIdUrl(String accountId) {
//		return host + "/v1/accounts/" + accountId;
//	}
//
//	// 查询个人账号(根据第三方用户ID)
//	public static String queryAccByThirdIdUrl(String thirdPartyUserId) {
//		return host + "/v1/accounts/getByThirdId?thirdPartyUserId=" + thirdPartyUserId;
//	}
//
//	// 注销个人账号(根据账号ID)
//	public static String logoutAccByIdUrl(String accountId) {
//		return host + "/v1/accounts/" + accountId;
//	}
//
//	// 注销个人账号(根据第三方用户ID)
//	public static String logoutAccByThirdIdUrl(String thirdPartyUserId) {
//		return host + "/v1/accounts/deleteByThirdId?thirdPartyUserId=" + thirdPartyUserId;
//	}
//
//	// ---机构账号---
//
//	// 创建机构账号
//	public static String createOrgAccUrl() {
//		return host + "/v1/organizations/createByThirdPartyUserId";
//	}
//
//	// 修改机构账号(根据账号ID)
//	public static String modifyOrgAccByIdUrl(String orgId) {
//		return host + "/v1/organizations/" + orgId;
//	}
//
//	// 修改机构账号(根据第三方用户ID)
//	public static String modifyOrgAccByThirdIdUrl(String thirdPartyUserId) {
//		return host + "/v1/organizations/updateByThirdId?thirdPartyUserId=" + thirdPartyUserId;
//	}
//
//	// 查询机构账号(根据账号ID)
//	public static String queryOrgAccByIdUrl(String orgId) {
//		return host + "/v1/organizations/" + orgId;
//	}
//
//	// 查询机构账号(根据第三方用户ID)
//	public static String queryOrgAccByThirdId_URL(String thirdPartyUserId) {
//		return host + "/v1/organizations/getByThirdId?thirdPartyUserId=" + thirdPartyUserId;
//	}
//
//	// 注销机构账号(根据账号ID)
//	public static String logoutOrgAccById_URL(String orgId) {
//		return host + "/v1/organizations/" + orgId;
//	}
//
//	// 注销机构账号(根据第三方用户ID)
//	public static String logoutOrgAccByThirdId_URL(String thirdPartyUserId) {
//		return host + "/v1/organizations/deleteByThirdId?thirdPartyUserId=" + thirdPartyUserId;
//	}
//
//	// ---签署授权---
//	// 设置/撤销静默签署(POST为设置静默授权,DELETE为撤销静默授权)
//	public static String signAuth_URL(String accountId) {
//		return host + "/v1/signAuth/" + accountId;
//	}
//
//	// ------文件相关地址------
//
//	// 获取文件直传地址
//	public static String fileUpload_URL() {
//		return host + "/v1/files/getUploadUrl";
//	}
//
//	// 文件模板创建待签文件
//	public static String createFileByTemplate_URL() {
//		return host + "/v1/files/createByTemplate";
//	}
//
//	// 获取文件下载地址(根据文件Id)
//	public static String fileDownloadByFileId_URL(String fileId) {
//		return host + "/v1/files/" + fileId;
//	}
//
//	// -----文件模板管理-------
//
//	// 通过上传方式创建模板
//	public static String createTemplateByUpload_URL() {
//		return host + "/v1/docTemplates/createByUploadUrl";
//	}
//
//	// 添加输入项组件
//	public static String addInputNodes_URL(String templateId) {
//
//		return host + "/v1/docTemplates/" + templateId + "/components";
//	}
//
//	// 删除输入项组件
//	public static String deleteInputNodes_URL(String templateId, String ids) {
//		return host + "/v1/docTemplates/" + templateId + "/components/" + ids;
//	}
//
//	// 查询模板详情
//	public static String queryInputNodes_URL(String templateId) {
//		return host + "/v1/docTemplates/" + templateId;
//	}
//
//	// ------印章相关地址------
//
//	// 创建个人模板印章
//	public static String createPerSeal_URL(String accountId) {
//		return host + "/v1/accounts/" + accountId + "/seals/personaltemplate";
//	}
//
//	// 创建机构模板印章
//	public static String createOfficialSeal_URL(String orgId) {
//		return host + "/v1/organizations/" + orgId + "/seals/officialtemplate";
//	}
//
//	// 创建个人/机构图片印章
//	public static String createImageSeal_URL(String accountId) {
//		return host + "/v1/accounts/" + accountId + "/seals/image";
//	}
//
//	// 查询个人印章
//	public static String queryPerSeal_URL(String accountId, int offset, int size) {
//		return host + "/v1/accounts/" + accountId + "/seals?offset=" + offset + "&size=" + size;
//	}
//
//	// 查询机构印章
//	public static String queryOrgSeal_URL(String orgId, int offset, int size) {
//		return host + "/v1/organizations/" + orgId + "/seals?offset=" + offset + "&size=" + size;
//	}
//
//	// ------签署相关地址------
//
//	// ---签署流程---
//	// 创建签署流程
//	public static String createFlows_URL() {
//		return host + "/v1/signflows";
//	}
//
//	// 查询签署流程
//	public static String queryFlows_URL(String flowId) {
//		return host + "/v1/signflows/" + flowId;
//	}
//
//	// 开启签署流程
//	public static String startFlows_URL(String flowId) {
//		return host + "/v1/signflows/" + flowId + "/start";
//	}
//
//	// 撤销签署流程
//	public static String revokeFlows_URL(String flowId) {
//		return host + "/v1/signflows/" + flowId + "/revoke";
//	}
//
//	// 归档签署流程
//	public static String archiveFlows_URL(String flowId) {
//		return host + "/v1/signflows/" + flowId + "/archive";
//	}
//
//	/**
//	 * 流程签署人催签
//	 * @param flowId 流程id
//	 * @return url
//	 */
//	public static String rushSignURL(String flowId) {
//		return host + "/v1/signflows/" + flowId + "/signers/rushsign";
//	}
//
//	// ---流程文档(请求方式决定增删或者下载)---
//	public static String aboutDocument_URL(String flowId, String fileIds) {
//		String url = host + "/v1/signflows/" + flowId + "/documents";
//		if (StringUtils.isNotBlank(fileIds)) {
//			url += "?fileIds=" + fileIds;
//		}
//		return url;
//	}
//
//	// ---流程附件(请式求方决定增删查)---
//	public static String aboutAttachments_URL(String flowId, String fileIds) {
//		String url = host + "/v1/signflows/" + flowId + "/attachments";
//		if (StringUtils.isNotBlank(fileIds)) {
//			url += "?fileIds=" + fileIds;
//		}
//		return url;
//	}
//
//	// ---流程签名域---
//
//	// 获得流程签名域列表
//	public static String getSignFields_URL(String flowId, String accountId, String signfieldIds) {
//		String url = host + "/v1/signflows/" + flowId + "/signfields";
//		boolean flag = false;
//		if (StringUtils.isNotBlank(accountId)) {
//			url += "?accountId=" + accountId;
//			flag = true;
//		}
//		if (StringUtils.isNotBlank(signfieldIds)) {
//			if (flag) {
//				url += "&";
//			} else {
//				url += "?";
//			}
//			url += "signfieldIds=" + signfieldIds;
//		}
//		return url;
//	}
//
//	// 添加流程签名域(对接平台自动盖章签名域)
//	public static String addAutoSignfieldsForPlatform_URL(String flowId) {
//		return host + "/v1/signflows/" + flowId + "/signfields/platformSign";
//	}
//
//	// 添加流程签名域(用户自动盖章签名域)
//	public static String addAutoSignfieldsForPerson_URL(String flowId) {
//		return host + "/v1/signflows/" + flowId + "/signfields/autoSign";
//	}
//
//	// 添加流程签名域(用户手动盖章签名域)
//	public static String addHandSignfieldsForPerson_URL(String flowId) {
//		return host + "/v1/signflows/" + flowId + "/signfields/handSign";
//	}
//
//	// 删除流程签名域
//	public static String deleteSignfields_URL(String flowId, String signfieldIds) {
//		return host + "/v1/signflows/" + flowId + "/signfields?signfieldIds=" + signfieldIds;
//	}
//
//	// ---流程签署人---
//
//	// 获取流程签署人列表
//	public static String getSignersList_URL(String flowId) {
//		return host + "/v1/signflows/" + flowId + "/signers";
//	}
//
//	// 流程签署人催签
//	public static String urgeSgin_URL(String flowId) {
//		return host + "/v1/signflows/" + flowId + "/signers/rushsign";
//	}
//
//	// 获取签署地址
//	public static String signUrl(String flowId, String accountId, String organizeId, String urlType) {
//		String url = host + "/v1/signflows/" + flowId + "/executeUrl?accountId=" + accountId;
//		if (StringUtils.isNotBlank(organizeId)) {
//			url += "&organizeId=" + organizeId;
//		}
//		if (StringUtils.isNotBlank(urlType)) {
//			url += "&urlType=" + urlType;
//		}
//		return url;
//	}
//
//	//一步发起签署
//	public static final String ONE_STEP_FLOW = host + "/api/v2/signflows/createFlowOneStep";
//
//	// ---辅助工具---
//
//	// 查询流程文档关键字坐标
//	public static String getKeywordsPosition_URL(String flowId, String fileId) {
//		StringBuffer keywordsPosition = new StringBuffer();
//		keywordsPosition.append(host).append("/v1/signflows/");
//		keywordsPosition.append(flowId);
//		keywordsPosition.append("/documents/");
//		keywordsPosition.append(fileId);
//		keywordsPosition.append("/searchWordsPosition");
//		return keywordsPosition.toString();
//	}
//
//	// ------存证服务------
//	// 定义所属行业类型
////    public static final String  defineBusiType_URL() {
//////    	String r_
////    }
////
//}
