package com.spider.utils;

import com.mathworks.toolbox.javabuilder.MWException;
import cx_odds.CXll;
import org.apache.log4j.Logger;

public class CaiexOddsUtils {

    private static final Logger ERROR_LOGGER = LogHelper.getErrorLogger();

    private static CXll a;

    static CXll newInstance() {

        if (a != null) {
            return a;
        }
        try {
            a = new CXll();
        } catch (MWException e) {
        } catch (Throwable e) {
            LogHelper.errorLog(ERROR_LOGGER, e, "init CXll error");
        }
        return a;
    }

    /**
     * 输出sup和ttg
     *
     * @param homeOdds
     * @param sup
     * @param awayOdds
     * @param hiOdds
     * @param ttg
     * @param lowOdds
     * @param durationTime
     * @param homeScore
     * @param awayScore
     * @return
     * @throws MWException
     */
    public static double[][] calcSUPandTTG(double homeOdds, double sup, double awayOdds, double hiOdds, double ttg, double lowOdds, double durationTime,
                                           double homeScore, double awayScore) throws MWException {

        double[] ds = new double[]{durationTime, homeScore, awayScore};
        return newInstance().SUPandTTG(homeOdds, sup, awayOdds, hiOdds, ttg, lowOdds, ds, 0.07, 0.5);
    }

    public static double[][] calcHadOdds(double sup, double ttg, double durationTime, double homeScore, double awayScore) throws MWException {

        return newInstance().calcOdds(sup, ttg, 1, 0, new double[]{durationTime, homeScore, awayScore}, 0.07, 0.5, 0.88);
    }

    public static double[][] calcHhadOdds(double sup, double ttg, double durationTime, double homeScore, double awayScore, double handicapLine) throws MWException {

        // 1. SUP
        // 2. TTG
        // 3. 输出结果类型 1.HAD 4.HHAD 0.所有
        // 4. 让球数
        // 5. 当前赛事时间 默认为0
        // 6. 同上边7
        // 7. 默认0.5
        // 8. 默认0.88
        // 9. 比赛总时长 默认94分钟
        return newInstance().calcOdds(sup, ttg, 4, handicapLine, new double[]{durationTime, homeScore, awayScore}, 0.07, 0.5, 0.88);
    }

}
