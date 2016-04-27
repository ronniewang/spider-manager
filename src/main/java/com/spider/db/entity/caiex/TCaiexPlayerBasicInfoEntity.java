package com.spider.db.entity.caiex;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/27.
 */
@Entity
@Table(name = "t_caiex_player_basic_info")
public class TCaiexPlayerBasicInfoEntity {

    private Long id;

    private Long currentWorkTeamId;

    private String name;

    private String nameEnglish;

    private String nameTraditional;

    private Integer height;

    private Integer weight;

    private BigDecimal price;

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
    @Column(name = "current_work_team_id")
    public Long getCurrentWorkTeamId() {

        return currentWorkTeamId;
    }

    public void setCurrentWorkTeamId(Long currentWorkTeamId) {

        this.currentWorkTeamId = currentWorkTeamId;
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
    @Column(name = "name_english", nullable = true, length = 255)
    public String getNameEnglish() {

        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {

        this.nameEnglish = nameEnglish;
    }

    @Basic
    @Column(name = "name_traditional", nullable = true, length = 255)
    public String getNameTraditional() {

        return nameTraditional;
    }

    public void setNameTraditional(String nameTraditional) {

        this.nameTraditional = nameTraditional;
    }

    @Basic
    @Column(name = "height", nullable = true)
    public Integer getHeight() {

        return height;
    }

    public void setHeight(Integer height) {

        this.height = height;
    }

    @Basic
    @Column(name = "weight", nullable = true)
    public Integer getWeight() {

        return weight;
    }

    public void setWeight(Integer weight) {

        this.weight = weight;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public BigDecimal getPrice() {

        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
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

        TCaiexPlayerBasicInfoEntity that = (TCaiexPlayerBasicInfoEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (nameEnglish != null ? !nameEnglish.equals(that.nameEnglish) : that.nameEnglish != null) return false;
        if (nameTraditional != null ? !nameTraditional.equals(that.nameTraditional) : that.nameTraditional != null)
            return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (nameEnglish != null ? nameEnglish.hashCode() : 0);
        result = 31 * result + (nameTraditional != null ? nameTraditional.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
