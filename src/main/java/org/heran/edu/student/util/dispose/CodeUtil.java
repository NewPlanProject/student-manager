package org.heran.edu.student.util.dispose;

import java.util.List;

/**
 * 代码工具类
 * User: Mr.zheng
 * Date: 2017/4/20
 * Time: 9:59
 */
public class CodeUtil {

    /**
     * 判断数组中元素是否为null
     * @param objs
     * @return
     */
    public static boolean isNotNull(Object[] objs){
        if(objs != null && objs.length > 0 ){
            for(Object obj : objs){
                if(obj == null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 判断元素不为null
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj){
        return obj != null;
    }

    /**
     * 判断元素为null
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj){
        return obj == null;
    }

    /**
     * 验证字符串不为null 和 empty
     * @param str
     * @return
     */
    public static boolean isNotNullEmpty(String str){
        return str != null && !"".equals(str.trim());
    }

    /**
     * 验证字符串数组不为null 和 empty
     * @param strs
     * @return
     */
    public static boolean isNotNullEmpty(String[] strs){
        if(strs != null && strs.length > 0 ){
            for(String str : strs){
                if(str == null || "".equals(str.trim())){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 判断集合不为null 且集合size大于0
     * @param list
     * @return
     */
    public static boolean isNotNullZero(List list){
        return list != null && list.size()>0;
    }
}
