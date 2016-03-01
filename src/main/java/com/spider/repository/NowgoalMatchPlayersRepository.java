package com.spider.repository;

import com.spider.entity.NowgoalMatchPlayersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NowgoalMatchPlayersRepository extends JpaRepository<NowgoalMatchPlayersEntity, Long> {

    List<NowgoalMatchPlayersEntity> findByMatchId(Long europeId);
}
