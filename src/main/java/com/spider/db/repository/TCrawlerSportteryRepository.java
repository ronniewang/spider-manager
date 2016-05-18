package com.spider.db.repository;

import com.spider.db.entity.TCrawlerSporttery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TCrawlerSportteryRepository extends JpaRepository<TCrawlerSporttery, Long> {

    List<TCrawlerSporttery> findByStartDateTimeBetween(Date start, Date end);

    List<TCrawlerSporttery> findByCompetitionNum(String competitionNum);
}
