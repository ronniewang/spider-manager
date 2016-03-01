package com.spider.repository;

import com.spider.entity.ServiceStateHistoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceStateHistoryRepository extends JpaRepository<ServiceStateHistoryEntity, Long> {

    @Query("from ServiceStateHistoryEntity s where SERVICE = :serviceName")
    List<ServiceStateHistoryEntity> findHistory(@Param("serviceName") String serviceName, Pageable pageable);
}
