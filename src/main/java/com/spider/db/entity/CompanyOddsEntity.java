package com.spider.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wsy on 2016/1/8.
 */
@Entity
@Table(name = "company_odds")
public class CompanyOddsEntity {

    private long id;

    private Integer europeId;

    private String gamingCompany;

    private String oddsOne;

    private String oddsTwo;

    private String oddsThree;

    private Integer oddsType;

    private String score;

    private String durationTime;

    private Integer homeRedCard;

    private Integer awayRedCard;

    private String state;

    private String oddsUpdateTime;

    private Timestamp updateTime = new Timestamp(System.currentTimeMillis());

    @Id
    @Column(name = "id")
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "europe_id")
    public Integer getEuropeId() {

        return europeId;
    }

    public void setEuropeId(Integer europeId) {

        this.europeId = europeId;
    }

    @Basic
    @Column(name = "gaming_company")
    public String getGamingCompany() {

        return gamingCompany;
    }

    public void setGamingCompany(String gamingCompany) {

        this.gamingCompany = gamingCompany;
    }

    @Basic
    @Column(name = "odds_one")
    public String getOddsOne() {

        return oddsOne;
    }

    public void setOddsOne(String oddsOne) {

        this.oddsOne = oddsOne;
    }

    @Basic
    @Column(name = "odds_two")
    public String getOddsTwo() {

        return oddsTwo;
    }

    public void setOddsTwo(String oddsTwo) {

        this.oddsTwo = oddsTwo;
    }

    @Basic
    @Column(name = "odds_three")
    public String getOddsThree() {

        return oddsThree;
    }

    public void setOddsThree(String oddsThree) {

        this.oddsThree = oddsThree;
    }

    @Basic
    @Column(name = "odds_type")
    public Integer getOddsType() {

        return oddsType;
    }

    public void setOddsType(Integer oddsType) {

        this.oddsType = oddsType;
    }

    @Basic
    @Column(name = "score")
    public String getScore() {

        return score;
    }

    public void setScore(String score) {

        this.score = score;
    }

    @Basic
    @Column(name = "duration_time")
    public String getDurationTime() {

        return durationTime;
    }

    public void setDurationTime(String durationTime) {

        this.durationTime = durationTime;
    }

    @Basic
    @Column(name = "home_red_card")
    public Integer getHomeRedCard() {

        return homeRedCard;
    }

    public void setHomeRedCard(Integer homeRedCard) {

        this.homeRedCard = homeRedCard;
    }

    @Basic
    @Column(name = "away_red_card")
    public Integer getAwayRedCard() {

        return awayRedCard;
    }

    public void setAwayRedCard(Integer awayRedCard) {

        this.awayRedCard = awayRedCard;
    }

    @Basic
    @Column(name = "state")
    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    @Basic
    @Column(name = "odds_update_time")
    public String getOddsUpdateTime() {

        return oddsUpdateTime;
    }

    public void setOddsUpdateTime(String oddsUpdateTime) {

        this.oddsUpdateTime = oddsUpdateTime;
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

        CompanyOddsEntity that = (CompanyOddsEntity) o;

        if (europeId != null ? !europeId.equals(that.europeId) : that.europeId != null) return false;
        if (gamingCompany != null ? !gamingCompany.equals(that.gamingCompany) : that.gamingCompany != null)
            return false;
        if (oddsOne != null ? !oddsOne.equals(that.oddsOne) : that.oddsOne != null) return false;
        if (oddsTwo != null ? !oddsTwo.equals(that.oddsTwo) : that.oddsTwo != null) return false;
        if (oddsThree != null ? !oddsThree.equals(that.oddsThree) : that.oddsThree != null) return false;
        if (oddsType != null ? !oddsType.equals(that.oddsType) : that.oddsType != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (durationTime != null ? !durationTime.equals(that.durationTime) : that.durationTime != null) return false;
        if (homeRedCard != null ? !homeRedCard.equals(that.homeRedCard) : that.homeRedCard != null) return false;
        if (awayRedCard != null ? !awayRedCard.equals(that.awayRedCard) : that.awayRedCard != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (oddsUpdateTime != null ? !oddsUpdateTime.equals(that.oddsUpdateTime) : that.oddsUpdateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (europeId != null ? europeId.hashCode() : 0);
        result = 31 * result + (gamingCompany != null ? gamingCompany.hashCode() : 0);
        result = 31 * result + (oddsOne != null ? oddsOne.hashCode() : 0);
        result = 31 * result + (oddsTwo != null ? oddsTwo.hashCode() : 0);
        result = 31 * result + (oddsThree != null ? oddsThree.hashCode() : 0);
        result = 31 * result + (oddsType != null ? oddsType.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (durationTime != null ? durationTime.hashCode() : 0);
        result = 31 * result + (homeRedCard != null ? homeRedCard.hashCode() : 0);
        result = 31 * result + (awayRedCard != null ? awayRedCard.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (oddsUpdateTime != null ? oddsUpdateTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    public boolean valid() {

        return !oddsOne.equals("");
    }

    @Override
    public String toString() {

        return "CompanyOddsEntity{" +
                "id=" + id +
                ", europeId=" + europeId +
                ", gamingCompany='" + gamingCompany + '\'' +
                ", oddsOne='" + oddsOne + '\'' +
                ", oddsTwo='" + oddsTwo + '\'' +
                ", oddsThree='" + oddsThree + '\'' +
                ", oddsType=" + oddsType +
                ", score='" + score + '\'' +
                ", durationTime='" + durationTime + '\'' +
                ", homeRedCard=" + homeRedCard +
                ", awayRedCard=" + awayRedCard +
                ", state='" + state + '\'' +
                ", oddsUpdateTime='" + oddsUpdateTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
