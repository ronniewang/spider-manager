package com.spider.db.repository;

import com.spider.db.entity.TCrawlerWin310;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TCrawlerWin310Repository extends JpaRepository<TCrawlerWin310, Long> {

    TCrawlerWin310 findByStartDateTimeAndCompetitionNum(Date startDateTime, String competitionNum);

    TCrawlerWin310 findByUniqueId(Long uniqueId);

    TCrawlerWin310 findByWin310EuropeId(String europeId);

    /**
     * 查询所有联赛
     *
     * @return leagues or empty
     */
    @Query(nativeQuery = true, value = "SELECT DISTINCT matchs FROM t_crawler_win310 ORDER BY matchs")
    List<String> findMatchsDistinct();

    /**
     * 根据联赛，日期区间查找310赛事
     *
     * @param league
     * @param startDate
     * @param endDate
     * @return
     */
    List<TCrawlerWin310> findByMatchsAndUpdateTimeBetween(String league, Date startDate, Date endDate);

    /**
     * 查找最新的彩客网赛事
     * 每次排序可能会有性能问题，待优化
     *
     * @param competitionNum 赛事编号
     * @return entity or null
     */
    TCrawlerWin310 findTop1ByCompetitionNumOrderByStartDateTimeDesc(String competitionNum);
}
