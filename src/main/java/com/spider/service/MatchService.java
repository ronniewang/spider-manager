package com.spider.service;

import com.spider.db.entity.TCrawlerSporttery;
import com.spider.db.entity.TCrawlerWin310;
import com.spider.model.ExcelMatchStatisticModel;
import com.spider.model.MatchModel;
import com.spider.model.MatchPlayerInfoModel;

import java.util.Date;
import java.util.List;

public interface MatchService {

    String ABSENCE_STATE_YES = "0";

    List<String> getAbsenceMatchCodes(List<TCrawlerSporttery> sportteries);

    TCrawlerWin310 getWin310MatchByMatchCode(String matchCode);

    /**
     * 根据时间查询比赛
     * @param startDate
     * @param endDate
     * @return
     */
    List<MatchModel> listMatch(Date startDate, Date endDate);

    List<MatchPlayerInfoModel> listMatchByLeague(Date startDate, Date endDate, String league);

    List<ExcelMatchStatisticModel> getExcelStatisticModels(Date startDate, Date endDate, String league);
}
