package com.spider.manager.service.impl;

import com.spider.db.entity.SportteryAllEntity;
import com.spider.db.entity.TCrawlerSporttery;
import com.spider.db.entity.TCrawlerWin310;
import com.spider.global.Constants;
import com.spider.manager.model.ProductModel;
import com.spider.db.repository.SportteryAllRepository;
import com.spider.db.repository.TCrawlerSportteryRepository;
import com.spider.db.repository.TCrawlerWin310Repository;
import com.spider.db.repository.specifications.SpotterySpecifications;
import com.spider.manager.service.MatchProductService;
import com.spider.manager.service.MatchService;
import com.spider.manager.service.SbcLeagueService;
import com.spider.utils.Calendars;
import com.spider.utils.DateUtils;
import com.spider.utils.LotteryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MatchProductServiceImpl implements MatchProductService {

    @Autowired
    private TCrawlerSportteryRepository tCrawlerSportteryRepository;

    @Autowired
    private TCrawlerWin310Repository tCrawlerWin310Repository;

    @Autowired
    private SportteryAllRepository sportteryAllRepository;

    @Autowired
    private SbcLeagueService sbcLeagueService;

    @Autowired
    private MatchService matchService;

    @Override
    public List<ProductModel> listMatchProduct(Date startDate, Date endDate) {

        List<ProductModel> productlist = new ArrayList<>();
        List<TCrawlerSporttery> sportteryList = tCrawlerSportteryRepository.findAll(SpotterySpecifications.startDateTimeBetween(startDate, endDate));
        if (sportteryList == null) {
            return productlist;
        }
        List<String> matchCodes = LotteryUtils.getMatchCodes(sportteryList);
        List<String> absenceMatchSet = matchService.getAbsenceMatchCodes(matchCodes);
        for (TCrawlerSporttery sporttery : sportteryList) {
            TCrawlerWin310 win310 = tCrawlerWin310Repository.findByStartDateTimeAndCompetitionNum(sporttery.getStartDateTime(),
                    sporttery.getCompetitionNum());

            ProductModel productModel = new ProductModel();
            productModel.setID(sporttery.getId());
            productModel.setMatchDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss", sporttery.getStartDateTime()));
            productModel.setMatchCode(sporttery.getCompetitionNum());
            productModel.setMatchLeague(sbcLeagueService.getLeagueName(sporttery));
            productModel.setHomeTeam(sporttery.getHomeTeam());
            productModel.setAwayTeam(sporttery.getVisitionTeam());
            Integer winCountTwo = sporttery.getWinCountTwo();
            productModel.setHandicapLine(winCountTwo == null ? null : winCountTwo.toString());
            if (Constants.STATUS_SELLING.equals(sporttery.getStatusOne())) {
                productModel.setHAD("Y");
            } else {
                productModel.setHAD("N");
            }
            if (Constants.STATUS_SELLING.equals(sporttery.getStatusTwo())) {
                productModel.setHHAD("Y");
            } else {
                productModel.setHHAD("N");
            }
            //rtodo 暂时都写为Y
            SportteryAllEntity sportteryAllEntity = sportteryAllRepository.findByMatchCode(sporttery.getCompetitionNum());
            productModel.setHAFU(sportteryAllEntity != null && sportteryAllEntity.getHafuAa() != null ? "Y" : "N");
            productModel.setCRS(sportteryAllEntity != null && sportteryAllEntity.getScore00() != null ? "Y" : "N");
            productModel.setTTG(sportteryAllEntity != null && sportteryAllEntity.getTtg0() != null ? "Y" : "N");

            setModelState(sporttery, win310, absenceMatchSet, productModel);
            productlist.add(productModel);
        }
        return productlist;
    }

    private void setModelState(TCrawlerSporttery sporttery, TCrawlerWin310 win310, List<String> absenceMatchSet, ProductModel productModel) {

        if (absenceMatchSet.contains(sporttery.getCompetitionNum())) {
            productModel.setAbsenceState(MatchService.ABSENCE_STATE_YES);
        }
        if (win310 == null) {// 不存在
            productModel.setState("1");
        } else {
            if (compareTwo(sporttery, win310)) {// 完全匹配
                productModel.setState("0");
            } else {// 有不同
                productModel.setState("2");
            }
        }
    }

    public boolean compareTwo(TCrawlerSporttery sporttery, TCrawlerWin310 win310) {// 判断竞猜官网和彩客是否匹配

        // boolean flag1 = false;
        // boolean flag2 = false;
        // boolean flag3 = false;
        // boolean flag4 = false;
        // boolean flag5 = false;
        // boolean flag6 = false;
        // String scheduleMatchDate = sporttery.getStartDate().substring(5, 10)
        // + " " + sporttery.getStartTime().substring(0, 5);
        // String win310MatchDate = win310.getStartTime();
        //
        // if (scheduleMatchDate.equals(win310MatchDate) || scheduleMatchDate ==
        // win310MatchDate) {
        // flag1 = true; // 验证日期
        // }
        // if (sporttery.getWinCountTwo() == win310.getWinCountTwo()) {
        // flag6 = true; // 验证让分线
        // }
        // if ((sporttery.getStatusOne() == Constants.STATUS_SELLING ||
        // sporttery.getStatusOne().equals(Constants.STATUS_SELLING)) &&
        // (!win310.getWinProbabilityTwo().equals(""))) {
        // flag2 = true; // 验证had存在
        // flag3 = true;// 验证had不存在
        // } else if ((sporttery.getStatusOne() == "" ||
        // sporttery.getStatusOne().equals("")) &&
        // (win310.getWinProbabilityOne().equals(""))) {
        // flag3 = true;// 验证had不存在
        // flag2 = true; // 验证had存在
        // }
        // if ((sporttery.getStatusTwo() == Constants.STATUS_SELLING ||
        // sporttery.getStatusTwo().equals(Constants.STATUS_SELLING)) &&
        // (!win310.getWinProbabilityTwo().equals(""))) {
        // flag4 = true;// 验证hhad存在
        // flag5 = true;// 验证hhad不存在
        // } else if ((sporttery.getStatusTwo() == "" ||
        // sporttery.getStatusTwo().equals("")) &&
        // (win310.getWinProbabilityOne().equals(""))) {
        // flag5 = true;// 验证hhad不存在
        // flag4 = true;// 验证hhad存在
        // }
        // return flag1 && flag2 && flag3 && flag4 && flag5 && flag6;
        return true;// FIXME
    }
}
