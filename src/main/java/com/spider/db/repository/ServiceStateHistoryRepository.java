package com.spider.db.repository;

import com.spider.db.entity.ServiceStateHistoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceStateHistoryRepository extends JpaRepository<ServiceStateHistoryEntity, Long> {

    @Query("from ServiceStateHistoryEntity s where s.service = :serviceName")
    List<ServiceStateHistoryEntity> findHistory(@Param("serviceName") String serviceName, Pageable pageable);
}
