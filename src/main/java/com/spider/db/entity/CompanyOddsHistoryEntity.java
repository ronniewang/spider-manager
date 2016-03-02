package com.spider.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wsy on 2016/1/8.
 */
@Entity
@Table(name = "company_odds_history")
public class CompanyOddsHistoryEntity {

    private long id;

    private Integer europeId;

    private String gamingCompany;

    private String oddsOne;

    private String oddsTwo;

    private String oddsThree;

    private Timestamp updateTime;

    private String oddsUpdateTime;

    private String state;

    private String score;

    private String durationTime;

    private Integer homeRedCard;

    private Integer awayRedCard;

    private Integer oddsType;

    public CompanyOddsHistoryEntity() {

    }

    public CompanyOddsHistoryEntity(CompanyOddsEntity companyOddsEntity) {

        this.europeId = companyOddsEntity.getEuropeId();
        this.gamingCompany = companyOddsEntity.getGamingCompany();
        this.oddsOne = companyOddsEntity.getOddsOne();
        this.oddsTwo = companyOddsEntity.getOddsTwo();
        this.oddsThree = companyOddsEntity.getOddsThree();
        this.updateTime = new Timestamp(System.currentTimeMillis());
        this.oddsUpdateTime = companyOddsEntity.getOddsUpdateTime();
        this.state = companyOddsEntity.getState();
        this.score = companyOddsEntity.getScore();
        this.oddsType = companyOddsEntity.getOddsType();
        this.durationTime = companyOddsEntity.getDurationTime();
        this.homeRedCard = companyOddsEntity.getHomeRedCard();
        this.awayRedCard = companyOddsEntity.getAwayRedCard();
    }

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
    @Column(name = "odds_type")
    public Integer getOddsType() {

        return oddsType;
    }

    public void setOddsType(Integer oddsType) {

        this.oddsType = oddsType;
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
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {

        this.updateTime = updateTime;
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
    @Column(name = "state")
    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyOddsHistoryEntity that = (CompanyOddsHistoryEntity) o;

        if (id != that.id) return false;
        if (europeId != null ? !europeId.equals(that.europeId) : that.europeId != null) return false;
        if (gamingCompany != null ? !gamingCompany.equals(that.gamingCompany) : that.gamingCompany != null)
            return false;
        if (oddsOne != null ? !oddsOne.equals(that.oddsOne) : that.oddsOne != null) return false;
        if (oddsTwo != null ? !oddsTwo.equals(that.oddsTwo) : that.oddsTwo != null) return false;
        if (oddsThree != null ? !oddsThree.equals(that.oddsThree) : that.oddsThree != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (oddsUpdateTime != null ? !oddsUpdateTime.equals(that.oddsUpdateTime) : that.oddsUpdateTime != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (durationTime != null ? !durationTime.equals(that.durationTime) : that.durationTime != null) return false;
        if (homeRedCard != null ? !homeRedCard.equals(that.homeRedCard) : that.homeRedCard != null) return false;
        if (awayRedCard != null ? !awayRedCard.equals(that.awayRedCard) : that.awayRedCard != null) return false;

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
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (oddsUpdateTime != null ? oddsUpdateTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (durationTime != null ? durationTime.hashCode() : 0);
        result = 31 * result + (homeRedCard != null ? homeRedCard.hashCode() : 0);
        result = 31 * result + (awayRedCard != null ? awayRedCard.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "CompanyOddsHistoryEntity{" +
                "id=" + id +
                ", europeId=" + europeId +
                ", gamingCompany='" + gamingCompany + '\'' +
                ", oddsOne='" + oddsOne + '\'' +
                ", oddsTwo='" + oddsTwo + '\'' +
                ", oddsThree='" + oddsThree + '\'' +
                ", updateTime=" + updateTime +
                ", oddsUpdateTime='" + oddsUpdateTime + '\'' +
                ", state='" + state + '\'' +
                ", score='" + score + '\'' +
                ", durationTime='" + durationTime + '\'' +
                ", homeRedCard=" + homeRedCard +
                ", awayRedCard=" + awayRedCard +
                ", oddsType=" + oddsType +
                '}';
    }
}
