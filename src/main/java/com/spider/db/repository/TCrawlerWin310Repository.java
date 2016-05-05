package com.spider.db.repository;

import com.spider.db.entity.TCrawlerWin310;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TCrawlerWin310Repository extends JpaRepository<TCrawlerWin310, Long>, JpaSpecificationExecutor<TCrawlerWin310> {

    TCrawlerWin310 findByStartDateTimeAndCompetitionNum(Date startDateTime, String competitionNum);

    TCrawlerWin310 findByUniqueId(@Param("uniqueId") String uniqueId);

    TCrawlerWin310 findByWin310EuropeId(String europeId);

    /**
     * 查询所有联赛
     *
     * @return
     */
    @Query(nativeQuery = true, value = "select DISTINCT matchs FROM t_crawler_win310 order by matchs")
    List<String> findMatchsDistinct();

    List<TCrawlerWin310> findByMatchsAndUpdateTimeBetween(String league, Date startDate, Date endDate);

    /**
     * 查找最新的彩客网赛事
     * 每次排序可能会有性能问题，待优化
     *
     * @param competitionNum
     * @return
     */
    TCrawlerWin310 findTop1ByCompetitionNumOrderByStartDateTimeDesc(String competitionNum);
}
