package com.spider.repository;

import com.spider.entity.MatchInfoUniteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchInfoUniteRepository extends JpaRepository<MatchInfoUniteEntity, Long> {

    MatchInfoUniteEntity findByWin310Id(Long id);
}
