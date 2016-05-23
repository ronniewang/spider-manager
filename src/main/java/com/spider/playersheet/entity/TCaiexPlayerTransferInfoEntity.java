package com.spider.playersheet.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/27.
 */
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

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Long getFromTeamId() {

        return fromTeamId;
    }

    public void setFromTeamId(Long fromTeamId) {

        this.fromTeamId = fromTeamId;
    }

    public Long getToTeamId() {

        return toTeamId;
    }

    public void setToTeamId(Long toTeamId) {

        this.toTeamId = toTeamId;
    }

    public String getTransferSeason() {

        return transferSeason;
    }

    public void setTransferSeason(String transferSeason) {

        this.transferSeason = transferSeason;
    }

    public String getTransferDate() {

        return transferDate;
    }

    public void setTransferDate(String transferDate) {

        this.transferDate = transferDate;
    }

    public String getContractExpireDate() {

        return contractExpireDate;
    }

    public void setContractExpireDate(String contractExpireDate) {

        this.contractExpireDate = contractExpireDate;
    }

    public BigDecimal getTransferPrice() {

        return transferPrice;
    }

    public void setTransferPrice(BigDecimal transferPrice) {

        this.transferPrice = transferPrice;
    }

    public String getTransferType() {

        return transferType;
    }

    public void setTransferType(String transferType) {

        this.transferType = transferType;
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

    @Override
    public String toString() {

        return "TCaiexPlayerTransferInfoEntity{" +
                "id=" + id +
                ", fromTeamId=" + fromTeamId +
                ", toTeamId=" + toTeamId +
                ", transferSeason='" + transferSeason + '\'' +
                ", transferDate='" + transferDate + '\'' +
                ", contractExpireDate='" + contractExpireDate + '\'' +
                ", transferPrice=" + transferPrice +
                ", transferType='" + transferType + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
