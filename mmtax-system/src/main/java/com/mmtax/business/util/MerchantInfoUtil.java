package com.mmtax.business.util;

import com.mmtax.business.domain.Cooperation;
import com.mmtax.common.enums.SigningTypeEnum;
import com.mmtax.common.enums.SwitchEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.EnumUtil;

/**
 * 商户工具类
 * @author Ljd
 * @date 2020/10/20
 */
public class MerchantInfoUtil {
    /**
     * 校验合作信息
     * @param cooperation 合作信息
     */
    public static  void checkCooperation (Cooperation cooperation) {
        if (cooperation == null) {
            throw new BusinessException("合作信息不存在");
        }
        Integer signSwitch = cooperation.getSignSwitch();
        if (signSwitch == null) {
            throw new BusinessException("未设置签约开关");
        }
        if (SwitchEnum.ON.getCode().equals(cooperation.getSignSwitch())) {
            if(SigningTypeEnum.PAPER.getValue().equals(cooperation.getSigningType())){
                throw new BusinessException("签约开关打开，不可以选择纸质签约");
            }
        }else {
            if(!SigningTypeEnum.PAPER.getValue().equals(cooperation.getSigningType())){
                throw new BusinessException("签约开关关闭，只可以选择纸质签约");
            }
        }
        Integer signingType = cooperation.getSigningType();
        if (EnumUtil.isNotExist(signingType)) {
            throw new BusinessException("签约信息不存在");
        }
        if(SigningTypeEnum.WECHAT.getValue().equals(cooperation.getSigningType()) &&
                SwitchEnum.ON.getCode().equals(cooperation.getPromotionSwitch())){
            throw new BusinessException("微信签约类型和推广商户为是不可同时选择");
        }
    }
}
