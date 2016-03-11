package com.spider.db.repository;

import com.spider.db.entity.OddsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OddsModelRepository extends JpaRepository<OddsModel, Long> {

    OddsModel findByEuropeId(Integer europeId);
}
