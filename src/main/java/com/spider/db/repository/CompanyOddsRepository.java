package com.spider.db.repository;

import com.spider.db.entity.CompanyOddsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyOddsRepository extends JpaRepository<CompanyOddsEntity, Long> {

    CompanyOddsEntity findByEuropeIdAndOddsTypeAndGamingCompany(Integer europeId, Integer oddsType, String gamingCompany);

}
