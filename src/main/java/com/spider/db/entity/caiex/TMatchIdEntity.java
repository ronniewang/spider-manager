package com.spider.db.entity.caiex;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ronnie on 2016/4/26.
 */
@Entity
@Table(name = "t_match_id")
public class TMatchIdEntity {

    private long matchId;

    @Id
    @Column(name = "match_id", nullable = false)
    public long getMatchId() {

        return matchId;
    }

    public void setMatchId(long matchId) {

        this.matchId = matchId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TMatchIdEntity that = (TMatchIdEntity) o;

        if (matchId != that.matchId) return false;

        return true;
    }

    @Override
    public int hashCode() {

        return (int) (matchId ^ (matchId >>> 32));
    }
}
