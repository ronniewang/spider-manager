package com.spider.manager.service.impl;

import com.spider.db.entity.*;
import com.spider.exception.UpdateException;
import com.spider.manager.model.JsonResult;
import com.spider.db.repository.*;
import com.spider.manager.sbc.SbcUpdateManager;
import com.spider.manager.service.SbcService;
import com.google.common.base.Preconditions;
import com.spider.domain.UpdateHdcOdds;
import com.spider.domain.UpdateHiloOdds;
import com.spider.domain.UpdateScoreAndHalf;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wsy on 2015/10/21.
 *
 * @author ronnie
 */
@Service
public class SbcServiceImpl implements SbcService {

    public static final int ODDS_TYPE_HILO = 2;

    private Logger logger = Logger.getLogger("info_logger");

    @Autowired
    private SbcUpdateManager sbcUpdateManager;

    @Autowired
    private TCrawlerWin310Repository win310Repository;

    @Autowired
    private W500Repository w500Repository;

    @Autowired
    private CompanyOddsRepository companyOddsRepository;

    /**
     * 同步赔率信息，根据id进行查找
     *
     * @param id not null
     * @return
     * @see UpdateHiloOdds
     * @see UpdateHdcOdds
     */
    @Override
    public JsonResult syncOdds(String id) {

        Preconditions.checkNotNull(id);

        try {
            CompanyOddsEntity odds = companyOddsRepository.findOne(Long.valueOf(id));
            if (odds == null) {
                logger.error("no this odds, id is [" + id + "]");
                return new JsonResult(1, "no this odds, id is [" + id + "]");
            }
            String europeId = odds.getEuropeId() + "";
            TCrawlerWin310 win310 = win310Repository.findByWin310EuropeId(europeId);
            String matchCode = win310.getCompetitionNum();
            if (win310 == null) {
                logger.error("no this win310, europeId is [" + europeId + "]");
                return new JsonResult(2, "no this win310, europeId is [" + europeId + "]");
            }
            doUpdateToMQ(matchCode, odds);
        } catch (UpdateException e) {
            logger.error("mq error", e);
            return new JsonResult(2, "mq error");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new JsonResult(1, e.getMessage());
        }
        return new JsonResult(1, "unknown exception");
    }

    @Override
    public JsonResult sync(String uniqueId, Integer type) {

        Preconditions.checkArgument(uniqueId != null);

        TCrawlerWin310 win310;
        W500Entity w500Entity;
        try {
            win310 = win310Repository.findByUniqueId(Long.valueOf(uniqueId));
            if (win310 == null) {
                logger.error("no win310 entity for this uniqueId [" + uniqueId + "]");
                return new JsonResult(1, "no win310 entity for this uniqueId [" + uniqueId + "]");
            }
            w500Entity = w500Repository.findByMatchCode(uniqueId);
            if (w500Entity == null) {
                logger.error("no w500 entity for this uniqueId [" + uniqueId + "]");
                return new JsonResult(1, "no w500 entity for this uniqueId [" + uniqueId + "]");
            }
        } catch (Exception e) {
            logger.error("mysql error", e);
            return new JsonResult(1, "mysql error, " + e.getMessage());
        }
        try {
            Integer europeId = Integer.valueOf(win310.getWin310EuropeId());
            updateSbcScoreAndHalf(w500Entity);
            if (type == null) {
                updateHiloAndHdcOdds(uniqueId, europeId);
            }
            return JsonResult.SUCCESS;
        } catch (UpdateException e) {
            logger.error("update error", e);
            return new JsonResult(2, "update error");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new JsonResult(1, "unexpected error");
        }
    }

    private void updateHiloAndHdcOdds(String uniqueId, Integer europeId) throws UpdateException {

        String matchCode = uniqueId.substring(8);
        List<CompanyOddsEntity> hdcHilos = companyOddsRepository.findHdcAndHilo(europeId);
        for (CompanyOddsEntity odds : hdcHilos) {
            doUpdateToMQ(matchCode, odds);
        }
    }

    private void doUpdateToMQ(String matchCode, CompanyOddsEntity odds) throws UpdateException {

        if (odds.getOddsType() == ODDS_TYPE_HILO) {
            sbcUpdateManager.update(buildUpdateHiloOdds(odds, matchCode), sbcUpdateManager.getHiloTag());
        } else {
            sbcUpdateManager.update(buildUpdateHdcOdds(odds, matchCode), sbcUpdateManager.getHdcTag());
        }
    }

    private UpdateHdcOdds buildUpdateHdcOdds(CompanyOddsEntity odds, String matchCode) {

        return new UpdateHdcOdds(matchCode, odds.getGamingCompany(), odds.getDurationTime(), odds.getScore(),
                odds.getOddsOne(), odds.getOddsThree(), odds.getOddsTwo(), odds.getHomeRedCard(),
                odds.getAwayRedCard(), odds.getOddsUpdateTime());
    }

    private UpdateHiloOdds buildUpdateHiloOdds(CompanyOddsEntity odds, String matchCode) {

        return new UpdateHiloOdds(matchCode, odds.getGamingCompany(), odds.getDurationTime(), odds.getScore(),
                odds.getOddsOne(), odds.getOddsThree(), odds.getOddsTwo(),
                odds.getHomeRedCard(), odds.getAwayRedCard(), odds.getOddsUpdateTime());
    }

    private void updateSbcScoreAndHalf(W500Entity w500) throws UpdateException {

        String score = w500.getScore();
        String half = w500.getHalf();
        String halfScore = w500.getHalfScore();
        Integer homeRedCard = w500.getHomeRedCard();
        Integer awayRedCard = w500.getAwayRedCard();
        Integer timeMinute;
        try {
            timeMinute = Integer.valueOf(w500.getDurationTime());
        } catch (Exception e) {
            timeMinute = null;
        }
        UpdateScoreAndHalf scoreAndHalf = new UpdateScoreAndHalf(w500.getMatchCode() + "", score, halfScore, half, homeRedCard, awayRedCard, timeMinute);
        sbcUpdateManager.update(scoreAndHalf, sbcUpdateManager.getScoreAndHalfTag(), sbcUpdateManager.getInplayParameterTopic());
    }
}
