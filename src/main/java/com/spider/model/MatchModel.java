package com.spider.model;

import com.spider.entity.GamingCompanyConfig;
import com.spider.global.AbsenceState;

import java.util.List;

public class MatchModel implements Comparable<MatchModel> {

    public static final String ABSENCE_STATE_DEFAULT = AbsenceState.Yes.value();

    private Long id;//

    private String matchDate;// 2014-01-01 00:00:00

    private String matchCode;//

    private String matchLeague;//

    private String homeTeam;//

    private String awayTeam;//

    private String state;// 0 完全匹配 1彩客不存在 2数据不同

    private String absenceState = ABSENCE_STATE_DEFAULT;// SBC是否有该场比赛，1表示不缺少，0表示缺少

    private List<GamingCompanyConfig> gamingCompanyConfigs;

    public List<GamingCompanyConfig> getGamingCompanyConfigs() {

        return gamingCompanyConfigs;
    }

    public void setGamingCompanyConfigs(List<GamingCompanyConfig> gamingCompanyConfigs) {

        this.gamingCompanyConfigs = gamingCompanyConfigs;
    }

    public String getAbsenceState() {

        return absenceState;
    }

    public void setAbsenceState(String absenceState) {

        this.absenceState = absenceState;
    }

    public Long getId() {

        return this.id;
    }

    public void setId(Long id) {

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

    @Override
    public int compareTo(MatchModel o) {

        return Integer.valueOf(this.matchCode) - Integer.valueOf(o.matchCode);
    }
}
