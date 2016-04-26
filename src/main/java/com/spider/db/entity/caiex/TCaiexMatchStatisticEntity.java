package com.spider.db.entity.caiex;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/26.
 */
@Entity
@Table(name = "t_caiex_match_statistic")
public class TCaiexMatchStatisticEntity {

    private Long id;

    private Long matchId;

    private Integer team;

    private String item;

    private Integer count;

    private Date updateTime;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "match_id", nullable = false)
    public Long getMatchId() {

        return matchId;
    }

    public void setMatchId(Long matchId) {

        this.matchId = matchId;
    }

    @Basic
    @Column(name = "team", nullable = true)
    public Integer getTeam() {

        return team;
    }

    public void setTeam(Integer team) {

        this.team = team;
    }

    @Basic
    @Column(name = "item", nullable = true, length = 255)
    public String getItem() {

        return item;
    }

    public void setItem(String item) {

        this.item = item;
    }

    @Basic
    @Column(name = "count", nullable = true)
    public Integer getCount() {

        return count;
    }

    public void setCount(Integer count) {

        this.count = count;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Date getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCaiexMatchStatisticEntity that = (TCaiexMatchStatisticEntity) o;

        if (id != that.id) return false;
        if (matchId != that.matchId) return false;
        if (team != null ? !team.equals(that.team) : that.team != null) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (matchId ^ (matchId >>> 32));
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
