package com.spider.repository;

import com.spider.entity.GamingCompanyConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamingCompanyRepository extends JpaRepository<GamingCompanyConfig, Long> {

    GamingCompanyConfig findByCompanyName(String name);
}
