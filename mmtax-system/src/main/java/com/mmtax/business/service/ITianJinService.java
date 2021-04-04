package com.mmtax.business.service;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.tianjindto.TianJinRequestResultDTO;
import com.mmtax.business.yunzbdto.YunZBSettNotyfyDTO;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/22 2:18 下午
 */
public interface ITianJinService {



    /**
     * 打款通知
     *
     * @param jsonObject 参数
     * @return
     */
    TianJinRequestResultDTO settNotify(JSONObject jsonObject);
}
