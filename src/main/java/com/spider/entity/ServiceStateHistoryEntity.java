package com.spider.entity;

import javax.persistence.*;

/**
 * Created by wsy on 2015/11/11.
 */
@Entity
@Table(name = "service_state_history")
public class ServiceStateHistoryEntity {

    private long id;

    private String service;

    private Long startTime;

    private Long endTime;

    private boolean success;

    private String errorMsg;

    public ServiceStateHistoryEntity() {
    }

    public ServiceStateHistoryEntity(ServiceStateEntity serviceStateEntity) {

        this.service = serviceStateEntity.getService();
        this.startTime = serviceStateEntity.getStartTime();
        this.endTime = serviceStateEntity.getEndTime();
        this.success = serviceStateEntity.isSuccess();
        this.errorMsg = serviceStateEntity.getErrorMsg();
    }

    @Id
    @Column(name = "ID")
    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "SERVICE")
    public String getService() {

        return service;
    }

    public void setService(String service) {

        this.service = service;
    }

    @Basic
    @Column(name = "START_TIME")
    public Long getStartTime() {

        return startTime;
    }

    public void setStartTime(Long startTime) {

        this.startTime = startTime;
    }

    @Basic
    @Column(name = "END_TIME")
    public Long getEndTime() {

        return endTime;
    }

    public void setEndTime(Long endTime) {

        this.endTime = endTime;
    }

    @Basic
    @Column(name = "SUCCESS")
    public boolean isSuccess() {

        return success;
    }

    public void setSuccess(boolean success) {

        this.success = success;
    }

    @Basic
    @Column(name = "ERROR_MSG")
    public String getErrorMsg() {

        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {

        this.errorMsg = errorMsg;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceStateHistoryEntity that = (ServiceStateHistoryEntity) o;

        if (id != that.id) return false;
        if (success != that.success) return false;
        if (service != null ? !service.equals(that.service) : that.service != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (errorMsg != null ? !errorMsg.equals(that.errorMsg) : that.errorMsg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (service != null ? service.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (success ? 1 : 0);
        result = 31 * result + (errorMsg != null ? errorMsg.hashCode() : 0);
        return result;
    }
}
