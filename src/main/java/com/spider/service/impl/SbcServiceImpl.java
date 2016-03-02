package com.spider.service.impl;

import com.spider.db.entity.*;
import com.spider.exception.MqException;
import com.spider.global.GamingCompany;
import com.spider.global.UpdateSBCType;
import com.spider.model.JsonResult;
import com.spider.db.repository.*;
import com.spider.sbc.SbcUpdateManager;
import com.spider.service.SbcService;
import com.google.common.base.Preconditions;
import com.spider.domain.UpdateHdcOdds;
import com.spider.domain.UpdateHiloOdds;
import com.spider.domain.UpdateScoreAndHalf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wsy on 2015/10/21.
 *
 * @author wsy
 */
@Service
public class SbcServiceImpl implements SbcService {

    public static final int ODDS_TYPE_HILO = 2;

    public static final int ODDS_TYPE_HDC = 1;

    @Autowired
    private TCrawlerWin310Repository win310Repository;

    @Autowired
    private SbcUpdateManager sbcUpdateManager;

    @Autowired
    private W500Repository w500Repository;

    @Autowired
    private CompanyOddsRepository companyOddsRepository;

    @Override
    public JsonResult sync(String matchCode, Integer type) {

        Preconditions.checkArgument(matchCode != null);

        TCrawlerWin310 win310 = win310Repository.findByCompetitionNum(matchCode);
        W500Entity w500Entity = w500Repository.findByMatchCode(Integer.valueOf(matchCode));
        try {
            if (win310 != null && w500Entity != null && type == null) {
                updateSbcScoreAndHalf(w500Entity, matchCode);
                updateAllHiloOdds(win310);
                updateAllHdcOdds(win310);
                return JsonResult.SUCCESS;
            } else if (w500Entity != null && type == UpdateSBCType.ScoreAndHalf.intValue()) {
                updateSbcScoreAndHalf(w500Entity, matchCode);
                return JsonResult.SUCCESS;
            } else if (win310 != null && type == UpdateSBCType.HiloOdds.intValue()) {
                updateAllHiloOdds(win310);
                return JsonResult.SUCCESS;
            } else if (win310 != null && type == UpdateSBCType.HdcOdds.intValue()) {
                updateAllHdcOdds(win310);
                return JsonResult.SUCCESS;
            }
        } catch (MqException e) {
            return new JsonResult(2, "mq error");
        } catch (Exception e) {
            return new JsonResult(1, "no match found");//rtodo
        }
        return new JsonResult(1, "no match found");
    }

    @Override
    public JsonResult syncOdds(String id) {

        CompanyOddsEntity companyOddsEntity = companyOddsRepository.findOne(Long.valueOf(id));
        if (companyOddsEntity == null) {
            return new JsonResult(1, "no this odds, id is " + id);
        } else {
            TCrawlerWin310 win310 = win310Repository.findByWin310EuropeId(companyOddsEntity.getEuropeId() + "");
            if (win310 == null) {
                return new JsonResult(2, "no this win310, europeId is " + companyOddsEntity.getEuropeId());
            } else {
                try {
                    Integer oddsType = companyOddsEntity.getOddsType();
                    if (oddsType == ODDS_TYPE_HDC) {//hdc
                        sbcUpdateManager.update(
                                new UpdateHdcOdds(win310.getCompetitionNum(),
                                        companyOddsEntity.getGamingCompany(),
                                        companyOddsEntity.getDurationTime(),
                                        companyOddsEntity.getScore(),
                                        companyOddsEntity.getOddsOne(),
                                        companyOddsEntity.getOddsThree(),
                                        companyOddsEntity.getOddsTwo(),
                                        companyOddsEntity.getHomeRedCard(),
                                        companyOddsEntity.getAwayRedCard(),
                                        companyOddsEntity.getOddsUpdateTime()), sbcUpdateManager.getHdcTag());
                        return JsonResult.SUCCESS;
                    } else if (oddsType == ODDS_TYPE_HILO) {//hilo
                        sbcUpdateManager.update(
                                new UpdateHiloOdds(win310.getCompetitionNum(),
                                        companyOddsEntity.getGamingCompany(),
                                        companyOddsEntity.getDurationTime(),
                                        companyOddsEntity.getScore(),
                                        companyOddsEntity.getOddsOne(),
                                        companyOddsEntity.getOddsThree(),
                                        companyOddsEntity.getOddsTwo(),
                                        companyOddsEntity.getHomeRedCard(),
                                        companyOddsEntity.getAwayRedCard(),
                                        companyOddsEntity.getOddsUpdateTime()), sbcUpdateManager.getHiloTag());
                        return JsonResult.SUCCESS;
                    }
                } catch (MqException e) {
                    return new JsonResult(2, "mq error");
                } catch (Exception e) {
                    return new JsonResult(1, "no match found");//rtodo
                }
            }
        }
        return new JsonResult(1, "unknow exception");
    }

    private void updateAllHdcOdds(TCrawlerWin310 win310) throws MqException {

        CompanyOddsEntity lj = companyOddsRepository.findByEuropeIdAndOddsTypeAndGamingCompany(Integer.valueOf(win310.getWin310EuropeId()), ODDS_TYPE_HDC, GamingCompany.LiJi.getName());
        if (lj != null) {
            syncOdds(String.valueOf(lj.getId()));
        }
        CompanyOddsEntity jbb = companyOddsRepository.findByEuropeIdAndOddsTypeAndGamingCompany(Integer.valueOf(win310.getWin310EuropeId()), ODDS_TYPE_HDC, GamingCompany.JinBaoBo.getName());
        if (jbb != null) {
            syncOdds(String.valueOf(jbb.getId()));
        }
    }

    private void updateAllHiloOdds(TCrawlerWin310 win310) throws MqException {

        CompanyOddsEntity lj = companyOddsRepository
                .findByEuropeIdAndOddsTypeAndGamingCompany(Integer.valueOf(win310.getWin310EuropeId()), ODDS_TYPE_HILO, GamingCompany.LiJi.getName());
        if (lj != null) {
            syncOdds(String.valueOf(lj.getId()));
        }
        CompanyOddsEntity jbb = companyOddsRepository
                .findByEuropeIdAndOddsTypeAndGamingCompany(Integer.valueOf(win310.getWin310EuropeId()), ODDS_TYPE_HILO, GamingCompany.JinBaoBo.getName());
        if (jbb != null) {
            syncOdds(String.valueOf(jbb.getId()));
        }
    }

    private void updateSbcScoreAndHalf(W500Entity w500, String matchCode) throws MqException {

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
        UpdateScoreAndHalf scoreAndHalf = new UpdateScoreAndHalf(matchCode, score, halfScore, half, homeRedCard, awayRedCard, timeMinute);
        sbcUpdateManager.update(scoreAndHalf, sbcUpdateManager.getScoreAndHalfTag(), sbcUpdateManager.getParameterTopic());
    }
}
