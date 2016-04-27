package com.spider.db.entity.caiex;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/27.
 */
@Entity
@Table(name = "t_caiex_team")
public class TCaiexTeamEntity {

    private Long id;

    private String name;

    private Date updateTime;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
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

        TCaiexTeamEntity that = (TCaiexTeamEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
