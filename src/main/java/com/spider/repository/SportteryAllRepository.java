package com.spider.repository;


import com.spider.entity.SportteryAllEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportteryAllRepository extends JpaRepository<SportteryAllEntity, Long> {

    SportteryAllEntity findByMatchCode(String matchCode);
}
