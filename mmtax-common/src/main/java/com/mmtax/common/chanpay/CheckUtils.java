package com.mmtax.common.chanpay;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CheckUtils {

    private static final String PARAM_NOT_NULL_MSG = "  can not be null!";

    /**
     * 验证对象非空
     * 
     * @param o
     * @return
     */
    public static boolean isNull(Object o) {
        boolean result = false;
        if (null == o) {
            result = true;
        } else if (o instanceof List) {
            result = ((List) o).size() == 0;
        } else if (o instanceof Map) {
            result = ((Map) o).isEmpty();
        } else if (o.getClass() == String.class) {
            result = StringUtils.isEmpty(o.toString());
        }
        return result;
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    /**
     * 验证对象数组非空
     * 
     * @param o
     * @return
     */
    public static boolean isNull(Object... o) {
        boolean result = false;
        if (null != o) {
            for (Object object : o) {
                if (isNull(object)) {
                    result = true;
                    break;
                }
            }
        } else {
            result = true;
        }
        return result;
    }

    /**
     * 验证对象数组同时为空
     * 
     * @param o
     * @return
     */
    public static boolean isAllNull(Object... o) {
        boolean result = true;
        if (null != o) {
            for (Object object : o) {
                if (isNotNull(object)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 验证对象数组同时为空
     * 
     * @param o
     * @return
     */
    public static boolean isAllNotNull(Object... o) {
        boolean result = true;
        if (null != o) {
            for (Object object : o) {
                if (isNull(object)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }




    /**
     * 判断左枚举是否与右边的集合元素有一个匹配
     * 
     * @param left
     * @param right
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean inRightEnums(Enum left, Enum... right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        for (Enum enum1 : right) {
            if (left.equals(enum1)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isBaseClass(Object o){
        if(o instanceof  Integer||o instanceof Boolean||o.getClass()==int.class||o.getClass()==boolean.class ) {
            return true;
        } else{
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isBaseClass(1));
    }

    /**
     * 校验枚举字符串
     *
     * @param enumClass
     * @param enumStr
     * @return
     */
    public static boolean checkEnumStr(Class enumClass, String enumStr) {
        if (enumClass == null || enumStr == null) {
            return false;
        }
        Enum[] enums = (Enum[]) enumClass.getEnumConstants();
        for (int i = 0, l = enums.length; i < l; i++) {
            Enum _enum = enums[i];
            if (enumStr.equals(_enum.name())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 判断文件是否存在（多次尝试防止延迟挂载）
     *
     * @param fileName
     * @return
     */
    public static boolean checkExistFile(String fileName) throws InterruptedException {
        int sleepTime = 3;
        int checkExistCount = 1; //解决挂载问题
        boolean isExistFileFlag = false;
        while (!isExistFileFlag && checkExistCount <= 3){
            isExistFileFlag = isExistFile(fileName);
            if(isExistFileFlag){
                return isExistFileFlag;
            }
            Thread.sleep(sleepTime*1000);
            checkExistCount ++;
        }
        if (!isExistFileFlag) {
            return false;
        }
        return true;
    }
    
    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    public static boolean isExistFile(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }


    /**
     * 验证对象是否为NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
     * @param obj 被验证的对象
     * @param message 异常信息
     */
    @SuppressWarnings("rawtypes")
    public static void notEmpty(Object obj, String message) {
        if (obj == null){
            throw new IllegalArgumentException(message + " must be specified");
        }
        if (obj instanceof String && obj.toString().trim().length()==0){
            throw new IllegalArgumentException(message + " must be specified");
        }
        if (obj.getClass().isArray() && Array.getLength(obj)==0){
            throw new IllegalArgumentException(message + " must be specified");
        }
        if (obj instanceof Collection && ((Collection)obj).isEmpty()){
            throw new IllegalArgumentException(message + " must be specified");
        }
        if (obj instanceof Map && ((Map)obj).isEmpty()){
            throw new IllegalArgumentException(message + " must be specified");
        }
    }

    /**
     * 验证对象是否为空
     * @param obj 被验证的对象
     * @param message 异常信息
     */
    public static void notNull(Object obj, String message) {
        if (obj == null){
            throw new IllegalArgumentException(message + " must be specified");
        }
    }


    /**
     * 判断参数是否非NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
     * @param obj
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj){
        if (obj == null){
            return true;
        }
        if (obj instanceof String && obj.toString().trim().length()==0){
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj)==0){
            return true;
        }
        if (obj instanceof Collection && ((Collection)obj).isEmpty()){
            return true;
        }
        if (obj instanceof Map && ((Map)obj).isEmpty()){
            return true;
        }
        return false;
    }
}

