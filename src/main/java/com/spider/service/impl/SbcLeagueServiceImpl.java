package com.spider.service.impl;

import com.spider.db.entity.SbcLeague;
import com.spider.db.entity.TCrawlerSporttery;
import com.spider.db.repository.SbcLeagueRepository;
import com.spider.service.SbcLeagueService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SbcLeagueServiceImpl implements SbcLeagueService {

    private static Logger logger = Logger.getLogger(SbcLeagueServiceImpl.class);

    @Autowired
    private SbcLeagueRepository sbcLeagueRepository;

    @Override
    public String getLeagueName(TCrawlerSporttery sporttery) {

        SbcLeague league = null;
        String leagueName = sporttery.getMatchs();
        try {
            league = sbcLeagueRepository.findBySportteryName(sporttery.getMatchs());
            if (league != null) {
                leagueName = league.getLeagueNameAbbr();
            }
        } catch (Exception e) {
            logger.error("find sbc league error", e);
        }
        return leagueName;
    }
}
