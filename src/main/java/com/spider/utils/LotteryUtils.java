package com.spider.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 赔率工具类
 *
 * @author wsy
 */
public class LotteryUtils {

    private static Logger errorLogger = LogHelper.getErrorLogger();

    /**
     * 数据库存的都是String类型的时间，因为有“-”等字符，所以传入List<String>，方法内部进行处理
     *
     * @param durationList
     * @return 遍历list，遇到数据转型错误, 跳过，如果最后都没有正确数据，返回null，否则返回最大值
     */
    public static Integer getMaxDurationTime(List<String> durationList) {

        Preconditions.checkArgument(CollectionUtils.isNotEmpty(durationList));
        List<Integer> intList = Lists.newArrayList();
        for (String duration : durationList) {
            try {
                if (!StringUtils.isBlank(duration)) {
                    intList.add(Integer.valueOf(duration));
                }
            } catch (NumberFormatException e) {
                LogHelper.errorLog(errorLogger, e, "getMaxDurationTime error");
            }
        }
        if (CollectionUtils.isEmpty(intList)) {
            return null;
        }
        Collections.sort(intList);
        return intList.get(intList.size() - 1);
    }

    /**
     * 计算had或者hhad的margin
     *
     * @param h numeric format, not blank
     * @param d numeric format, not blank
     * @param a numeric format, not blank
     * @return 返回xxx.xx%的形式，保留两位小数
     */
    public static String calcHadOrHhadMargin(String h, String d, String a) {

        if (StringUtils.isBlank(h) || StringUtils.isBlank(d) || StringUtils.isBlank(a)) {
            return "";
        }
        if (!isGoodFormatOdds(h) || !isGoodFormatOdds(d) || !isGoodFormatOdds(a)) {
            return "";
        }
        BigDecimal hNum = new BigDecimal(h);
        BigDecimal dNum = new BigDecimal(d);
        BigDecimal aNum = new BigDecimal(a);
        try {
            hNum = BigDecimal.ONE.divide(hNum, 5, RoundingMode.HALF_EVEN);
            dNum = BigDecimal.ONE.divide(dNum, 5, RoundingMode.HALF_EVEN);
            aNum = BigDecimal.ONE.divide(aNum, 5, RoundingMode.HALF_EVEN);
        } catch (Exception e) {
            return "-";
        }
        BigDecimal result = new BigDecimal(0);
        result = result.add(hNum).add(dNum).add(aNum).multiply(new BigDecimal(100));
        return result.setScale(2, RoundingMode.HALF_EVEN).toString() + '%';
    }

    /**
     * 计算hilo或者hdc的margin
     *
     * @param a numeric format, not blank
     * @param b numeric format, not blank
     * @return 返回xxx.xx%的形式，保留两位小数
     */
    public static String calcHiloOrHdcMargin(String a, String b) {

        if (StringUtils.isBlank(a) || StringUtils.isBlank(b)) {
            return "";
        }
        if (!isGoodFormatOdds(a) || !isGoodFormatOdds(b)) {
            return "";
        }
        BigDecimal aNum = new BigDecimal(a);
        BigDecimal bNum = new BigDecimal(b);
        aNum = BigDecimal.ONE.divide(aNum, 5, RoundingMode.HALF_EVEN);
        bNum = BigDecimal.ONE.divide(bNum, 5, RoundingMode.HALF_EVEN);
        BigDecimal result = new BigDecimal(0);
        result = result.add(aNum).add(bNum).multiply(new BigDecimal(100));
        return result.setScale(2, RoundingMode.HALF_EVEN).toString() + '%';
    }

    /**
     * 判断赔率是否格式良好，现在只判断两项 1.not blank 2.包含"-"字符
     *
     * @param odds
     * @return
     */
    public static boolean isGoodFormatOdds(String odds) {

        if (StringUtils.isBlank(odds)) {
            return false;
        }
        if (odds.contains("-")) {
            return false;
        }
        return true;
    }

    /**
     * 将彩客网抓取的开踢时间转换为对应的日期和时间
     *
     * @param win310MatchDateTime 形如 08-07 10:00
     * @return
     */
    public static Date convertWin310MatchDateTime(String win310MatchDateTime) {

        Preconditions.checkArgument(!StringUtils.isBlank(win310MatchDateTime), "参数不能为空");
        Preconditions.checkState(Pattern.matches("\\d{2}-\\d{2}\\s\\d{2}:\\d{2}", win310MatchDateTime), "日期格式不正确");
        String year = Calendar.getInstance().get(Calendar.YEAR) + "-";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return format.parse(year + win310MatchDateTime);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将彩客网抓取的开踢时间转换为对应的日期
     *
     * @param win310MatchDate 形如 08-07 10:00
     * @return 日期字符串
     */
    public static String convertWin310MatchDate(String win310MatchDate) {

        Preconditions.checkArgument(!StringUtils.isBlank(win310MatchDate), "参数不能为空");
        String year = Calendar.getInstance().get(Calendar.YEAR) + "-";
        return year + win310MatchDate.replaceAll("\\s\\d{2}:\\d{2}", "");
    }

    private LotteryUtils() {

    }

    /**
     * @param oddsTwo
     * @return if ok return result added one, else return "-"
     */
    public static String addOneToOdds(String oddsTwo) {

        if (isGoodFormatOdds(oddsTwo)) {
            return new BigDecimal(oddsTwo).add(BigDecimal.ONE).setScale(2).toString();
        } else {
            return "-";
        }
    }
}
