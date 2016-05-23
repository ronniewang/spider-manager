package com.spider.playersheet.entity;

import java.util.Date;

/**
 * Created by ronnie on 2016/4/27.
 */
public class TCaiexLeagueEntity {

    private Long id;

    private String name;

    private String nameAbbr;

    private Date updateTime;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getNameAbbr() {

        return nameAbbr;
    }

    public void setNameAbbr(String nameAbbr) {

        this.nameAbbr = nameAbbr;
    }

    public Date getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCaiexLeagueEntity that = (TCaiexLeagueEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nameAbbr != null ? !nameAbbr.equals(that.nameAbbr) : that.nameAbbr != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nameAbbr != null ? nameAbbr.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {

        return "TCaiexLeagueEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameAbbr='" + nameAbbr + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
