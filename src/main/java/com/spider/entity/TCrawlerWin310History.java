package com.spider.entity;

// Generated 2015-7-14 17:26:36 by Hibernate Tools 4.3.1

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TCrawlerWin310 generated by hbm2java
 */
@Entity
@Table(name = "t_crawler_win310_history")
public class TCrawlerWin310History implements java.io.Serializable {



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
    public void setUniqueId(Long uniqueId) {

        this.uniqueId = uniqueId;
    }

    @Basic
    @Column(name = "unique_id")
    public Long getUniqueId() {

        return uniqueId;
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

        this.win310EuropeId = win310EuropeId.trim();
    }

    @Column(name = "COMPETITION_NUM", nullable = false, length = 10)
    public String getCompetitionNum() {

        return this.competitionNum;
    }

    public void setCompetitionNum(String competitionNum) {

        this.competitionNum = competitionNum.trim();
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

        this.BDate = BDate.trim();
    }

    @Column(name = "MATCHS", nullable = false, length = 20)
    public String getMatchs() {

        return this.matchs;
    }

    public void setMatchs(String matchs) {

        this.matchs = matchs.trim();
    }

    @Column(name = "HOME_TEAM", nullable = false, length = 50)
    public String getHomeTeam() {

        return this.homeTeam;
    }

    public void setHomeTeam(String homeTeam) {

        this.homeTeam = homeTeam.trim();
    }

    @Column(name = "VISITION_TEAM", nullable = false, length = 50)
    public String getVisitionTeam() {

        return this.visitionTeam;
    }

    public void setVisitionTeam(String visitionTeam) {

        this.visitionTeam = visitionTeam.trim();
    }

    @Column(name = "SCORE", nullable = false, length = 10)
    public String getScore() {

        return this.score;
    }

    public void setScore(String score) {

        this.score = score.trim();
    }

    @Column(name = "HALF_SCORE", nullable = false, length = 10)
    public String getHalfScore() {

        return this.halfScore;
    }

    public void setHalfScore(String halfScore) {

        this.halfScore = halfScore.trim();
    }

    @Column(name = "ASIA_LINK", nullable = false, length = 150)
    public String getAsiaLink() {

        return this.asiaLink;
    }

    public void setAsiaLink(String asiaLink) {

        this.asiaLink = asiaLink.trim();
    }

    @Column(name = "EUROPE_LINK", nullable = false, length = 150)
    public String getEuropeLink() {

        return this.europeLink;
    }

    public void setEuropeLink(String europeLink) {

        this.europeLink = europeLink.trim();
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

        this.winProbabilityOne = winProbabilityOne.trim();
    }

    @Column(name = "FAIL_PROBABILITY_ONE", nullable = false, length = 5)
    public String getFailProbabilityOne() {

        return this.failProbabilityOne;
    }

    public void setFailProbabilityOne(String failProbabilityOne) {

        this.failProbabilityOne = failProbabilityOne.trim();
    }

    @Column(name = "DRAW_PROBABILITY_ONE", nullable = false, length = 5)
    public String getDrawProbabilityOne() {

        return this.drawProbabilityOne;
    }

    public void setDrawProbabilityOne(String drawProbabilityOne) {

        this.drawProbabilityOne = drawProbabilityOne.trim();
    }

    @Column(name = "WIN_PROBABILITY_TWO", nullable = false, length = 5)
    public String getWinProbabilityTwo() {

        return this.winProbabilityTwo;
    }

    public void setWinProbabilityTwo(String winProbabilityTwo) {

        this.winProbabilityTwo = winProbabilityTwo.trim();
    }

    @Column(name = "FAIL_PROBABILITY_TWO", nullable = false, length = 5)
    public String getFailProbabilityTwo() {

        return this.failProbabilityTwo;
    }

    public void setFailProbabilityTwo(String failProbabilityTwo) {

        this.failProbabilityTwo = failProbabilityTwo.trim();
    }

    @Column(name = "DRAW_PROBABILITY_TWO", nullable = false, length = 5)
    public String getDrawProbabilityTwo() {

        return this.drawProbabilityTwo;
    }

    public void setDrawProbabilityTwo(String drawProbabilityTwo) {

        this.drawProbabilityTwo = drawProbabilityTwo.trim();
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME", length = 19)
    public Date getUpdateTime() {

        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;
    }

    public TCrawlerWin310History from(TCrawlerWin310 bean) {

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
        this.startDateTime = bean.getStartDateTime();
        this.stopSaleTime = bean.getStopSaleTime();
        this.durationTime = bean.getDurationTime();
        this.uniqueId = bean.getUniqueId();
        this.timeMinute = bean.getTimeMinute();
        return this;
    }

    @Override
    public String toString() {

        return "TCrawlerWin310History{" +
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
                ", timeMinute=" + timeMinute +
                '}';
    }
}
