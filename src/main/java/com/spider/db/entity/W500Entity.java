package com.spider.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wsy on 2016/1/7.
 */
@Entity
@Table(name = "w500")
public class W500Entity {

    private long id;

    private String homeTeam;

    private String awayTeam;

    private Integer matchCode;

    private Integer homeRedCard;

    private Integer homeYellowCard;

    private Integer awayRedCard;

    private Integer awayYellowCard;

    private String score;

    private String halfScore;

    private Timestamp updateTime;

    private String durationTime;

    private Timestamp matchTime;

    private Double hadH;

    private Double hadD;

    private Double hadA;

    private String half;

    private Long uniqueId;

    private String w500Id;

    public W500Entity(W500Entity w500Entity) {

        this.homeTeam = w500Entity.getHomeTeam();
        this.awayTeam = w500Entity.getAwayTeam();
        this.matchCode = w500Entity.getMatchCode();
        this.homeRedCard = w500Entity.getHomeRedCard();
        this.homeYellowCard = w500Entity.getHomeYellowCard();
        this.awayRedCard = w500Entity.getAwayRedCard();
        this.awayYellowCard = w500Entity.getAwayYellowCard();
        this.score = w500Entity.getScore();
        this.halfScore = w500Entity.getHalfScore();
        this.updateTime = w500Entity.getUpdateTime();
        this.durationTime = w500Entity.getDurationTime();
        this.matchTime = w500Entity.getMatchTime();
        this.hadH = w500Entity.getHadH();
        this.hadD = w500Entity.getHadD();
        this.hadA = w500Entity.getHadA();
        this.half = w500Entity.getHalf();
        this.uniqueId = w500Entity.getUniqueId();
    }

    public W500Entity() {

    }

    public boolean needToSbc(W500Entity w500Entity) {

        if (w500Entity == null) {
            return true;
        }
        if (!w500Entity.getScore().equals(this.score)) {
            return true;
        } else if (!w500Entity.getHalfScore().equals(this.halfScore)) {
            return true;
        } else if (!w500Entity.getDurationTime().equals(this.durationTime)) {
            return true;
        } else if (!w500Entity.getHalf().equals(this.half)) {
            return true;
        } else {
            return false;
        }
    }

    @Id
    @Column(name = "id")
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Column(name = "half")
    public String getHalf() {

        return half;
    }

    public void setHalf(String half) {

        this.half = half;
    }

    @Basic
    @Column(name = "home_team")
    public String getHomeTeam() {

        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {

        this.homeTeam = homeTeam;
    }

    @Basic
    @Column(name = "away_team")
    public String getAwayTeam() {

        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {

        this.awayTeam = awayTeam;
    }

    @Basic
    @Column(name = "match_code")
    public Integer getMatchCode() {

        return matchCode;
    }

    public void setMatchCode(Integer matchCode) {

        this.matchCode = matchCode;
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
    @Column(name = "home_yellow_card")
    public Integer getHomeYellowCard() {

        return homeYellowCard;
    }

    public void setHomeYellowCard(Integer homeYellowCard) {

        this.homeYellowCard = homeYellowCard;
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
    @Column(name = "away_yellow_card")
    public Integer getAwayYellowCard() {

        return awayYellowCard;
    }

    public void setAwayYellowCard(Integer awayYellowCard) {

        this.awayYellowCard = awayYellowCard;
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
    @Column(name = "half_score")
    public String getHalfScore() {

        return halfScore;
    }

    public void setHalfScore(String halfScore) {

        this.halfScore = halfScore;
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
    @Column(name = "duration_time")
    public String getDurationTime() {

        return durationTime;
    }

    public void setDurationTime(String durationTime) {

        this.durationTime = durationTime;
    }

    @Basic
    @Column(name = "match_time")
    public Timestamp getMatchTime() {

        return matchTime;
    }

    public void setMatchTime(Timestamp matchTime) {

        this.matchTime = matchTime;
    }

    @Basic
    @Column(name = "had_h")
    public Double getHadH() {

        return hadH;
    }

    public void setHadH(Double hadH) {

        this.hadH = hadH;
    }

    @Basic
    @Column(name = "had_d")
    public Double getHadD() {

        return hadD;
    }

    public void setHadD(Double hadD) {

        this.hadD = hadD;
    }

    @Basic
    @Column(name = "had_a")
    public Double getHadA() {

        return hadA;
    }

    public void setHadA(Double hadA) {

        this.hadA = hadA;
    }

    @Basic
    @Column(name = "unique_id")
    public Long getUniqueId() {

        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {

        this.uniqueId = uniqueId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        W500Entity that = (W500Entity) o;

        if (homeTeam != null ? !homeTeam.equals(that.homeTeam) : that.homeTeam != null) return false;
        if (awayTeam != null ? !awayTeam.equals(that.awayTeam) : that.awayTeam != null) return false;
        if (matchCode != null ? !matchCode.equals(that.matchCode) : that.matchCode != null) return false;
        if (homeRedCard != null ? !homeRedCard.equals(that.homeRedCard) : that.homeRedCard != null) return false;
        if (homeYellowCard != null ? !homeYellowCard.equals(that.homeYellowCard) : that.homeYellowCard != null)
            return false;
        if (awayRedCard != null ? !awayRedCard.equals(that.awayRedCard) : that.awayRedCard != null) return false;
        if (awayYellowCard != null ? !awayYellowCard.equals(that.awayYellowCard) : that.awayYellowCard != null)
            return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (halfScore != null ? !halfScore.equals(that.halfScore) : that.halfScore != null) return false;
        if (half != null ? !half.equals(that.half) : that.half != null) return false;
        if (durationTime != null ? !durationTime.equals(that.durationTime) : that.durationTime != null) return false;
        if (matchTime != null ? !matchTime.equals(that.matchTime) : that.matchTime != null) return false;
        if (hadH != null ? !hadH.equals(that.hadH) : that.hadH != null) return false;
        if (hadD != null ? !hadD.equals(that.hadD) : that.hadD != null) return false;
        if (hadA != null ? !hadA.equals(that.hadA) : that.hadA != null) return false;
        if (uniqueId != null ? !uniqueId.equals(that.uniqueId) : that.uniqueId != null) return false;
        if (w500Id != null ? !w500Id.equals(that.w500Id) : that.w500Id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (homeTeam != null ? homeTeam.hashCode() : 0);
        result = 31 * result + (awayTeam != null ? awayTeam.hashCode() : 0);
        result = 31 * result + (matchCode != null ? matchCode.hashCode() : 0);
        result = 31 * result + (homeRedCard != null ? homeRedCard.hashCode() : 0);
        result = 31 * result + (homeYellowCard != null ? homeYellowCard.hashCode() : 0);
        result = 31 * result + (awayRedCard != null ? awayRedCard.hashCode() : 0);
        result = 31 * result + (awayYellowCard != null ? awayYellowCard.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (halfScore != null ? halfScore.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (durationTime != null ? durationTime.hashCode() : 0);
        result = 31 * result + (matchTime != null ? matchTime.hashCode() : 0);
        result = 31 * result + (hadH != null ? hadH.hashCode() : 0);
        result = 31 * result + (hadD != null ? hadD.hashCode() : 0);
        result = 31 * result + (hadA != null ? hadA.hashCode() : 0);
        result = 31 * result + (uniqueId != null ? uniqueId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "W500Entity{" +
                "id=" + id +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", matchCode=" + matchCode +
                ", homeRedCard=" + homeRedCard +
                ", homeYellowCard=" + homeYellowCard +
                ", awayRedCard=" + awayRedCard +
                ", awayYellowCard=" + awayYellowCard +
                ", score='" + score + '\'' +
                ", halfScore='" + halfScore + '\'' +
                ", updateTime=" + updateTime +
                ", durationTime='" + durationTime + '\'' +
                ", matchTime=" + matchTime +
                ", hadH=" + hadH +
                ", hadD=" + hadD +
                ", hadA=" + hadA +
                ", half='" + half + '\'' +
                ", uniqueId=" + uniqueId +
                ", w500Id=" + w500Id +
                '}';
    }

    public void setW500Id(String w500Id) {

        this.w500Id = w500Id;
    }

    @Column(name = "w500_id")
    public String getW500Id() {

        return w500Id;
    }
}
