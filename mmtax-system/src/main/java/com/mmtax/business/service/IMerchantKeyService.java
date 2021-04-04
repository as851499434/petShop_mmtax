package com.mmtax.business.service;

import com.mmtax.business.domain.MerchantKey;
import com.mmtax.business.dto.ManagerSeretKeyDTO;
import com.mmtax.business.tianjindto.TianJinSecretDTO;
import com.mmtax.system.domain.SysUser;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/16 19:38
 */
public interface IMerchantKeyService {

    /**
     * 获取商户密钥列表
     *
     * @param managerSeretKeyDTO
     * @return
     */
    List<ManagerSeretKeyDTO> getListManagerSeretKey(ManagerSeretKeyDTO managerSeretKeyDTO);

    /**
     * 根据秘钥id获取秘钥
     *
     * @param id 秘钥id
     * @return
     */
    MerchantKey getMerchantKey(Integer id);


    /**
     * 根据商户id获取对接信息
     *
     * @param merchantId 商户id
     * @return
     */
    MerchantKey getMerchantKeyByMerchantId(Integer merchantId);

    /**
     * 添加ip白名单
     *
     * @param merchantKey
     */
    void updateMerchantKey(MerchantKey merchantKey);

    /**
     * 获取天津渠道信息
     * @param merchantId 商户id
     * @return
     */
    TianJinSecretDTO getTianJinSecret(Integer merchantId);

    /**
     * 更新天津去到信息
     * @param tianJinSecretDTO
     */
    void updateTianJinSecret(TianJinSecretDTO tianJinSecretDTO);
}
