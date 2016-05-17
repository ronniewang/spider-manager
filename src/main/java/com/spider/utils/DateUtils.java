package com.spider.utils;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    private static final Logger __logger = Logger.getLogger("error_logger");

    private static SimpleDateFormat sdf = new SimpleDateFormat();

    public static Date add(Date givenDate, int adds, TimeUnit timeUnit) {

        if (givenDate == null) {
            return null;
        }
        return new Date(givenDate.getTime() + timeUnit.toMillis(adds));
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

    public static String getDateFormat(Date date, String pattern) {

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
}