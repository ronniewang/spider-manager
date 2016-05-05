package com.spider.db.repository.specifications;

import com.spider.db.entity.TCrawlerWin310;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * 此类已无用，留作事例，如果需要Specification可以参照，注意Repository实现{@link org.springframework.data.jpa.repository.JpaSpecificationExecutor}
 *
 * @author ronnie
 */
@Deprecated
public class Win310Specifications {

    public static Specification<TCrawlerWin310> startDateTimeBetween(final Date startDate, final Date endDate) {

        return new Specification<TCrawlerWin310>() {

            @Override
            public Predicate toPredicate(Root<TCrawlerWin310> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return cb.between(root.<Date>get("startDateTime"), startDate, endDate);
            }

        };
    }

    public static Specification<TCrawlerWin310> equalsCompetitionNum(final String competitionNum) {

        return new Specification<TCrawlerWin310>() {

            @Override
            public Predicate toPredicate(Root<TCrawlerWin310> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                return cb.equal(root.<String>get("competitionNum"), competitionNum);
            }

        };
    }
}