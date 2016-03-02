package com.spider.db.repository;


import com.spider.db.entity.SportteryAllEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportteryAllRepository extends JpaRepository<SportteryAllEntity, Long> {

    SportteryAllEntity findByMatchCode(String matchCode);
}
