package com.spider.model;

public class ProductModel {

    private long ID;//

    private String matchDate;// 2014-01-01 00:00:00

    private String matchCode;//

    private String matchLeague;//

    private String homeTeam;//

    private String awayTeam;//

    private String state;// 0 完全匹配 1彩客不存在 2数据不同

    private String HAD;

    private String HHAD;

    private String HAFU;

    private String CRS;

    private String TTG;

    private String handicapLine;

    private String absenceState = MatchModel.ABSENCE_STATE_DEFAULT;// SBC是否有该场比赛，1表示不缺少，0表示缺少

    public String getAbsenceState() {

        return absenceState;
    }

    public void setAbsenceState(String absenceState) {

        this.absenceState = absenceState;
    }

    public long getID() {

        return ID;
    }

    public void setID(long iD) {

        ID = iD;
    }

    public String getMatchDate() {

        return matchDate;
    }

    public void setMatchDate(String matchDate) {

        this.matchDate = matchDate;
    }

    public String getMatchCode() {

        return matchCode;
    }

    public void setMatchCode(String matchCode) {

        this.matchCode = matchCode;
    }

    public String getMatchLeague() {

        return matchLeague;
    }

    public void setMatchLeague(String matchLeague) {

        this.matchLeague = matchLeague;
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

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public String getHAD() {

        return HAD;
    }

    public void setHAD(String hAD) {

        HAD = hAD;
    }

    public String getHHAD() {

        return HHAD;
    }

    public void setHHAD(String hHAD) {

        HHAD = hHAD;
    }

    public String getHAFU() {

        return HAFU;
    }

    public void setHAFU(String hAFU) {

        HAFU = hAFU;
    }

    public String getCRS() {

        return CRS;
    }

    public void setCRS(String cRS) {

        CRS = cRS;
    }

    public String getTTG() {

        return TTG;
    }

    public void setTTG(String tTG) {

        TTG = tTG;
    }

    public String getHandicapLine() {

        return handicapLine;
    }

    public void setHandicapLine(String handicapLine) {

        this.handicapLine = handicapLine;
    }

}
