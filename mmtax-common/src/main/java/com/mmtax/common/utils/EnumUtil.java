package com.mmtax.common.utils;


import com.mmtax.common.enums.SigningTypeEnum;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 枚举工具类
 * @author yuanligang
 * @date 2019/7/10
 */
@Slf4j
public class EnumUtil {
    /**
     * 检验参数是否在枚举范围内
     * @param enumClass 枚举类
     * @param name 枚举类名
     * @return 判断结果
     */
   public static <E extends Enum<E>>  boolean isNotExist(Class<E> enumClass,String name){
       Map<String,E> map  =cn.hutool.core.util.EnumUtil.getEnumMap(enumClass);
      if (map.containsKey(name)){
          return  false;
      }
      return true;
   }

    /**
     * 获取匹配的枚举
     * @param enumClass 枚举类
     * @param attributeClass 匹配属性类型
     * @param attribute 匹配属性
     */
    public static <E extends Enum<E>, T> E getEnumByAttributes(Class<E> enumClass, Class<T> attributeClass
            , T attribute){
        E[] enums = enumClass.getEnumConstants();
        try{
            Field[] fields = enumClass.getDeclaredFields();
            for (E one : enums) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    Class vv = field.getType();
                    if(!attributeClass.equals(vv)){
                        continue;
                    }
                    if (field.get(one) != null && ((T) field.get(one)).equals(attribute)) {
                        return one;
                    }
                }
            }
        }catch (IllegalAccessException e){
            log.error("类型匹配出错：",e);
        }
        return null;
    }

    /**
     * 检验签约类型是否在枚举范围内 暂无复用性
     * @param value 枚举类
     * @return 判断结果
     */
    public static  boolean isNotExist(int value){
        for (SigningTypeEnum en : SigningTypeEnum.values() ) {
            if (en.getValue().equals(value)){
                return false;
            }
        }
        return  true;
    }

}
