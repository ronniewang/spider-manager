package com.spider.entity;

// Generated 2015-7-14 17:26:36 by Hibernate Tools 4.3.1

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TCrawlerWin310 generated by hbm2java
 */
@Entity
@Table(name = "t_crawler_win310")
public class TCrawlerWin310 implements java.io.Serializable {

    private static final long serialVersionUID = 3188494558012592504L;

    private Long id;

    private String win310EuropeId = "";

    private String competitionNum = "";

    private String startTime = "";

    private String BDate = "";

    private String matchs = "";

    private String homeTeam = "";

    private String visitionTeam = "";

    private String score = "";

    private String halfScore = "";

    private String asiaLink = "";

    private String europeLink = "";

    private Integer winCountOne;

    private Integer winCountTwo;

    private String winProbabilityOne = "";

    private String failProbabilityOne = "";

    private String drawProbabilityOne = "";

    private String winProbabilityTwo = "";

    private String failProbabilityTwo = "";

    private String drawProbabilityTwo = "";

    private Date updateTime = new Date();

    private Date startDateTime;

    private Date stopSaleTime;

    private String durationTime = "";

    private Long uniqueId;

    private String timeMinute;

    @Column(name = "TIME_MINUTE")
    public String getTimeMinute() {

        return timeMinute;
    }

    public void setTimeMinute(String timeMinute) {

        this.timeMinute = timeMinute;
    }

    @Column(name = "HOME_RED_CARD")
    public Integer getHomeRedCard() {

        return homeRedCard;
    }

    public void setHomeRedCard(Integer redCard) {

        this.homeRedCard = redCard;
    }

    private Integer homeRedCard;

    private Integer guestRedCard;

    @Column(name = "GUEST_RED_CARD")
    public Integer getGuestRedCard() {

        return guestRedCard;
    }

    public void setGuestRedCard(Integer guestRedCard) {

        this.guestRedCard = guestRedCard;
    }

    private String singleState;

    @Column(name = "COMPANY_WEIGHT", length = 100)
    public String getSingleState() {

        return singleState;
    }

    public void setSingleState(String singleState) {

        this.singleState = singleState;
    }

    @Column(name = "DURATION_TIME", length = 10)
    public String getDurationTime() {

        return durationTime;
    }

    public void setDurationTime(String durationTime) {

        this.durationTime = durationTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STOP_SALE_TIME", length = 19)
    public Date getStopSaleTime() {

        return stopSaleTime;
    }

    public void setStopSaleTime(Date stopSaleTime) {

        this.stopSaleTime = stopSaleTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE_TIME", length = 19)
    public Date getStartDateTime() {

        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {

        this.startDateTime = startDateTime;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Column(name = "WIN310_EUROPE_ID", nullable = false, length = 20)
    public String getWin310EuropeId() {

        return this.win310EuropeId;
    }

    public void setWin310EuropeId(String win310EuropeId) {

        this.win310EuropeId = StringUtils.trim(win310EuropeId);
    }

    @Column(name = "COMPETITION_NUM", nullable = false, length = 10)
    public String getCompetitionNum() {

        return this.competitionNum;
    }

    public void setCompetitionNum(String competitionNum) {

        this.competitionNum = StringUtils.trim(competitionNum);
    }

    @Column(name = "START_TIME", nullable = false, length = 20)
    public String getStartTime() {

        return this.startTime;
    }

    public void setStartTime(String startTime) {

        this.startTime = startTime;
    }

    @Column(name = "B_DATE", nullable = false, length = 10)
    public String getBDate() {

        return this.BDate;
    }

    public void setBDate(String BDate) {

        this.BDate = StringUtils.trim(BDate);
    }

    @Column(name = "MATCHS", nullable = false, length = 20)
    public String getMatchs() {

        return this.matchs;
    }

    public void setMatchs(String matchs) {

        this.matchs = StringUtils.trim(matchs);
    }

    @Column(name = "HOME_TEAM", nullable = false, length = 50)
    public String getHomeTeam() {

        return this.homeTeam;
    }

    public void setHomeTeam(String homeTeam) {

        this.homeTeam = StringUtils.trim(homeTeam);
    }

    @Column(name = "VISITION_TEAM", nullable = false, length = 50)
    public String getVisitionTeam() {

        return this.visitionTeam;
    }

    public void setVisitionTeam(String visitionTeam) {

        this.visitionTeam = StringUtils.trim(visitionTeam);
    }

    @Column(name = "SCORE", nullable = false, length = 10)
    public String getScore() {

        return this.score;
    }

    public void setScore(String score) {

        this.score = StringUtils.trim(score);
    }

    @Column(name = "HALF_SCORE", nullable = false, length = 10)
    public String getHalfScore() {

        return this.halfScore;
    }

    public void setHalfScore(String halfScore) {

        this.halfScore = StringUtils.trim(halfScore);
    }

    @Column(name = "ASIA_LINK", nullable = false, length = 150)
    public String getAsiaLink() {

        return this.asiaLink;
    }

    public void setAsiaLink(String asiaLink) {

        this.asiaLink = StringUtils.trim(asiaLink);
    }

    @Column(name = "EUROPE_LINK", nullable = false, length = 150)
    public String getEuropeLink() {

        return this.europeLink;
    }

    public void setEuropeLink(String europeLink) {

        this.europeLink = StringUtils.trim(europeLink);
    }

    @Column(name = "WIN_COUNT_ONE")
    public Integer getWinCountOne() {

        return this.winCountOne;
    }

    public void setWinCountOne(Integer winCountOne) {

        this.winCountOne = winCountOne;
    }

    @Column(name = "WIN_COUNT_TWO")
    public Integer getWinCountTwo() {

        return this.winCountTwo;
    }

    public void setWinCountTwo(Integer winCountTwo) {

        this.winCountTwo = winCountTwo;
    }

    @Column(name = "WIN_PROBABILITY_ONE", nullable = false, length = 5)
    public String getWinProbabilityOne() {

        return this.winProbabilityOne;
    }

    public void setWinProbabilityOne(String winProbabilityOne) {

        this.winProbabilityOne = StringUtils.trim(winProbabilityOne);
    }

    @Column(name = "FAIL_PROBABILITY_ONE", nullable = false, length = 5)
    public String getFailProbabilityOne() {

        return this.failProbabilityOne;
    }

    public void setFailProbabilityOne(String failProbabilityOne) {

        this.failProbabilityOne = StringUtils.trim(failProbabilityOne);
    }

    @Column(name = "DRAW_PROBABILITY_ONE", nullable = false, length = 5)
    public String getDrawProbabilityOne() {

        return this.drawProbabilityOne;
    }

    public void setDrawProbabilityOne(String drawProbabilityOne) {

        this.drawProbabilityOne = StringUtils.trim(drawProbabilityOne);
    }

    @Column(name = "WIN_PROBABILITY_TWO", nullable = false, length = 5)
    public String getWinProbabilityTwo() {

        return this.winProbabilityTwo;
    }

    public void setWinProbabilityTwo(String winProbabilityTwo) {

        this.winProbabilityTwo = StringUtils.trim(winProbabilityTwo);
    }

    @Column(name = "FAIL_PROBABILITY_TWO", nullable = false, length = 5)
    public String getFailProbabilityTwo() {

        return this.failProbabilityTwo;
    }

    public void setFailProbabilityTwo(String failProbabilityTwo) {

        this.failProbabilityTwo = StringUtils.trim(failProbabilityTwo);
    }

    @Column(name = "DRAW_PROBABILITY_TWO", nullable = false, length = 5)
    public String getDrawProbabilityTwo() {

        return this.drawProbabilityTwo;
    }

    public void setDrawProbabilityTwo(String drawProbabilityTwo) {

        this.drawProbabilityTwo = StringUtils.trim(drawProbabilityTwo);
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME", length = 19)
    public Date getUpdateTime() {

        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;
    }

    public void setUniqueId(Long uniqueId) {

        this.uniqueId = uniqueId;
    }

    @Basic
    @Column(name = "unique_id")
    public Long getUniqueId() {

        return uniqueId;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((BDate == null) ? 0 : BDate.hashCode());
        result = prime * result + ((asiaLink == null) ? 0 : asiaLink.hashCode());
        result = prime * result + ((competitionNum == null) ? 0 : competitionNum.hashCode());
        result = prime * result + ((drawProbabilityOne == null) ? 0 : drawProbabilityOne.hashCode());
        result = prime * result + ((drawProbabilityTwo == null) ? 0 : drawProbabilityTwo.hashCode());
        result = prime * result + ((durationTime == null) ? 0 : durationTime.hashCode());
        result = prime * result + ((europeLink == null) ? 0 : europeLink.hashCode());
        result = prime * result + ((failProbabilityOne == null) ? 0 : failProbabilityOne.hashCode());
        result = prime * result + ((failProbabilityTwo == null) ? 0 : failProbabilityTwo.hashCode());
        result = prime * result + ((halfScore == null) ? 0 : halfScore.hashCode());
        result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((matchs == null) ? 0 : matchs.hashCode());
        result = prime * result + ((score == null) ? 0 : score.hashCode());
        result = prime * result + ((startDateTime == null) ? 0 : startDateTime.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((stopSaleTime == null) ? 0 : stopSaleTime.hashCode());
        result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
        result = prime * result + ((visitionTeam == null) ? 0 : visitionTeam.hashCode());
        result = prime * result + ((win310EuropeId == null) ? 0 : win310EuropeId.hashCode());
        result = prime * result + ((winCountOne == null) ? 0 : winCountOne.hashCode());
        result = prime * result + ((winCountTwo == null) ? 0 : winCountTwo.hashCode());
        result = prime * result + ((winProbabilityOne == null) ? 0 : winProbabilityOne.hashCode());
        result = prime * result + ((winProbabilityTwo == null) ? 0 : winProbabilityTwo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TCrawlerWin310 other = (TCrawlerWin310) obj;
        if (BDate == null) {
            if (other.BDate != null)
                return false;
        } else if (!BDate.equals(other.BDate))
            return false;
        if (asiaLink == null) {
            if (other.asiaLink != null)
                return false;
        } else if (!asiaLink.equals(other.asiaLink))
            return false;
        if (competitionNum == null) {
            if (other.competitionNum != null)
                return false;
        } else if (!competitionNum.equals(other.competitionNum))
            return false;
        if (drawProbabilityOne == null) {
            if (other.drawProbabilityOne != null)
                return false;
        } else if (!drawProbabilityOne.equals(other.drawProbabilityOne))
            return false;
        if (drawProbabilityTwo == null) {
            if (other.drawProbabilityTwo != null)
                return false;
        } else if (!drawProbabilityTwo.equals(other.drawProbabilityTwo))
            return false;
        if (europeLink == null) {
            if (other.europeLink != null)
                return false;
        } else if (!europeLink.equals(other.europeLink))
            return false;
        if (failProbabilityOne == null) {
            if (other.failProbabilityOne != null)
                return false;
        } else if (!failProbabilityOne.equals(other.failProbabilityOne))
            return false;
        if (failProbabilityTwo == null) {
            if (other.failProbabilityTwo != null)
                return false;
        } else if (!failProbabilityTwo.equals(other.failProbabilityTwo))
            return false;
        if (halfScore == null) {
            if (other.halfScore != null)
                return false;
        } else if (!halfScore.equals(other.halfScore))
            return false;
        if (homeTeam == null) {
            if (other.homeTeam != null)
                return false;
        } else if (!homeTeam.equals(other.homeTeam))
            return false;
        if (matchs == null) {
            if (other.matchs != null)
                return false;
        } else if (!matchs.equals(other.matchs))
            return false;
        if (score == null) {
            if (other.score != null)
                return false;
        } else if (!score.equals(other.score))
            return false;
        if (startDateTime == null) {
            if (other.startDateTime != null)
                return false;
        } else if (!startDateTime.equals(other.startDateTime))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (stopSaleTime == null) {
            if (other.stopSaleTime != null)
                return false;
        } else if (!stopSaleTime.equals(other.stopSaleTime))
            return false;
        if (visitionTeam == null) {
            if (other.visitionTeam != null)
                return false;
        } else if (!visitionTeam.equals(other.visitionTeam))
            return false;
        if (win310EuropeId == null) {
            if (other.win310EuropeId != null)
                return false;
        } else if (!win310EuropeId.equals(other.win310EuropeId))
            return false;
        if (winCountOne == null) {
            if (other.winCountOne != null)
                return false;
        } else if (!winCountOne.equals(other.winCountOne))
            return false;
        if (winCountTwo == null) {
            if (other.winCountTwo != null)
                return false;
        } else if (!winCountTwo.equals(other.winCountTwo))
            return false;
        if (winProbabilityOne == null) {
            if (other.winProbabilityOne != null)
                return false;
        } else if (!winProbabilityOne.equals(other.winProbabilityOne))
            return false;
        if (winProbabilityTwo == null) {
            if (other.winProbabilityTwo != null)
                return false;
        } else if (!winProbabilityTwo.equals(other.winProbabilityTwo))
            return false;
        if (durationTime == null) {
            if (other.durationTime != null)
                return false;
        } else if (!durationTime.equals(other.durationTime))
            return false;
        if (singleState == null) {
            if (other.singleState != null)
                return false;
        } else if (!singleState.equals(other.singleState))
            return false;
        if (homeRedCard == null) {
            if (other.homeRedCard != null)
                return false;
        } else if (!homeRedCard.equals(other.homeRedCard))
            return false;
        if (guestRedCard == null) {
            if (other.guestRedCard != null)
                return false;
        } else if (!guestRedCard.equals(other.guestRedCard))
            return false;
        if (uniqueId == null) {
            if (other.uniqueId != null)
                return false;
        } else if (!uniqueId.equals(other.uniqueId))
            return false;
        if (timeMinute == null) {
            if (other.timeMinute != null)
                return false;
        } else if (!timeMinute.equals(other.timeMinute))
            return false;
        return true;
    }

    public TCrawlerWin310 from(TCrawlerWin310 bean) {

        this.win310EuropeId = bean.getWin310EuropeId();
        this.competitionNum = bean.getCompetitionNum();
        this.startTime = bean.getStartTime();
        this.BDate = bean.getBDate();
        this.matchs = bean.getMatchs();
        this.homeTeam = bean.getHomeTeam();
        this.visitionTeam = bean.getVisitionTeam();
        this.score = bean.getScore();
        this.halfScore = bean.getHalfScore();
        this.asiaLink = bean.getAsiaLink();
        this.europeLink = bean.getEuropeLink();
        this.winCountOne = bean.getWinCountOne();
        this.winCountTwo = bean.getWinCountTwo();
        this.winProbabilityOne = bean.getWinProbabilityOne();
        this.failProbabilityOne = bean.getFailProbabilityOne();
        this.drawProbabilityOne = bean.getDrawProbabilityOne();
        this.winProbabilityTwo = bean.getWinProbabilityTwo();
        this.failProbabilityTwo = bean.getFailProbabilityTwo();
        this.drawProbabilityTwo = bean.getDrawProbabilityTwo();
        this.updateTime = new Date();
        this.homeRedCard = bean.getHomeRedCard();
        this.guestRedCard = bean.getGuestRedCard();
        this.durationTime = bean.getDurationTime();
        this.uniqueId = bean.getUniqueId();
        this.timeMinute = bean.getTimeMinute();
        return this;
    }

    public boolean sameAs(TCrawlerSporttery sporttery) {

        if (!this.startDateTime.equals(sporttery.getStartDateTime())) {
            return false;
        }
        //TODO 暂时尚未比较所有字段
        return true;
    }

    @Override
    public String toString() {

        return "TCrawlerWin310{" +
                "id=" + id +
                ", win310EuropeId='" + win310EuropeId + '\'' +
                ", competitionNum='" + competitionNum + '\'' +
                ", startTime='" + startTime + '\'' +
                ", BDate='" + BDate + '\'' +
                ", matchs='" + matchs + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", visitionTeam='" + visitionTeam + '\'' +
                ", score='" + score + '\'' +
                ", halfScore='" + halfScore + '\'' +
                ", asiaLink='" + asiaLink + '\'' +
                ", europeLink='" + europeLink + '\'' +
                ", winCountOne=" + winCountOne +
                ", winCountTwo=" + winCountTwo +
                ", winProbabilityOne='" + winProbabilityOne + '\'' +
                ", failProbabilityOne='" + failProbabilityOne + '\'' +
                ", drawProbabilityOne='" + drawProbabilityOne + '\'' +
                ", winProbabilityTwo='" + winProbabilityTwo + '\'' +
                ", failProbabilityTwo='" + failProbabilityTwo + '\'' +
                ", drawProbabilityTwo='" + drawProbabilityTwo + '\'' +
                ", updateTime=" + updateTime +
                ", startDateTime=" + startDateTime +
                ", stopSaleTime=" + stopSaleTime +
                ", durationTime='" + durationTime + '\'' +
                ", uniqueId=" + uniqueId +
                ", homeRedCard=" + homeRedCard +
                ", guestRedCard=" + guestRedCard +
                ", singleState='" + singleState + '\'' +
                ", timeMinute='" + timeMinute + '\'' +
                '}';
    }
}
