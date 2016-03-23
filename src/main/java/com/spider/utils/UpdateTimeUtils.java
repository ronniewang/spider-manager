package com.spider.utils;

import com.spider.global.Mills;

import java.util.Date;

public class UpdateTimeUtils {

    private UpdateTimeUtils() {

    }

    /**
     * 获取更新时间到现在的差距，以易读的形式显示
     *
     * @param updateTime 给定时间
     * @return 5m ago，6h ago
     */
    public static String getReadableUpdateTimeFromNow(Date updateTime) {

        long diff = new Date().getTime() - updateTime.getTime();
        return translateDiff(diff);
    }

    private static String translateDiff(long diff) {

        if (diff >= Mills.DAY) {
            return "1d ago";
        } else if (Mills.HOUR <= diff && diff < Mills.DAY) {
            return diff / Mills.HOUR + "h ago";
        } else if (Mills.MINUTE <= diff && diff < Mills.HOUR) {
            return diff / Mills.MINUTE + "m ago";
        } else if (Mills.SECOND <= diff && diff < Mills.MINUTE) {
            return diff / Mills.SECOND + "s ago";
        }
        return "just now";
    }

}
