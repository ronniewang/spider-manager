package com.spider.repository;

import com.spider.entity.NowgoalKeyEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NowgoalKeyEventRepository extends JpaRepository<NowgoalKeyEvent, Long> {

    List<NowgoalKeyEvent> findByMatchId(Long europeId);
}
