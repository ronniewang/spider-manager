package com.spider.db.entity.caiex;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/27.
 */
@Entity
@Table(name = "t_caiex_player_transfer_info")
public class TCaiexPlayerTransferInfoEntity {

    private long id;

    private Long fromTeamId;

    private Long toTeamId;

    private String transferSeason;

    private String transferDate;

    private String contractExpireDate;

    private BigDecimal transferPrice;

    private String transferType;

    private Date updateTime;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "from_team_id", nullable = true)
    public Long getFromTeamId() {

        return fromTeamId;
    }

    public void setFromTeamId(Long fromTeamId) {

        this.fromTeamId = fromTeamId;
    }

    @Basic
    @Column(name = "to_team_id", nullable = true)
    public Long getToTeamId() {

        return toTeamId;
    }

    public void setToTeamId(Long toTeamId) {

        this.toTeamId = toTeamId;
    }

    @Basic
    @Column(name = "transfer_season", nullable = true, length = 255)
    public String getTransferSeason() {

        return transferSeason;
    }

    public void setTransferSeason(String transferSeason) {

        this.transferSeason = transferSeason;
    }

    @Basic
    @Column(name = "transfer_date", nullable = true, length = 255)
    public String getTransferDate() {

        return transferDate;
    }

    public void setTransferDate(String transferDate) {

        this.transferDate = transferDate;
    }

    @Basic
    @Column(name = "contract_expire_date", nullable = true, length = 255)
    public String getContractExpireDate() {

        return contractExpireDate;
    }

    public void setContractExpireDate(String contractExpireDate) {

        this.contractExpireDate = contractExpireDate;
    }

    @Basic
    @Column(name = "transfer_price", nullable = true, precision = 0)
    public BigDecimal getTransferPrice() {

        return transferPrice;
    }

    public void setTransferPrice(BigDecimal transferPrice) {

        this.transferPrice = transferPrice;
    }

    @Basic
    @Column(name = "transfer_type", nullable = true, length = 255)
    public String getTransferType() {

        return transferType;
    }

    public void setTransferType(String transferType) {

        this.transferType = transferType;
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

        TCaiexPlayerTransferInfoEntity that = (TCaiexPlayerTransferInfoEntity) o;

        if (id != that.id) return false;
        if (fromTeamId != null ? !fromTeamId.equals(that.fromTeamId) : that.fromTeamId != null) return false;
        if (toTeamId != null ? !toTeamId.equals(that.toTeamId) : that.toTeamId != null) return false;
        if (transferSeason != null ? !transferSeason.equals(that.transferSeason) : that.transferSeason != null)
            return false;
        if (transferDate != null ? !transferDate.equals(that.transferDate) : that.transferDate != null) return false;
        if (contractExpireDate != null ? !contractExpireDate.equals(that.contractExpireDate) : that.contractExpireDate != null)
            return false;
        if (transferPrice != null ? !transferPrice.equals(that.transferPrice) : that.transferPrice != null)
            return false;
        if (transferType != null ? !transferType.equals(that.transferType) : that.transferType != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (fromTeamId != null ? fromTeamId.hashCode() : 0);
        result = 31 * result + (toTeamId != null ? toTeamId.hashCode() : 0);
        result = 31 * result + (transferSeason != null ? transferSeason.hashCode() : 0);
        result = 31 * result + (transferDate != null ? transferDate.hashCode() : 0);
        result = 31 * result + (contractExpireDate != null ? contractExpireDate.hashCode() : 0);
        result = 31 * result + (transferPrice != null ? transferPrice.hashCode() : 0);
        result = 31 * result + (transferType != null ? transferType.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
