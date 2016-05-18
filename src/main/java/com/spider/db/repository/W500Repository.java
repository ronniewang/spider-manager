package com.spider.db.repository;

import com.spider.db.entity.W500Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface W500Repository extends JpaRepository<W500Entity, Long> {

    /**
     * @param uniqueId 需求改为按照unique_id查询 2016-05-03
     * @return
     */
    @Query(nativeQuery = true, value = "select * from w500 where unique_id = :uniqueId order by match_time desc limit 0,1")
    W500Entity findByMatchCode(@Param("uniqueId") String uniqueId);
}
