package com.spider.playersheet.entity;

import java.util.Date;

/**
 * Created by ronnie on 2016/4/26.
 */
public class TCaiexMatchPlayerEntity {

    private Long id;

    private Long matchId;

    private Long playerId;

    private Integer number;

    private String position;

    private String name;

    private String state;

    private Date updateTime;

    private Long teamId;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getMatchId() {

        return matchId;
    }

    public void setMatchId(Long matchId) {

        this.matchId = matchId;
    }

    public Long getPlayerId() {

        return playerId;
    }

    public void setPlayerId(Long playerId) {

        this.playerId = playerId;
    }

    public void setTeamId(Long teamId) {

        this.teamId = teamId;
    }

    public Long getTeamId() {

        return this.teamId;
    }

    public Integer getNumber() {

        return number;
    }

    public void setNumber(Integer number) {

        this.number = number;
    }

    public String getPosition() {

        return position;
    }

    public void setPosition(String position) {

        this.position = position;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
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

        TCaiexMatchPlayerEntity that = (TCaiexMatchPlayerEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (matchId != null ? !matchId.equals(that.matchId) : that.matchId != null) return false;
        if (playerId != null ? !playerId.equals(that.playerId) : that.playerId != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        return teamId != null ? teamId.equals(that.teamId) : that.teamId == null;

    }

    @Override
    public int hashCode() {

        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (matchId != null ? matchId.hashCode() : 0);
        result = 31 * result + (playerId != null ? playerId.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "TCaiexMatchPlayerEntity{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", playerId=" + playerId +
                ", number=" + number +
                ", position='" + position + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

}
