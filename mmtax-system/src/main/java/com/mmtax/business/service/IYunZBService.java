package com.mmtax.business.service;

import com.mmtax.business.yunzbdto.YunZBAddMerchantNotifyDTO;
import com.mmtax.business.yunzbdto.YunZBRechargeNotifyDTO;
import com.mmtax.business.yunzbdto.YunZBSettNotyfyDTO;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/12 15:47
 */
public interface IYunZBService {
    /**
     * 商户开户通知
     *
     * @param dto 参数
     */
    void updateMerchantInfo(YunZBAddMerchantNotifyDTO dto) throws Exception;

    /**
     * 商户充值通知
     *
     * @param dto 参数
     */
    void rechargeNotify(YunZBRechargeNotifyDTO dto) throws Exception;

    /**
     * 打款通知
     *
     * @param dto 参数
     */
    Boolean settNotify(YunZBSettNotyfyDTO dto);
}
