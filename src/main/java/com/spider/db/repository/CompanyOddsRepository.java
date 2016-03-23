package com.spider.db.repository;

import com.spider.db.entity.CompanyOddsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyOddsRepository extends JpaRepository<CompanyOddsEntity, Long> {

    CompanyOddsEntity findByEuropeIdAndOddsTypeAndGamingCompany(Integer europeId, Integer oddsType, String gamingCompany);

    @Query(nativeQuery = true,
            value = "select * from company_odds where europe_id = :europeId and (odds_type = 1 or odds_type = 2)")
    List<CompanyOddsEntity> findHdcAndHilo(@Param("europeId") Integer europeId);
}
