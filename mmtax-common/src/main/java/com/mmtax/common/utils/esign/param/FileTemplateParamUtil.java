package com.mmtax.common.utils.esign.param;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.esign.comm.ConfigConstant;
import com.mmtax.common.utils.esign.comm.FileHelper;
import com.mmtax.common.utils.esign.domain.component.Context;
import com.mmtax.common.utils.esign.domain.component.Pos;
import com.mmtax.common.utils.esign.domain.component.StructComponent;
import com.mmtax.common.utils.esign.domain.component.Style;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @description 文件模板管理相关参数组装 工具类
 * @author 宫清
 * @date 2019年7月15日 上午11:02:28
 * @since JDK1.7
 */
public class FileTemplateParamUtil {

	/**
	 * 不允许外部创建实例
	 */
	private FileTemplateParamUtil() {
	}

	// ------------------------------公有方法start----------------------------------------------

	// -----------------------------------------------------------------------------------------------文件管理相关start----------------

	/**
	 * @description 通过上传文件方式创建文件【本地文件上传 / 模板文件上传】，仅支持 ".pdf" 文件 参数
	 * 
	 *              待填充参数：
	 *              <p>
	 *              contentMd5：先计算文件md5值，再对该md5值进行base64编码【必填】
	 *              <p>
	 *              contentType：目标文件的MIME类型【必填】
	 *              <p>
	 *              fileName：文件名称（必须带上文件扩展名，必然会导致后续发起流程校验过不去，例如："合同.pdf" ）【必填】
	 *              <p>
	 *              fileSize:文件大小【必填】
	 *              <p>
	 *              accountId：所属账号 id，即个人账号 id 或 机构账号 id，如不传，则默认属 于对接平台【可空】
	 *
	 * @param filePath  {@link String} 文件路径
	 * @param accountId {@link String} 账号Id
	 * @return
	 * @author 宫清
	 * @throws BusinessException
	 * @date 2019年7月14日 下午1:26:56
	 */
	public static String getUploadUrlParam(String filePath, String fileName, String accountId) throws BusinessException {
		File file = new File(filePath);
		if(!file.exists()) {
			throw new BusinessException("文件不存在");
		}
		
		JSONObject json = new JSONObject();
		json.put("contentMd5", FileHelper.getContentMD5(filePath));
		json.put("contentType", ConfigConstant.PDF_TYPE);
		json.put("fileName", fileName);
		json.put("fileSize", String.valueOf(file.length()));
		json.put("accountId", accountId);
		return json.toString();
	}

	/**
	 * @description 通过模板创建文件 参数
	 *
	 *              待填充参数：
	 *              <p>
	 *              name:文件名称【必填】
	 *              <p>
	 *              templateId:模板编号, 可通过 e 签宝网站->企业模板下 创建和查询【必填】
	 *              <p>
	 *              simpleFormFields:输入项填充内容，key:value 传入；可使用 输入项组件 id+填充内容，也可使用输入项
	 *              组件 key+填充内容方式填充【可空】
	 * 
	 * 
	 * @return
	 * @author 宫清
	 * @date 2019年7月14日 下午2:11:42
	 */
	public static String createFileByTemplateParam(String name, String templateId,
			Map<String, String> simpleFormFields) {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("templateId", templateId);
		json.put("simpleFormFields", simpleFormFields);
		return json.toString();
	}

	// -----------------------------------------------------------------------------------------------文件管理相关end----------------

	// -----------------------------------------------------------------------------------------------模板管理相关start--------------

	/**
	 * @description 通过上传方式创建模板
	 * 
	 *              待填充参数：
	 *              <p>
	 *              contentMd5：模板文件md5值【必填】
	 *              <p>
	 *              contentType：目标文件的 MIME 类型【必填】
	 *              <p>
	 *              fileName：文件名称，必须带 扩展名 如:.pdf,.doc,.docx【必填】
	 *              <p>
	 *              convert2Pdf：是否需要转成 pdf，如果模板文 件为.doc/.docx 传 true，为 pdf 传
	 *              false【必填】
	 *
	 *
	 * @param filePath {@link String} 模板文件路径
	 * @param fileName {@link String} 模板文件名称
	 * @return
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月14日 下午2:24:25
	 */
	public static String addTemplateByUploadUrlParam(String filePath, String fileName) throws BusinessException {
		JSONObject json = new JSONObject();

		json.put("contentMd5", FileHelper.getContentMD5(filePath));
		json.put("contentType", ConfigConstant.PDF_TYPE);
		json.put("fileName", fileName);

		boolean convert2Pdf = false;
		if (StringUtils.isBlank(fileName) || !fileName.endsWith(".pdf")) {
			convert2Pdf = true;
		}

		json.put("convert2Pdf", convert2Pdf);
		return json.toString();
	}

	/**
	 * @description 添加输入项组件
	 *
	 *              待填充参数说明：
	 *              <p>
	 *              structComponent:组件信息【必填】
	 *
	 * @return
	 * @author 宫清
	 * @date 2019年7月14日 下午3:33:41
	 */
	public static String addTemplateComponentsParam() {
		// 创建输入项组件信息集合，并转化为JSON数组
		JSONArray jarr = JSONArray.parseArray(JSON.toJSONString(buildComponents()));
		JSONObject json = new JSONObject();
		json.put("structComponent", jarr);
		return json.toString();
	}

	// -----------------------------------------------------------------------------------------------模板管理相关end-----------------

	// ------------------------------公有方法end----------------------------------------------

	// ------------------------------私有方法start--------------------------------------------

	/**
	 * @description 组装模板组件集合（这里只使用一个组件举例）
	 * 
	 *              简要说明：
	 *              <p>
	 *              Lists.newArrayList是guava的集合工具方法，可直接传入元素进行初始化集合，多个元素用逗号隔开
	 * 
	 * 
	 *              使用对象：对应 cn.tsign.hz.domain.component 包
	 *              对象设置参数时，请参考接口文档中该接口的参数入参设置规则
	 *              <p>
	 *              {@link Pos} 自定义位置信息对象
	 *              <p>
	 *              {@link Style} 自定义组件样式对象
	 *              <p>
	 *              {@link Context} 自定义组件上下文信息对象
	 *              <p>
	 *              {@link StructComponent} 自定义组件信息封装对象
	 * 
	 * @return
	 * @author 宫清
	 * @date 2019年7月14日 下午3:32:43
	 */
	private static List<StructComponent> buildComponents() {
		// 位置信息
		Pos pos = new Pos(1, 170, 682);
		// 组件样式
		Style style = new Style(100, 24, null, null, null);
		// 上下文信息
		Context context = new Context("租用者", null, null, style, pos);
		// 组件包装
		StructComponent structComponent = new StructComponent(null, null, 1, context);
		return Lists.newArrayList(structComponent);
	}

	// ------------------------------私有方法end----------------------------------------------

}
