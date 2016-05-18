package com.spider.manager.service;

import com.spider.db.entity.OddsModel;
import com.spider.manager.model.ExcelOddsModel;
import com.spider.manager.model.SupAndTtgModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MatchOddsServcie {

    /**
     * 根据日期区间查找赔率信息
     *
     * @param startDate not null
     * @param endDate   not null
     * @return
     * @see OddsModel
     */
    List<OddsModel> listOdds(Date startDate, Date endDate);

    /**
     * 根据赛事编号刷新赔率信息
     *
     * @param matchCode
     * @return
     */
    OddsModel refreshOdds(String matchCode);

    /**
     * 计算sup和ttg
     *
     * @param oddsModel 前台传入的参数，用于计算sup和ttg
     * @return
     */
    Map<String/*liji, jbb*/, SupAndTtgModel> calcSupAndTtg(OddsModel oddsModel);

    /**
     * 根据联赛和日期区间获取赔率信息，用于导出excel
     *
     * @param start
     * @param end
     * @param league
     * @return
     */
    List<ExcelOddsModel> getExcelOddsModels(Date start, Date end, String league);
}
