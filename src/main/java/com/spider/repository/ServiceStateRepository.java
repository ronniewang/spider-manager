package com.spider.repository;

import com.spider.entity.ServiceStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceStateRepository extends JpaRepository<ServiceStateEntity, Long> {

    @Query("from ServiceStateEntity s where s.service = :name")
    ServiceStateEntity findByService(@Param("name") String name);
}
