package com.spider.utils;

import com.spider.global.Mills;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    private static final Logger __logger = Logger.getLogger("error_logger");

    private static SimpleDateFormat sdf = new SimpleDateFormat();

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    public static Date addDays(Date givenDate, int adds) {

        return new Date(givenDate.getTime() + Mills.DAY * ((long) adds));
    }

    public static Date add(Date givenDate, int adds, TimeUnit timeUnit) {

        if (givenDate == null) {
            return null;
        }
        return new Date(givenDate.getTime() + timeUnit.toMillis(adds));
    }

    public static String getDate(String timeType) {

        String strTime = null;
        try {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(timeType);
            strTime = simpledateformat.format(new Date());
        } catch (Exception ex) {
            __logger.error("格式化日期错误 : " + ex.getMessage());
        }
        return strTime;
    }

    public static String getDate(String timeType, Date date) {

        String strTime = null;
        try {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(timeType);
            strTime = simpledateformat.format(date);
        } catch (Exception ex) {
            __logger.error("格式化日期错误 : " + ex.getMessage());
        }
        return strTime;
    }

    public static String getDate() {

        return getDate("yyyy-MM-dd HH:mm:ss");
    }

    public static synchronized String getDateFormat(Date date, String pattern) {

        synchronized (sdf) {
            String str = null;
            try {
                sdf.applyPattern(pattern);
                str = sdf.format(date);
            } catch (Exception localException) {
            }
            return str;
        }
    }


    public static synchronized String toString(Date theDate, boolean hasTime) {

        DateFormat theFormat;
        if (hasTime) {
            theFormat = getDateTimeFormat();
        } else {
            theFormat = getDateFormat();
        }
        return toString(theDate, theFormat);
    }

    public static synchronized DateFormat getDateFormat() {

        SimpleDateFormat theDateFormat = (SimpleDateFormat) DATE_FORMAT.clone();
        theDateFormat.setLenient(false);
        return theDateFormat;
    }

    public static synchronized DateFormat getDateTimeFormat() {

        SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) DATE_TIME_FORMAT.clone();
        theDateTimeFormat.setLenient(false);
        return theDateTimeFormat;
    }

    public static synchronized String toString(Date theDate, DateFormat theDateFormat) {

        if (theDate == null)
            return "";
        return theDateFormat.format(theDate);
    }

    /**
     * 给定的格式，返回字符串
     *
     * @param format
     * @return
     */
    public static String getNowStr(String format) {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(now);
    }

    public static Date addHours(Date date, int hours) {

        return new Date(date.getTime() + Mills.HOUR * hours);
    }
}