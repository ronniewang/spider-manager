package com.spider.repository.specifications;

import com.spider.entity.TCrawlerWin310;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class Win310Specifications {

    public static Specification<TCrawlerWin310> startDateTimeBetween(final Date startDate, final Date endDate) {

        return new Specification<TCrawlerWin310>() {

            @Override
            public Predicate toPredicate(Root<TCrawlerWin310> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return cb.between(root.<Date> get("startDateTime"), startDate, endDate);
            }

        };
    }

    public static Specification<TCrawlerWin310> equalsCompetitionNum(final String competitionNum) {

        return new Specification<TCrawlerWin310>() {

            @Override
            public Predicate toPredicate(Root<TCrawlerWin310> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return cb.equal(root.<String> get("competitionNum"), competitionNum);
            }

        };
    }
}