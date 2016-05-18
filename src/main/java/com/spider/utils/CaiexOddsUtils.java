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

}
