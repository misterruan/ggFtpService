package com.ytx.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rock on 2016/12/27.
 */
public class DateUtil {

    /**
     *字符串转Date
     * @param dateString:eg:2016-12-26 08:08:08
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String dateString)  {
        if(StringUtil.isEmpty(dateString)){
            return null;
        }else{
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            try {
                return sdf.parse( dateString );
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    }




}
