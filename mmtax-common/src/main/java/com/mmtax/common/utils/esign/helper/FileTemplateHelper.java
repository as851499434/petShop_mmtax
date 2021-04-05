//package com.mmtax.common.utils.esign.helper;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.mmtax.common.exception.BusinessException;
//import com.mmtax.common.utils.esign.comm.ConfigConstant;
//import com.mmtax.common.utils.esign.comm.FileHelper;
//import com.mmtax.common.utils.esign.comm.HttpHelper;
//import com.mmtax.common.utils.esign.comm.RequestHelper;
//import com.mmtax.common.utils.esign.domain.request.CommonRequest;
//import com.mmtax.common.utils.esign.enums.RequestType;
//import com.mmtax.common.utils.esign.param.FileTemplateParamUtil;
//
//import java.io.IOException;
//import java.util.Map;
//
///**
// * @description 文件模板相关辅助类
// * @author 宫清
// * @date 2019年7月20日 下午7:05:42
// * @since JDK1.7
// */
//public class FileTemplateHelper {
//
//	private FileTemplateHelper() {
//	}
//
//	// -----------------------------------------------------------------------------------------------文件管理相关start--------------
//
//	/**
//	 * @description 通过上传方式创建文件
//	 *
//	 *              说明:
//	 *              <p>
//	 *              为隔离业务数据流和文件流，e 签宝采用文件直传的方式完成用户文件的上传。对 接方通过该方法获取文件上传的授权地址
//	 *              <p>
//	 *              对接方直接使用本地待签文件进行签署时，需先调用该接口完成本地文件上传 e 签宝
//	 *              <p>
//	 *              对接方通过接口创建文件模板时，需要先调用该接口完成模板文件上传 e 签宝
//	 *              <p>
//	 *              目前仅支持文件格式: pdf
//	 *
//	 *
//	 *              组装参数：
//	 *              <p>
//	 *              {@link FileTemplateParamUtil#getUploadUrlParam(String, String, String)}
//	 *
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月20日 下午7:13:58
//	 */
//	public static CommonRequest createFileByUpload(String filePath, String fileName, String accountId)
//			throws BusinessException {
//
//		String param = FileTemplateParamUtil.getUploadUrlParam(filePath, fileName, accountId);
//		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.fileUpload_URL(), param);
//		return RequestHelper.castDataJson(json);
//	}
//
//	/**
//	 * @description 文件流上传文件
//	 *
//	 *              说明：
//	 *              <p>
//	 *              要注意正确设置文件的contentMd5、文件MIME以及字节流等信息，否则会导致Http状态为400的异常
//	 *
//	 *
//	 * @param filePath  文件路径
//	 * @param uploadUrl 上传方式创建文件时返回的uploadUrl
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @throws IOException
//	 * @date 2019年7月20日 下午8:26:03
//	 */
//	public static void streamUpload(String filePath, String uploadUrl) throws BusinessException {
//
//		byte[] bytes = FileHelper.getBytes(filePath);
//		String contentMd5 = FileHelper.getContentMD5(filePath);
//		JSONObject json = HttpHelper.doUploadHttp(RequestType.PUT, uploadUrl, bytes, contentMd5, ConfigConstant.PDF_TYPE);
//		RequestHelper.castDataJson(json);
//	}
//
//	/**
//	 * @description 获取文件下载地址
//	 *
//	 * @param fileId 文件id
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月20日 下午8:59:08
//	 */
//	public static CommonRequest getDownLoadUrl(String fileId) throws BusinessException {
//		JSONObject json = HttpHelper.doCommHttp(RequestType.GET, ConfigConstant.fileDownloadByFileId_URL(fileId), null);
//		return RequestHelper.castDataJson(json);
//	}
//	// -----------------------------------------------------------------------------------------------文件管理相关end----------------
//
//	// -----------------------------------------------------------------------------------------------模板管理相关start--------------
//
//	/**
//	 * @description 通过上传方式创建模板
//	 *
//	 *              组装参数：
//	 *              <p>
//	 *              {@link FileTemplateParamUtil#addTemplateByUploadUrlParam(String, String)}
//	 *
//	 * @param filePath 模板路径
//	 * @param fileName 模板名称
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月20日 下午9:09:59
//	 */
//	public static CommonRequest createTemplateByUpload(String filePath, String fileName) throws BusinessException {
//
//		String param = FileTemplateParamUtil.addTemplateByUploadUrlParam(filePath, fileName);
//		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.createTemplateByUpload_URL(), param);
//		return RequestHelper.castDataJson(json);
//	}
//
//	/**
//	 * @description 添加输入项组件
//	 *
//	 *              组装参数：
//	 *              <p>
//	 *              {@link FileTemplateParamUtil#addTemplateComponentsParam()}
//	 *
//	 * @param templateId 模板id，通过上传方式创建模板时返回
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月20日 下午9:18:08
//	 */
//	@SuppressWarnings("unchecked")
//	public static CommonRequest addComponents(String templateId) throws BusinessException {
//
//		String param = FileTemplateParamUtil.addTemplateComponentsParam();
//		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.addInputNodes_URL(templateId), param);
//		return RequestHelper.castDataJson(json);
//	}
//
//	/**
//	 * @description 删除输入项组件
//	 *
//	 * @param templateId 模板ID
//	 * @param ids        输入项组件id集合，逗号分隔
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月20日 下午9:41:24
//	 */
//	public static void delComponents(String templateId, String ids) throws BusinessException {
//		HttpHelper.doCommHttp(RequestType.DELETE, ConfigConstant.deleteInputNodes_URL(templateId, ids), null);
//	}
//
//	/**
//	 * @description 查询输入项详情
//	 *
//	 * @param templateId 模板ID
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月20日 下午9:49:16
//	 */
//	public static CommonRequest qryComponents(String templateId) throws BusinessException {
//		JSONObject json = HttpHelper.doCommHttp(RequestType.GET, ConfigConstant.queryInputNodes_URL(templateId), null);
//		return RequestHelper.castDataJson(json);
//	}
//
//	/**
//	 * @description 通过模板创建文件
//	 *
//	 * @param templateId 模板ID
//	 * @return
//	 * @throws BusinessException
//	 * @author 宫清
//	 * @date 2019年7月20日 下午9:49:16
//	 */
//	public static CommonRequest createByTemplate(String templateId, String name, Map<String,String> simpleFormFields)
//			throws BusinessException {
//		JSONObject jsonReq = new JSONObject();
//		jsonReq.put("name", name);
//		jsonReq.put("templateId",templateId);
//		jsonReq.put("simpleFormFields",simpleFormFields);
//		JSONObject json = HttpHelper.doCommHttp(RequestType.POST, ConfigConstant.createFileByTemplate_URL()
//				, jsonReq.toJSONString());
//		return RequestHelper.castDataJson(json);
//	}
//
//	// -----------------------------------------------------------------------------------------------模板管理相关end----------------
//
//}
