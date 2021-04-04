package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.CustomerInfo;
import com.mmtax.business.domain.CustomerWechatInfo;
import com.mmtax.business.domain.OnlineCustomerInfo;
import com.mmtax.business.domain.WechatInfo;
import com.mmtax.business.mapper.WechatInfoMapper;
import com.mmtax.common.constant.WechatLoginConstants;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IWechatInfoService;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 个人微信 服务层实现
 * 
 * @author meimiao
 * @date 2020-11-26
 */
@Service
public class WechatInfoServiceImpl implements IWechatInfoService
{
    @Autowired
    WechatInfoMapper wechatInfoMapper;

    @Override
    public JSONObject wechatLogin(String code) {
        Map<String,String> paramMap = new HashMap<>(4);
        paramMap.put("appid", WechatLoginConstants.APPID);
        paramMap.put("secret", WechatLoginConstants.APPSECRET);
        paramMap.put("grant_type", WechatLoginConstants.GRANT_TYPE);
        paramMap.put("code", code);
        String openIdAccessToken = HttpUtils.sendGet(WechatLoginConstants.GET_ACESSTOKEN_OPENID_HOST
                ,HttpUtils.buildUpGetParams(paramMap));
        JSONObject jsonObject = JSON.parseObject(openIdAccessToken);
        if(StringUtils.isEmpty(jsonObject.getString("openid"))){
            throw new BusinessException("请求失败");
        }
        String accessToken = jsonObject.getString("access_token");
        String openId = jsonObject.getString("openid");
        paramMap.clear();
        paramMap.put("access_token",accessToken);
        paramMap.put("openid",openId);
        paramMap.put("lang",WechatLoginConstants.LANG);
        String result = HttpUtils.sendGet(WechatLoginConstants.GET_WECHAT_INFO_HOST,HttpUtils.buildUpGetParams(paramMap));
        JSONObject resultObject = JSON.parseObject(result);
        if(StringUtils.isEmpty(resultObject.getString("openid"))){
            throw new BusinessException("请求失败");
        }
        WechatInfo wechatInfo = new WechatInfo();
        wechatInfo.setOpenId(openId);
        wechatInfo = Optional.ofNullable(wechatInfoMapper.selectOne(wechatInfo)).orElseGet(()->{
            WechatInfo info = new WechatInfo();
            info.setOpenId(openId);
            info.setCreateTime(DateUtil.date());
            info.setUpdateTime(DateUtil.date());
            wechatInfoMapper.insertSelective(info);
            return info;
        });
        resultObject.put("wechatInfoId",wechatInfo.getId());
        return resultObject;
    }
}
