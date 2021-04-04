package com.mmtax.business.service;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.WechatInfo;
import java.util.List;

/**
 * 个人微信 服务层
 * 
 * @author meimiao
 * @date 2020-11-26
 */
public interface IWechatInfoService
{
    /** 授权登录 */
    JSONObject wechatLogin(String code);
}
