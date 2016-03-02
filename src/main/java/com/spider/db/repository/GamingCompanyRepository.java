package com.spider.db.repository;

import com.spider.db.entity.GamingCompanyConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamingCompanyRepository extends JpaRepository<GamingCompanyConfig, Long> {

    GamingCompanyConfig findByCompanyName(String name);
}
