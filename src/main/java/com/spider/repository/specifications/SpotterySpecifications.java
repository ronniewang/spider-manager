package com.spider.repository.specifications;

import com.spider.entity.TCrawlerSporttery;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class SpotterySpecifications {

    public static Specification<TCrawlerSporttery> startDateTimeBetween(final Date startDate, final Date endDate) {

        return new Specification<TCrawlerSporttery>() {

            @Override
            public Predicate toPredicate(Root<TCrawlerSporttery> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return cb.and(
                        cb.greaterThanOrEqualTo(root.<Date>get("startDateTime"), startDate),
                        cb.lessThan(root.<Date>get("startDateTime"), endDate));
            }

        };
    }

    public static Specification<TCrawlerSporttery> equalsCompetitionNum(final String competitionNum) {

        return new Specification<TCrawlerSporttery>() {

            @Override
            public Predicate toPredicate(Root<TCrawlerSporttery> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return cb.equal(root.<String>get("competitionNum"), competitionNum);
            }

        };
    }
}