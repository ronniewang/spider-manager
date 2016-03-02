package com.spider.manager.domain;

import com.spider.global.GamingCompany;

import java.io.Serializable;

/**
 * 用于向sbc推送hilo赔率的变化情况
 *
 * @author wsy
 */
public class UpdateHiloOdds implements Serializable {

    private static long serialVersionUID = 1485252682161618287L;

    /**
     * 比赛编号
     */
    private String matchCode;

    /**
     * 博彩公司名称
     */
    private String company;

    /**
     * 比赛进行时间，为空时推送""
     */
    private String timeMinute;

    /**
     * 比分，为空时推送"-"
     */
    private String score;

    /**
     * 为空的时候推送"-"
     */
    private String oddsHigh;

    /**
     * 为空的时候推送"-"
     */
    private String oddsLow;

    /**
     * 为空的时候推送""
     */
    private String line;

    /**
     * 主队红牌数量，如果为null表示没抓到
     */
    private Integer homeRedCard;

    /**
     * 客队红牌数量，如果为null表示没抓到
     */
    private Integer awayRedCard;

    private String updateTime;

    public UpdateHiloOdds(String matchCode, String company, String timeMinute,
                          String score, String oddsHigh, String oddsLow, String line,
                          Integer homeRedCard, Integer awayRedCard, String updateTime) {

        this.matchCode = matchCode;
        this.company = GamingCompany.abbr(company);
        this.timeMinute = timeMinute;
        this.score = score;
        this.oddsHigh = oddsHigh;
        this.oddsLow = oddsLow;
        this.line = line;
        this.updateTime = updateTime;
        this.homeRedCard = homeRedCard;
        this.awayRedCard = awayRedCard;
    }

    public String getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(String updateTime) {

        this.updateTime = updateTime;
    }

    public Integer getHomeRedCard() {

        return homeRedCard;
    }

    public void setHomeRedCard(Integer homeRedCard) {

        this.homeRedCard = homeRedCard;
    }

    public Integer getAwayRedCard() {

        return awayRedCard;
    }

    public void setAwayRedCard(Integer awayRedCard) {

        this.awayRedCard = awayRedCard;
    }

    public String getMatchCode() {

        return matchCode;
    }

    public String getCompany() {

        return company;
    }

    public String getTimeMinute() {

        return timeMinute;
    }

    public String getScore() {

        return score;
    }

    public String getOddsHigh() {

        return oddsHigh;
    }

    public String getOddsLow() {

        return oddsLow;
    }

    public String getLine() {

        return line;
    }

    public static void setSerialVersionUID(long serialVersionUID) {

        UpdateHiloOdds.serialVersionUID = serialVersionUID;
    }

    public void setMatchCode(String matchCode) {

        this.matchCode = matchCode;
    }

    public void setCompany(String company) {

        this.company = company;
    }

    public void setTimeMinute(String timeMinute) {

        this.timeMinute = timeMinute;
    }

    public void setScore(String score) {

        this.score = score;
    }

    public void setOddsHigh(String oddsHigh) {

        this.oddsHigh = oddsHigh;
    }

    public void setOddsLow(String oddsLow) {

        this.oddsLow = oddsLow;
    }

    public void setLine(String line) {

        this.line = line;
    }

    @Override
    public String toString() {

        return "UpdateHiloOdds{" +
                "matchCode='" + matchCode + '\'' +
                ", company='" + company + '\'' +
                ", timeMinute='" + timeMinute + '\'' +
                ", score='" + score + '\'' +
                ", oddsHigh='" + oddsHigh + '\'' +
                ", oddsLow='" + oddsLow + '\'' +
                ", line='" + line + '\'' +
                ", homeRedCard=" + homeRedCard +
                ", awayRedCard=" + awayRedCard +
                '}';
    }
}
