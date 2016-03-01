package com.spider.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class HandicapLineConvertor {

    /**
     * @param key not null
     * @return 如果找到映射，返回对应的值，否则返回key
     */
    public static String getHdc(String key) {

        Preconditions.checkArgument(key != null);

        String value = hdcMap.get(StringUtils.trim(key));
        return value == null ? key : value;
    }

    /**
     * @param key not null
     * @return 如果找到映射，返回对应的值，否则返回key
     */
    public static String getHilo(String key) {

        Preconditions.checkArgument(key != null);

        String value = hiloMap.get(StringUtils.trim(key));
        return value == null ? key : value;
    }

    /**
     * 亚赔转换map
     */
    private static Map<String, String> hdcMap = new HashMap<>();

    static {
        hdcMap.put("十球", "-10.0");
        hdcMap.put("九球半/十球", "-9.75");
        hdcMap.put("九球半", "-9.5");
        hdcMap.put("九球/九球半", "-9.25");
        hdcMap.put("九球", "-9.0");
        hdcMap.put("八球半/九球", "-8.75");
        hdcMap.put("八球半", "-8.5");
        hdcMap.put("八球/八球半", "-8.25");
        hdcMap.put("八球", "-8.0");
        hdcMap.put("七球半/八球", "-7.75");
        hdcMap.put("七球半", "-7.5");
        hdcMap.put("七球/七球半", "-7.25");
        hdcMap.put("七球", "-7.0");
        hdcMap.put("六球半/七球", "-6.75");
        hdcMap.put("六球半", "-6.5");
        hdcMap.put("六球/六球半", "-6.25");
        hdcMap.put("六球", "-6.0");
        hdcMap.put("五球半/六球", "-5.75");
        hdcMap.put("五球半", "-5.5");
        hdcMap.put("五球/五球半", "-5.25");
        hdcMap.put("五球", "-5.0");
        hdcMap.put("四球半/五球", "-4.75");
        hdcMap.put("四球半", "-4.5");
        hdcMap.put("四球/四球半", "-4.25");
        hdcMap.put("四球", "-4.0");
        hdcMap.put("三球半/四球", "-3.75");
        hdcMap.put("三球半", "-3.5");
        hdcMap.put("三球/三球半", "-3.25");
        hdcMap.put("三球", "-3.0");
        hdcMap.put("两球半/三球", "-2.75");
        hdcMap.put("两球半", "-2.5");
        hdcMap.put("两球/两球半", "-2.25");
        hdcMap.put("两球", "-2.0");
        hdcMap.put("球半/两球", "-1.75");
        hdcMap.put("球半", "-1.5");
        hdcMap.put("一球/球半", "-1.25");
        hdcMap.put("一球", "-1.0");
        hdcMap.put("半球/一球", "-0.75");
        hdcMap.put("半球", "-0.5");
        hdcMap.put("平手/半球", "-0.25");
        hdcMap.put("平手", "0.0");
        hdcMap.put("受让平手/半球", "0.25");
        hdcMap.put("受让半球", "0.5");
        hdcMap.put("受让半球/一球", "0.75");
        hdcMap.put("受让一球", "1.0");
        hdcMap.put("受让一球/球半", "1.25");
        hdcMap.put("受让球半", "1.5");
        hdcMap.put("受让球半/两球", "1.75");
        hdcMap.put("受让两球", "2.0");
        hdcMap.put("受让两球/两球半", "2.25");
        hdcMap.put("受让两球半", "2.5");
        hdcMap.put("受让两球半/三球", "2.75");
        hdcMap.put("受让三球", "3.0");
        hdcMap.put("受让三球/三球半", "3.25");
        hdcMap.put("受让三球半", "3.5");
        hdcMap.put("受让三球半/四球", "3.75");
        hdcMap.put("受让四球", "4.0");
        hdcMap.put("受让四球/四球半", "4.25");
        hdcMap.put("受让四球半", "4.5");
        hdcMap.put("受让四球半/五球", "4.75");
        hdcMap.put("受让五球", "5.0");
        hdcMap.put("受让五球/五球半", "5.25");
        hdcMap.put("受让五球半", "5.5");
        hdcMap.put("受让五球半/六球", "5.75");
        hdcMap.put("受让六球", "6.0");
        hdcMap.put("受让六球/六球半", "6.25");
        hdcMap.put("受让六球半", "6.5");
        hdcMap.put("受让六球半/七球", "6.75");
        hdcMap.put("受让七球", "7.0");
        hdcMap.put("受让七球/七球半", "7.25");
        hdcMap.put("受让七球半", "7.5");
        hdcMap.put("受让七球半/八球", "7.75");
        hdcMap.put("受让八球", "8.0");
        hdcMap.put("受让八球/八球半", "8.25");
        hdcMap.put("受让八球半", "8.5");
        hdcMap.put("受让八球半/九球", "8.75");
        hdcMap.put("受让九球", "9.0");
        hdcMap.put("受让九球/九球半", "9.25");
        hdcMap.put("受让九球半", "9.5");
        hdcMap.put("受让九球半/十球", "9.75");
        hdcMap.put("受让十球", "10.0");
        hdcMap.put("10", "-10.0");
        hdcMap.put("9.5/10", "-9.75");
        hdcMap.put("9.5", "-9.5");
        hdcMap.put("9/9.5", "-9.25");
        hdcMap.put("9", "-9.0");
        hdcMap.put("8.5/9", "-8.75");
        hdcMap.put("8.5", "-8.5");
        hdcMap.put("8/8.5", "-8.25");
        hdcMap.put("8", "-8.0");
        hdcMap.put("7.5/8", "-7.75");
        hdcMap.put("7.5", "-7.5");
        hdcMap.put("7/7.5", "-7.25");
        hdcMap.put("7", "-7.0");
        hdcMap.put("6.5/7", "-6.75");
        hdcMap.put("6.5", "-6.5");
        hdcMap.put("6/6.5", "-6.25");
        hdcMap.put("6", "-6.0");
        hdcMap.put("5.5/6", "-5.75");
        hdcMap.put("5.5", "-5.5");
        hdcMap.put("5/5.5", "-5.25");
        hdcMap.put("5", "-5.0");
        hdcMap.put("4.5/5", "-4.75");
        hdcMap.put("4.5", "-4.5");
        hdcMap.put("4/4.5", "-4.25");
        hdcMap.put("4", "-4.0");
        hdcMap.put("3.5/4", "-3.75");
        hdcMap.put("3.5", "-3.5");
        hdcMap.put("3/3.5", "-3.25");
        hdcMap.put("3", "-3.0");
        hdcMap.put("2.5/3", "-2.75");
        hdcMap.put("2.5", "-2.5");
        hdcMap.put("2/2.5", "-2.25");
        hdcMap.put("2", "-2.0");
        hdcMap.put("1.5/2", "-1.75");
        hdcMap.put("1.5", "-1.5");
        hdcMap.put("1/1.5", "-1.25");
        hdcMap.put("1", "-1.0");
        hdcMap.put("0.5/1", "-0.75");
        hdcMap.put("0.5", "-0.5");
        hdcMap.put("0/0.5", "-0.25");
        hdcMap.put("0", "0.0");
        hdcMap.put("0/-0.5", "0.25");
        hdcMap.put("-0.5", "0.5");
        hdcMap.put("-0.5/-1", "0.75");
        hdcMap.put("-1", "1.0");
        hdcMap.put("-1/-1.5", "1.25");
        hdcMap.put("-1.5", "1.5");
        hdcMap.put("-1.5/-2", "1.75");
        hdcMap.put("-2", "2.0");
        hdcMap.put("-2/-2.5", "2.25");
        hdcMap.put("-2.5", "2.5");
        hdcMap.put("-2.5/-3", "2.75");
        hdcMap.put("-3", "3.0");
        hdcMap.put("-3/3.5", "3.25");
        hdcMap.put("-3.5", "3.5");
        hdcMap.put("-3.5/-4", "3.75");
        hdcMap.put("-4", "4.0");
        hdcMap.put("-4/-4.5", "4.25");
        hdcMap.put("-4.5", "4.5");
        hdcMap.put("-4.5/-5", "4.75");
        hdcMap.put("-5", "5.0");
        hdcMap.put("-5/-5.5", "5.25");
        hdcMap.put("-5.5", "5.5");
        hdcMap.put("-5.5/-6", "5.75");
        hdcMap.put("-6", "6.0");
        hdcMap.put("-6/-6.5", "6.25");
        hdcMap.put("-6.5", "6.5");
        hdcMap.put("-6.5/-7", "6.75");
        hdcMap.put("-7", "7.0");
        hdcMap.put("-7/-7.5", "7.25");
        hdcMap.put("-7.5", "7.5");
        hdcMap.put("-7.5/-8", "7.75");
        hdcMap.put("-8", "8.0");
        hdcMap.put("-8/-8.5", "8.25");
        hdcMap.put("-8.5", "8.5");
        hdcMap.put("-8.5/-9", "8.75");
        hdcMap.put("-9", "9.0");
        hdcMap.put("-9/-9.5", "9.25");
        hdcMap.put("-9.5", "9.5");
        hdcMap.put("-9.5/-10", "9.75");
        hdcMap.put("-10", "10.0");
    }

    /**
     * 大小球转换map
     */
    private static Map<String, String> hiloMap = new HashMap<>();

    static {
        hiloMap.put("0.0", "0.0");
        hiloMap.put("0/0.5", "0.25");
        hiloMap.put("0.5", "0.5");
        hiloMap.put("0.5/1", "0.75");
        hiloMap.put("1.0", "1.0");
        hiloMap.put("1/1.5", "1.25");
        hiloMap.put("1.5", "1.5");
        hiloMap.put("1.5/2", "1.75");
        hiloMap.put("2.0", "2.0");
        hiloMap.put("2/2.5", "2.25");
        hiloMap.put("2.5", "2.5");
        hiloMap.put("2.5/3", "2.75");
        hiloMap.put("3.0", "3.0");
        hiloMap.put("3/3.5", "3.25");
        hiloMap.put("3.5", "3.5");
        hiloMap.put("3.5/4", "3.75");
        hiloMap.put("4.0", "4.0");
        hiloMap.put("4/4.5", "4.25");
        hiloMap.put("4.5", "4.5");
        hiloMap.put("4.5/5", "4.75");
        hiloMap.put("5.0", "5.0");
        hiloMap.put("5/5.5", "5.25");
        hiloMap.put("5.5", "5.5");
        hiloMap.put("5.5/6", "5.75");
        hiloMap.put("6.0", "6.0");
        hiloMap.put("6/6.5", "6.25");
        hiloMap.put("6.5", "6.5");
        hiloMap.put("6.5/7", "6.75");
        hiloMap.put("7.0", "7.0");
        hiloMap.put("7/7.5", "7.25");
        hiloMap.put("7.5", "7.5");
        hiloMap.put("7.5/8", "7.75");
        hiloMap.put("8.0", "8.0");
        hiloMap.put("8/8.5", "8.25");
        hiloMap.put("8.5", "8.5");
        hiloMap.put("8.5/9", "8.75");
        hiloMap.put("9.0", "9.0");
        hiloMap.put("9/9.5", "9.25");
        hiloMap.put("9.5", "9.5");
        hiloMap.put("9.5/10", "9.75");
        hiloMap.put("10.0", "10.0");
    }

}
