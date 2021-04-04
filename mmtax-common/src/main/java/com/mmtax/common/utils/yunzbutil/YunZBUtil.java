package com.mmtax.common.utils.yunzbutil;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/8 15:49
 */
public class YunZBUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(YunZBUtil.class);

    /**
     * 远程接口调用
     *
     * @param methodName
     * @param reqData
     * @return
     * @throws Exception
     */
    public static Response remoteInvoke(String methodName, Object reqData, String secretKey) throws Exception {
        return remoteInvoke(methodName, reqData, false, secretKey);
    }

    /**
     * 远程接口调用
     *
     * @param methodName
     * @param reqData
     * @return
     * @throws Exception
     */
    public static Response remoteInvoke(String methodName, Object reqData, boolean verifySign, String secretKey) throws Exception {
        if (!YunZBConstants.UPLOAD_PIC.equals(methodName)){
            LOGGER.info("请求参数为：{}", com.alibaba.fastjson.JSONObject.toJSON(reqData));
        }
        // 待签名参数
        String pairsWithKey = RequestUtil.getPairs(reqData) + "key=" + secretKey;
        // 生成参数签名
        String sign = RequestUtil.getSign(pairsWithKey);
        // 设置请求签名值
        BaseRequest baseRequest = (BaseRequest) reqData;
        baseRequest.setSign(sign);
        // 请求平台接口
        String respData = RequestUtil.httpPost(YunZBConstants.serviceUrl + "/" + methodName, reqData);
        LOGGER.info("返回参数为：{}", com.alibaba.fastjson.JSONObject.toJSON(respData));
        // 设置返回数据
        Response response = new Response();
        if (verifySign) {
            // 返回结果验签
            response.setVerifyResult(RequestUtil.verify(respData, secretKey));
        }
        response.setPairsWithKey(pairsWithKey);
        response.setSignValue(sign);
        response.setReqData(reqData);
        response.setRespData(respData);
        return response;
    }
}
