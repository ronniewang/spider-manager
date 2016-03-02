package com.spider.db.entity;

import javax.persistence.*;

/**
 * Created by wsy on 2015/12/21.
 */
@Entity
@Table(name = "sbc_team")
public class SbcTeamEntity {


    private long id;

    private String sbcName;

    private String win310Name;

    private String win310NameEnglish;

    private String sportteryName;

    private String pinnacleNameEnglish;

    private String w500Name;

    private String w500NameEnglish;

    @Id
    @Column(name = "id")
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "sbc_name")
    public String getSbcName() {

        return sbcName;
    }

    public void setSbcName(String sbcName) {

        this.sbcName = sbcName;
    }

    @Basic
    @Column(name = "win310_name")
    public String getWin310Name() {

        return win310Name;
    }

    public void setWin310Name(String win310Name) {

        this.win310Name = win310Name;
    }

    @Basic
    @Column(name = "win310_name_english")
    public String getWin310NameEnglish() {

        return win310NameEnglish;
    }

    public void setWin310NameEnglish(String win310NameEnglish) {

        this.win310NameEnglish = win310NameEnglish;
    }

    @Basic
    @Column(name = "sporttery_name")
    public String getSportteryName() {

        return sportteryName;
    }

    public void setSportteryName(String sportteryName) {

        this.sportteryName = sportteryName;
    }

    @Basic
    @Column(name = "pinnacle_name_english")
    public String getPinnacleNameEnglish() {

        return pinnacleNameEnglish;
    }

    public void setPinnacleNameEnglish(String pinnacleNameEnglish) {

        this.pinnacleNameEnglish = pinnacleNameEnglish;
    }

    @Basic
    @Column(name = "w500_name")
    public String getW500Name() {

        return w500Name;
    }

    public void setW500Name(String w500Name) {

        this.w500Name = w500Name;
    }

    @Basic
    @Column(name = "w500_name_english")
    public String getW500NameEnglish() {

        return w500NameEnglish;
    }

    public void setW500NameEnglish(String w500NameEnglish) {

        this.w500NameEnglish = w500NameEnglish;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SbcTeamEntity that = (SbcTeamEntity) o;

        if (id != that.id) return false;
        if (sbcName != null ? !sbcName.equals(that.sbcName) : that.sbcName != null) return false;
        if (win310Name != null ? !win310Name.equals(that.win310Name) : that.win310Name != null) return false;
        if (win310NameEnglish != null ? !win310NameEnglish.equals(that.win310NameEnglish) : that.win310NameEnglish != null)
            return false;
        if (sportteryName != null ? !sportteryName.equals(that.sportteryName) : that.sportteryName != null)
            return false;
        if (pinnacleNameEnglish != null ? !pinnacleNameEnglish.equals(that.pinnacleNameEnglish) : that.pinnacleNameEnglish != null)
            return false;
        if (w500Name != null ? !w500Name.equals(that.w500Name) : that.w500Name != null) return false;
        if (w500NameEnglish != null ? !w500NameEnglish.equals(that.w500NameEnglish) : that.w500NameEnglish != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (sbcName != null ? sbcName.hashCode() : 0);
        result = 31 * result + (win310Name != null ? win310Name.hashCode() : 0);
        result = 31 * result + (win310NameEnglish != null ? win310NameEnglish.hashCode() : 0);
        result = 31 * result + (sportteryName != null ? sportteryName.hashCode() : 0);
        result = 31 * result + (pinnacleNameEnglish != null ? pinnacleNameEnglish.hashCode() : 0);
        result = 31 * result + (w500Name != null ? w500Name.hashCode() : 0);
        result = 31 * result + (w500NameEnglish != null ? w500NameEnglish.hashCode() : 0);
        return result;
    }
}
