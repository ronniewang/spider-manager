package com.spider.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wsy on 2015/12/24.
 */
@Entity
@Table(name = "nowgoal_match_statistic")
public class NowgoalMatchStatisticEntity {

    private Long id;

    private long matchId;

    private Integer team;

    private Integer count;

    private String item;

    private Timestamp updateTime;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {

        return count;
    }

    public void setCount(Integer count) {

        this.count = count;
    }

    @Basic
    @Column(name = "match_id")
    public long getMatchId() {

        return matchId;
    }

    public void setMatchId(long matchId) {

        this.matchId = matchId;
    }

    @Basic
    @Column(name = "team")
    public Integer getTeam() {

        return team;
    }

    public void setTeam(Integer team) {

        this.team = team;
    }

    @Basic
    @Column(name = "item")
    public String getItem() {

        return item;
    }

    public void setItem(String item) {

        this.item = item;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {

        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NowgoalMatchStatisticEntity that = (NowgoalMatchStatisticEntity) o;

        if (matchId != that.matchId) return false;
        if (team != null ? !team.equals(that.team) : that.team != null) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        return !(updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null);

    }

    @Override
    public int hashCode() {

        int result = (int) (matchId ^ (matchId >>> 32));
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "NowgoalMatchStatisticEntity{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", team=" + team +
                ", count=" + count +
                ", item='" + item + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
