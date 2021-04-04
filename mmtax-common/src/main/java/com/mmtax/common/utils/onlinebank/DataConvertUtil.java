package com.mmtax.common.utils.onlinebank;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>数据转换类</p>
 * @author fjl
 * @version $Id: ParamCharsetConvert.java, v 0.1 2013-11-12 下午1:01:09 fjl Exp $
 */
public class DataConvertUtil {

    /**
     * 请求原始数据转换成Map
     * @param data
     * @param charset
     * @return
     */
    public static Map<String, String> paramCharsetConvert(Map<?, ?> data, String charset){

        Map<String, String> formattedParameters = new HashMap<>(data.size());
        for (Map.Entry<?, ?> entry : data.entrySet()) {
            if (entry.getValue() == null || Array.getLength(entry.getValue()) == 0) {
                formattedParameters.put((String) entry.getKey(), null);
            } else {
                String str = (String) Array.get(entry.getValue(), 0);
                if(StringUtils.isNotBlank(str)) {
                	formattedParameters.put((String) entry.getKey(),str);
                }
            }
        }

        return formattedParameters;
    }

    


    /**
     * 打印map
     * @param data
     * @return
     */
    public static String toString(Map<?, ?> data) {
        if (data == null || data.isEmpty()) {
            return "";
        }
        Iterator<?> it = data.keySet().iterator();
        String key = null;
        String value = null;
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        while (it.hasNext()) {
            key = (String) it.next();
            value = (String) data.get(key);
            if (!first) {
                sb.append("&");
            }
            sb.append(key).append("=").append(value);
            first = false;
        }
        return sb.toString();
    }

}
