package com.mmtax.common.utils.esign.comm;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;

/**
 * @description JSON 处理辅助类
 * @author 宫清
 * @date 2019年7月20日 下午5:35:31
 * @since JDK1.7
 */
public class RequestHelper {

	private RequestHelper() {
	}

	/**
	 * @description 格式化json data数据
	 *
	 * @param json
	 * @return
	 * @throws BusinessException
	 * @author 宫清
	 * @date 2019年7月20日 下午5:34:38
	 */
	@SuppressWarnings("unchecked")
	public static CommonRequest castDataJson(JSONObject json) throws BusinessException {
		CommonRequest commonRequest = new CommonRequest();
		int code = json.getIntValue("code");
		String message = json.getString("message");
		commonRequest.setCode(code);
		commonRequest.setMessage(message);
		if (code == 0) {
			JSONObject data = null;
			try {
				data = json.getJSONObject("data");
			}catch (Exception ignored){

			}
			commonRequest.setData(data);
		}
		return commonRequest;
	}
}
