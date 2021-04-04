package com.mmtax.common.utils.esign.param;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.esign.domain.flowonestep.*;
import com.mmtax.common.utils.esign.domain.signarea.PosBean;
import com.mmtax.common.utils.esign.domain.signarea.Signfield;
import com.mmtax.common.utils.esign.domain.signflow.SignFlowStart;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @description 签署服务相关参数组装工具类
 * @author 宫清
 * @date 2019年7月15日 下午12:08:53
 * @since JDK1.7
 */
public class SignParamUtil {

	/**
	 * 不允许外部创建实例
	 */
	private SignParamUtil() {
	}
	// ------------------------------公有方法start--------------------------------------------

	// ------------------------------------------------------------------------------------------------签署流程相关start------------------------

	/**
	 * @description 签署流程创建 参数设置
	 * 
	 * @return
	 * @date 2019年7月17日 下午3:05:14
	 * @author 宫清
	 */
	public static String createSignFlowParam(SignFlowStart sfs) {
		return JSON.toJSONString(sfs);
	}

	/**
	 * @description 签署流程撤销 参数设置
	 * 
	 *              待填充参数：
	 *              <p>
	 *              revokeReason:撤销原因，默认“撤销”【可空】
	 * 
	 * @param revokeReason
	 * @return
	 * @date 2019年7月18日 下午3:07:50
	 * @author 宫清
	 */
	public static String revokeSignFlowParam(String revokeReason) {
		JSONObject json = new JSONObject();
		json.put("revokeReason", revokeReason);
		return json.toString();
	}
	// ------------------------------------------------------------------------------------------------签署流程相关end--------------------------

	// ------------------------------------------------------------------------------------------------流程文档相关start------------------------

	/**
	 * @description 流程文档添加 参数设置
	 * 
	 * 
	 *              说明：
	 *              <p>
	 *              可添加多个流程文档，这里只添加一个作为示例
	 * 
	 *              待填充参数：
	 *              <p>
	 *              fileId:文档id【必填】
	 *              <p>
	 *              encryption:是否加密，0-不加密，1-加密，默认加密【可空】
	 *              <p>
	 *              fileName：文档名称，默认文件名称
	 *              <p>
	 *              filePassword:文档密码，如果encryption为1，文档密码不能为空，默认没有密码【可空】
	 * 
	 * @param fileIds 多个fileId拼接，英文"," 隔开
	 * @throws BusinessException
	 * @return
	 * @date 2019年7月18日 下午3:20:28
	 * @author 宫清
	 */
	public static String addFlowDocParam(String fileIds) throws BusinessException {
		if (StringUtils.isBlank(fileIds)) {
			throw new BusinessException("文档ID为空");
		}
		JSONObject outterjson = new JSONObject();
		JSONArray jarr = new JSONArray();

		String[] arr = fileIds.split(",");
		for (String fileId : arr) {
			JSONObject innerjson = new JSONObject();
			innerjson.put("fileId", fileId);
			innerjson.put("encryption", null);
			innerjson.put("fileName", null);
			innerjson.put("filePassword", null);
			jarr.add(innerjson);
		}
		outterjson.put("docs", jarr);

		return outterjson.toString();
	}

	// ------------------------------------------------------------------------------------------------流程文档相关end--------------------------

	// ------------------------------------------------------------------------------------------------流程附件相关start------------------------

	/**
	 * @description 流程附件添加
	 * 
	 * 
	 *              说明：
	 *              <p>
	 *              可添加多个流程附件，这里只添加一个作为示例
	 * 
	 *              待填充参数：
	 *              <p>
	 *              attachmentName:附件名称【必填】
	 *              <p>
	 *              fileId:附件id【必填】
	 * 
	 * @param fileIds     文件ID列表
	 * @param attachNames 附件名称列表，其元素与fileIds一一对应
	 * @return
	 * @date 2019年7月18日 下午3:36:44
	 * @author 宫清
	 */
	public static String addFlowAttachParam(List<String> fileIds, List<String> attachNames) throws BusinessException {
		if (CollectionUtils.isEmpty(fileIds) || CollectionUtils.isEmpty(attachNames)) {
			throw new BusinessException("文档ID或者附件名称为空");
		}
		if (fileIds.size() != attachNames.size()) {
			throw new BusinessException("文档ID个数与附件名称个数不一致");
		}

		JSONObject outterjson = new JSONObject();
		JSONArray jarr = new JSONArray();

		for (int i = 0; i < fileIds.size(); i++) {
			JSONObject innerjson = new JSONObject();
			innerjson.put("attachmentName", attachNames.get(i));
			innerjson.put("fileId", fileIds.get(i));
			jarr.add(innerjson);
		}
		outterjson.put("attachments", jarr);
		return outterjson.toString();
	}

	// ------------------------------------------------------------------------------------------------流程附件相关end--------------------------

	// ------------------------------------------------------------------------------------------------流程签署区相关start----------------------

	/**
	 * @description 添加平台自动盖章签署区 参数设置
	 * @return
	 * @param fileIds 文件Id列表
	 * @param sealIds 印章Id列表
	 * @date 2019年7月18日 下午3:40:20
	 * @author 宫清
	 * @throws BusinessException
	 */
	public static String addPlatformSignAreaParam(List<String> fileIds, List<String> sealIds) throws BusinessException {
		JSONArray jarr = JSONArray.parseArray(JSON.toJSONString(createPlatformSignfields(fileIds, sealIds)));
		JSONObject json = new JSONObject();
		json.put("signfields", jarr);
		return json.toString();
	}

	/**
	 * @description 添加签署方自动盖章签署区 参数设置
	 * @return
	 * @param fileIds              文件Id列表
	 * @param authorizedAccountIds 授权主体账号ID列表
	 * @param sealIds              印章Id列表
	 * @date 2019年7月18日 下午3:40:20
	 * @author 宫清
	 * @throws BusinessException
	 */
	public static String addSignerAutoSignAreaParam(List<String> fileIds, List<String> authorizedAccountIds,
			List<String> sealIds) throws BusinessException {
		JSONArray jarr = JSONArray
				.parseArray(JSON.toJSONString(createSignerAutoSignfields(fileIds, authorizedAccountIds, sealIds)));
		JSONObject json = new JSONObject();
		json.put("signfields", jarr);
		return json.toString();
	}

	/**
	 * @description 添加签署方手动盖章签署区 参数设置
	 * @return
	 * @param fileIds              文件Id列表
	 * @param signerAccountIds     签署人账号ID列表
	 * @param authorizedAccountIds 授权主体账号ID列表
	 * @date 2019年7月18日 下午3:40:20
	 * @author 宫清
	 * @throws BusinessException
	 */
	public static String addSignerHandSignAreaParam(List<String> fileIds, List<String> signerAccountIds,
			List<String> authorizedAccountIds) throws BusinessException {
		JSONArray jarr = JSONArray.parseArray(
				JSON.toJSONString(createSignerHandSignfields(fileIds, signerAccountIds, authorizedAccountIds)));
		JSONObject json = new JSONObject();
		json.put("signfields", jarr);
		return json.toString();
	}

	// ------------------------------------------------------------------------------------------------流程签署区相关end----------------------

	// ------------------------------------------------------------------------------------------------流程签署人相关start--------------------

	/**
	 * @description 流程签署人催签 参数设置
	 * 
	 *              待填充参数：
	 *              <p>
	 *              accountId:催签人账户id【可空】
	 *              <p>
	 *              noticeTypes: 通知方式，逗号分割，1-短信，2-邮件 3-支付宝 4-钉钉，默认按照走流程设置【可空】
	 *              <p>
	 *              rushsignAccountId: 被催签人账号id，为空表示：催签当前轮到签署但还未签署的所有签署人【可空】
	 * 
	 * @param accoundId
	 * @param noticeTypes
	 * @param rushsignAccountId
	 * @return
	 * @date 2019年7月18日 下午6:01:01
	 * @author 宫清
	 */
	public static String rushsignersParam(String accoundId, String noticeTypes, String rushsignAccountId) {
		JSONObject json = new JSONObject();
		json.put("accountId", accoundId);
		json.put("noticeTypes", noticeTypes);
		json.put("rushsignAccountId", rushsignAccountId);
		return json.toString();
	}

	// ------------------------------------------------------------------------------------------------流程签署人相关end----------------------

	// ------------------------------------------------------------------------------------------------一步发起签署start----------------------

	/**
	 * @description 一步发起签署 -- 构建请求参数
	 *              <p>
	 * 
	 *              attachments:附件信息列表【可空】
	 *              <p>
	 *              copiers：抄送人列表【可空】
	 *              <p>
	 *              docs:待签文档信息【可空】
	 *              <p>
	 *              flowInfo:流程基本信息【可空】
	 *              <p>
	 *              signers:签署方信息列表【必填】
	 * 
	 * @return
	 * @date 2019年11月19日 下午4:06:30
	 * @author 宫清
	 */
	public static String buildOneStepFlowParam(List<Attachment> attachments, List<Copier> copiers, List<Doc> docs,
											   FlowInfo flowInfo, List<Signer> signers) {
		JSONObject json = new JSONObject();
		json.put("attachments", attachments);
		json.put("copiers", copiers);
		json.put("docs", docs);
		json.put("flowInfo", flowInfo);
		json.put("signers", signers);
		return json.toString();
	}
	// ------------------------------------------------------------------------------------------------流程签署人相关end----------------------

	// ------------------------------公有方法end----------------------------------------------

	// ------------------------------私有方法start--------------------------------------------

	/**
	 * @description 构建平台自动签署区列表数据
	 * 
	 *              设计对象：cn.tign.hz.domain.signarea 包下
	 *              <p>
	 *              {@link PosBean} 签署位置信息
	 *              <p>
	 *              {@link Signfield} 签署区数据信息
	 * 
	 * 
	 * @param fileIds 文件file id列表
	 * @param sealIds 印章id列表，与fileIds一一对应
	 * @return
	 * @date 2019年7月18日 下午5:22:53
	 * @author 宫清
	 */
	private static List<Signfield> createPlatformSignfields(List<String> fileIds, List<String> sealIds)
			throws BusinessException {
		if (CollectionUtils.isEmpty(fileIds) || CollectionUtils.isEmpty(sealIds)) {
			throw new BusinessException("印章ID与文件ID不能为空");
		}
		if (fileIds.size() != sealIds.size()) {
			throw new BusinessException("印章ID个数与文件ID个数不一致");
		}

		// 例如一个签署区
		String fileId1 = fileIds.get(0);
		String sealId1 = sealIds.get(0);

		List<Signfield> list = Lists.newArrayList();
		PosBean posBean1 = new PosBean("1", 10f, 100f, null, null);
		Signfield signfield1 = new Signfield(fileId1, null, posBean1, sealId1, null, null);

		list.add(signfield1);
		return list;
	}

	/**
	 * @description 构建用户自动签署签署区列表数据
	 * 
	 *              设计对象：cn.tign.hz.domain.signarea 包下
	 *              <p>
	 *              {@link PosBean} 签署位置信息
	 *              <p>
	 *              {@link Signfield} 签署区数据信息
	 * 
	 * 
	 * @param fileIds              文件file id列表
	 * @param authorizedAccountIds 签约主体id列表
	 * @param sealIds              印章id列表，与fileIds、authorizedAccountIds 一一对应
	 * @return
	 * @date 2019年7月18日 下午5:22:53
	 * @author 宫清
	 */
	private static List<Signfield> createSignerAutoSignfields(List<String> fileIds, List<String> authorizedAccountIds,
			List<String> sealIds) throws BusinessException {
		if (CollectionUtils.isEmpty(fileIds) || CollectionUtils.isEmpty(sealIds)
				|| CollectionUtils.isEmpty(authorizedAccountIds)) {
			throw new BusinessException("印章ID、 授权主体账号签约主体账号以及文件ID不能为空");
		}
		if (fileIds.size() != sealIds.size() || fileIds.size() != authorizedAccountIds.size()) {
			throw new BusinessException("印章ID、 授权主体账号签约主体账号以及文件ID个数不一致");
		}

		// 例如一个签署区
		String fileId1 = fileIds.get(0);
		String authorizedAccountId1 = authorizedAccountIds.get(0);
		String sealId1 = sealIds.get(0);

		List<Signfield> list = Lists.newArrayList();
		PosBean posBean1 = new PosBean("1", 10f, 100f, null, null);
		Signfield signfield1 = new Signfield(fileId1, authorizedAccountId1, null, posBean1, sealId1, null, null);

		list.add(signfield1);
		return list;
	}

	/**
	 * @description 构建用户手动签署签署区列表数据
	 * 
	 *              设计对象：cn.tign.hz.domain.signarea 包下
	 *              <p>
	 *              {@link PosBean} 签署位置信息
	 *              <p>
	 *              {@link Signfield} 签署区数据信息
	 * 
	 * 
	 * @param fileIds              文件file id列表
	 * @param authorizedAccountIds 签约主体id列表
	 * @param signerAccountIds     签署人账号ID列表，与fileIds、authorizedAccountIds 一一对应
	 * @return
	 * @date 2019年7月18日 下午5:22:53
	 * @author 宫清
	 */
	private static List<Signfield> createSignerHandSignfields(List<String> fileIds, List<String> signerAccountIds,
			List<String> authorizedAccountIds) throws BusinessException {
		if (CollectionUtils.isEmpty(fileIds) || CollectionUtils.isEmpty(signerAccountIds)) {
			throw new BusinessException(" 授权主体账号签约主体账号、签署人个人账号以及文件ID不能为空");
		}
		if ((CollectionUtils.isNotEmpty(authorizedAccountIds) && fileIds.size() != authorizedAccountIds.size())
				|| fileIds.size() != signerAccountIds.size()) {
			throw new BusinessException(" 授权主体账号签约主体账号、签署人个人账号以及文件ID个数不一致");
		}

		// 这里举一个签署区的例子
		String fileId1 = fileIds.get(0);
		String signerAccountId1 = signerAccountIds.get(0);
		String authorizedAccountId1 = CollectionUtils.isNotEmpty(authorizedAccountIds) ? authorizedAccountIds.get(0)
				: null;

		List<Signfield> list = Lists.newArrayList();
		PosBean posBean1 = new PosBean("1", 500f, 100f, null, null);
		Signfield signfield1 = new Signfield(fileId1, signerAccountId1, null, authorizedAccountId1, null, 1, posBean1,
				null, null, null);

		list.add(signfield1);
		return list;
	}

	// ------------------------------私有方法end----------------------------------------------

	// ------------------------------getter/setter方法start-----------------------------------

	// ------------------------------getter/setter方法end-------------------------------------

}
