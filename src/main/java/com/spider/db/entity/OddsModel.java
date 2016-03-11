package com.spider.db.entity;


import com.spider.manager.model.SportteryAllModel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "odds_model")
public class OddsModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;//

    @Column(name = "europe_id")
    private Integer europeId;

    @Column(name = "match_date")
    private String matchDate;// 2014-01-01 00:00:00

    @Column(name = "score")
    private String score;

    @Column(name = "match_code")
    private String matchCode;//

    @Column(name = "match_league")
    private String matchLeague;//

    @Column(name = "home_team")
    private String homeTeam;//

    @Column(name = "away_team")
    private String awayTeam;//

    @Column(name = "win310_start_date_time")
    private Date win310StartDateTime;

    @Column(name = "handicap_line")
    private String handicapLine;

    @Column(name = "sporttery_had_h")
    private String sportteryHadH = "";

    @Column(name = "sporttery_had_d")
    private String sportteryHadD = "";

    @Column(name = "sporttery_had_a")
    private String sportteryHadA = "";

    @Column(name = "sporttery_had_update")
    private String sportteryHadUpdate = "";

    @Column(name = "sporttery_hhad_h")
    private String sportteryHhadH = "";

    @Column(name = "sporttery_hhad_d")
    private String sportteryHhadD = "";

    @Column(name = "sporttery_hhad_a")
    private String sportteryHhadA = "";

    @Column(name = "sporttery_hhad_update")
    private String sportteryHhadUpdate = "";

    @Column(name = "win310_had_h")
    private String win310HadH = "";

    @Column(name = "win310_had_d")
    private String win310HadD = "";

    @Column(name = "win310_had_a")
    private String win310HadA = "";

    @Column(name = "win310_had_update")
    private String win310HadUpdate = "";

    @Column(name = "win310_hhad_h")
    private String win310HhadH = "";

    @Column(name = "win310_hhad_d")
    private String win310HhadD = "";

    @Column(name = "win310_hhad_a")
    private String win310HhadA = "";

    @Column(name = "win310_hhad_update")
    private String win310HhadUpdate = "";

    @Column(name = "liji_had_h")
    private String lijiHadH = "";

    @Column(name = "liji_had_d")
    private String lijiHadD = "";

    @Column(name = "liji_had_a")
    private String lijiHadA = "";

    @Column(name = "liji_had_update")
    private String lijiHadUpdate = "";

    @Column(name = "liji_hdc_home")
    private String lijiHdcHome = "";

    @Column(name = "liji_hdc_away")
    private String lijiHdcAway = "";

    @Column(name = "liji_hdc_update")
    private String lijiHdcUpdate = "";

    @Column(name = "liji_hdc_line")
    private String lijiHdcLine = "";

    @Column(name = "liji_hilo_h")
    private String lijiHiloH = "";

    @Column(name = "liji_hilo_l")
    private String lijiHiloL = "";

    @Column(name = "liji_hilo_update")
    private String lijiHiloUpdate = "";

    @Column(name = "liji_hilo_line")
    private String lijiHiloLine = "";

    @Column(name = "jinbaobo_hilo_h")
    private String jinbaoboHiloH = "";

    @Column(name = "jinbaobo_hilo_l")
    private String jinbaoboHiloL = "";

    @Column(name = "jinbaobo_hilo_update")
    private String jinbaoboHiloUpdate = "";

    @Column(name = "jinbaobo_hilo_line")
    private String jinbaoboHiloLine = "";

    @Column(name = "jinbaobo_had_h")
    private String jinbaoboHadH = "";

    @Column(name = "jinbaobo_had_d")
    private String jinbaoboHadD = "";

    @Column(name = "jinbaobo_had_a")
    private String jinbaoboHadA = "";

    @Column(name = "jinbaobo_had_update")
    private String jinbaoboHadUpdate = "";

    @Column(name = "jinbaobo_hdc_home")
    private String jinbaoboHdcHome = "";

    @Column(name = "jinbaobo_hdc_away")
    private String jinbaoboHdcAway = "";

    @Column(name = "jinbaobo_hdc_update")
    private String jinbaoboHdcUpdate = "";

    @Column(name = "jinbaobo_hdc_line")
    private String jinbaoboHdcLine = "";

    @Column(name = "sporttery_had_margin")
    private String sportteryHadMargin = "";

    @Column(name = "win310_had_margin")
    private String win310HadMargin = "";

    @Column(name = "liji_had_margin")
    private String lijiHadMargin = "";

    @Column(name = "jinbaobo_had_margin")
    private String jinbaoboHadMargin = "";

    @Column(name = "sporttery_hhad_margin")
    private String sportteryHhadMargin = "";

    @Column(name = "win310_hhad_margin")
    private String win310HhadMargin = "";

    @Column(name = "liji_hdc_margin")
    private String lijiHdcMargin = "";

    @Column(name = "jinbaobo_hdc_margin")
    private String jinbaoboHdcMargin = "";

    @Column(name = "duration_time")
    private String durationTime = "";

    @Column(name = "liji_hilo_margin")
    private String lijiHiloMargin;

    @Column(name = "jinbaobo_hilo_margin")
    private String jinbaoboHiloMargin;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Transient
    private SportteryAllModel sportteryAllModel;

    @Transient
    private String state;// 0 完全匹配 1彩客不存在 2数据不同

    @Transient
    private String absenceState = "1";// SBC是否有该场比赛，1表示不缺少，0表示缺少

    @Transient
    private boolean isDifferent = false;

    public SportteryAllModel getSportteryAllModel() {

        return sportteryAllModel;
    }

    public void setSportteryAllModel(SportteryAllModel sportteryAllModel) {

        this.sportteryAllModel = sportteryAllModel;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Date getCreatedDate() {

        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {

        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {

        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {

        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean isDifferent() {

        return isDifferent;
    }

    public void setIsDifferent(boolean isDifferent) {

        this.isDifferent = isDifferent;
    }

    public Integer getEuropeId() {

        return europeId;
    }

    public void setEuropeId(Integer europeId) {

        this.europeId = europeId;
    }

    public Date getWin310StartDateTime() {

        return win310StartDateTime;
    }

    public void setWin310StartDateTime(Date win310StartDateTime) {

        this.win310StartDateTime = win310StartDateTime;
    }

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

    public String getAbsenceState() {

        return absenceState;
    }

    public void setAbsenceState(String absenceState) {

        this.absenceState = absenceState;
    }

    public Long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OddsModel oddsModel = (OddsModel) o;

        if (europeId != null ? !europeId.equals(oddsModel.europeId) : oddsModel.europeId != null) return false;
        if (matchDate != null ? !matchDate.equals(oddsModel.matchDate) : oddsModel.matchDate != null) return false;
        if (score != null ? !score.equals(oddsModel.score) : oddsModel.score != null) return false;
        if (matchCode != null ? !matchCode.equals(oddsModel.matchCode) : oddsModel.matchCode != null) return false;
        if (matchLeague != null ? !matchLeague.equals(oddsModel.matchLeague) : oddsModel.matchLeague != null)
            return false;
        if (homeTeam != null ? !homeTeam.equals(oddsModel.homeTeam) : oddsModel.homeTeam != null) return false;
        if (awayTeam != null ? !awayTeam.equals(oddsModel.awayTeam) : oddsModel.awayTeam != null) return false;
        if (win310StartDateTime != null ? !win310StartDateTime.equals(oddsModel.win310StartDateTime) : oddsModel.win310StartDateTime != null)
            return false;
        if (handicapLine != null ? !handicapLine.equals(oddsModel.handicapLine) : oddsModel.handicapLine != null)
            return false;
        if (sportteryHadH != null ? !sportteryHadH.equals(oddsModel.sportteryHadH) : oddsModel.sportteryHadH != null)
            return false;
        if (sportteryHadD != null ? !sportteryHadD.equals(oddsModel.sportteryHadD) : oddsModel.sportteryHadD != null)
            return false;
        if (sportteryHadA != null ? !sportteryHadA.equals(oddsModel.sportteryHadA) : oddsModel.sportteryHadA != null)
            return false;
        if (sportteryHadUpdate != null ? !sportteryHadUpdate.equals(oddsModel.sportteryHadUpdate) : oddsModel.sportteryHadUpdate != null)
            return false;
        if (sportteryHhadH != null ? !sportteryHhadH.equals(oddsModel.sportteryHhadH) : oddsModel.sportteryHhadH != null)
            return false;
        if (sportteryHhadD != null ? !sportteryHhadD.equals(oddsModel.sportteryHhadD) : oddsModel.sportteryHhadD != null)
            return false;
        if (sportteryHhadA != null ? !sportteryHhadA.equals(oddsModel.sportteryHhadA) : oddsModel.sportteryHhadA != null)
            return false;
        if (sportteryHhadUpdate != null ? !sportteryHhadUpdate.equals(oddsModel.sportteryHhadUpdate) : oddsModel.sportteryHhadUpdate != null)
            return false;
        if (win310HadH != null ? !win310HadH.equals(oddsModel.win310HadH) : oddsModel.win310HadH != null) return false;
        if (win310HadD != null ? !win310HadD.equals(oddsModel.win310HadD) : oddsModel.win310HadD != null) return false;
        if (win310HadA != null ? !win310HadA.equals(oddsModel.win310HadA) : oddsModel.win310HadA != null) return false;
        if (win310HadUpdate != null ? !win310HadUpdate.equals(oddsModel.win310HadUpdate) : oddsModel.win310HadUpdate != null)
            return false;
        if (win310HhadH != null ? !win310HhadH.equals(oddsModel.win310HhadH) : oddsModel.win310HhadH != null)
            return false;
        if (win310HhadD != null ? !win310HhadD.equals(oddsModel.win310HhadD) : oddsModel.win310HhadD != null)
            return false;
        if (win310HhadA != null ? !win310HhadA.equals(oddsModel.win310HhadA) : oddsModel.win310HhadA != null)
            return false;
        if (win310HhadUpdate != null ? !win310HhadUpdate.equals(oddsModel.win310HhadUpdate) : oddsModel.win310HhadUpdate != null)
            return false;
        if (lijiHadH != null ? !lijiHadH.equals(oddsModel.lijiHadH) : oddsModel.lijiHadH != null) return false;
        if (lijiHadD != null ? !lijiHadD.equals(oddsModel.lijiHadD) : oddsModel.lijiHadD != null) return false;
        if (lijiHadA != null ? !lijiHadA.equals(oddsModel.lijiHadA) : oddsModel.lijiHadA != null) return false;
        if (lijiHadUpdate != null ? !lijiHadUpdate.equals(oddsModel.lijiHadUpdate) : oddsModel.lijiHadUpdate != null)
            return false;
        if (lijiHdcHome != null ? !lijiHdcHome.equals(oddsModel.lijiHdcHome) : oddsModel.lijiHdcHome != null)
            return false;
        if (lijiHdcAway != null ? !lijiHdcAway.equals(oddsModel.lijiHdcAway) : oddsModel.lijiHdcAway != null)
            return false;
        if (lijiHdcUpdate != null ? !lijiHdcUpdate.equals(oddsModel.lijiHdcUpdate) : oddsModel.lijiHdcUpdate != null)
            return false;
        if (lijiHdcLine != null ? !lijiHdcLine.equals(oddsModel.lijiHdcLine) : oddsModel.lijiHdcLine != null)
            return false;
        if (lijiHiloH != null ? !lijiHiloH.equals(oddsModel.lijiHiloH) : oddsModel.lijiHiloH != null) return false;
        if (lijiHiloL != null ? !lijiHiloL.equals(oddsModel.lijiHiloL) : oddsModel.lijiHiloL != null) return false;
        if (lijiHiloUpdate != null ? !lijiHiloUpdate.equals(oddsModel.lijiHiloUpdate) : oddsModel.lijiHiloUpdate != null)
            return false;
        if (lijiHiloLine != null ? !lijiHiloLine.equals(oddsModel.lijiHiloLine) : oddsModel.lijiHiloLine != null)
            return false;
        if (jinbaoboHiloH != null ? !jinbaoboHiloH.equals(oddsModel.jinbaoboHiloH) : oddsModel.jinbaoboHiloH != null)
            return false;
        if (jinbaoboHiloL != null ? !jinbaoboHiloL.equals(oddsModel.jinbaoboHiloL) : oddsModel.jinbaoboHiloL != null)
            return false;
        if (jinbaoboHiloUpdate != null ? !jinbaoboHiloUpdate.equals(oddsModel.jinbaoboHiloUpdate) : oddsModel.jinbaoboHiloUpdate != null)
            return false;
        if (jinbaoboHiloLine != null ? !jinbaoboHiloLine.equals(oddsModel.jinbaoboHiloLine) : oddsModel.jinbaoboHiloLine != null)
            return false;
        if (jinbaoboHadH != null ? !jinbaoboHadH.equals(oddsModel.jinbaoboHadH) : oddsModel.jinbaoboHadH != null)
            return false;
        if (jinbaoboHadD != null ? !jinbaoboHadD.equals(oddsModel.jinbaoboHadD) : oddsModel.jinbaoboHadD != null)
            return false;
        if (jinbaoboHadA != null ? !jinbaoboHadA.equals(oddsModel.jinbaoboHadA) : oddsModel.jinbaoboHadA != null)
            return false;
        if (jinbaoboHadUpdate != null ? !jinbaoboHadUpdate.equals(oddsModel.jinbaoboHadUpdate) : oddsModel.jinbaoboHadUpdate != null)
            return false;
        if (jinbaoboHdcHome != null ? !jinbaoboHdcHome.equals(oddsModel.jinbaoboHdcHome) : oddsModel.jinbaoboHdcHome != null)
            return false;
        if (jinbaoboHdcAway != null ? !jinbaoboHdcAway.equals(oddsModel.jinbaoboHdcAway) : oddsModel.jinbaoboHdcAway != null)
            return false;
        if (jinbaoboHdcUpdate != null ? !jinbaoboHdcUpdate.equals(oddsModel.jinbaoboHdcUpdate) : oddsModel.jinbaoboHdcUpdate != null)
            return false;
        if (jinbaoboHdcLine != null ? !jinbaoboHdcLine.equals(oddsModel.jinbaoboHdcLine) : oddsModel.jinbaoboHdcLine != null)
            return false;
        if (sportteryHadMargin != null ? !sportteryHadMargin.equals(oddsModel.sportteryHadMargin) : oddsModel.sportteryHadMargin != null)
            return false;
        if (win310HadMargin != null ? !win310HadMargin.equals(oddsModel.win310HadMargin) : oddsModel.win310HadMargin != null)
            return false;
        if (lijiHadMargin != null ? !lijiHadMargin.equals(oddsModel.lijiHadMargin) : oddsModel.lijiHadMargin != null)
            return false;
        if (jinbaoboHadMargin != null ? !jinbaoboHadMargin.equals(oddsModel.jinbaoboHadMargin) : oddsModel.jinbaoboHadMargin != null)
            return false;
        if (sportteryHhadMargin != null ? !sportteryHhadMargin.equals(oddsModel.sportteryHhadMargin) : oddsModel.sportteryHhadMargin != null)
            return false;
        if (win310HhadMargin != null ? !win310HhadMargin.equals(oddsModel.win310HhadMargin) : oddsModel.win310HhadMargin != null)
            return false;
        if (lijiHdcMargin != null ? !lijiHdcMargin.equals(oddsModel.lijiHdcMargin) : oddsModel.lijiHdcMargin != null)
            return false;
        if (jinbaoboHdcMargin != null ? !jinbaoboHdcMargin.equals(oddsModel.jinbaoboHdcMargin) : oddsModel.jinbaoboHdcMargin != null)
            return false;
        if (durationTime != null ? !durationTime.equals(oddsModel.durationTime) : oddsModel.durationTime != null)
            return false;
        if (lijiHiloMargin != null ? !lijiHiloMargin.equals(oddsModel.lijiHiloMargin) : oddsModel.lijiHiloMargin != null)
            return false;
        return !(jinbaoboHiloMargin != null ? !jinbaoboHiloMargin.equals(oddsModel.jinbaoboHiloMargin) : oddsModel.jinbaoboHiloMargin != null);
    }

    @Override
    public int hashCode() {

        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (europeId != null ? europeId.hashCode() : 0);
        result = 31 * result + (matchDate != null ? matchDate.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (matchCode != null ? matchCode.hashCode() : 0);
        result = 31 * result + (matchLeague != null ? matchLeague.hashCode() : 0);
        result = 31 * result + (homeTeam != null ? homeTeam.hashCode() : 0);
        result = 31 * result + (awayTeam != null ? awayTeam.hashCode() : 0);
        result = 31 * result + (win310StartDateTime != null ? win310StartDateTime.hashCode() : 0);
        result = 31 * result + (handicapLine != null ? handicapLine.hashCode() : 0);
        result = 31 * result + (sportteryHadH != null ? sportteryHadH.hashCode() : 0);
        result = 31 * result + (sportteryHadD != null ? sportteryHadD.hashCode() : 0);
        result = 31 * result + (sportteryHadA != null ? sportteryHadA.hashCode() : 0);
        result = 31 * result + (sportteryHadUpdate != null ? sportteryHadUpdate.hashCode() : 0);
        result = 31 * result + (sportteryHhadH != null ? sportteryHhadH.hashCode() : 0);
        result = 31 * result + (sportteryHhadD != null ? sportteryHhadD.hashCode() : 0);
        result = 31 * result + (sportteryHhadA != null ? sportteryHhadA.hashCode() : 0);
        result = 31 * result + (sportteryHhadUpdate != null ? sportteryHhadUpdate.hashCode() : 0);
        result = 31 * result + (win310HadH != null ? win310HadH.hashCode() : 0);
        result = 31 * result + (win310HadD != null ? win310HadD.hashCode() : 0);
        result = 31 * result + (win310HadA != null ? win310HadA.hashCode() : 0);
        result = 31 * result + (win310HadUpdate != null ? win310HadUpdate.hashCode() : 0);
        result = 31 * result + (win310HhadH != null ? win310HhadH.hashCode() : 0);
        result = 31 * result + (win310HhadD != null ? win310HhadD.hashCode() : 0);
        result = 31 * result + (win310HhadA != null ? win310HhadA.hashCode() : 0);
        result = 31 * result + (win310HhadUpdate != null ? win310HhadUpdate.hashCode() : 0);
        result = 31 * result + (lijiHadH != null ? lijiHadH.hashCode() : 0);
        result = 31 * result + (lijiHadD != null ? lijiHadD.hashCode() : 0);
        result = 31 * result + (lijiHadA != null ? lijiHadA.hashCode() : 0);
        result = 31 * result + (lijiHadUpdate != null ? lijiHadUpdate.hashCode() : 0);
        result = 31 * result + (lijiHdcHome != null ? lijiHdcHome.hashCode() : 0);
        result = 31 * result + (lijiHdcAway != null ? lijiHdcAway.hashCode() : 0);
        result = 31 * result + (lijiHdcUpdate != null ? lijiHdcUpdate.hashCode() : 0);
        result = 31 * result + (lijiHdcLine != null ? lijiHdcLine.hashCode() : 0);
        result = 31 * result + (lijiHiloH != null ? lijiHiloH.hashCode() : 0);
        result = 31 * result + (lijiHiloL != null ? lijiHiloL.hashCode() : 0);
        result = 31 * result + (lijiHiloUpdate != null ? lijiHiloUpdate.hashCode() : 0);
        result = 31 * result + (lijiHiloLine != null ? lijiHiloLine.hashCode() : 0);
        result = 31 * result + (jinbaoboHiloH != null ? jinbaoboHiloH.hashCode() : 0);
        result = 31 * result + (jinbaoboHiloL != null ? jinbaoboHiloL.hashCode() : 0);
        result = 31 * result + (jinbaoboHiloUpdate != null ? jinbaoboHiloUpdate.hashCode() : 0);
        result = 31 * result + (jinbaoboHiloLine != null ? jinbaoboHiloLine.hashCode() : 0);
        result = 31 * result + (jinbaoboHadH != null ? jinbaoboHadH.hashCode() : 0);
        result = 31 * result + (jinbaoboHadD != null ? jinbaoboHadD.hashCode() : 0);
        result = 31 * result + (jinbaoboHadA != null ? jinbaoboHadA.hashCode() : 0);
        result = 31 * result + (jinbaoboHadUpdate != null ? jinbaoboHadUpdate.hashCode() : 0);
        result = 31 * result + (jinbaoboHdcHome != null ? jinbaoboHdcHome.hashCode() : 0);
        result = 31 * result + (jinbaoboHdcAway != null ? jinbaoboHdcAway.hashCode() : 0);
        result = 31 * result + (jinbaoboHdcUpdate != null ? jinbaoboHdcUpdate.hashCode() : 0);
        result = 31 * result + (jinbaoboHdcLine != null ? jinbaoboHdcLine.hashCode() : 0);
        result = 31 * result + (sportteryHadMargin != null ? sportteryHadMargin.hashCode() : 0);
        result = 31 * result + (win310HadMargin != null ? win310HadMargin.hashCode() : 0);
        result = 31 * result + (lijiHadMargin != null ? lijiHadMargin.hashCode() : 0);
        result = 31 * result + (jinbaoboHadMargin != null ? jinbaoboHadMargin.hashCode() : 0);
        result = 31 * result + (sportteryHhadMargin != null ? sportteryHhadMargin.hashCode() : 0);
        result = 31 * result + (win310HhadMargin != null ? win310HhadMargin.hashCode() : 0);
        result = 31 * result + (lijiHdcMargin != null ? lijiHdcMargin.hashCode() : 0);
        result = 31 * result + (jinbaoboHdcMargin != null ? jinbaoboHdcMargin.hashCode() : 0);
        result = 31 * result + (durationTime != null ? durationTime.hashCode() : 0);
        result = 31 * result + (lijiHiloMargin != null ? lijiHiloMargin.hashCode() : 0);
        result = 31 * result + (jinbaoboHiloMargin != null ? jinbaoboHiloMargin.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (absenceState != null ? absenceState.hashCode() : 0);
        result = 31 * result + (isDifferent ? 1 : 0);
        return result;
    }
}
