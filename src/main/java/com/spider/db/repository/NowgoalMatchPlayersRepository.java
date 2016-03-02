package com.spider.db.repository;

import com.spider.db.entity.NowgoalMatchPlayersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NowgoalMatchPlayersRepository extends JpaRepository<NowgoalMatchPlayersEntity, Long> {

    List<NowgoalMatchPlayersEntity> findByMatchId(Long europeId);
}
