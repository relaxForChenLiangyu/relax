package com.example.cynthia.relax.beans;

import java.io.Serializable;

public class PreOrderStatusBean implements Serializable {
    private Integer isOrdered;

    private Integer isFree;

    private Integer timeslotId;

    private Integer specialistId;

    private Integer day;

    public Integer getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(Integer timeslotId) {
        this.timeslotId = timeslotId;
    }

    public Integer getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Integer specialistId) {
        this.specialistId = specialistId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getIsOrdered() {
        return isOrdered;
    }

    public void setIsOrdered(Integer isOrdered) {
        this.isOrdered = isOrdered;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }
}
