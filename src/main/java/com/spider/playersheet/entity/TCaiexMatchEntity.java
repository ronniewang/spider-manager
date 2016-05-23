package com.spider.playersheet.entity;

import java.util.Date;

/**
 * Created by ronnie on 2016/4/26.
 */
public class TCaiexMatchEntity {

    private Long id;

    private Long leagueId;

    private Date matchDate;

    private Integer homeScore;

    private Integer awayScore;

    private Date updateTime;

    private Long homeTeamId;

    private Long awayTeamId;

    private Integer isNeutral;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getLeagueId() {

        return leagueId;
    }

    public void setLeagueId(Long leagueId) {

        this.leagueId = leagueId;
    }

    public Long getHomeTeamId() {

        return homeTeamId;
    }

    public Integer getIsNeutral() {

        return isNeutral;
    }

    public void setIsNeutral(Integer isNeutral) {

        this.isNeutral = isNeutral;
    }

    public void setHomeTeamId(Long homeTeamId) {

        this.homeTeamId = homeTeamId;
    }

    public Long getAwayTeamId() {

        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {

        this.awayTeamId = awayTeamId;
    }

    public Date getMatchDate() {

        return matchDate;
    }

    public void setMatchDate(Date matchDate) {

        this.matchDate = matchDate;
    }

    public Integer getHomeScore() {

        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {

        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {

        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {

        this.awayScore = awayScore;
    }

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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (leagueId != null ? !leagueId.equals(that.leagueId) : that.leagueId != null) return false;
        if (matchDate != null ? !matchDate.equals(that.matchDate) : that.matchDate != null) return false;
        if (homeScore != null ? !homeScore.equals(that.homeScore) : that.homeScore != null) return false;
        if (awayScore != null ? !awayScore.equals(that.awayScore) : that.awayScore != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (homeTeamId != null ? !homeTeamId.equals(that.homeTeamId) : that.homeTeamId != null) return false;
        if (awayTeamId != null ? !awayTeamId.equals(that.awayTeamId) : that.awayTeamId != null) return false;
        return isNeutral != null ? isNeutral.equals(that.isNeutral) : that.isNeutral == null;

    }

    @Override
    public int hashCode() {

        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (leagueId != null ? leagueId.hashCode() : 0);
        result = 31 * result + (matchDate != null ? matchDate.hashCode() : 0);
        result = 31 * result + (homeScore != null ? homeScore.hashCode() : 0);
        result = 31 * result + (awayScore != null ? awayScore.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (homeTeamId != null ? homeTeamId.hashCode() : 0);
        result = 31 * result + (awayTeamId != null ? awayTeamId.hashCode() : 0);
        result = 31 * result + (isNeutral != null ? isNeutral.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "TCaiexMatchEntity{" +
                "id=" + id +
                ", leagueId=" + leagueId +
                ", matchDate=" + matchDate +
                ", homeScore=" + homeScore +
                ", awayScore=" + awayScore +
                ", updateTime=" + updateTime +
                ", homeTeamId=" + homeTeamId +
                ", awayTeamId=" + awayTeamId +
                ", isNeutral=" + isNeutral +
                '}';
    }
}
