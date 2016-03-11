package com.spider.domain;

import com.spider.global.GamingCompany;

import java.io.Serializable;

/**
 * 用于向sbc推送hdc赔率的变化情况
 *
 * @author wsy
 */
public class UpdateHdcOdds implements Serializable {

    private static long serialVersionUID = 1485252681161618287L;

    private final String updateTime;

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
     * 为空的时候为"-"
     */
    private String oddsH;

    /**
     * 为空的时候为"-"
     */
    private String oddsD;

    /**
     * 为空的时候为""
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

    public UpdateHdcOdds(
            String matchCode, String company, String timeMinute,
            String score, String oddsH, String oddsD, String line,
            Integer homeRedCard, Integer awayRedCard, String updateTime) {

        this.matchCode = matchCode;
        this.company = GamingCompany.abbr(company);
        this.timeMinute = timeMinute;
        this.score = score;
        this.oddsH = oddsH;
        this.oddsD = oddsD;
        this.line = line;
        this.updateTime = updateTime;
        this.homeRedCard = homeRedCard;
        this.awayRedCard = awayRedCard;
    }

    public String getUpdateTime() {

        return updateTime;
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

    public String getOddsH() {

        return oddsH;
    }

    public String getOddsD() {

        return oddsD;
    }

    public String getLine() {

        return line;
    }

    public static void setSerialVersionUID(long serialVersionUID) {

        UpdateHdcOdds.serialVersionUID = serialVersionUID;
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

    public void setOddsH(String oddsH) {

        this.oddsH = oddsH;
    }

    public void setOddsD(String oddsD) {

        this.oddsD = oddsD;
    }

    public void setLine(String line) {

        this.line = line;
    }

    @Override
    public String toString() {

        return "UpdateHdcOdds{" +
                "matchCode='" + matchCode + '\'' +
                ", company='" + company + '\'' +
                ", timeMinute='" + timeMinute + '\'' +
                ", score='" + score + '\'' +
                ", oddsH='" + oddsH + '\'' +
                ", oddsD='" + oddsD + '\'' +
                ", line='" + line + '\'' +
                ", homeRedCard=" + homeRedCard +
                ", awayRedCard=" + awayRedCard +
                '}';
    }
}
