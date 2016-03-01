package com.spider.repository;

import com.spider.entity.SbcLeague;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SbcLeagueRepository extends JpaRepository<SbcLeague, Long> {

    SbcLeague findBySportteryName(String sportteryName);

}
