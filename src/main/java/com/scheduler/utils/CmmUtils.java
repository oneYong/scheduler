package com.scheduler.utils;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by WYKIM on 2017-08-16.
 */
public class CmmUtils {

    public static String getGMTToday(){
        Calendar cal = Calendar.getInstance();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter.format(cal.getTime());
    }

    public static String getGMTTodayTime(){
        Calendar cal = Calendar.getInstance();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter.format(cal.getTime());
    }

    public static String getGMTYesterday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter.format(cal.getTime());
    }

    public static String getGMTBeforeYesterday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter.format(cal.getTime());
    }
}
