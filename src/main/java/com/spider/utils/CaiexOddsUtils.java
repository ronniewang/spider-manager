package com.spider.utils;

import com.google.common.collect.Maps;
import com.mathworks.toolbox.javabuilder.MWException;
import com.spider.db.entity.CompanyOddsEntity;
import com.spider.db.entity.OddsModel;
import com.spider.domain.UpdateHdcOdds;
import com.spider.domain.UpdateHiloOdds;
import com.spider.manager.model.SupAndTtgModel;
import com.spider.manager.service.impl.MatchOddsServiceImpl;
import cx_odds.CXll;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CaiexOddsUtils {

    private static final Logger errorLogger = LogHelper.getErrorLogger();

    private static final Logger ERROR_LOGGER = errorLogger;

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


    public static UpdateHdcOdds buildHdcUpdate(CompanyOddsEntity odds, String matchCode) {

        return new UpdateHdcOdds(matchCode, odds.getGamingCompany(), odds.getDurationTime(), odds.getScore(),
                odds.getOddsOne(), odds.getOddsThree(), odds.getOddsTwo(), odds.getHomeRedCard(),
                odds.getAwayRedCard(), odds.getOddsUpdateTime());
    }


    public static UpdateHiloOdds buildHiloUpdate(CompanyOddsEntity odds, String matchCode) {

        return new UpdateHiloOdds(matchCode, odds.getGamingCompany(), odds.getDurationTime(), odds.getScore(),
                odds.getOddsOne(), odds.getOddsThree(), odds.getOddsTwo(),
                odds.getHomeRedCard(), odds.getAwayRedCard(), odds.getOddsUpdateTime());
    }

    public static Map<String, SupAndTtgModel> getStringSupAndTtgModelMap(OddsModel oddsModel) {

        Map<String/*liji, jbb*/, SupAndTtgModel> map = Maps.newHashMap();
        LogHelper.infoLog(LogHelper.getInfoLogger(), null, "start calc sup and ttg, oddsModel={0} ", oddsModel);
        double duration = 0.0;
        String durationTime = oddsModel.getDurationTime();
        if (!StringUtils.isBlank(durationTime)) {
            try {
                duration = Double.valueOf(durationTime);
            } catch (NumberFormatException e) {
            }
        }
        double homeScore = 0.0;
        double awayScore = 0.0;
        String score = oddsModel.getScore();
        String[] socres = score.split("-");
        if (score.length() > 1) {
            if (StringUtils.isNotBlank(socres[MatchOddsServiceImpl.ODDS_TYPE_HAD])) {
                try {
                    homeScore = Double.valueOf(socres[MatchOddsServiceImpl.ODDS_TYPE_HAD].trim());
                } catch (NumberFormatException e) {
                }
            }
            if (StringUtils.isNotBlank(socres[1])) {
                try {
                    awayScore = Double.valueOf(socres[1].trim());
                } catch (NumberFormatException e) {
                }
            }
        }
        //~~~~~~~~~~~~~~~~~~~~~~liji~~~~~~~~~~~~~~~~~~~~~~~~
        double[][] supAndTtg;
        try {
            String hdcHome = oddsModel.getLijiHdcHome();
            String hdcLine = oddsModel.getLijiHdcLine();
            String hdcAway = oddsModel.getLijiHdcAway();
            String hiloHigh = oddsModel.getLijiHiloH();
            String hiloLine = oddsModel.getLijiHiloLine();
            String hiloLow = oddsModel.getLijiHiloL();
            supAndTtg = calcSUPandTTG(
                    Double.valueOf(hdcHome),
                    Double.valueOf(hdcLine),
                    Double.valueOf(hdcAway),
                    Double.valueOf(hiloHigh),
                    Double.valueOf(hiloLine),
                    Double.valueOf(hiloLow),
                    duration, homeScore, awayScore);
        } catch (NumberFormatException e) {
            LogHelper.errorLog(errorLogger, e, "calc sup and ttg error");
            return null;
        } catch (MWException e) {
            LogHelper.errorLog(errorLogger, e, "calc sup and ttg error");
            return null;
        } catch (Exception e) {
            LogHelper.errorLog(errorLogger, e, "calc sup and ttg runtime error");
            return null;
        }
        map.put("liji", new SupAndTtgModel(
                new BigDecimal(supAndTtg[0][0]).setScale(2, RoundingMode.HALF_DOWN).toString(),
                new BigDecimal(supAndTtg[0][1]).setScale(2, RoundingMode.HALF_DOWN).toString()));
        //~~~~~~~~~~~~~~~~~~~~~~jinbaobo~~~~~~~~~~~~~~~~~~~~~~~
        double[][] supAndTtg1;
        try {
            String hdcHome1 = oddsModel.getJinbaoboHdcHome();
            String hdcLine1 = oddsModel.getJinbaoboHdcLine();
            String hdcAway1 = oddsModel.getJinbaoboHdcAway();
            String hiloHigh1 = oddsModel.getJinbaoboHiloH();
            String hiloLine1 = oddsModel.getJinbaoboHiloLine();
            String hiloLow1 = oddsModel.getJinbaoboHiloL();
            supAndTtg1 = calcSUPandTTG(
                    Double.valueOf(hdcHome1),
                    Double.valueOf(hdcLine1),
                    Double.valueOf(hdcAway1),
                    Double.valueOf(hiloHigh1),
                    Double.valueOf(hiloLine1),
                    Double.valueOf(hiloLow1),
                    duration, homeScore, awayScore);
        } catch (NumberFormatException e) {
            LogHelper.errorLog(errorLogger, e, "calc sup and ttg error");
            return null;
        } catch (MWException e) {
            LogHelper.errorLog(errorLogger, e, "calc sup and ttg error");
            return null;
        } catch (Exception e) {
            LogHelper.errorLog(errorLogger, e, "calc sup and ttg runtime error");
            return null;
        }
        map.put("jinbaobo", new SupAndTtgModel(
                new BigDecimal(supAndTtg1[0][0]).setScale(2, RoundingMode.HALF_DOWN).toString(),
                new BigDecimal(supAndTtg1[0][1]).setScale(2, RoundingMode.HALF_DOWN).toString()));
        return map;
    }
}
