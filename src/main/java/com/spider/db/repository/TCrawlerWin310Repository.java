package com.spider.db.repository;

import com.spider.db.entity.TCrawlerWin310;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TCrawlerWin310Repository extends JpaRepository<TCrawlerWin310, Long>, JpaSpecificationExecutor<TCrawlerWin310> {

    @Query("select s from TCrawlerWin310 s where START_DATE_TIME = :startDateTime and COMPETITION_NUM = :competitionNum")
    TCrawlerWin310 findByStartDateTimeAndCompetitionNum(@Param("startDateTime") Date startDateTime, @Param("competitionNum") String competitionNum);

    /**
     * @param uniqueId 需求改为按照unique_id查询 2016-05-03
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_crawler_win310 where unique_id = :uniqueId order by start_date_time desc limit 0,1")
    TCrawlerWin310 findByMatchCode(@Param("uniqueId") String uniqueId);

    TCrawlerWin310 findByWin310EuropeId(String europeId);

    @Query(nativeQuery = true, value = "select DISTINCT matchs FROM t_crawler_win310 order by matchs")
    List<String> findMatchsDistinct();

    List<TCrawlerWin310> findByMatchsAndUpdateTimeBetween(String league, Date startDate, Date endDate);
}
