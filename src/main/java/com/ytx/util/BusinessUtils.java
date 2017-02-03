package com.ytx.util;

/**
 * Created by rock on 2016/12/28.
 * 业务工具类
 */
public class BusinessUtils {

    public static Integer buyOrSellDictionary(String str){
       str =  (str==null) ? "" : str.trim();
       if(str.equals("买")){
            return 1;
       }else if(str.equals("卖")){
           return 2;
       }else {
           return null;
       }
    }







}
