package com.spider.repository;

import com.spider.entity.TCrawlerSporttery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TCrawlerSportteryRepository extends JpaRepository<TCrawlerSporttery, Long>, JpaSpecificationExecutor<TCrawlerSporttery> {

    @Query("select s from TCrawlerSporttery s where B_DATE = :BDate and COMPETITION_NUM = :competitionNum")
    List<TCrawlerSporttery> findByBDateAndCompetitionNum(@Param("BDate") String BDate, @Param("competitionNum") String competitionNum);

    @Query("select s from TCrawlerSporttery s where UPDATE_TIME >= :startTime and UPDATE_TIME < :endTime")
    List<TCrawlerSporttery> findByUpdateTimeBetween(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    TCrawlerSporttery findByCompetitionNum(String competitionNum);

    @Query("from TCrawlerSporttery s where s.startDateTime >= :start and s.startDateTime < :end")
    List<TCrawlerSporttery> findByStartDateAndEndDate(@Param("start") Date start, @Param("end") Date end);
}
