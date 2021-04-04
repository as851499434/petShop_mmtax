package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.CheckAndChangeRate;
import com.mmtax.business.dto.ChangeRateSuccessDTO;
import com.mmtax.business.dto.ChangeTaxRateResultDTO;
import com.mmtax.business.dto.CheckAndChangeRateDTO;
import com.mmtax.business.mapper.CheckAndChangeRateMapper;
import com.mmtax.business.mapper.CooperationMapper;
import com.mmtax.business.mapper.MerchantInfoMapper;
import com.mmtax.common.constant.WkycConstants;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.MmtaxSign;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.ICheckAndChangeRateService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 费率变更 服务层实现
 * 
 * @author meimiao
 * @date 2020-11-18
 */
@Service
@Slf4j
public class CheckAndChangeRateServiceImpl implements ICheckAndChangeRateService
{
    @Autowired
    private CheckAndChangeRateMapper checkAndChangeRateMapper;
    @Autowired
    private CooperationMapper cooperationMapper;
    @Autowired
    private MerchantInfoMapper merchantInfoMapper;

    @Value("${wkyc.change.rate.url}")
    private String changeRateURL;

    @Value("${wkyc.dev.key}")
    private String appKey;

    @Override
    public List<CheckAndChangeRate> selectAllChangeRateInfo(CheckAndChangeRateDTO changeRateDTO) {
        List<CheckAndChangeRate> checkAndChangeRates = checkAndChangeRateMapper.selectAllChangeRateInfo(changeRateDTO);
        return checkAndChangeRates;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public Boolean updateRateSuccess(ChangeRateSuccessDTO changeRateSuccessDTO) {
        Integer number = checkAndChangeRateMapper.updateRateSuccess(changeRateSuccessDTO);
        //推送审核信息值悟空云创
        String paramFlag = WkycConstants.PUSH_MERCHANT_INFO_SUCCESS;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merchantCode",changeRateSuccessDTO.getMerchantCode());
        jsonObject.put("newOrdinaryServiceRate",changeRateSuccessDTO.getNewOrdinaryServiceRate());
        jsonObject.put("newBigServiceRate",changeRateSuccessDTO.getNewBigServiceRate());
        jsonObject.put("flag",paramFlag);
        String content = JSON.toJSONString(jsonObject);
        content = MmtaxSign.addSignByJsonStr(content,WkycConstants.APP_KEY);
        String callbackResult = HttpUtils.sendPost(changeRateURL, content);
        ChangeTaxRateResultDTO changeTaxRateResultDTO = JSONObject.parseObject(callbackResult, ChangeTaxRateResultDTO.class);
        if(!WkycConstants.PUSH_MERCHANT_INFO_SUCCESS.equals(changeTaxRateResultDTO.getCode())){
            log.info("审核通过信息推送失败：{}",changeTaxRateResultDTO.getDescription());
            throw new BusinessException("审核通过信息推送失败");
        }
        return number > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public Boolean updateRateFail(Integer merchantId) {
        Integer number = checkAndChangeRateMapper.updateRateFail(merchantId);
        //推送审核信息值悟空云创
        String paramFlag = WkycConstants.PUSH_MERCHANT_INFO_FAIL;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("merchantCode",merchantInfoMapper.selectMerchantCodeByMerchantId(merchantId));
        jsonObject.put("flag",paramFlag);
        String content = JSON.toJSONString(jsonObject);
        content = MmtaxSign.addSignByJsonStr(content,WkycConstants.APP_KEY);
        String callbackResult = HttpUtils.sendPost(changeRateURL, content);
        ChangeTaxRateResultDTO changeTaxRateResultDTO = JSONObject.parseObject(callbackResult, ChangeTaxRateResultDTO.class);
        if(!WkycConstants.PUSH_MERCHANT_INFO_SUCCESS.equals(changeTaxRateResultDTO.getCode())){
            log.info("驳回审核信息推送失败：{}",changeTaxRateResultDTO.getDescription());
            throw new BusinessException("驳回审核信息推送失败");
        }
        return number > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public String insertChangeRateInfo(HttpServletRequest request) throws IOException {
        String data = IOUtils.toString(request.getInputStream(), WkycConstants.UTF_8_ENCODING);
        log.info("请求参数data：{}", JSON.toJSON(data));
        CheckAndChangeRate checkAndChangeRate = JSON.parseObject(data,CheckAndChangeRate.class);
        if (StringUtils.isNull(checkAndChangeRate)){
            return WkycConstants.GET_CHANGE_RATE_INFO_FAIL;
        }
        String sign = checkAndChangeRate.getSign();
        checkAndChangeRate.setSign(null);
        String sign1 = MmtaxSign.addSignByJsonStr(JSON.toJSONString(checkAndChangeRate), appKey);
        if(!sign1.equalsIgnoreCase(sign)){
            return WkycConstants.GET_CHANGE_RATE_INFO_FAIL;
        }
        if (StringUtils.isEmpty(checkAndChangeRate.getMerchantCode())){
            return WkycConstants.GET_CHANGE_RATE_INFO_FAIL;
        }
        Integer merchantId = merchantInfoMapper.selectMerchantIdByMerchantCode(checkAndChangeRate.getMerchantCode());
        checkAndChangeRate.setMerchantId(merchantId);
        //费率变更状态待审核
        checkAndChangeRate.setCheckState(0);
        checkAndChangeRate.setCreateTime(DateUtil.date());
        checkAndChangeRate.setUpdateTime(DateUtil.date());
        checkAndChangeRateMapper.insertSelective(checkAndChangeRate);
        return WkycConstants.GET_CHANGE_RATE_INFO_SUCCESS;
    }
}
