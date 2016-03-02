package com.spider.db.repository;

import com.spider.db.entity.CompanyOddsHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyOddsHistoryRepository extends JpaRepository<CompanyOddsHistoryEntity, Long> {

    @Query("from CompanyOddsHistoryEntity s where s.europeId = :europeId and s.durationTime = ''")
    List<CompanyOddsHistoryEntity> findByEuropeIdAndDurationTimeEqualsEmpty(@Param(value = "europeId") Integer europeId);

}
