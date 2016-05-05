package com.spider.manager.service.impl;

import com.spider.db.entity.*;
import com.spider.global.Constants;
import com.spider.global.GamingCompany;
import com.spider.manager.model.ExcelOddsModel;
import com.spider.manager.model.SportteryAllModel;
import com.spider.manager.model.SupAndTtgModel;
import com.spider.db.repository.*;
import com.spider.manager.service.MatchOddsServcie;
import com.spider.manager.service.MatchService;
import com.spider.manager.service.SbcLeagueService;
import com.spider.utils.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mathworks.toolbox.javabuilder.MWException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 关于赔率的Service，包含较多业务逻辑
 *
 * @author ronnie
 */
@Service
public class MatchOddsServiceImpl implements MatchOddsServcie {

    private static final String JBB_NAME = Constants.JINBAOBO_NAME;

    private static final String LJ_NAME = Constants.LIJI_NAME;

    private static final Logger errorLogger = LogHelper.getErrorLogger();

    private static final Logger infoLogger = LogHelper.getInfoLogger();

    public static final int ODDS_TYPE_HILO = 2;

    public static final int ODDS_TYPE_HDC = 1;

    public static final int ODDS_TYPE_HAD = 0;

    @Autowired
    private TCrawlerSportteryRepository sportteryRepository;

    @Autowired
    private TCrawlerWin310Repository win310Repository;

    @Autowired
    private SbcLeagueService sbcLeagueService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private SportteryAllRepository sportteryAllRepository;

    @Autowired
    private CompanyOddsRepository companyOddsRepository;

    @Autowired
    private CompanyOddsHistoryRepository companyOddsHistoryRepository;

    @Autowired
    private OddsModelRepository oddsModelRepository;

    @Override
    public List<OddsModel> listOdds(Date startDate, Date endDate) {

        List<OddsModel> oddslist = Lists.newArrayList();
        List<TCrawlerSporttery> sportteryList = sportteryRepository.findByStartDateTimeBetween(startDate, endDate);
        if (sportteryList == null) {
            return oddslist;
        }
        List<String> matchCodes = LotteryUtils.getMatchCodes(sportteryList);
        List<String> absenceMatchSet = matchService.getAbsenceMatchCodes(matchCodes);
        for (TCrawlerSporttery sporttery : sportteryList) {
            TCrawlerWin310 win310 = win310Repository.findTop1ByCompetitionNumOrderByStartDateTimeDesc(sporttery.getCompetitionNum());

            if (win310 == null) {
                System.out.println("win310 doesn't exist, match code is " + sporttery.getCompetitionNum());
                continue;
            }
            OddsModel oddsModel = oddsModelRepository.findByEuropeId(Integer.valueOf(win310.getWin310EuropeId()));
            if (oddsModel == null) {
                continue;
            }
            if (win310 != null) {
                if (!win310.sameAs(sporttery)) {
                    oddsModel.setIsDifferent(true);
                }
                setState(sporttery, absenceMatchSet, oddsModel);
            } else {
                if (sporttery.getStartDateTime().before(new Date())) {//老的那一场
                    continue;
                }
            }
            SportteryAllEntity sportteryAllEntity = sportteryAllRepository.findByMatchCode(sporttery.getCompetitionNum());
            if (sportteryAllEntity != null && oddsModel != null) {
                oddsModel.setSportteryAllModel(new SportteryAllModel(sportteryAllEntity));
            }
            oddslist.add(oddsModel);
        }
        return oddslist;
    }

    @Override
    public OddsModel refreshOdds(String matchCode) {

        List<TCrawlerSporttery> sportteries = sportteryRepository.findByCompetitionNum(matchCode);
        if (CollectionUtils.isNotEmpty(sportteries)) {
            TCrawlerWin310 win310 = win310Repository.findTop1ByCompetitionNumOrderByStartDateTimeDesc(matchCode);
            if (win310 != null) {
                return getOddsModel(sportteries.get(0), win310);
            }
        }
        return null;
    }

    private OddsModel getOddsModel(TCrawlerSporttery sporttery, TCrawlerWin310 win310) {

        OddsModel oddsModel = new OddsModel();
        setGeneralData(sporttery, oddsModel);
        setSportteryOdds(sporttery, oddsModel);
        if (win310 == null) {
            return oddsModel;
        }
        setWin310Odds(win310, oddsModel);
        return oddsModel;
    }

    private void setGeneralData(TCrawlerSporttery sporttery, OddsModel oddsModel) {

        oddsModel.setMatchLeague(sbcLeagueService.getLeagueName(sporttery));
        oddsModel.setId(sporttery.getId());
        oddsModel.setMatchDate(DateUtils.getDate("yyyy-MM-dd HH:mm", sporttery.getStartDateTime()));
        oddsModel.setMatchCode(sporttery.getCompetitionNum());
        oddsModel.setHomeTeam(sporttery.getHomeTeam());
        oddsModel.setAwayTeam(sporttery.getVisitionTeam());
        Integer winCountTwo = sporttery.getWinCountTwo();
        oddsModel.setHandicapLine(winCountTwo == null ? null : winCountTwo.toString());
    }

    private void setSportteryOdds(TCrawlerSporttery sporttery, OddsModel oddsModel) {

        oddsModel.setSportteryHadH(sporttery.getWinProbabilityOne());
        oddsModel.setSportteryHadD(sporttery.getDrawProbabilityOne());
        oddsModel.setSportteryHadA(sporttery.getFailProbabilityOne());
        oddsModel.setSportteryHadMargin(LotteryUtils.calcHadOrHhadMargin(oddsModel.getSportteryHadH(), oddsModel.getSportteryHadD(),
                oddsModel.getSportteryHadA()));
        String readableUpdateTimeFromNow = UpdateTimeUtils.getReadableUpdateTimeFromNow(sporttery.getUpdateTime());
        oddsModel.setSportteryHadUpdate(readableUpdateTimeFromNow);
        oddsModel.setSportteryHhadH(sporttery.getWinProbabilityTwo());
        oddsModel.setSportteryHhadD(sporttery.getDrawProbabilityTwo());
        oddsModel.setSportteryHhadA(sporttery.getFailProbabilityTwo());
        oddsModel.setSportteryHhadMargin(LotteryUtils.calcHadOrHhadMargin(oddsModel.getSportteryHhadH(), oddsModel.getSportteryHhadD(),
                oddsModel.getSportteryHhadA()));
        oddsModel.setSportteryHhadUpdate(readableUpdateTimeFromNow);
    }

    private void setWin310Odds(TCrawlerWin310 win310, OddsModel oddsModel) {

        oddsModel.setMatchDate(DateUtils.getDateFormat(win310.getStartDateTime(), "yyyy-MM-dd HH:mm:ss"));
        oddsModel.setScore(win310.getScore());
        oddsModel.setWin310HadH(win310.getWinProbabilityOne());
        oddsModel.setWin310HadD(win310.getDrawProbabilityOne());
        oddsModel.setWin310HadA(win310.getFailProbabilityOne());
        oddsModel.setWin310HadMargin(LotteryUtils.calcHadOrHhadMargin(oddsModel.getWin310HadH(), oddsModel.getWin310HadD(), oddsModel.getWin310HadA()));
        String readableUpdateTimeFromNow = UpdateTimeUtils.getReadableUpdateTimeFromNow(win310.getUpdateTime());
        oddsModel.setWin310HadUpdate(readableUpdateTimeFromNow);
        oddsModel.setWin310HhadH(win310.getWinProbabilityTwo());
        oddsModel.setWin310HhadD(win310.getDrawProbabilityTwo());
        oddsModel.setWin310HhadA(win310.getFailProbabilityTwo());
        oddsModel.setWin310HhadMargin(LotteryUtils.calcHadOrHhadMargin(oddsModel.getWin310HhadH(), oddsModel.getWin310HhadD(), oddsModel.getWin310HhadA()));
        oddsModel.setWin310HhadUpdate(readableUpdateTimeFromNow);
        List<String> durationList = Lists.newArrayList();
        Integer europeId = Integer.valueOf(win310.getWin310EuropeId());
        CompanyOddsEntity hiloLj = companyOddsRepository.findByEuropeIdAndOddsTypeAndGamingCompany(europeId, ODDS_TYPE_HILO, LJ_NAME);
        if (hiloLj != null) {
            oddsModel.setLijiHiloH(hiloLj.getOddsOne());
            oddsModel.setLijiHiloL(hiloLj.getOddsThree());
            oddsModel.setLijiHiloLine(HandicapLineConvertor.getHilo(hiloLj.getOddsTwo()));
            oddsModel.setLijiHiloMargin(LotteryUtils.calcHiloOrHdcMargin(hiloLj.getOddsOne(), hiloLj.getOddsThree()));
            oddsModel.setLijiHiloUpdate(UpdateTimeUtils.getReadableUpdateTimeFromNow(hiloLj.getUpdateTime()));
            durationList.add(hiloLj.getDurationTime());
        }
        CompanyOddsEntity hiloJbb = companyOddsRepository.findByEuropeIdAndOddsTypeAndGamingCompany(europeId, ODDS_TYPE_HILO, JBB_NAME);
        if (hiloJbb != null) {
            oddsModel.setJinbaoboHiloH(hiloJbb.getOddsOne());
            oddsModel.setJinbaoboHiloL(hiloJbb.getOddsThree());
            oddsModel.setJinbaoboHiloLine(HandicapLineConvertor.getHilo(hiloJbb.getOddsTwo()));
            oddsModel.setJinbaoboHiloMargin(LotteryUtils.calcHiloOrHdcMargin(hiloJbb.getOddsOne(), hiloJbb.getOddsThree()));
            oddsModel.setJinbaoboHiloUpdate(UpdateTimeUtils.getReadableUpdateTimeFromNow(hiloJbb.getUpdateTime()));
            durationList.add(hiloJbb.getDurationTime());
        }

        CompanyOddsEntity hadLj = companyOddsRepository.findByEuropeIdAndOddsTypeAndGamingCompany(europeId, ODDS_TYPE_HAD, LJ_NAME);
        if (hadLj != null) {
            oddsModel.setLijiHadH(hadLj.getOddsOne());
            oddsModel.setLijiHadD(hadLj.getOddsTwo());
            oddsModel.setLijiHadA(hadLj.getOddsThree());
            oddsModel.setScore(hadLj.getScore());
            oddsModel.setLijiHadMargin(LotteryUtils.calcHadOrHhadMargin(oddsModel.getLijiHadH(), oddsModel.getLijiHadD(), oddsModel.getLijiHadA()));
            oddsModel.setLijiHadUpdate(UpdateTimeUtils.getReadableUpdateTimeFromNow(hadLj.getUpdateTime()));
            durationList.add(hadLj.getDurationTime());
        }
        CompanyOddsEntity hadJbb = companyOddsRepository.findByEuropeIdAndOddsTypeAndGamingCompany(europeId, ODDS_TYPE_HAD, JBB_NAME);
        if (hadJbb != null) {
            oddsModel.setJinbaoboHadH(hadJbb.getOddsOne());
            oddsModel.setJinbaoboHadD(hadJbb.getOddsTwo());
            oddsModel.setJinbaoboHadA(hadJbb.getOddsThree());
            oddsModel.setScore(hadJbb.getScore());
            oddsModel.setJinbaoboHadMargin(LotteryUtils.calcHadOrHhadMargin(oddsModel.getJinbaoboHadH(), oddsModel.getJinbaoboHadD(), oddsModel.getJinbaoboHadA()));
            oddsModel.setJinbaoboHadUpdate(UpdateTimeUtils.getReadableUpdateTimeFromNow(hadJbb.getUpdateTime()));
            durationList.add(hadJbb.getDurationTime());
        }
        CompanyOddsEntity hdcLj = companyOddsRepository.findByEuropeIdAndOddsTypeAndGamingCompany(europeId, ODDS_TYPE_HDC, LJ_NAME);
        if (hdcLj != null) {
            oddsModel.setLijiHdcHome(hdcLj.getOddsOne());
            oddsModel.setLijiHdcAway(hdcLj.getOddsThree());
            oddsModel.setLijiHdcLine(HandicapLineConvertor.getHdc(hdcLj.getOddsTwo()));
            oddsModel.setLijiHdcMargin(LotteryUtils.calcHiloOrHdcMargin(hdcLj.getOddsOne(), hdcLj.getOddsThree()));
            oddsModel.setLijiHdcUpdate(UpdateTimeUtils.getReadableUpdateTimeFromNow(hdcLj.getUpdateTime()));
            durationList.add(hdcLj.getDurationTime());
        }
        CompanyOddsEntity hdcJbb = companyOddsRepository.findByEuropeIdAndOddsTypeAndGamingCompany(europeId, ODDS_TYPE_HDC, JBB_NAME);
        if (hdcJbb != null) {
            oddsModel.setJinbaoboHdcHome(hdcJbb.getOddsOne());
            oddsModel.setJinbaoboHdcAway(hdcJbb.getOddsThree());
            oddsModel.setJinbaoboHdcLine(HandicapLineConvertor.getHdc(hdcJbb.getOddsTwo()));
            oddsModel.setJinbaoboHdcMargin(LotteryUtils.calcHiloOrHdcMargin(hdcJbb.getOddsOne(), hdcJbb.getOddsThree()));
            oddsModel.setJinbaoboHdcUpdate(UpdateTimeUtils.getReadableUpdateTimeFromNow(hdcJbb.getUpdateTime()));
            durationList.add(hdcJbb.getDurationTime());
        }
        if (CollectionUtils.isNotEmpty(durationList)) {
            Integer maxDurationTime = LotteryUtils.getMaxDurationTime(durationList);
            oddsModel.setDurationTime(maxDurationTime == null ? "" : maxDurationTime + "");
        }
    }

    private void setState(TCrawlerSporttery sporttery, List<String> absenceMatchSet, OddsModel oddsModel) {

        if (sporttery == null || oddsModel == null) {
            return;
        }
        if (absenceMatchSet.contains(sporttery.getCompetitionNum())) {
            oddsModel.setAbsenceState(MatchService.ABSENCE_STATE_YES);
        }
    }

    @Override
    public Map<String, SupAndTtgModel> calcSupAndTtg(OddsModel oddsModel) {

        Map<String/*liji, jbb*/, SupAndTtgModel> map = Maps.newHashMap();
        LogHelper.infoLog(infoLogger, null, "start calc sup and ttg, oddsModel={0} ", oddsModel);
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
            if (StringUtils.isNotBlank(socres[ODDS_TYPE_HAD])) {
                try {
                    homeScore = Double.valueOf(socres[ODDS_TYPE_HAD].trim());
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
            supAndTtg = CaiexOddsUtils.calcSUPandTTG(
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
            supAndTtg1 = CaiexOddsUtils.calcSUPandTTG(
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

    @Override
    public List<ExcelOddsModel> getExcelOddsModels(Date start, Date end, String league) {

        List<TCrawlerWin310> win310s = win310Repository.findByMatchsAndUpdateTimeBetween(league, start, end);
        List<ExcelOddsModel> excelOddsModels = new ArrayList<>();
        for (TCrawlerWin310 win310 : win310s) {
            Integer europeId = Integer.valueOf(win310.getWin310EuropeId());
            List<CompanyOddsHistoryEntity> companyOddsEntities = companyOddsHistoryRepository.findByEuropeIdAndDurationTimeEqualsEmpty(europeId);

            List<CompanyOddsHistoryEntity> lj0List = new ArrayList<>();
            List<CompanyOddsHistoryEntity> lj1List = new ArrayList<>();
            List<CompanyOddsHistoryEntity> lj2List = new ArrayList<>();
            List<CompanyOddsHistoryEntity> jbb0List = new ArrayList<>();
            List<CompanyOddsHistoryEntity> jbb1List = new ArrayList<>();
            List<CompanyOddsHistoryEntity> jbb2List = new ArrayList<>();
            for (CompanyOddsHistoryEntity companyOddsEntity : companyOddsEntities) {
                if (companyOddsEntity.getGamingCompany().equals(GamingCompany.JinBaoBo.getName())) {
                    if (companyOddsEntity.getOddsType() == 0) {
                        jbb0List.add(companyOddsEntity);
                    } else if (companyOddsEntity.getOddsType() == 1) {
                        jbb1List.add(companyOddsEntity);
                    } else if (companyOddsEntity.getOddsType() == 2) {
                        jbb2List.add(companyOddsEntity);
                    }
                } else if (companyOddsEntity.getGamingCompany().equals(GamingCompany.LiJi.getName())) {
                    if (companyOddsEntity.getOddsType() == 0) {
                        lj0List.add(companyOddsEntity);
                    } else if (companyOddsEntity.getOddsType() == 1) {
                        lj1List.add(companyOddsEntity);
                    } else if (companyOddsEntity.getOddsType() == 2) {
                        lj2List.add(companyOddsEntity);
                    }
                } else {
                    //ignore
                }
            }
            ExcelOddsModel lj = getExcelOddsModel(win310, lj0List, lj1List, lj2List);
            lj.setHadBookmaker(LJ_NAME);
            lj.setHdcBookmaker(LJ_NAME);
            lj.setHiloBookmaker(LJ_NAME);
            excelOddsModels.add(lj);
            ExcelOddsModel jbb = getExcelOddsModel(win310, jbb0List, jbb1List, jbb2List);
            jbb.setHadBookmaker(JBB_NAME);
            jbb.setHdcBookmaker(JBB_NAME);
            jbb.setHiloBookmaker(JBB_NAME);
            excelOddsModels.add(jbb);
        }
        return excelOddsModels;
    }

    private ExcelOddsModel getExcelOddsModel(TCrawlerWin310 win310, List<CompanyOddsHistoryEntity> hadList, List<CompanyOddsHistoryEntity> hdcList, List<CompanyOddsHistoryEntity> hiloList) {

        ExcelOddsModel excelOddsModel = new ExcelOddsModel();
        excelOddsModel.setHomeTeam(win310.getHomeTeam());
        excelOddsModel.setAwayTeam(win310.getVisitionTeam());
        excelOddsModel.setDate(win310.getBDate());
        excelOddsModel.setScore(win310.getScore());
        excelOddsModel.setHalfScore(win310.getHalfScore());
        if (CollectionUtils.isNotEmpty(hadList)) {
            Integer size = hadList.size();
            CompanyOddsHistoryEntity startOdds = hadList.get(0);
            excelOddsModel.setHadHStart(startOdds.getOddsOne());
            excelOddsModel.setHadDStart(startOdds.getOddsTwo());
            excelOddsModel.setHadAStart(startOdds.getOddsThree());
            excelOddsModel.setHadTimeStart(startOdds.getOddsUpdateTime());
            CompanyOddsHistoryEntity closeOdds = hadList.get(size - 1);
            excelOddsModel.setHadHClose(closeOdds.getOddsOne());
            excelOddsModel.setHadDClose(closeOdds.getOddsTwo());
            excelOddsModel.setHadAClose(closeOdds.getOddsThree());
            excelOddsModel.setHadTimeClose(closeOdds.getOddsUpdateTime());
        }
        if (CollectionUtils.isNotEmpty(hdcList)) {
            Integer size = hdcList.size();
            CompanyOddsHistoryEntity startOdds = hdcList.get(0);
            excelOddsModel.setHdcHomeStart(startOdds.getOddsOne());
            excelOddsModel.setHdcHCStart(startOdds.getOddsTwo());
            excelOddsModel.setHdcAwayStart(startOdds.getOddsThree());
            excelOddsModel.setHdcTimeStart(startOdds.getOddsUpdateTime());
            CompanyOddsHistoryEntity closeOdds = hdcList.get(size - 1);
            excelOddsModel.setHdcHomeClose(closeOdds.getOddsOne());
            excelOddsModel.setHdcHCClose(closeOdds.getOddsTwo());
            excelOddsModel.setHdcAwayClose(closeOdds.getOddsThree());
            excelOddsModel.setHdcTimeClose(closeOdds.getOddsUpdateTime());
        }
        if (CollectionUtils.isNotEmpty(hiloList)) {
            Integer size = hiloList.size();
            CompanyOddsHistoryEntity startOdds = hiloList.get(0);
            excelOddsModel.setHiloOverStart(startOdds.getOddsOne());
            excelOddsModel.setHiloKeyStart(startOdds.getOddsTwo());
            excelOddsModel.setHiloUnderStart(startOdds.getOddsThree());
            excelOddsModel.setHiloTimeStart(startOdds.getOddsUpdateTime());
            CompanyOddsHistoryEntity closeOdds = hiloList.get(size - 1);
            excelOddsModel.setHiloOverClose(closeOdds.getOddsOne());
            excelOddsModel.setHiloKeyClose(closeOdds.getOddsTwo());
            excelOddsModel.setHiloUnderClose(closeOdds.getOddsThree());
            excelOddsModel.setHiloTimeClose(closeOdds.getOddsUpdateTime());
        }
        return excelOddsModel;
    }
}
