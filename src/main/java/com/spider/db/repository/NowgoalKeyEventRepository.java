package com.spider.db.repository;

import com.spider.db.entity.NowgoalKeyEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NowgoalKeyEventRepository extends JpaRepository<NowgoalKeyEvent, Long> {

    List<NowgoalKeyEvent> findByMatchId(Long europeId);
}
