package com.spider.repository;

import com.spider.entity.TCrawlerWin310;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TCrawlerWin310Repository extends JpaRepository<TCrawlerWin310, Long>, JpaSpecificationExecutor<TCrawlerWin310> {

    @Query("select s from TCrawlerWin310 s where START_DATE_TIME = :startDateTime and COMPETITION_NUM = :competitionNum")
    TCrawlerWin310 findByStartDateTimeAndCompetitionNum(@Param("startDateTime") Date startDateTime, @Param("competitionNum") String competitionNum);

    @Query(nativeQuery = true, value = "select * from t_crawler_win310 where COMPETITION_NUM = :competitionNum order by update_time desc limit 0,1")
    TCrawlerWin310 findByCompetitionNum(@Param("competitionNum") String competitionNum);

    TCrawlerWin310 findByWin310EuropeId(String europeId);

    @Query(nativeQuery = true, value = "select DISTINCT matchs FROM t_crawler_win310 order by matchs")
    List<String> findMatchsDistinct();

    List<TCrawlerWin310> findByMatchsAndUpdateTimeBetween(String league, Date startDate, Date endDate);
}
