package com.spider.manager.model;

import com.spider.db.entity.SportteryAllEntity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wsy on 2015/11/19.
 */
public class SportteryAllModel {

    private long id;

    private String matchCode;

    private String league;

    private String homeTeam;

    private String awayTeam;

    private String hadH;

    private String hadD;

    private String hadA;

    private String hhadH;

    private String hhadD;

    private String hhadA;

    private String hhadLine;

    private String hafuHh;

    private String hafuHd;

    private String hafuHa;

    private String hafuDh;

    private String hafuDd;

    private String hafuDa;

    private String hafuAh;

    private String hafuAd;

    private String hafuAa;

    private String ttg0;

    private String ttg1;

    private String ttg2;

    private String ttg3;

    private String ttg4;

    private String ttg5;

    private String ttg6;

    private String ttg7Up;

    private String score10;

    private String score20;

    private String score21;

    private String score30;

    private String score31;

    private String score32;

    private String score40;

    private String score41;

    private String score42;

    private String score50;

    private String score51;

    private String score52;

    private String scoreHElse;

    private String score00;

    private String score11;

    private String score22;

    private String score33;

    private String scoreDElse;

    private String score01;

    private String score02;

    private String score12;

    private String score03;

    private String score13;

    private String score23;

    private String score04;

    private String score14;

    private String score24;

    private String score05;

    private String score15;

    private String score25;

    private String scoreAElse;

    private Timestamp oddsUpdateTime;

    private Timestamp updateTime;

    public SportteryAllModel() {

    }

    public SportteryAllModel(SportteryAllEntity s) {

        this.matchCode = s.getMatchCode();
        this.league = s.getLeague();
        this.homeTeam = s.getHomeTeam();
        this.awayTeam = s.getAwayTeam();
        this.hadH = s.getHadH() + "";
        this.hadD = s.getHadD() + "";
        this.hadA = s.getHadA() + "";
        this.hhadH = s.getHhadH() + "";
        this.hhadD = s.getHhadD() + "";
        this.hhadA = s.getHhadA() + "";
        this.hhadLine = s.getHhadLine() + "";
        this.hafuHh = s.getHafuHh() + "";
        this.hafuHd = s.getHafuHd() + "";
        this.hafuHa = s.getHafuHa() + "";
        this.hafuDh = s.getHafuDh() + "";
        this.hafuDd = s.getHafuDd() + "";
        this.hafuDa = s.getHafuDa() + "";
        this.hafuAh = s.getHafuAh() + "";
        this.hafuAd = s.getHafuAd() + "";
        this.hafuAa = s.getHafuAa() + "";
        this.ttg0 = s.getTtg0() + "";
        this.ttg1 = s.getTtg1() + "";
        this.ttg2 = s.getTtg2() + "";
        this.ttg3 = s.getTtg3() + "";
        this.ttg4 = s.getTtg4() + "";
        this.ttg5 = s.getTtg5() + "";
        this.ttg6 = s.getTtg6() + "";
        this.ttg7Up = s.getTtg7Up() + "";
        this.score10 = s.getScore10() + "";
        this.score20 = s.getScore20() + "";
        this.score21 = s.getScore21() + "";
        this.score30 = s.getScore30() + "";
        this.score31 = s.getScore31() + "";
        this.score32 = s.getScore32() + "";
        this.score40 = s.getScore40() + "";
        this.score41 = s.getScore41() + "";
        this.score42 = s.getScore42() + "";
        this.score50 = s.getScore50() + "";
        this.score51 = s.getScore51() + "";
        this.score52 = s.getScore52() + "";
        this.scoreHElse = s.getScoreHElse() + "";
        this.score00 = s.getScore00() + "";
        this.score11 = s.getScore11() + "";
        this.score22 = s.getScore22() + "";
        this.score33 = s.getScore33() + "";
        this.scoreDElse = s.getScoreDElse() + "";
        this.score01 = s.getScore01() + "";
        this.score02 = s.getScore02() + "";
        this.score12 = s.getScore12() + "";
        this.score03 = s.getScore03() + "";
        this.score13 = s.getScore13() + "";
        this.score23 = s.getScore23() + "";
        this.score04 = s.getScore04() + "";
        this.score14 = s.getScore14() + "";
        this.score24 = s.getScore24() + "";
        this.score05 = s.getScore05() + "";
        this.score15 = s.getScore15() + "";
        this.score25 = s.getScore25() + "";
        this.scoreAElse = s.getScoreAElse() + "";
        this.oddsUpdateTime = s.getStartDateTime();
        this.updateTime = s.getUpdateTime();
    }

    @Id
    @Column(name = "id")
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "match_code")
    public String getMatchCode() {

        return matchCode;
    }

    public void setMatchCode(String matchCode) {

        this.matchCode = matchCode;
    }

    @Basic
    @Column(name = "league")
    public String getLeague() {

        return league;
    }

    public void setLeague(String league) {

        this.league = league;
    }

    @Basic
    @Column(name = "home_team")
    public String getHomeTeam() {

        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {

        this.homeTeam = homeTeam;
    }

    @Basic
    @Column(name = "away_team")
    public String getAwayTeam() {

        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {

        this.awayTeam = awayTeam;
    }

    @Basic
    @Column(name = "had_h")
    public String getHadH() {

        return hadH;
    }

    public void setHadH(String hadH) {

        this.hadH = hadH;
    }

    @Basic
    @Column(name = "had_d")
    public String getHadD() {

        return hadD;
    }

    public void setHadD(String hadD) {

        this.hadD = hadD;
    }

    @Basic
    @Column(name = "had_a")
    public String getHadA() {

        return hadA;
    }

    public void setHadA(String hadA) {

        this.hadA = hadA;
    }

    @Basic
    @Column(name = "hhad_h")
    public String getHhadH() {

        return hhadH;
    }

    public void setHhadH(String hhadH) {

        this.hhadH = hhadH;
    }

    @Basic
    @Column(name = "hhad_d")
    public String getHhadD() {

        return hhadD;
    }

    public void setHhadD(String hhadD) {

        this.hhadD = hhadD;
    }

    @Basic
    @Column(name = "hhad_a")
    public String getHhadA() {

        return hhadA;
    }

    public void setHhadA(String hhadA) {

        this.hhadA = hhadA;
    }

    @Basic
    @Column(name = "hhad_line")
    public String getHhadLine() {

        return hhadLine;
    }

    public void setHhadLine(String hhadLine) {

        this.hhadLine = hhadLine;
    }

    @Basic
    @Column(name = "hafu_hh")
    public String getHafuHh() {

        return hafuHh;
    }

    public void setHafuHh(String hafuHh) {

        this.hafuHh = hafuHh;
    }

    @Basic
    @Column(name = "hafu_hd")
    public String getHafuHd() {

        return hafuHd;
    }

    public void setHafuHd(String hafuHd) {

        this.hafuHd = hafuHd;
    }

    @Basic
    @Column(name = "hafu_ha")
    public String getHafuHa() {

        return hafuHa;
    }

    public void setHafuHa(String hafuHa) {

        this.hafuHa = hafuHa;
    }

    @Basic
    @Column(name = "hafu_dh")
    public String getHafuDh() {

        return hafuDh;
    }

    public void setHafuDh(String hafuDh) {

        this.hafuDh = hafuDh;
    }

    @Basic
    @Column(name = "hafu_dd")
    public String getHafuDd() {

        return hafuDd;
    }

    public void setHafuDd(String hafuDd) {

        this.hafuDd = hafuDd;
    }

    @Basic
    @Column(name = "hafu_da")
    public String getHafuDa() {

        return hafuDa;
    }

    public void setHafuDa(String hafuDa) {

        this.hafuDa = hafuDa;
    }

    @Basic
    @Column(name = "hafu_ah")
    public String getHafuAh() {

        return hafuAh;
    }

    public void setHafuAh(String hafuAh) {

        this.hafuAh = hafuAh;
    }

    @Basic
    @Column(name = "hafu_ad")
    public String getHafuAd() {

        return hafuAd;
    }

    public void setHafuAd(String hafuAd) {

        this.hafuAd = hafuAd;
    }

    @Basic
    @Column(name = "hafu_aa")
    public String getHafuAa() {

        return hafuAa;
    }

    public void setHafuAa(String hafuAa) {

        this.hafuAa = hafuAa;
    }

    @Basic
    @Column(name = "ttg_0")
    public String getTtg0() {

        return ttg0;
    }

    public void setTtg0(String ttg0) {

        this.ttg0 = ttg0;
    }

    @Basic
    @Column(name = "ttg_1")
    public String getTtg1() {

        return ttg1;
    }

    public void setTtg1(String ttg1) {

        this.ttg1 = ttg1;
    }

    @Basic
    @Column(name = "ttg_2")
    public String getTtg2() {

        return ttg2;
    }

    public void setTtg2(String ttg2) {

        this.ttg2 = ttg2;
    }

    @Basic
    @Column(name = "ttg_3")
    public String getTtg3() {

        return ttg3;
    }

    public void setTtg3(String ttg3) {

        this.ttg3 = ttg3;
    }

    @Basic
    @Column(name = "ttg_4")
    public String getTtg4() {

        return ttg4;
    }

    public void setTtg4(String ttg4) {

        this.ttg4 = ttg4;
    }

    @Basic
    @Column(name = "ttg_5")
    public String getTtg5() {

        return ttg5;
    }

    public void setTtg5(String ttg5) {

        this.ttg5 = ttg5;
    }

    @Basic
    @Column(name = "ttg_6")
    public String getTtg6() {

        return ttg6;
    }

    public void setTtg6(String ttg6) {

        this.ttg6 = ttg6;
    }

    @Basic
    @Column(name = "ttg_7up")
    public String getTtg7Up() {

        return ttg7Up;
    }

    public void setTtg7Up(String ttg7Up) {

        this.ttg7Up = ttg7Up;
    }

    @Basic
    @Column(name = "score1_0")
    public String getScore10() {

        return score10;
    }

    public void setScore10(String score10) {

        this.score10 = score10;
    }

    @Basic
    @Column(name = "score2_0")
    public String getScore20() {

        return score20;
    }

    public void setScore20(String score20) {

        this.score20 = score20;
    }

    @Basic
    @Column(name = "score2_1")
    public String getScore21() {

        return score21;
    }

    public void setScore21(String score21) {

        this.score21 = score21;
    }

    @Basic
    @Column(name = "score3_0")
    public String getScore30() {

        return score30;
    }

    public void setScore30(String score30) {

        this.score30 = score30;
    }

    @Basic
    @Column(name = "score3_1")
    public String getScore31() {

        return score31;
    }

    public void setScore31(String score31) {

        this.score31 = score31;
    }

    @Basic
    @Column(name = "score3_2")
    public String getScore32() {

        return score32;
    }

    public void setScore32(String score32) {

        this.score32 = score32;
    }

    @Basic
    @Column(name = "score4_0")
    public String getScore40() {

        return score40;
    }

    public void setScore40(String score40) {

        this.score40 = score40;
    }

    @Basic
    @Column(name = "score4_1")
    public String getScore41() {

        return score41;
    }

    public void setScore41(String score41) {

        this.score41 = score41;
    }

    @Basic
    @Column(name = "score4_2")
    public String getScore42() {

        return score42;
    }

    public void setScore42(String score42) {

        this.score42 = score42;
    }

    @Basic
    @Column(name = "score5_0")
    public String getScore50() {

        return score50;
    }

    public void setScore50(String score50) {

        this.score50 = score50;
    }

    @Basic
    @Column(name = "score5_1")
    public String getScore51() {

        return score51;
    }

    public void setScore51(String score51) {

        this.score51 = score51;
    }

    @Basic
    @Column(name = "score5_2")
    public String getScore52() {

        return score52;
    }

    public void setScore52(String score52) {

        this.score52 = score52;
    }

    @Basic
    @Column(name = "score_h_else")
    public String getScoreHElse() {

        return scoreHElse;
    }

    public void setScoreHElse(String scoreHElse) {

        this.scoreHElse = scoreHElse;
    }

    @Basic
    @Column(name = "score0_0")
    public String getScore00() {

        return score00;
    }

    public void setScore00(String score00) {

        this.score00 = score00;
    }

    @Basic
    @Column(name = "score1_1")
    public String getScore11() {

        return score11;
    }

    public void setScore11(String score11) {

        this.score11 = score11;
    }

    @Basic
    @Column(name = "score2_2")
    public String getScore22() {

        return score22;
    }

    public void setScore22(String score22) {

        this.score22 = score22;
    }

    @Basic
    @Column(name = "score3_3")
    public String getScore33() {

        return score33;
    }

    public void setScore33(String score33) {

        this.score33 = score33;
    }

    @Basic
    @Column(name = "score_d_else")
    public String getScoreDElse() {

        return scoreDElse;
    }

    public void setScoreDElse(String scoreDElse) {

        this.scoreDElse = scoreDElse;
    }

    @Basic
    @Column(name = "score0_1")
    public String getScore01() {

        return score01;
    }

    public void setScore01(String score01) {

        this.score01 = score01;
    }

    @Basic
    @Column(name = "score0_2")
    public String getScore02() {

        return score02;
    }

    public void setScore02(String score02) {

        this.score02 = score02;
    }

    @Basic
    @Column(name = "score1_2")
    public String getScore12() {

        return score12;
    }

    public void setScore12(String score12) {

        this.score12 = score12;
    }

    @Basic
    @Column(name = "score0_3")
    public String getScore03() {

        return score03;
    }

    public void setScore03(String score03) {

        this.score03 = score03;
    }

    @Basic
    @Column(name = "score1_3")
    public String getScore13() {

        return score13;
    }

    public void setScore13(String score13) {

        this.score13 = score13;
    }

    @Basic
    @Column(name = "score2_3")
    public String getScore23() {

        return score23;
    }

    public void setScore23(String score23) {

        this.score23 = score23;
    }

    @Basic
    @Column(name = "score0_4")
    public String getScore04() {

        return score04;
    }

    public void setScore04(String score04) {

        this.score04 = score04;
    }

    @Basic
    @Column(name = "score1_4")
    public String getScore14() {

        return score14;
    }

    public void setScore14(String score14) {

        this.score14 = score14;
    }

    @Basic
    @Column(name = "score2_4")
    public String getScore24() {

        return score24;
    }

    public void setScore24(String score24) {

        this.score24 = score24;
    }

    @Basic
    @Column(name = "score0_5")
    public String getScore05() {

        return score05;
    }

    public void setScore05(String score05) {

        this.score05 = score05;
    }

    @Basic
    @Column(name = "score1_5")
    public String getScore15() {

        return score15;
    }

    public void setScore15(String score15) {

        this.score15 = score15;
    }

    @Basic
    @Column(name = "score2_5")
    public String getScore25() {

        return score25;
    }

    public void setScore25(String score25) {

        this.score25 = score25;
    }

    @Basic
    @Column(name = "score_a_else")
    public String getScoreAElse() {

        return scoreAElse;
    }

    public void setScoreAElse(String scoreAElse) {

        this.scoreAElse = scoreAElse;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportteryAllModel that = (SportteryAllModel) o;

        if (matchCode != null ? !matchCode.equals(that.matchCode) : that.matchCode != null) return false;
        if (league != null ? !league.equals(that.league) : that.league != null) return false;
        if (homeTeam != null ? !homeTeam.equals(that.homeTeam) : that.homeTeam != null) return false;
        if (awayTeam != null ? !awayTeam.equals(that.awayTeam) : that.awayTeam != null) return false;
        if (hadH != null ? !hadH.equals(that.hadH) : that.hadH != null) return false;
        if (hadD != null ? !hadD.equals(that.hadD) : that.hadD != null) return false;
        if (hadA != null ? !hadA.equals(that.hadA) : that.hadA != null) return false;
        if (hhadH != null ? !hhadH.equals(that.hhadH) : that.hhadH != null) return false;
        if (hhadD != null ? !hhadD.equals(that.hhadD) : that.hhadD != null) return false;
        if (hhadA != null ? !hhadA.equals(that.hhadA) : that.hhadA != null) return false;
        if (hhadLine != null ? !hhadLine.equals(that.hhadLine) : that.hhadLine != null) return false;
        if (hafuHh != null ? !hafuHh.equals(that.hafuHh) : that.hafuHh != null) return false;
        if (hafuHd != null ? !hafuHd.equals(that.hafuHd) : that.hafuHd != null) return false;
        if (hafuHa != null ? !hafuHa.equals(that.hafuHa) : that.hafuHa != null) return false;
        if (hafuDh != null ? !hafuDh.equals(that.hafuDh) : that.hafuDh != null) return false;
        if (hafuDd != null ? !hafuDd.equals(that.hafuDd) : that.hafuDd != null) return false;
        if (hafuDa != null ? !hafuDa.equals(that.hafuDa) : that.hafuDa != null) return false;
        if (hafuAh != null ? !hafuAh.equals(that.hafuAh) : that.hafuAh != null) return false;
        if (hafuAd != null ? !hafuAd.equals(that.hafuAd) : that.hafuAd != null) return false;
        if (hafuAa != null ? !hafuAa.equals(that.hafuAa) : that.hafuAa != null) return false;
        if (ttg0 != null ? !ttg0.equals(that.ttg0) : that.ttg0 != null) return false;
        if (ttg1 != null ? !ttg1.equals(that.ttg1) : that.ttg1 != null) return false;
        if (ttg2 != null ? !ttg2.equals(that.ttg2) : that.ttg2 != null) return false;
        if (ttg3 != null ? !ttg3.equals(that.ttg3) : that.ttg3 != null) return false;
        if (ttg4 != null ? !ttg4.equals(that.ttg4) : that.ttg4 != null) return false;
        if (ttg5 != null ? !ttg5.equals(that.ttg5) : that.ttg5 != null) return false;
        if (ttg6 != null ? !ttg6.equals(that.ttg6) : that.ttg6 != null) return false;
        if (ttg7Up != null ? !ttg7Up.equals(that.ttg7Up) : that.ttg7Up != null) return false;
        if (score10 != null ? !score10.equals(that.score10) : that.score10 != null) return false;
        if (score20 != null ? !score20.equals(that.score20) : that.score20 != null) return false;
        if (score21 != null ? !score21.equals(that.score21) : that.score21 != null) return false;
        if (score30 != null ? !score30.equals(that.score30) : that.score30 != null) return false;
        if (score31 != null ? !score31.equals(that.score31) : that.score31 != null) return false;
        if (score32 != null ? !score32.equals(that.score32) : that.score32 != null) return false;
        if (score40 != null ? !score40.equals(that.score40) : that.score40 != null) return false;
        if (score41 != null ? !score41.equals(that.score41) : that.score41 != null) return false;
        if (score42 != null ? !score42.equals(that.score42) : that.score42 != null) return false;
        if (score50 != null ? !score50.equals(that.score50) : that.score50 != null) return false;
        if (score51 != null ? !score51.equals(that.score51) : that.score51 != null) return false;
        if (score52 != null ? !score52.equals(that.score52) : that.score52 != null) return false;
        if (scoreHElse != null ? !scoreHElse.equals(that.scoreHElse) : that.scoreHElse != null) return false;
        if (score00 != null ? !score00.equals(that.score00) : that.score00 != null) return false;
        if (score11 != null ? !score11.equals(that.score11) : that.score11 != null) return false;
        if (score22 != null ? !score22.equals(that.score22) : that.score22 != null) return false;
        if (score33 != null ? !score33.equals(that.score33) : that.score33 != null) return false;
        if (scoreDElse != null ? !scoreDElse.equals(that.scoreDElse) : that.scoreDElse != null) return false;
        if (score01 != null ? !score01.equals(that.score01) : that.score01 != null) return false;
        if (score02 != null ? !score02.equals(that.score02) : that.score02 != null) return false;
        if (score12 != null ? !score12.equals(that.score12) : that.score12 != null) return false;
        if (score03 != null ? !score03.equals(that.score03) : that.score03 != null) return false;
        if (score13 != null ? !score13.equals(that.score13) : that.score13 != null) return false;
        if (score23 != null ? !score23.equals(that.score23) : that.score23 != null) return false;
        if (score04 != null ? !score04.equals(that.score04) : that.score04 != null) return false;
        if (score14 != null ? !score14.equals(that.score14) : that.score14 != null) return false;
        if (score24 != null ? !score24.equals(that.score24) : that.score24 != null) return false;
        if (score05 != null ? !score05.equals(that.score05) : that.score05 != null) return false;
        if (score15 != null ? !score15.equals(that.score15) : that.score15 != null) return false;
        if (score25 != null ? !score25.equals(that.score25) : that.score25 != null) return false;
        if (scoreAElse != null ? !scoreAElse.equals(that.scoreAElse) : that.scoreAElse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (matchCode != null ? matchCode.hashCode() : 0);
        result = 31 * result + (league != null ? league.hashCode() : 0);
        result = 31 * result + (homeTeam != null ? homeTeam.hashCode() : 0);
        result = 31 * result + (awayTeam != null ? awayTeam.hashCode() : 0);
        result = 31 * result + (hadH != null ? hadH.hashCode() : 0);
        result = 31 * result + (hadD != null ? hadD.hashCode() : 0);
        result = 31 * result + (hadA != null ? hadA.hashCode() : 0);
        result = 31 * result + (hhadH != null ? hhadH.hashCode() : 0);
        result = 31 * result + (hhadD != null ? hhadD.hashCode() : 0);
        result = 31 * result + (hhadA != null ? hhadA.hashCode() : 0);
        result = 31 * result + (hhadLine != null ? hhadLine.hashCode() : 0);
        result = 31 * result + (hafuHh != null ? hafuHh.hashCode() : 0);
        result = 31 * result + (hafuHd != null ? hafuHd.hashCode() : 0);
        result = 31 * result + (hafuHa != null ? hafuHa.hashCode() : 0);
        result = 31 * result + (hafuDh != null ? hafuDh.hashCode() : 0);
        result = 31 * result + (hafuDd != null ? hafuDd.hashCode() : 0);
        result = 31 * result + (hafuDa != null ? hafuDa.hashCode() : 0);
        result = 31 * result + (hafuAh != null ? hafuAh.hashCode() : 0);
        result = 31 * result + (hafuAd != null ? hafuAd.hashCode() : 0);
        result = 31 * result + (hafuAa != null ? hafuAa.hashCode() : 0);
        result = 31 * result + (ttg0 != null ? ttg0.hashCode() : 0);
        result = 31 * result + (ttg1 != null ? ttg1.hashCode() : 0);
        result = 31 * result + (ttg2 != null ? ttg2.hashCode() : 0);
        result = 31 * result + (ttg3 != null ? ttg3.hashCode() : 0);
        result = 31 * result + (ttg4 != null ? ttg4.hashCode() : 0);
        result = 31 * result + (ttg5 != null ? ttg5.hashCode() : 0);
        result = 31 * result + (ttg6 != null ? ttg6.hashCode() : 0);
        result = 31 * result + (ttg7Up != null ? ttg7Up.hashCode() : 0);
        result = 31 * result + (score10 != null ? score10.hashCode() : 0);
        result = 31 * result + (score20 != null ? score20.hashCode() : 0);
        result = 31 * result + (score21 != null ? score21.hashCode() : 0);
        result = 31 * result + (score30 != null ? score30.hashCode() : 0);
        result = 31 * result + (score31 != null ? score31.hashCode() : 0);
        result = 31 * result + (score32 != null ? score32.hashCode() : 0);
        result = 31 * result + (score40 != null ? score40.hashCode() : 0);
        result = 31 * result + (score41 != null ? score41.hashCode() : 0);
        result = 31 * result + (score42 != null ? score42.hashCode() : 0);
        result = 31 * result + (score50 != null ? score50.hashCode() : 0);
        result = 31 * result + (score51 != null ? score51.hashCode() : 0);
        result = 31 * result + (score52 != null ? score52.hashCode() : 0);
        result = 31 * result + (scoreHElse != null ? scoreHElse.hashCode() : 0);
        result = 31 * result + (score00 != null ? score00.hashCode() : 0);
        result = 31 * result + (score11 != null ? score11.hashCode() : 0);
        result = 31 * result + (score22 != null ? score22.hashCode() : 0);
        result = 31 * result + (score33 != null ? score33.hashCode() : 0);
        result = 31 * result + (scoreDElse != null ? scoreDElse.hashCode() : 0);
        result = 31 * result + (score01 != null ? score01.hashCode() : 0);
        result = 31 * result + (score02 != null ? score02.hashCode() : 0);
        result = 31 * result + (score12 != null ? score12.hashCode() : 0);
        result = 31 * result + (score03 != null ? score03.hashCode() : 0);
        result = 31 * result + (score13 != null ? score13.hashCode() : 0);
        result = 31 * result + (score23 != null ? score23.hashCode() : 0);
        result = 31 * result + (score04 != null ? score04.hashCode() : 0);
        result = 31 * result + (score14 != null ? score14.hashCode() : 0);
        result = 31 * result + (score24 != null ? score24.hashCode() : 0);
        result = 31 * result + (score05 != null ? score05.hashCode() : 0);
        result = 31 * result + (score15 != null ? score15.hashCode() : 0);
        result = 31 * result + (score25 != null ? score25.hashCode() : 0);
        result = 31 * result + (scoreAElse != null ? scoreAElse.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "SportteryAllEntity{" +
                "id=" + id +
                ", matchCode='" + matchCode + '\'' +
                ", league='" + league + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", hadH=" + hadH +
                ", hadD=" + hadD +
                ", hadA=" + hadA +
                ", hhadH=" + hhadH +
                ", hhadD=" + hhadD +
                ", hhadA=" + hhadA +
                ", hhadLine=" + hhadLine +
                ", hafuHh=" + hafuHh +
                ", hafuHd=" + hafuHd +
                ", hafuHa=" + hafuHa +
                ", hafuDh=" + hafuDh +
                ", hafuDd=" + hafuDd +
                ", hafuDa=" + hafuDa +
                ", hafuAh=" + hafuAh +
                ", hafuAd=" + hafuAd +
                ", hafuAa=" + hafuAa +
                ", ttg0=" + ttg0 +
                ", ttg1=" + ttg1 +
                ", ttg2=" + ttg2 +
                ", ttg3=" + ttg3 +
                ", ttg4=" + ttg4 +
                ", ttg5=" + ttg5 +
                ", ttg6=" + ttg6 +
                ", ttg7Up=" + ttg7Up +
                ", score10=" + score10 +
                ", score20=" + score20 +
                ", score21=" + score21 +
                ", score30=" + score30 +
                ", score31=" + score31 +
                ", score32=" + score32 +
                ", score40=" + score40 +
                ", score41=" + score41 +
                ", score42=" + score42 +
                ", score50=" + score50 +
                ", score51=" + score51 +
                ", score52=" + score52 +
                ", scoreHElse=" + scoreHElse +
                ", score00=" + score00 +
                ", score11=" + score11 +
                ", score22=" + score22 +
                ", score33=" + score33 +
                ", scoreDElse=" + scoreDElse +
                ", score01=" + score01 +
                ", score02=" + score02 +
                ", score12=" + score12 +
                ", score03=" + score03 +
                ", score13=" + score13 +
                ", score23=" + score23 +
                ", score04=" + score04 +
                ", score14=" + score14 +
                ", score24=" + score24 +
                ", score05=" + score05 +
                ", score15=" + score15 +
                ", score25=" + score25 +
                ", scoreAElse=" + scoreAElse +
                ", oddsUpdateTime=" + oddsUpdateTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Basic
    @Column(name = "odds_update_time")
    public Timestamp getOddsUpdateTime() {

        return oddsUpdateTime;
    }

    public void setOddsUpdateTime(Timestamp oddsUpdateTime) {

        this.oddsUpdateTime = oddsUpdateTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {

        this.updateTime = updateTime;
    }
}
