package com.spider.db.repository;

import com.spider.db.entity.SbcLeague;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SbcLeagueRepository extends JpaRepository<SbcLeague, Long> {

    SbcLeague findBySportteryName(String sportteryName);

}
