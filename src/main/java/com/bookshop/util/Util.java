package com.bookshop.util;

/**
 * @ClassName: Util
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/28 22:36
 */
public class Util {
    public static String checkStringIsEmpty(String param){
        return param==null?null:(param.equals("")?null:""+param);
    }
}
