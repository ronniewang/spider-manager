package com.spider.db.entity.caiex;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/26.
 */
@Entity
@Table(name = "t_caiex_match")
public class TCaiexMatchEntity {

    private Long id;

    private Long leagueId;

    private Date matchDate;

    private Integer homeScore;

    private Integer awayScore;

    private Date updateTime;

    private Long homeTeamId;

    private Long awayTeamId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "league_id", nullable = false)
    public Long getLeagueId() {

        return leagueId;
    }

    public void setLeagueId(Long leagueId) {

        this.leagueId = leagueId;
    }

    @Basic
    @Column(name = "home_team_id", nullable = true)
    public Long getHomeTeamId() {

        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {

        this.homeTeamId = homeTeamId;
    }

    @Basic
    @Column(name = "away_team_id", nullable = true)
    public Long getAwayTeamId() {

        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {

        this.awayTeamId = awayTeamId;
    }

    @Basic
    @Column(name = "match_date", nullable = true)
    public Date getMatchDate() {

        return matchDate;
    }

    public void setMatchDate(Date matchDate) {

        this.matchDate = matchDate;
    }

    @Basic
    @Column(name = "home_score", nullable = true)
    public Integer getHomeScore() {

        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {

        this.homeScore = homeScore;
    }

    @Basic
    @Column(name = "away_score", nullable = true)
    public Integer getAwayScore() {

        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {

        this.awayScore = awayScore;
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

        TCaiexMatchEntity that = (TCaiexMatchEntity) o;

        if (id != that.id) return false;
        if (leagueId != null ? !leagueId.equals(that.leagueId) : that.leagueId != null) return false;
        if (matchDate != null ? !matchDate.equals(that.matchDate) : that.matchDate != null) return false;
        if (homeScore != null ? !homeScore.equals(that.homeScore) : that.homeScore != null) return false;
        if (awayScore != null ? !awayScore.equals(that.awayScore) : that.awayScore != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (leagueId != null ? leagueId.hashCode() : 0);
        result = 31 * result + (matchDate != null ? matchDate.hashCode() : 0);
        result = 31 * result + (homeScore != null ? homeScore.hashCode() : 0);
        result = 31 * result + (awayScore != null ? awayScore.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

}
