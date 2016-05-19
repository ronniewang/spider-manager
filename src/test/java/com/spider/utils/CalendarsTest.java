package com.spider.utils;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/18.
 */
public class CalendarsTest {

    @Test
    public void testGetTodayEleven() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date elevenClockStub = calendar.getTime();
        Date elevenClockToday = Calendars.getTodayEleven();

        assertTrue(elevenClockStub.equals(elevenClockToday));
    }
}
