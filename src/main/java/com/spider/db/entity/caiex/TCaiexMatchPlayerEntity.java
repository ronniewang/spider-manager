package com.spider.db.entity.caiex;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/26.
 */
@Entity
@Table(name = "t_caiex_match_player")
public class TCaiexMatchPlayerEntity {

    private Long id;

    private Long matchId;

    private Long playerId;

    private Integer number;

    private String position;

    private String name;

    private String state;

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
    @Column(name = "player_id", nullable = true)
    public Long getPlayerId() {

        return playerId;
    }

    public void setPlayerId(Long playerId) {

        this.playerId = playerId;
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
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Basic
    @Column(name = "state", nullable = true, length = 255)
    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
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

        TCaiexMatchPlayerEntity that = (TCaiexMatchPlayerEntity) o;

        if (id != that.id) return false;
        if (matchId != that.matchId) return false;
        if (playerId != null ? !playerId.equals(that.playerId) : that.playerId != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (matchId ^ (matchId >>> 32));
        result = 31 * result + (playerId != null ? playerId.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
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
