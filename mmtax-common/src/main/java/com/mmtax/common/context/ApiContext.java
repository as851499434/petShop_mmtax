package com.mmtax.common.context;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/5/29 9:34
 */
@Component
public class ApiContext {
    private static final String KEY_CURRENT_PROVIDER_ID = "KEY_CURRENT_PROVIDER_ID";
    private static final Map<String, Object> mContext = Maps.newConcurrentMap();

    public void setCurrentProviderId(Long providerId) {
        mContext.put(KEY_CURRENT_PROVIDER_ID, providerId);
    }

    public Long getCurrentProviderId() {
        return (Long) mContext.get(KEY_CURRENT_PROVIDER_ID);
    }
}