package com.spider.db.repository;

import com.spider.db.entity.W500Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface W500Repository extends JpaRepository<W500Entity, Long> {

    @Query(nativeQuery = true, value = "select * from w500 where match_code = :matchCode order by match_time desc limit 0,1")
    W500Entity findByMatchCode(@Param("matchCode") Integer matchCode);
}
