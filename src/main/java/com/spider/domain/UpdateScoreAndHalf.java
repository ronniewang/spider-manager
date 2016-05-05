package com.spider.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ronnie
 *         该类用于更新比分和比赛进行阶段
 *         两者有一个变化时即进行推送
 *         比分默认为“0-0”
 *         比赛阶段为：上，中，下，完
 */
public class UpdateScoreAndHalf implements Serializable {

    private static long serialVersionUID = -6756531442008657932L;

    private Date catchTime;

    private Integer timeMinute;

    public UpdateScoreAndHalf(
            String matchCode, String score, String halfScore, String half, Integer homeRedCard, Integer awayRedCard, Integer timeMinute) {

        this.halfScore = halfScore;
        this.matchCode = matchCode;
        this.score = score;
        if (half != null) {
            this.half = half.contains("完") ? "完" : half.contains("中") ? "中" : half;
        }
        this.timeMinute = timeMinute;
        this.catchTime = new Date();
        this.homeRedCard = homeRedCard;
        this.awayRedCard = awayRedCard;
    }

    private String half;

    private String matchCode;

    private String score;

    /**
     * 主队红牌数量，如果为null表示没抓到
     */
    private Integer homeRedCard;

    /**
     * 客队红牌数量，如果为null表示没抓到
     */
    private Integer awayRedCard;

    /**
     * 没有的话推"-"
     */
    private String halfScore;

    public Date getCatchTime() {

        return catchTime;
    }

    public void setCatchTime(Date catchTime) {

        this.catchTime = catchTime;
    }

    public Integer getTimeMinute() {

        return timeMinute;
    }

    public void setTimeMinute(Integer timeMinute) {

        this.timeMinute = timeMinute;
    }

    public String getHalfScore() {

        return halfScore;
    }

    public String getHalf() {

        return half;
    }

    public String getMatchCode() {

        return matchCode;
    }

    public String getScore() {

        return score;
    }

    public static void setSerialVersionUID(long serialVersionUID) {

        UpdateScoreAndHalf.serialVersionUID = serialVersionUID;
    }

    public void setHalf(String half) {

        this.half = half;
    }

    public void setMatchCode(String matchCode) {

        this.matchCode = matchCode;
    }

    public void setScore(String score) {

        this.score = score;
    }

    public void setHalfScore(String halfScore) {

        this.halfScore = halfScore;
    }

    public Integer getHomeRedCard() {

        return homeRedCard;
    }

    public Integer getAwayRedCard() {

        return awayRedCard;
    }


    @Override
    public String toString() {

        return "UpdateScoreAndHalf{" +
                "catchTime=" + catchTime +
                ", timeMinute=" + timeMinute +
                ", half='" + half + '\'' +
                ", matchCode='" + matchCode + '\'' +
                ", score='" + score + '\'' +
                ", homeRedCard=" + homeRedCard +
                ", awayRedCard=" + awayRedCard +
                ", halfScore='" + halfScore + '\'' +
                '}';
    }
}