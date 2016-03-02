package com.spider.db.repository;

import com.spider.db.entity.MatchInfoUniteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchInfoUniteRepository extends JpaRepository<MatchInfoUniteEntity, Long> {

    MatchInfoUniteEntity findByWin310Id(Long id);
}
