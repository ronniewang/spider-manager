package com.spider.db.entity.caiex;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/27.
 */
@Entity
@Table(name = "t_caiex_player_work_info", schema = "crawler", catalog = "")
public class TCaiexPlayerWorkInfoEntity {

    private long id;

    private long playerId;

    private long currentWorkTeamId;

    private Integer number;

    private String position;

    private Date updateTime;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "player_id", nullable = false)
    public long getPlayerId() {

        return playerId;
    }

    public void setPlayerId(long playerId) {

        this.playerId = playerId;
    }

    @Basic
    @Column(name = "current_work_team_id", nullable = false)
    public long getCurrentWorkTeamId() {

        return currentWorkTeamId;
    }

    public void setCurrentWorkTeamId(long currentWorkTeamId) {

        this.currentWorkTeamId = currentWorkTeamId;
    }

    @Basic
    @Column(name = "number", nullable = true)
    public Integer getNumber() {

        return number;
    }

    public void setNumber(Integer number) {

        this.number = number;
    }

    @Basic
    @Column(name = "position", nullable = true, length = 255)
    public String getPosition() {

        return position;
    }

    public void setPosition(String position) {

        this.position = position;
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
}
