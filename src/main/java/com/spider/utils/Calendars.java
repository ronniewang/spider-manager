package com.spider.utils;

import com.google.common.base.Preconditions;

import java.util.Calendar;
import java.util.Date;

/**
 * Calendar utils
 *
 * @author wsy
 */
public class Calendars {

    public static Date getTodayEleven() {

        Calendar instance = Calendar.getInstance();
        return set(instance, instance.get(Calendar.DAY_OF_MONTH), 11, 0, 0).getTime();
    }

    /**
     * 24小时制
     *
     * @param calendar not null
     * @param day      1-31
     * @param hour     0-23
     * @param minute   0-59
     * @param second   0-59
     * @return
     */
    private static Calendar set(Calendar calendar, int day, int hour, int minute, int second) {

        Preconditions.checkNotNull(calendar);
        Preconditions.checkArgument(0 <= hour && hour < 24);
        Preconditions.checkArgument(0 <= minute && minute < 60);
        Preconditions.checkArgument(1 <= day && day <= 31);
        Preconditions.checkArgument(0 <= second && second < 60);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}
