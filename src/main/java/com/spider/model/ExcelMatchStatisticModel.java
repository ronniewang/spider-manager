package com.spider.model;

import com.spider.entity.NowgoalMatchStatisticEntity;
import com.spider.entity.TCrawlerWin310;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by wsy on 2016/2/15.
 * 字段顺序很重要，不要轻易更改，导出的excel字段按照此顺序排列
 *
 * @author wsy
 */
public class ExcelMatchStatisticModel {

    private String date = "";

    private String homeTeam = "";

    private String score = "";

    private String halfScore = "";

    private String awayTeam = "";

    private String homeCorner = "";

    private String awayCorner = "";

    private String homeYCards = "";

    private String awayYCards = "";

    private String homeShots = "";

    private String awayShots = "";

    private String homeShotsOnGoal = "";

    private String awayShotsOnGoal = "";

    private String homePossession = "";

    private String awayPossession = "";

    private String homePass = "";

    private String awayPass = "";

    private String homePassSuccess = "";

    private String awayPassSuccess = "";

    private String homeSaves = "";

    private String awaySaves = "";

    private String homeTackles = "";

    private String awayTackles = "";

    private String homeDribbles = "";

    private String awayDribbles = "";

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getHomeTeam() {

        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {

        this.homeTeam = homeTeam;
    }

    public String getScore() {

        return score;
    }

    public void setScore(String score) {

        this.score = score;
    }

    public String getHalfScore() {

        return halfScore;
    }

    public void setHalfScore(String halfScore) {

        this.halfScore = halfScore;
    }

    public String getAwayTeam() {

        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {

        this.awayTeam = awayTeam;
    }

    public String getHomeCorner() {

        return homeCorner;
    }

    public void setHomeCorner(String homeCorner) {

        this.homeCorner = homeCorner;
    }

    public String getAwayCorner() {

        return awayCorner;
    }

    public void setAwayCorner(String awayCorner) {

        this.awayCorner = awayCorner;
    }

    public String getHomeYCards() {

        return homeYCards;
    }

    public void setHomeYCards(String homeYCards) {

        this.homeYCards = homeYCards;
    }

    public String getAwayYCards() {

        return awayYCards;
    }

    public void setAwayYCards(String awayYCards) {

        this.awayYCards = awayYCards;
    }

    public String getHomeShots() {

        return homeShots;
    }

    public void setHomeShots(String homeShots) {

        this.homeShots = homeShots;
    }

    public String getAwayShots() {

        return awayShots;
    }

    public void setAwayShots(String awayShots) {

        this.awayShots = awayShots;
    }

    public String getHomeShotsOnGoal() {

        return homeShotsOnGoal;
    }

    public void setHomeShotsOnGoal(String homeShotsOnGoal) {

        this.homeShotsOnGoal = homeShotsOnGoal;
    }

    public String getAwayShotsOnGoal() {

        return awayShotsOnGoal;
    }

    public void setAwayShotsOnGoal(String awayShotsOnGoal) {

        this.awayShotsOnGoal = awayShotsOnGoal;
    }

    public String getHomePossession() {

        return homePossession;
    }

    public void setHomePossession(String homePossession) {

        this.homePossession = homePossession;
    }

    public String getAwayPossession() {

        return awayPossession;
    }

    public void setAwayPossession(String awayPossession) {

        this.awayPossession = awayPossession;
    }

    public String getHomePass() {

        return homePass;
    }

    public void setHomePass(String homePass) {

        this.homePass = homePass;
    }

    public String getAwayPass() {

        return awayPass;
    }

    public void setAwayPass(String awayPass) {

        this.awayPass = awayPass;
    }

    public String getHomePassSuccess() {

        return homePassSuccess;
    }

    public void setHomePassSuccess(String homePassSuccess) {

        this.homePassSuccess = homePassSuccess;
    }

    public String getAwayPassSuccess() {

        return awayPassSuccess;
    }

    public void setAwayPassSuccess(String awayPassSuccess) {

        this.awayPassSuccess = awayPassSuccess;
    }

    public String getHomeSaves() {

        return homeSaves;
    }

    public void setHomeSaves(String homeSaves) {

        this.homeSaves = homeSaves;
    }

    public String getAwaySaves() {

        return awaySaves;
    }

    public void setAwaySaves(String awaySaves) {

        this.awaySaves = awaySaves;
    }

    public String getHomeTackles() {

        return homeTackles;
    }

    public void setHomeTackles(String homeTackles) {

        this.homeTackles = homeTackles;
    }

    public String getAwayTackles() {

        return awayTackles;
    }

    public void setAwayTackles(String awayTackles) {

        this.awayTackles = awayTackles;
    }

    public String getHomeDribbles() {

        return homeDribbles;
    }

    public void setHomeDribbles(String homeDribbles) {

        this.homeDribbles = homeDribbles;
    }

    public String getAwayDribbles() {

        return awayDribbles;
    }

    public void setAwayDribbles(String awayDribbles) {

        this.awayDribbles = awayDribbles;
    }

    public void addRowToExcel(WritableSheet writableSheet, int row) {

        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                writableSheet.addCell(new Label(i, row, (String) fields[i].get(this)));
            } catch (WriteException e) {
                throw new IllegalStateException("无法写入excel", e);
            } catch (IllegalAccessException e) {
                //ignore
            }
        }
    }

    public ExcelMatchStatisticModel build(TCrawlerWin310 win310, List<NowgoalMatchStatisticEntity> statisticEntities) {

        this.setHomeTeam(win310.getHomeTeam());
        this.setAwayTeam(win310.getVisitionTeam());
        this.setDate(win310.getBDate());
        this.setScore(win310.getScore());
        this.setHalfScore(win310.getHalfScore());
        for (NowgoalMatchStatisticEntity s : statisticEntities) {
            if (s.getItem().equals("Possession")) {
                if (s.getTeam() == 1) {
                    this.setHomePossession(String.valueOf(s.getCount()));
                } else {
                    this.setAwayPossession(String.valueOf(s.getCount()));
                }
            }
            if (s.getItem().equals("Saves")) {
                if (s.getTeam() == 1) {
                    this.setHomeSaves(String.valueOf(s.getCount()));
                } else {
                    this.setAwaySaves(String.valueOf(s.getCount()));
                }
            }
            if (s.getItem().equals("Shots")) {
                if (s.getTeam() == 1) {
                    this.setHomeShots(String.valueOf(s.getCount()));
                } else {
                    this.setAwayShots(String.valueOf(s.getCount()));
                }
            }
            if (s.getItem().equals("Shots On Goal")) {
                if (s.getTeam() == 1) {
                    this.setHomeShotsOnGoal(String.valueOf(s.getCount()));
                } else {
                    this.setAwayShotsOnGoal(String.valueOf(s.getCount()));
                }
            }
            if (s.getItem().equals("Corner Kicks")) {
                if (s.getTeam() == 1) {
                    this.setHomeCorner(String.valueOf(s.getCount()));
                } else {
                    this.setAwayCorner(String.valueOf(s.getCount()));
                }
            }
            if (s.getItem().equals("Yellow Cards")) {
                if (s.getTeam() == 1) {
                    this.setHomeYCards(String.valueOf(s.getCount()));
                } else {
                    this.setAwayYCards(String.valueOf(s.getCount()));
                }
            }
        }
        return this;
    }
}
