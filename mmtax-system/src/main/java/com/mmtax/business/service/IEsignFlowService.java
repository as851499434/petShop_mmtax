package com.mmtax.business.service;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.CustomerProtocol;
import com.mmtax.business.domain.EsignFlow;
import com.mmtax.business.domain.PersonalMerchant;
import com.mmtax.business.dto.NotifySignDTO;
import com.mmtax.business.dto.NotifySignReqDTO;
import com.mmtax.business.dto.SignCheckPassDTO;
import com.mmtax.business.dto.SignInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 签约流程 服务层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
public interface IEsignFlowService
{
    void setTokenToRedis();

    Map batchUploadFile(MultipartFile file, SignCheckPassDTO dto);

    Map getSignErrorData(Integer currentPage,Integer pageSize,String key);

    void batchSign(String batchNo,Integer merchantId);

    String signUp(SignInfoDTO dto, String batchNo,Integer esignTemplateId,Integer merchantId);

    String signUpOfPerMer(PersonalMerchant personalMerchant,Integer esignTemplateId);

    <T> EsignFlow createFlow(Integer cusEsignId,String fileName,Integer taxSourceId,T protocol,Class<T> c
            ,String redirectUrl);

    void handNotify(String reqBody);

    JSONObject getSignDownUrl(Integer esignFlowId);

    List<JSONObject> getFlowIdByCustomerId(String idCardNo);

    NotifySignDTO apiSign(HttpServletRequest request, NotifySignReqDTO dto);

    String customerSign(Integer customerId);

}
