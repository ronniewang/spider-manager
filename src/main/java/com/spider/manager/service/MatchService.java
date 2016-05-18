package com.spider.manager.service;

import com.spider.manager.model.ExcelMatchStatisticModel;
import com.spider.manager.model.MatchModel;
import com.spider.manager.model.MatchPlayerInfoModel;

import java.util.Date;
import java.util.List;

public interface MatchService {

    String ABSENCE_STATE_YES = "0";

    List<String> getAbsenceMatchCodes(List<String> sportteries);

    /**
     * 根据时间查询比赛
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<MatchModel> listMatch(Date startDate, Date endDate);

    /**
     * 根据时间和联赛查询比赛
     *
     * @param startDate
     * @param endDate
     * @param league
     * @return
     */
    List<MatchPlayerInfoModel> listMatchByLeague(Date startDate, Date endDate, String league);

    /**
     * @param startDate
     * @param endDate
     * @param league
     * @return
     */
    List<ExcelMatchStatisticModel> getExcelStatisticModels(Date startDate, Date endDate, String league);
}
