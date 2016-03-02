package com.spider.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wsy on 2015/12/23.
 */
@Entity
@Table(name = "nowgoal_key_event")
public class NowgoalKeyEvent {

    private long id;

    private Long matchId;

    private Integer timeMinute;

    private Integer eventType;

    private String relativePlayer;

    private String eventDesc;

    private Integer teamType;

    private Timestamp updateTime;

    @Id
    @Column(name = "id")
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "match_id")
    public Long getMatchId() {

        return matchId;
    }

    public void setMatchId(Long matchId) {

        this.matchId = matchId;
    }

    @Basic
    @Column(name = "time_minute")
    public Integer getTimeMinute() {

        return timeMinute;
    }

    public void setTimeMinute(Integer timeMinute) {

        this.timeMinute = timeMinute;
    }

    @Basic
    @Column(name = "event_type")
    public Integer getEventType() {

        return eventType;
    }

    public void setEventType(Integer eventType) {

        this.eventType = eventType;
    }

    @Basic
    @Column(name = "relative_player")
    public String getRelativePlayer() {

        return relativePlayer;
    }

    public void setRelativePlayer(String relativePlayer) {

        this.relativePlayer = relativePlayer;
    }

    @Basic
    @Column(name = "event_desc")
    public String getEventDesc() {

        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {

        this.eventDesc = eventDesc;
    }

    @Basic
    @Column(name = "team_type")
    public Integer getTeamType() {

        return teamType;
    }

    public void setTeamType(Integer teamType) {

        this.teamType = teamType;
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

        NowgoalKeyEvent that = (NowgoalKeyEvent) o;

        if (id != that.id) return false;
        if (matchId != null ? !matchId.equals(that.matchId) : that.matchId != null) return false;
        if (timeMinute != null ? !timeMinute.equals(that.timeMinute) : that.timeMinute != null) return false;
        if (eventType != null ? !eventType.equals(that.eventType) : that.eventType != null) return false;
        if (relativePlayer != null ? !relativePlayer.equals(that.relativePlayer) : that.relativePlayer != null)
            return false;
        if (eventDesc != null ? !eventDesc.equals(that.eventDesc) : that.eventDesc != null) return false;
        if (teamType != null ? !teamType.equals(that.teamType) : that.teamType != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (matchId != null ? matchId.hashCode() : 0);
        result = 31 * result + (timeMinute != null ? timeMinute.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (relativePlayer != null ? relativePlayer.hashCode() : 0);
        result = 31 * result + (eventDesc != null ? eventDesc.hashCode() : 0);
        result = 31 * result + (teamType != null ? teamType.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "NowgoalKeyEvent{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", timeMinute=" + timeMinute +
                ", eventType=" + eventType +
                ", relativePlayer='" + relativePlayer + '\'' +
                ", eventDesc='" + eventDesc + '\'' +
                ", teamType=" + teamType +
                ", updateTime=" + updateTime +
                '}';
    }
}
