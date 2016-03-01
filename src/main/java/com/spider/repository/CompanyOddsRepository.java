package com.spider.repository;

import com.spider.entity.CompanyOddsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyOddsRepository extends JpaRepository<CompanyOddsEntity, Long> {

    CompanyOddsEntity findByEuropeIdAndOddsTypeAndGamingCompany(Integer europeId, Integer oddsType, String gamingCompany);

}
