package com.sinochem.androidarchitecture.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.http.PUT;

/**
 * @author jackydu
 * @date 2019/2/21
 */
public final class TimeUtils {


    public static long parseTime(String rule,String time){
        long result = 0;
        SimpleDateFormat sf = new SimpleDateFormat(rule);
        try {
            time = time.replace("Z","" );
            result = sf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String formatTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String format = dateFormat.format(new Date());
        return format;
    }

}
