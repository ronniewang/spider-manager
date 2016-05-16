package com.spider.db.repository;

import com.spider.db.entity.NowgoalMatchStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NowgoalMatchStatisticRepository extends JpaRepository<NowgoalMatchStatisticEntity, Long> {

    List<NowgoalMatchStatisticEntity> findByMatchId(Long europeId);
}
