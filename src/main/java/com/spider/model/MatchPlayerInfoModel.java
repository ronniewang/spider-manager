package com.spider.model;

import java.util.List;

/**
 * Created by wsy on 2016/2/3.
 */
public class MatchPlayerInfoModel {

    String homeTeam;

    String awayTeam;

    String date;

    String score;

    String halfScore;

    List<String> homeFirstPlayers;

    List<String> awayFirstPlayers;

    List<String> homeSecondPlayers;

    List<String> awaySecondPlayers;

    public List<String> getHomeFirstPlayers() {

        return homeFirstPlayers;
    }

    public void setHomeFirstPlayers(List<String> homeFirstPlayers) {

        this.homeFirstPlayers = homeFirstPlayers;
    }

    public List<String> getAwayFirstPlayers() {

        return awayFirstPlayers;
    }

    public void setAwayFirstPlayers(List<String> awayFirstPlayers) {

        this.awayFirstPlayers = awayFirstPlayers;
    }

    public List<String> getHomeSecondPlayers() {

        return homeSecondPlayers;
    }

    public void setHomeSecondPlayers(List<String> homeSecondPlayers) {

        this.homeSecondPlayers = homeSecondPlayers;
    }

    public List<String> getAwaySecondPlayers() {

        return awaySecondPlayers;
    }

    public void setAwaySecondPlayers(List<String> awaySecondPlayers) {

        this.awaySecondPlayers = awaySecondPlayers;
    }

    public String getHomeTeam() {

        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {

        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {

        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {

        this.awayTeam = awayTeam;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
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
}
