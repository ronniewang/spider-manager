package com.spider.playersheet.entity;

import java.util.Date;

/**
 * Created by ronnie on 2016/4/27.
 */
public class TCaiexPlayerWorkInfoEntity {

    private long id;

    private long playerId;

    private long currentWorkTeamId;

    private Integer number;

    private String position;

    private Date updateTime;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getPlayerId() {

        return playerId;
    }

    public void setPlayerId(long playerId) {

        this.playerId = playerId;
    }

    public long getCurrentWorkTeamId() {

        return currentWorkTeamId;
    }

    public void setCurrentWorkTeamId(long currentWorkTeamId) {

        this.currentWorkTeamId = currentWorkTeamId;
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

        TCaiexPlayerWorkInfoEntity that = (TCaiexPlayerWorkInfoEntity) o;

        if (id != that.id) return false;
        if (playerId != that.playerId) return false;
        if (currentWorkTeamId != that.currentWorkTeamId) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (playerId ^ (playerId >>> 32));
        result = 31 * result + (int) (currentWorkTeamId ^ (currentWorkTeamId >>> 32));
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "TCaiexPlayerWorkInfoEntity{" +
                "id=" + id +
                ", playerId=" + playerId +
                ", currentWorkTeamId=" + currentWorkTeamId +
                ", number=" + number +
                ", position='" + position + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
