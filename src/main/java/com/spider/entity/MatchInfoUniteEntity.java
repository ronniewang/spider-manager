package com.spider.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wsy on 2016/2/26.
 */
@Entity
@Table(name = "match_info_unite", schema = "", catalog = "crawler")
@EntityListeners(AuditingEntityListener.class)
public class MatchInfoUniteEntity {

    private long id;

    private Long sportteryId;

    private Long sportteryAllId;

    private Long win310Id;

    private Long pinnacleId;

    private Long w500Id;

    private Date updateTime;

    private Date createTime;

    private String createdByUser;

    private String modifiedByUser;

    @Column(name = "created_by_user", nullable = false)
    @CreatedBy
    public String getCreatedByUser() {

        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {

        this.createdByUser = createdByUser;
    }

    @Column(name = "modified_by_user", nullable = false)
    @LastModifiedBy
    public String getModifiedByUser() {

        return modifiedByUser;
    }

    public void setModifiedByUser(String modifiedByUser) {

        this.modifiedByUser = modifiedByUser;
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
    @Column(name = "sporttery_id")
    public Long getSportteryId() {

        return sportteryId;
    }

    public void setSportteryId(Long sportteryId) {

        this.sportteryId = sportteryId;
    }

    @Basic
    @Column(name = "sporttery_all_id")
    public Long getSportteryAllId() {

        return sportteryAllId;
    }

    public void setSportteryAllId(Long sportteryAllId) {

        this.sportteryAllId = sportteryAllId;
    }

    @Basic
    @Column(name = "win310_id")
    public Long getWin310Id() {

        return win310Id;
    }

    public void setWin310Id(Long win310Id) {

        this.win310Id = win310Id;
    }

    @Basic
    @Column(name = "pinnacle_id")
    public Long getPinnacleId() {

        return pinnacleId;
    }

    public void setPinnacleId(Long pinnacleId) {

        this.pinnacleId = pinnacleId;
    }

    @Basic
    @Column(name = "w500_id")
    public Long getW500Id() {

        return w500Id;
    }

    public void setW500Id(Long w500Id) {

        this.w500Id = w500Id;
    }

    @Basic
    @LastModifiedDate
    @Column(name = "update_time")
    public Date getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {

        this.updateTime = updateTime;
    }

    @Basic
    @CreatedDate
    @Column(name = "create_time")
    public Date getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchInfoUniteEntity that = (MatchInfoUniteEntity) o;

        if (id != that.id) return false;
        if (sportteryId != null ? !sportteryId.equals(that.sportteryId) : that.sportteryId != null) return false;
        if (sportteryAllId != null ? !sportteryAllId.equals(that.sportteryAllId) : that.sportteryAllId != null)
            return false;
        if (win310Id != null ? !win310Id.equals(that.win310Id) : that.win310Id != null) return false;
        if (pinnacleId != null ? !pinnacleId.equals(that.pinnacleId) : that.pinnacleId != null) return false;
        if (w500Id != null ? !w500Id.equals(that.w500Id) : that.w500Id != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (sportteryId != null ? sportteryId.hashCode() : 0);
        result = 31 * result + (sportteryAllId != null ? sportteryAllId.hashCode() : 0);
        result = 31 * result + (win310Id != null ? win310Id.hashCode() : 0);
        result = 31 * result + (pinnacleId != null ? pinnacleId.hashCode() : 0);
        result = 31 * result + (w500Id != null ? w500Id.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
