package com.ytx.util;

/**
 * Created by rock on 2016/12/22.
 */
public class StringUtil {

    public static String getEmptyString(){
        return "";
    }

    /**
     * @param str
     * @return:null|""返回true
     */
    public static boolean isEmpty(String str){
        if(str != null && !("".equals(str))){
            return false;
        }
        return true;
    }

}
