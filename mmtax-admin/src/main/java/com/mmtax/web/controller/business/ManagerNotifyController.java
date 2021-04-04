package com.mmtax.web.controller.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.EsignInfo;
import com.mmtax.business.dto.NotifyDTO;
import com.mmtax.business.mapper.EsignInfoMapper;
import com.mmtax.business.service.IEsignFlowService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.business.service.ITianJinService;
import com.mmtax.business.service.IYunZBService;
import com.mmtax.business.tianjindto.TianJinRequestResultDTO;
import com.mmtax.business.yunzbdto.YunZBAddMerchantNotifyDTO;
import com.mmtax.business.yunzbdto.YunZBRechargeNotifyDTO;
import com.mmtax.business.yunzbdto.YunZBSettNotyfyDTO;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.utils.esign.comm.ConfigConstant;
import com.mmtax.common.utils.esign.helper.SafeVerify;
import com.mmtax.common.utils.onlinebank.DataConvertUtil;
import com.mmtax.common.utils.onlinebank.SecurityVerifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/12 15:24
 */
@Controller
@RequestMapping("/notify")
public class ManagerNotifyController extends BaseController {

    @Value("${esign.app.secret}")
    public String APP_SECRET;

    @Autowired
    private IYunZBService yunZBService;
    @Autowired
    private ITianJinService tianJinService;
    @Autowired
    private IOnlineBankService iOnlineBankService;
    @Autowired
    private IEsignFlowService esignFlowService;
    @Autowired
    private EsignInfoMapper esignInfoMapper;


    @PostMapping("open_acc_notify")
    @ResponseBody
    public String openAccNotify(@RequestBody YunZBAddMerchantNotifyDTO dto) {
        try {
            yunZBService.updateMerchantInfo(dto);
        } catch (Exception e) {
            logger.error("/notify/open_acc_notify", e);
            return "FAIL";
        }
        return "SUCCESS";
    }


    @PostMapping("recharge_notify")
    @ResponseBody
    public String rechargeNotify(@RequestBody YunZBRechargeNotifyDTO dto) {
        try {
            yunZBService.rechargeNotify(dto);
        } catch (Exception e) {
            logger.error("/notify/recharge_notify", e);
            return "FAIL";
        }
        return "SUCCESS";
    }

    @PostMapping("sett_notify")
    @ResponseBody
    public String settNotify(@RequestBody YunZBSettNotyfyDTO dto) {
        return yunZBService.settNotify(dto) == true ? "SUCCESS" : "FAIL";
    }

    @PostMapping("settleNotify")
    @ResponseBody
    public TianJinRequestResultDTO tianjinNotify(@RequestBody JSONObject jsonObject) {
        return tianJinService.settNotify(jsonObject);
    }

    @PostMapping(value = "/receiveTranctionNotify")
    @ResponseBody
    public void receiveTranctionNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,String> map = new HashMap<>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length != 1) {
                continue;
            }
            String paramValue = paramValues[0];
            if (paramValue.length() != 0) {
                map.put(paramName, paramValue);
            }
        }

        Map<String, String> paramCharsetConvert = DataConvertUtil.paramCharsetConvert(
                request.getParameterMap(), "UTF-8");
        logger.info("json形式的入参：{}", JSON.toJSONString(paramCharsetConvert));
        String succInfo = iOnlineBankService.receiveTranctionNotify(paramCharsetConvert,map);
        response.setCharacterEncoding("utf8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.getWriter().write(succInfo);
    }

    /**
     * 签约的异步通知接口
     */
    @RequestMapping(value = "/esignReceiveTranctionNotify",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void esignReceiveTranctionNotify(HttpServletRequest request, @RequestBody(required=false) String reqContent){
        String appId = request.getHeader("X-Tsign-Open-App-Id");
        String signature = request.getHeader("X-Tsign-Open-SIGNATURE");
        String timestamp = request.getHeader("X-Tsign-Open-TIMESTAMP");
        String signatureAlgorithm = request.getHeader("X-Tsign-Open-SIGNATURE-ALGORITHM");
        logger.info("appId={},signature={},timestamp={},signatureAlgorithm={}"
                ,appId,signature,timestamp,signatureAlgorithm);
        // 回调通知推送的具体内容，即请求body体中的JSON字符串
        logger.info("签约的异步通知的请求Body: "+ reqContent);
        //验签-先去掉
        boolean validSign = SafeVerify.checkPass(request, APP_SECRET,reqContent);
        if(!validSign){
            logger.info("验签失败，直接返回");
            return;
        }
        esignFlowService.handNotify(reqContent);
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuffer headerStr = new StringBuffer();
        while (headerNames.hasMoreElements()) {
            String headName = headerNames.nextElement();
            headerStr.append(headName).append(":").append(request.getHeader(headName)).append(",");
        }
        if (',' == headerStr.charAt(headerStr.length() - 1)) {
            headerStr = headerStr.deleteCharAt(headerStr.length() - 1);
        }
        // 获取全部请求头信息
        String headers = headerStr.toString();
        logger.info("请求头Headers：" + headers);
    }

    /**
     * 测试用
     * @param notifyDTO
     */
    @PostMapping(value = "/receiveNotifyTest")
    @ResponseBody
    public void receiveNotifyTest(@RequestBody NotifyDTO notifyDTO,HttpServletResponse response) throws IOException{
        logger.info("接受到了发送的json：{}",JSON.toJSONString(notifyDTO));
        response.setCharacterEncoding("utf8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.getWriter().write("SUCCESS");
    }


}
