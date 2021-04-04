package com.mmtax.common.utils.business;

import com.mmtax.common.constant.ConfigConstants;
import com.mmtax.common.enums.BigRateSwitchEnum;

/**
 * 参数配置表 工具类
 * @author Ljd
 * @date 2020/8/24
 */
public class ConfigUtil {

    /**
     * @param bigRateSwitch 大额开关值
     * 获取单人单月累计发佣限额参数key
     * @return sys_config key
     */
    public static String getMonthlyLimitAmountKey(Integer bigRateSwitch) {
        if (bigRateSwitch != null && BigRateSwitchEnum.BIDRATEOPEN.getStatus().equals(bigRateSwitch)) {
            return ConfigConstants.SINGLE_MONTH_AMOUNT_LARGE;
        }
        return ConfigConstants.SINGLE_MONTH_AMOUNT;
    }
}
