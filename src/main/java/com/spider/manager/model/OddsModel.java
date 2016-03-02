package com.spider.manager.model;

import com.spider.global.AbsenceState;

public class OddsModel {

    private long ID;//

    private String matchDate;// 2014-01-01 00:00:00

    private String score;

    private String matchCode;//

    private String matchLeague;//

    private String homeTeam;//

    private String awayTeam;//

    private String state;// 0 完全匹配 1彩客不存在 2数据不同

    private String handicapLine;

    private String sportteryHadH = "";

    private String sportteryHadD = "";

    private String sportteryHadA = "";

    private String sportteryHadUpdate = "";

    private String sportteryHhadH = "";

    private String sportteryHhadD = "";

    private String sportteryHhadA = "";

    private String sportteryHhadUpdate = "";

    private String win310HadH = "";

    private String win310HadD = "";

    private String win310HadA = "";

    private String win310HadUpdate = "";

    private String win310HhadH = "";

    private String win310HhadD = "";

    private String win310HhadA = "";

    private String win310HhadUpdate = "";

    private String lijiHadH = "";

    private String lijiHadD = "";

    private String lijiHadA = "";

    private String lijiHadUpdate = "";

    private String lijiHdcHome = "";

    private String lijiHdcAway = "";

    private String lijiHdcUpdate = "";

    private String lijiHdcLine = "";

    private String lijiHiloH = "";

    private String lijiHiloL = "";

    private String lijiHiloUpdate = "";

    private String lijiHiloLine = "";

    private String jinbaoboHiloH = "";

    private String jinbaoboHiloL = "";

    private String jinbaoboHiloUpdate = "";

    private String jinbaoboHiloLine = "";

    private String jinbaoboHadH = "";

    private String jinbaoboHadD = "";

    private String jinbaoboHadA = "";

    private String jinbaoboHadUpdate = "";

    private String jinbaoboHdcHome = "";

    private String jinbaoboHdcAway = "";

    private String jinbaoboHdcUpdate = "";

    private String jinbaoboHdcLine = "";

    private String sportteryHadMargin = "";

    private String win310HadMargin = "";

    private String lijiHadMargin = "";

    private String jinbaoboHadMargin = "";

    private String sportteryHhadMargin = "";

    private String win310HhadMargin = "";

    private String lijiHdcMargin = "";

    private String jinbaoboHdcMargin = "";

    private String durationTime = "";

    private SportteryAllModel sportteryAllModel;

    public SportteryAllModel getSportteryAllModel() {

        return sportteryAllModel;
    }

    public void setSportteryAllModel(SportteryAllModel sportteryAllEntity) {

        this.sportteryAllModel = sportteryAllEntity;
    }

    public boolean isDifferent() {

        return isDifferent;
    }

    public void setIsDifferent(boolean isDifferent) {

        this.isDifferent = isDifferent;
    }

    private boolean isDifferent = false;

    public String getDurationTime() {

        return durationTime;
    }

    public void setDurationTime(String durationTime) {

        this.durationTime = durationTime;
    }

    public String getScore() {

        return score;
    }

    public void setScore(String score) {

        this.score = score;
    }

    public String getSportteryHadUpdate() {

        return sportteryHadUpdate;
    }

    public void setSportteryHadUpdate(String sportteryHadUpdate) {

        this.sportteryHadUpdate = sportteryHadUpdate;
    }

    public String getSportteryHhadUpdate() {

        return sportteryHhadUpdate;
    }

    public void setSportteryHhadUpdate(String sportteryHhadUpdate) {

        this.sportteryHhadUpdate = sportteryHhadUpdate;
    }

    public String getWin310HadUpdate() {

        return win310HadUpdate;
    }

    public void setWin310HadUpdate(String win310HadUpdate) {

        this.win310HadUpdate = win310HadUpdate;
    }

    public String getWin310HhadUpdate() {

        return win310HhadUpdate;
    }

    public void setWin310HhadUpdate(String win310HhadUpdate) {

        this.win310HhadUpdate = win310HhadUpdate;
    }

    public String getLijiHadUpdate() {

        return lijiHadUpdate;
    }

    public void setLijiHadUpdate(String lijiHadUpdate) {

        this.lijiHadUpdate = lijiHadUpdate;
    }

    public String getLijiHdcUpdate() {

        return lijiHdcUpdate;
    }

    public void setLijiHdcUpdate(String lijiHdcUpdate) {

        this.lijiHdcUpdate = lijiHdcUpdate;
    }

    public String getLijiHiloUpdate() {

        return lijiHiloUpdate;
    }

    public void setLijiHiloUpdate(String lijiHiloUpdate) {

        this.lijiHiloUpdate = lijiHiloUpdate;
    }

    public String getJinbaoboHiloUpdate() {

        return jinbaoboHiloUpdate;
    }

    public void setJinbaoboHiloUpdate(String jinbaoboHiloUpdate) {

        this.jinbaoboHiloUpdate = jinbaoboHiloUpdate;
    }

    public String getJinbaoboHadUpdate() {

        return jinbaoboHadUpdate;
    }

    public void setJinbaoboHadUpdate(String jinbaoboHadUpdate) {

        this.jinbaoboHadUpdate = jinbaoboHadUpdate;
    }

    public String getJinbaoboHdcUpdate() {

        return jinbaoboHdcUpdate;
    }

    public void setJinbaoboHdcUpdate(String jinbaoboHdcUpdate) {

        this.jinbaoboHdcUpdate = jinbaoboHdcUpdate;
    }

    public String getSportteryHadMargin() {

        return sportteryHadMargin;
    }

    public void setSportteryHadMargin(String sportteryHadMargin) {

        this.sportteryHadMargin = sportteryHadMargin;
    }

    public String getWin310HadMargin() {

        return win310HadMargin;
    }

    public void setWin310HadMargin(String win310HadMargin) {

        this.win310HadMargin = win310HadMargin;
    }

    public String getLijiHadMargin() {

        return lijiHadMargin;
    }

    public void setLijiHadMargin(String lijiHadMargin) {

        this.lijiHadMargin = lijiHadMargin;
    }

    public String getJinbaoboHadMargin() {

        return jinbaoboHadMargin;
    }

    public void setJinbaoboHadMargin(String jinbaoboHadMargin) {

        this.jinbaoboHadMargin = jinbaoboHadMargin;
    }

    public String getSportteryHhadMargin() {

        return sportteryHhadMargin;
    }

    public void setSportteryHhadMargin(String sportteryHhadMargin) {

        this.sportteryHhadMargin = sportteryHhadMargin;
    }

    public String getWin310HhadMargin() {

        return win310HhadMargin;
    }

    public void setWin310HhadMargin(String win310HhadMargin) {

        this.win310HhadMargin = win310HhadMargin;
    }

    public String getLijiHdcMargin() {

        return lijiHdcMargin;
    }

    public void setLijiHdcMargin(String lijiHdcMargin) {

        this.lijiHdcMargin = lijiHdcMargin;
    }

    public String getJinbaoboHdcMargin() {

        return jinbaoboHdcMargin;
    }

    public void setJinbaoboHdcMargin(String jinbaoboHdcMargin) {

        this.jinbaoboHdcMargin = jinbaoboHdcMargin;
    }

    public String getLijiHiloMargin() {

        return lijiHiloMargin;
    }

    public void setLijiHiloMargin(String lijiHiloMargin) {

        this.lijiHiloMargin = lijiHiloMargin;
    }

    public String getJinbaoboHiloMargin() {

        return jinbaoboHiloMargin;
    }

    public void setJinbaoboHiloMargin(String jinbaoboHiloMargin) {

        this.jinbaoboHiloMargin = jinbaoboHiloMargin;
    }

    private String lijiHiloMargin;

    private String jinbaoboHiloMargin;

    private String absenceState = AbsenceState.Yes.value();// SBC是否有该场比赛，1表示不缺少，0表示缺少

    private String companyName = "";

    private String updateTime;

    public String getCompanyName() {

        return companyName;
    }

    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }

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

    public String getSportteryHadH() {

        return sportteryHadH;
    }

    public void setSportteryHadH(String sportteryHadH) {

        this.sportteryHadH = sportteryHadH;
    }

    public String getSportteryHadD() {

        return sportteryHadD;
    }

    public void setSportteryHadD(String sportteryHadD) {

        this.sportteryHadD = sportteryHadD;
    }

    public String getSportteryHadA() {

        return sportteryHadA;
    }

    public void setSportteryHadA(String sportteryHadA) {

        this.sportteryHadA = sportteryHadA;
    }

    public String getSportteryHhadH() {

        return sportteryHhadH;
    }

    public void setSportteryHhadH(String sportteryHhadH) {

        this.sportteryHhadH = sportteryHhadH;
    }

    public String getSportteryHhadD() {

        return sportteryHhadD;
    }

    public void setSportteryHhadD(String sportteryHhadD) {

        this.sportteryHhadD = sportteryHhadD;
    }

    public String getSportteryHhadA() {

        return sportteryHhadA;
    }

    public void setSportteryHhadA(String sportteryHhadA) {

        this.sportteryHhadA = sportteryHhadA;
    }

    public String getWin310HadH() {

        return win310HadH;
    }

    public void setWin310HadH(String win310HadH) {

        this.win310HadH = win310HadH;
    }

    public String getWin310HadD() {

        return win310HadD;
    }

    public void setWin310HadD(String win310HadD) {

        this.win310HadD = win310HadD;
    }

    public String getWin310HadA() {

        return win310HadA;
    }

    public void setWin310HadA(String win310HadA) {

        this.win310HadA = win310HadA;
    }

    public String getWin310HhadH() {

        return win310HhadH;
    }

    public void setWin310HhadH(String win310HhadH) {

        this.win310HhadH = win310HhadH;
    }

    public String getWin310HhadD() {

        return win310HhadD;
    }

    public void setWin310HhadD(String win310HhadD) {

        this.win310HhadD = win310HhadD;
    }

    public String getWin310HhadA() {

        return win310HhadA;
    }

    public void setWin310HhadA(String win310HhadA) {

        this.win310HhadA = win310HhadA;
    }

    public String getHandicapLine() {

        return handicapLine;
    }

    public void setHandicapLine(String handicapLine) {

        this.handicapLine = handicapLine;
    }

    public String getLijiHadH() {

        return lijiHadH;
    }

    public void setLijiHadH(String lijiHadH) {

        this.lijiHadH = lijiHadH;
    }

    public String getLijiHadD() {

        return lijiHadD;
    }

    public void setLijiHadD(String lijiHadD) {

        this.lijiHadD = lijiHadD;
    }

    public String getLijiHadA() {

        return lijiHadA;
    }

    public void setLijiHadA(String lijiHadA) {

        this.lijiHadA = lijiHadA;
    }

    public String getLijiHdcHome() {

        return lijiHdcHome;
    }

    public void setLijiHdcHome(String lijiHdcHome) {

        this.lijiHdcHome = lijiHdcHome;
    }

    public String getLijiHdcAway() {

        return lijiHdcAway;
    }

    public void setLijiHdcAway(String lijiHdcAway) {

        this.lijiHdcAway = lijiHdcAway;
    }

    public String getLijiHdcLine() {

        return lijiHdcLine;
    }

    public void setLijiHdcLine(String lijiHdcLine) {

        this.lijiHdcLine = lijiHdcLine;
    }

    public String getJinbaoboHadH() {

        return jinbaoboHadH;
    }

    public void setJinbaoboHadH(String jinbaoboHadH) {

        this.jinbaoboHadH = jinbaoboHadH;
    }

    public String getJinbaoboHadD() {

        return jinbaoboHadD;
    }

    public void setJinbaoboHadD(String jinbaoboHadD) {

        this.jinbaoboHadD = jinbaoboHadD;
    }

    public String getJinbaoboHadA() {

        return jinbaoboHadA;
    }

    public void setJinbaoboHadA(String jinbaoboHadA) {

        this.jinbaoboHadA = jinbaoboHadA;
    }

    public String getJinbaoboHdcHome() {

        return jinbaoboHdcHome;
    }

    public void setJinbaoboHdcHome(String jinbaoboHdcHome) {

        this.jinbaoboHdcHome = jinbaoboHdcHome;
    }

    public String getJinbaoboHdcAway() {

        return jinbaoboHdcAway;
    }

    public void setJinbaoboHdcAway(String jinbaoboHdcAway) {

        this.jinbaoboHdcAway = jinbaoboHdcAway;
    }

    public String getJinbaoboHdcLine() {

        return jinbaoboHdcLine;
    }

    public void setJinbaoboHdcLine(String jinbaoboHdcLine) {

        this.jinbaoboHdcLine = jinbaoboHdcLine;
    }

    public String getLijiHiloH() {

        return lijiHiloH;
    }

    public void setLijiHiloH(String lijiHiloH) {

        this.lijiHiloH = lijiHiloH;
    }

    public String getLijiHiloL() {

        return lijiHiloL;
    }

    public void setLijiHiloL(String lijiHiloL) {

        this.lijiHiloL = lijiHiloL;
    }

    public String getLijiHiloLine() {

        return lijiHiloLine;
    }

    public void setLijiHiloLine(String lijiHiloLine) {

        this.lijiHiloLine = lijiHiloLine;
    }

    public String getJinbaoboHiloH() {

        return jinbaoboHiloH;
    }

    public void setJinbaoboHiloH(String jinbaoboHiloH) {

        this.jinbaoboHiloH = jinbaoboHiloH;
    }

    public String getJinbaoboHiloL() {

        return jinbaoboHiloL;
    }

    public void setJinbaoboHiloL(String jinbaoboHiloL) {

        this.jinbaoboHiloL = jinbaoboHiloL;
    }

    public String getJinbaoboHiloLine() {

        return jinbaoboHiloLine;
    }

    public void setJinbaoboHiloLine(String jinbaoboHiloLine) {

        this.jinbaoboHiloLine = jinbaoboHiloLine;
    }

    public void setUpdateTime(String updateTime) {

        this.updateTime = updateTime;
    }

    public String getUpdateTime() {

        return updateTime;
    }

}
